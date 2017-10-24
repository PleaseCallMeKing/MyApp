(function() {
    'use strict';

    angular
        .module('myApp')
        .controller('StudentMySuffixDetailController', StudentMySuffixDetailController);

    StudentMySuffixDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Student', 'Cource', 'Teacher'];

    function StudentMySuffixDetailController($scope, $rootScope, $stateParams, previousState, entity, Student, Cource, Teacher) {
        var vm = this;

        vm.student = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('myApp:studentUpdate', function(event, result) {
            vm.student = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
