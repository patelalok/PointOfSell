(function() {
	'use strict';

	angular.module('sampleApp').controller('HomeController', HomeController);

	HomeController.$inject = [ '$scope', '$rootScope', 'device.utility','GlobalVariable','DialogFactory','dataService','$window','$filter','$timeout','RestrictedCharacter.Types'];

	function HomeController($scope, $rootScope, device ,GlobalVariable,DialogFactory,dataService,$window,$filter,$timeout,restrictCharacter) 
	{
		GlobalVariable.isLoginPage = false;
		$scope.restrictCharacter=restrictCharacter;	
	    $scope.series = ['2016', '2015'];
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
			var years = getCurrentandPreviousYear().split("-");
			var currentStartDate =years[0]+"-01-01 00:00:00";
			var currentEndDate =years[0]+"-12-31 23:59:59";
			$scope.previousStartDate = years[1]+"-01-01 00:00:00";
			$scope.previousEndDate = years[1]+"-12-31 23:59:59";
			
			if($scope.dashboardDrop == 'monthWiseDash')
			{
				$scope.labels = ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'June', 'July','Aug','Sept','Oct','Nov','Dec'];
				url = "http://localhost:8080/getYearlyTransactionDetails?startDate="+currentStartDate+"&endDate="+currentEndDate;
				
				dataService.Get(url,getReportsMonthlySuccessHandler,getReportsMonthlyErrorHandler,'application/json','application/json');
				//getReportsMonthlySuccessHandler('');
			}
			else
			{
				var currentDayStartDate = years[0]+"-"+getCurrentMonth()+"-01 00:00:00";
				var currentDayEndDate = years[0]+"-"+getCurrentMonth()+"-07 23:59:59";
				
				var previousDayStartDate = years[1]+"-"+getCurrentMonth()+"-01 00:00:00";
				var previousDayEndDate = years[1]+"-"+getCurrentMonth()+"-07 23:59:59";
			}	
		};
		function getReportsMonthlyPrevSuccessHandler(response)
		{
			$scope.monthlyPrevResponse = response;
				
			$scope.totalRevenueData= [[$scope.monthlyResponse[0].total,$scope.monthlyResponse[1].total,$scope.monthlyResponse[2].total,$scope.monthlyResponse[3].total,
			                           $scope.monthlyResponse[4].total,$scope.monthlyResponse[5].total,$scope.monthlyResponse[6].total,$scope.monthlyResponse[7].total,
			                           $scope.monthlyResponse[8].total,$scope.monthlyResponse[9].total,$scope.monthlyResponse[10].total,$scope.monthlyResponse[11].total],
			                           [$scope.monthlyPrevResponse[0].total,$scope.monthlyPrevResponse[1].total,$scope.monthlyPrevResponse[2].total,$scope.monthlyPrevResponse[3].total,
				                           $scope.monthlyPrevResponse[4].total,$scope.monthlyPrevResponse[5].total,$scope.monthlyPrevResponse[6].total,$scope.monthlyPrevResponse[7].total,
				                           $scope.monthlyPrevResponse[8].total,$scope.monthlyPrevResponse[9].total,$scope.monthlyPrevResponse[10].total,$scope.monthlyPrevResponse[11].total]];
		
		
			$scope.cashData=[[$scope.monthlyResponse[0].cash,$scope.monthlyResponse[1].cash,$scope.monthlyResponse[2].cash,$scope.monthlyResponse[3].cash,
	                           $scope.monthlyResponse[4].cash,$scope.monthlyResponse[5].cash,$scope.monthlyResponse[6].cash,$scope.monthlyResponse[7].cash,
	                           $scope.monthlyResponse[8].cash,$scope.monthlyResponse[9].cash,$scope.monthlyResponse[10].cash,$scope.monthlyResponse[11].cash],
	                           [$scope.monthlyPrevResponse[0].cash,$scope.monthlyPrevResponse[1].cash,$scope.monthlyPrevResponse[2].cash,$scope.monthlyPrevResponse[3].cash,
		                           $scope.monthlyPrevResponse[4].cash,$scope.monthlyPrevResponse[5].cash,$scope.monthlyPrevResponse[6].cash,$scope.monthlyPrevResponse[7].cash,
		                           $scope.monthlyPrevResponse[8].cash,$scope.monthlyPrevResponse[9].cash,$scope.monthlyPrevResponse[10].cash,$scope.monthlyPrevResponse[11].cash]];
			
			$scope.creditData=[[$scope.monthlyResponse[0].credit,$scope.monthlyResponse[1].credit,$scope.monthlyResponse[2].credit,$scope.monthlyResponse[3].credit,
		                           $scope.monthlyResponse[4].credit,$scope.monthlyResponse[5].credit,$scope.monthlyResponse[6].credit,$scope.monthlyResponse[7].credit,
		                           $scope.monthlyResponse[8].credit,$scope.monthlyResponse[9].credit,$scope.monthlyResponse[10].credit,$scope.monthlyResponse[11].credit],
		                           [$scope.monthlyPrevResponse[0].credit,$scope.monthlyPrevResponse[1].credit,$scope.monthlyPrevResponse[2].credit,$scope.monthlyPrevResponse[3].credit,
			                           $scope.monthlyPrevResponse[4].credit,$scope.monthlyPrevResponse[5].credit,$scope.monthlyPrevResponse[6].credit,$scope.monthlyPrevResponse[7].credit,
			                           $scope.monthlyPrevResponse[8].credit,$scope.monthlyPrevResponse[9].credit,$scope.monthlyPrevResponse[10].credit,$scope.monthlyPrevResponse[11].credit]];
			
			$scope.profitData=[[$scope.monthlyResponse[0].profit,$scope.monthlyResponse[1].profit,$scope.monthlyResponse[2].profit,$scope.monthlyResponse[3].profit,
		                           $scope.monthlyResponse[4].profit,$scope.monthlyResponse[5].profit,$scope.monthlyResponse[6].profit,$scope.monthlyResponse[7].profit,
		                           $scope.monthlyResponse[8].profit,$scope.monthlyResponse[9].profit,$scope.monthlyResponse[10].profit,$scope.monthlyResponse[11].profit],
		                           [$scope.monthlyPrevResponse[0].profit,$scope.monthlyPrevResponse[1].profit,$scope.monthlyPrevResponse[2].profit,$scope.monthlyPrevResponse[3].profit,
			                           $scope.monthlyPrevResponse[4].profit,$scope.monthlyPrevResponse[5].profit,$scope.monthlyPrevResponse[6].profit,$scope.monthlyPrevResponse[7].profit,
			                           $scope.monthlyPrevResponse[8].profit,$scope.monthlyPrevResponse[9].profit,$scope.monthlyPrevResponse[10].profit,$scope.monthlyPrevResponse[11].profit]];
			
			$scope.taxData=[[$scope.monthlyResponse[0].tax,$scope.monthlyResponse[1].tax,$scope.monthlyResponse[2].tax,$scope.monthlyResponse[3].tax,
	                           $scope.monthlyResponse[4].tax,$scope.monthlyResponse[5].tax,$scope.monthlyResponse[6].tax,$scope.monthlyResponse[7].tax,
	                           $scope.monthlyResponse[8].tax,$scope.monthlyResponse[9].tax,$scope.monthlyResponse[10].tax,$scope.monthlyResponse[11].tax],
	                           [$scope.monthlyPrevResponse[0].tax,$scope.monthlyPrevResponse[1].tax,$scope.monthlyPrevResponse[2].tax,$scope.monthlyPrevResponse[3].tax,
		                           $scope.monthlyPrevResponse[4].tax,$scope.monthlyPrevResponse[5].tax,$scope.monthlyPrevResponse[6].tax,$scope.monthlyPrevResponse[7].tax,
		                           $scope.monthlyPrevResponse[8].tax,$scope.monthlyPrevResponse[9].tax,$scope.monthlyPrevResponse[10].tax,$scope.monthlyPrevResponse[11].tax]];
			
			$scope.discData=[[$scope.monthlyResponse[0].discount,$scope.monthlyResponse[1].discount,$scope.monthlyResponse[2].discount,$scope.monthlyResponse[3].discount,
	                           $scope.monthlyResponse[4].discount,$scope.monthlyResponse[5].discount,$scope.monthlyResponse[6].discount,$scope.monthlyResponse[7].discount,
	                           $scope.monthlyResponse[8].discount,$scope.monthlyResponse[9].discount,$scope.monthlyResponse[10].discount,$scope.monthlyResponse[11].discount],
	                           [$scope.monthlyPrevResponse[0].discount,$scope.monthlyPrevResponse[1].discount,$scope.monthlyPrevResponse[2].discount,$scope.monthlyPrevResponse[3].discount,
		                           $scope.monthlyPrevResponse[4].discount,$scope.monthlyPrevResponse[5].discount,$scope.monthlyPrevResponse[6].discount,$scope.monthlyPrevResponse[7].discount,
		                           $scope.monthlyPrevResponse[8].discount,$scope.monthlyPrevResponse[9].discount,$scope.monthlyPrevResponse[10].discount,$scope.monthlyPrevResponse[11].discount]];
			
			$scope.transData=[[$scope.monthlyResponse[0].noOfTrans,$scope.monthlyResponse[1].noOfTrans,$scope.monthlyResponse[2].noOfTrans,$scope.monthlyResponse[3].noOfTrans,
	                           $scope.monthlyResponse[4].noOfTrans,$scope.monthlyResponse[5].noOfTrans,$scope.monthlyResponse[6].noOfTrans,$scope.monthlyResponse[7].noOfTrans,
	                           $scope.monthlyResponse[8].noOfTrans,$scope.monthlyResponse[9].noOfTrans,$scope.monthlyResponse[10].noOfTrans,$scope.monthlyResponse[11].noOfTrans],
	                           [$scope.monthlyPrevResponse[0].noOfTrans,$scope.monthlyPrevResponse[1].noOfTrans,$scope.monthlyPrevResponse[2].noOfTrans,$scope.monthlyPrevResponse[3].noOfTrans,
		                           $scope.monthlyPrevResponse[4].noOfTrans,$scope.monthlyPrevResponse[5].noOfTrans,$scope.monthlyPrevResponse[6].noOfTrans,$scope.monthlyPrevResponse[7].noOfTrans,
		                           $scope.monthlyPrevResponse[8].noOfTrans,$scope.monthlyPrevResponse[9].noOfTrans,$scope.monthlyPrevResponse[10].noOfTrans,$scope.monthlyPrevResponse[11].noOfTrans]];
			
			$scope.basketData=[[$scope.monthlyResponse[0].avgBasketSize,$scope.monthlyResponse[1].avgBasketSize,$scope.monthlyResponse[2].avgBasketSize,$scope.monthlyResponse[3].avgBasketSize,
	                           $scope.monthlyResponse[4].avgBasketSize,$scope.monthlyResponse[5].avgBasketSize,$scope.monthlyResponse[6].avgBasketSize,$scope.monthlyResponse[7].avgBasketSize,
	                           $scope.monthlyResponse[8].avgBasketSize,$scope.monthlyResponse[9].avgBasketSize,$scope.monthlyResponse[10].avgBasketSize,$scope.monthlyResponse[11].avgBasketSize],
	                           [$scope.monthlyPrevResponse[0].avgBasketSize,$scope.monthlyPrevResponse[1].avgBasketSize,$scope.monthlyPrevResponse[2].avgBasketSize,$scope.monthlyResponse[3].avgBasketSize,
		                           $scope.monthlyPrevResponse[4].avgBasketSize,$scope.monthlyPrevResponse[5].avgBasketSize,$scope.monthlyPrevResponse[6].avgBasketSize,$scope.monthlyResponse[7].avgBasketSize,
		                           $scope.monthlyPrevResponse[8].avgBasketSize,$scope.monthlyPrevResponse[9].avgBasketSize,$scope.monthlyPrevResponse[10].avgBasketSize,$scope.monthlyResponse[11].avgBasketSize]];
		
		}
		function getReportsMonthlyPrevErrorHandler()
		{
			
		}
		function getReportsMonthlySuccessHandler(response)
		{
			$scope.monthlyResponse = response;
				var urlPreviuos = "http://localhost:8080/getYearlyTransactionDetails?startDate="+$scope.previousStartDate+"&endDate="+$scope.previousEndDate;
			dataService.Get(urlPreviuos,getReportsMonthlyPrevSuccessHandler,getReportsMonthlyPrevErrorHandler,'application/json','application/json');
			//getReportsMonthlyPrevSuccessHandler('');
			
		}
		function getReportsMonthlyErrorHandler(response)
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