(function() {
	'use strict';

	angular.module('sampleApp').factory('getProductDetails',getProductDetails);
	

	getProductDetails.$inject = ['dataService','GlobalVariable'];

	function getProductDetails(dataService,GlobalVariable) {
		
		var getProductDetails = {
				
				getProductValues:getProductValues
		};
		function getProductValues()
		{
			dataService.Get("http://localhost:8080/getVendor",getVendorSuccessHandler,getVendorErrorHandler,"application/json","application/json");
			dataService.Get("http://localhost:8080/getBrand",getBrandSuccessHandler,getBrandErrorHandler,"application/json","application/json");
			dataService.Get("http://localhost:8080/getCategory",getCategorySuccessHandler,getCategoryErrorHandler,"application/json","application/json");
			dataService.Get("http://localhost:8080/getProduct",getProductSuccessHandler,getProductErrorHandler,"application/json","application/json");
		}
		function getVendorSuccessHandler(reponse)
		{
			GlobalVariable.getVendors = response;
		}
		function getVendorErrorHandler(errorResponse)
		{
			
		}
		function getBrandSuccessHandler(reponse)
		{
			GlobalVariable.getBrands = response;
		}
		function getBrandErrorHandler(errorResponse)
		{
			
		}
		function getCategorySuccessHandler(reponse)
		{
			GlobalVariable.getCategory = response;
		}
		function getCategoryErrorHandler(errorResponse)
		{
			
		}
		function getProductSuccessHandler(reponse)
		{
			GlobalVariable.getProducts = response;
		}
		function getProductErrorHandler(errorResponse)
		{
			
		}
		
		
		return getProductDetails;
	};
})();/**
 * 
 */