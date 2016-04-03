System.register(["../__base/annotation", "../__base/menu"], function(exports_1, context_1) {
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
            function (annotation_1_1) {
                exportStar_1(annotation_1_1);
            },
            function (_1) {}],
        execute: function() {
        }
    }
});
//# sourceMappingURL=annotation.js.map