(function() {
	'use strict';

	angular.module('sampleApp').controller('BodyController', Body);

	Body.$inject = [ '$scope', '$rootScope', 'device.utility','GlobalVariable','$state','dataService','getProductDetails'];

	function Body($scope, $rootScope, device,GlobalVariable,$state,dataService,getProductDetails) {
		
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
			menuName:"Sales History",
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
			path:"productmain"
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
		$scope.navigateToBrand = function()
		{
			$state.go('brand');
		};
		function render()
		{
			getProductDetails.getProductValues();
		}
		$scope.logOut = function()
		{
			$state.go('login');
		};
		render();
	}
})();