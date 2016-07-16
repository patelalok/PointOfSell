(function() {
	'use strict';

	angular.module('sampleApp').controller('HomeController', HomeController);

	HomeController.$inject = [ '$scope', '$rootScope', 'device.utility','GlobalVariable','DialogFactory','dataService','$window','$filter','$timeout','RestrictedCharacter.Types'];

	function HomeController($scope, $rootScope, device ,GlobalVariable,DialogFactory,dataService,$window,$filter,$timeout,restrictCharacter) 
	{
		GlobalVariable.isLoginPage = false;
		$scope.restrictCharacter=restrictCharacter;
		$scope.totalRevenueData=[];
		$scope.revData=[];
		$scope.prevRevData=[];
		$scope.cashData=[];
		$scope.cData=[];$scope.cDataPrev=[];
		$scope.creditData=[];
		$scope.crData =[];$scope.crDataPrev=[];
		$scope.profitData=[];
		$scope.pData=[];$scope.pDataPrev=[];
		$scope.taxData1=[];
		$scope.taxData=[];$scope.taxDataPrev=[];;
		$scope.discData1=[];
		$scope.discData=[];$scope.discDataPrev=[];
		$scope.transData=[];
		$scope.nData=[];$scope.nDataPrev=[];
		$scope.basketData=[];
		$scope.nvgData=[];$scope.nvgDataPrev=[];
	    $scope.series = ['2016', '2015'];
	    $scope.onClick = function (points, evt) {
	      console.log(points, evt);
	    };
	    $scope.onHover = function (points) {
	      if (points.length > 0) {
	        //console.log('Point', points[0].value);
	      } else {
	        //console.log('No point');
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
			if($scope.monthlyPrevResponse == [] || $scope.monthlyPrevResponse.length ==0)
			{
				for(var i=0;i<12;i++)
				{
					$scope.monthlyPrevResponse[i].yearlyListDtos[0].total;
					$scope.monthlyPrevResponse[i].yearlyListDtos[0].credit;
					$scope.monthlyPrevResponse[i].yearlyListDtos[0].cash;
					$scope.monthlyPrevResponse[i].yearlyListDtos[0].tax;
					$scope.monthlyPrevResponse[i].yearlyListDtos[0].discount;
					$scope.monthlyPrevResponse[i].yearlyListDtos[0].profit;
					$scope.monthlyPrevResponse[i].yearlyListDtos[0].noOfTrans;
					$scope.monthlyPrevResponse[i].yearlyListDtos[0].avgBasketSize;
				}
			}	
			
			for(var i=0;i<12;i++)
			{
				$scope.revData.push($scope.monthlyResponse[i].yearlyListDtos[0].total);
				$scope.prevRevData.push($scope.monthlyPrevResponse[i].yearlyListDtos[0].total);
			}
			for(var i=0;i<12;i++)
			{
				$scope.cData.push($scope.monthlyResponse[i].yearlyListDtos[0].cash);
				$scope.cDataPrev.push($scope.monthlyPrevResponse[i].yearlyListDtos[0].cash);
			}
			for(var i=0;i<12;i++)
			{
				$scope.crData.push($scope.monthlyResponse[i].yearlyListDtos[0].credit);
				$scope.crDataPrev.push($scope.monthlyPrevResponse[i].yearlyListDtos[0].credit);
			}
			for(var i=0;i<12;i++)
			{
				$scope.pData.push($scope.monthlyResponse[i].yearlyListDtos[0].profit);
				$scope.pDataPrev.push($scope.monthlyPrevResponse[i].yearlyListDtos[0].profit);
			}
			for(var i=0;i<12;i++)
			{
				$scope.taxData1.push($scope.monthlyResponse[i].yearlyListDtos[0].tax);
				$scope.taxDataPrev.push($scope.monthlyPrevResponse[i].yearlyListDtos[0].tax);
			}
			for(var i=0;i<12;i++)
			{
				$scope.discData1.push($scope.monthlyResponse[i].yearlyListDtos[0].discount);
				$scope.discDataPrev.push($scope.monthlyPrevResponse[i].yearlyListDtos[0].discount);
			}
			for(var i=0;i<12;i++)
			{
				$scope.nData.push($scope.monthlyResponse[i].yearlyListDtos[0].noOfTrans);
				$scope.nDataPrev.push($scope.monthlyPrevResponse[i].yearlyListDtos[0].noOfTrans);
			}
			for(var i=0;i<12;i++)
			{
				$scope.nvgData.push($scope.monthlyResponse[i].yearlyListDtos[0].avgBasketSize);
				$scope.nvgDataPrev.push($scope.monthlyPrevResponse[i].yearlyListDtos[0].avgBasketSize);
			}	
			
			$scope.totalRevenueData.push($scope.revData);$scope.totalRevenueData.push($scope.prevRevData);
			$scope.cashData.push($scope.cData);$scope.cashData.push($scope.cDataPrev);
			$scope.creditData.push($scope.crData);$scope.creditData.push($scope.crDataPrev);
			$scope.profitData.push($scope.pData);$scope.profitData.push($scope.pDataPrev);
			$scope.taxData.push($scope.taxData1);$scope.taxData.push($scope.taxDataPrev);
			$scope.discData.push($scope.discData1);$scope.discData.push($scope.discDataPrev);
			$scope.transData.push($scope.nData);$scope.transData.push($scope.nDataPrev);
			$scope.basketData.push($scope.nvgData);$scope.basketData.push($scope.nvgDataPrev);
			
			
		}
		function getReportsMonthlyPrevErrorHandler()
		{
			
		}
		function getReportsMonthlySuccessHandler(response)
		{
			
			$scope.monthlyResponse = response;
			$scope.monthlyResponse = GlobalVariable.reportResponse;
			var urlPreviuos = "http://localhost:8080/getYearlyTransactionDetails?startDate="+$scope.previousStartDate+"&endDate="+$scope.previousEndDate;
			dataService.Get(urlPreviuos,getReportsMonthlyPrevSuccessHandler,getReportsMonthlyPrevErrorHandler,'application/json','application/json');
			
			
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