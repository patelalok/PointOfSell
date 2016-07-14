(function() {
	'use strict';

	angular.module('sampleApp').controller('SetupController', SetupController);

	SetupController.$inject = [ '$scope', '$rootScope', 'device.utility','GlobalVariable','DialogFactory'];

	function SetupController($scope, $rootScope, device ,GlobalVariable,DialogFactory) 
	{
		$scope.showAddUser = false;
		$scope.changeTax = function()
		{
			GlobalVariable.totalTaxSetup = $scope.taxSetup;
		};
		$scope.addUser = function()
		{
			var _tmPath = 'app/Setup/createUser.html';
			var _ctrlPath = 'CreateUserController';
			DialogFactory.show(_tmPath, _ctrlPath, $scope.callBackCreateUser);
			
		};
		$scope.callBackCreateUser = function()
		{
			
		};
		$scope.createUser = function()
		{
			var request = {
				'userName':$scope.userName,	
				'password':$scope.newPassword,
				'firstName':$scope.firstName,
				'lastName':$scope.lastName,
				'email':$scope.email
			};
			$scope.showAddUser = false;
			
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