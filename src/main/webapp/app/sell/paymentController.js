(function() {
	'use strict';

	angular.module('sampleApp').controller('paymentPopupController', paymentPopupController);

	paymentPopupController.$inject = [ '$scope', '$rootScope', 'device.utility','GlobalVariable','DialogFactory','modalService'];

	function paymentPopupController($scope, $rootScope, device ,GlobalVariable,DialogFactory,modalService) 
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
		};
		$scope.payOutMax = function()
		{
			$scope.balanceAmount =0;
			$scope.checkPayout = GlobalVariable.checkOuttotal;
			$scope.creditcardPayout = GlobalVariable.checkOuttotal;
			$scope.debitcardPayout = GlobalVariable.checkOuttotal;
			$scope.cashPayout = GlobalVariable.checkOuttotal;
		};
		function render()
		{
			$scope.checkPayment = GlobalVariable.checkOuttotal;
			$scope.balanceAmount = GlobalVariable.checkOuttotal;
		}
		render();
	}
		
})();