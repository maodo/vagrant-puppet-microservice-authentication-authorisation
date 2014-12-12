'use strict';

/* Controllers */

var appControllers = angular.module('appControllers', []);

appControllers.controller('personsCtrl', ['$scope', 'Person',
  function($scope, Person) {
    $scope.persons = Person.query();
  }]);

appControllers.controller('personCtrl', ['$scope', '$location', '$routeParams', 'Person',
  function($scope, $location, $routeParams,  Person) {
    $scope.person = Person.get({ id: $routeParams.id });

    $scope.update = function(person) {
    	person.$update();
    	$location.path("/persons/" + $routeParams.id);
    };

  }]);