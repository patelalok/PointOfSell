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
			//getSalesHistorySuccessHandler('');
			
		}
		$scope.onDateSelected = function(startDate, endDate, label, element) {
			var receiptIndex = element.attr('data-receipt-index');
			element.find('span').eq(0).html(endDate.format('MM/DD/YYYY'));
			$scope.salesDates[receiptIndex] = endDate.format('MM/DD/YYYY');
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
			$scope.salesHistory =  response;
			/*[
			                       {
			                    	    "transactionId": 13,
			                    	    "transactionDate": "1000-01-01 00:00:00.0",
			                    	    "totalAmount": 12,
			                    	    "tax": 12.99,
			                    	    "discount": 0,
			                    	    "customerPhoneno": 1234,
			                    	    "userId": 1,
			                    	    "paymentId": 1,
			                    	    "status": "completed",
			                    	    "paidAmount": 0,
			                    	    "changeAmount": 0
			                    	  },
			                    	  {
				                    	    "transactionId": 14,
				                    	    "transactionDate": "1000-01-01 00:00:00.0",
				                    	    "totalAmount": 12,
				                    	    "tax": 12.99,
				                    	    "discount": 0,
				                    	    "customerPhoneno": 123456789,
				                    	    "userId": 1,
				                    	    "paymentId": 1,
				                    	    "status": "completed",
				                    	    "paidAmount": 0,
				                    	    "changeAmount": 0
				                    	  }
			                    	];*/
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
			//getPrintSuccessHandler('');
			
		};
		function getPrintSuccessHandler(response)
		{
			GlobalVariable.receiptData = response;
			/*[
			                      {
			                    	    "transactionCompId": 96,
			                    	    "transactionDate": "2016-06-17 22:22:14.0",
			                    	    "totalAmount": 12.99,
			                    	    "tax": 0,
			                    	    "transactionDiscount": 0,
			                    	    "customerPhoneNo": "7707030801",
			                    	    "userId": 2,
			                    	    "paymentId": 1,
			                    	    "status": "completed",
			                    	    "paidAmount": 100,
			                    	    "changeAmount": 87.01,
			                    	    "username": "Alok",
			                    	    "paymentIdMulty": 0,
			                    	    "paidAmountMulty": 0,
			                    	    "transactionLineItemId": 0,
			                    	    "productId": 4,
			                    	    "quantity": 1,
			                    	    "retail": 15.99,
			                    	    "cost": 1.23,
			                    	    "productDiscount": 0,
			                    	    "paymentType": null,
			                    	    "storeDetails": null
			                    	  }
			                    	];*/
			$timeout(function() {
				$window.print();
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
			$state.go('sell');
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
				format : 'MM/DD/YYYY',
				singleDatePicker : true
			};
			$scope.startDate = $filter('date')(new Date(), "MM/dd/yyyy");
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