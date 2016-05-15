(function() {
	'use strict';

	angular.module('sampleApp').controller('sellController', Body);

	Body.$inject = [ '$scope', '$rootScope', 'device.utility','GlobalVariable'];

	function Body($scope, $rootScope, device ,GlobalVariable) {
		
		$scope.device = device;
		$scope.GlobalVariable = GlobalVariable;
		GlobalVariable.isLoginPage = false;

		$scope.pageSize = 10;
		
		$scope.testData = [{
			
			"itemNo":1,
			"item":"paint",
			"quantity":1,
			"retail":"test",
			"discount":20,
			"tax":10.98,
			"total":20.00
		},
		{
			"itemNo":1,
			"item":"paint",
			"quantity":1,
			"retail":"test",
			"discount":20,
			"tax":10.98,
			"total":20.00
		}
			
		];
		
		$scope.addRow = function()
		{
			$scope.testData.push({"itemNo":1,
				"item":"paint",
				"quantity":1,
				"retail":"test",
				"discount":20,
				"tax":10.98,
				"total":20.00});
		};
		function render()
		{
			$scope.currentPageIndexArr = 0;
		}
		render();
	}
		
})();