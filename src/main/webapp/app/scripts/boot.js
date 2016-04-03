System.register(["./common/directives", "./common/filters", "./common/servers", "./common/annotation", './views/all'], function(exports_1, context_1) {
    "use strict";
    var __moduleName = context_1 && context_1.id;
    var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
        var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
        if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
        else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
        return c > 3 && r && Object.defineProperty(target, key, r), r;
    };
    var __metadata = (this && this.__metadata) || function (k, v) {
        if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
    };
    var annotation_1;
    var Run;
    return {
        setters:[
            function (_1) {},
            function (_2) {},
            function (_3) {},
            function (annotation_1_1) {
                annotation_1 = annotation_1_1;
            },
            function (_4) {}],
        execute: function() {
            Run = (function () {
                function Run($rootScope, $route, MenuServer) {
                    //菜单注入
                    $rootScope.menus = MenuServer.query();
                    $rootScope.menus.$promise.then(function (list) {
                        angular.forEach($route.routes, function (option, route) {
                            if (!query(list, route)) {
                                delete $route.routes[route];
                            }
                        });
                        function query(list, route) {
                            for (var i = 0; i < list.length; i++) {
                                var item = list[i];
                                var path = item.route;
                                if (path) {
                                    var path2 = (path[path.length - 1] == '/') ? path.substr(0, path.length - 1) : path + '/';
                                    if (path === route || path2 === route) {
                                        return item;
                                    }
                                }
                                if (item.childs) {
                                    return query(item.childs, route);
                                }
                            }
                            return null;
                        }
                    });
                    //设置标题
                    $rootScope.setTitle = function (title) {
                        $rootScope.pageTitle = title;
                    };
                    $rootScope.$on('$routeChangeStart', function (e, route) {
                        $rootScope.setTitle(null);
                    });
                    var activeRoute = '';
                    $rootScope.$on('$routeChangeSuccess', function (e, current) {
                        if (current.$$route) {
                            activeRoute = current.$$route.originalPath;
                        }
                    });
                    //页面是否激活
                    $rootScope.isActive = function (route) {
                        return activeRoute === route;
                    };
                }
                Run = __decorate([
                    annotation_1.BeforeRun, 
                    __metadata('design:paramtypes', [Object, Object, Object])
                ], Run);
                return Run;
            }());
            angular.bootstrap(document, ['app']);
        }
    }
});
//# sourceMappingURL=boot.js.map