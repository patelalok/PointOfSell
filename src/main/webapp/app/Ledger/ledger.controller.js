(function() {
	'use strict';

	angular.module('sampleApp').controller('LedgerController', LedgerController);

	LedgerController.$inject = [ '$scope', '$rootScope', 'device.utility','GlobalVariable','DialogFactory','dataService','$window','$filter','$timeout','RestrictedCharacter.Types','$state'];

	function LedgerController($scope, $rootScope, device ,GlobalVariable,DialogFactory,dataService,$window,$filter,$timeout,restrictCharacter,$state) 
	{
		$scope.restrictCharacter=restrictCharacter;	
		$scope.closePopup = function()
		{ 
			DialogFactory.close(true);
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
		function loadSalesHistoryData(startDate,endDate)
		{
			var url ='http://localhost:8080/getSalesHistory?startDate='+startDate+'&endDate='+endDate;
			dataService.Get(url,getSalesHistorySuccessHandler,getSalesHistroyErrorHandler,"application/json","application/json");
			
			
		}
		$scope.onDateSelected = function(startDate, endDate, label, element) {
			var receiptIndex = element.attr('data-receipt-index');
			element.find('span').eq(0).html(endDate.format('yyyy-MM-dd'));
		};
		$scope.filterSalesHistory =  function(value)
		{
			console.log(value);
			$scope.filterHistory = value;
		};
		$scope.checkfilterValue = function(value)
		{
			if(($scope.customerPhone == undefined && $scope.receiptNumber == undefined) || ($scope.customerPhone == "" && $scope.receiptNumber == "")
					||($scope.customerPhone == undefined && $scope.receiptNumber == "")||($scope.customerPhone == "" && $scope.receiptNumber == undefined))
			{
				$scope.filterHistory= '';
			}
			else
				$scope.filterHistory = value;
		};
		
		function getSalesHistorySuccessHandler(response)
		{
			$scope.salesHistory = response;
			GlobalVariable.histroyData = $scope.salesHistory;
		}
		function getSalesHistroyErrorHandler(response)
		{
			
		}
		$scope.print = function(id)
		{
			$scope.testPrint = "hi";
			var url="http://localhost:8080/getReceiptDetails?receiptId="+id;
			dataService.Get(url,getPrintSuccessHandler,getPrintErrorHandler,"application/json","application/json");
			
			
		};
		function getPrintSuccessHandler(response)
		{
			GlobalVariable.receiptData =response;
			GlobalVariable.isPrintPage = true;
			
			$timeout(function() {
				$window.print();
				GlobalVariable.isPrintPage = false;
			}, 2000);
			
		}
		function getPrintErrorHandler(response)
		{
			
		}
		$scope.navigateToReturnPage = function(transactionDate,transactionCompId)
		{
			/*var request = new Object();
			request.transactionDate = transactionDate;
			request.transactionCompId = transactionCompId;
			request = JSON.stringify(request);*/
			var url="http://localhost:8080/getReceiptDetails?receiptId="+transactionCompId;
			dataService.Get(url,getReturnsSuccessHandler,getReturnsErrorHandler,"application/json","application/json");
			

		};
		function getReturnsSuccessHandler(response)
		{
			GlobalVariable.getReturnDetails = response;
			
			GlobalVariable.returnProduct = true;
			$state.go('return');
		}
		function getReturnsErrorHandler(response)
		{
			
		}
/*		salesDate : moment($scope.salesDates[receiptIndex]).format("MM/DD/YYYY")
*/		function render()
		{
			//$scope.startDate = moment();
			/** Options for the date picker directive * */
			$scope.dateRangeOptions = {
				//startDate : moment(),
				showDropdowns : true,
				format : 'yyyy-MM-dd',
				singleDatePicker : true
			};
			$scope.startDate = $filter('date')(new Date(), "yyyy-MM-dd");
			$scope.endDate = $scope.startDate;
			var start = js_yyyy_mm_dd()+''+' 00:00:00';
			var end = js_yyyy_mm_dd()+''+' 23:59:59';
			loadSalesHistoryData(start,end);
		}
$scope.applyFilterHistory = function()
{
	//$scope.startDate = $filter('date')($scope.startDate, "yyyy-MM-dd HH:mm:ss");
	//$scope.endDate = $filter('date')($scope.endDate, "yyyy-MM-dd HH:mm:ss");
	loadSalesHistoryData($filter('date')($scope.startDate, "yyyy-MM-dd HH:mm:ss"),$filter('date')($scope.endDate, "yyyy-MM-dd HH:mm:ss"));
};
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
		
})();