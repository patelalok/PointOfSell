(function() {
	'use strict';

	angular.module('sampleApp').controller('ReportController', ReportController);

	ReportController.$inject = [ '$scope', '$rootScope', 'device.utility','GlobalVariable','DialogFactory','dataService','$window','$filter','$timeout','RestrictedCharacter.Types'];

	function ReportController($scope, $rootScope, device ,GlobalVariable,DialogFactory,dataService,$window,$filter,$timeout,restrictCharacter) 
	{
		$scope.labels = ['January', 'February', 'March', 'April', 'May', 'June', 'July'];
	    $scope.series = ['Series A', 'Series B'];
	    $scope.data = [
	      [65, 59, 80, 81, 56, 55, 40],
	      [28, 48, 40, 19, 86, 27, 90]
	    ];
	    $scope.onClick = function (points, evt) {
	      console.log(points, evt);
	    };
	    $scope.onHover = function (points) {
	      if (points.length > 0) {
	        console.log('Point', points[0].value);
	      } else {
	        console.log('No point');
	      }
	    };
	}
		
})();