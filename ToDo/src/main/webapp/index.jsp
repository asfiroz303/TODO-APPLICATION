<!doctype>
<html ng-app="todoApp">
<head>

	
   <link rel="stylesheet"
   href="bower_components/angular-material/angular-material.css">

</head>

<body layout>
	<!-- <div layout="row" ui-view></div> -->
	 <div layout flex ui-view></div>
</body>
<script src="bower_components/angular/angular.js"></script>
<script src="bower_components/angular-animate/angular-animate.js"></script>
<script src="bower_components/angular-aria/angular-aria.js"></script>
<script src="bower_components/angular-material/angular-material.js"></script>
<script src="bower_components/angular-ui-router/release/angular-ui-router.js"></script>
<script type="bower_components/angular-messages/angular-messages.js"></script>

<script type="text/javascript" src="scripts/app.js"></script>
<script type="text/javascript" src="controller/registrationController.js"></script>
<script type="text/javascript" src="controller/loginController.js"></script>
<script type="text/javascript" src="controller/homeController.js"></script>
<script type="text/javascript" src="controller/setPasswordController.js"></script>

<script type="text/javascript" src="services/registrationService.js"></script>
<script type="text/javascript" src="services/loginService.js"></script>
<script type="text/javascript" src="services/homeService.js"></script>
<script type="text/javascript" src="directives/homeDirectives.js"></script>
</html>
