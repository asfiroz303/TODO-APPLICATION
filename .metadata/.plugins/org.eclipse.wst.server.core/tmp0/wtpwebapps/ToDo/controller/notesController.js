/*var todoApp = angular.module("todoApp");
todoApp.controller('homeController',
		function($scope, $state, fileReader, homeService, $timeout, $filter,
				$mdSidenav, $mdDialog, mdcDateTimeDialog, toastr, $interval,
				$http, $location, Upload, $base64) {

	*//** ******* get the notes************* *//*
	var search=[];
	var getNotes = function() {
		var url = 'noteList';
		var notes = homeService.service(url, 'GET', notes);
		notes.then(function(response) {
			$scope.notes = response.data;
			reminder($scope.notes);
			homeService.notes = response.data;
			console.log(notes)
			for (var i = 0; i < response.data.length; i++) {
			 console.log("inside get notes push into............@@@@@@@@@@@@@")
					search.push(response.data[i]);
			}
		}, function(response) {
			$scope.error = response.data.responseMessage;
			console.log(response);
		});
	}
	
	//////////////////toastr///////////////////////
	
	var reminder=function(notes){
	 $interval(function() {
		for (var i = 0; i < notes.length; i++) {
		  if (notes[i].reminder) {
		    var date = new Date(notes[i].reminder);
			console.log("date",$filter('date')(date,"MM/dd/yyyy hh:mm") )
			console.log("reminder",date)
			date=$filter('date')(date,"MM/dd/yyyy hh:mm")
		     if (date == $filter('date')(new Date(),"MM/dd/yyyy hh:mm")) {
				toastr.success(notes[i].description, 'Reminder Set',
				notes[i].title);
					}
				}
			}
		},60000);
	}
	
	*//** ******* create notes ************* *//*
	$scope.createNote = function() {
		console.log('hello');
		$scope.note = {};
		var url = 'addNote'
		$scope.note.title = document.getElementById("title").innerHTML;
		$scope.note.description = document
				.getElementById("description").innerHTML;

		var notes = homeService.service(url, 'POST', $scope.note);
		notes.then(function(response) {
			getNotes();
			document.getElementById("title").innerHTML = "";
			document.getElementById("description").innerHTML = "";

		}, function(response) {
			getNotes();
			$scope.error = response.data.message;
		});
	}

	*//** ******* delete notes ************* *//*
	$scope.deleteNote = function(note) {
		console.log($scope.note);
		note.trash = true;
		note.archive = false;
		note.pinned = false;
		var url = 'update';
		var notes = homeService.service(url, 'PUT', note);
		notes.then(function(response) {
			getNotes();
		}, function(response) {
			$scope.error = response.data.message;
		});
	}

	*//** ******* permanently delete the notes from trash************* *//*
	$scope.deleteForever = function(note) {
		var url = 'delete/' + note.noteId;
		var notes = homeService.service(url, 'DELETE', note);
		notes.then(function(response) {
			getNotes();
		}, function(response) {
			getNotes();
			$scope.error = response.data.message;
		});
	}

	*//** *******logic for get the notes ************* *//*
	$scope.restoreNote = function(note) {
		note.trash = 0;
		var url = 'update';
		var notes = homeService.service(url, 'PUT', note);
		notes.then(function(response) {
			getNotes();
		}, function(response) {
			getNotes();
			$scope.error = response.data.message;
		});
	}
	*//** ******* popup the page for update ************* *//*

	$scope.popUp = function(note, event) {
		$mdDialog.show({
			locals : {
				data : note
			},
			templateUrl : 'templates/UpdateNote.html',
			parent : angular.element(document.description),
			targetEvent : event,
			clickOutsideToClose : true,
			controllerAs : 'controller',
			controller : mdDialogController
		});
	}

	*//** ******* update notes ************* *//*

	function mdDialogController($scope, $state, data) {
		$scope.mdDialogData = data;
		$scope.updateNote = function() {
			var url = 'update';
			data.title = document.getElementById("getTitle").innerHTML;
			data.description = document
					.getElementById("getDescription").innerHTML;
			var notes = homeService.service(url, 'PUT', data);

			notes.then(function(response) {
				$mdDialog.cancel();
			}, function(response) {
				$scope.error = response.data.responseMessage;
			});
		}
	}
    getNotes();
});
*/