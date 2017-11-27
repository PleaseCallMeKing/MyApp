(function() {
    'use strict';

    angular
        .module('myApp')
        .controller('UploadExcelController', UploadExcelController);

    UploadExcelController.$inject = ['Principal', 'Auth', 'JhiLanguageService', '$translate'];

    function UploadExcelController (Principal, Auth, JhiLanguageService, $translate) {
        var vm = this;

        vm.error = null;
        vm.settingsAccount = null;
        vm.success = null;

        var copyAccount = function (account) {
            return {
                activated: account.activated,
                email: account.email,
                firstName: account.firstName,
                langKey: account.langKey,
                lastName: account.lastName,
                login: account.login
            };
        };

        Principal.identity().then(function (account) {
            vm.settingsAccount = copyAccount(account);
        });
    }
})();
