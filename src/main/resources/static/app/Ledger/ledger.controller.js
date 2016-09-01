(function() {
	'use strict';

	angular.module('sampleApp').controller('LedgerController', LedgerController);

	LedgerController.$inject = [ '$scope', '$rootScope', 'device.utility','GlobalVariable','DialogFactory','dataService','$window','$filter','$timeout','RestrictedCharacter.Types','$state','GlobalConstants'];

	function LedgerController($scope, $rootScope, device ,GlobalVariable,DialogFactory,dataService,$window,$filter,$timeout,restrictCharacter,$state,GlobalConstants)
	{
		$scope.restrictCharacter=restrictCharacter;
		$scope.maxDate = new Date();
		$scope.Math = window.Math;
		$scope.modifiedData = [];
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
		function loadSalesHistoryData(startDate,endDate)
		{
			var url =GlobalConstants.URLCONSTANTS+'getSalesHistory?startDate='+startDate+'&endDate='+endDate;
			dataService.Get(url,getSalesHistorySuccessHandler,getSalesHistroyErrorHandler,"application/json","application/json");
			
			
		}
		$scope.onDateSelected = function(startDate, endDate, label, element) {
			var receiptIndex = element.attr('data-receipt-index');
			element.find('span').eq(0).html(endDate.format('yyyy-MM-dd'));
		};
		$scope.filterSalesHistory =  function(value)
		{
			console.log(value);
			$scope.filterHistory = value;
		};
		$scope.checkfilterValue = function(value)
		{
			if(($scope.custName == undefined && $scope.receiptNumber == undefined) || ($scope.custName == "" && $scope.receiptNumber == "")
					||($scope.custName == undefined && $scope.receiptNumber == "")||($scope.custName == "" && $scope.receiptNumber == undefined))
			{
				$scope.filterHistory= '';
			}
			else
				$scope.filterHistory = value;
		};
		
		function getSalesHistorySuccessHandler(response)
		{
			$scope.salesHistory = response;
			GlobalVariable.histroyData = $scope.salesHistory;
		}
		function getSalesHistroyErrorHandler(response)
		{
			
		}
		$scope.print = function(id)
		{
			$scope.testPrint = "hi";
			var url=GlobalConstants.URLCONSTANTS+"getReceiptDetails?receiptId="+id;
			dataService.Get(url,getPrintSuccessHandler,getPrintErrorHandler,"application/json","application/json");
			
			
		};
		function getPrintSuccessHandler(response)
		{
			GlobalVariable.receiptData =response;
			$scope.itemTotal =Number(parseFloat(GlobalVariable.receiptData[0].transactionDtoList[0].subTotal)+parseFloat(GlobalVariable.receiptData[0].transactionDtoList[0].lineItemDiscount)).toFixed(2);
			$scope.modifiedData=[];
			$scope.printFirstName='';
			$scope.printLastName ='';
			$scope.printStreet='';
			$scope.printCity='';
			$scope.printState='';
			$scope.printCountry='';
			$scope.printzipCode='';
			$scope.printPhone='';
			for(var i=0;i<GlobalVariable.receiptData[0].transactionLineItemDtoList.length;i++)
			{
				$scope.modifiedData.push(
					{
						"productNumber":GlobalVariable.receiptData[0].transactionLineItemDtoList[i].productNumber,
						"productDescription":GlobalVariable.receiptData[0].transactionLineItemDtoList[i].productDescription,
						"retail":GlobalVariable.receiptData[0].transactionLineItemDtoList[i].retail,
						"discountPercentage":GlobalVariable.receiptData[0].transactionLineItemDtoList[i].discountPercentage,
						"retwdisc":(parseFloat(GlobalVariable.receiptData[0].transactionLineItemDtoList[i].totalProductPrice)/parseFloat(GlobalVariable.receiptData[0].transactionLineItemDtoList[i].quantity)).toFixed(2),
						"quantity":GlobalVariable.receiptData[0].transactionLineItemDtoList[i].quantity,
						"totalProductPrice":GlobalVariable.receiptData[0].transactionLineItemDtoList[i].totalProductPrice,
						"imeiNo":GlobalVariable.receiptData[0].transactionLineItemDtoList[i].imeiNo
					}
				);
			}

			GlobalVariable.isPrintPage = true;
			if(GlobalVariable.receiptData[0].customerDtosList .length !== 0)
			{

				$scope.printFirstName=GlobalVariable.receiptData[0].customerDtosList[0].firstName;
					$scope.printLastName =GlobalVariable.receiptData[0].customerDtosList[0].lastName;
						$scope.printStreet=GlobalVariable.receiptData[0].customerDtosList[0].street;
							$scope.printCity=GlobalVariable.receiptData[0].customerDtosList[0].city;
								$scope.printState=GlobalVariable.receiptData[0].customerDtosList[0].state;
									$scope.printCountry=GlobalVariable.receiptData[0].customerDtosList[0].country;
										$scope.printzipCode=GlobalVariable.receiptData[0].customerDtosList[0].zipcode;
						$scope.printPhone=GlobalVariable.receiptData[0].customerDtosList[0].phoneNo;

			}
			
			$timeout(function() {
				$window.print();
				GlobalVariable.isPrintPage = false;
			}, 2000);
			
		}
		function getPrintErrorHandler(response)
		{
			
		}
		$scope.navigateToReturnPage = function(transactionDate,transactionCompId)
		{
			/*var request = new Object();
			request.transactionDate = transactionDate;
			request.transactionCompId = transactionCompId;
			request = JSON.stringify(request);*/
			var url=GlobalConstants.URLCONSTANTS+"getReceiptDetails?receiptId="+transactionCompId;
			dataService.Get(url,getReturnsSuccessHandler,getReturnsErrorHandler,"application/json","application/json");
			

		};
		function getReturnsSuccessHandler(response)
		{
			GlobalVariable.getReturnDetails = response;
			
			GlobalVariable.returnProduct = true;
			$state.go('return');
		}
		function getReturnsErrorHandler(response)
		{
			
		}
/*		salesDate : moment($scope.salesDates[receiptIndex]).format("MM/DD/YYYY")
*/		function render()
		{
			//$scope.startDate = moment();
			/** Options for the date picker directive * */
			$scope.dateRangeOptions = {
				//startDate : moment(),
				showDropdowns : true,
				format : 'yyyy-MM-dd',
				singleDatePicker : true
			};
			$scope.startDate = $filter('date')(new Date(), "yyyy-MM-dd");
			$scope.endDate = $scope.startDate;
			var start = js_yyyy_mm_dd()+''+' 00:00:00';
			var end = js_yyyy_mm_dd()+''+' 23:59:59';
			loadSalesHistoryData(start,end);
			getStoreAddress();
		}
		function getStoreAddress()
		{
			var url=GlobalConstants.URLCONSTANTS+'getPageSetUpDetails';
			dataService.Get(url,onStoreSuccess,onStoreError,'application/json','application/json');
		}
		function onStoreSuccess(response)
		{
			GlobalVariable.storeAddress = response[0].storeAddress;
			GlobalVariable.footerReceipt = response[0].footerReceipt;
		}
		function onStoreError(error)
		{

		}
$scope.applyFilterHistory = function()
{
	var start = $filter('date')($scope.startDate, "yyyy-MM-dd")+" 00:00:00";
	var end = $filter('date')($scope.endDate, "yyyy-MM-dd")+" 23:59:59";
	loadSalesHistoryData(start,end);
};
function js_yyyy_mm_dd_hh_mm_ss () {
	  var now = new Date();
	  var year = "" + now.getFullYear();
	  var month = "" + (now.getMonth() + 1); if (month.length == 1) { month = "0" + month; }
	  var day = "" + now.getDate(); if (day.length == 1) { day = "0" + day; }
	 var  hour = "" + now.getHours(); if (hour.length == 1) { hour = "0" + hour; }
	  var minute = "" + now.getMinutes(); if (minute.length == 1) { minute = "0" + minute; }
	  var second = "" + now.getSeconds(); if (second.length == 1) { second = "0" + second; }
	  return year + "-" + month + "-" + day + " " + hour + ":" + minute + ":" + second;
	}
		render();
	}
	function js_yyyy_mm_dd () {
		  var now = new Date();
		  var year = "" + now.getFullYear();
		  var month = "" + (now.getMonth() + 1); if (month.length == 1) { month = "0" + month; }
		  var day = "" + now.getDate(); if (day.length == 1) { day = "0" + day; }
		 var  hour = "" + now.getHours(); if (hour.length == 1) { hour = "0" + hour; }
		  var minute = "" + now.getMinutes(); if (minute.length == 1) { minute = "0" + minute; }
		  var second = "" + now.getSeconds(); if (second.length == 1) { second = "0" + second; }
		  return year + "-" + month + "-" + day ;
		}
		
})();