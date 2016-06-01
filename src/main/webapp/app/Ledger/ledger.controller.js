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

		function render()
		{
			
		}
		render();
	}
		
})();