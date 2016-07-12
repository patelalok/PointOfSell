(function() {
	'use strict';

	angular.module('sampleApp').controller('MonthlyAnalysisController', MonthlyAnalysisController);

	MonthlyAnalysisController.$inject = [ '$scope', '$rootScope', 'device.utility','GlobalVariable','$state','$filter','dataService','$window'];

	function MonthlyAnalysisController($scope, $rootScope, device,GlobalVariable,$state,$filter,dataService,$window) {
		
		
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
			var start = js_yyyy_mm_dd()+''+' 00:00:00';
			var end = js_yyyy_mm_dd()+''+' 23:59:59';
			loadWeeklyDtls(start,end);
			loadMonthlyDtls(start,end);
		}
		render();
		function js_yyyy_mm_dd () {
			  var now = new Date();
			  var year = "" + now.getFullYear();
			  var month = "" + (now.getMonth() + 1); if (month.length == 1) { month = "0" + month; }
			  var day = "" + now.getDate(); if (day.length == 1) { day = "0" + day; }
			 var  hour = "" + now.getHours(); if (hour.length == 1) { hour = "0" + hour; }
			  var minute = "" + now.getMinutes(); if (minute.length == 1) { minute = "0" + minute; }
			  var second = "" + now.getSeconds(); if (second.length == 1) { second = "0" + second; }
			  return year + "-" + month + "-" + day ;
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
		function loadWeeklyDtls(start,end)
		{
			var url = "http://localhost:8080/getWeeklyTransactionDetails?startDate="+start+"&endDate="+end+"&month=2";
			dataService.Get(url,getWeeklyDtlsSuccessHandler,getWeeklyDtlsErrorHandler,'application/json','application/json');
			//getWeeklyDtlsSuccessHandler('');
			
		}
		function getWeeklyDtlsSuccessHandler(response)
		{
			$scope.weeklyDtls = response;
			/*$scope.weeklyDtls = [
			                     {
			                    	    "cash1": 12.99,
			                    	    "cash2": 12.99,
			                    	    "cash3": 12.99,
			                    	    "cash4": 12.99,
			                    	    "cash5": 12.99,
			                    	    "credit1": 11.99,
			                    	    "credit2": 11.99,
			                    	    "credit3": 11.99,
			                    	    "credit4": 11.99,
			                    	    "credit5": 11.99,
			                    	    "total1": 24,
			                    	    "total2": 24,
			                    	    "total3": 24,
			                    	    "total4": 24,
			                    	    "total5": 24,
			                    	    "weekAvg": 134.99
			                    	  }
			                    	];*/
		}
		function getWeeklyDtlsErrorHandler(response)
		{
			
		}
		
		function loadMonthlyDtls(start,end)
		{
			var url = "http://localhost:8080/getMonthlyTransactionDetails?startDate="+start+"&endDate="+end+"&month=2";
			dataService.Get(url,getMonthlyDtlsSuccessHandler,getMonthlyDtlsErrorHandler,'application/json','application/json');
			//getMonthlyDtlsSuccessHandler('');
		}
		function getMonthlyDtlsSuccessHandler(response)
		{
			$scope.monthlyDtls = response;
			/*$scope.monthlyDtls = [
			                      {
			                    	    "date": "2016-06-01",
			                    	    "sumCash": 32,
			                    	    "sumCredit": 0,
			                    	    "total": 0,
			                    	    "sumTax": 0,
			                    	    "discount": 0,
			                    	    "dailyAvgForMonth": 0
			                    	  },
			                    	  {
			                    	    "date": "2016-06-10",
			                    	    "sumCash": 23,
			                    	    "sumCredit": 0,
			                    	    "total": 12.99,
			                    	    "sumTax": 0,
			                    	    "discount": 0,
			                    	    "dailyAvgForMonth": 0
			                    	  },
			                    	  {
			                    	    "date": "2016-06-20",
			                    	    "sumCash": 0,
			                    	    "sumCredit": 0,
			                    	    "total": 0,
			                    	    "sumTax": 0,
			                    	    "discount": 0,
			                    	    "dailyAvgForMonth": 0
			                    	  },
			                    	  {
			                    	    "date": "2016-06-21",
			                    	    "sumCash": 0,
			                    	    "sumCredit": 0,
			                    	    "total": 0,
			                    	    "sumTax": 0,
			                    	    "discount": 0,
			                    	    "dailyAvgForMonth": 0
			                    	  },
			                    	  {
			                    	    "date": "2016-06-22",
			                    	    "sumCash": 96,
			                    	    "sumCredit": 118.86999999999999,
			                    	    "total": 204.87,
			                    	    "sumTax": 0,
			                    	    "discount": 0,
			                    	    "dailyAvgForMonth": 0
			                    	  }
			                    	];*/
		}
		$scope.printMonthly = function()
		{
			$window.print();
		};
		function getMonthlyDtlsErrorHandler(response)
		{
			
		}
	}
})();