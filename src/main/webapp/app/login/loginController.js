(function() {
	'use strict';

	angular.module('sampleApp').controller('loginController',loginFunction);

	loginFunction.$inject = [ '$scope', '$rootScope', 'device.utility','$state','GlobalVariable'];

	function loginFunction($scope, $rootScope, device,$state,GlobalVariable) {
		
		
		$scope.device = device;
		$scope.GlobalVariable = GlobalVariable;
		
		GlobalVariable.isLoginPage = true;
		
		$scope.navigateToProduct = function() {
			$state.go('product');
		};
		
		$scope.onLoginClicked = function($event)
		{
			$state.go('home');
		};
	}
})();