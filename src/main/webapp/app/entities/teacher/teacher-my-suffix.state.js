(function() {
    'use strict';

    angular
        .module('myApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('teacher-my-suffix', {
            parent: 'entity',
            url: '/teacher-my-suffix',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'myApp.teacher.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/teacher/teachersmySuffix.html',
                    controller: 'TeacherMySuffixController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('teacher');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('teacher-my-suffix-detail', {
            parent: 'teacher-my-suffix',
            url: '/teacher-my-suffix/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'myApp.teacher.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/teacher/teacher-my-suffix-detail.html',
                    controller: 'TeacherMySuffixDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('teacher');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Teacher', function($stateParams, Teacher) {
                    return Teacher.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'teacher-my-suffix',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('teacher-my-suffix-detail.edit', {
            parent: 'teacher-my-suffix-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/teacher/teacher-my-suffix-dialog.html',
                    controller: 'TeacherMySuffixDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Teacher', function(Teacher) {
                            return Teacher.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('teacher-my-suffix.new', {
            parent: 'teacher-my-suffix',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/teacher/teacher-my-suffix-dialog.html',
                    controller: 'TeacherMySuffixDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                teacherNo: null,
                                teacherName: null,
                                teacherPhone: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('teacher-my-suffix', null, { reload: 'teacher-my-suffix' });
                }, function() {
                    $state.go('teacher-my-suffix');
                });
            }]
        })
        .state('teacher-my-suffix.edit', {
            parent: 'teacher-my-suffix',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/teacher/teacher-my-suffix-dialog.html',
                    controller: 'TeacherMySuffixDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Teacher', function(Teacher) {
                            return Teacher.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('teacher-my-suffix', null, { reload: 'teacher-my-suffix' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('teacher-my-suffix.delete', {
            parent: 'teacher-my-suffix',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/teacher/teacher-my-suffix-delete-dialog.html',
                    controller: 'TeacherMySuffixDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Teacher', function(Teacher) {
                            return Teacher.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('teacher-my-suffix', null, { reload: 'teacher-my-suffix' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
