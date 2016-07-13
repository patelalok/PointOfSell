(function() {
	'use strict';

	angular.module('sampleApp').controller('CloseRegisterController', CloseRegisterController);

	CloseRegisterController.$inject = [ '$scope', '$rootScope', 'device.utility','GlobalVariable','$state','dataService'];

	function CloseRegisterController($scope, $rootScope, device,GlobalVariable,$state,dataService) {
		$scope.totalUser = 0;
		$scope.totalInValue = 0;
		function getClosingDetails(startDate,endDate)
		{
			var url="http://localhost:8080/getClosingDetails?startDate="+startDate+"&endDate="+endDate;
			dataService.Get(url,getClosingDetailsSuccessHandler,getClosingDtlsErrorHandler,'application/json','applicaiton/json');
			//getClosingDetailsSuccessHandler('');
		}
		function getClosingDetailsSuccessHandler(response)
		{
			$scope.getClosingDtls = response;
			/*$scope.getClosingDtls = [
			                         {
			                        	    "registerId": 0,
			                        	    "userIdClose": 1,
			                        	    "reportCash": 12,
			                        	    "reportCredit": 12,
			                        	    "reportTotalAmount": 24,
			                        	    "closeCash": 23,
			                        	    "closeCredit": 23,
			                        	    "closeDate": "2016-06-22 12:23:56.0",
			                        	    "closeTotalAmount": 12,
			                        	    "differenceCash": 12,
			                        	    "differenceCredit": 21,
			                        	    "totalDifference": 12,
			                        	    "totalBusinessAmount": 321,
			                        	    "totalTax": 12,
			                        	    "totalDiscount": 12,
			                        	    "totalProfit": 2,
			                        	    "totalMarkup": 2,
			                        	    "registerStatus": null
			                        	  }
			                         ];*/
			$scope.systemDebit = $scope.getClosingDtls[0].reportCredit;
			$scope.systemCash = $scope.getClosingDtls[0].reportCash;
			$scope.sysCheck =''
				$scope.totalSys = $scope.getClosingDtls[0].reportTotalAmount;
			$scope.difDebit = $scope.getClosingDtls[0].differenceCredit;
			$scope.difCash = $scope.getClosingDtls[0].differenceCash;
			$scope.totalDiff = $scope.getClosingDtls[0].totalDifference;
		}
		function getClosingDtlsErrorHandler(response)
		{
			
		}
		$scope.getUserDebit = function(value)
		{
			$scope.totalUser = parseFloat($scope.totalUser)+parseFloat(value); 
		};
		$scope.getUserCash = function(value)
		{
			$scope.totalUser = parseFloat($scope.totalUser)+parseFloat(value); 
		};
		$scope.getTotal = function(value,entText)
		{
			
			if(entText == 'hun')
				$scope.totalInValue = parseFloat($scope.totalInValue) + (parseFloat(value) * 100);
			else if(entText == 'fif')
				$scope.totalInValue = parseFloat($scope.totalInValue) + (parseFloat(value) * 50);
			else if(entText == 'twn')
				$scope.totalInValue = parseFloat($scope.totalInValue) + (parseFloat(value) * 20);
			else if(entText == 'ten')
				$scope.totalInValue = parseFloat($scope.totalInValue) + (parseFloat(value) * 10);
			else if(entText == 'fiv')
				$scope.totalInValue = parseFloat($scope.totalInValue) + (parseFloat(value) * 5);
			else if(entText == 'two')
				$scope.totalInValue = parseFloat($scope.totalInValue) + (parseFloat(value) * 2);
			else if(entText == 'one')
				$scope.totalInValue = parseFloat($scope.totalInValue) + (parseFloat(value) * 1);
		};
		$scope.closeRegister = function()
		{
			var request = {
				    "userIdClose": 1,
				    "reportCash": $scope.systemCash,
				    "reportCredit": $scope.systemDebit,
				    "reportTotalAmount":$scope.totalSys,
				    "closeCash": $scope.userCash,
				    "closeCredit":$scope.userDebit,
				    "closeDate": "2016-06-23 12:23:56.0",
				    "closeTotalAmount": $scope.totalUser,
				    "differenceCash": $scope.difCash,
				    "differenceCredit": $scope.difDebit,
				    "totalDifference": $scope.totalDiff,
				    "totalBusinessAmount": 321,
				    "totalTax": 12,
				    "totalDiscount": 12,
				    "totalProfit": 2,
				    "totalMarkup": 2,
				    "registerStatus": null
				  };
			var url="http://localhost:8080/addClosingDetails";
			dataService.Post(url,request,getSuccessAddhandler,getErrorAddHandler,'application/json','application/json');
			//getSuccessAddhandler('');

		};
		function getSuccessAddhandler(response)
		{
			
		};
		function getErrorAddHandler(response)
		{
			
		};
		function render()
		{
			var startDate = js_yyyy_mm_dd_hh_mm_ss()+''+' 00:00:00';
			var endDate = js_yyyy_mm_dd_hh_mm_ss()+''+' 23:59:59';
			getClosingDetails(startDate,endDate);
			getPaidOutDetails(startDate,endDate);
		}
		function getPaidOutDetails(startDate,endDate)
		{
			var url=":http://localhost:8080/getPaidOut?startDate="+startDate+"&endDate="+endDate;
			dataService.Get(url,getaddPaidSuccessHandler,getaddpaidErrorHandler,'application/json','applicaiton/json');
			//getaddPaidSuccessHandler('');
		}
		function getaddPaidSuccessHandler(response)
		{
			$scope.paidOutDtls = response;
		/*	$scope.paidOutDtls = [
			                      {
			                    	    "paidOut1": 1,
			                    	    "paidOut2": 2,
			                    	    "paidOut3": 3,
			                    	    "reason1": "asd",
			                    	    "reason2": "asd",
			                    	    "reason3": "asdas",
			                    	    "paidOutDate": "2016-06-22 10:11:43.0"
			                    	  }
			                    	];*/
			$scope.amount1 = $scope.paidOutDtls[0].paidOut1;
			$scope.amount2 = $scope.paidOutDtls[0].paidOut2;
			$scope.amount3 = $scope.paidOutDtls[0].paidOut3;
			$scope.reason1 = $scope.paidOutDtls[0].reason1;
			$scope.reason2 = $scope.paidOutDtls[0].reason2;
			$scope.reason3 = $scope.paidOutDtls[0].reason3;
		}
		function getaddpaidErrorHandler(response){
			
		}
		$scope.addPaidOut = function()
		{
			var request ={
			    "paidOut1": $scope.amount1,
			    "paidOut2": $scope.amount2,
			    "paidOut3": $scope.amount3,
			    "reason1": $scope.reason1,
			    "reason2": $scope.reason2,
			    "reason3": $scope.reason3,
			    "paidOutDate": js_yyyy_mm_dd_hh_mm_ss()
			  };
			var url="http://localhost:8080/addPaidOut";
			dataService.Post(url,request,addPaidSuccessHandler,addPaidErrorHandler,'application/json','application/json');
			//addPaidSuccessHandler('');
			
		};
		function addPaidSuccessHandler(response)
		{
			
		}
		function addPaidErrorHandler(response)
		{
			
		}
		render();
		function js_yyyy_mm_dd_hh_mm_ss () {
			  var now = new Date();
			  var year = "" + now.getFullYear();
			  var month = "" + (now.getMonth() + 1); if (month.length == 1) { month = "0" + month; }
			  var day = "" + now.getDate(); if (day.length == 1) { day = "0" + day; }
			 var  hour = "" + now.getHours(); if (hour.length == 1) { hour = "0" + hour; }
			  var minute = "" + now.getMinutes(); if (minute.length == 1) { minute = "0" + minute; }
			  var second = "" + now.getSeconds(); if (second.length == 1) { second = "0" + second; }
			  return year + "-" + month + "-" + day ;
			}
	}
})();