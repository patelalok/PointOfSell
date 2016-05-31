(function() {
	'use strict';

	angular.module('sampleApp').controller('addPopupController', addPopupController);

	addPopupController.$inject = [ '$scope', '$rootScope', 'device.utility','GlobalVariable','DialogFactory'];

	function addPopupController($scope, $rootScope, device ,GlobalVariable,DialogFactory) 
	{
		$scope.device= device;
		$scope.GlobalVariable = GlobalVariable;
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