(function() {
    'use strict';

    angular
        .module('myApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider.state('uploadExcel', {
            parent: 'account',
            url: '/upload',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'global.menu.account.uploadExcel'
            },
            views: {
                'content@': {
                    templateUrl: 'app/account/uploadExcel/uploadExcel.html',
                    controller: 'UploadExcelController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('settings');
                    return $translate.refresh();
                }]
            }
        });
    }
})();
