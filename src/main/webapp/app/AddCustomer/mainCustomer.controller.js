(function() {
	'use strict';

	angular.module('sampleApp').controller('mainCustomerController', mainCustomerController);

	mainCustomerController.$inject = [ '$scope', '$rootScope', 'device.utility','GlobalVariable','$state','DialogFactory','$timeout'];

	function mainCustomerController($scope, $rootScope, device ,GlobalVariable,$state,DialogFactory,$timeout) {
		
		$scope.device = device;
		$scope.GlobalVariable = GlobalVariable;
		GlobalVariable.isLoginPage = false;
		$scope.selectedIndex = 0;
		$scope.isAsc = false;
		$scope.enabled = true;
		
		
		$scope.customerData =  [
                                      {
                                          "contact": 999999999,
                                          "company": "Tcl-delta",
                                          "suburb": "test",
                                          "city": "smyrna",
                                          "code": "GA",
                                          "group": "All Customers",
                                          "pend":60,
                                          "balance":89
                                        },
                                        {
                                            "contact": 999999999,
                                            "company": "Tcl-delta",
                                            "suburb": "test",
                                            "city": "smyrna",
                                            "code": "GA",
                                            "group": "All Customers",
                                            "pend":60,
                                            "balance":89
                                          
                                        }];
		
		$scope.sortColumnData = function(index) {
			if ($scope.testGridData != null && $scope.testGridData.length > 0) {
				if (index != 0) {
					return false;
				}
				if ($scope.isAsc) {
					$scope.isAsc = false;
				} else {
					$scope.isAsc = true;$scope
				}$scope
			}
		};
		function render()
		{
			$scope.currentPageIndexArr = 0;
			$scope.curPageOnTotalLen = 0;
			$scope.totalLength = 0;
				
		}
		render();
		
	}
		
})();