var todoApp = angular.module('todoApp', [ 'ui.router' ]);

todoApp.config([ '$stateProvider', '$urlRouterProvider',
		function($stateProvider, $urlRouterProvider) {
			$stateProvider.state('register', {

				url : '/register',
				templateUrl : 'templates/Registration.html',
				controller : 'registrationController'

			}).state('login', {

				url : '/login',
				templateUrl : 'templates/Login.html',
				controller : 'loginController'

			}).state('home', {

				url : '/home',
				templateUrl : 'templates/Home.html',
				controller : 'homeController'

			});

			// $urlRouterProvider.otherwise('register');
			$urlRouterProvider.otherwise('login');
		} ]);
