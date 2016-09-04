(function() {
    'use strict';

    angular.module('sampleApp').factory('GlobalConstants', Globals);

    Globals.$inject = [];

    function Globals() {
        var GlobalConstantObj = {};

        GlobalConstantObj.URLCONSTANTS = 'http://pointofsellalok.cfapps.io/';

        return GlobalConstantObj;
    }
})();
