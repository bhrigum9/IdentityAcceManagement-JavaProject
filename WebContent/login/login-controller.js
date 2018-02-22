(function() {
	'use strict';

	angular.module('app').controller('LoginController', LoginController);

	LoginController.$inject = ['$scope', '$rootScope', '$location',
			'AuthenticationService', 'FlashService'];
	function LoginController($scope, $rootScope, $location,
			AuthenticationService, FlashService) {
		$scope = this;

		$scope.login = login;

		(function initController() {
			// reset login status
			AuthenticationService.ClearCredentials();
		})();

		function login() {
			$scope.dataLoading = true;
			AuthenticationService.Login($scope.username, $scope.password,
					function(response) {
						if (response.success) {
							AuthenticationService.SetCredentials(
									$scope.username, $scope.password,
									response.userid);
							$location.path('/home');
						} else {
							FlashService.Error(response.message);
							$scope.dataLoading = false;
						}
					});
		};

	}

})();