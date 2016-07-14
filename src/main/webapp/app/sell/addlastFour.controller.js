(function() {
	'use strict';

	angular.module('sampleApp').controller('addLastFourController', addLastFourController);

	addLastFourController.$inject = [ '$scope', '$rootScope', 'device.utility','GlobalVariable','DialogFactory'];

	function addLastFourController($scope, $rootScope, device ,GlobalVariable,DialogFactory) 
	{
		$scope.GlobalVariable = GlobalVariable;
		$scope.onaddFour = function()
		{
			DialogFactory.close(true);
		};
		function render()
		{
			
		}
		render();
	}
		
})();