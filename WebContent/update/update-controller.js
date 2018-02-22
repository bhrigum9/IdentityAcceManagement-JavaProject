(function() {
	'use strict';

	angular.module('app').controller('UpdateController', UpdateController);

	UpdateController.$inject = ['UserService', '$location', '$scope',
			'$rootScope'];
	function UpdateController(UserService, $location, $scope, $rootScope) {

		var vm = this;
		vm.thisuser = [];
		var id = $rootScope.globals.currentUser.userid;
		vm.updateUser = updateUser;

		initController();

		function initController() {
			loadCurrentUser(id);

		}

		function loadCurrentUser(id) {
			UserService.GetById(id).then(function(user) {
				vm.thisuser = user;
				console.log(vm.thisuser);

			});
		}

		function updateUser() {
			UserService.Update(vm.thisuser).then(function(user) {
				vm.thisuser = user;
				$scope.status = "User Updated Successfully!!!";
				// UserService.GetAll();
			});
			$location.path('/home');

		}

	}
})();