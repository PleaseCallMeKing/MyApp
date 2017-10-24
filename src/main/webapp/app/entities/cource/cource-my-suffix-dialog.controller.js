(function() {
    'use strict';

    angular
        .module('myApp')
        .controller('CourceMySuffixDialogController', CourceMySuffixDialogController);

    CourceMySuffixDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Cource', 'Teacher', 'Student'];

    function CourceMySuffixDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Cource, Teacher, Student) {
        var vm = this;

        vm.cource = entity;
        vm.clear = clear;
        vm.save = save;
        vm.teachers = Teacher.query();
        vm.students = Student.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.cource.id !== null) {
                Cource.update(vm.cource, onSaveSuccess, onSaveError);
            } else {
                Cource.save(vm.cource, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('myApp:courceUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
