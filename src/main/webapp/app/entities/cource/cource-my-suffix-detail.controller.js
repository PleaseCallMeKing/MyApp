(function() {
    'use strict';

    angular
        .module('myApp')
        .controller('CourceMySuffixDetailController', CourceMySuffixDetailController);

    CourceMySuffixDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Cource', 'Teacher', 'Student'];

    function CourceMySuffixDetailController($scope, $rootScope, $stateParams, previousState, entity, Cource, Teacher, Student) {
        var vm = this;

        vm.cource = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('myApp:courceUpdate', function(event, result) {
            vm.cource = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
