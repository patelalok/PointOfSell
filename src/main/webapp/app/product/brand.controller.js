(function() {
	'use strict';

	angular.module('sampleApp').controller('BrandController', BrandController);

	BrandController.$inject = [ '$scope', '$rootScope', 'device.utility','GlobalVariable','$state','DialogFactory'];

	function BrandController($scope, $rootScope, device ,GlobalVariable,$state,DialogFactory) {
		
		$scope.device = device;
		$scope.GlobalVariable = GlobalVariable;
		GlobalVariable.isLoginPage = false;
		$scope.selectedIndex = 0;
		$scope.isAsc = false;
		
$scope.brandData = [{
			
			"name":"DressShirt",
			"desc":"25 April 2016",
			"noofprod":5
		},
		{
			"name":"DressShirt",
			"desc":"25 April 2016",
			"noofprod":5.00,
			"count":26
		},
		{
			"name":"DressShirt",
			"desc":"25 April 2016",
			"noofprod":5.00,
			"count":26
		},
		{
			"name":"DressShirt",
			"desc":"25 April 2016",
			"noofprod":5.00,
			"count":26
		},
		{
			"name":"DressShirt",
			"desc":"25 April 2016",
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
			GlobalVariable.addHeaderName = "Add Brand";
			GlobalVariable.textName = "Brand";
			GlobalVariable.addButtonName = "Add Brand";
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