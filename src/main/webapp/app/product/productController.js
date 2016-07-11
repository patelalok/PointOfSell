(function() {
	'use strict';

	angular.module('sampleApp').controller('productController', Body);

	Body.$inject = [ '$scope', '$rootScope', 'device.utility','GlobalVariable','RestrictedCharacter.Types','dataService','$state','$stateParams','getProductDetails'];

	function Body($scope, $rootScope, device ,GlobalVariable,restrictCharacter,dataService,$state,$stateParams,getProductDetails) {
		
		$scope.device = device;
		$scope.restrictCharacter=restrictCharacter;
		$scope.GlobalVariable = GlobalVariable;
		GlobalVariable.isLoginPage = false;
		GlobalVariable.productSuccessAlert = false;
		GlobalVariable.addedSucces= false;
		GlobalVariable.editedSuccess= false;
	
		
		$scope.populateRetailPrice = function()
		{
			if($scope.prodCP !== '' && $scope.prodCP !== undefined)
			{
				if($scope.prodMarkup !== '' && $scope.prodMarkup !== undefined)
				{
					$scope.prodRetail =(parseFloat($scope.prodCP))+(parseFloat($scope.prodCP) *( (parseFloat($scope.prodMarkup))/100));
				}	
			}
			else
			{
				$scope.prodRetail = 0;
			}	
		};
		$scope.setVendorType = function(vendorId,vendorName)
		{
			$scope.selectedVendorType = vendorName;
			$scope.vendorId = vendorId;
		};
		$scope.setBrandType = function(brandId,brandName)
		{
			$scope.selectedBrandType = brandName;
			$scope.brandId = brandId;
		};
		$scope.setCategoryType = function(categoryId,categoryName,categoryDescription)
		{
			$scope.selectedCategoryType = categoryName;
			$scope.categoryId = categoryId;
			$scope.categoryDescription = categoryDescription;
		};
		$scope.generateRandomId = function()
		{
			$scope.productId = Math.round(((Math.random() * 10) * 10)+1000000000);
		}
		$scope.addProduct = function()
		{
			if(GlobalVariable.editProduct == true)
			{
				var request={
						"productId": GlobalVariable.editProductDetails.productId,
						"productNo":GlobalVariable.editProductDetails.productNo,
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
				var url ="http://localhost:8080/editProduct";
			}
			else
			{
				var request = {
		        	    
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
				var url ="http://localhost:8080/addProduct";
			}	
			
			 
			request= JSON.stringify(request);
			
			dataService.Post(url,request,addProductSuccessHandler,addProductErrorHandler,"application/json","application/json");
		}
		function addProductSuccessHandler(response)
		{
			GlobalVariable.productSuccessAlert = true;
			if(GlobalVariable.editProduct == true)
			{
				
				
				GlobalVariable.editedSuccess= true;
			}
			else
				GlobalVariable.addedSucces= true;
			getProductDetails.getProductDetail();
			$state.go('productmain');
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
			if(GlobalVariable.editProduct == true)
			{
				$scope.productId = GlobalVariable.editProductDetails.productId;
				$scope.setVendorType(GlobalVariable.editProductDetails.vendorId,GlobalVariable.editProductDetails.vendorName);
				$scope.setBrandType(GlobalVariable.editProductDetails.brandId,GlobalVariable.editProductDetails.brandName);
				$scope.setCategoryType(GlobalVariable.editProductDetails.categoryId,GlobalVariable.editProductDetails.categoryName,'');
				//$scope.selectedVendorType.vendorName = GlobalVariable.editProductDetails.vendorName;
				//$scope.selectedBrandType.brandrName = GlobalVariable.editProductDetails.brandName;
				//$scope.selectedCatgeoryType.categoryName = GlobalVariable.editProductDetails.categoryName;
				$scope.description = GlobalVariable.editProductDetails.description;
				$scope.altNo = GlobalVariable.editProductDetails.altNo;
				$scope.prodCP = GlobalVariable.editProductDetails.costPrice;
				$scope.prodMarkup = GlobalVariable.editProductDetails.markup;
				$scope.prodRetail = GlobalVariable.editProductDetails.retailPrice;
				$scope.prodQuantity = GlobalVariable.editProductDetails.quantity;
				$scope.prodMinquantity = GlobalVariable.editProductDetails.minProductQuantity;
			}	
		}
		render();
	}
		
})();