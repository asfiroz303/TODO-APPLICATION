var todoApp = angular.module('todoApp', [ 'ui.router' ]);

todoApp.config([ '$stateProvider', '$urlRouterProvider',
		function($stateProvider, $urlRouterProvider) {
			/* alert('hello'); */
			$stateProvider.state('register', {

				url : '/register',
				templateUrl : 'templates/Registration.html',
				controller : 'registrationController'
			})

			// $urlRouterProvider.otherwise('register');

			.state('login', {

				url : '/login',
				templateUrl : 'templates/Login.html',
				controller : 'loginController'
			});
			$urlRouterProvider.otherwise('register');
			$urlRouterProvider.otherwise('login');
		} ]);
