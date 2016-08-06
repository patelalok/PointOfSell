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
            dataService.Get(url,request,onAddTaxSuccess,onAddTaxError,'application/json','application/json');
        };
        $scope.editTax = function()
        {
            var request={};

            request=
                {
                    "id": parseFloat($scope.taxId),
                    "tax": parseFloat($scope.taxValue),
                    "storeAddress": "3210 Tucker Norcross Rd Suite J, Tucker, Gorgia 30084",
                    "storeLogo": null,
                    "footerReceipt": "Phones can be returned within 7 days with a restocking fee of 25% with the receipt.  No exception. Accessory sales sre final, no refunds or exchnage."
                };

            request = JSON.stringify(request);
            var url="http://localhost:8080/editPageSetUpDetails";
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