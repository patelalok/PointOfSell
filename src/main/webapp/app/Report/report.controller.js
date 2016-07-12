(function() {
	'use strict';

	angular.module('sampleApp').controller('ReportController', ReportController);

	ReportController.$inject = [ '$scope', '$rootScope', 'device.utility','GlobalVariable','DialogFactory','dataService','$window','$filter','$timeout','RestrictedCharacter.Types'];

	function ReportController($scope, $rootScope, device ,GlobalVariable,DialogFactory,dataService,$window,$filter,$timeout,restrictCharacter) 
	{
		GlobalVariable.isLoginPage = false;
		$scope.restrictCharacter=restrictCharacter;	
		
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
			$scope.reportType = 'salesSummary';
			$scope.measureType = 'yearlySummary'
			
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
			var start = js_yyyy_mm_dd('')+''+' 00:00:00';
			var endM = js_yyyy_mm_dd('M')+''+' 23:59:59';
			var endY = js_yyyy_mm_dd('Y')+''+' 23:59:59';
			var endH = js_yyyy_mm_dd('')+''+' 23:59:59';
			loadSalesMonthlyData(start,endM);
			loadSalesHourlyData(start,endH);
			loadSalesYearlyData(start,endY);
			
		}
		function loadSalesYearlyData(start,end)
		{
			var url='http://localhost:8080/getYearlyTransactionDetails?startDate='+start+'&endDate='+end;
			dataService.Get(url,onYearlySucces,onYearlyError,'application/json','application/json');
			//onMonthlySucces('');
			
		}
		function onYearlySucces(response)
		{
			$scope.yearlySummary = response;
			/*$scope.monthlySummary = [
			                         {
			                        	    "date": "2016-06-01",
			                        	    "credit": 0,
			                        	    "cash": 32,
			                        	    "check": 0,
			                        	    "tax": 32.99,
			                        	    "discount": 0,
			                        	    "returnAmount": 0,
			                        	    "profit": 23,
			                        	    "marginPercentage": 0,
			                        	    "total": 0,
			                        	    "monthAvg": 0,
			                        	    "cost": 0,
			                        	    "retail": 23
			                        	  },
			                        	  {
			                        	    "date": "2016-06-22",
			                        	    "credit": 56.89000000000001,
			                        	    "cash": 101,
			                        	    "check": 0,
			                        	    "tax": 0,
			                        	    "discount": 0,
			                        	    "returnAmount": 0,
			                        	    "profit": 939,
			                        	    "marginPercentage": 0,
			                        	    "total": 157.89000000000001,
			                        	    "monthAvg": 0,
			                        	    "cost": 948.99,
			                        	    "retail": 1887.99
			                        	  }
			                        	];*/
		}
		function onYearlyError(response)
		{
			
		}
		function loadSalesHourlyData(start,end)
		{
			var url='http://localhost:8080/getHourlyTransactionDetails?startDate='+start+'&endDate='+end;
			dataService.Get(url,onHourlySucces,onHourlyError,'application/json','application/json');
			//onMonthlySucces('');
			
		}
		function onHourlySucces(response)
		{
			$scope.hourlySummary = response;
			/*$scope.monthlySummary = [
			                         {
			                        	    "date": "2016-06-01",
			                        	    "credit": 0,
			                        	    "cash": 32,
			                        	    "check": 0,
			                        	    "tax": 32.99,
			                        	    "discount": 0,
			                        	    "returnAmount": 0,
			                        	    "profit": 23,
			                        	    "marginPercentage": 0,
			                        	    "total": 0,
			                        	    "monthAvg": 0,
			                        	    "cost": 0,
			                        	    "retail": 23
			                        	  },
			                        	  {
			                        	    "date": "2016-06-22",
			                        	    "credit": 56.89000000000001,
			                        	    "cash": 101,
			                        	    "check": 0,
			                        	    "tax": 0,
			                        	    "discount": 0,
			                        	    "returnAmount": 0,
			                        	    "profit": 939,
			                        	    "marginPercentage": 0,
			                        	    "total": 157.89000000000001,
			                        	    "monthAvg": 0,
			                        	    "cost": 948.99,
			                        	    "retail": 1887.99
			                        	  }
			                        	];*/
		}
		function onHourlyError(response)
		{
			
		}
		function loadSalesMonthlyData(start,end)
		{
			var url='http://localhost:8080/getMonthlyTransactionDetails?startDate='+start+'&endDate='+end;
			dataService.Get(url,onMonthlySucces,onMonthlyError,'application/json','application/json');
			//onMonthlySucces('');
			
		}
		function onMonthlySucces(response)
		{
			$scope.monthlySummary = response;
			/*$scope.monthlySummary = [
			                         {
			                        	    "date": "2016-06-01",
			                        	    "credit": 0,
			                        	    "cash": 32,
			                        	    "check": 0,
			                        	    "tax": 32.99,
			                        	    "discount": 0,
			                        	    "returnAmount": 0,
			                        	    "profit": 23,
			                        	    "marginPercentage": 0,
			                        	    "total": 0,
			                        	    "monthAvg": 0,
			                        	    "cost": 0,
			                        	    "retail": 23
			                        	  },
			                        	  {
			                        	    "date": "2016-06-22",
			                        	    "credit": 56.89000000000001,
			                        	    "cash": 101,
			                        	    "check": 0,
			                        	    "tax": 0,
			                        	    "discount": 0,
			                        	    "returnAmount": 0,
			                        	    "profit": 939,
			                        	    "marginPercentage": 0,
			                        	    "total": 157.89000000000001,
			                        	    "monthAvg": 0,
			                        	    "cost": 948.99,
			                        	    "retail": 1887.99
			                        	  }
			                        	];*/
		}
		function onMonthlyError(response)
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
		function js_yyyy_mm_dd (value) {
			  var now = new Date();
			 
			  var year = "" + now.getFullYear();
			  var month = "" + (now.getMonth() + 1); if (month.length == 1) { month = "0" + month; }
			  var day = "" + now.getDate(); if (day.length == 1) { day = "0" + day; }
			  var  hour = "" + now.getHours(); if (hour.length == 1) { hour = "0" + hour; }
			  var minute = "" + now.getMinutes(); if (minute.length == 1) { minute = "0" + minute; }
			  var second = "" + now.getSeconds(); if (second.length == 1) { second = "0" + second; }
			  
			  if(value == 'Y')
			  {
				  //var test = new Date(year,month,day);
				  now.setMonth(now.getMonth()+12);
				  now.setDate(now.getDate() +31);
				  var year = "" + now.getFullYear();
				  var month = "" + (now.getMonth() + 1); if (month.length == 1) { month = "0" + month; }
				  var day = "" + now.getDate(); if (day.length == 1) { day = "0" + day; }
				  return year + "-" + month + "-" + day ;
			  }
			  else if(value == 'M' )
			  {
				  //var test = new Date(year,month,day);
				  now.setDate(now.getDate() +31);
				  var year = "" + now.getFullYear();
				  var month = "" + (now.getMonth() + 1); if (month.length == 1) { month = "0" + month; }
				  var day = "" + now.getDate(); if (day.length == 1) { day = "0" + day; }
				  return year + "-" + month + "-" + day ;
			  }
			  else
			  {
				  return year + "-" + month + "-" + day ;
			  }	  
			  
			 
			  
			  
			}
	}
		
})();