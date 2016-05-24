var app = angular.module('sampleApp', ['ui.router','ui.bootstrap','ngSanitize','matchMedia']);
  
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
	  .state('sell', {
		    url: '/sell',
		    templateUrl: 'app/sell/sell.html',
		    controller: 'sellController'
		  });
  
}]);
