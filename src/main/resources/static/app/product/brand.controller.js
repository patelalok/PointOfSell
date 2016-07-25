(function() {
	'use strict';

	angular.module('sampleApp').controller('BrandController', BrandController);

	BrandController.$inject = [ '$scope', '$rootScope', 'device.utility','GlobalVariable','$state','DialogFactory','modalService','dataService'];

	function BrandController($scope, $rootScope, device ,GlobalVariable,$state,DialogFactory,modalService,dataService) {
		
		$scope.device = device;
		$scope.GlobalVariable = GlobalVariable;
		GlobalVariable.isLoginPage = false;
		$scope.selectedIndex = 0;
		$scope.isAsc = false;
		GlobalVariable.enableEdit = false;
		

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

		$rootScope.closeBootstrapAlert = function()
		{
			GlobalVariable.successAlert = false;
		};
		$scope.openAddPopup = function()
		{
			GlobalVariable.enableEdit = false;
			GlobalVariable.addHeaderName = "Add Brand";
			GlobalVariable.textName = "Brand";
			GlobalVariable.addButtonName = "Add Brand";
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
		$scope.editBrand = function(brandDetails)
		{
			GlobalVariable.enableEdit = true;
			GlobalVariable.editBrandName = brandDetails.brandName;
			GlobalVariable.editBrandDescription = brandDetails.brandDescription;
			GlobalVariable.editBrandId = brandDetails.brandId;
			GlobalVariable.addHeaderName = "Edit Brand";
			GlobalVariable.textName = "Brand";
			GlobalVariable.addButtonName = "Edit Brand";
			var _tmPath = 'app/product/AddPopup.html';
			var _ctrlPath = 'addPopupController';
			DialogFactory.show(_tmPath, _ctrlPath, addPopupControllerCallBack);
		};
		$scope.deleteBrand =function(brandId)
		{
			$scope.deleteBrandId = brandId;
			modalService.showModal('', {
				isCancel : true
			}, "Are you Sure Want to Delete ? ", $scope.callBackDeleteAction);
		};
		$scope.callBackDeleteAction = function(isOKClicked)
		{
			if(isOKClicked)
			{
				var request = new Object();
				request.brandId = $scope.deleteBrandId;
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