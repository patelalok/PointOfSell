(function() {
	'use strict';

	angular.module('sampleApp').controller('loginController',loginFunction);

	loginFunction.$inject = [ '$scope', '$rootScope', 'device.utility','$state','GlobalVariable','dataService'];

	function loginFunction($scope, $rootScope, device,$state,GlobalVariable,dataService) {
		
		
		$scope.device = device;
		$scope.GlobalVariable = GlobalVariable;
		
		GlobalVariable.isLoginPage = true;
		
		$scope.navigateToProduct = function() {
			$state.go('product');
		};
		
		$scope.onLoginClicked = function($event)
		{
			/*var request = {};
			request.email = $scope.loginemail;
			request.password=$scope.loginpassword;*/
			var url='http://localhost:8080/getUserDetails?username='+$scope.loginemail+'&password='+$scope.loginpassword;
			dataService.Get(url,onLoginSuccess,onLoginError,'application/json','application/json');
			//$state.go('report');
		};
		function onLoginSuccess(response)
		{
			if(response == true)
				$state.go('home');
		}
		function onLoginError(response)
		{
			
		}
	}
})();