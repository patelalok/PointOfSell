(function() {
	'use strict';

	angular.module('sampleApp').controller('paymentPopupController', paymentPopupController);

	paymentPopupController.$inject = [ '$scope', '$rootScope', 'device.utility','GlobalVariable','DialogFactory','modalService','dataService'];

	function paymentPopupController($scope, $rootScope, device ,GlobalVariable,DialogFactory,modalService,dataService)
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
		$scope.callBackCheckout = function()
		{
			//TODO 
			//Database service call to complete transaction request.
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
				"transactionDate":"1000-01-01 00:00:00",  
				"totalAmount":GlobalVariable.checkOuttotal,
				"tax":GlobalVariable.taxTotal,
				"discount":GlobalVariable.discountTotal ,
				"customerPhoneNo":"123456789012",
				"userId":"1234", 
				"paymentId":"1",
				"status":"completed",
			"paidAmount":$scope.amountPaid,
			"changeAmount":$scope.changeAmount

			};
			dataService.Post(url,request,addTransactionSuccessHandler,addTransactionErrorHandler,"application/json","application/json");
		};
		function addTransactionSuccessHandler(response)
		{
			$rootScope.testData = [];
		};
		function addTransactionErrorHandler(response)
		{
			
		};
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