(function() {
	'use strict';

	angular.module('sampleApp').controller('paymentPopupController', paymentPopupController);

	paymentPopupController.$inject = [ '$scope', '$rootScope', 'device.utility','GlobalVariable','DialogFactory','modalService','dataService','$state'];

	function paymentPopupController($scope, $rootScope, device ,GlobalVariable,DialogFactory,modalService,dataService,$state)
	{
		$scope.color= false;
		
		$scope.closePopup = function()
		{
			DialogFactory.close(true);
		};
		$scope.addAmount = function(amount)
		{
			$scope.balanceAmount =parseFloat($scope.balanceAmount)-amount;
			if($scope.balanceAmount <= 0)
			{
				var msg= "Your total balance is "+$scope.balanceAmount;
				modalService.showModal('', '', msg, $scope.callBackCheckout);
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
			//TODO 
			//Database service call to complete transaction request.
			var trasnactionDate = js_yyyy_mm_dd_hh_mm_ss();
			GlobalVariable.transactionCompletedId = Math.round(((Math.random() * 10) * 10));
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
			var url ="http://localhost:8080/addTransaction";
			var request = new Object();
			request = {
				"transactionDate":trasnactionDate,  
				"totalAmount":GlobalVariable.checkOuttotal,
				"tax":GlobalVariable.taxTotal,
				"discount":GlobalVariable.discountTotal ,
				"customerPhoneNo":$rootScope.customerPhone,
				"userId":"1234", 
				"paymentId":"1",
				"status":"completed",
			"paidAmount":$scope.amountPaid,
			"changeAmount":$scope.changeAmount,
			"transactionCompId":GlobalVariable.transactionCompletedId

			};
			request = JSON.stringify(request);
			dataService.Post(url,request,addTransactionSuccessHandler,addTransactionErrorHandler,"application/json","application/json");
			//addTransactionSuccessHandler(response);
		};
		function addTransactionSuccessHandler(response)
		{	
			var request = [];
			for(var i=0;i< $rootScope.testData.length ; i++)
			{
				request.push({
					
					"transactionCompId":GlobalVariable.transactionCompletedId,
					 "productId":$rootScope.testData[i].itemNo,
					"description":$rootScope.testData[i].item,
					"quantity":$rootScope.testData[i].quantity,
					 "retail":$rootScope.testData[i].retail,
					 "cost":$rootScope.testData[i].costPrice,
					 "discount":$rootScope.testData[i].discount
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
			//TODO
			//popup to display whther to print receipt or not;
			$state.go('ledger');
		};
		function addTransactionLineItemErrorHandler(response)
		{
			
		}
		$scope.payOutMax = function()
		{
			$scope.balanceAmount =0;
			$scope.checkPayout = GlobalVariable.checkOuttotal;
			$scope.creditcardPayout = GlobalVariable.checkOuttotal;
			$scope.debitcardPayout = GlobalVariable.checkOuttotal;
			$scope.cashPayout = GlobalVariable.checkOuttotal;
			var msg= "Your total balance is "+$scope.balanceAmount;
				modalService.showModal('', '', msg, $scope.callBackCheckout);
				$scope.color = true;

		};
		function render()
		{
			$scope.checkPayment = GlobalVariable.checkOuttotal;
			$scope.balanceAmount = GlobalVariable.checkOuttotal;
		}
		render();
	}
		
})();