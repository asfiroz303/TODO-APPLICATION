var todoApp = angular.module("todoApp");
todoApp.factory('homeService', function($http, $location){
	
	var card = {};
	 
	card.cardUser = function() {
		return $http({
			method : "GET",
			url : 'noteList',
			data : note
		});
	}
	return card;
});
