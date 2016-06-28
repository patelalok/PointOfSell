(function() {
	'use strict';

	angular.module('sampleApp').controller('ReportController', ReportController);

	ReportController.$inject = [ '$scope', '$rootScope', 'device.utility','GlobalVariable','DialogFactory','dataService','$window','$filter','$timeout','RestrictedCharacter.Types'];

	function ReportController($scope, $rootScope, device ,GlobalVariable,DialogFactory,dataService,$window,$filter,$timeout,restrictCharacter) 
	{
		GlobalVariable.isLoginPage = false;
		$scope.restrictCharacter=restrictCharacter;	
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
	    $scope.openStartCalendar = function($event) {
			$event.preventDefault();
			$event.stopPropagation();
			$scope.openStart = true;
		};
		$scope.openEndCalendar = function($event) {
			$event.preventDefault();
			$event.stopPropagation();
			$scope.openEnd = true;
		};
		$scope.onDateSelected = function(startDate, endDate, label, element) {
			var receiptIndex = element.attr('data-receipt-index');
			element.find('span').eq(0).html(endDate.format('MM/DD/YYYY'));
			$scope.salesDates[receiptIndex] = endDate.format('MM/DD/YYYY');
		};
		function render()
		{
			//$scope.startDate = moment();
			/** Options for the date picker directive * */
			$scope.dateRangeOptions = {
				//startDate : moment(),
				showDropdowns : true,
				format : 'MM/DD/YYYY',
				singleDatePicker : true
			};
			$scope.startDate = $filter('date')(new Date(), "MM/dd/yyyy");
			$scope.endDate = $scope.startDate;
			
			loadReports(js_yyyy_mm_dd_hh_mm_ss(),js_yyyy_mm_dd_hh_mm_ss());
		}
		function loadReports(start,end)
		{
			var url ="http://localhost:8080/getDailyTransactionDetails?startDate="+start+"&endDate="+end;
			dataService.Get(url,getReportsSuccessHandler,getReportsErrorHandler,'application/json','application/json');
			getReportsSuccessHandler('');
		}
		function getReportsSuccessHandler(response)
		{
			$scope.loadReports = response;
			/*$scope.loadReports = [
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
			                      ];*/
		}
		function getReportsErrorHandler(response)
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
		render();
	}
		
})();