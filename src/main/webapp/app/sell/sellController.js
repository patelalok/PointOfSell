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
			
			$scope.testData.push({"itemNo":Math.round((Math.random() * 10) * 10),
				"item":"check",
				"quantity":89,
				"retail":"test",
				"discount":20,
				"tax":10.98,
				"total":20.00,
				"stock":5});
			
			
			$scope.quantity =90;
			$scope.price =10;
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
	$scope.changeQuantity= function()
	{
		var searchTxt = $scope.searchValue.toString();
		if(searchTxt.indexOf(".") >=0 )
		{
			$scope.quantity = $scope.testData[$scope.testData.length-1].quantity;
			$scope.total = $scope.searchValue;
		}
		else
		{
			$scope.quantity = $scope.searchValue;
			$scope.total = $scope.testData[$scope.testData.length-1].total;
		}	
		
		$scope.testData.push({"itemNo":$scope.testData[$scope.testData.length-1].itemNo,
			"item":$scope.testData[$scope.testData.length-1].item,
			"quantity":$scope.quantity,
			"retail":$scope.testData[$scope.testData.length-1].retail,
			"discount":$scope.testData[$scope.testData.length-1].discount,
			"tax":$scope.testData[$scope.testData.length-1].tax,
			"total":$scope.total,
			"stock":$scope.testData[$scope.testData.length-1].stock});
		//for(var i=0;i<$scope.testData.length-1;i++)
			//{
			$scope.removeRowOnSearch($scope.testData[$scope.testData.length-2].itemNo);
			//}
		
	};
	$scope.removeRowOnSearch = function(itemNo){				
		var index = -1;		
		var comArr = eval( $scope.testData );
		for( var i = 0; i < comArr.length; i++ ) {
			if(itemNo.toString().indexOf(".")>=0)
			{
				if( comArr[i].itemNo === itemNo ) {
					index = i;
					break;
				}
			}
			else
			{
				if( comArr[i].itemNo === itemNo ) {
					index = i;
					break;
				}
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