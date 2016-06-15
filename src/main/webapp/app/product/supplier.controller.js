(function() {
	'use strict';

	angular.module('sampleApp').controller('SupplierController', SupplierController);

	SupplierController.$inject = [ '$scope', '$rootScope', 'device.utility','GlobalVariable','$state','DialogFactory','dataService','modalService'];

	function SupplierController($scope, $rootScope, device ,GlobalVariable,$state,DialogFactory,dataService,modalService) {
		
		$scope.device = device;
		$scope.GlobalVariable = GlobalVariable;
		GlobalVariable.isLoginPage = false;
		$scope.selectedIndex = 0;
		$scope.isAsc = false;
		GlobalVariable.enableEdit = false;

		
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
			GlobalVariable.enableEdit = false;
			GlobalVariable.addHeaderName = "Add Category";
			GlobalVariable.textName = "Category";
			GlobalVariable.addButtonName = "Add Category";
			var _tmPath = 'app/product/AddPopup.html';
			var _ctrlPath = 'addPopupController';
			DialogFactory.show(_tmPath, _ctrlPath, addPopupControllerCallBack);
		};
		$scope.editCategory = function(categoryDetails)
		{
			GlobalVariable.enableEdit = true;
			GlobalVariable.editBrandName = categoryDetails.categoryName;
			GlobalVariable.editBrandDescription = categoryDetails.categoryDescription;
			GlobalVariable.editBrandId = categoryDetails.categoryId;
			GlobalVariable.addHeaderName = "Edit Category";
			GlobalVariable.textName = "Category";
			GlobalVariable.addButtonName = "Edit Category";
			var _tmPath = 'app/product/AddPopup.html';
			var _ctrlPath = 'addPopupController';
			DialogFactory.show(_tmPath, _ctrlPath, addPopupControllerCallBack);
		};
		function addPopupControllerCallBack()
		{
			GlobalVariable.addesSuccessfull = true;
			GlobalVariable.addHeaderName = "";
			GlobalVariable.textName = "";
			GlobalVariable.addButtonName = "";
		}
		$scope.deleteCategory = function(categoryId)
		{
			$scope.deleteCategoryrId = categoryId;
			modalService.showModal('', {
				isCancel : true
			}, "Are you Sure Want to Delete ? ", $scope.callBackDeleteAction);
		};
		$scope.callBackDeleteAction = function(isOKClicked)
		{
			if(isOKClicked)
			{
				var request = new Object();
				request.categoryId = $scope.deleteCategoryrId;
				request = JSON.stringify(request);

				dataService.Post(url,request,deleteSuccessHandler,deleteErrorHandler,"application/json","application/json");
			}
		};
		function deleteSuccessHandler(response)
		{
			console.log(response);
		}
		function deleteErrorHandler(response)
		{
			console.log(response);
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