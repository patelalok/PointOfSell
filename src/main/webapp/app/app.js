var app = angular.module('sampleApp', ['ui.router','ui.bootstrap','ngSanitize','matchMedia']);
  
app.config(['$stateProvider','$urlRouterProvider',function($stateProvider, $urlRouterProvider){

	$urlRouterProvider.otherwise('/dummy');
	
	$stateProvider
	  .state('dummy', {
	    url: '/dummy',
	    templateUrl: 'app/dummy/dummy.html',
	    controller: 'dummyController'
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
