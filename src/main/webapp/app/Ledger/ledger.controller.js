(function() {
	'use strict';

	angular.module('sampleApp').controller('LedgerController', LedgerController);

	LedgerController.$inject = [ '$scope', '$rootScope', 'device.utility','GlobalVariable','DialogFactory','dataService','$window'];

	function LedgerController($scope, $rootScope, device ,GlobalVariable,DialogFactory,dataService,$window) 
	{

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
		function loadSalesHistoryData()
		{
			var url =' http://localhost:8080/getSalesHistory?startDate=2016-06-16 20:02:53&endDate=2016-06-19 13:15:06';
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
			$scope.filterVlaue = value;
		};
		$scope.checkfilterValue = function()
		{
			if(($scope.customerPhone == undefined && $scope.receiptNumber == undefined) || ($scope.customerPhone == "" && $scope.receiptNumber == "")
					||($scope.customerPhone == undefined && $scope.receiptNumber == "")||($scope.customerPhone == "" && $scope.receiptNumber == undefined))
			{
				$scope.filterVlaue= '';
			}
		};
		
		function getSalesHistorySuccessHandler(response)
		{
			$scope.salesHistory =  response;/*[
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
			getPrintSuccessHandler('');
			
		};
		function getPrintSuccessHandler(response)
		{
			$scope.receiptData = response;/*[
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
			$window.print();
		}
		function getPrintErrorHandler(response)
		{
			
		}
		$scope.navigateToReturnPage = function(transactionDate,transactionCompId)
		{
			var request = new Object();
			request.transactionDate = transactionDate;
			request.transactionCompId = transactionCompId;
			request = JSON.stringify(request);
			var url="http://localhost:8080/";
			dataService.Post(url,request,getReturnsSuccessHandler,getReturnsErrorHandler,"application/json","application/json");

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
			$scope.startDate = moment();
			/** Options for the date picker directive * */
			$scope.dateRangeOptions = {
				startDate : moment(),
				showDropdowns : true,
				format : 'MM/DD/YYYY',
				singleDatePicker : true
			};
			loadSalesHistoryData();
		}
		render();
	}
		
})();