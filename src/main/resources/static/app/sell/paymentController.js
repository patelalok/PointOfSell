(function() {
	'use strict';

	angular.module('sampleApp').controller('paymentPopupController', paymentPopupController);

	paymentPopupController.$inject = [ '$scope', '$sce','$window','$rootScope', 'device.utility','GlobalVariable','DialogFactory','modalService','dataService','$state','RestrictedCharacter.Types'];

	function paymentPopupController($scope,$sce,$window ,$rootScope, device ,GlobalVariable,DialogFactory,modalService,dataService,$state,restrictCharacter)
	{
		$scope.color= false;
		$scope.paidAmountCash =0;
		$scope.paidAmountCredit =0;
		$scope.restrictCharacter=restrictCharacter;
		$scope.GlobalVariable = GlobalVariable;
		
		$scope.closePopup = function()
		{
			DialogFactory.close(true);
		};
		$scope.addAmount = function(amount)
		{
			$scope.paidAmountCash =parseFloat($scope.paidAmountCash)+parseFloat(amount);
			$scope.cashId = 1;
			$scope.balanceAmount =Number(parseFloat($scope.balanceAmount)-amount).toFixed(2);
			if($scope.balanecAmount <= 0 && $scope.paidAmountCredit ==0)
			{
				$scope.paidAmountCash = GlobalVariable.checkOuttotal;
				$rootScope.amountBalance =$scope.balanceAmount;
				DialogFactory.close(true);
				var _tmPath = 'app/sell/printReceiptModal.html';
				var _ctrlPath = 'PrintRecepitController';
				DialogFactory.show(_tmPath, _ctrlPath, $scope.callBackCheckout);
				$scope.color = true;
			}	
		};
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
		$scope.callBackCheckout = function()
		{

			var trasnactionDate = js_yyyy_mm_dd_hh_mm_ss();
			GlobalVariable.transDate = trasnactionDate;
			GlobalVariable.transactionCompletedId = Math.round(((Math.random() * 10) * 10)) * 11;
			DialogFactory.close(true);
			if($scope.balanceAmount == 0)
			{
				$scope.amountPaid = parseFloat($scope.checkPayment);
				$scope.changeAmount = 0;
			}
			else if($scope.balanceAmount < 0)
			{
				$scope.amountPaid = parseFloat($scope.checkPayment)-parseFloat($scope.balanceAmount);
				$scope.changeAmount = -(parseFloat($scope.balanceAmount));
			}
			else if($scope.balanceAmount > 0)
			{
				$scope.amountPaid = parseFloat($scope.checkPayment)-parseFloat($scope.balanceAmount);
				$scope.changeAmount = $scope.balanceAmount;
			}	
			
			if($scope.cashId == undefined)
				$scope.cashId = '';
			if($scope.paidAmountCash == undefined)
				$scope.paidAmountCash = '';
			if($scope.creditIdMulty == undefined)
				$scope.creditIdMulty = '';
			if($scope.paidAmountCredit == undefined)
				$scope.paidAmountCredit = '';
			if(GlobalVariable.customerFound == true)
			{
				if($scope.balanceAmount > 0)
					GlobalVariable.custBalance = $scope.balanceAmount;
				else
					GlobalVariable.custBalance = 0;
			}
			else
			{
				GlobalVariable.custBalance = 0;
			}



			var url ="http://localhost:8080/addTransaction";
			var request = new Object();
			request = {
				"transactionDate":trasnactionDate,  
				"totalAmount":parseFloat(GlobalVariable.checkOuttotal).toFixed(2),
				"tax":parseFloat(GlobalVariable.taxTotal).toFixed(2),
				"discount":parseFloat(GlobalVariable.discountTotal).toFixed(2) ,
				"customerPhoneNo":$rootScope.customerPhone,
				"userId":"2",
				"cashId":$scope.cashId,
				"status":"completed",
			"paidAmountCash":parseFloat($scope.paidAmountCash).toFixed(2),
			"changeAmount":parseFloat($scope.changeAmount).toFixed(2),
				"creditId":$scope.creditIdMulty,
				"paidAmountCredit":parseFloat($scope.paidAmountCredit).toFixed(2),
			"transactionCompId":GlobalVariable.transactionCompletedId,
			"subTotal":parseFloat(GlobalVariable.totalSub).toFixed(2),
			"totalQuantity":parseInt(GlobalVariable.quantityTotal),
			"transCreditId":GlobalVariable.transId,
			"last4Digits":GlobalVariable.last4,
				"prevBalance":parseFloat(GlobalVariable.custBalance).toFixed(2)

			};
			request = JSON.stringify(request);
			dataService.Post(url,request,addTransactionSuccessHandler,addTransactionErrorHandler,"application/json","application/json");
			
		};
		function addTransactionSuccessHandler(response)
		{	
			var request = [];
			for(var i=0;i< $rootScope.testData.length ; i++)
			{
				request.push({
					
					"transactionCompId":GlobalVariable.transactionCompletedId,
					 "productId":$rootScope.testData[i].itemNo,
					"quantity":$rootScope.testData[i].quantity,
					 "retail":$rootScope.testData[i].retail,
					 "cost":$rootScope.testData[i].costPrice,
					 "discount":$rootScope.testData[i].discount,
					 "retailWithDis":$rootScope.testData[i].discount,
					 "totalProductPrice":$rootScope.testData[i].total,
					 "transactionDate":GlobalVariable.transDate
					 
			});
			}	
			var url ="http://localhost:8080/addTransactionLineItem";
			request = JSON.stringify(request);
			dataService.Post(url,request,addTransactionLineItemSuccessHandler,addTransactionLineItemErrorHandler,"application/json","application/json");
			
			
		};
		function addTransactionErrorHandler(response)
		{
			
		};
		function addTransactionLineItemSuccessHandler(response)
		{
			$rootScope.testData = [];

			/*var msg= 'Print Receipt or Cancel';
			msg=$sce.trustAsHtml(msg);	
			modalService.showModal('', {isCancel:true,closeButtonText:'Cancel',actionButtonText:'Print'}, msg, $scope.callBackCheckoutComplete);
			*/
			
			$rootScope.totalPayment = '0.00';
			$rootScope.customerName = '';
			$rootScope.regPhone = '';
			$rootScope.customerNameOnSearch = '';
			GlobalVariable.customerFound = false;
			$rootScope.totalQuantity = 0;
			$rootScope.subTotal = 0;
			$rootScope.productTotal = 0;


		};
		/*$scope.callBackCheckoutComplete = function()
		{
			DialogFactory.close(true);
			$window.print();
		};*/
		function addTransactionLineItemErrorHandler(response)
		{
			
		}
		$scope.payOutMax = function(value)
		{
			
			if(value == 'cash')
			{
				$scope.cashId =1;
				$scope.paidAmountCash = parseFloat($scope.paidAmountCash) + parseFloat($scope.balanceAmount);
			}
			else
			{
				$scope.creditIdMulty = 2;
				$scope.paidAmountCredit = parseFloat($scope.paidAmountCredit) + parseFloat($scope.balanceAmount);
			}
			
				$scope.balanceAmount =0;
			$rootScope.amountBalance =0;
				
				$scope.checkPayout = GlobalVariable.checkOuttotal;
				$scope.creditcardPayout = GlobalVariable.checkOuttotal;
				$scope.debitcardPayout = GlobalVariable.checkOuttotal;
				$scope.cashPayout = GlobalVariable.checkOuttotal;
				DialogFactory.close(true);
				if(value == 'credit')
				{
					/*var msg='<div class="row padding-top-15">'+
					'<div class="col-md-6">'+
                '<label for="transId" class="padding-bottom-5 font-weight-label">Trans ID</label>'+
                '<input type="text" class="form-control" name="transId" id="transId" data-ng-model="transId"'+
                'autocomplete="off"  style="width:100%;height:50px;" />'+
		'</div>'+
		'<div class="col-md-6">'+
                '<label for="last4" class="padding-bottom-5 font-weight-label">Last 4 Digits</label>'+
                '<input type="text" class="form-control" name="last4" id="last4" data-ng-model="last4"'+
                'autocomplete="off"  style="width:100%;height:50px;" />'+
		'</div>'+
	'</div>'; 
				msg=$sce.trustAsHtml(msg);	
				modalService.showModal('', '', msg, $scope.callBackCheckoutCredit);*/
				var _tmPath = 'app/sell/addlastfour.html';
				var _ctrlPath = 'addLastFourController';
				DialogFactory.show(_tmPath, _ctrlPath, $scope.callBackCheckoutCredit);
				}	
				else
				{
					/*var msg= 'Your total balance <span style="color:red;font-size:30px;">is</span> '+Number(parseFloat($scope.balanceAmount)).toFixed(2);
					msg=$sce.trustAsHtml(msg);	
					modalService.showModal('', '', msg, $scope.callBackCheckout);*/
					var _tmPath = 'app/sell/printReceiptModal.html';
					var _ctrlPath = 'PrintRecepitController';
					DialogFactory.show(_tmPath, _ctrlPath, $scope.callBackCheckout);
						$scope.color = true;
				}	
				
			/*var msg= 'Your total balance <span style="color:red;font-size:30px;">is1</span> '+Number(parseFloat($scope.balanceAmount)).toFixed(2);
			msg=$sce.trustAsHtml(msg);	
			modalService.showModal('', '', msg, $scope.callBackCheckout);
				$scope.color = true;*/
				

		};
		
		$scope.callBackCheckoutCredit = function()
		{
			console.log($scope.last4+""+$scope.transId);
			/*var msg= 'Your total balance is '+Number(parseFloat($scope.balanceAmount)).toFixed(2);
			msg=$sce.trustAsHtml(msg);	
			modalService.showModal('', '', msg, $scope.callBackCheckout);*/
			var _tmPath = 'app/sell/printReceiptModal.html';
			var _ctrlPath = 'PrintRecepitController';
			DialogFactory.show(_tmPath, _ctrlPath, $scope.callBackCheckout);
				$scope.color = true;
		};
		$scope.makePartialPayment = function()
		{
			$rootScope.amountBalance = $scope.balanceAmount;
			DialogFactory.close(true);
			var _tmPath = 'app/sell/printReceiptModal.html';
			var _ctrlPath = 'PrintRecepitController';
			DialogFactory.show(_tmPath, _ctrlPath, $scope.callBackCheckout);
		};
		$scope.calculateAmount = function(value,means)
		{
			if(value == "")
				value= 0;
			if(means == 'cash')
			{
				$scope.cashId =1;
				if(parseFloat($scope.balanceAmount) > parseFloat(value))
				{
					$scope.paidAmountCash = parseFloat($scope.paidAmountCash) + parseFloat(value);
				}
				else
				{
					$scope.paidAmountCash =  parseFloat($scope.paidAmountCash) +  $scope.balanceAmount  ;
				}
				$scope.balanceAmount =parseFloat($scope.balanceAmount)-parseFloat(value);
				if($scope.balanceAmount <= 0 && $scope.paidAmountCredit == 0) {

					$scope.paidAmountCash = GlobalVariable.checkOuttotal;
				}

			}
			else
			{
				$scope.creditIdMulty = 2;
				if(parseFloat($scope.balanceAmount) > parseFloat(value) )
				{
					$scope.paidAmountCredit = parseFloat($scope.paidAmountCredit) + parseFloat(value);
				}
				else
				{
					$scope.paidAmountCredit =  parseFloat($scope.paidAmountCredit) +  $scope.balanceAmount  ;
				}
				$scope.balanceAmount =parseFloat($scope.balanceAmount)-parseFloat(value);
				if($scope.balanceAmount <= 0 && $scope.paidAmountCash == 0) {

					$scope.paidAmountCredit = GlobalVariable.checkOuttotal;
				}
			}
			
			//$scope.remainingBalance = parseFloat(GlobalVariable.checkOuttotal)-parseFloat(value);

			if($scope.balanceAmount <= 0)
			{
				$rootScope.amountBalance =$scope.balanceAmount;
				DialogFactory.close(true);
				var _tmPath = 'app/sell/printReceiptModal.html';
				var _ctrlPath = 'PrintRecepitController';
				DialogFactory.show(_tmPath, _ctrlPath, $scope.callBackCheckout);
				$scope.color = true;
			}
		};
		function render()
		{
			$scope.checkPayment = GlobalVariable.checkOuttotal;
			$scope.balanceAmount = GlobalVariable.checkOuttotal;
		}
		render();
	}
		
})();