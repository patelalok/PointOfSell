(function() {
	'use strict';

	angular.module('sampleApp').controller('mainProductController', mainProductController);

	mainProductController.$inject = [ '$scope', '$rootScope', 'device.utility','GlobalVariable','$state','DialogFactory','$timeout','RestrictedCharacter.Types'];

	function mainProductController($scope, $rootScope, device ,GlobalVariable,$state,DialogFactory,$timeout,restrictCharacter) {
		
		$scope.device = device;
		$scope.GlobalVariable = GlobalVariable;

		$scope.restrictCharacter=restrictCharacter;
		GlobalVariable.isLoginPage = false;
		$scope.selectedIndex = 0;
		$scope.isAsc = false;
		$scope.enabled = true;
		
		
		$scope.navigateToAddProduct = function(page)
		{
			if(page == 'product')
			{
				var test = new Object();
				test.name = 'Prodcut';
				test.last = 'No';
				$state.go(page,{obj:test});
			}
			else
			{	
			$state.go(page);
			}
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
			
			$scope.brandOptions = GlobalVariable.getBrands;
			$scope.categoryOptions = GlobalVariable.getCategory;
			
				
		}
		render();
		
	}
		
})();