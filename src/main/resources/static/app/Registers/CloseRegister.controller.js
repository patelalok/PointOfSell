(function() {
	'use strict';

	angular.module('sampleApp').controller('CloseRegisterController',
		CloseRegisterController);

	CloseRegisterController.$inject = [ '$scope', '$rootScope',
		'device.utility', 'GlobalVariable', '$state', 'dataService',
		'RestrictedCharacter.Types', '$filter', '$timeout', '$window' ];

	function CloseRegisterController($scope, $rootScope, device,
									 GlobalVariable, $state, dataService, restrictCharacter, $filter,
									 $timeout, $window) {
		$scope.totalUser = 0;
		$scope.totalInValue = 0;
		$scope.userCash = 0;
		$scope.userDebit = 0;
		$scope.userCheck = 0;
		$scope.difDebit = 0;
		$scope.difCash = 0;
		$scope.difCheck = 0;
		$scope.totalDiff = 0;
		$scope.maxDate = new Date();
		$scope.showCloseRegister = true;
		$scope.restrictCharacter = restrictCharacter;
		function getClosingDetails(startDate, endDate) {
			var url = "http://localhost:8080/getClosingDetails?startDate="
				+ startDate + "&endDate=" + endDate;
			dataService.Get(url, getClosingDetailsSuccessHandler,
				getClosingDtlsErrorHandler, 'application/json',
				'application/json');
			// getClosingDetailsSuccessHandler('');
		}
		function getClosingDetailsSuccessHandler(response) {
			$scope.getClosingDtls = response;

			if (response.length !== 0) {
				$scope.systemDebit = $scope.getClosingDtls[0].reportCredit;
				$scope.systemCash = $scope.getClosingDtls[0].reportCash;
				$scope.sysCheck = $scope.getClosingDtls[0].reportCheck;
				$scope.userCash = $scope.getClosingDtls[0].closeCash;
				$scope.userDebit = $scope.getClosingDtls[0].closeCredit;
				$scope.totalSys = parseFloat($scope.getClosingDtls[0].reportTotalAmount).toFixed(2);
				$scope.registerId = $scope.getClosingDtls[0].registerId;
				$scope.totalUser = $scope.getClosingDtls[0].closeTotalAmount;
				$scope.totalUser = parseFloat($scope.totalUser).toFixed(2);
				$scope.difCash = $scope.getClosingDtls[0].differenceCash;
				$scope.difDebit = $scope.getClosingDtls[0].differenceCredit;
				$scope.totalDiff = $scope.getClosingDtls[0].totalDifference;
				$scope.totalProfit = $scope.getClosingDtls[0].totalProfit;
				$scope.totalDisc = $scope.getClosingDtls[0].totalDiscount;
				$scope.totalTax = $scope.getClosingDtls[0].totalTax;
				$scope.totalMarkup = $scope.getClosingDtls[0].totalMarkup;
				$scope.totalBusinessAmount = $scope.getClosingDtls[0].totalBusinessAmount;
				$scope.netSales = parseFloat($scope.userCash)
					+ parseFloat($scope.userDebit)
					+ parseFloat($scope.userCheck)
					- parseFloat($scope.totalTax);
				$scope.dateTime = js_yyyy_mm_dd_hh_mm_ss1();
			} else {
				$scope.systemDebit = 0;
				$scope.systemCash = 0;
				$scope.sysCheck = 0;
				$scope.userCash = 0;
				$scope.userDebit = 0;
				$scope.totalSys = 0;
				$scope.registerId = 0;
				$scope.totalUser = 0;
				$scope.totalUser = 0;
				$scope.difCash = 0;
				$scope.difDebit = 0;
				$scope.totalDiff = 0;
				$scope.totalProfit = 0;
				$scope.totalDisc = 0;
				$scope.totalTax = 0;

			}

		}
		function getClosingDtlsErrorHandler(response) {

		}
		$scope.getUserDebit = function(value) {
			if ($scope.userDebit == '')
				$scope.userDebit = 0;
			if ($scope.userCash == '')
				$scope.userCash = 0;
			if ($scope.userCheck == '')
				$scope.userCheck = 0;

			$scope.totalUser = parseFloat($scope.userDebit)
				+ parseFloat($scope.userCash)
				+ parseFloat($scope.userCheck);
			$scope.totalUser = parseFloat($scope.totalUser).toFixed(2);
			$scope.difDebit = parseFloat($scope.userDebit)
				- parseFloat($scope.systemDebit);
			$scope.totalDiff = parseFloat($scope.totalUser)
				- parseFloat($scope.totalSys)
			if ($scope.difDebit > 0)
				$scope.debitColor = 'green';
			else if ($scope.difDebit < 0)
				$scope.debitColor = 'red';
			else
				$scope.debitColor = 'black';

			if ($scope.totalDiff > 0)
				$scope.totalColor = 'green';
			else if ($scope.totalDiff < 0)
				$scope.totalColor = 'red';
			else
				$scope.totalColor = 'black';
		};
		$scope.getUserCash = function(value) {
			if ($scope.userDebit == '')
				$scope.userDebit = 0;
			if ($scope.userCash == '')
				$scope.userCash = 0;
			if ($scope.userCheck == '')
				$scope.userCheck = 0;

			$scope.totalUser = parseFloat($scope.userDebit)
				+ parseFloat($scope.userCash)
				+ parseFloat($scope.userCheck);
			$scope.totalUser = parseFloat($scope.totalUser).toFixed(2);
			$scope.difCash = parseFloat($scope.userCash)
				- parseFloat($scope.systemCash);
			$scope.totalDiff = parseFloat($scope.totalUser)
				- parseFloat($scope.totalSys)
			if ($scope.difCash > 0)
				$scope.cashColor = 'green';
			else if ($scope.difCash < 0)
				$scope.cashColor = 'red';
			else
				$scope.cashColor = 'black';

			if ($scope.totalDiff > 0)
				$scope.totalColor = 'green';
			else if ($scope.totalDiff < 0)
				$scope.totalColor = 'red';
			else
				$scope.totalColor = 'black';
		};
		$scope.getUserCheck = function(value) {
			if ($scope.userDebit == '')
				$scope.userDebit = 0;
			if ($scope.userCash == '')
				$scope.userCash = 0;
			if ($scope.userCheck == '')
				$scope.userCheck = 0;

			$scope.totalUser = parseFloat($scope.userDebit)
				+ parseFloat($scope.userCash)
				+ parseFloat($scope.userCheck);
			$scope.totalUser = parseFloat($scope.totalUser).toFixed(2);
			$scope.difCheck = parseFloat($scope.userCheck)
				- parseFloat($scope.sysCheck);
			$scope.totalDiff = parseFloat($scope.totalUser)
				- parseFloat($scope.totalSys)

			if ($scope.difCheck > 0)
				$scope.checkColor = 'green';
			else if ($scope.difCheck < 0)
				$scope.checkColor = 'red';
			else
				$scope.checkColor = 'black';

			if ($scope.totalDiff > 0)
				$scope.totalColor = 'green';
			else if ($scope.totalDiff < 0)
				$scope.totalColor = 'red';
			else
				$scope.totalColor = 'black';
		};
		$scope.getTotal = function(value, entText) {

			if (entText == 'hun')
				$scope.totalInValue = parseFloat($scope.totalInValue)
					+ (parseFloat(value) * 100);
			else if (entText == 'fif')
				$scope.totalInValue = parseFloat($scope.totalInValue)
					+ (parseFloat(value) * 50);
			else if (entText == 'twn')
				$scope.totalInValue = parseFloat($scope.totalInValue)
					+ (parseFloat(value) * 20);
			else if (entText == 'ten')
				$scope.totalInValue = parseFloat($scope.totalInValue)
					+ (parseFloat(value) * 10);
			else if (entText == 'fiv')
				$scope.totalInValue = parseFloat($scope.totalInValue)
					+ (parseFloat(value) * 5);
			else if (entText == 'two')
				$scope.totalInValue = parseFloat($scope.totalInValue)
					+ (parseFloat(value) * 2);
			else if (entText == 'one')
				$scope.totalInValue = parseFloat($scope.totalInValue)
					+ (parseFloat(value) * 1);
		};
		$scope.closeRegister = function() {
			var request = {
				"userIdClose" : $scope.registerId,
				"reportCash" : parseFloat($scope.systemCash).toFixed(2),
				"reportCredit" : parseFloat($scope.systemDebit).toFixed(2),
				"reportTotalAmount" : parseFloat($scope.totalSys).toFixed(2),
				"closeCash" : parseFloat($scope.userCash).toFixed(2),
				"closeCredit" : parseFloat($scope.userDebit).toFixed(2),
				"closeDate" : js_yyyy_mm_dd_hh_mm_ss1(),
				"closeTotalAmount" : parseFloat($scope.totalUser).toFixed(2),
				"differenceCash" : parseFloat($scope.difCash).toFixed(2),
				"differenceCredit" : parseFloat($scope.difDebit).toFixed(2),
				"totalDifference" : parseFloat($scope.totalDiff).toFixed(2),
				"totalBusinessAmount" : $scope.totalBusinessAmount,
				"totalTax" : parseFloat($scope.totalTax).toFixed(2),
				"totalDiscount" : parseFloat($scope.totalDisc).toFixed(2),
				"totalProfit" : parseFloat($scope.totalProfit).toFixed(2),
				"totalMarkup" : $scope.totalMarkup,
				"registerStatus" : null,
				"registerId" : $scope.registerId
			};
			var url = "http://localhost:8080/addClosingDetails";
			dataService.Post(url, request, getSuccessAddhandler,
				getErrorAddHandler, 'application/json', 'application/json');

		};
		function getSuccessAddhandler(response) {
			var startDate = js_yyyy_mm_dd_hh_mm_ss() + '' + ' 00:00:00';
			var endDate = js_yyyy_mm_dd_hh_mm_ss() + '' + ' 23:59:59';
			getClosingDetails(startDate, endDate);
			$scope.addPaidOut();
		}
		;
		function getErrorAddHandler(response) {

		}
		;
		function render() {
			$scope.dateRangeOptions = {
				// startDate : moment(),
				showDropdowns : true,
				format : 'yyyy-MM-dd',
				singleDatePicker : true
			};
			var startDate = js_yyyy_mm_dd_hh_mm_ss() + '' + ' 00:00:00';
			var endDate = js_yyyy_mm_dd_hh_mm_ss() + '' + ' 23:59:59';
			$scope.startDate = $filter('date')(new Date(), "yyyy-MM-dd");
			getClosingDetails(startDate, endDate);
			getPaidOutDetails(startDate, endDate);
		}
		function getPaidOutDetails(startDate, endDate) {
			var url = "http://localhost:8080/getPaidOut?startDate=" + startDate
				+ "&endDate=" + endDate;
			dataService.Get(url, getaddPaidSuccessHandler,
				getaddpaidErrorHandler, 'application/json',
				'application/json');
			// getaddPaidSuccessHandler('');
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
		$scope.changeDtls = function() {
			var chnDt = $filter('date')($scope.startDate, "yyyy-MM-dd");
			var start = $filter('date')($scope.startDate, "yyyy-MM-dd") + ''
				+ ' 00:00:00';
			var end = $filter('date')($scope.startDate, "yyyy-MM-dd") + ''
				+ ' 23:59:59';
			if (chnDt < js_yyyy_mm_dd_hh_mm_ss()) {
				$scope.showCloseRegister = false;
			} else {
				$scope.showCloseRegister = true;
			}
			getPaidOutDetails(start, end);
			getClosingDetails(start, end);
		};
		function getaddPaidSuccessHandler(response) {
			// $scope.getPaidOutDtls = response;
			/*
			 * response=[ { "paidOutId": 13, "paidOutAmount1": 32.99,
			 * "paidOutAmount2": 23, "paidOutAmount3": 32, "paidOutReason1":
			 * "test", "paidOutReason2": null, "paidOutReason3": null,
			 * "paidOutDate": "2016-07-17 00:00:00.0", "sumPaidOut": 0 } ];
			 */
			if (response.length !== 0) {
				$scope.userPaid = response[0].paidOutAmount1;
				$scope.userReason = response[0].paidOutReason1;
				$scope.systemPaid = response[0].paidOutAmount2;
				$scope.sysReason = response[0].paidOutReason2;
				$scope.difPaid = response[0].paidOutAmount3;
				$scope.difReason = response[0].paidOutReason3;
				$scope.paidOutId = response[0].paidOutId;
				} else {
				$scope.userPaid = 0;
				$scope.userReason = '';
				$scope.systemPaid = 0;
				$scope.sysReason = '';
				$scope.difPaid = 0;
				$scope.difReason = '';
				$scope.paidOutId = '';
			}
			$scope.totalPaid = parseFloat($scope.userPaid)
				+ parseFloat($scope.systemPaid)
				+ parseFloat($scope.difPaid);
			$scope.netSales = parseFloat($scope.netSales)
				+ $scope.totalPaid;
			$scope.grossSales = (parseFloat($scope.userDebit)+parseFloat($scope.userCash)+parseFloat($scope.userCheck)).toFixed(2);


			/*
			 * $scope.totalPaidOut =0; for(var i=0;i<response.length;i++) {
			 * $scope.totalPaidOut = $scope.totalPaidOut +
			 * parseFloat(response[i].paidOutAmount); }
			 */
		}
		function getaddpaidErrorHandler(response) {

		}

		$scope.addPaidOut = function() {
			var request = {

				"paidOutAmount1" : $scope.userPaid,
				"paidOutReason1" : $scope.userReason,
				"paidOutAmount2" : $scope.systemPaid,
				"paidOutReason2" : $scope.sysReason,
				"paidOutAmount3" : $scope.difPaid,
				"paidOutReason3" : $scope.difReason,
				"paidOutId" : $scope.paidOutId,
				"paidOutDate" : js_yyyy_mm_dd_hh_mm_ss(),
				'sumPaidOut' : parseFloat($scope.userPaid)
				+ parseFloat($scope.systemPaid)
				+ parseFloat($scope.difPaid)
			};
			request = JSON.stringify(request);
			var url = "http://localhost:8080/addPaidOut";
			dataService
				.Post(url, request, addPaidSuccessHandler,
					addPaidErrorHandler, 'application/json',
					'application/json');

		};
		function addPaidSuccessHandler(response) {
			//sendEmail('alokpatel.au@gmail.com', "hi", "hello");
			var startDate = js_yyyy_mm_dd_hh_mm_ss() + '' + ' 00:00:00';
			var endDate = js_yyyy_mm_dd_hh_mm_ss() + '' + ' 23:59:59';
			getPaidOutDetails(startDate, endDate);
			// getClosingDetails(startDate,endDate);
		}
		function sendEmail(email, subject, body) {
			var link = "mailto:" + email + "?subject=New%20email "
				+ escape(subject) + "&body=" + escape(body);

			window.location.href = link;
		}
		function addPaidErrorHandler(response) {

		}
		render();
		function js_yyyy_mm_dd_hh_mm_ss() {
			var now = new Date();
			var year = "" + now.getFullYear();
			var month = "" + (now.getMonth() + 1);
			if (month.length == 1) {
				month = "0" + month;
			}
			var day = "" + now.getDate();
			if (day.length == 1) {
				day = "0" + day;
			}
			var hour = "" + now.getHours();
			if (hour.length == 1) {
				hour = "0" + hour;
			}
			var minute = "" + now.getMinutes();
			if (minute.length == 1) {
				minute = "0" + minute;
			}
			var second = "" + now.getSeconds();
			if (second.length == 1) {
				second = "0" + second;
			}
			return year + "-" + month + "-" + day;
		}
		function js_yyyy_mm_dd_hh_mm_ss1() {
			var now = new Date();
			var year = "" + now.getFullYear();
			var month = "" + (now.getMonth() + 1);
			if (month.length == 1) {
				month = "0" + month;
			}
			var day = "" + now.getDate();
			if (day.length == 1) {
				day = "0" + day;
			}
			var hour = "" + now.getHours();
			if (hour.length == 1) {
				hour = "0" + hour;
			}
			var minute = "" + now.getMinutes();
			if (minute.length == 1) {
				minute = "0" + minute;
			}
			var second = "" + now.getSeconds();
			if (second.length == 1) {
				second = "0" + second;
			}
			return year + "-" + month + "-" + day + " " + hour + ":" + minute
				+ ":" + second;
		}
		$scope.printCloseRegister = function() {
			GlobalVariable.isPrintPage = true;
			$timeout(function() {
				$window.print();
				GlobalVariable.isPrintPage = false;
			}, 2000);
		};
	}
})();
