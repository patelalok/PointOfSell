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

		
$rootScope.closeBootstrapAlert = function()
{
	GlobalVariable.successAlert = false;
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
		
		$scope.navigateToProduct = function()
		{
			$state.go('productmain');
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