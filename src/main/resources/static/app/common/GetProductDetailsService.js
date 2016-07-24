(function() {
	'use strict';

	angular.module('sampleApp').factory('getProductDetails',getProductDetails);
	

	getProductDetails.$inject = ['dataService','GlobalVariable'];

	function getProductDetails(dataService,GlobalVariable) {
		
		var getProductDetails = {
				
				getProductValues:getProductValues,
				getVendorDetails:getVendorDetails,
				getBrandDetails:getBrandDetails,
				getCategoryDetails:getCategoryDetails,
				getProductDetail:getProductDetail,
				getCustomerDetails:getCustomerDetails
				
		};
		function getProductValues()
		{
			getVendorDetails();
			getBrandDetails();
			getCategoryDetails();
			getProductDetail();
			getCustomerDetails();
			
			
			/*getVendorSuccessHandler('');
			getBrandSuccessHandler('');
			getCategorySuccessHandler('');
			
			getProductSuccessHandler('');
			getCustomerDetailSuccessHandler('');*/
		}
		function getCustomerDetails()
		{
			dataService.Get(" http://localhost:8080/getCustomerDetail",getCustomerDetailSuccessHandler,getCustomerDetailErrorHandler,"application/json","application/json");

		}
		function getProductDetail()
		{
			dataService.Get("http://localhost:8080/getProduct",getProductSuccessHandler,getProductErrorHandler,"application/json","application/json");

		}
		function getCategoryDetails()
		{
			dataService.Get("http://localhost:8080/getCategory",getCategorySuccessHandler,getCategoryErrorHandler,"application/json","application/json");

		}
		function getBrandDetails()
		{
			dataService.Get("http://localhost:8080/getBrand",getBrandSuccessHandler,getBrandErrorHandler,"application/json","application/json");
		}
		function getVendorDetails()
		{
			dataService.Get("http://localhost:8080/getVendor",getVendorSuccessHandler,getVendorErrorHandler,"application/json","application/json");
		}
		function getVendorSuccessHandler(response)
		{
			GlobalVariable.getVendors = response;
		}
		function getVendorErrorHandler(errorResponse)
		{
			
		}
		function getBrandSuccessHandler(response)
		{
			GlobalVariable.getBrands =  response;
		}
		function getBrandErrorHandler(errorResponse)
		{
			
		}
		function getCategorySuccessHandler(response)
		{
			GlobalVariable.getCategory = response;
		}
		function getCategoryErrorHandler(errorResponse)
		{
			
		}
		function getProductSuccessHandler(response)
		{
			GlobalVariable.getProducts =response;
		}
		function getProductErrorHandler(errorResponse)
		{
			
		}
		function getCustomerDetailSuccessHandler(response)
		{
			GlobalVariable.getCustomerDtls =response;
		}
		function getCustomerDetailErrorHandler(response)
		{
			//GlobalVariable.getCustomerDtls =response;
		}
		return getProductDetails;
	};
})();/**
 * 
 */