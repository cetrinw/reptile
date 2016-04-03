System.register(["angular-resource", "angular-route", "lodash"], function(exports_1, context_1) {
    "use strict";
    var __moduleName = context_1 && context_1.id;
    var lodash_1;
    var baseModule;
    return {
        setters:[
            function (_1) {},
            function (_2) {},
            function (lodash_1_1) {
                lodash_1 = lodash_1_1;
            }],
        execute: function() {
            exports_1("baseModule", baseModule = angular.module('base-module', ['ngResource', 'ngRoute']));
            //虚拟dom元素,递归优化
            baseModule.factory('RecursionHelper', function ($compile) {
                return function () {
                    return function (element, link) {
                        // Normalize the link parameter
                        if (lodash_1.default.isFunction(link)) {
                            link = { post: link };
                        }
                        // Break the recursion loop by removing the contents
                        var contents = element.contents().remove();
                        var compiledContents;
                        return {
                            pre: (link && link.pre) ? link.pre : null,
                            /**
                             * Compiles and re-adds the contents
                             */
                            post: function () {
                                var args = [];
                                for (var _i = 0; _i < arguments.length; _i++) {
                                    args[_i - 0] = arguments[_i];
                                }
                                var scope = args[0], element = args[1];
                                // Compile the contents
                                if (!compiledContents) {
                                    compiledContents = $compile(contents);
                                }
                                // Re-add the compiled contents to the element
                                compiledContents(scope, function (clone) {
                                    element.append(clone);
                                });
                                // Call the post-linking function, if any
                                if (link && link.post) {
                                    link.post.apply(link, args);
                                }
                            }
                        };
                    };
                };
            });
            baseModule.config(function ($httpProvider, $resourceProvider) {
                var defaults, headers, key, value;
                defaults = $httpProvider.defaults;
                headers = defaults.headers;
                defaults.transformRequest = function (data) {
                    if (data === void 0) {
                        return data;
                    }
                    else {
                        return $.param(angular.fromJson(angular.toJson(data)));
                    }
                };
                value = 'application/x-www-form-urlencoded; charset=UTF-8';
                key = 'Content-Type';
                headers.post[key] = value;
                headers.put[key] = value;
                headers.patch[key] = value;
                var DefaultActions = $resourceProvider.defaults.actions;
                DefaultActions['queryList'] = { method: 'GET', isArray: true };
                DefaultActions['put'] = { method: 'PUT' };
                DefaultActions['post'] = { method: 'POST' };
            });
            angular.module('ngResource').factory('resource', function ($resource) {
                var ref = ['get', 'query', 'queryList', 'delete', 'remove'];
                return function resourceFactory(url, defaultParams, actions, options) {
                    var cls = $resource(url, defaultParams, actions, options);
                    function merge(m, d) {
                        if (angular.isString(m)) {
                            m = (_a = {}, _a[m] = true, _a);
                        }
                        return angular.extend({}, m, d);
                        var _a;
                    }
                    ref.forEach(function (name) {
                        if (cls[name]) {
                            var oldFn = cls[name];
                            cls[name] = function (a1, a2, a3, a4) {
                                var params, data, success, error;
                                if (!arguments.length) {
                                    return oldFn.call(this);
                                }
                                switch (arguments.length) {
                                    case 4:
                                        error = a4;
                                        success = a3;
                                    case 3:
                                    case 2:
                                        if (angular.isFunction(a2)) {
                                            if (angular.isFunction(a1)) {
                                                success = a1;
                                                error = a2;
                                                break;
                                            }
                                            success = a2;
                                            error = a3;
                                        }
                                        else {
                                            params = a1;
                                            data = a2;
                                            success = a3;
                                        }
                                        break;
                                    case 1:
                                        if (angular.isFunction(a1)) {
                                            success = a1;
                                        }
                                        else {
                                            data = a1;
                                        }
                                        break;
                                }
                                return oldFn.call(this, merge(params, data), success, error);
                            };
                        }
                    });
                    return cls;
                };
            });
        }
    }
});
//# sourceMappingURL=base-module.js.map