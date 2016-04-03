System.register(["app", "lodash"], function(exports_1, context_1) {
    "use strict";
    var __moduleName = context_1 && context_1.id;
    var app_1, lodash_1;
    //运行
    function BeforeRun(target) {
        app_1.default.run(target);
    }
    exports_1("BeforeRun", BeforeRun);
    //config
    function BeforeConfig(target) {
        app_1.default.config(target);
    }
    exports_1("BeforeConfig", BeforeConfig);
    //设置路由
    function Route(option) {
        return function (target) {
            if (!option.controller) {
                option.controller = target.name;
            }
            app_1.default.config(function ($routeProvider) {
                $routeProvider.when(option.route, option);
            });
        };
    }
    exports_1("Route", Route);
    //设置为controller
    function Controller(strOrFunc) {
        if (lodash_1.default.isString(strOrFunc)) {
            return function (target) {
                app_1.default.controller(strOrFunc, target);
            };
        }
        else if (lodash_1.default.isFunction(strOrFunc)) {
            app_1.default.controller(strOrFunc.name, strOrFunc);
        }
        else {
            throw Error("@Controller 必须标注在 function or Class");
        }
    }
    exports_1("Controller", Controller);
    function Menu(menuItem) {
        app_1.default.config(function (menuProvider) {
            menuProvider.register(menuItem);
        });
    }
    exports_1("Menu", Menu);
    return {
        setters:[
            function (app_1_1) {
                app_1 = app_1_1;
            },
            function (lodash_1_1) {
                lodash_1 = lodash_1_1;
            }],
        execute: function() {
        }
    }
});
//# sourceMappingURL=annotation.js.map