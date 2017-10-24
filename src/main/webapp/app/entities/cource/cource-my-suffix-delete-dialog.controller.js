(function() {
    'use strict';

    angular
        .module('myApp')
        .controller('CourceMySuffixDeleteController',CourceMySuffixDeleteController);

    CourceMySuffixDeleteController.$inject = ['$uibModalInstance', 'entity', 'Cource'];

    function CourceMySuffixDeleteController($uibModalInstance, entity, Cource) {
        var vm = this;

        vm.cource = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Cource.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
