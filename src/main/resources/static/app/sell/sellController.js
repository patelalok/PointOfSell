(function() {
	'use strict';

	angular.module('sampleApp').controller('sellController', sellController);

	sellController.$inject = [ '$scope', '$rootScope', 'device.utility',
		'GlobalVariable', 'DialogFactory', 'modalService',
		'RestrictedCharacter.Types', 'dataService', '$state', '$timeout' ];

	function sellController($scope, $rootScope, device, GlobalVariable,
							DialogFactory, modalService, restrictCharacter, dataService,
							$state, $timeout) {

		$scope.device = device;
		$scope.GlobalVariable = GlobalVariable;
		$scope.restrictCharacter = restrictCharacter;
		GlobalVariable.isLoginPage = false;
		$scope.balanceRemaining = 0;
		GlobalVariable.customerFound = false;
		$scope.showEditableFields = false;
		//GlobalVariable.addProductClicked= false;

		var i = 0;
		$scope.pageSize = 10;

		$rootScope.testData = [];
		$scope.productNames = [];
		$scope.firstNames = [];

		$scope.addRow = function() {

			/*
			 * $rootScope.testData.push({"itemNo":Math.round((Math.random() *
			 * 10) * 10), "item":"check", "quantity":89, "retail":"test",
			 * "discount":20, "total":20.00, "stock":5, "costPrice":"12.90"});
			 *
			 * $scope.loadCheckOutData();
			 */
			GlobalVariable.addProductClicked= true;
			GlobalVariable.editProduct = false;
			$state.go('product');
		};
		$scope.removeRow = function(row) {

			GlobalVariable.editValues = row;
			GlobalVariable.itemNoToDelete = row.itemId;
			modalService.showModal('', {
				isCancel : true
			}, "Are you Sure Want to Delete ? ", $scope.callBackAction);
			/*
			 * var index = -1; var comArr = eval( $rootScope.testData ); for(
			 * var i = 0; i < comArr.length; i++ ) { if( comArr[i].itemNo ===
			 * itemNo ) { index = i; break; } } if( index === -1 ) { alert(
			 * "Something gone wrong" ); } $rootScope.testData.splice( index, 1 );
			 */
		};
		$scope.callBackAction = function(isOKClicked) {

			if (isOKClicked) {
				var index = -1;
				var comArr = eval($rootScope.testData);
				for (var i = 0; i < comArr.length; i++) {
					if (comArr[i].itemId === GlobalVariable.itemNoToDelete) {

						comArr[i].quantity = 20;
						index = i;
						break;
					}
				}
				if (index === -1) {
					alert("Something gone wrong");
				}
				$rootScope.testData.splice(index, 1);
				/*
				 * $rootScope.testData.splice(index,0,{"itemNo":GlobalVariable.editValues.itemNo,
				 * "item":GlobalVariable.editValues.item, "quantity":12,
				 * "retail":GlobalVariable.editValues.retail,
				 * "discount":GlobalVariable.editValues.discount, "total":
				 * GlobalVariable.editValues.total,
				 * "stock":GlobalVariable.editValues.stock,
				 * "costPrice":GlobalVariable.editValues.costPrice});
				 */
				$scope.loadCheckOutData();
			}
		}
		$scope.editItemNo = function(row) {
			console.log(row);
		};
		$scope.changeQuantity = function() {
			var searchTxt = $scope.searchValue.toString();
			if (searchTxt !== '' && searchTxt !== undefined
				&& searchTxt.indexOf(".") !== 0) {
				if (searchTxt.match(/[a-z]/i)) {
					console.log("contains only charcters");
					$scope.discount = 0;
					for (var i = 0; i < GlobalVariable.getProducts.length; i++) {
						if (searchTxt === GlobalVariable.getProducts[i].description) {
							if(GlobalVariable.getProducts[i].addTax == true)
							{
								var totalWithOutTax = Number((parseFloat(GlobalVariable.getProducts[i].retailPrice) - (parseFloat($scope.discount))) * parseFloat(GlobalVariable.getProducts[i].quantity))
									.toFixed(2);
								totalWithOutTax = parseFloat(totalWithOutTax);
								var totalWithTax = totalWithOutTax + (($scope.totalDefaultTax /100) * totalWithOutTax);
							}
							else
							{
								var totalWithOutTax = Number((parseFloat(GlobalVariable.getProducts[i].retailPrice) - (parseFloat($scope.discount))) * parseFloat(GlobalVariable.getProducts[i].quantity))
									.toFixed(2);
								totalWithOutTax = parseFloat(totalWithOutTax);
								var totalWithTax = totalWithOutTax;
							}
							$rootScope.testData
								.push({
									"itemId":GlobalVariable.getProducts[i].productId,
									"itemNo" : GlobalVariable.getProducts[i].productNo,
									"item" : GlobalVariable.getProducts[i].description,
									"quantity" : GlobalVariable.getProducts[i].quantity,
									"retail" : GlobalVariable.getProducts[i].retailPrice,
									"discount" : (parseFloat($scope.discount))
										.toFixed(2),
									"total" : totalWithOutTax,
									"stock" : GlobalVariable.getProducts[i].stock,
									"costPrice" : GlobalVariable.getProducts[i].costPrice,
									"categoryName":GlobalVariable.getProducts[i].categoryName,
									"totalWithTax":totalWithTax,
									"totalTax":parseFloat(totalWithTax)-parseFloat(totalWithOutTax)
								});
							if(GlobalVariable.getProducts[i].categoryName == 'Plans')
							{
								for (var i = 0; i < GlobalVariable.getProducts.length; i++) {
									if(GlobalVariable.getProducts[i].productNo = "1000000027")
									{
										$rootScope.testData
											.push({
												"itemId":GlobalVariable.getProducts[i].productId,
												"itemNo" : GlobalVariable.getProducts[i].productNo,
												"item" : GlobalVariable.getProducts[i].description,
												"quantity" : GlobalVariable.getProducts[i].quantity,
												"retail" : GlobalVariable.getProducts[i].retailPrice,
												"discount" : (parseFloat($scope.discount))
													.toFixed(2),
												"total" : totalWithOutTax,
												"stock" : GlobalVariable.getProducts[i].stock,
												"costPrice" : GlobalVariable.getProducts[i].costPrice,
												"categoryName":GlobalVariable.getProducts[i].categoryName,
												"totalWithTax":totalWithTax,
												"totalTax":parseFloat(totalWithTax)-parseFloat(totalWithOutTax)
											});
										break;
									}
								}
							}
							break;
						}
					}

				} else if (searchTxt.length > 5) {
					console.log("" + $scope.searchValue);
					$scope.discount = 0;
					for (var i = 0; i < GlobalVariable.getProducts.length; i++) {
						if (searchTxt === GlobalVariable.getProducts[i].productNo) {
							if(GlobalVariable.getProducts[i].addTax == true)
							{
								var totalWithOutTax = Number((parseFloat(GlobalVariable.getProducts[i].retailPrice) - (parseFloat($scope.discount))) * parseFloat(GlobalVariable.getProducts[i].quantity))
									.toFixed(2);
								totalWithOutTax = parseFloat(totalWithOutTax);
								var totalWithTax = totalWithOutTax + (($scope.totalDefaultTax /100) * totalWithOutTax);
							}
							else
							{
								var totalWithOutTax =  Number((parseFloat(GlobalVariable.getProducts[i].retailPrice) - (parseFloat($scope.discount))) * parseFloat(GlobalVariable.getProducts[i].quantity))
									.toFixed(2);
								totalWithOutTax = parseFloat(totalWithOutTax);
								var totalWithTax = totalWithOutTax;
							}

							$rootScope.testData
								.push({
									"itemId":GlobalVariable.getProducts[i].productId,
									"itemNo" : GlobalVariable.getProducts[i].productNo,
									"item" : GlobalVariable.getProducts[i].description,
									"quantity" : GlobalVariable.getProducts[i].quantity,
									"retail" : GlobalVariable.getProducts[i].retailPrice,
									"discount" : (parseFloat($scope.discount))
										.toFixed(2),
									"total" : totalWithOutTax,
									"stock" : GlobalVariable.getProducts[i].stock,
									"costPrice" : GlobalVariable.getProducts[i].costPrice,
									"categoryName":GlobalVariable.getProducts[i].categoryName,
									"totalWithTax":totalWithTax,
									"totalTax":parseFloat(totalWithTax)-parseFloat(totalWithOutTax)
								});
						}
					}
				} else {
					if (searchTxt.indexOf(".") > 0) {
						$scope.quantity = $rootScope.testData[$rootScope.testData.length - 1].quantity;
						if (parseFloat($scope.searchValue) > parseFloat(parseFloat($rootScope.testData[$rootScope.testData.length - 1].retail))) {
							$scope.discount = 0;
							$rootScope.testData[$rootScope.testData.length - 1].retail = $scope.searchValue;

						} else {
							$scope.discount = (parseFloat($scope.searchValue))
								.toFixed(2);
						}
						if ($scope.discount == 0) {
							$scope.total = ((parseFloat($rootScope.testData[$rootScope.testData.length - 1].retail) - $scope.discount) * parseFloat($scope.quantity))
								.toFixed(2);
						}
						else {
							$scope.total = (($scope.discount) * parseFloat($scope.quantity))
								.toFixed(2);
						}
						if($rootScope.testData[$rootScope.testData.length - 1].total == $rootScope.testData[$rootScope.testData.length - 1].totalWithTax)
						{
							$scope.tWTax = parseFloat($scope.total);
						}
						else
						{
							$scope.tWTax = parseFloat($scope.total)+((parseFloat($scope.total)*8)/100);
						}
					} else {
						$scope.quantity = $scope.searchValue;
						if (parseFloat($rootScope.testData[$rootScope.testData.length - 1].discount) == 'NaN') {
							$scope.discount = 0
						} else {
							$scope.discount = parseFloat($rootScope.testData[$rootScope.testData.length - 1].discount);
						}
						if ($scope.discount !== 0) {
							$scope.total = (parseFloat($scope.quantity) * parseFloat($scope.discount))
								.toFixed(2);

						} else {
							$scope.total = ((parseFloat($rootScope.testData[$rootScope.testData.length - 1].retail) - parseFloat($scope.discount)) * parseFloat($scope.quantity))
								.toFixed(2);

						}

						if($rootScope.testData[$rootScope.testData.length - 1].total == $rootScope.testData[$rootScope.testData.length - 1].totalWithTax)
						{
							$scope.tWTax = parseFloat($scope.total);
						}
						else
						{
							$scope.tWTax = parseFloat($scope.total)+((parseFloat($scope.total)*8)/100);
						}
					}

					$rootScope.testData
						.push({
							"itemId":$rootScope.testData[$rootScope.testData.length - 1].productId,
							"itemNo" : $rootScope.testData[$rootScope.testData.length - 1].itemNo,
							"item" : $rootScope.testData[$rootScope.testData.length - 1].item,
							"quantity" : $scope.quantity,
							"retail" : $rootScope.testData[$rootScope.testData.length - 1].retail,
							"discount" : $scope.discount,
							"total" : parseFloat($scope.total),
							"stock" : $rootScope.testData[$rootScope.testData.length - 1].stock,
							"costPrice" : $rootScope.testData[$rootScope.testData.length - 1].costPrice,
							"categoryName":$rootScope.testData[$rootScope.testData.length - 1].categoryName,
							"totalWithTax":parseFloat($scope.tWTax),
							"totalTax":parseFloat($rootScope.testData[$rootScope.testData.length - 1].total)-parseFloat($rootScope.testData[$rootScope.testData.length - 1].totalWithTax)
						});
					// for(var i=0;i<$rootScope.testData.length-1;i++)
					// {
					$scope
						.removeRowOnSearch($rootScope.testData[$rootScope.testData.length - 2].itemNo);
					// }
				}

				$scope.loadCheckOutData();
				$scope.searchValue = '';
			}
			angular.element("#dataTable").scrollTop(angular.element("#table tr:last").position().top);
		};
		$scope.removeRowOnSearch = function(itemNo) {
			var index = -1;
			var comArr = eval($rootScope.testData);
			for (var i = 0; i < comArr.length; i++) {
				if (itemNo.toString().indexOf(".") >= 0) {
					if (comArr[i].itemNo === itemNo) {
						index = i;
						break;
					}
				} else {
					if (comArr[i].itemNo === itemNo) {
						index = i;
						break;
					}
				}

			}
			if (index === -1) {
				alert("Something gone wrong");
			}
			$rootScope.testData.splice(index, 1);
		};
		$scope.openCashPopup = function() {

			var _tmPath = 'app/sell/paymentPopup.html';
			var _ctrlPath = 'paymentPopupController';
			DialogFactory.show(_tmPath, _ctrlPath, callbackPayment);
		};
		$scope.createCustomer = function() {
			var _tmPath = 'app/AddCustomer/addcustomer.html';
			var _ctrlPath = 'addCustomerController';
			DialogFactory.show(_tmPath, _ctrlPath, callbackCust);
		};
		function callbackCust() {
			$timeout(function() {
				$scope.closeBootstrapAlert();
			}, 9000);
		}
		$scope.closeBootstrapAlert = function() {
			GlobalVariable.successCustAlert = false;
			GlobalVariable.addedCustSuccessfull = false;
			GlobalVariable.editCustSuccess = false;
		};
		function callbackPayment() {
			$rootScope.totalQuantity = 0;
			$rootScope.subTotal = 0;
			$rootScope.productTotal = 0;
			//$rootScope.totalProductPriceAfterTax = 0;
			$scope.regPhone = '';
			$scope.customerNameOnSearch = '';
			$scope.balanceRemaining = '';
			// $rootScope.testData = [];
			$scope.loadCheckOutData();
			// GlobalVariable.customerFound = false;
			$scope.balanceRemaining = 0;
			//GlobalVariable.taxTotal=0;

		}
		$scope.test = function() {
			console.log("" + $scope.searchValue);
		};
		$scope.loadCheckOutData = function() {
			$rootScope.totalQuantity = 0;
			$rootScope.subTotal = 0;
			$rootScope.totalProductPriceAfterTax = 0;
			GlobalVariable.taxTotal=0;
			for (var i = 0; i < $rootScope.testData.length; i++) {
				$rootScope.totalQuantity = parseFloat($rootScope.totalQuantity)
					+ parseFloat($rootScope.testData[i].quantity);
				$rootScope.subTotal = parseFloat($rootScope.subTotal)
					+ parseFloat($rootScope.testData[i].total);
				if($scope.selectTax=='default')
				{
					$rootScope.totalProductPriceAfterTax = parseFloat($rootScope.totalProductPriceAfterTax)+
						parseFloat($rootScope.testData[i].totalWithTax);
					GlobalVariable.taxTotal = parseFloat(GlobalVariable.taxTotal)+parseFloat($rootScope.testData[i].totalTax);
				}
				else
				{
					$rootScope.totalProductPriceAfterTax = parseFloat($rootScope.totalProductPriceAfterTax)+
						parseFloat($rootScope.testData[i].total);
					GlobalVariable.taxTotal = 0;
				}



			}
			$rootScope.totalQuantity = (parseFloat($rootScope.totalQuantity))
				.toFixed(2);
			$rootScope.subTotal = Number(parseFloat($rootScope.subTotal)).toFixed(2);
			GlobalVariable.quantityTotal = $rootScope.totalQuantity;
			GlobalVariable.totalSub = $rootScope.subTotal;
			if ($scope.totalDisc == undefined)
				$scope.totalDisc = 0;

			GlobalVariable.discountTotal = $scope.totalDisc;
			
			if ($scope.totalDisc == "")
				$rootScope.totalProductPriceAfterTax = Number(
					parseFloat($rootScope.totalProductPriceAfterTax)).toFixed(2);
			else
				$rootScope.totalProductPriceAfterTax = Number(
					parseFloat($rootScope.totalProductPriceAfterTax)
					- parseFloat($scope.totalDisc)).toFixed(2);

			// if ($scope.productTotalWithoutTax == 'NaN') {
			// 	$scope.productTotalWithoutTax = 0;
			// }
			GlobalVariable.selectedTaxDrp = $scope.selectTax;

			// if ($scope.selectTax == undefined)
			// 	$scope.totalTax = 0;
			// else if ($scope.selectTax == 'default')
			// 	$scope.totalTax = parseFloat($scope.totalDefaultTax);
			// else if ($scope.selectTax == 'noTax')
			// 	$scope.totalTax = 0;

			// GlobalVariable.taxTotal = parseFloat($scope.productTotalWithoutTax)
			// 	* (parseFloat($scope.totalTax) / 100);
			$rootScope.productTotal = Number(parseFloat($rootScope.totalProductPriceAfterTax)).toFixed(2);
				// Number(
				// parseFloat($scope.productTotalWithoutTax)
				// + (((parseFloat($scope.productTotalWithoutTax) * parseFloat($scope.totalTax))) / 100))
				// .toFixed(2);

			if ($scope.balanceRemaining > 0) {
				$rootScope.productTotal = parseFloat($rootScope.productTotal)
					+ parseFloat($scope.balanceRemaining);
			}
			$rootScope.totalPayment = parseFloat($rootScope.productTotal)
				.toFixed(2);
			GlobalVariable.checkOuttotal = parseFloat($rootScope.totalPayment)
				.toFixed(2);
			GlobalVariable.onAddProduct = $rootScope.testData;
		}
		$scope.editRow = function(row) {
			GlobalVariable.editQuanDtls = row;
			var _tmPath = 'app/sell/editQuant.html';
			var _ctrlPath = 'EditQuantityController';
			DialogFactory.show(_tmPath, _ctrlPath, $scope.callBackEditQuan);
		};
		$scope.callBackEditQuan = function() {
			var index = -1;
			var comArr = eval($rootScope.testData);
			for (var i = 0; i < comArr.length; i++) {
				if (comArr[i].itemNo === GlobalVariable.editQuanDtls.itemNo) {

					index = i;
					break;
				}
			}
			if (index === -1) {
				alert("Something gone wrong");
			}
			$rootScope.testData.splice(index, 1);
			if (parseFloat(GlobalVariable.editQuanDtls.discount) !== 0) {
				var editSub = (parseFloat(GlobalVariable.editQuanDtls.quantity) * parseFloat(GlobalVariable.editQuanDtls.discount))
					.toFixed(2);

			} else {
				var editSub = (parseFloat(GlobalVariable.editQuanDtls.quantity) * parseFloat(GlobalVariable.editQuanDtls.retail))
					.toFixed(2)
			}
			if(GlobalVariable.editQuanDtls.total == GlobalVariable.editQuanDtls.totalWithTax)
			{
				var editSubTax = parseFloat(editSub);
			}
			else
			{
				var editSubTax = parseFloat(editSub) + (($scope.totalDefaultTax /100) * parseFloat(editSub));
			}
			$rootScope.testData.splice(index, 0, {
				"itemId":GlobalVariable.editQuanDtls.itemId,
				"itemNo" : GlobalVariable.editQuanDtls.itemNo,
				"item" : GlobalVariable.editQuanDtls.item,
				"quantity" : GlobalVariable.editQuanDtls.quantity,
				"retail" : GlobalVariable.editQuanDtls.retail,
				"discount" : GlobalVariable.editQuanDtls.discount,
				"total" : editSub,
				"stock" : GlobalVariable.editQuanDtls.stock,
				"costPrice" : GlobalVariable.editQuanDtls.costPrice,
				"categoryName" : GlobalVariable.editQuanDtls.categoryName,
				"totalWithTax":editSubTax,
				"totalTax":parseFloat(editSubTax)-parseFloat(editSub)
			});
			$scope.loadCheckOutData();
		};
		$scope.searchCustomer = function() {
			// $scope.customerPhone;
			for (var i = 0; i < GlobalVariable.getCustomerDtls.length; i++) {
				if ($scope.regPhone == GlobalVariable.getCustomerDtls[i].phoneNo) {
					$scope.customerNameOnSearch = GlobalVariable.getCustomerDtls[i].firstName;
					$rootScope.customerPhone = $scope.regPhone;
					var url = ' http://localhost:8080/getCustomerBalance?phoneNo='
						+ $scope.regPhone;
					dataService.Get(url, onBalanceSuccess, onBalanceError,
						'application/json', 'application/json');
					GlobalVariable.customerFound = true;
					GlobalVariable.custTypeCd = GlobalVariable.getCustomerDtls[i].customerType;
					if (GlobalVariable.custTypeCd == 'Business') {
						$scope.selectTax = 'noTax';
						GlobalVariable.selectedTaxDrp = 'noTax';
					}
					else if (GlobalVariable.custTypeCd == 'Retail') {
						$scope.selectTax = 'default';
						GlobalVariable.selectedTaxDrp = 'default';
					}

					break;
				} else {
					$rootScope.customerNameOnSearch = 'No customer found';
					$rootScope.customerPhone = '';
				}

			}
			$scope.loadCheckOutData();

		};
		function onBalanceSuccess(response) {
			if (response !== null && response !== ''
				&& parseFloat(response) !== 0) {
				$scope.balanceRemaining = parseFloat(response);
				// GlobalVariable.remainingBalanceAmount =
				// $scope.balanceRemaining;
				$rootScope.productTotal = $scope.balanceRemaining;
				GlobalVariable.custBalance = $scope.balanceRemaining;
				$rootScope.totalPayment = $scope.balanceRemaining;
				GlobalVariable.checkOuttotal = $rootScope.totalPayment;
			} else {
				$scope.balanceRemaining = parseFloat(response);
				GlobalVariable.custBalance = $scope.balanceRemaining;
			}

		}
		function onBalanceError(response) {

		}

		$scope.searchCustomerByFirst = function() {
			for (var i = 0; i < GlobalVariable.getCustomerDtls.length; i++) {
				if ($scope.customerNameOnSearch == GlobalVariable.getCustomerDtls[i].firstName) {
					$scope.customerNameOnSearch = GlobalVariable.getCustomerDtls[i].firstName;
					$rootScope.customerPhone = GlobalVariable.getCustomerDtls[i].phoneNo;
					$scope.regPhone = GlobalVariable.getCustomerDtls[i].phoneNo;
					GlobalVariable.customerFound = true;
					break;
				}
				else
				{
					$rootScope.customerPhone = '';
				}
				/*
				 * else { $rootScope.customerName = 'No customer found';
				 * $rootScope.customerPhone = $scope.regPhone; }
				 */

			}
		}
		function render() {
			$scope.currentPageIndexArr = 0;
			$rootScope.customerNameOnSearch = '';
			if(GlobalVariable.addProductClicked)
			{
				$rootScope.testData = GlobalVariable.onAddProduct;
			}	
			getTaxDetails();
			for (var i = 0; i < GlobalVariable.getProducts.length; i++) {
				$scope.productNames
					.push(GlobalVariable.getProducts[i].description);
			}
			for (var i = 0; i < GlobalVariable.getCustomerDtls.length; i++) {
				$scope.firstNames
					.push(GlobalVariable.getCustomerDtls[i].firstName);
			}

		}
		$scope.$watch('GlobalVariable.getCustomerDtls',function(newValue)
		{
			for (var i = 0; i < GlobalVariable.getCustomerDtls.length; i++) {
				$scope.firstNames
					.push(GlobalVariable.getCustomerDtls[i].firstName);
			}
		});
		function getTaxDetails() {
			var url = 'http://localhost:8080/getPageSetUpDetails';
			dataService.Get(url, onGetTaxSuccess, onGetTaxError,
				'application/json', 'application/json');
		}
		function onGetTaxSuccess(response) {
			$scope.totalDefaultTax = response[0].tax;

			$scope.selectTax = "default";
			$scope.loadCheckOutData();
		}
		$scope.clearValue = function(value)
		{
			if(value == '' || value == undefined)
			{
				$scope.regPhone = '';
				$scope.customerNameOnSearch = '';
				$rootScope.customerPhone = '';
				GlobalVariable.customerFound=false;
				$scope.selectTax = "default";
				GlobalVariable.selectedTaxDrp = "default";
			}
		};
		$scope.clearValuePhone = function()
		{
			console.log("$scope.regPhone= "+$scope.regPhone);
			if($scope.regPhone == '')
			{
				$scope.customerNameOnSearch = '';
				$rootScope.customerPhone = '';
				GlobalVariable.customerFound=false;
				$scope.selectTax = "default";
				GlobalVariable.selectedTaxDrp = "default";
			}
		}
		function onGetTaxError(response) {

		}

		render();
	}

})();
