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
            /**
             * @examples
             *  <iframe frame-src="{{T.src}}"></iframe>
             *
             *  class TestCtrl{
             *    public src:string = '';
             *
             *    constructor(){
             *      this.src = 'http://host/to/path/to/file'
             *    }
             *  }
             */
            app_1.default.directive('frameSrc', function () {
                return function (scope, iframe, attr) {
                    if (iframe.is('iframe')) {
                        attr.$observe('iframeSrc', function (val) {
                            iframe.attr('src', val || 'about:blank');
                        });
                    }
                    else {
                        console.warn('not iframe tag');
                    }
                };
            });
        }
    }
});
//# sourceMappingURL=frame-src.js.map