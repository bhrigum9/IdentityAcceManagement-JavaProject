(function() {
	'use strict';

	angular.module('app').controller('HomeController', HomeController);

	HomeController.$inject = ['UserService', '$location', '$scope',
			'$rootScope'];
	function HomeController(UserService, $location, $scope, $rootScope) {

		var vm = this;
		vm.allUsers = [];
		vm.deleteUser = deleteUser;
		vm.updateDetail = updateDetail;
		vm.searchString = searchString;

		initController();

		function initController() {
			loadAllUsers();
			vm.user = $rootScope.globals.currentUser;

		}

		function loadAllUsers() {
			UserService.GetAll().then(function(users) {
				vm.allUsers = users;

			});
		}
		function searchString(value) {
			UserService.GetBySearchString(value).then(function(users) {
				vm.allUsers = users;

			});
		}

		function deleteUser() {
			UserService.Delete(vm.user.userid).then(function() {
			});
		}
		function updateDetail() {
			$location.path('/update');

		}
	}

})();