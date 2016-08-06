(function() {
	'use strict';

	angular.module('sampleApp').controller('clockPopupController', clockPopupController);

	clockPopupController.$inject = [ '$scope', '$rootScope', 'device.utility','GlobalVariable','DialogFactory','modalService','dataService','$state','RestrictedCharacter.Types'];

	function clockPopupController($scope, $rootScope, device ,GlobalVariable,DialogFactory,modalService,dataService,$state,restrictCharacter)
	{
		$scope.closePopup = function()
		{
			DialogFactory.close(true);
		};
		function loadHistoryData()
		{
			var url =' http://localhost:8080/getUserClockIn?username='+GlobalVariable.usernameCust;
			dataService.Get(url,geClockHistorySuccessHandler,getClockHistroyErrorHandler,"application/json","application/json");
			
		}
		function geClockHistorySuccessHandler(response)
		{
			$scope.getClockHistory = response;
			
		}
		function getClockHistroyErrorHandler(response)
		{
			
		}
		function render()
		{
			loadHistoryData();
		}
		render();
	}
		
})();