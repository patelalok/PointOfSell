(function() {
	'use strict';

	angular.module('sampleApp').controller('productController', Body);

	Body.$inject = [ '$scope', '$rootScope', 'device.utility','GlobalVariable','RestrictedCharacter.Types','dataService','$state','$stateParams','getProductDetails','util','$timeout'];

	function Body($scope, $rootScope, device ,GlobalVariable,restrictCharacter,dataService,$state,$stateParams,getProductDetails,util,$timeout) {
		
		$scope.device = device;
		$scope.restrictCharacter=restrictCharacter;
		$scope.GlobalVariable = GlobalVariable;
		GlobalVariable.isLoginPage = false;
		GlobalVariable.productSuccessAlert = false;
		GlobalVariable.addedSucces= false;
		GlobalVariable.editedSuccess= false;
		var authElemArray = new Array();

		$scope.closeBootstrapAlert = function()
		{
			GlobalVariable.productSuccessAlert = false;
			GlobalVariable.addedSucces= false;
			GlobalVariable.editedSuccess= false;
		};
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

		$scope.populateMarkup = function()
		{
			if($scope.prodRetail !== '' && $scope.prodRetail !== undefined)
			{
				if($scope.prodCP !== '' && $scope.prodCP !== undefined)
				{
					$scope.prodMarkup = ((parseFloat($scope.prodRetail) -(parseFloat($scope.prodCP)))/(parseFloat($scope.prodCP))) * 100;
				}
				if($scope.prodMarkup == 'Infinity')
				{
					$scope.prodMarkup = 0;
				}
			}
			else
			{
				$scope.prodRetail = 0;
				$scope.prodMarkup =0;
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
		$scope.setReturnType = function(returnType)
		{
				$scope.selectedReturnType = returnType;
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
		function addValidated()
		{
			authElemArray = new Array();
			if($scope.productId == '' || $scope.productId== undefined)
			{
				authElemArray.push({
					'id' : 'productId',
					'msg' : 'Product Number cannot be empty'
				});
			}
			if($scope.selectedVendorType == undefined)
			{
				authElemArray.push({
					'id' : 'vendName',
					'msg' : 'Product Number cannot be empty'
				});
			}
			if($scope.selectedCategoryType == undefined) {
				authElemArray.push({
					'id': 'categoryName',
					'msg': 'Product Number cannot be empty'
				});
			}

				if($scope.selectedBrandType == undefined) {
					authElemArray.push({
						'id': 'brandName',
						'msg': 'Product Number cannot be empty'
					});
				}
			if(GlobalVariable.editProduct == false)
			{
				for(var i=0;i<$scope.prodAltNo.length;i++)
				{
					if($scope.productId == $scope.prodAltNo[i].productNo)
					{
						authElemArray.push({
							'id' : 'productId',
							'msg' : 'Product Number already exists'
						});

					}
				}

				if($scope.altNo !==''  && $scope.altNo != undefined)
				{
					for(var i=0;i<$scope.prodAltNo.length;i++)
					{
						if($scope.altNo == $scope.prodAltNo[i].atlNo)
						{
							authElemArray.push({
								'id' : 'altNo',
								'msg' : 'Alt Number already exists'
							});

						}
					}
				}
			}



			if (authElemArray.length >= 1) {
				util.customError.show(authElemArray, "");

				return false;
			} else {
				return true;
			}


		}
		$scope.addProduct = function()
		{
			util.customError.hide(['vendName','categoryName','brandName','productId','altNo']);
			if(addValidated())
			{
				if(GlobalVariable.editProduct == true)
				{
					if($scope.selectedReturnType == 'Custom')
						$scope.retType = $scope.customReturn;
					else
						$scope.retType = $scope.selectedReturnType;

					if($scope.selectedCategoryType == 'Phone')
						$scope.phoneIMEI = $scope.IMEI;
					else
						$scope.phoneIMEI = '';
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
						"returnRule":$scope.retType,
						"imeiNo":$scope.phoneIMEI,
						"image": "image",
						"createdDate": "1000-01-01 00:00:00",
						"addTax":$scope.productYesyNO
					};
					var url ="http://localhost:8080/editProduct";
				}
				else
				{

					if($scope.selectedReturnType == 'Custom')
						$scope.retType = $scope.customReturn;
					else
						$scope.retType = $scope.selectedReturnType;


					if($scope.selectedCategoryType == 'Phone')
						$scope.phoneIMEI = $scope.IMEI
					else
						$scope.phoneIMEI = '';
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
						"returnRule": $scope.retType,
						"imeiNo":$scope.phoneIMEI,
						"image": "image",
						"createdDate": "1000-01-01 00:00:00",
						"addTax":$scope.productYesyNO
					};
					var url ="http://localhost:8080/addProduct";
				}


				request= JSON.stringify(request);

				dataService.Post(url,request,addProductSuccessHandler,addProductErrorHandler,"application/json","application/json");
			}

		}
		function addProductSuccessHandler(response)
		{
			GlobalVariable.productSuccessAlert = true;
			if(GlobalVariable.editProduct == true)
			{
				
				
				GlobalVariable.editedSuccess= true;
			}
			else
			{
				GlobalVariable.addedSucces= true;
				resetValues();
			}

			getProductDetails.getProductDetail();
			//$state.go('productmain');
			$timeout(function() {
				$scope.closeBootstrapAlert();
			}, 9000);

		}
		function resetValues()
		{
			$scope.productId = '';
			$scope.selectedVendorType = '';
			$scope.selectedBrandType = '';
			$scope.selectedCategoryType='';
			$scope.description ='';
			$scope.IMEI ='';
			$scope.altNo ='';
			$scope.prodCP =0;
			$scope.prodMarkup=0;
			$scope.prodRetail=0;
			$scope.selectedReturnType ='';
			$scope.customReturn='';
			$scope.prodQuantity ='';
			$scope.prodMinquantity='';
		}
		function addProductErrorHandler(response)
		{
			
		}
		$scope.cancelProduct = function()
		{
			$state.go('productmain');
		};
		function getProAlt()
		{
			var url='http://localhost:8080/getProductNoAndAltNo';
			dataService.Get(url,getProAltSuccess,getProAltError,"application/json","application/json");
		}
		function getProAltSuccess(response)
		{
			$scope.prodAltNo = response;
		}
		function getProAltError(response)
		{

		}
		function render()
		{
			console.log("params = "+$state.params);
			getProAlt();
			$scope.prodCP = 0;
			$scope.prodMarkup = 0;
			$scope.prodRetail = 0;
			$scope.productYesyNO= true;
			if(GlobalVariable.editProduct == true)
			{
				$scope.productId = GlobalVariable.editProductDetails.productNo;
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
				$scope.productYesyNO = GlobalVariable.editProductDetails.tax;
				$scope.selectedReturnType = GlobalVariable.editProductDetails.returnRule;
			}

		}
		render();
	}
		
})();