'use strict';

/* Services */

var appServices = angular.module('appServices', ['ngResource', 'appConfig']);

appServices.factory('Person', ['$resource',
  function($resource){
    return $resource(appConfig.personServiceUrl + '/:id', { id: '@id' }, {
      update: { method:'PUT' }
    });
  }]);
