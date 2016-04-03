// Created by baihuibo on 16/3/29.
System.register(["./page/PageCtrl", "./page2/Page2Ctrl"], function(exports_1, context_1) {
    "use strict";
    var __moduleName = context_1 && context_1.id;
    function exportStar_1(m) {
        var exports = {};
        for(var n in m) {
            if (n !== "default") exports[n] = m[n];
        }
        exports_1(exports);
    }
    return {
        setters:[
            function (PageCtrl_1_1) {
                exportStar_1(PageCtrl_1_1);
            },
            function (Page2Ctrl_1_1) {
                exportStar_1(Page2Ctrl_1_1);
            }],
        execute: function() {
        }
    }
});
//# sourceMappingURL=all.js.map