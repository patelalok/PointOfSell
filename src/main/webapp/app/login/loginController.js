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
			var request = {};
			request.email = $scope.loginemail;
			request.password=$scope.loginpassword;
			var url='';
			dataService.Post(url,request,onLoginSuccess,onLoginError,'application/json','application/json');
			$state.go('report');
		};
		function onLoginSuccess(response)
		{
			
		}
		function onLoginError(response)
		{
			
		}
	}
})();