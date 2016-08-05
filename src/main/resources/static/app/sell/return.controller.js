(function() {
    'use strict';

    angular.module('sampleApp').controller('ReturnController', ReturnController);

    ReturnController.$inject = [ '$scope', '$rootScope', 'device.utility','GlobalVariable','DialogFactory','modalService','RestrictedCharacter.Types','dataService','$state'];

    function ReturnController($scope, $rootScope, device ,GlobalVariable,DialogFactory,modalService,restrictCharacter,dataService,$state) {

        $scope.device = device;
        $scope.GlobalVariable = GlobalVariable;
        $scope.restrictCharacter=restrictCharacter;
        GlobalVariable.isLoginPage = false;

        var i=0;
        $scope.pageSize = 10;

        $rootScope.returnData = [];
        $scope.productNames = [];


        $scope.removeRow = function(itemNo){

            GlobalVariable.itemNoToDelete = itemNo;
            modalService.showModal('', {
                isCancel : true
            }, "Are you Sure Want to Delete ? ", $scope.callBackAction);
            /*var index = -1;
             var comArr = eval( $rootScope.returnData );
             for( var i = 0; i < comArr.length; i++ ) {
             if( comArr[i].itemNo === itemNo ) {
             index = i;
             break;
             }
             }
             if( index === -1 ) {
             alert( "Something gone wrong" );
             }
             $rootScope.returnData.splice( index, 1 );*/
        };
        $scope.callBackAction = function(isOKClicked)
        {

            if(isOKClicked)
            {
                var index = -1;
                var comArr = eval( $rootScope.returnData );
                for( var i = 0; i < comArr.length; i++ ) {
                    if( comArr[i].itemNo === GlobalVariable.itemNoToDelete ) {
                        index = i;
                        break;
                    }
                }
                if( index === -1 ) {
                    alert( "Something gone wrong" );
                }
                $rootScope.returnData.splice( index, 1 );
                $scope.loadCheckOutData();
            }
        }



        function callbackPayment()
        {
            $scope.totalQuantity = 0;
            $scope.subTotal = 0;
            $scope.productTotal = 0;

        }

        $scope.loadCheckOutData = function()
        {
            $scope.totalQuantity=0;
            $scope.subTotal = 0;
            for(var i=0;i<$rootScope.returnData.length;i++)
            {
                $scope.totalQuantity = parseFloat( $scope.totalQuantity) + parseFloat($rootScope.returnData[i].quantity);
                $scope.subTotal = parseFloat($scope.subTotal) + parseFloat($rootScope.returnData[i].total);

            }
            GlobalVariable.quantityTotal = $scope.totalQuantity;
            GlobalVariable.totalSub = $scope.subTotal;
            if($scope.totalDisc == undefined)
                $scope.totalDisc = 0;

            GlobalVariable.discountTotal = $scope.totalDisc ;
            if($scope.totalDisc == "")
                $scope.productTotalWithoutTax = Number(parseFloat( $scope.subTotal)).toFixed(2);
            else
                $scope.productTotalWithoutTax = Number(parseFloat( $scope.subTotal) - parseFloat($scope.totalDisc)).toFixed(2);


            if($scope.productTotalWithoutTax == 'NaN')
            {
                $scope.productTotalWithoutTax =0;
            }

            if($scope.totalTax == undefined)
                $scope.totalTax = 0;

            GlobalVariable.taxTotal = parseFloat($scope.productTotalWithoutTax) * (parseFloat($scope.totalTax) / 100);
            $scope.productTotal = Number(parseFloat($scope.productTotalWithoutTax)+(((parseFloat($scope.productTotalWithoutTax) * parseFloat($scope.totalTax))) / 100 )).toFixed(2);

            $rootScope.totalReturnPayment = $scope.productTotal;
            GlobalVariable.checkOuttotal = $rootScope.totalReturnPayment;
        }
        function render()
        {
            $scope.currentPageIndexArr = 0;
            $scope.totalTax=GlobalVariable.totalTaxSetup;
            $scope.loadCheckOutData();
            if(GlobalVariable.returnProduct == true)
            {
                for(var i=0;i<GlobalVariable.getReturnDetails[0].transactionLineItemDtoList.length;i++)
                {
                    $rootScope.returnData.push({"itemNo":GlobalVariable.getReturnDetails[0].transactionLineItemDtoList[i].productId,
                        "item":GlobalVariable.getReturnDetails[0].transactionLineItemDtoList[i].productNumber,
                        "quantity":GlobalVariable.getReturnDetails[0].transactionLineItemDtoList[i].quantity,
                        "retail":GlobalVariable.getReturnDetails[0].transactionLineItemDtoList[i].retail,
                        "discount":GlobalVariable.getReturnDetails[0].transactionLineItemDtoList[i].retailWithDis,
                        "total":GlobalVariable.getReturnDetails[0].transactionLineItemDtoList[i].totalProductPrice,
                        "stock":GlobalVariable.getReturnDetails[0].transactionLineItemDtoList[i].quantity,
                        "costPrice":GlobalVariable.getReturnDetails[0].transactionLineItemDtoList[i].cost
                    });
                }
                $scope.totalQuantity =GlobalVariable.getReturnDetails[0].transactionDtoList[0].totalQuantity;
                $scope.subTotal = GlobalVariable.getReturnDetails[0].transactionDtoList[0].subTotal;
                $scope.totalDisc =GlobalVariable.getReturnDetails[0].transactionDtoList[0].discount;
                $scope.totalTax =GlobalVariable.getReturnDetails[0].transactionDtoList[0].tax;
                $scope.productTotal= GlobalVariable.getReturnDetails[0].transactionDtoList[0].totalAmount;
                $rootScope.totalReturnPayment =$scope.productTotal;
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
                $scope.changeAmountReturn =GlobalVariable.getReturnDetails[0].transactionDtoList[0].changeAmount;
                $scope.creditIdReturn =GlobalVariable.getReturnDetails[0].transactionDtoList[0].creditId;

            }
        }
        $scope.returnProduct = function()
        {
           /* var msg= "Your total balance is "+-(Number(parseFloat($scope.productTotal)).toFixed(2));
            modalService.showModal('', '', msg, $scope.callBackReturnCheckout);*/
            var _tmPath = 'app/sell/printReceiptModal.html';
            var _ctrlPath = 'PrintRecepitController';
            DialogFactory.show(_tmPath, _ctrlPath, $scope.callBackReturnCheckout);
        };
        $scope.callBackReturnCheckout = function()
        {
            console.log("callback");
            var url ="http://localhost:8080/editTransaction?previousTransId="+$scope.previousId;
            var request = new Object();
            request = {
                "transactionDate":$scope.returnDate,
                "totalAmount":$scope.returnAmount,
                "tax":($scope.totalTax),
                "discount":($scope.totalDisc) ,
                "customerPhoneNo":$scope.returnPhone,
                "userId":$scope.userIdReturn,
                "cashId":$scope.returncashId,
                "status":"completed",
                "paidAmountCash":($scope.paidAmountReturn),
                "changeAmount":($scope.changeAmountReturn),
                "creditId":$scope.creditIdReturn,
                "paidAmountCredit":($scope.paidAmountCreditReturn),
                "transactionCompId":$scope.returnId,
                "subTotal":($scope.subTotal),
                "totalQuantity":($scope.totalQuantity)

            };
            request = JSON.stringify(request);
            dataService.Post(url,request,returnTransactionSuccessHandler,returnTransactionErrorHandler,"application/json","application/json");
        };
        function returnTransactionSuccessHandler(response)
        {
           /* $rootScope.returnData = [];
            $scope.totalQuantity = 0;
            $scope.subTotal = 0;
            $scope.productTotal = 0;
            $rootScope.totalPayment = 0;*/

            var request = [];
            for(var i=0;i< $rootScope.returnData.length ; i++)
            {
                request.push({

                    "transactionCompId":$scope.returnId,
                    "productId":$rootScope.returnData[i].itemNo,
                    "quantity":$rootScope.returnData[i].quantity,
                    "retail":$rootScope.returnData[i].retail,
                    "cost":$rootScope.returnData[i].costPrice,
                    "discount":$rootScope.returnData[i].discount,
                    "retailWithDis":$rootScope.returnData[i].discount,
                    "totalProductPrice":$rootScope.returnData[i].total,
                    "transactionDate":$scope.returnDate,
                    "transactionStatus":"completed"

                });
            }
            var url ="http://localhost:8080/editTransactionLineItem?previousTransId="+$scope.previousId;
            request = JSON.stringify(request);
            dataService.Post(url,request,returnTransactionLineItemSuccessHandler,returnTransactionLineItemErrorHandler,"application/json","application/json");

        }
        function returnTransactionErrorHandler(response)
        {

        }
        function returnTransactionLineItemSuccessHandler(response)
        {
            $rootScope.returnData = [];
            $scope.totalQuantity = 0;
            $scope.subTotal = 0;
            $scope.productTotal = 0;
            $rootScope.totalReturnPayment = 0;
            $state.go('ledger');
        }
        function returnTransactionLineItemErrorHandler()
        {

        }
        $scope.navigateToSellPage = function()
        {
          $state.go('sell');
        };
        render();
    }

})();