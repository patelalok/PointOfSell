(function() {
    'use strict';

    angular.module('sampleApp').controller('TaxController', TaxController);

    TaxController.$inject = [ '$scope', '$rootScope', 'device.utility','GlobalVariable','DialogFactory','dataService'];

    function TaxController($scope, $rootScope, device ,GlobalVariable,DialogFactory,dataService)
    {
        $scope.GlobalVariable = GlobalVariable;
        $scope.closeTaxAdd = function()
        {
            DialogFactory.close(true);
        };

        $scope.addTax = function()
        {
            var request= {};
            request={
                'id':$scope.taxId,
                'tax':$scope.taxValue
            };
            request = JSON.stringify(request);
            var url="http://localhost:8080/addTax";
            dataService.Post(url,request,onAddTaxSuccess,onAddTaxError,'application/json','application/json');
        };
        $scope.editTax = function()
        {
            var request= {};
            request={
                'id':$scope.taxId,
                'tax':$scope.taxValue
            };
            request = JSON.stringify(request);
            var url="http://localhost:8080/editTax";
            dataService.Post(url,request,onEditTaxSuccess,onEditTaxError,'application/json','application/json');
        };
        function onEditTaxSuccess(response)
        {
            DialogFactory.close(true);
            GlobalVariable.successTaxAlert = true;
            GlobalVariable.editSuccess = true;
        }
        function onEditTaxError(response)
        {
            DialogFactory.close(true);
            GlobalVariable.successTaxAlert = false;
            GlobalVariable.editSuccess = false;
        }
        function onAddTaxSuccess(response)
        {
            DialogFactory.close(true);
            GlobalVariable.successTaxAlert = true;
            GlobalVariable.addedSuccessfull = true;
        }
        function onAddTaxError(response)
        {
            DialogFactory.close(true);
            GlobalVariable.successTaxAlert = false;
            GlobalVariable.addedSuccessfull = false;
        }
        function render()
        {
                if(GlobalVariable.editTax == true)
                {
                    $scope.taxId= GlobalVariable.editTaxId;
                    $scope.taxValue = GlobalVariable.editedTax;
                }
        }
        render();
    }

})();