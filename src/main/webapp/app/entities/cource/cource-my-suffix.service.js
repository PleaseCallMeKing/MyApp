(function() {
    'use strict';
    angular
        .module('myApp')
        .factory('Cource', Cource);

    Cource.$inject = ['$resource'];

    function Cource ($resource) {
        var resourceUrl =  'api/cources/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                    }
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    }
})();
