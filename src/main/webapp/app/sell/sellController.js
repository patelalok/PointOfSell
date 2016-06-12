(function() {
	'use strict';

	angular.module('sampleApp').controller('sellController', sellController);

	sellController.$inject = [ '$scope', '$rootScope', 'device.utility','GlobalVariable','DialogFactory','modalService','RestrictedCharacter.Types'];

	function sellController($scope, $rootScope, device ,GlobalVariable,DialogFactory,modalService,restrictCharacter) {
		
		$scope.device = device;
		$scope.GlobalVariable = GlobalVariable;
		$scope.restrictCharacter=restrictCharacter;
		GlobalVariable.isLoginPage = false;
		var i=0;
		$scope.pageSize = 10;
		
		$rootScope.testData = [];
		GlobalVariable.getProducts = [{
			 "productId": 6,
			    "productNo": "123456789012",
			    "categoryId": 1,
			    "vendorId": 11,
			    "brandId": 1,
			    "altNo": "1234",
			    "description": "iphone-6",
			    "costPrice": "12.99",
			    "markup": "25",
			    "retailPrice": "15.00",
			    "quantity": "10",
			    "minProductQuantity": "5",
			    "returnRule": "NextWeek",
			    "image": "image",
			    "createdDate": "1000-01-01 00:00:00",
			    "stock":10
		},
		{
			 "productId": 7,
			    "productNo": "123456789012",
			    "categoryId": 1,
			    "vendorId": 11,
			    "brandId": 1,
			    "altNo": "1234",
			    "description": "samsung",
			    "costPrice": "12.99",
			    "markup": "25",
			    "retailPrice": "15.00",
			    "quantity": "10",
			    "minProductQuantity": "5",
			    "returnRule": "NextWeek",
			    "image": "image",
			    "createdDate": "1000-01-01 00:00:00",
			    "stock":10
		},
		{
			 "productId": 8,
			    "productNo": "123456789012",
			    "categoryId": 1,
			    "vendorId": 11,
			    "brandId": 1,
			    "altNo": "1234",
			    "description": "htc",
			    "costPrice": "12.99",
			    "markup": "25",
			    "retailPrice": "15.00",
			    "quantity": "10",
			    "minProductQuantity": "5",
			    "returnRule": "NextWeek",
			    "image": "image",
			    "createdDate": "1000-01-01 00:00:00",
			    "stock":10
		},
		{
			 "productId": 9,
			    "productNo": "123456789012",
			    "categoryId": 1,
			    "vendorId": 11,
			    "brandId": 1,
			    "altNo": "1234",
			    "description": "google",
			    "costPrice": "12.99",
			    "markup": "25",
			    "retailPrice": "15.00",
			    "quantity": "10",
			    "minProductQuantity": "5",
			    "returnRule": "NextWeek",
			    "image": "image",
			    "createdDate": "1000-01-01 00:00:00",
			    "stock":10
		},
		{
			 "productId": 10,
			    "productNo": "123456789012",
			    "categoryId": 1,
			    "vendorId": 11,
			    "brandId": 1,
			    "altNo": "1234",
			    "description": "motorolla",
			    "costPrice": "12.99",
			    "markup": "25",
			    "retailPrice": "15.00",
			    "quantity": "10",
			    "minProductQuantity": "5",
			    "returnRule": "NextWeek",
			    "image": "image",
			    "createdDate": "1000-01-01 00:00:00",
			    "stock":10
		},
		{
			 "productId": 11,
			    "productNo": "123456789012",
			    "categoryId": 1,
			    "vendorId": 11,
			    "brandId": 1,
			    "altNo": "1234",
			    "description": "lg",
			    "costPrice": "12.99",
			    "markup": "25",
			    "retailPrice": "15.00",
			    "quantity": "10",
			    "minProductQuantity": "5",
			    "returnRule": "NextWeek",
			    "image": "image",
			    "createdDate": "1000-01-01 00:00:00",
			    "stock":10
		}
			
		];
		$scope.productNames = [];
		
		$scope.addRow = function()
		{
			
			$rootScope.testData.push({"itemNo":Math.round((Math.random() * 10) * 10),
				"item":"check",
				"quantity":89,
				"retail":"test",
				"discount":20,
				"total":20.00,
				"stock":5});
			
			$scope.loadCheckOutData();
		};
		$scope.removeRow = function(itemNo){	
			
			GlobalVariable.itemNoToDelete = itemNo;
			modalService.showModal('', {
				isCancel : true
			}, "Are you Sure Want to Delete ? ", $scope.callBackAction);
		/*var index = -1;		
		var comArr = eval( $rootScope.testData );
		for( var i = 0; i < comArr.length; i++ ) {
			if( comArr[i].itemNo === itemNo ) {
				index = i;
				break;
			}
		}
		if( index === -1 ) {
			alert( "Something gone wrong" );
		}
		$rootScope.testData.splice( index, 1 );*/		
	};
	$scope.callBackAction = function(isOKClicked)
	{
		
		if(isOKClicked)
		{
			var index = -1;		
			var comArr = eval( $rootScope.testData );
			for( var i = 0; i < comArr.length; i++ ) {
				if( comArr[i].itemNo === GlobalVariable.itemNoToDelete ) {
					index = i;
					break;
				}
			}
			if( index === -1 ) {
				alert( "Something gone wrong" );
			}
			$rootScope.testData.splice( index, 1 );
		}	
	}
	$scope.changeQuantity= function()
	{
		var searchTxt = $scope.searchValue.toString();
		if(searchTxt !== '' && searchTxt !== undefined)
		{	
		if (searchTxt.match(/[a-z]/i)) {
				    console.log("contains only charcters");
				    $scope.discount =0;
				    for(var i=0;i<GlobalVariable.getProducts.length;i++)
				    {
				    	if(searchTxt === GlobalVariable.getProducts[i].description)
				    	{
				    		$rootScope.testData.push({"itemNo":GlobalVariable.getProducts[i].productId,
								"item":GlobalVariable.getProducts[i].description,
								"quantity":GlobalVariable.getProducts[i].quantity,
								"retail":GlobalVariable.getProducts[i].retailPrice,
								"discount":parseFloat($scope.discount),
								"total":(parseFloat(GlobalVariable.getProducts[i].retailPrice)-(parseFloat($scope.discount)))*parseFloat(GlobalVariable.getProducts[i].quantity),
								"stock":GlobalVariable.getProducts[i].stock});
				    	}	
				    }	
				    
				}
				else if(searchTxt.length > 5)
				{
					console.log(""+$scope.searchValue);
					$scope.discount =0;
					 for(var i=0;i<GlobalVariable.getProducts.length;i++)
					    {
					    	if(searchTxt === GlobalVariable.getProducts[i].prodcutNo)
					    	{
					    		$rootScope.testData.push({"itemNo":GlobalVariable.getProducts[i].productId,
									"item":GlobalVariable.getProducts[i].description,
									"quantity":GlobalVariable.getProducts[i].quantity,
									"retail":GlobalVariable.getProducts[i].retailPrice,
									"discount":parseFloat($scope.discount),
									"total":(parseFloat(GlobalVariable.getProducts[i].retailPrice)-(parseFloat($scope.discount)))*parseFloat(GlobalVariable.getProducts[i].quantity),
									"stock":GlobalVariable.getProducts[i].stock});
					    	}	
					    }
				}	
				else
				{
					if(searchTxt.indexOf(".") >=0 )
					{
						$scope.quantity = $rootScope.testData[$rootScope.testData.length-1].quantity;
						if(parseFloat($scope.searchValue) > parseFloat(parseFloat($rootScope.testData[$rootScope.testData.length-1].retail)))
						{
							$scope.discount =0;
							$rootScope.testData[$rootScope.testData.length-1].retail = $scope.searchValue;
							
						}
						else
						{
							$scope.discount = parseFloat($rootScope.testData[$rootScope.testData.length-1].retail)-parseFloat($scope.searchValue);
						}	
						
						$scope.total = (parseFloat($rootScope.testData[$rootScope.testData.length-1].retail)-$scope.discount)*parseFloat($scope.quantity);
					}
					else
					{
						$scope.quantity = $scope.searchValue;
						if(parseFloat($rootScope.testData[$rootScope.testData.length-1].discount) == 'NaN')
						{
							$scope.discount=0
						}	
						else
						{
							$scope.discount = parseFloat($rootScope.testData[$rootScope.testData.length-1].discount);
						}	
						$scope.total = (parseFloat($rootScope.testData[$rootScope.testData.length-1].retail)-parseFloat($scope.discount))*parseFloat($scope.quantity);
					}	
					
					$rootScope.testData.push({"itemNo":$rootScope.testData[$rootScope.testData.length-1].itemNo,
						"item":$rootScope.testData[$rootScope.testData.length-1].item,
						"quantity":$scope.quantity,
						"retail":$rootScope.testData[$rootScope.testData.length-1].retail,
						"discount":$scope.discount,
						"total":$scope.total,
						"stock":$rootScope.testData[$rootScope.testData.length-1].stock});
					//for(var i=0;i<$rootScope.testData.length-1;i++)
						//{
						$scope.removeRowOnSearch($rootScope.testData[$rootScope.testData.length-2].itemNo);
						//}
				}	
				
		$scope.loadCheckOutData();
					$scope.searchValue = '';
		}
	};
	$scope.removeRowOnSearch = function(itemNo){				
		var index = -1;		
		var comArr = eval( $rootScope.testData );
		for( var i = 0; i < comArr.length; i++ ) {
			if(itemNo.toString().indexOf(".")>=0)
			{
				if( comArr[i].itemNo === itemNo ) {
					index = i;
					break;
				}
			}
			else
			{
				if( comArr[i].itemNo === itemNo ) {
					index = i;
					break;
				}
			}	
			
		}
		if( index === -1 ) {
			alert( "Something gone wrong" );
		}
		$rootScope.testData.splice( index, 1 );		
	};
		$scope.openCashPopup = function()
		{
			var _tmPath = 'app/sell/paymentPopup.html';
			var _ctrlPath = 'paymentPopupController';
			DialogFactory.show(_tmPath, _ctrlPath, callbackPayment);
		};
		$scope.createCustomer = function()
		{
			var _tmPath = 'app/AddCustomer/addcustomer.html';
			var _ctrlPath = 'addCustomerController';
			DialogFactory.show(_tmPath, _ctrlPath, callbackPayment);
		};
		function callbackPayment()
		{
			
		}
		$scope.test = function()
		{
			console.log(""+$scope.searchValue);
		};
		$scope.loadCheckOutData = function()
		{
			$scope.totalQuantity=0;
			$scope.subTotal = 0;
			for(var i=0;i<$rootScope.testData.length;i++)
			{
				$scope.totalQuantity = parseFloat( $scope.totalQuantity) + parseFloat($rootScope.testData[i].quantity);
				$scope.subTotal = parseFloat($scope.subTotal) + parseFloat($rootScope.testData[i].total);
			}
			if($scope.totalDisc == undefined)
				$scope.totalDisc = 0;
			
			$scope.productTotalWithoutTax = ( $scope.subTotal) - parseFloat($scope.totalDisc);
			
			if($scope.totalTax == undefined)
				$scope.totalTax = 0;
			
			$scope.productTotal = Number(parseFloat($scope.productTotalWithoutTax)+(((parseFloat($scope.productTotalWithoutTax) * parseFloat($scope.totalTax))) / 100 )).toFixed(2);
			 
			$scope.totalPayment = $scope.productTotal;
			GlobalVariable.checkOuttotal = $scope.totalPayment;
		}
		function render()
		{
			$scope.currentPageIndexArr = 0;
			$scope.totalTax=GlobalVariable.totalTaxSetup;
			for(var i=0;i<GlobalVariable.getProducts.length;i++)
			{
				$scope.productNames.push(GlobalVariable.getProducts[i].description);
			}
			$scope.loadCheckOutData();
		}
		render();
	}
		
})();