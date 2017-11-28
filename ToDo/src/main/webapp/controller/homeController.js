var todoApp = angular.module("todoApp");
  todoApp.controller('homeController', function ($scope,$state, homeService, $timeout, $filter, $mdSidenav,  $mdDialog, mdcDateTimeDialog, toastr, $interval, $http) {
	  
	  $scope.mouse=false;
	/*********logic for get the notes **************/
	  
	  var getNotes=function(){
	    	  var url = 'noteList';
	    	  var notes=homeService.service(url,'GET',notes);
	    	  notes.then(function(response){ 
	    	  $scope.notes=response.data;
	    	/*  console.log("$scope.notes::",$scope.notes);
	    	 },function(response){
	    	   $scope.error=response.data.responseMessage;
	    	   $location.path('login');
	    	 });
			   $scope.notes=notes;
	        }*/
	    	   //reminder checker
			   $interval(function () {   
		        for (var i = 0; i < response.data.length; i++) {
		         if(response.data[i].reminder) {
		           var date=new Date(response.data[i].reminder);
		           if ($filter('date')(date)== $filter('date')(new Date())) {
		               toastr.success(response.data[i].body, response.data[i].title);
		            }
		            }
		            }
		            },60000);
	              },function(response){
		           $scope.error=response.data.responseMessage;
		           $location.path('login');
	            });
	           }
	  /*********logic for create notes **************/
	      $scope.createNote = function() {
			    $scope.note = {};
			    var url = 'addNote'
				$scope.note.title= document.getElementById("title").innerHTML;
				$scope.note.description= document.getElementById("description").innerHTML;
				
				var notes = homeService.service(url,'POST',$scope.note);
				notes.then(function(response) {
					getNotes();
					document.getElementById("title").innerHTML = "";
					document.getElementById("description").innerHTML = "";						

				},function(response) {
					getNotes();
					$scope.error = response.data.message;
				});
	          }
	
	  /*********logic for delete the notes from homepage **************/
	        $scope.deleteNote = function(note) {
				  console.log($scope.note);
				  note.trash=true;
				  note.archive=false;
				  note.pinned=false;
				  var url='update';
				  var notes = homeService.service(url,'PUT',note);
				  notes.then(function(response) {
			      getNotes();
				 },function(response) {
					  $scope.error = response.data.message;
					});
	             }
	   /*********logic permanently delete the notes from trash**************/
             $scope.deleteForever=function(note){
				   var url='delete/'+note.id;
				   var notes = homeService.service(url,'DELETE',note);
				   notes.then(function(response) {
				   getNotes();
				  },function(response) {
					getNotes();
				    $scope.error = response.data.message;
					});
				  }
      /*********logic for  get the notes **************/
	        $scope.restoreNote=function(note){
	    		   note.trash=0;
	    		   var url='update';
	    		   var notes = homeService.service(url,'PUT',note);
	    		   notes.then(function(response) {
	    		   getNotes();
	    		  },function(response) {
	    			getNotes();
	    			$scope.error = response.data.message;
	    		  });
	    	   }
	  /*********logic for popup the page for update **************/
		
	         $scope.popUp = function(note, event) {
			        $mdDialog.show({
				    locals: {
				      data: note 
				    },
				    templateUrl: 'templates/UpdateNote.html',
				    parent: angular.element(document.description),
				    targetEvent: event,
				    clickOutsideToClose: true,
				    controllerAs: 'controller',
				    controller: mdDialogController
			      });
	           }		    	
	  /*********logic for update the notes **************/
			
			function mdDialogController($scope,$state,data) { 
				    $scope.mdDialogData = data;
			      	$scope.updateNote = function() {
			    	var url = 'update';
			    	data.title= document.getElementById("getTitle").innerHTML;
			    	data.description= document.getElementById("getDescription").innerHTML;
			  		var notes = homeService.service(url,'PUT',data);
			  		
			  		notes.then(function(response){
			  			$mdDialog.cancel();
					},function(response){
						$scope.error=response.data.responseMessage;
					});
			      	}
			    	}
	/*********logic for archives **************/
			 $scope.archive=function(note,archive){
			    	note.archive = archive;
			    	note.pinned = false;
			    	var url = 'update';
					var notes = homeService.service(url,'PUT',note)
					notes.then(function(response){
					getNotes();
					},function(response){
						$scope.error=response.data.responseMessage;
					});
			    }	
			 //pinned
			 $scope.pinned = function(note,pinned) {
					note.pinned=pinned;
					note.archive=false;
					var url = 'update';
					var notes = homeService.service(url,'PUT',note)
					notes.then(function(response){
					
					},function(response){
						$scope.error=response.data.responseMessage;
					});
			    }	
			 
     /********* Set color of a created note **************/
				$scope.colors = [ '#fff', '#ff8a80', '#ffd180', '#ffff8d','#ccff90','#a7ffeb','#80d8ff','#82b1ff','#b388ff','#f8bbd0','#d7ccc8','#cfd8dc'];
				 $scope.noteColor=function(newColor, oldColor)
				 {
					 console.log(newColor);
					 $scope.color = newColor;
				 }
				$scope.colorChanged = function(newColor, oldColor, note) {
			        note.noteColor=newColor; 
			        var url='update';
					var notes = homeService.service(url,'PUT',note);
					notes.then(function(response) {
						getNotes();
					}, function(response) {
						getNotes();
						$scope.error = response.data.message;
					});
			    }
		 /********* Make copy of a note **************/
				$scope.copy=function(note){
					note.pinned = "false";
					note.archive= "false";
					var url = 'addNote'
					var notes=homeService.service(url,'POST',note);
					notes.then(function(response){
					getNotes();
					},function(response){
					getNotes();
					$scope.error=response.data;
					});
					}
				
				/********* Reminder **************/
			    $scope.displayDialog = function (note) {
			        mdcDateTimeDialog.show({
			        time: true,
			        shortTime : true
			      })
			       .then(function (date) {
			         $scope.selectedDateTime = date;
			         note.reminder=date;
			         console.log('New Date / Time selected:', date);
			         var url='update';
				  	 var notes = homeService.service(url,'PUT',note);
				  	 notes.then(function(response) {
				  		getNotes();
				   },function(response) {
				  		getNotes();
				  	 $scope.error = response.data.message;
				  	});
			        });
			     };
		    getNotes();
	});