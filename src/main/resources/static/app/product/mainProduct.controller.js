(function() {
	'use strict';

	angular.module('sampleApp').controller('mainProductController', mainProductController);

	mainProductController.$inject = [ '$scope', '$rootScope', 'device.utility','GlobalVariable','$state','DialogFactory','$timeout','RestrictedCharacter.Types','$filter','util'];

	function mainProductController($scope, $rootScope, device ,GlobalVariable,$state,DialogFactory,$timeout,restrictCharacter,$filter,util) {
		
		$scope.device = device;
		$scope.GlobalVariable = GlobalVariable;
		$scope.getProductDtls = [];

		$scope.restrictCharacter=restrictCharacter;
		GlobalVariable.isLoginPage = false;
		$scope.selectedIndex = 0;
		$scope.isAsc = false;
		$scope.enabled = true;
		//util.Wait(true);
		loadCDetails();
		/*GlobalVariable.productSuccessAlert = false;
		GlobalVariable.addedSucces= false;
		GlobalVariable.editedSuccess= false;*/
		
		
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
			GlobalVariable.editProduct = false;
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
		
		/*$scope.applyFilter = function()
		{
			$scope.getProductDtls = $filter('filter')($scope.getProductDtls,$scope.vType);
		};*/
		$scope.checkValue = function()
		{
			if($scope.cType == undefined && $scope.bType == undefined && $scope.vType == undefined)
				{
				$scope.bType.filterValue = '';
				}
			if($scope.productType == "select")
				$scope.bType.filterValue = '';
				
		};
		$scope.navigateToSales = function(productId)
		{
			GlobalVariable.productIdHistory = productId;
			var _tmPath = 'app/product/historyPopup.html';
			var _ctrlPath = 'historyPopupController';
			DialogFactory.show(_tmPath, _ctrlPath, callbackPaymentHistory,undefined, undefined, 'lg');
		};
		function callbackPaymentHistory()
		{
			
		}
		$scope.editProduct = function(row)
		{
			
			var request = new Object();
			GlobalVariable.editProductDetails = row;
			GlobalVariable.editProduct = true;
			$state.go('product');
			
		};
		function render()
		{
			$scope.currentPageIndexArr = 0;
			$scope.curPageOnTotalLen = 0;
			$scope.totalLength = 0;
			$scope.productType = "select";

			//$scope.loadCDetails();
			$timeout(function() {
				$scope.closeBootstrapAlert();
			}, 9000);


			
				
		}
		function loadCDetails()
		{
			/*util.Wait(true);*/
			$scope.brandOptions = GlobalVariable.getBrands;
			$scope.categoryOptions = GlobalVariable.getCategory;
			$scope.vendorOptions = GlobalVariable.getVendors;
			$scope.getProductDtls = GlobalVariable.getProducts;
			/*$timeout(function() {
				util.Wait(false);
			}, 9000);*/
		};
		$scope.closeBootstrapAlert = function()
		{
			GlobalVariable.productSuccessAlert = false;
			GlobalVariable.addedSucces= false;
			GlobalVariable.editedSuccess= false;
		};
		render();
		
	}
		
})();