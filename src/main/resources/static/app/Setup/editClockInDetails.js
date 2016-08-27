(function() {
    'use strict';

    angular.module('sampleApp').controller('EditClockController', EditClockController);

    EditClockController.$inject = [ '$scope', '$rootScope', 'device.utility','GlobalVariable','DialogFactory','RestrictedCharacter.Types'];

    function EditClockController($scope, $rootScope, device ,GlobalVariable,DialogFactory,restrictCharacter)
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
            var url=' http://localhost:8080/editUserClockIn';
            var request= {
                "clockInId": GlobalVariable.editClockDtls.clockInId,
                "username": GlobalVariable.editClockDtls.username,
                "clockInTime": "2016-08-08 09:08:28.0",
                "clockOutTime": "2016-08-08 09:15:03.0",
                "noOfhours": null,
                "horlyRate": $scope.hrlyRate,
                "date": null

            }
        };
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