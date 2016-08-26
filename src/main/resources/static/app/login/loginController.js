(function() {
	'use strict';

	angular.module('sampleApp').controller('loginController',loginFunction);

	loginFunction.$inject = [ '$scope', '$rootScope', 'device.utility','$state','GlobalVariable','dataService','DialogFactory'];

	function loginFunction($scope, $rootScope, device,$state,GlobalVariable,dataService,DialogFactory) {
		
		
		$scope.device = device;
		$scope.GlobalVariable = GlobalVariable;
		$scope.errorMessage="";
		$scope.productKeyAdded = true;
		
		GlobalVariable.isLoginPage = true;
		
		$scope.navigateToProduct = function() {
			$state.go('product');
		};
		
		$scope.onLoginClicked = function($event)
		{
			var date = new Date();
			var firstDay = new Date(date.getFullYear(), date.getMonth(), 1);
			var lastDay = new Date(date.getFullYear(), date.getMonth() + 1, 0);
			var day = date.getUTCDate()-1;
			if((day == firstDay.getDate()) && ($scope.productKeyAdded == false))
			{
				var _tmPath = 'app/login/licensing.html';
				var _ctrlPath = 'LicensingController';
				DialogFactory.show(_tmPath, _ctrlPath, $scope.callBackProductKey);
			}
			else
			{
				sessionStorage.userName = $scope.loginemail;
				var url='http://localhost:8080/getUserLoginDetails?username='+$scope.loginemail+'&password='+$scope.loginpassword;
				dataService.Get(url,onLoginSuccess,onLoginError,'application/json','application/json');
			}


		};
		$scope.callBackProductKey = function()
		{
			var url='localhost:8080/getLicenceKey?licenceKey='+GlobalVariable.productKey;
			dataService.Get(url,onProductKeySuccess,onProductKeyError,'application/json','application/json');
		};
		function onProductKeySuccess(response)
		{
			if(response == true) {
				$scope.productKeyAdded = true;
				sessionStorage.userName = $scope.loginemail;
				var url = 'http://localhost:8080/getUserLoginDetails?username=' + $scope.loginemail + '&password=' + $scope.loginpassword;
				dataService.Get(url, onLoginSuccess, onLoginError, 'application/json', 'application/json');
			}
			else
			{
				$scope.productKeyAdded = false;
			}
		}
		function onProductKeyError(response)
		{

		}
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
				sessionStorage.userRole = response.userRole;
				GlobalVariable.userRole= response.userRole;

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