(function() {
    'use strict';

    angular
        .module('myApp')
        .controller('TeacherMySuffixDialogController', TeacherMySuffixDialogController);

    TeacherMySuffixDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', '$q', 'entity', 'Teacher', 'Cource', 'Student'];

    function TeacherMySuffixDialogController ($timeout, $scope, $stateParams, $uibModalInstance, $q, entity, Teacher, Cource, Student) {
        var vm = this;

        vm.teacher = entity;
        vm.clear = clear;
        vm.save = save;
        vm.cources = Cource.query({filter: 'teacher(teacherno)-is-null'});
        $q.all([vm.teacher.$promise, vm.cources.$promise]).then(function() {
            if (!vm.teacher.courceId) {
                return $q.reject();
            }
            return Cource.get({id : vm.teacher.courceId}).$promise;
        }).then(function(cource) {
            vm.cources.push(cource);
        });
        vm.students = Student.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.teacher.id !== null) {
                Teacher.update(vm.teacher, onSaveSuccess, onSaveError);
            } else {
                Teacher.save(vm.teacher, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('myApp:teacherUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
