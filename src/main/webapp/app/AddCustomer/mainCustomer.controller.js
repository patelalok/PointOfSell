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
			$scope.customerData=[
				{
					"firstName": "asdas",
					"lastName": null,
					"phoneNo": "1234",
					"email": null,
					"dateOfBirth": null,
					"customerType": null,
					"gender": null,
					"street": null,
					"city": null,
					"state": null,
					"country": null,
					"zipcode": null,
					"fax": null,
					"customerCreatedDate": null,
					"balance": 0
				},
				{
					"firstName": "b",
					"lastName": "a",
					"phoneNo": "12345",
					"email": null,
					"dateOfBirth": null,
					"customerType": null,
					"gender": null,
					"street": null,
					"city": null,
					"state": null,
					"country": null,
					"zipcode": null,
					"fax": null,
					"customerCreatedDate": null,
					"balance": 0
				},
				{
					"firstName": "Alok",
					"lastName": "Patel",
					"phoneNo": "7707030801",
					"email": "alok@alok",
					"dateOfBirth": "12/12/12",
					"customerType": null,
					"gender": "male",
					"street": "12",
					"city": "sdf",
					"state": "gz",
					"country": "us",
					"zipcode": "23423",
					"fax": null,
					"customerCreatedDate": null,
					"balance": 24
				}
			];
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