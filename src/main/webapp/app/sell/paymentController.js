(function() {
	'use strict';

	angular.module('sampleApp').controller('paymentPopupController', paymentPopupController);

	paymentPopupController.$inject = [ '$scope', '$rootScope', 'device.utility','GlobalVariable','DialogFactory'];

	function paymentPopupController($scope, $rootScope, device ,GlobalVariable,DialogFactory) 
	{
		
		$scope.closePopup = function()
		{
			DialogFactory.close(true);
		};

		function render()
		{
			
		}
		render();
	}
		
})();