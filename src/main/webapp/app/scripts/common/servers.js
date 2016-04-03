System.register(["./app"], function(exports_1, context_1) {
    "use strict";
    var __moduleName = context_1 && context_1.id;
    var app_1;
    return {
        setters:[
            function (app_1_1) {
                app_1 = app_1_1;
            }],
        execute: function() {
            app_1.default.factory("Test", function () {
                return {};
            });
            app_1.default.factory("TestPaging", function (resource) {
                return resource('data/paging.json');
            });
        }
    }
});
//# sourceMappingURL=servers.js.map