(function() {
	'use strict';

	angular.module('sampleApp').controller('BodyController', Body);

	Body.$inject = [ '$scope', '$rootScope', 'device.utility','GlobalVariable','$state'];

	function Body($scope, $rootScope, device,GlobalVariable,$state) {
		
		var vm = this;
		vm.device = device;
		$scope.GlobalVariable = GlobalVariable;
		$scope.device = device;
		GlobalVariable.isLoginPage = false;
		
		$scope.menuOptions = [{
			
			menuCode:1,
			menuName:"Home",
			imgPath:"assets/images/home.png",
			path:"home"
		},
		{
			menuCode:2,
			menuName:"Sell",
			imgPath:"assets/images/sell.png",
			path:"sell"
		},
		{
			menuCode:3,
			menuName:"Sales Ledger",
			imgPath:"assets/images/ledger.png",
			path:"ledger"
		},
		{
			menuCode:4,
			menuName:"Report",
			imgPath:"assets/images/report.png",
			path:"report"
		},
		{
			menuCode:5,
			menuName:"Product",
			imgPath:"assets/images/final-logo.png",
			path:"product"
		},
		{
			menuCode:6,
			menuName:"Customer",
			imgPath:"assets/images/addCustomer.png",
			path:"customer"
		},
		{
			menuCode:7,
			menuName:"Setup",
			imgPath:"assets/images/setup.png",
			path:"setup"
		}
			
		];
		$scope.openNav = function() {
			$rootScope.displaySideBar = !$rootScope.displaySideBar;
		};
		
		$scope.navigate = function(code,page)
		{
			$scope.selectedMenuCd = code;
			$state.go(page);
		};
	}
})();