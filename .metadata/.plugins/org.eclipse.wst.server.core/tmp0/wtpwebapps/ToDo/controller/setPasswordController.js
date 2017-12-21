var todoApp = angular.module('todoApp');

todoApp.controller('setPasswordController', function($scope, setPasswordService,
		$state) {
	$scope.setPassword=function(){
		
		var set = setPasswordService.setPassword($scope.user, $scope.error);
		set.then(function (responce) {
			$state.go('setPassword');
		},
		function(error){
			$scope.errorMessage = error.data.message;
			$state.go('setPassword');	
	
		})
		
		
	}
	
});