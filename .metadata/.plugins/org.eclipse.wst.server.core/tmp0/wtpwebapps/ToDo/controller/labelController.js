/*var todoApp = angular.module("todoApp");
todoApp.controller('label+Controller',
		function($scope, $state, fileReader, homeService, $mdSidenav, $mdDialog,$http, $location) {

///////////////////////////// create Label//////////////////////////////
		
			$scope.createLabel=function($event,user){
		    	  $mdDialog.show({
		    		  locals: {
		    		        dataToPass: user 
		    		      },
		    		 templateUrl : 'templates/Labels.html',
		    		 parent : angular.element(document.description),
		    		 targetEvent : event,
		    		 clickOutsideToClose: true,
		    		 controllerAs : 'controller',
		    		 controller : createLabelController
		    	  });
		      }
		      
			 function createLabelController($scope,dataToPass){
		    	  $scope.userlabel=dataToPass;
		    	  console.log(" $scope.userlabel "+ $scope.userlabel);
		    	  $scope.createLabel=function(labelName){
		    		  $scope.label={};
		    		  $scope.label.name=labelName;
		    		  url = 'createLabel';
		    		  
		    		  var createLabel= homeService.service(url,'POST',$scope.label)
		    		  createLabel.then(function(response){
		    			  $state.reload();
		    			  $mdDialog.hide();
		    		  },function(response){
		    			  $scope.error = response.data.responseMessage;
		    		  })
		    	  }
		      }
			
			 $scope.labelToggle=function(note,label){
		    	  console.log("clicked");
		    	  
		    	  var index = -1;
		    	  var i=0;
					for ( i = 0; i<note.labels.length;i++) {
						if (note.labels[i].name === label.name) {
							index = i;
							break;
						}
					}

					if (index == -1) {
						note.labels.push(label);
						update(note);
					} else {
						note.labels.splice(index, 1);
						update(note);
					}
		    	  
		      }
		      
				$scope.checkboxCheck = function(note, label) {
					
					var labels = note.labels;
					for (var i = 0; i < labels.length; i++) {
						if (labels[i].name === label.name) {
							return true;
						}
					}
					return false;
				}
				
				////////////////////////DELETE LABEL//////////////////
				$scope.deleteLabel=function(label){
					var url = 'deleteLabel';
					var deletelabel = homeService.label(url,'POST',label);
					deletelabel.then(function(response){
					$state.reload();
					},function(response){
					})
				}
				
				////////////////////////REMOVE LABEL//////////////////

				$scope.removeLabel=function(note,label){
					var removeLabel = note.labels;
					var indexOfLabel = removeLabel.indexOf(label);
					removeLabel.splice(indexOfLabel, 1);
					update(note);
				}
			getNotes();
		});*/