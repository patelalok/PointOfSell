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
			getProductHistorySuccessHandler('');
		}
		function getProductHistorySuccessHandler(response)
		{
			$scope.getProductHistory = response;
			
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