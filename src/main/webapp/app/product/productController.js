(function() {
	'use strict';

	angular.module('sampleApp').controller('productController', Body);

	Body.$inject = [ '$scope', '$rootScope', 'device.utility','GlobalVariable','RestrictedCharacter.Types','dataService','$state','$stateParams'];

	function Body($scope, $rootScope, device ,GlobalVariable,restrictCharacter,dataService,$state,$stateParams) {
		
		$scope.device = device;
		$scope.restrictCharacter=restrictCharacter;
		$scope.GlobalVariable = GlobalVariable;
		GlobalVariable.isLoginPage = false;
	
		
		$scope.populateRetailPrice = function()
		{
			if($scope.prodCP !== '' && $scope.prodCP !== undefined)
			{
				if($scope.prodMarkup !== '' && $scope.prodMarkup !== undefined)
				{
					$scope.prodRetail = parseFloat($scope.prodCP) * parseFloat($scope.prodMarkup);
				}	
			}
			else
			{
				$scope.prodRetail = 0;
			}	
		};
		$scope.setVendorType = function(vendor)
		{
			$scope.selectedVendorType = vendor;
			$scope.vendorId = vendor.vendorId;
		};
		$scope.setBrandType = function(brand)
		{
			$scope.selectedBrandType = brand;
			$scope.brandId = brand.brandId;
		};
		$scope.setCategoryType = function(category)
		{
			$scope.selectedCategoryType = category;
			$scope.categoryId = category.categoryId;
			$scope.categoryDescription = category.description;
		};
		$scope.generateRandomId = function()
		{
			$scope.productId = Math.round(((Math.random() * 10) * 10)+1000000000);
		}
		$scope.addProduct = function()
		{
			var request={
				"productNo":$scope.productId,
				"categoryId": $scope.categoryId,
				"vendorId": $scope.vendorId,
				"brandId": $scope.brandId,
				"altNo": $scope.altNo,
				"description":$scope.description,
				"costPrice":$scope.prodCP,
				"markup": $scope.prodMarkup,
				"retailPrice": $scope.prodRetail,
				"quantity": $scope.prodQuantity,
				"minProductQuantity": $scope.prodMinquantity,
				"returnRule": "NextWeek",
				"image": "image",
				"createdDate": "1000-01-01 00:00:00"
			};
			request= JSON.stringify(request);
			var url ="http://localhost:8080/addProduct";
			dataService.Post(url,request,addProductSuccessHandler,addProductErrorHandler,"application/json","application/json");
		}
		function addProductSuccessHandler(response)
		{
			
		}
		function addProductErrorHandler(response)
		{
			
		}
		function render()
		{
			console.log("params = "+$state.params);
			$scope.prodCP = 0;
			$scope.prodMarkup = 0;
			$scope.prodRetail = 0;
			
		}
		render();
	}
		
})();