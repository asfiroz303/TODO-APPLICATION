/*var todoApp = angular.module("todoApp");
todoApp.factory('archiveService', function($http, $location) {

	var notes = {};
	notes.service = function(url, method, note) {
		console.log("in service");
		console.log(note);
		return $http({
			method : method,
			url : url,
			data : note,
			headers : {
				'Authorization' : localStorage.getItem('token')
			}
		});
	}
	return notes;
});
*/