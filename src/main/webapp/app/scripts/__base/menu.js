System.register(["app"], function(exports_1, context_1) {
    "use strict";
    var __moduleName = context_1 && context_1.id;
    var app_1;
    return {
        setters:[
            function (app_1_1) {
                app_1 = app_1_1;
            }],
        execute: function() {
            app_1.default.provider("menu", (function () {
                function menu() {
                    this.menus = [];
                }
                menu.prototype.register = function (menu) {
                    menu && this.menus.push(menu);
                };
                menu.prototype.$get = function () {
                    return this.menus;
                };
                return menu;
            }()));
            app_1.default.factory("MenuServer", function (resource) {
                return resource('data/menu.json');
            });
        }
    }
});
//# sourceMappingURL=menu.js.map