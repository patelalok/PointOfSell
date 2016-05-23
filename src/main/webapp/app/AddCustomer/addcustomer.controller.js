(function() {
	'use strict';

	angular.module('sampleApp').controller('addCustomerController', addCustomerController);

	addCustomerController.$inject = [ '$scope', '$rootScope', 'device.utility','GlobalVariable','DialogFactory'];

	function addCustomerController($scope, $rootScope, device ,GlobalVariable,DialogFactory) 
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