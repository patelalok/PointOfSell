var app = angular.module('sampleApp', ['ui.bootstrap','ngSanitize','matchMedia','ui.router']);
  
app.config(['$stateProvider','$urlRouterProvider',function($stateProvider, $urlRouterProvider){

	$urlRouterProvider.otherwise('/login');
	
	$stateProvider
	  .state('login', {
	    url: '/login',
	    templateUrl: 'app/login/login.html',
	    controller: 'loginController'
	  })
	  .state('product', {
	    url: '/product',
	    templateUrl: 'app/product/product.html',
	    controller: 'productController'
	  })
	   .state('productmain', {
	    url: '/productmain',
	    templateUrl: 'app/product/mainProduct.html',
	    controller: 'mainProductController'
	  })
	  .state('sell', {
		    url: '/sell',
		    templateUrl: 'app/sell/sell.html',
		    controller: 'sellController'
		  });
  
}]);
