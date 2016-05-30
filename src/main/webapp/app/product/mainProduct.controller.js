(function() {
	'use strict';

	angular.module('sampleApp').controller('mainProductController', mainProductController);

	mainProductController.$inject = [ '$scope', '$rootScope', 'device.utility','GlobalVariable','$state'];

	function mainProductController($scope, $rootScope, device ,GlobalVariable,$state) {
		
		$scope.device = device;
		$scope.GlobalVariable = GlobalVariable;
		GlobalVariable.isLoginPage = false;
		$scope.selectedIndex = 0;
		$scope.isAsc = false;
		$scope.enabled = true;
		
$scope.productData = [{
			
			"product":"DressShirt",
			"created":"25 April 2016",
			"tags":"Mens winter",
			"brand":"Summerly",
			"supplier":"FD Ltd",
			"variants":"6",
			"price":90.00,
			"count":46
		},
		{
			"product":"mircrophone",
			"created":"25 May 2016",
			"tags":"Necklacer",
			"brand":"In shade",
			"supplier":"Ramons",
			"variants":"4",
			"price":190.00,
			"count":26
		}
			
		];
		
		$scope.navigateToAddProduct = function(page)
		{
			$state.go(page);
		};
		
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
		function render()
		{
			$scope.currentPageIndexArr = 0;
			$scope.curPageOnTotalLen = 0;
			$scope.totalLength = 0;
		}
		render();
		
	}
		
})();