(function() {
	'use strict';

	angular.module('sampleApp').controller('addPopupController', addPopupController);

	addPopupController.$inject = [ '$scope', '$rootScope', 'device.utility','GlobalVariable','DialogFactory','dataService','$timeout'];

	function addPopupController($scope, $rootScope, device ,GlobalVariable,DialogFactory,dataService,$timeout) 
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
				var url = "http://localhost:8080/addBrand";
				request.brandName = $scope.brandName;
				request.brandDescription = $scope.brandDescription;
			}
			else if(name == 'Vendor') {
				var url = "http://localhost:8080/addVendor";
				request.vendorName = $scope.brandName;
				request.description = $scope.brandDescription;
				request.commision = $scope.commision;
			}
			else if(name == 'Category') {
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
			
		}
		render();
	}
		
})();