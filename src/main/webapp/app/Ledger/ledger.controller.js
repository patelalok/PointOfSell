(function() {
	'use strict';

	angular.module('sampleApp').controller('LedgerController', LedgerController);

	LedgerController.$inject = [ '$scope', '$rootScope', 'device.utility','GlobalVariable','DialogFactory'];

	function LedgerController($scope, $rootScope, device ,GlobalVariable,DialogFactory) 
	{
$scope.ledgerData = [{
			
			"date":"26 April 2016",
			"user":"Bob",
			"receipt":45,
			"customer":"harish",
			"status":"Paked Return",
			"saleTotal":78.90
		},
		{
			"date":"26 April 2016",
			"user":"Bob",
			"receipt":45,
			"customer":"harish",
			"status":"Paked Return",
			"saleTotal":78.90
		},
		{
			"date":"26 April 2016",
			"user":"Bob",
			"receipt":45,
			"customer":"harish",
			"status":"Paked Return",
			"saleTotal":78.90
		},
		{
			"date":"26 April 2016",
			"user":"Bob",
			"receipt":45,
			"customer":"harish",
			"status":"Paked Return",
			"saleTotal":78.90
		},
		{
			"date":"26 April 2016",
			"user":"Bob",
			"receipt":45,
			"customer":"harish",
			"status":"Paked Return",
			"saleTotal":78.90
		}
			
		];
		$scope.closePopup = function()
		{ 
			DialogFactory.close(true);
		};
		function loadSalesHistoryData()
		{
			var url =' http://localhost:8080/getSalesHistory?startDate=1000-01-0100:00:00';
			dataService.Get(url,getSalesHistorySuccessHandler,getSalesHistroyErrorHandler,"application/json","application/json");
			
		}
		function getSalesHistorySuccessHandler(response)
		{
			$scope.salesHistory = [
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
			                    	  }
			                    	];
		}
		function getSalesHistroyErrorHandler(response)
		{
			
		}
		function render()
		{
			loadSalesHistoryData();
		}
		render();
	}
		
})();