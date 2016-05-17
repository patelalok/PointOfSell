(function() {
	'use strict';

	angular.module('sampleApp').factory('dataService', DataService);

	DataService.$inject = [ '$http', '$state', 'GlobalConstants', 'GlobalVariable', '$rootScope',
			'DialogFactory', 'util'];

	function DataService($http,$state, GlobalConstants, GlobalVariable, $rootScope, DialogFactory, util) {
		return {
			Get : function(_url, success, error) {
				$http({
					method : 'GET',
					url : _url+util.getRandomNumberForUrl(_url),
					cache : false
				}).success(function(data, status, headers, config) {
					if (status === 200) {
						success(data);
					} else {
						error(data);
					}
				}).error(function(data, status, headers, config) {
					error(data);
				});
			},
			Post : function(_url, _reqData, success, error, contentType, accept, isNonFormParamFlow) {
				var thisObj = this;
				contentType = (contentType) ? contentType : 'application/x-www-form-urlencoded';
				accept = (accept) ? accept : 'application/json';
				var headerObj = {
						Accept : accept
				};
				_reqData = _reqData ? (_reqData) : {};

				var reqData = isNonFormParamFlow ? JSON.stringify(_reqData) : $.param(_reqData);
				$http({
					method : 'POST',
					url : _url,
					data : reqData,
					headers : headerObj
				})
						.success(
								function(data, status, headers, config) {
									console.log('data in success main= ' + (data));
								
										success(data);
									return false;
								}).error(
								function(data, status, headers, config) {
									if (status === 403) {
										
										util.Wait(false);
									} else if (error) {
										error(data);
									}
									return false;
								});
			}
		};
	}
})();