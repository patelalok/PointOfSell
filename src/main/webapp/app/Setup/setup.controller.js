(function() {
	'use strict';

	angular.module('sampleApp').controller('SetupController', SetupController);

	SetupController.$inject = [ '$scope', '$rootScope', 'device.utility','GlobalVariable','DialogFactory'];

	function SetupController($scope, $rootScope, device ,GlobalVariable,DialogFactory) 
	{
		$scope.changeTax = function()
		{
			GlobalVariable.totalTaxSetup = $scope.taxSetup;
		};
		
		function render()
		{
			if(GlobalVariable.totalTaxSetup)
				{
				$scope.taxSetup = GlobalVariable.totalTaxSetup;
				}
		}
		render();
	}
		
})();