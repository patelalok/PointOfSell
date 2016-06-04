(function() {
	'use strict';

	angular.module('sampleApp').controller('sellController', sellController);

	sellController.$inject = [ '$scope', '$rootScope', 'device.utility','GlobalVariable','DialogFactory'];

	function sellController($scope, $rootScope, device ,GlobalVariable,DialogFactory) {
		
		$scope.device = device;
		$scope.GlobalVariable = GlobalVariable;
		GlobalVariable.isLoginPage = false;
		var i=0;
		$scope.pageSize = 10;
		
		$scope.testData = [];
		
		$scope.addRow = function()
		{
			
			$scope.testData.push({"itemNo":i,
				"item":"check",
				"quantity":89,
				"retail":"test",
				"discount":20,
				"tax":10.98,
				"total":20.00,
				"image":"test",
				"stock":5});
			i++;
			
		};
		$scope.removeRow = function(itemNo){				
		var index = -1;		
		var comArr = eval( $scope.testData );
		for( var i = 0; i < comArr.length; i++ ) {
			if( comArr[i].itemNo === itemNo ) {
				index = i;
				break;
			}
		}
		if( index === -1 ) {
			alert( "Something gone wrong" );
		}
		$scope.testData.splice( index, 1 );		
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