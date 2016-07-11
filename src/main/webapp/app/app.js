var app = angular.module('sampleApp', ['ui.bootstrap','ngSanitize','matchMedia','ui.router','uiSwitch','ui.mask','AngularPrint','chart.js']);
  
app.config(['$stateProvider','$urlRouterProvider','ChartJsProvider',function($stateProvider, $urlRouterProvider,ChartJsProvider){

	$urlRouterProvider.otherwise('/login');
	//ChartJsProvider.setOptions({ colors : [ '#803690', '#00ADF9', '#DCDCDC', '#46BFBD', '#FDB45C', '#949FB1', '#4D5360'] });
	 ChartJsProvider.setOptions({
	      colours: [ '#803690', '#00ADF9', '#DCDCDC', '#46BFBD', '#FDB45C', '#949FB1', '#4D5360'],
	      responsive: true
	    });
	    // Configure all line charts
	  /*  ChartJsProvider.setOptions('Line', {
	      datasetFill: false
	    });*/
	
	$stateProvider
	  .state('login', {
	    url: '/login',
	    templateUrl: 'app/login/login.html',
	    controller: 'loginController'
	  })
	  .state('product', {
	    url: '/product',
	    templateUrl: 'app/product/product.html',
	    controller: 'productController',
	    params:{
	    	obj:null
	    }
	  })
	   .state('productmain', {
	    url: '/productmain',
	    templateUrl: 'app/product/mainProduct.html',
	    controller: 'mainProductController'
	  })
	  .state('customer', {
	    url: '/customer',
	    templateUrl: 'app/AddCustomer/mainCustomer.html',
	    controller: 'mainCustomerController'
	  })
	  .state('sell', {
		    url: '/sell',
		    templateUrl: 'app/sell/sell.html',
		    controller: 'sellController'
		  })
		   .state('brand', {
		    url: '/brand',
		    templateUrl: 'app/product/brand.html',
		    controller: 'BrandController'
		  })
		  .state('supplier', {
		    url: '/supplier',
		    templateUrl: 'app/product/supplier.html',
		    controller: 'SupplierController'
		  })
		   .state('vendor', {
		    url: '/vendor',
		    templateUrl: 'app/product/vendor.html',
		    controller: 'VendorController'
		  })
		  .state('setup', {
		    url: '/setup',
		    templateUrl: 'app/Setup/setup.html',
		    controller: 'SetupController'
		  })
		    .state('ledger', {
		    url: '/ledger',
		    templateUrl: 'app/Ledger/ledger.html',
		    controller: 'LedgerController'
		  })
		   .state('report', {
		    url: '/report',
		    templateUrl: 'app/Report/report.html',
		    controller: 'ReportController'
		  })
		   .state('closeregister', {
		    url: '/closeregister',
		    templateUrl: 'app/Registers/CloseRegister.html',
		    controller: 'CloseRegisterController'
		  })
		   .state('openregister', {
		    url: '/openregister',
		    templateUrl: 'app/Registers/OpenRegister.html',
		    controller: 'OpenRegisterController'
		  })
		  .state('monthly', {
		    url: '/monthly',
		    templateUrl: 'app/Registers/monthlyanalysis.html',
		    controller: 'MonthlyAnalysisController'
		  })
		  .state('home', {
		    url: '/home',
		    templateUrl: 'app/home/home.html',
		    controller: 'HomeController'
		  });
  
}]);
