(function() {
	'use strict';

	angular.module('sampleApp').controller('productController', Body);

	Body.$inject = [ '$scope', '$rootScope', 'device.utility','GlobalVariable'];

	function Body($scope, $rootScope, device ,GlobalVariable) {
		
		$scope.device = device;
		$scope.GlobalVariable = GlobalVariable;
		GlobalVariable.isLoginPage = false;
		
	}
		
})();