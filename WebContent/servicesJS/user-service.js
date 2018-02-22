(function () {
    'use strict';

    angular
        .module('app')
        .factory('UserService', UserService);

    UserService.$inject = ['$http'];
    function UserService($http) {
        var service = {};

        service.GetAll = GetAll;
        service.GetById = GetById;
        service.GetByUsername = GetByUsername;
        service.Create = Create;
        service.Update = Update;
        service.Delete = Delete;
        service.GetBySearchString = GetBySearchString;

        return service;

        function GetAll() {
            return $http.get('/iamProject/rest/UserService/users').then(handleSuccess, handleError('Error getting all users'));
        }

        function GetById(id) {
            return $http.get('/iamProject/rest/UserService/id/' + id).then(handleSuccess, handleError('Error getting user by id'));
        }
// for next level requirement
       function GetByUsername(username) {
         return $http.get('/api/users/' + username).then(handleSuccess,handleError('Error getting user by username'));
       }
        
        function GetBySearchString(value) {
            return $http.get('/iamProject/rest/UserService/search/' + value).then(handleSuccess, handleError('Error getting search by value'));
        }


        function Create(user) {
            return $http.post('/iamProject/rest/UserService/create', user).then(function(response) {
            	response = {
						success : true
					};
                return response.data;
            })
            .catch(function(error) {
                $log.error('ERROR: User Creation', error);
                throw error;
            });
            
            
        }

        function Update(user) {
            return $http.put('/iamProject/rest/UserService/update' , user).then(handleSuccess, handleError('Error updating user'));
        }

        function Delete(id) {
            return $http.delete('/iamProject/rest/UserService/delete/' + id).then(handleSuccess, handleError('Error deleting user'));
        }

        // private functions

        function handleSuccess(response) {
            return response.data;
        }

        function handleError(error) {
            return function () {
                return { success: false, message: error };
            };
        }
    }

})();