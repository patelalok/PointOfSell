(function() {
	'use strict';

	angular.module('sampleApp').controller('dummyController', dummyFunction);

	dummyFunction.$inject = [ '$scope', '$rootScope', 'device.utility','$state','GlobalVariable'];

	function dummyFunction($scope, $rootScope, device,$state,GlobalVariable) {
		
		
		$scope.device = device;
		$scope.GlobalVariable = GlobalVariable;
		
		GlobalVariable.isLoginPage = true;
		
		$scope.navigateToProduct = function() {
			$state.go('product');
		};
	}
})();