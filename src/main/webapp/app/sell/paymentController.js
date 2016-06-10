(function() {
	'use strict';

	angular.module('sampleApp').controller('paymentPopupController', paymentPopupController);

	paymentPopupController.$inject = [ '$scope', '$rootScope', 'device.utility','GlobalVariable','DialogFactory'];

	function paymentPopupController($scope, $rootScope, device ,GlobalVariable,DialogFactory) 
	{
		$scope.color= false;
		
		$scope.closePopup = function()
		{
			DialogFactory.close(true);
		};
		$scope.addAmount = function(amount)
		{
			$scope.balanceAmount =parseFloat($scope.balanceAmount)-amount;
			if($scope.balanceAmount < 0)
			{
				
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