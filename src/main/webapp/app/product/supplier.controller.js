(function() {
	'use strict';

	angular.module('sampleApp').controller('SupplierController', SupplierController);

	SupplierController.$inject = [ '$scope', '$rootScope', 'device.utility','GlobalVariable','$state','DialogFactory'];

	function SupplierController($scope, $rootScope, device ,GlobalVariable,$state,DialogFactory) {
		
		$scope.device = device;
		$scope.GlobalVariable = GlobalVariable;
		GlobalVariable.isLoginPage = false;
		$scope.selectedIndex = 0;
		$scope.isAsc = false;
		
$scope.supplierData = [{
			
			"name":"DressShirt",
			"desc":"25 April 2016",
			"defaultmarkup":"5%",
			"noofprod":5
		},
		{
			"name":"DressShirt",
			"desc":"25 April 2016",
			"defaultmarkup":"15%",
			"noofprod":5.00,
			"count":26
		},
		{
			"name":"DressShirt",
			"desc":"25 April 2016",
			"defaultmarkup":"50%",
			"noofprod":5.00,
			"count":26
		},
		{
			"name":"DressShirt",
			"desc":"25 April 2016",
			"defaultmarkup":"25%",
			"noofprod":5.00,
			"count":26
		},
		{
			"name":"DressShirt",
			"desc":"25 April 2016",
			"defaultmarkup":"35%",
			"noofprod":5.00,
			"count":26
		}
			
		];
		
		
		$scope.sortColumnData = function(index) {
			if ($scope.testGridData != null && $scope.testGridData.length > 0) {
				if (index != 0) {
					return false;
				}
				if ($scope.isAsc) {
					$scope.isAsc = false;
				} else {
					$scope.isAsc = true;$scope
				}$scope
			}
		};
		
		$scope.navigateToProduct = function()
		{
			$state.go('product');
		};
		$scope.openAddPopup = function()
		{
			GlobalVariable.addHeaderName = "Add Category";
			GlobalVariable.textName = "Category";
			GlobalVariable.addButtonName = "Add Category";
			var _tmPath = 'app/product/AddPopup.html';
			var _ctrlPath = 'addPopupController';
			DialogFactory.show(_tmPath, _ctrlPath, addPopupControllerCallBack);
		};
		function addPopupControllerCallBack()
		{
			GlobalVariable.addHeaderName = "";
			GlobalVariable.textName = "";
			GlobalVariable.addButtonName = "";
		}
		function render()
		{
			$scope.currentPageIndexArr = 0;
			$scope.curPageOnTotalLen = 0;
			$scope.totalLength = 0;
		}
		render();
		
	}
		
})();