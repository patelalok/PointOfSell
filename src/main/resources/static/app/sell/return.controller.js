(function() {
    'use strict';

    angular.module('sampleApp')
        .controller('ReturnController', ReturnController);

    ReturnController.$inject = [ '$scope', '$rootScope', 'device.utility',
        'GlobalVariable', 'DialogFactory', 'modalService',
        'RestrictedCharacter.Types', 'dataService', '$state','GlobalConstants'];

    function ReturnController($scope, $rootScope, device, GlobalVariable,
                              DialogFactory, modalService, restrictCharacter, dataService, $state,GlobalConstants) {

        $scope.device = device;
        $scope.GlobalVariable = GlobalVariable;
        $scope.restrictCharacter = restrictCharacter;
        GlobalVariable.isLoginPage = false;
        $scope.itemDeleted = false;

        var i = 0;
        $scope.pageSize = 10;

        $rootScope.returnData = [];
        $scope.productNames = [];
        $rootScope.returnBackData = [];

        $scope.removeRow = function(itemNo,details) {

            GlobalVariable.itemNoToDelete = itemNo;
            $scope.itemDeleted = true;
            $rootScope.returnBackData
                .push({
                    "itemNo" : details.itemNo,
                    "item" : details.item,
                    "quantity" : details.quantity,
                    "retail" : details.retail,
                    "discount" : details.discount,
                    "total" : details.total,
                    "stock" : details.stock,
                    "costPrice" : details.costPrice
                });
            modalService.showModal('', {
                isCancel : true
            }, "Are you Sure Want to Delete ? ", $scope.callBackAction);
            /*
             * var index = -1; var comArr = eval( $rootScope.returnData ); for(
             * var i = 0; i < comArr.length; i++ ) { if( comArr[i].itemNo ===
             * itemNo ) { index = i; break; } } if( index === -1 ) { alert(
             * "Something gone wrong" ); } $rootScope.returnData.splice( index,
             * 1 );
             */
        };
        $scope.callBackAction = function(isOKClicked) {

            if (isOKClicked) {
                var index = -1;
                var comArr = eval($rootScope.returnData);
                for (var i = 0; i < comArr.length; i++) {
                    if (comArr[i].itemNo === GlobalVariable.itemNoToDelete) {
                        index = i;
                        break;
                    }
                }
                if (index === -1) {
                    alert("Something gone wrong");
                }
                $rootScope.returnData.splice(index, 1);
                $scope.loadCheckOutData();
                $scope.loadDeletedCheckoutData();
            }
        }

        function callbackPayment() {
            $scope.totalQuantity = 0;
            $scope.subTotal = 0;
            $scope.productTotal = 0;
            $scope.undelProdTotal =0;

        }

        $scope.loadCheckOutData = function() {
            $scope.totalQuantity = 0;
            $scope.subTotal = 0;
            $scope.totalDelRetail = 0;
            $scope.totalDelRetailDisc = 0;
            $scope.totalDisc =0;


            for (var i = 0; i < $rootScope.returnData.length; i++) {
                $scope.totalQuantity = parseFloat($scope.totalQuantity)
                    + parseFloat($rootScope.returnData[i].quantity);
                $scope.subTotal = parseFloat($scope.subTotal)
                    + parseFloat($rootScope.returnData[i].total);
                $scope.totalRetail = parseFloat($scope.totalRetail)+parseFloat($rootScope.returnData[i].retail);
                $scope.totalRetailDisc = parseFloat($scope.totalRetailDisc)+parseFloat($rootScope.returnData[i].discount);

            }
           // $scope.totalDisc = Number(parseFloat($scope.totalRetail)-parseFloat($scope.totalRetailDisc)).toFixed(2);
            GlobalVariable.quantityTotal = $scope.totalQuantity;
            GlobalVariable.totalSub = $scope.subTotal;
            if ($scope.totalDisc == undefined)
                $scope.totalDisc = 0;

            GlobalVariable.discountTotal = $scope.totalDisc;

                $scope.productTotalWithoutTax = Number(
                    parseFloat($scope.subTotal)).toFixed(2);


            if ($scope.productTotalWithoutTax == 'NaN') {
                $scope.productTotalWithoutTax = 0;
            }

            if ($scope.selectReturnTax == undefined)
                $scope.totalTax = 0;
            else if ($scope.selectReturnTax == 'default')
                $scope.totalTax = parseFloat($scope.totalDefaultTax);
            else if ($scope.selectReturnTax == 'noTax')
                $scope.totalTax = 0;

            GlobalVariable.taxTotal = parseFloat($scope.productTotalWithoutTax)
                * (parseFloat($scope.totalTax) / 100);
            $scope.productTotal = Number(
                parseFloat($scope.productTotalWithoutTax)
                + (((parseFloat($scope.productTotalWithoutTax) * parseFloat($scope.totalTax))) / 100))
                .toFixed(2);
            $scope.undelProdTotal = Number(
                parseFloat($scope.productTotalWithoutTax)
                + (((parseFloat($scope.productTotalWithoutTax) * parseFloat($scope.totalTax))) / 100))
                .toFixed(2);

            if ($scope.returnprevBalance > 0) {
                $scope.productTotal = parseFloat($scope.productTotal)
                    + parseFloat($scope.returnprevBalance);
                $scope.undelProdTotal = parseFloat($scope.productTotal)
                    + parseFloat($scope.returnprevBalance);
            }
            $rootScope.totalReturnPayment = $scope.productTotal;
            GlobalVariable.checkOuttotal = $rootScope.totalReturnPayment;
            $scope.getLastTransId();
        }
        function render() {
            $scope.currentPageIndexArr = 0;
            $scope.totalTax = GlobalVariable.totalTaxSetup;
            getTaxDetails();
           // $scope.loadCheckOutData();
            if (GlobalVariable.returnProduct == true) {
                for (var i = 0; i < GlobalVariable.getReturnDetails[0].transactionLineItemDtoList.length; i++) {
                    $rootScope.returnData
                        .push({
                            "itemNo" : GlobalVariable.getReturnDetails[0].transactionLineItemDtoList[i].productNumber,
                            "item" : GlobalVariable.getReturnDetails[0].transactionLineItemDtoList[i].productDescription,
                            "quantity" : GlobalVariable.getReturnDetails[0].transactionLineItemDtoList[i].quantity,
                            "retail" : GlobalVariable.getReturnDetails[0].transactionLineItemDtoList[i].retail,
                            "discount" : GlobalVariable.getReturnDetails[0].transactionLineItemDtoList[i].retailWithDis,
                            "total" : GlobalVariable.getReturnDetails[0].transactionLineItemDtoList[i].totalProductPrice,
                            "stock" : GlobalVariable.getReturnDetails[0].transactionLineItemDtoList[i].quantity,
                            "costPrice" : GlobalVariable.getReturnDetails[0].transactionLineItemDtoList[i].cost,
                            "totalWithTax":GlobalVariable.getReturnDetails[0].transactionLineItemDtoList[i].totalProductPriceWithTax
                        });


                }
                $scope.totalQuantity = GlobalVariable.getReturnDetails[0].transactionDtoList[0].totalQuantity;
                $scope.subTotal = GlobalVariable.getReturnDetails[0].transactionDtoList[0].subTotal;
                $scope.totalDisc = GlobalVariable.getReturnDetails[0].transactionDtoList[0].discount;
                $scope.totalTax = GlobalVariable.getReturnDetails[0].transactionDtoList[0].tax;
                $scope.productTotal = GlobalVariable.getReturnDetails[0].transactionDtoList[0].totalAmount;
                $rootScope.totalReturnPayment = $scope.productTotal;
                $scope.returnAmount = $rootScope.totalReturnPayment
                $scope.returnDate = GlobalVariable.getReturnDetails[0].transactionDtoList[0].transactionDate;
                $scope.returnId = Math.round(((Math.random() * 10) * 10)) * 13;
                $scope.previousId = GlobalVariable.getReturnDetails[0].transactionDtoList[0].transactionCompId
                $scope.userIdReturn = GlobalVariable.getReturnDetails[0].transactionDtoList[0].userId;
                $scope.returnPhone = GlobalVariable.getReturnDetails[0].transactionDtoList[0].customerPhoneNo;
                $scope.returnCreditId = GlobalVariable.getReturnDetails[0].transactionDtoList[0].customerPhoneNo;
                $scope.returncashId = GlobalVariable.getReturnDetails[0].transactionDtoList[0].cashId;
                $scope.paidAmountReturn = GlobalVariable.getReturnDetails[0].transactionDtoList[0].paidAmountCash;
                $scope.paidAmountCreditReturn = GlobalVariable.getReturnDetails[0].transactionDtoList[0].paidAmountCredit;
                $scope.changeAmountReturn = GlobalVariable.getReturnDetails[0].transactionDtoList[0].changeAmount;
                $scope.creditIdReturn = GlobalVariable.getReturnDetails[0].transactionDtoList[0].creditId;
                $scope.returnprevBalance = GlobalVariable.getReturnDetails[0].transactionDtoList[0].prevBalance;

            }
        }
        $scope.returnProduct = function() {
            /*
             * var msg= "Your total balance is
             * "+-(Number(parseFloat($scope.productTotal)).toFixed(2));
             * modalService.showModal('', '', msg,
             * $scope.callBackReturnCheckout);
             */
            var _tmPath = 'app/sell/printReceiptModal.html';
            var _ctrlPath = 'PrintRecepitController';
            DialogFactory.show(_tmPath, _ctrlPath,
                $scope.callBackReturnCheckout);
        };
        $scope.loadDeletedCheckoutData = function()
        {
            $scope.totalDelQuantity = 0;
            $scope.subDelTotal = 0;
            $scope.totalDelRetail = 0;
            $scope.totalDelRetailDisc = 0;



            for (var i = 0; i < $rootScope.returnBackData.length; i++) {
                $scope.totalDelQuantity = parseFloat($scope.totaDelQuantity)
                    + parseFloat($rootScope.returnBackData[i].quantity);
                $scope.subDelTotal = parseFloat($scope.subDelTotal)
                    + parseFloat($rootScope.returnBackData[i].total);
                $scope.totalDelRetail = parseFloat($scope.totalDelRetail)+parseFloat($rootScope.returnBackData[i].retail);
                $scope.totalDelRetailDisc = parseFloat($scope.totalDelRetailDisc)+parseFloat($rootScope.returnBackData[i].discount);

            }

           $scope.totalDelDisc = Number(parseFloat($scope.totalDelRetail)-parseFloat($scope.totalDelRetailDisc)).toFixed(2);

            $scope.productTotalWithoutDelTax = Number(
                parseFloat($scope.subDelTotal)).toFixed(2);


            if ($scope.productTotalWithoutDelTax == 'NaN') {
                $scope.productTotalWithoutDelTax = 0;
            }

            if ($scope.selectReturnTax == undefined)
                $scope.totalTax = 0;
            else if ($scope.selectReturnTax == 'default')
                $scope.totalTax = parseFloat($scope.totalDefaultTax);
            else if ($scope.selectReturnTax == 'noTax')
                $scope.totalTax = 0;

            GlobalVariable.taxDelTotal = parseFloat($scope.productTotalWithoutDelTax)
                * (parseFloat($scope.totalTax) / 100);
            $scope.productDelTotal = Number(
                parseFloat($scope.productTotalWithoutDelTax)
                + (((parseFloat($scope.productTotalWithoutDelTax) * parseFloat($scope.totalTax))) / 100))
                .toFixed(2);

            if ($scope.returnprevBalance > 0) {
                $scope.productDelTotal = parseFloat($scope.productDelTotal)
                    + parseFloat($scope.returnprevBalance);
            }
            $rootScope.totalReturnPayment = $scope.productDelTotal;
            $scope.productTotal = $scope.productDelTotal;
            GlobalVariable.checkOuttotal = $rootScope.totalReturnPayment;
            $scope.getLastTransId();

        };
        $scope.getLastTransId = function()
        {
            var url=GlobalConstants.URLCONSTANTS+'getLastTransactionId';
            dataService.Get(url,lastTransSuccess,lastTransError,'application/json','application/json');
        }
        function lastTransSuccess(response)
        {

            $scope.lastTransId =  parseInt(response);

        }
        function lastTransError(response)
        {

        }
        $scope.callBackReturnCheckout = function() {
            console.log("callback");
            var url = GlobalConstants.URLCONSTANTS+"editTransaction?previousTransId="
                + $scope.previousId;
            var request = new Object();
            if($scope.itemDeleted == true)
            {
                var paidRTCh =0;
                var paidRTCr =0;
                if(parseInt($scope.paidAmountReturn) == 0)
                {
                    paidRTCh = $scope.undelProdTotal;
                    paidRTCr =0;
                }
                else if(parseInt($scope.paidAmountCreditReturn) == 0)
                {
                    paidRTCh = 0;
                    paidRTCr =$scope.undelProdTotal;
                }
                else
                {
                    paidRTCh = $scope.undelProdTotal;
                    paidRTCr =0;
                }
                request = {
                    "transactionDate" : js_yyyy_mm_dd_hh_mm_ss(),
                    "totalAmount" : $scope.undelProdTotal,
                    "tax" : GlobalVariable.taxTotal,
                    "discount" : $scope.totalDisc,
                    "customerPhoneNo" : $scope.returnPhone,
                    "userId" : $scope.userIdReturn,
                    "cashId" : $scope.returncashId,
                    "status" : "c",
                    "paidAmountCash" : paidRTCh,
                    "changeAmount" : 0,
                    "creditId" : $scope.creditIdReturn,
                    "paidAmountCredit" : paidRTCr,
                    "transactionCompId" : parseInt($scope.lastTransId) +1,
                    "subTotal" : ($scope.subTotal),
                    "totalQuantity" : ($scope.totalQuantity)

                };
                request = JSON.stringify(request);
                dataService.Post(url, request, returnTransactionSuccessHandler,
                    returnTransactionErrorHandler, "application/json",
                    "application/json");
            }
            else
            {
                request='';
                dataService.Post(url, null, returnTransactionSuccessHandler,
                    returnTransactionErrorHandler, "application/json",
                    "application/json");
            }



        };
        function returnTransactionSuccessHandler(response) {
            /*
             * $rootScope.returnData = []; $scope.totalQuantity = 0;
             * $scope.subTotal = 0; $scope.productTotal = 0;
             * $rootScope.totalPayment = 0;
             */
            var url = GlobalConstants.URLCONSTANTS+"editTransactionLineItem?previousTransId="
                + $scope.previousId;
            var request = [];
            if($scope.itemDeleted == true)
            {
                for (var i = 0; i < $rootScope.returnData.length; i++) {
                    request.push({

                        "transactionCompId" : parseInt($scope.lastTransId) +1,
                        "productNumber" : $rootScope.returnData[i].itemNo,
                        "quantity" : $rootScope.returnData[i].quantity,
                        "retail" : $rootScope.returnData[i].retail,
                        "cost" : $rootScope.returnData[i].costPrice,
                        "discount" : $rootScope.returnData[i].discount,
                        "retailWithDis" : $rootScope.returnData[i].discount,
                        "totalProductPrice" : $rootScope.returnData[i].total,
                        "transactionDate" : js_yyyy_mm_dd_hh_mm_ss(),
                        "transactionStatus" : "c"

                    });
                }
                request = JSON.stringify(request);
                dataService.Post(url, request,
                    returnTransactionLineItemSuccessHandler,
                    returnTransactionLineItemErrorHandler, "application/json",
                    "application/json");
            }
            else
            {
                request=[];
                dataService.Post(url, null,
                    returnTransactionLineItemSuccessHandler,
                    returnTransactionLineItemErrorHandler, "application/json",
                    "application/json");
            }





        }
        function returnTransactionErrorHandler(response) {

        }
        function returnTransactionLineItemSuccessHandler(response) {
            $rootScope.returnData = [];
            $scope.totalQuantity = 0;
            $scope.subTotal = 0;
            $scope.productTotal = 0;
            $rootScope.totalReturnPayment = 0;
            $state.go('ledger');
        }
        function returnTransactionLineItemErrorHandler() {

        }
        $scope.navigateToSellPage = function() {
            $state.go('sell');
        };
        function getTaxDetails() {
            var url = GlobalConstants.URLCONSTANTS+'getPageSetUpDetails';
            dataService.Get(url, onGetTaxSuccess, onGetTaxError,
                'application/json', 'application/json');
        }
        function onGetTaxSuccess(response) {
            $scope.totalDefaultTax = response[0].tax;

            $scope.selectReturnTax = "default";
            $scope.loadCheckOutData();
        }
        function onGetTaxError(response) {

        }
        function js_yyyy_mm_dd_hh_mm_ss () {
            var now = new Date();
            var year = "" + now.getFullYear();
            var month = "" + (now.getMonth() + 1); if (month.length == 1) { month = "0" + month; }
            var day = "" + now.getDate(); if (day.length == 1) { day = "0" + day; }
            var  hour = "" + now.getHours(); if (hour.length == 1) { hour = "0" + hour; }
            var minute = "" + now.getMinutes(); if (minute.length == 1) { minute = "0" + minute; }
            var second = "" + now.getSeconds(); if (second.length == 1) { second = "0" + second; }
            return year + "-" + month + "-" + day + " " + hour + ":" + minute + ":" + second;
        }
        render();
    }

})();
