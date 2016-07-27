(function() {
	'use strict';

	angular.module('sampleApp').controller('CloseRegisterController', CloseRegisterController);

	CloseRegisterController.$inject = [ '$scope', '$rootScope', 'device.utility','GlobalVariable','$state','dataService','RestrictedCharacter.Types','$filter'];

	function CloseRegisterController($scope, $rootScope, device,GlobalVariable,$state,dataService,restrictCharacter,$filter) {
		$scope.totalUser = 0;
		$scope.totalInValue = 0;
		$scope.userCash = 0;
		$scope.userDebit = 0;
		$scope.userCheck = 0;
		$scope.difDebit =0;
		$scope.difCash =0;
		$scope.difCheck =0;
		$scope.totalDiff =0;
		$scope.restrictCharacter=restrictCharacter;
		function getClosingDetails(startDate,endDate)
		{
			var url="http://localhost:8080/getClosingDetails?startDate="+startDate+"&endDate="+endDate;
			dataService.Get(url,getClosingDetailsSuccessHandler,getClosingDtlsErrorHandler,'application/json','application/json');
			//getClosingDetailsSuccessHandler('');
		}
		function getClosingDetailsSuccessHandler(response)
		{
			$scope.getClosingDtls = response;

			if(response.length !== 0)
			{
				$scope.systemDebit = $scope.getClosingDtls[0].reportCredit;
				$scope.systemCash = $scope.getClosingDtls[0].reportCash;
				$scope.sysCheck =$scope.getClosingDtls[0].reportCheck;
				$scope.totalSys = $scope.getClosingDtls[0].reportTotalAmount;
			}

		}
		function getClosingDtlsErrorHandler(response)
		{
			
		}
		$scope.getUserDebit = function(value)
		{
			if($scope.userDebit == '')
				$scope.userDebit = 0;
			if($scope.userCash == '')
				$scope.userCash = 0;
			if($scope.userCheck == '')
				$scope.userCheck = 0;
			if($scope.userPaid == '')
				$scope.userPaid = 0;
			$scope.totalUser = parseFloat($scope.userDebit)+parseFloat($scope.userCash)+parseFloat($scope.userCheck)+parseFloat($scope.userPaid);
			$scope.difDebit = parseFloat($scope.userDebit)-parseFloat($scope.systemDebit);
			$scope.totalDiff = parseFloat($scope.totalUser)-parseFloat($scope.totalSys)
			if($scope.difDebit >0)
				$scope.debitColor = 'green';
			else if($scope.difDebit < 0)
				$scope.debitColor = 'red';
			else
				$scope.debitColor = 'black';


			if($scope.totalDiff >0)
				$scope.totalColor = 'green';
			else if($scope.totalDiff < 0)
				$scope.totalColor = 'red';
			else
				$scope.totalColor = 'black';
		};
		$scope.getUserCash = function(value)
		{
			if($scope.userDebit == '')
				$scope.userDebit = 0;
			if($scope.userCash == '')
				$scope.userCash = 0;
			if($scope.userCheck == '')
				$scope.userCheck = 0;
			if($scope.userPaid == '')
				$scope.userPaid = 0;
			$scope.totalUser = parseFloat($scope.userDebit)+parseFloat($scope.userCash)+parseFloat($scope.userCheck)+parseFloat($scope.userPaid);
			$scope.difCash = parseFloat($scope.userCash)-parseFloat($scope.systemCash);
			$scope.totalDiff = parseFloat($scope.totalUser)-parseFloat($scope.totalSys)
			if($scope.difCash >0)
				$scope.cashColor = 'green';
			else if($scope.difCash < 0)
				$scope.cashColor = 'red';
			else
				$scope.cashColor = 'black';


			if($scope.totalDiff >0)
				$scope.totalColor = 'green';
			else if($scope.totalDiff < 0)
				$scope.totalColor = 'red';
			else
				$scope.totalColor = 'black';
		};
		$scope.getUserPaid = function(value)
		{
			if($scope.userDebit == '')
				$scope.userDebit = 0;
			if($scope.userCash == '')
				$scope.userCash = 0;
			if($scope.userCheck == '')
				$scope.userCheck = 0;
			if($scope.userPaid == '')
				$scope.userPaid = 0;
			$scope.totalUser = parseFloat($scope.userDebit)+parseFloat($scope.userCash)+parseFloat($scope.userCheck)+parseFloat($scope.userPaid);
			$scope.difCheck = parseFloat($scope.userCheck)-parseFloat($scope.sysCheck);
			$scope.totalDiff = parseFloat($scope.totalUser)-parseFloat($scope.totalSys)

			if($scope.difPaid >0)
				$scope.paidColor = 'green';
			else if($scope.difPaid < 0)
				$scope.paidColor = 'red';
			else
				$scope.paidColor = 'black';

			if($scope.totalDiff >0)
				$scope.totalColor = 'green';
			else if($scope.totalDiff < 0)
				$scope.totalColor = 'red';
			else
				$scope.totalColor = 'black';
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
				    "closeDate": js_yyyy_mm_dd_hh_mm_ss1(),
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


		};
		function getSuccessAddhandler(response)
		{
			
		};
		function getErrorAddHandler(response)
		{
			
		};
		function render()
		{
			$scope.dateRangeOptions = {
				//startDate : moment(),
				showDropdowns : true,
				format : 'yyyy-MM-dd',
				singleDatePicker : true
			};
			var startDate = js_yyyy_mm_dd_hh_mm_ss()+''+' 00:00:00';
			var endDate = js_yyyy_mm_dd_hh_mm_ss()+''+' 23:59:59';
			$scope.startDate = $filter('date')(new Date(), "yyyy-MM-dd");
			getClosingDetails(startDate,endDate);
			getPaidOutDetails(startDate,endDate);
		}
		function getPaidOutDetails(startDate,endDate)
		{
			var url="http://localhost:8080/getPaidOut?startDate="+startDate+"&endDate="+endDate;
			dataService.Get(url,getaddPaidSuccessHandler,getaddpaidErrorHandler,'application/json','application/json');

		}
		$scope.openStartCalendar = function($event) {
			$event.preventDefault();
			$event.stopPropagation();
			$scope.openStart = true;
		};
		$scope.onDateSelected = function(startDate, endDate, label, element) {
			var receiptIndex = element.attr('data-receipt-index');
			element.find('span').eq(0).html(endDate.format('yyyy-MM-dd'));

		};
		$scope.changeDtls = function()
		{
			var start = $filter('date')($scope.startDate, "yyyy-MM-dd")+''+' 00:00:00';
			var end = $filter('date')($scope.startDate, "yyyy-MM-dd")+''+' 23:59:59';
			getPaidOutDetails(start,end);
			getClosingDetails(start,end);
		};
		function getaddPaidSuccessHandler(response)
		{
			$scope.getPaidOutDtls = response;
			/*$scope.totalPaidOut =0;
			for(var i=0;i<response.length;i++)
			{
				$scope.totalPaidOut = $scope.totalPaidOut + parseFloat(response[i].paidOutAmount);
			}*/
		}
		function getaddpaidErrorHandler(response){
			
		}
		$scope.editPaidOut = function(data)
		{
			var request = {
				"paidOutAmount": $scope.amount1,
				"reason":$scope.reason1,
				"paidOutId": data.paidOutId
			};
			request = JSON.stringify(request);
			var url='http://localhost:8080/editPaidOut';
			dataService.Post(url,request,onEditPaidSuccess,onEditPaidError,'application/json','application/json');
		};
		function onEditPaidSuccess(response)
		{
			var startDate = js_yyyy_mm_dd_hh_mm_ss()+''+' 00:00:00';
			var endDate = js_yyyy_mm_dd_hh_mm_ss()+''+' 23:59:59';
			getPaidOutDetails(startDate,endDate);
		}
		function onEditPaidError(response)
		{

		}
		$scope.addPaidOut = function()
		{
			var request ={

				"paidOutAmount": $scope.amount1,
				"reason":$scope.reason1,
				"paidOutDate": js_yyyy_mm_dd_hh_mm_ss()
			  };
			  request = JSON.stringify(request);
			var url="http://localhost:8080/addPaidOut";
			dataService.Post(url,request,addPaidSuccessHandler,addPaidErrorHandler,'application/json','application/json');

			
		};
		function addPaidSuccessHandler(response)
		{
			var startDate = js_yyyy_mm_dd_hh_mm_ss()+''+' 00:00:00';
			var endDate = js_yyyy_mm_dd_hh_mm_ss()+''+' 23:59:59';
			getPaidOutDetails(startDate,endDate);
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
		function js_yyyy_mm_dd_hh_mm_ss1 () {
			var now = new Date();
			var year = "" + now.getFullYear();
			var month = "" + (now.getMonth() + 1); if (month.length == 1) { month = "0" + month; }
			var day = "" + now.getDate(); if (day.length == 1) { day = "0" + day; }
			var  hour = "" + now.getHours(); if (hour.length == 1) { hour = "0" + hour; }
			var minute = "" + now.getMinutes(); if (minute.length == 1) { minute = "0" + minute; }
			var second = "" + now.getSeconds(); if (second.length == 1) { second = "0" + second; }
			return year + "-" + month + "-" + day + " " + hour + ":" + minute + ":" + second;
		}
	}
})();