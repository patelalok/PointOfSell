(function() {
	'use strict';

	angular.module('sampleApp').controller('SetupController', SetupController);

	SetupController.$inject = [ '$scope', '$rootScope', 'device.utility','GlobalVariable','DialogFactory','dataService','$timeout'];

	function SetupController($scope, $rootScope, device ,GlobalVariable,DialogFactory,dataService,$timeout)
	{
		$scope.showAddUser = false;
		GlobalVariable.successUsesAlert = false;
		GlobalVariable.addedSuccessfull = false;GlobalVariable.editSuccess= false;
		GlobalVariable.successTaxAlert = false;

		$scope.changeTax = function()
		{
			GlobalVariable.totalTaxSetup = $scope.taxSetup;
		};
		$rootScope.closeBootstrapAlert = function()
		{
			GlobalVariable.successUsesAlert = false;
			GlobalVariable.addedSuccessfull = false;
			GlobalVariable.editSuccess = false;
		};
		$scope.createNewUser = function()
		{
			GlobalVariable.editUser = false;
			var _tmPath = 'app/Setup/createUser.html';
			var _ctrlPath = 'CreateUserController';
			DialogFactory.show(_tmPath, _ctrlPath, $scope.callBackCreateUser);
			
		};
		$scope.callBackCreateUser = function()
		{
			getUserDetails();
			$timeout(function() {
				$rootScope.closeBootstrapAlert();
			}, 9000);
		};

		$scope.getTax = function()
		{
			GlobalVariable.editTax = false;
			var _tmPath = 'app/Setup/TaxDetails.html';
			var _ctrlPath = 'TaxController';
			DialogFactory.show(_tmPath, _ctrlPath, $scope.callBackAddTax);
		};
		$scope.callBackAddTax = function()
		{
			getTaxDetails();
			$timeout(function() {
				$rootScope.closeBootstrapAlert();
			}, 9000);
		};
		function getTaxDetails()
		{
			var url='http://localhost:8080/getPageSetUpDetails';
			dataService.Get(url,onGetTaxSuccess,onGetTaxError,'application/json','application/json');
		}
		function onGetTaxSuccess(response)
		{
			$scope.getTaxDtls = response;
		}
		function onGetTaxError(response)
		{

		}
		$scope.editTax = function(row)
		{
			GlobalVariable.editTax = true;
			GlobalVariable.editTaxId = row.id;
			GlobalVariable.editedTax = row.tax;
			var _tmPath = 'app/Setup/TaxDetails.html';
			var _ctrlPath = 'TaxController';
			DialogFactory.show(_tmPath, _ctrlPath, $scope.callBackAddTax);

		};
		function getUserDetails()
		{
			var url='http://localhost:8080/getUserDetails';
			dataService.Get(url,onGetUserDtlsSuccess,onGetUserDtlsError,'application/json','application/json');
		}
		function onGetUserDtlsSuccess(response)
		{
			$scope.getUserDtls = response;
		}
		function onGetUserDtlsError(response)
		{

		}
		$scope.editUser = function(row)
		{
			GlobalVariable.editUser = true;
			GlobalVariable.edituserName = row.username;
			GlobalVariable.editPassword = row.passowrd;
			GlobalVariable.editUserRole = row.userRole;
			GlobalVariable.editCreatedDate = row.createdDate;
			GlobalVariable.editUserId = row.userId;
			var _tmPath = 'app/Setup/createUser.html';
			var _ctrlPath = 'CreateUserController';
			DialogFactory.show(_tmPath, _ctrlPath, $scope.callBackCreateUser);
		};

		function render()
		{
			if(GlobalVariable.totalTaxSetup)
				{
				$scope.taxSetup = GlobalVariable.totalTaxSetup;
				}
				getTaxDetails();
				getUserDetails();

		}
		render();
	}
		
})();