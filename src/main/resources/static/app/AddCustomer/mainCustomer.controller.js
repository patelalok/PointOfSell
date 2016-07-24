(function() {
	'use strict';

	angular.module('sampleApp').controller('mainCustomerController', mainCustomerController);

	mainCustomerController.$inject = [ '$scope', '$rootScope', 'device.utility','GlobalVariable','$state','DialogFactory','$timeout','dataService'];

	function mainCustomerController($scope, $rootScope, device ,GlobalVariable,$state,DialogFactory,$timeout,dataService) {
		
		$scope.device = device;
		$scope.GlobalVariable = GlobalVariable;
		GlobalVariable.isLoginPage = false;

		function getCustomerDetails()
		{
			var url='http://localhost:8080/getCustomerDetail';
			dataService.Get(url,onGetCustDtlsSuccess,onGetCustDtlsError,'application/json','application/json');
		}
		function onGetCustDtlsSuccess(response)
		{
				$scope.customerData = response;

		}
		function onGetCustDtlsError(response)
		{

		}
		$scope.addNewCustomerDetails = function()
		{
			GlobalVariable.editCustomer = false;
			var _tmPath = 'app/AddCustomer/addcustomer.html';
			var _ctrlPath = 'addCustomerController';
			DialogFactory.show(_tmPath, _ctrlPath, callbackAddCustomer);
		};
		function callbackAddCustomer()
		{
			getCustomerDetails();
			$timeout(function() {
				$scope.closeBootstrapAlert();
			}, 9000);
		}
		$scope.closeBootstrapAlert = function()
		{
			GlobalVariable.successCustAlert = false;
			GlobalVariable.addedCustSuccessfull = false;
			GlobalVariable.editCustSuccess = false;
		};
		$scope.editCustomerDetails = function(row)
		{
			GlobalVariable.editedFirstName = row.firstName;
			GlobalVariable.editedLastName = row.lastName;
			GlobalVariable.editedPhone = row.phoneNo;
			GlobalVariable.editedFEmail = row.email;
			GlobalVariable.editedDOB = row.dateOfBirth;
			GlobalVariable.editedGender = row.gender;
			GlobalVariable.editedStreet =row.street;
			 GlobalVariable.editedCity = row.city;
			GlobalVariable.editedState =row.state;
			 GlobalVariable.editedCountry = row.country;
			GlobalVariable.editedCode =row.zipcode;
			GlobalVariable.editedCusyType = row.customerType;
			GlobalVariable.editCustomer = true;
			var _tmPath = 'app/AddCustomer/addcustomer.html';
			var _ctrlPath = 'addCustomerController';
			DialogFactory.show(_tmPath, _ctrlPath, callbackAddCustomer);
		};
		function render()
		{

			getCustomerDetails();

				
		}
		render();
		
	}
		
})();