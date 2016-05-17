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
		
		$scope.openNav = function() {
			$rootScope.displaySideBar = !$rootScope.displaySideBar;
		};
		
		$scope.navigate = function(page)
		{
			$state.go(page);
		};
	}
})();