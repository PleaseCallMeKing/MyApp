(function() {
    'use strict';

    angular
        .module('myApp')
        .controller('TeacherMySuffixController', TeacherMySuffixController);

    TeacherMySuffixController.$inject = ['Teacher'];

    function TeacherMySuffixController(Teacher) {

        var vm = this;

        vm.teachers = [];

        loadAll();

        function loadAll() {
            Teacher.query(function(result) {
                vm.teachers = result;
                vm.searchQuery = null;
            });
        }
    }
})();
