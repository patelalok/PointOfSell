(function() {
    'use strict';

    angular.module('sampleApp').controller('PrintRecepitController', PrintRecepitController);

    PrintRecepitController.$inject = [ '$scope', '$rootScope', 'device.utility','GlobalVariable','DialogFactory','$timeout','$window'];

    function PrintRecepitController($scope, $rootScope, device ,GlobalVariable,DialogFactory,$timeout,$window)
    {
        $scope.GlobalVariable = GlobalVariable;
        $scope.onaddFour = function()
        {
            DialogFactory.close(true);
        };
        $scope.onaddFourPrint = function()
        {
            DialogFactory.close(true);
            $timeout(function() {
                $window.print();
                GlobalVariable.isPrintPage = false;
            }, 2000);
        };
        function render()
        {

        }
        render();
    }

})();