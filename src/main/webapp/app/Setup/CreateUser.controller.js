(function() {
	'use strict';

	angular.module('sampleApp').controller('CreateUserController', CreateUserController);

	CreateUserController.$inject = [ '$scope', '$rootScope', 'device.utility','GlobalVariable','DialogFactory'];

	function CreateUserController($scope, $rootScope, device ,GlobalVariable,DialogFactory) 
	{
		$scope.GlobalVariable = GlobalVariable;
		$scope.closeCreateUser = function()
		{
			DialogFactory.close(true);
		};
		function render()
		{
			
		}
		render();
	}
		
})();