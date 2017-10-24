(function() {
    'use strict';

    angular
        .module('myApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('cource-my-suffix', {
            parent: 'entity',
            url: '/cource-my-suffix',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'myApp.cource.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/cource/courcesmySuffix.html',
                    controller: 'CourceMySuffixController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('cource');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('cource-my-suffix-detail', {
            parent: 'cource-my-suffix',
            url: '/cource-my-suffix/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'myApp.cource.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/cource/cource-my-suffix-detail.html',
                    controller: 'CourceMySuffixDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('cource');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Cource', function($stateParams, Cource) {
                    return Cource.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'cource-my-suffix',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('cource-my-suffix-detail.edit', {
            parent: 'cource-my-suffix-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/cource/cource-my-suffix-dialog.html',
                    controller: 'CourceMySuffixDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Cource', function(Cource) {
                            return Cource.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('cource-my-suffix.new', {
            parent: 'cource-my-suffix',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/cource/cource-my-suffix-dialog.html',
                    controller: 'CourceMySuffixDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                courceId: null,
                                courceName: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('cource-my-suffix', null, { reload: 'cource-my-suffix' });
                }, function() {
                    $state.go('cource-my-suffix');
                });
            }]
        })
        .state('cource-my-suffix.edit', {
            parent: 'cource-my-suffix',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/cource/cource-my-suffix-dialog.html',
                    controller: 'CourceMySuffixDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Cource', function(Cource) {
                            return Cource.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('cource-my-suffix', null, { reload: 'cource-my-suffix' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('cource-my-suffix.delete', {
            parent: 'cource-my-suffix',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/cource/cource-my-suffix-delete-dialog.html',
                    controller: 'CourceMySuffixDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Cource', function(Cource) {
                            return Cource.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('cource-my-suffix', null, { reload: 'cource-my-suffix' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
