(function() {
	'use strict';

	angular.module('sampleApp').controller('productController', Body);

	Body.$inject = [ '$scope', '$rootScope', 'device.utility','GlobalVariable','RestrictedCharacter.Types','dataService'];

	function Body($scope, $rootScope, device ,GlobalVariable,restrictCharacter,dataService) {
		
		$scope.device = device;
		$scope.restrictCharacter=restrictCharacter;
		$scope.GlobalVariable = GlobalVariable;
		GlobalVariable.isLoginPage = false;
		/*GlobalVariable.getVendors =  [
		                                        {
		                                            "vendorId": 11,
		                                            "vendorName": "Tcl-delta",
		                                            "commision": "12",
		                                            "phoneNo": 1234567890,
		                                            "companyName": "TCS",
		                                            "address": "This is from china"
		                                          }
		                                        ];
		GlobalVariable.getBrands =[
		                           {
		                        	    "brandId": 1,
		                        	    "brandName": "test",
		                        	    "brandDescription": "test"
		                        	  },
		                        	  {
		                        	    "brandId": 2,
		                        	    "brandName": "test",
		                        	    "brandDescription": "test"
		                        	  },
		                        	  {
		                        	    "brandId": 3,
		                        	    "brandName": "Iphone",
		                        	    "brandDescription": "This is iphone brand"
		                        	  }
		                        	];
		GlobalVariable.getCategory=[
		  {
		    "categoryId": 1,
		    "categoryName": "dtgf",
		    "description": "fdgdf"
		  },
		  {
		    "categoryId": 2,
		    "categoryName": "Phone",
		    "description": "This category is for phone"
		  }
		]*/
		
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
			$scope.categoryrId = category.categoryId;
			$scope.categoryDescription = category.description;
		};
		$scope.generateRandomId = function()
		{
			$scope.productId = Math.round((Math.random() * 10) * 10);
		}
		$scope.addProduct = function()
		{
			var request={
				"productNo":$scope.productId,
				"categoryId": $scope.categoryId,
				"vendorId": $scope.vendorId,
				"brandId": $scope.brandId,
				"altNo": $scope.altNo,
				"description":$scope.prodCP,
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
		function render($stateParams)
		{
			console.log("params = "+$stateParams);
		}
		render();
	}
		
})();