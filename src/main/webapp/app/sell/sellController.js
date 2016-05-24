(function() {
	'use strict';

	angular.module('sampleApp').controller('sellController', sellController);

	sellController.$inject = [ '$scope', '$rootScope', 'device.utility','GlobalVariable','DialogFactory'];

	function sellController($scope, $rootScope, device ,GlobalVariable,DialogFactory) {
		
		$scope.device = device;
		$scope.GlobalVariable = GlobalVariable;
		GlobalVariable.isLoginPage = false;

		$scope.pageSize = 10;
		
		$scope.testData = [{
			
			"itemNo":1,
			"item":"paint",
			"quantity":1,
			"retail":"test",
			"discount":20,
			"tax":10.98,
			"total":20.00
		},
		{
			"itemNo":1,
			"item":"paint",
			"quantity":1,
			"retail":"test",
			"discount":20,
			"tax":10.98,
			"total":20.00
		}
			
		];
		
		$scope.addRow = function()
		{
			$scope.testData.push({"itemNo":1,
				"item":"paint",
				"quantity":1,
				"retail":"test",
				"discount":20,
				"tax":10.98,
				"total":20.00,
				"image":"test"});
		};
		$scope.openCashPopup = function()
		{
			var _tmPath = 'app/sell/paymentPopup.html';
			var _ctrlPath = 'paymentPopupController';
			DialogFactory.show(_tmPath, _ctrlPath, callbackPayment);
		};
		$scope.createCustomer = function()
		{
			var _tmPath = 'app/AddCustomer/addcustomer.html';
			var _ctrlPath = 'addCustomerController';
			DialogFactory.show(_tmPath, _ctrlPath, callbackPayment);
		};
		function callbackPayment()
		{
			
		}
		function render()
		{
			$scope.currentPageIndexArr = 0;
		}
		render();
	}
		
})();