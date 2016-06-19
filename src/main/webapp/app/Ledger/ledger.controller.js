(function() {
	'use strict';

	angular.module('sampleApp').controller('LedgerController', LedgerController);

	LedgerController.$inject = [ '$scope', '$rootScope', 'device.utility','GlobalVariable','DialogFactory','dataService','$window'];

	function LedgerController($scope, $rootScope, device ,GlobalVariable,DialogFactory,dataService,$window) 
	{
/*$scope.ledgerData = [{
			
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
			
		];*/
		$scope.closePopup = function()
		{ 
			DialogFactory.close(true);
		};
		function loadSalesHistoryData()
		{
			var url =' http://localhost:8080/getSalesHistory?startDate=1000-01-0100:00:00';
			dataService.Get(url,getSalesHistorySuccessHandler,getSalesHistroyErrorHandler,"application/json","application/json");
			//getSalesHistorySuccessHandler('');
			
		}
		function getSalesHistorySuccessHandler(response)
		{
			$scope.salesHistory =response;
		}
		function getSalesHistroyErrorHandler(response)
		{
			
		}
		$scope.print = function()
		{
			$scope.testPrint = "hi";
			$window.print();
		};
		$scope.navigateToReturnPage = function(id)
		{
			
		};
		function render()
		{
			loadSalesHistoryData();
		}
		render();
	}
		
})();