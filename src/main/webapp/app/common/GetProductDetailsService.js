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
			//getVendorSuccessHandler('');
			//getBrandSuccessHandler('');
			//getCategorySuccessHandler('');
			
			//getProductSuccessHandler('');
		}
		function getVendorSuccessHandler(response)
		{
			GlobalVariable.getVendors = response;
			/*[
			                              {
			                            	    "vendorId": 11,
			                            	    "vendorName": "Tcl-delta",
			                            	    "commision": "12",
			                            	    "phoneNo": 1234567890,
			                            	    "companyName": "TCS",
			                            	    "address": "This is from china",
			                            	"noOfProducts":"10"
			                            	  }
			                            	];*/
				//response;
		}
		function getVendorErrorHandler(errorResponse)
		{
			
		}
		function getBrandSuccessHandler(response)
		{
			GlobalVariable.getBrands =  response;
				/*[
			                             {
			                            	    "brandId": 1,
			                            	    "brandName": "test",
			                            	    "brandDescription": "test",
			                            	     "noOfProducts":"10"
			                            	  },
			                            	  {
			                            	    "brandId": 2,
			                            	    "brandName": "test",
			                            	    "brandDescription": "test",
			                            	"noOfProducts":"10"

			                            	  },
			                            	  {
			                            	    "brandId": 3,
			                            	    "brandName": "Iphone",
			                            	    "brandDescription": "This is iphone brand",
			                            	"noOfProducts":"10"

			                            	  }
			                            	];*/
				//response;
		}
		function getBrandErrorHandler(errorResponse)
		{
			
		}
		function getCategorySuccessHandler(response)
		{
			GlobalVariable.getCategory = response;
			/*	[
			                              {
			                            	    "categoryId": 1,
			                            	    "categoryName": "dtgf",
			                            	    "description": "fdgdf",
			                            	"noOfProducts":"10"

			                            	  },
			                            	  {
			                            	    "categoryId": 2,
			                            	    "categoryName": "Phone",
			                            	    "description": "This category is for phone",
			                            	"noOfProducts":"10"
			                            	  }
			                            	];
				//response;
*/		}
		function getCategoryErrorHandler(errorResponse)
		{
			
		}
		function getProductSuccessHandler(response)
		{
			GlobalVariable.getProducts =response;
				/* [
			                              {
			                            	    "productId": 6,
			                            	    "productNo": "123456789012",
			                            	    "categoryId": 1,
			                            	    "vendorId": 11,
			                            	    "brandId": 1,
			                            	    "altNo": "1234",
			                            	    "description": "iphone-6",
			                            	    "costPrice": "12.99",
			                            	    "markup": "25",
			                            	    "retailPrice": "15.00",
			                            	    "quantity": "10",
			                            	    "minProductQuantity": "5",
			                            	    "returnRule": "NextWeek",
			                            	    "image": "image",
			                            	    "createdDate": "1000-01-01 00:00:00"
			                            	  }
			                            	];
				//response;
*/		}
		function getProductErrorHandler(errorResponse)
		{
			
		}
		
		
		return getProductDetails;
	};
})();/**
 * 
 */