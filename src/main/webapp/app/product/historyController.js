(function() {
	'use strict';

	angular.module('sampleApp').controller('historyPopupController', historyPopupController);

	historyPopupController.$inject = [ '$scope', '$rootScope', 'device.utility','GlobalVariable','DialogFactory','modalService','dataService','$state','RestrictedCharacter.Types'];

	function historyPopupController($scope, $rootScope, device ,GlobalVariable,DialogFactory,modalService,dataService,$state,restrictCharacter)
	{
		$scope.closePopup = function()
		{
			DialogFactory.close(true);
		};
		function loadHistoryData()
		{
			var url ='http://localhost:8080/getProductHistory?productId='+GlobalVariable.productIdHistory;
			dataService.Get(url,getProductHistorySuccessHandler,getProductHistroyErrorHandler,"application/json","application/json");
			//getProductHistorySuccessHandler('');
		}
		function getProductHistorySuccessHandler(response)
		{
			$scope.getProductHistory = response;
			/*$scope.getProductHistory =[
			  {
			    "transactionLineItemId": 0,
			    "transactionCompId": 0, 
			    "productId": 6, 
			    "productNumber": "1000000021",
			    "quantity": 12,
			    "retail": 12.99,
			    "cost": 12.99, 
			    "discount": 0, 
			    "date": null, 
			    "productCount": "2", 
			    "productDescription": "test"  
			  },
			  {
			    "transactionLineItemId": 0,
			    "transactionCompId": 0,
			    "productId": 6,
			    "productNumber": "1000000021",
			    "quantity": 12,
			    "retail": 12.99,
			    "cost": 0,
			    "discount": 0,
			    "date": null,
			    "productCount": "2",
			    "productDescription": "test"
			  }
			]*/
		}
		function getProductHistroyErrorHandler(response)
		{
			
		}
		function render()
		{
			loadHistoryData();
		}
		render();
	}
		
})();