(function() {
	'use strict';

	angular.module('sampleApp').controller('addPopupController', addPopupController);

	addPopupController.$inject = [ '$scope', '$rootScope', 'device.utility','GlobalVariable','DialogFactory','dataService'];

	function addPopupController($scope, $rootScope, device ,GlobalVariable,DialogFactory,dataService) 
	{
		$scope.device= device;
		$scope.GlobalVariable = GlobalVariable;
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
			console.log(response);
		}
		function addItemsErrorHandler(response)
		{
			console.log(response);
		}
		function render()
		{
			
		}
		render();
	}
		
})();