(function() {
    'use strict';

    angular
        .module('myApp')
        .controller('TeacherMySuffixDetailController', TeacherMySuffixDetailController);

    TeacherMySuffixDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Teacher', 'Cource', 'Student'];

    function TeacherMySuffixDetailController($scope, $rootScope, $stateParams, previousState, entity, Teacher, Cource, Student) {
        var vm = this;

        vm.teacher = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('myApp:teacherUpdate', function(event, result) {
            vm.teacher = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
