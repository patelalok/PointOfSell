(function() {
	'use strict';

	angular.module('sampleApp').controller('HomeController', HomeController);

	HomeController.$inject = [ '$scope', '$rootScope', 'device.utility','GlobalVariable','DialogFactory','dataService','$window','$filter','$timeout','RestrictedCharacter.Types'];

	function HomeController($scope, $rootScope, device ,GlobalVariable,DialogFactory,dataService,$window,$filter,$timeout,restrictCharacter) 
	{
		GlobalVariable.isLoginPage = false;
		$scope.restrictCharacter=restrictCharacter;	
		$scope.labels = ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'June', 'July','August','Sept','Oct','Nov','Dec'];
	    $scope.series = ['Series A', 'Series B'];
	    $scope.data = [
	      [6965, 5559, 1280, 8100, 2356, 9055, 3340,4545,2131,9872,1334,9999],
	      [28, 48, 40, 19, 86, 27, 90,56,21,99,34,56]
	    ];
	    $scope.totalData = [[28, 48, 40, 19, 86, 27, 90,56,21,99,34,56]];
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
	    
		function render()
		{
			$scope.dashboardDrop = 'monthWiseDash';
			$scope.getDashboardDetails();
		}
		$scope.getDashboardDetails = function()
		{
			var url;
			var years = js_yyyy_mm_dd_hh_mm_ss().split("-");
			var currentStartDate =years[0]+"-01-01 00:00:00";
			var currentEndDate =years[0]+"-12-31 23:59:59";
			var previousStartDate = years[1]+"-01-01 00:00:00";
			var previousEndDate = years[1]+"-12-31 23:59:59";
			
			if($scope.dashboardDrop == 'monthWiseDash')
			{
				//url = "http://localhost:8080/getDailyTransactionDetails?startDate="+start+"&endDate="+end;
			}
			else
			{
				var currentDayStartDate = years[0]+"-"+getCurrentMonth()+"-01 00:00:00";
				var currentDayEndDate = years[0]+"-"+getCurrentMonth()+"-07 23:59:59";
				
				var previousDayStartDate = years[1]+"-"+getCurrentMonth()+"-01 00:00:00";
				var previousDayEndDate = years[1]+"-"+getCurrentMonth()+"-07 23:59:59";
			}	
		};
		function loadReports(start,end)
		{
			var url ="http://localhost:8080/getDailyTransactionDetails?startDate="+start+"&endDate="+end;
			dataService.Get(url,getReportsSuccessHandler,getReportsErrorHandler,'application/json','application/json');
			getReportsSuccessHandler('');
		}
		function getReportsSuccessHandler(response)
		{
			//$scope.loadReports = response;
			$scope.loadReports = [
			                      {
			                    	  "credit":120.89,
			                    	  "tax":12,
			                    	  "discount":10,
			                    	  "total":218.90,
			                    	  "grossSale":298.90,
			                    	  "markup":0,
			                    	  "noOfTransactions":11,
			                    	  "avgTotal":19.08,
			                    	  "profitAmount":23,
			                    	  "cash":89
			                      }
			                      ];
		}
		function getReportsErrorHandler(response)
		{
			
		}
		function getCurrentandPreviousYear () {
			  var now = new Date();
			  var year = "" + now.getFullYear();
			  var prevoiusYear = now.getFullYear()-1;
			  
			  return year + "-" + prevoiusYear;
			}
		function getCurrentMonth()
		{
			var now = new Date();
			var month = "" + (now.getMonth() + 1); if (month.length == 1) { month = "0" + month; }
			  var day = "" + now.getDate(); if (day.length == 1) { day = "0" + day; }
			 var  hour = "" + now.getHours(); if (hour.length == 1) { hour = "0" + hour; }
			  var minute = "" + now.getMinutes(); if (minute.length == 1) { minute = "0" + minute; }
			  var second = "" + now.getSeconds(); if (second.length == 1) { second = "0" + second; }
			  
			  return month;
		}
		render();
	}
		
})();