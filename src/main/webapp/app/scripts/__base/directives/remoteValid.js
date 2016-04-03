System.register(["app", "lodash"], function(exports_1, context_1) {
    "use strict";
    var __moduleName = context_1 && context_1.id;
    var app_1, lodash_1;
    return {
        setters:[
            function (app_1_1) {
                app_1 = app_1_1;
            },
            function (lodash_1_1) {
                lodash_1 = lodash_1_1;
            }],
        execute: function() {
            /**
             * 远程校验
             * @examples
             *  <form name="fr">
             *     <input type="text" name="username" ng-model="T.name" remote-valid="validOption">
             *
             *     <p> username.$error.remoteValid : {{fr.username.$error.remoteValid}} </p>
             *     <p> username.$valid : {{fr.username.$valid}} </p>
             *     <p> username.$invalid : {{fr.username.$invalid}} </p>
             *  </form>
             *
             *     class TestCtrl{
             *       public validOption:RemoteValidOption = {};
             *       public name:string = '';
             *       constructor(TestResource){
             *         this.validOption.resource = TestResource;
             *       }
             *     }
             */
            app_1.default.directive('remoteValid', function ($log) {
                var defaults = {
                    method: 'get',
                    check: null,
                    params: {},
                    invokeParam: {}
                };
                var name = "remoteValid";
                return {
                    restrict: 'A',
                    require: '?ngModel',
                    scope: { option: '=remoteValid' },
                    link: function (scope, el, attr, ctrl) {
                        if (!ctrl)
                            return;
                        var option = lodash_1.default.defaults(scope.option || {}, defaults);
                        function validData(newVal) {
                            option.params.value = newVal;
                            option.resource[option.method](option.invokeParam, option.params, function (data) {
                                var pass;
                                if (option.check && lodash_1.default.isFunction(option.check)) {
                                    pass = option.check(data);
                                }
                                else {
                                    pass = !!data.result;
                                }
                                ctrl.$setValidity(name, pass);
                            }, function () {
                                ctrl.$setValidity(name, false);
                            });
                            return newVal;
                        }
                        el.on('paste', false);
                        ctrl.$parsers.push(validData);
                        scope.$watch(attr.ngModel, function (newVal, oldVal) {
                            if (!newVal || newVal != oldVal) {
                                ctrl.$setValidity(name, true);
                            }
                        });
                    }
                };
            });
        }
    }
});
//# sourceMappingURL=remoteValid.js.map