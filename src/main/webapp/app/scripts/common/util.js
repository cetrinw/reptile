//Created by baihuibo on 16/1/27.
System.register([], function(exports_1, context_1) {
    "use strict";
    var __moduleName = context_1 && context_1.id;
    function uppercase(str) {
        return String(str || "").toUpperCase();
    }
    exports_1("uppercase", uppercase);
    function sort(arr, sortFn) {
        arr.sort(sortFn);
    }
    exports_1("sort", sort);
    return {
        setters:[],
        execute: function() {
        }
    }
});
//# sourceMappingURL=util.js.map