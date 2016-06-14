(function() {
	'use strict';

	angular.module('sampleApp').controller('mainProductController', mainProductController);

	mainProductController.$inject = [ '$scope', '$rootScope', 'device.utility','GlobalVariable','$state','DialogFactory','$timeout','RestrictedCharacter.Types','$filter'];

	function mainProductController($scope, $rootScope, device ,GlobalVariable,$state,DialogFactory,$timeout,restrictCharacter,$filter) {
		
		$scope.device = device;
		$scope.GlobalVariable = GlobalVariable;
		$scope.getProductDtls = [];

		$scope.restrictCharacter=restrictCharacter;
		GlobalVariable.isLoginPage = false;
		$scope.selectedIndex = 0;
		$scope.isAsc = false;
		$scope.enabled = true;
		
		
		$scope.navigateToAddProduct = function(page)
		{
			if(page == 'product')
			{
				var test = new Object();
				test.name = 'Prodcut';
				test.last = 'No';
				$state.go(page,{obj:test});
			}
			else
			{	
			$state.go(page);
			}
		};
		
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
		
		$scope.applyFilter = function()
		{
			$scope.getProductDtls = $filter('filter')($scope.getProductDtls,$scope.vType);
		};
		$scope.checkValue = function()
		{
			if($scope.cType == undefined && $scope.bType == undefined && $scope.vType == undefined)
				{
				$scope.getProductDtls = GlobalVariable.getProducts;
				}
		};
		function render()
		{
			$scope.currentPageIndexArr = 0;
			$scope.curPageOnTotalLen = 0;
			$scope.totalLength = 0;
			$scope.productType = "select";
			
			$scope.brandOptions = GlobalVariable.getBrands;
			$scope.categoryOptions = GlobalVariable.getCategory;
			$scope.vendorOptions = GlobalVariable.getVendors;
			$scope.getProductDtls = GlobalVariable.getProducts;
			
				
		}
		render();
		
	}
		
})();