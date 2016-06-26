(function() {
	'use strict';

	angular.module('sampleApp').controller('paymentPopupController', paymentPopupController);

	paymentPopupController.$inject = [ '$scope', '$rootScope', 'device.utility','GlobalVariable','DialogFactory','modalService','dataService','$state','RestrictedCharacter.Types'];

	function paymentPopupController($scope, $rootScope, device ,GlobalVariable,DialogFactory,modalService,dataService,$state,restrictCharacter)
	{
		$scope.color= false;
		$scope.paidAmountCash =0;
		$scope.paidAmountCredit =0;
		$scope.restrictCharacter=restrictCharacter;
		
		$scope.closePopup = function()
		{
			DialogFactory.close(true);
		};
		$scope.addAmount = function(amount)
		{
			$scope.paidAmountCash =parseFloat($scope.paidAmountCash)+parseFloat(amount);
			$scope.cashId = 1;
			$scope.balanceAmount =Number(parseFloat($scope.balanceAmount)-amount).toFixed(2);
			if($scope.balanceAmount <= 0)
			{
				var msg= "Your total balance is "+Number(parseFloat($scope.balanceAmount)).toFixed(2);
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
			GlobalVariable.transDate = trasnactionDate;
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
			
			if($scope.cashId == undefined)
				$scope.cashId = '';
			if($scope.paidAmountCash == undefined)
				$scope.paidAmountCash = '';
			if($scope.creditIdMulty == undefined)
				$scope.creditIdMulty = '';
			if($scope.paidAmountCredit == undefined)
				$scope.paidAmountCredit = '';
			
			var url ="http://localhost:8080/addTransaction";
			var request = new Object();
			request = {
				"transactionDate":trasnactionDate,  
				"totalAmount":GlobalVariable.checkOuttotal,
				"tax":GlobalVariable.taxTotal,
				"discount":GlobalVariable.discountTotal ,
				"customerPhoneNo":$rootScope.customerPhone,
				"userId":"2",
				"cashId":$scope.cashId,
				"status":"completed",
			"paidAmountCash":$scope.paidAmountCash,
			"changeAmount":$scope.changeAmount,
			"creditId":$scope.creditIdMulty,
			"paidAmountCredit":$scope.paidAmountCredit,
		"transactionCompId":GlobalVariable.transactionCompletedId,
		"subTotal":GlobalVariable.totalSub,
		"totalQuantity":GlobalVariable.quantityTotal

			};
			request = JSON.stringify(request);
			dataService.Post(url,request,addTransactionSuccessHandler,addTransactionErrorHandler,"application/json","application/json");
			addTransactionSuccessHandler('');
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
			addTransactionLineItemSuccessHandler('');
			
		};
		function addTransactionErrorHandler(response)
		{
			
		};
		function addTransactionLineItemSuccessHandler(response)
		{
			$rootScope.testData = [];
			//TODO
			//popup to display whther to print receipt or not;
			/*$state.go('ledger');*/
		};
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
				
				$scope.checkPayout = GlobalVariable.checkOuttotal;
				$scope.creditcardPayout = GlobalVariable.checkOuttotal;
				$scope.debitcardPayout = GlobalVariable.checkOuttotal;
				$scope.cashPayout = GlobalVariable.checkOuttotal;
			
			
			var msg= "Your total balance is "+Number(parseFloat($scope.balanceAmount)).toFixed(2);
				modalService.showModal('', '', msg, $scope.callBackCheckout);
				$scope.color = true;

		};
		$scope.calculateAmount = function(value,means)
		{
			if(value == "")
				value= 0;
			if(means == 'cash')
			{
				$scope.cashId =1;
				$scope.paidAmountCash = parseFloat($scope.paidAmountCash) + parseFloat(value);
			}
			else
			{
				$scope.creditIdMulty = 2;
				$scope.paidAmountCredit = parseFloat($scope.paidAmountCredit) + parseFloat(value);
			}
			
			$scope.remainingBalance = parseFloat(GlobalVariable.checkOuttotal)-parseFloat(value);
			$scope.balanceAmount =$scope.remainingBalance;
			if($scope.balanceAmount <= 0)
			{
				var msg= "Your total balance is "+Number(parseFloat($scope.balanceAmount)).toFixed(2);
				modalService.showModal('', '', msg, $scope.callBackCheckout);
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