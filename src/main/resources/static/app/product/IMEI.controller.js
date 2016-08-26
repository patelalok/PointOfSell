(function() {
    'use strict';

    angular.module('sampleApp').controller('IMEIController', IMEIController);

    IMEIController.$inject = [ '$scope', '$rootScope', 'device.utility','GlobalVariable','DialogFactory','dataService','$timeout','$state','getProductDetails'];

    function IMEIController($scope, $rootScope, device ,GlobalVariable,DialogFactory,dataService,$timeout,$state,getProductDetails)
    {
        $scope.device= device;
        $scope.GlobalVariable = GlobalVariable;
        $scope.successAlert = false;
        $scope.closePopup = function()
        {
            DialogFactory.close(true);
        };

        $scope.populateRetailPrice1 = function()
        {
            if($scope.prodIMEICP !== '' && $scope.prodIMEICP !== undefined)
            {
                if($scope.prodIMEIMarkup !== '' && $scope.prodIMEIMarkup !== undefined)
                {
                    $scope.prodIMEIRetail =(parseFloat($scope.prodIMEICP))+(parseFloat($scope.prodIMEICP) *( (parseFloat($scope.prodIMEIMarkup))/100));
                }
            }
            else
            {
                $scope.prodIMEIRetail = 0;
            }
        };

        $scope.populateMarkup1 = function()
        {
            if($scope.prodIMEIRetail !== '' && $scope.prodIMEIRetail !== undefined)
            {
                if($scope.prodIMEICP !== '' && $scope.prodIMEICP !== undefined)
                {
                    $scope.prodIMEIMarkup = ((parseFloat($scope.prodIMEIRetail) -(parseFloat($scope.prodIMEICP)))/(parseFloat($scope.prodIMEICP))) * 100;
                }
                if($scope.prodIMEIMarkup == 'Infinity')
                {
                    $scope.prodIMEIMarkup = 0;
                }
            }
            else
            {
                $scope.prodIEMIRetail = 0;
                $scope.prodIMEIMarkup =0;
            }
        };
        $scope.addIMEI = function()
        {
          var url='http://localhost:8080/addIMEINo';
            var request={
                "productNo":(GlobalVariable.IMEIProductID).toString(),
                "imeiNo":$scope.imeiNumber,
                "costPrice":$scope.prodIMEICP,
                "retailPrice":$scope.prodIMEIRetail,
                "markup":$scope.prodIMEIMarkup,
                "lastUpdatedTimeStamp":js_yyyy_mm_dd_hh_mm_ss()
            };
            request=JSON.stringify(request);
            dataService.Post(url,request,onAddIMEISuccess,onAddIMEIError,'application/json','application/json');

        };
        function onAddIMEISuccess(response)
        {
            DialogFactory.close(true);
        }
        function onAddIMEIError(response)
        {

        }
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
        function render()
        {
            $scope.prodIMEICP = 0;
            $scope.prodIMEIMarkup = 0;
            $scope.prodIMEIRetail = 0;
                if(GlobalVariable.editIMEI == true)
                {

                }
        }
        render();
    }

})();