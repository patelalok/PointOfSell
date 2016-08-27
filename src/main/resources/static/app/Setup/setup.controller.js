(function() {
	'use strict';

	angular.module('sampleApp').controller('SetupController', SetupController);

	SetupController.$inject = [ '$scope', '$rootScope', 'device.utility','GlobalVariable','DialogFactory','dataService','$timeout','$state'];

	function SetupController($scope, $rootScope, device ,GlobalVariable,DialogFactory,dataService,$timeout,$state)
	{
		$scope.showAddUser = false;
		$scope.showUserDtls = false;
		GlobalVariable.successUsesAlert = false;
		GlobalVariable.addedSuccessfull = false;GlobalVariable.editSuccess= false;
		GlobalVariable.successTaxAlert = false;
		$scope.showTaxDtls = true;

		$scope.changeTax = function()
		{
			GlobalVariable.totalTaxSetup = $scope.taxSetup;
		};
		$rootScope.closeBootstrapAlert = function()
		{
			GlobalVariable.successUsesAlert = false;
			GlobalVariable.addedSuccessfull = false;
			GlobalVariable.editSuccess = false;
			GlobalVariable.successTaxAlert = false;
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
			$scope.getUserDetails();
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
			$scope.getTaxDetails();
			$timeout(function() {
				$rootScope.closeBootstrapAlert();
			}, 9000);
		};
		$scope.getTaxDetails = function()
		{

			$scope.showTaxDtls = true;
			$scope.showUserDtls = false;
			$scope.showRcptDtls = false;
			var url='http://localhost:8080/getPageSetUpDetails';
			dataService.Get(url,onGetTaxSuccess,onGetTaxError,'application/json','application/json');
		}
		function onGetTaxSuccess(response)
		{
			if(response.length!==0)
			{
			$scope.getTaxDtls = response;
			$scope.footerReceipt = response[0].footerReceipt;
			}
		}
		function onGetTaxError(response)
		{

		}
		$scope.editTax = function(row)
		{
			GlobalVariable.editTax = true;
			GlobalVariable.editTaxId = row.id;
			GlobalVariable.editedTax = row.tax;
			GlobalVariable.strAdd = row.storeAddress;
			var _tmPath = 'app/Setup/TaxDetails.html';
			var _ctrlPath = 'TaxController';
			DialogFactory.show(_tmPath, _ctrlPath, $scope.callBackAddTax);

		};
		$scope.getUserDetails = function()
		{
			$scope.showTaxDtls = false;
			$scope.showUserDtls = true;
			$scope.showRcptDtls = false;
			var url='http://localhost:8080/getUserDetails';
			dataService.Get(url,onGetUserDtlsSuccess,onGetUserDtlsError,'application/json','application/json');
		};
		function onGetUserDtlsSuccess(response)
		{
			$scope.getUserDtls = response;
		}
		function onGetUserDtlsError(response)
		{

		}
		$scope.getReceiptDetails = function()
		{
			$scope.showTaxDtls = false;
			$scope.showUserDtls = false;
			$scope.showRcptDtls = true;
		};
		$scope.editUser = function(row)
		{
			GlobalVariable.editUser = true;
			GlobalVariable.edituserName = row.username;
			GlobalVariable.editPassword = row.password;
			GlobalVariable.editUserRole = row.userRole;
			GlobalVariable.editCreatedDate = row.createdDate;
			GlobalVariable.editUserId = row.userId;
			GlobalVariable.editFirstName = row.firstName;
			GlobalVariable.editLastName = row.lastName;
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
				$scope.getTaxDetails();
				//$scope.getUserDetails();

		}
		$scope.navigateToClockInDtls=function(username)
		{
			GlobalVariable.usernameCust = username;
			$state.go('clock');
/*			var _tmPath = 'app/Setup/clockPopup.html';
			var _ctrlPath = 'clockPopupController';
			DialogFactory.show(_tmPath, _ctrlPath, callbackClockHistory,undefined, undefined, 'lg');*/
		};
		function callbackClockHistory()
		{

		}
		render();
	}
		
})();