(function() {
	'use strict';

	angular.module('sampleApp').controller('loginController',loginFunction);

	loginFunction.$inject = [ '$scope', '$rootScope', 'device.utility','$state','GlobalVariable','dataService'];

	function loginFunction($scope, $rootScope, device,$state,GlobalVariable,dataService) {
		
		
		$scope.device = device;
		$scope.GlobalVariable = GlobalVariable;
		$scope.errorMessage="";
		
		GlobalVariable.isLoginPage = true;
		
		$scope.navigateToProduct = function() {
			$state.go('product');
		};
		
		$scope.onLoginClicked = function($event)
		{
			/*var request = {};
			request.email = $scope.loginemail;
			request.password=$scope.loginpassword;*/

			sessionStorage.userName = $scope.loginemail;
			var url='http://localhost:8080/getUserLoginDetails?username='+$scope.loginemail+'&password='+$scope.loginpassword;
			dataService.Get(url,onLoginSuccess,onLoginError,'application/json','application/json');
			//$state.go('report');
		};
		function js_yyyy_mm_dd_hh_mm_ss () {
			var now = new Date();
			var year = "" + now.getFullYear();
			var month = "" + (now.getMonth() + 1); if (month.length == 1) { month = "0" + month; }
			var day = "" + now.getDate(); if (day.length == 1) { day = "0" + day; }
			var  hour = "" + now.getHours(); if (hour.length == 1) { hour = "0" + hour; }
			var minute = "" + now.getMinutes(); if (minute.length == 1) { minute = "0" + minute; }
			var second = "" + now.getSeconds(); if (second.length == 1) { second = "0" + second; }
			return year + "-" + month + "-" + day + " " + hour + ":" + minute + ":" + second;
		}
		function onLoginSuccess(response)
		{
			if(response.validUser == true) {
				sessionStorage.userRole = response.userRole;
				$scope.errorMessage = '';
				sessionStorage.clockTime = js_yyyy_mm_dd_hh_mm_ss();
				sessionStorage.userId =response.userId;

				$state.go('home');
			}
			else
			{
				$scope.errorMessage="Your login is incorrect. Please re-enter your User Name and Password. "
			}
		}

		function onLoginError(response)
		{
			$scope.errorMessage="Your login is incorrect. Please re-enter your User Name and Password. "
		}
	}
})();