var todoApp = angular.module('todoApp');

todoApp.controller('setController', function($scope, forgotPasswordService,
		$location) {

	$scope.sendLink = function() {
		
		var httpSendLink = forgotPasswordService.linkForEmail($scope.user);
		
		httpSendLink.then(function(response) {

			if (response.data.status == 500) {
				$scope.response = 'User not found :';
			} else if (response.data.status == -200) {
				$scope.response = 'Mail could not be sent';
			} else if (response.data.status == 200) {
				$location.path('setPassword');
			}
		});
	}

	$scope.setPassword = function() {

		var httpSet = forgotPasswordService.setPassword($scope.user);

		httpSet.then(function(response) {

			if (response.data.status == 500) {
				$scope.response = 'Invalid OTP or email address';
			} else if (response.data.status == 500) {
				$scope.response = 'Password could not be updated';
			} else if (response.data.status == 200) {
				$location.path('login');
			}
		});
	}
});