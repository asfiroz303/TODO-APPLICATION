var todoApp = angular.module("todoApp");
todoApp.directive('topNavBar', function(){
	
	return{
		//restrict:'E',
		templateUrl : 'templates/TopNavBar.html'
	}
})
todoApp.directive('sideNavBar', function(){
	
	return{
		//restrict:'E',
		templateUrl : 'templates/SideNavBar.html'
	}
});	