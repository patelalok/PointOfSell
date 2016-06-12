(function() {
	'use strict';

	angular.module('sampleApp').controller('addPopupController', addPopupController);

	addPopupController.$inject = [ '$scope', '$rootScope', 'device.utility','GlobalVariable','DialogFactory','dataService','$timeout','$state'];

	function addPopupController($scope, $rootScope, device ,GlobalVariable,DialogFactory,dataService,$timeout,$state) 
	{
		$scope.device= device;
		$scope.GlobalVariable = GlobalVariable;
		$scope.successAlert = false;
		$scope.closePopup = function()
		{
			DialogFactory.close(true);
		};
		
		$scope.addItems = function(name)
		{
			
			var request = new Object();
			if(name == 'Brand') {
				
				if(GlobalVariable.enableEdit == true)
				{	
					var url = "http://localhost:8080/editBrand";
					request.brandId = GlobalVariable.editBrandId; 
				}
				else
				var url = "http://localhost:8080/addBrand";
				request.brandName = $scope.brandName;
				request.brandDescription = $scope.brandDescription;
			}
			else if(name == 'Vendor') {
				if(GlobalVariable.enableEdit == true)
				{	
					var url = "http://localhost:8080/editVendor";
					request.vendorId = GlobalVariable.editBrandId;
				}
				else
				var url = "http://localhost:8080/addVendor";
				request.vendorName = $scope.brandName;
				request.description = $scope.brandDescription;
				request.commision = $scope.commision;
			}
			else if(name == 'Category') {
				if(GlobalVariable.enableEdit == true)
				{	
					var url = "http://localhost:8080/editCategory";
					request.categoryId = GlobalVariable.editBrandId;
				}
				else
				var url = "http://localhost:8080/addCategory";
				request.categoryName = $scope.brandName;
				request.description = $scope.brandDescription;

			}


			request = JSON.stringify(request);

			dataService.Post(url,request,addItemsSuccessHandler,addItemsErrorHandler,"application/json","application/json");
		};
		function addItemsSuccessHandler(response)
		{
			DialogFactory.close(true);
			GlobalVariable.successAlert = true;
			if(GlobalVariable.textName == 'Brand')
				$state.go('brand');
			else if(GlobalVariable.textName == 'Vendor')
				$state.go('vendor');
			else
				$state.go('category');
			console.log(response);
			$timeout(function() {
				$rootScope.closeBootstrapAlert();
			}, 9000);
		}
		function addItemsErrorHandler(response)
		{
			DialogFactory.close(true);
			$timeout(function() {
				$rootScope.closeBootstrapAlert();
			}, 9000);
		}
		
		function render()
		{
			$scope.brandName = '';
			$scope.brandDescription ='';
			if(GlobalVariable.textName == 'Vendor')
			$scope.commision = '';
			if(GlobalVariable.enableEdit == true)
			{
				$scope.brandName = GlobalVariable.editBrandName;
				$scope.brandDescription = GlobalVariable.editBrandDescription;
				if(GlobalVariable.textName == 'Vendor')
				$scope.commision = GlobalVariable.editCommision;
			}	
		}
		render();
	}
		
})();