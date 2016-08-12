(function() {
	'use strict';

	angular.module('sampleApp').controller('ReportController', ReportController);

	ReportController.$inject = [ '$scope', '$rootScope', 'device.utility','GlobalVariable','DialogFactory','dataService','$window','$filter','$timeout','RestrictedCharacter.Types'];

	function ReportController($scope, $rootScope, device ,GlobalVariable,DialogFactory,dataService,$window,$filter,$timeout,restrictCharacter) 
	{
		GlobalVariable.isLoginPage = false;
		$scope.yearlySummary = [];
		$scope.restrictCharacter=restrictCharacter;
		$scope.maxDate = new Date();
		$scope.minDate = moment().subtract(1, "days").toDate();
	    $scope.openStartCalendar = function($event) {
			$event.preventDefault();
			$event.stopPropagation();
			$scope.openStart = true;
		};
		$scope.openStartCalendarDaily = function($event) {
			$event.preventDefault();
			$event.stopPropagation();
			$scope.openStartDaily = true;
		};
		$scope.openEndCalendar = function($event) {
			$event.preventDefault();
			$event.stopPropagation();
			$scope.openEnd = true;
		};
		$scope.onDateSelected = function(startDate, endDate, label, element) {
			var receiptIndex = element.attr('data-receipt-index');
			element.find('span').eq(0).html(endDate.format('yyyy-MM-dd'));
		};
		function render()
		{
			$scope.reportType = 'salesSummary';
			$scope.measureType = 'yearlySummary';
			$scope.dlyTransType = 'thisDay';
			$scope.hlyTransType = 'yest';
			$scope.slCatType = 'yestSales';
			$scope.cType = 'cat';
			
			//$scope.startDate = moment();
			/** Options for the date picker directive * */
			$scope.dateRangeOptions = {
				//startDate : moment(),
				showDropdowns : true,
				format : 'yyyy-MM-dd',
				singleDatePicker : true
			};
			$scope.yrlyTransType = 'thisYear';
			$scope.mntTransType = 'Jan';
			$scope.startDate = $filter('date')(new Date(), "MM/dd/yyyy");
			$scope.endDate = $scope.startDate;
			var years = getCurrentandPreviousYear().split("-");
			var currentStartDate =years[0]+"-01-01 00:00:00";
			var currentEndDate =years[0]+"-12-31 23:59:59";
			loadSalesYearlyData(currentStartDate,currentEndDate);
			
		}
		$scope.checkType = function()
		{
			$scope.slCatType = 'yestSales';
			if($scope.reportType == 'salesCategory')
			{
				$scope.loadSalesCatData('yestSales','salesCategory');
			}
			else if($scope.reportType == 'salesBrand')
			{
				$scope.loadSalesCatData('yestSales','salesBrand');
			}
			else if($scope.reportType == 'salesVendor')
			{
				$scope.loadSalesCatData('yestSales','salesVendor');
			}
			else if($scope.reportType == 'salesUser')
			{
				$scope.loadSalesCatData('yestSales','salesUser');
			}
			else if($scope.reportType == 'salesProduct')
			{
				$scope.loadSalesCatData('yestSales','salesProduct');
			}
			else if($scope.reportType == 'salesCustomer')
			{
				$scope.loadSalesCatData('yestSales','salesCustomer');
			}
			else if($scope.reportType == 'salesSummary')
			{
				$scope.measureType = 'yearlySummary';
				var years = getCurrentandPreviousYear().split("-");
				var currentStartDate =years[0]+"-01-01 00:00:00";
				var currentEndDate =years[0]+"-12-31 23:59:59";
				loadSalesYearlyData(currentStartDate,currentEndDate);
			}
			else if($scope.reportType == 'inventorySummary')
			{
					$scope.loadSalesIneventory('cat');
			}
		};

		$scope.loadSalesIneventory = function(type)
		{
			var url;
			if(type == 'cat')
			{
				url =' http://localhost:8080/getInventoryByCategory';
			}
			else if(type == 'ven')
			{
				url =' http://localhost:8080/getInventoryByVendor';
			}
			else if(type == 'bran')
			{
				url =' http://localhost:8080/getInventoryByBrand';
			}
			else if(type == 'tin')
			{

			}
			dataService.Get(url,onSalesInvSuccess,onSalesInvError,'application/json','application/json');
		};
		function onSalesInvSuccess(response)
		{
			$scope.inventorySummary = response;
		}
		function onSalesInvError(response)
		{

		}

		$scope.checkMeasure = function()
		{
			if($scope.measureType == 'monthlySummary')
			{
				$scope.loadSalesMonthlyData('Jan');
			}
			else if($scope.measureType == 'dailySummary')
			{
				$scope.loadSalesDailyData('thisDay');
			}
			else if($scope.measureType == 'hourlySummary')
			{
				$scope.loadSalesHourlyData('yest');
			}
			else if($scope.measureType == 'yearlySummary')
			{
				var years = getCurrentandPreviousYear().split("-");
				var currentStartDate =years[0]+"-01-01 00:00:00";
				var currentEndDate =years[0]+"-12-31 23:59:59";
				loadSalesYearlyData(currentStartDate,currentEndDate);
			}
		};
		$scope.applySalesByType = function(type)
		{
			$scope.loadSalesCatData('',type);
		};
		$scope.loadSalesCatData = function(saleDate,type)
		{
			var url;
			var start,end;
			if(saleDate == 'yestSales')
			{
				start = getPreviousDay()+''+' 00:00:00';
				end = getPreviousDay()+''+' 23:59:59';
			}
			else if(saleDate == 'lastWeekSales')
			{
				start = getLast7Day()+' 00:00:00';
				end = getCurrentDay()+' 23:59:59';
			}
			else if(saleDate == 'thisMonthSales')
			{
				start = getcurrentYear()+"-"+getcurrentMonth()+"-01 00:00:00";
				end = getcurrentYear()+"-"+getcurrentMonth()+"-31 23:59:59";
			}
			else if(saleDate == 'lastMonthSales')
			{
				start = getcurrentYear()+"-"+getlastMonth()+"-01 00:00:00";
				end = getcurrentYear()+"-"+getcurrentMonth()+"-31 23:59:59";
			}
			else if(saleDate == 'last3MonthsSales')
			{
				start = getlast3Months()+" 00:00:00";
				end = getCurrentDay()+" 23:59:59";
			}
			else if(saleDate == 'last6MonthsSales')
			{
				start = getlast6Months()+" 00:00:00";
				end = getCurrentDay()+" 23:59:59";
			}
			else if(saleDate == 'thisYearSales')
			{
				var years = getCurrentandPreviousYear().split("-");
				start =years[0]+"-01-01 00:00:00";
				end =years[0]+"-12-31 23:59:59";
			}
			else if(saleDate == 'lastYearSales')
			{
				var years = getCurrentandPreviousYear().split("-");
				start =years[1]+"-01-01 00:00:00";
				end =years[1]+"-12-31 23:59:59";
			}
			else
			{
				start = $filter('date')($scope.startTransDate, "yyyy-MM-dd")+" 00:00:00";
				end = $filter('date')($scope.endTransDate, "yyyy-MM-dd")+" 23:59:59";
			}


			if(type == 'salesCategory')
			{
				var url='http://localhost:8080/getSalesByCategory?startDate='+start+'&endDate='+end;
			}
			else if(type == 'salesBrand')
			{
				var url='http://localhost:8080/getSalesByBrand?startDate='+start+'&endDate='+end;
			}
			else if(type == 'salesVendor')
			{
				var url='http://localhost:8080/getSalesByVendor?startDate='+start+'&endDate='+end;
			}
			else if(type == 'salesUser')
			{
				var url='http://localhost:8080/getSalesByUser?startDate='+start+'&endDate='+end;
			}
			else if(type == 'salesProduct')
			{
				var url='http://localhost:8080/getSalesByProduct?startDate='+start+'&endDate='+end;
			}
			else if(type == 'salesCustomer')
			{
				var url='http://localhost:8080/getSalesByCustomer?startDate='+start+'&endDate='+end;
			}


			/*else
			{
				start = $filter('date')($scope.startTransDate, "yyyy-MM-dd HH:mm:ss");
				end = $filter('date')($scope.endTransDate, "yyyy-MM-dd HH:mm:ss");
			}*/

			dataService.Get(url,onSalesSuccess,onSalesError,'application/json','application/json');
		};
		function onSalesSuccess(response)
		{
			if($scope.reportType == 'salesUser')
			{
				if(response !== null)
				$scope.salesByUser = response;
			}
			else if($scope.reportType == 'salesCustomer')
			{
				if(response !== null)
				$scope.salesByCustomer = response;
			}
			else if($scope.reportType == 'salesProduct')
			{
				if(response !== null)
				$scope.salesByProduct = response;
			}
			else if($scope.reportType == 'salesBrand')
			{
				if(response !== null)
				$scope.salesByBrand = response;
			}
			else if($scope.reportType == 'salesVendor')
			{
				if(response !== null)
				$scope.salesByVendor = response;
			}
			else if($scope.reportType == 'salesCategory')
			{
				if(response !== null)
				$scope.salesByCategory = response;
			}
		};
		function onSalesError(response)
		{

		};
		$scope.loadSalesHourlyData = function(hr)
		{
			var start,end;
			if(hr == 'yest')
			{
				start = getPreviousDay()+''+' 00:00:00';
				end = getPreviousDay()+''+' 23:59:59';
			}
			else if(hr == 'lastWeek')
			{
					start = getLast7Day()+' 00:00:00';
					end = getCurrentDay()+' 23:59:59';
			}
			else if(hr == 'thisMonth')
			{
				start = getcurrentYear()+"-"+getcurrentMonth()+"-01 00:00:00";
				end = getcurrentYear()+"-"+getcurrentMonth()+"-31 23:59:59";
			}
			else if(hr == 'lastMonth')
			{
					start = getcurrentYear()+"-"+getlastMonth()+"-01 00:00:00";
					end = getcurrentYear()+"-"+getcurrentMonth()+"-31 23:59:59";
			}
			else if(hr == 'last3Months')
			{
				start = getlast3Months()+" 00:00:00";
				end = getCurrentDay()+" 23:59:59";
			}
			else if(hr == 'last6Months')
			{
					start = getlast6Months()+" 00:00:00";
					end = getCurrentDay()+" 23:59:59";
			}
			else if(hr == 'thisYear')
			{
				var years = getCurrentandPreviousYear().split("-");
				start =years[0]+"-01-01 00:00:00";
				end =years[0]+"-12-31 23:59:59";
			}
			else if(hr == 'lastYear')
			{
				var years = getCurrentandPreviousYear().split("-");
				start =years[1]+"-01-01 00:00:00";
				end =years[1]+"-12-31 23:59:59";
			}
			else
			{
				start = $filter('date')($scope.startTransDate, "yyyy-MM-dd")+" 00:00:00";
				end = $filter('date')($scope.endTransDate, "yyyy-MM-dd")+" 23:59:59";
			}
			var url='http://localhost:8080/getHourlyTransactionDetails?startDate='+start+'&endDate='+end;
			dataService.Get(url,onHourlySucces,onHourlyError,'application/json','application/json');


		}
		function onHourlySucces(response)
		{
			if(response.hourlyDtoList !== null) {
				$scope.hourlySummary = response.hourlyDtoList;
				$scope.hourlySummary.push({
					"hour": "Total",
					"credit": parseFloat(response.finalTotalForReportsDtoList[0].totalCredit).toFixed(2),
					"cash": parseFloat(response.finalTotalForReportsDtoList[0].totalCash).toFixed(2),
					"check": parseFloat(response.finalTotalForReportsDtoList[0].totalCheck).toFixed(2),
					"tax": parseFloat(response.finalTotalForReportsDtoList[0].totalTax).toFixed(2),
					"discount": parseFloat(response.finalTotalForReportsDtoList[0].totalDiscount).toFixed(2),
					"returnAmount": parseFloat(response.finalTotalForReportsDtoList[0].totalReturn).toFixed(2),
					"profit": parseFloat(response.finalTotalForReportsDtoList[0].totalProfit).toFixed(2),
					"marginPercentage": parseFloat(response.finalTotalForReportsDtoList[0].avgMargin).toFixed(2),
					"total": parseFloat(response.finalTotalForReportsDtoList[0].grandTotal).toFixed(2),
					"monthAvg": 0.0,
					"cost": 0.0,
					"retail": 0.0,
					"noOfTrans": parseFloat(response.finalTotalForReportsDtoList[0].noOfTrans).toFixed(2),
					"avgBasketSize": parseFloat(response.finalTotalForReportsDtoList[0].avgBasketSize).toFixed(2)});
			}
			else
				$scope.hourlySummary = [];

		}
		function onHourlyError(response)
		{

		}
		$scope.applyHourlyMonthly = function()
		{
			$scope.loadSalesHourlyData('');
		};

		$scope.loadSalesDailyData = function(day)
		{
			var start,end;
			if(day == 'thisDay')
			{
				start = getCurrentDay()+''+' 00:00:00';
				end = getCurrentDay()+''+' 23:59:59';
			}
			else if(day == 'lastDay')
			{
				start = getPreviousDay()+''+' 00:00:00';
				end = getPreviousDay()+''+' 23:59:59';
			}
			else
			{
				start = $filter('date')($scope.startTransDailyDate, "yyyy-MM-dd")+" 00:00:00";
				end = $filter('date')($scope.startTransDailyDate, "yyyy-MM-dd")+" 23:59:59";
			}
			var url="http://localhost:8080/getDailyTransactionDetails?startDate="+start+"&endDate="+end;
				dataService.Get(url,onDailySucces,onDailyError,'application/json','application/json');
		};
		function onDailySucces(response)
		{
				$scope.dailySummary = response;
		};

		function onDailyError(response)
		{

		};
		$scope.applyDailyMonthly = function()
		{
			$scope.loadSalesDailyData('');
		};
		$scope.getYearlyTransData = function ()
		{
			if($scope.yrlyTransType == 'thisYear')
			{
				var years = getCurrentandPreviousYear().split("-");
				var currentStartDate =years[0]+"-01-01 00:00:00";
				var currentEndDate =years[0]+"-12-31 23:59:59";
				loadSalesYearlyData(currentStartDate,currentEndDate);
			}
			else
			{
				var years = getCurrentandPreviousYear().split("-");
				var currentStartDate =years[1]+"-01-01 00:00:00";
				var currentEndDate =years[1]+"-12-31 23:59:59";
				loadSalesYearlyData(currentStartDate,currentEndDate);
			}
		}
		function loadSalesYearlyData(start,end)
		{
			var url='http://localhost:8080/getYearlyTransactionDetails?startDate='+start+'&endDate='+end;
			dataService.Get(url,onYearlySucces,onYearlyError,'application/json','application/json');

			
		}
		function onYearlySucces(response)
		{
			if(response.yearlyListDtos !== null && response.yearlyListDtos !== '')
			{
				//$scope.yearlySummary = response.yearlyListDtos;
				for(var i=0;i<response.yearlyListDtos.length;i++)
				{
					$scope.yearlySummary.push({
						"monthName": response.yearlyListDtos[i].monthName,
						"credit": parseFloat(response.yearlyListDtos[i].credit).toFixed(2),
						"cash": parseFloat(response.yearlyListDtos[i].cash).toFixed(2),
						"check": parseFloat(response.yearlyListDtos[i].check).toFixed(2),
						"tax": parseFloat(response.yearlyListDtos[i].tax).toFixed(2),
						"discount": parseFloat(response.yearlyListDtos[i].discount).toFixed(2),
						"returnAmount": parseFloat(response.yearlyListDtos[i].returnAmount).toFixed(2),
						"profit": parseFloat(response.yearlyListDtos[i].profit).toFixed(2),
						"marginPercentage": parseFloat(response.yearlyListDtos[i].marginPercentage).toFixed(2),
						"total": parseFloat(response.yearlyListDtos[i].total).toFixed(2),
						"monthAvg": parseFloat(response.yearlyListDtos[i].monthAvg).toFixed(2),
						"cost": parseFloat(response.yearlyListDtos[i].cost).toFixed(2),
						"retail": parseFloat(response.yearlyListDtos[i].retail).toFixed(2),
						"noOfTrans": parseFloat(response.yearlyListDtos[i].noOfTrans).toFixed(2),
						"avgBasketSize": parseFloat(response.yearlyListDtos[i].avgBasketSize).toFixed(2)


					});
				}
				$scope.yearlySummary.push({
				"monthName": "Total",
				"credit": parseFloat(response.finalTotalForReportsDtos[0].totalCredit).toFixed(2),
				"cash": parseFloat(response.finalTotalForReportsDtos[0].totalCash).toFixed(2),
				"check": parseFloat(response.finalTotalForReportsDtos[0].totalCheck).toFixed(2),
				"tax": parseFloat(response.finalTotalForReportsDtos[0].totalTax).toFixed(2),
				"discount": parseFloat(response.finalTotalForReportsDtos[0].totalDiscount).toFixed(2),
				"returnAmount": parseFloat(response.finalTotalForReportsDtos[0].totalReturn).toFixed(2),
				"profit": parseFloat(response.finalTotalForReportsDtos[0].totalProfit).toFixed(2),
				"marginPercentage": parseFloat(response.finalTotalForReportsDtos[0].avgMargin).toFixed(2),
				"total": parseFloat(response.finalTotalForReportsDtos[0].grandTotal).toFixed(2),
				"monthAvg": 0.0,
				"cost": 0.0,
				"retail": 0.0,
				"noOfTrans": parseFloat(response.finalTotalForReportsDtos[0].noOfTrans).toFixed(2),
				"avgBasketSize": parseFloat(response.finalTotalForReportsDtos[0].avgBasketSize).toFixed(2)});
			}
			//$scope.yearlySummary = response.yearlyListDtos;

		}
		function onYearlyError(response)
		{
			
		}

		$scope.loadSalesMonthlyData = function(month)
		{
			var startDate,endDate;
			var years = getCurrentandPreviousYear().split("-");
			if(month == 'Jan')
			{
				startDate = years[0]+'-01-01 00:00:000';
				endDate = years[0]+'-01-31 23:59:59';
			}
			else if(month == 'Feb')
			{
				startDate = years[0]+'-02-01 00:00:000';
				endDate = years[0]+'-02-31 23:59:59';
			}
			else if(month == 'Mar')
			{
				startDate = years[0]+'-03-01 00:00:000';
				endDate = years[0]+'-03-31 23:59:59';
			}
			else if(month == 'Apr')
			{
				startDate = years[0]+'-04-01 00:00:000';
				endDate = years[0]+'-04-31 23:59:59';
			}
			else if(month == 'May')
			{
				startDate = years[0]+'-05-01 00:00:000';
				endDate = years[0]+'-05-31 23:59:59';
			}
			else if(month == 'Jun')
			{
				startDate = years[0]+'-06-01 00:00:000';
				endDate = years[0]+'-06-31 23:59:59';
			}
			else if(month == 'Jul')
			{
				startDate = years[0]+'-07-01 00:00:000';
				endDate = years[0]+'-07-31 23:59:59';
			}
			else if(month == 'Aug')
			{
				startDate = years[0]+'-08-01 00:00:000';
				endDate = years[0]+'-08-31 23:59:59';
			}
			else if(month == 'Sep')
			{
				startDate = years[0]+'-09-01 00:00:000';
				endDate = years[0]+'-09-31 23:59:59';
			}
			else if(month == 'Oct')
			{
				startDate = years[0]+'-10-01 00:00:000';
				endDate = years[0]+'-10-31 23:59:59';
			}
			else if(month == 'Nov')
			{
				startDate = years[0]+'-11-01 00:00:000';
				endDate = years[0]+'-11-31 23:59:59';
			}
			else if(month == 'Dec')
			{
				startDate = years[0]+'-12-01 00:00:000';
				endDate = years[0]+'-12-31 23:59:59';
			}
			else
			{
				startDate = $filter('date')($scope.startTransDate, "yyyy-MM-dd")+" 00:00:00";
				endDate = $filter('date')($scope.endTransDate, "yyyy-MM-dd")+" 23:59:59";
			}
			var url='http://localhost:8080/getMonthlyTransactionDetails?startDate='+startDate+'&endDate='+endDate;
			dataService.Get(url,onMonthlySucces,onMonthlyError,'application/json','application/json');
			//onMonthlySucces('');
			
		}
		$scope.applyFilterMonthly = function()
		{
			$scope.loadSalesMonthlyData('');
		};
		function onMonthlySucces(response)
		{
			if(response.monthDtos !== '' && response.monthDtos !== null)
			{
				$scope.monthlySummary = response.monthDtos;
				$scope.monthlySummary.push({
					"date": "Total",
					"credit": parseFloat(response.finalTotalForReportsDtos[0].totalCredit).toFixed(2),
					"cash": parseFloat(response.finalTotalForReportsDtos[0].totalCash).toFixed(2),
					"check": parseFloat(response.finalTotalForReportsDtos[0].totalCheck).toFixed(2),
					"tax": parseFloat(response.finalTotalForReportsDtos[0].totalTax).toFixed(2),
					"discount": parseFloat(response.finalTotalForReportsDtos[0].totalDiscount).toFixed(2),
					"returnAmount": parseFloat(response.finalTotalForReportsDtos[0].totalReturn).toFixed(2),
					"profit": parseFloat(response.finalTotalForReportsDtos[0].totalProfit).toFixed(2),
					"marginPercentage": parseFloat(response.finalTotalForReportsDtos[0].avgMargin).toFixed(2),
					"total": parseFloat(response.finalTotalForReportsDtos[0].grandTotal).toFixed(2),
					"monthAvg": 0.0,
					"cost": 0.0,
					"retail": 0.0,
					"noOfTrans": parseFloat(response.finalTotalForReportsDtos[0].noOfTrans).toFixed(2),
					"avgBasketSize": parseFloat(response.finalTotalForReportsDtos[0].avgBasketSize).toFixed(2)});
			}


		}
		function onMonthlyError(response)
		{
			
		}
		function getCurrentDay () {
			  var now = new Date();
			  var year = "" + now.getFullYear();
			  var month = "" + (now.getMonth() + 1); if (month.length == 1) { month = "0" + month; }
			  var day = "" + now.getDate(); if (day.length == 1) { day = "0" + day; }
			 var  hour = "" + now.getHours(); if (hour.length == 1) { hour = "0" + hour; }
			  var minute = "" + now.getMinutes(); if (minute.length == 1) { minute = "0" + minute; }
			  var second = "" + now.getSeconds(); if (second.length == 1) { second = "0" + second; }
			  return year + "-" + month + "-" + day;
			}
		render();
		function getPreviousDay () {
			  var now = new Date();
			now.setDate(now.getDate() - 1);
			  var year = "" + now.getFullYear();
			  var month = "" + (now.getMonth() + 1); if (month.length == 1) { month = "0" + month; }
			  var day = "" + now.getDate(); if (day.length == 1) { day = "0" + day; }
			  var  hour = "" + now.getHours(); if (hour.length == 1) { hour = "0" + hour; }
			  var minute = "" + now.getMinutes(); if (minute.length == 1) { minute = "0" + minute; }
			  var second = "" + now.getSeconds(); if (second.length == 1) { second = "0" + second; }
				  return year + "-" + month + "-" + day ;

		}
		function getLast7Day () {
			var now = new Date();
			now.setDate(now.getDate() - 7);
			var year = "" + now.getFullYear();
			var month = "" + (now.getMonth() + 1); if (month.length == 1) { month = "0" + month; }
			var day = "" + now.getDate(); if (day.length == 1) { day = "0" + day; }
			var  hour = "" + now.getHours(); if (hour.length == 1) { hour = "0" + hour; }
			var minute = "" + now.getMinutes(); if (minute.length == 1) { minute = "0" + minute; }
			var second = "" + now.getSeconds(); if (second.length == 1) { second = "0" + second; }
			return year + "-" + month + "-" + day ;

		}
		function getlast6Months () {
			var now = new Date();
			var year = "" + now.getFullYear();
			var month = "" + (now.getMonth() - 5); if (month.length == 1) { month = "0" + month; }
			var day = "" + now.getDate(); if (day.length == 1) { day = "0" + day; }
			var  hour = "" + now.getHours(); if (hour.length == 1) { hour = "0" + hour; }
			var minute = "" + now.getMinutes(); if (minute.length == 1) { minute = "0" + minute; }
			var second = "" + now.getSeconds(); if (second.length == 1) { second = "0" + second; }
			return year + "-" + month + "-" + day ;

		}
		function getlast3Months () {
			var now = new Date();
			var year = "" + now.getFullYear();
			var month = "" + (now.getMonth() - 2); if (month.length == 1) { month = "0" + month; }
			var day = "" + now.getDate(); if (day.length == 1) { day = "0" + day; }
			var  hour = "" + now.getHours(); if (hour.length == 1) { hour = "0" + hour; }
			var minute = "" + now.getMinutes(); if (minute.length == 1) { minute = "0" + minute; }
			var second = "" + now.getSeconds(); if (second.length == 1) { second = "0" + second; }
			return year + "-" + month + "-" + day ;

		}
		function getlastMonth()
		{
			var now = new Date();
			var year = "" + now.getFullYear();
			var month = "" + (now.getMonth()); if (month.length == 1) { month = "0" + month; }

			return month ;
		}
		function getcurrentMonth()
		{
			var now = new Date();
			var year = "" + now.getFullYear();
			var month = "" + (now.getMonth()+1); if (month.length == 1) { month = "0" + month; }

			return month ;
		}
		function getcurrentYear () {
			var now = new Date();
			var year = "" + now.getFullYear();

			return year;
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
	}
		
})();