(function() {
    'use strict';

    angular.module('sampleApp').controller('EditClockController', EditClockController);

    EditClockController.$inject = [ '$scope', '$rootScope', 'device.utility','GlobalVariable','DialogFactory','RestrictedCharacter.Types','GlobalConstants','dataService'];

    function EditClockController($scope, $rootScope, device ,GlobalVariable,DialogFactory,restrictCharacter,GlobalConstants,dataService)
    {
        $scope.GlobalVariable = GlobalVariable;
        $scope.restrictCharacter=restrictCharacter;
        $scope.maxDate = new Date();
        $scope.minDate = new Date();
        $scope.onaddFour = function()
        {
            DialogFactory.close(true);
        };
        $scope.openclockInDateCalendar = function($event) {
            $event.preventDefault();
            $event.stopPropagation();
            $scope.openStart = true;
        };
        $scope.openclockOutDateCalendar = function($event) {
            $event.preventDefault();
            $event.stopPropagation();
            $scope.openEnd = true;
        };
        $scope.onDateSelected = function(startDate, endDate, label, element) {
            var receiptIndex = element.attr('data-receipt-index');
            element.find('span').eq(0).html(endDate.format('yyyy-MM-dd'));
        };
        $scope.editClockInDetails = function()
        {
            var date1 = new Date($scope.clockInDate);
            var date2 = new Date($scope.clockOutDate);
            var hours = Number(Math.abs(date1 - date2) / 36e5).toFixed(2);
            var url=GlobalConstants.URLCONSTANTS+'editUserClockIn';
            var request= {
                "clockInId": GlobalVariable.editClockDtls.clockInId,
                "username": GlobalVariable.editClockDtls.username,
                "clockInTime": $filter('date')($scope.clockInDate, "yyyy-MM-dd hh:mm:ss"),
                "clockOutTime": $filter('date')($scope.clockOutDate, "yyyy-MM-dd hh:mm:ss"),
                "noOfhours": hours,
                "horlyRate": $scope.hrlyRate.replace('$','').replace(/,/g,''),
                "date": null

            }
            dataService.Post(url,request,onEditClckSuccess,onEditClckError,'application/json','application/json');
        };
        function onEditClckSuccess(response)
        {

        }
        function onEditClckError(response)
        {

        }
        function render()
        {
            /** Options for the date picker directive * */
            $scope.dateRangeOptions = {
                //startDate : moment(),
                showDropdowns : true,
                format : 'yyyy-MM-dd',
                singleDatePicker : true
            };
            $scope.clockInDate=GlobalVariable.editClockDtls.clockInTime;
            $scope.clockOutDate =GlobalVariable.editClockDtls.clockOutTime;
            $scope.hrlyRate =GlobalVariable.editClockDtls.hrlyRate;
        }
        render();
    }

})();