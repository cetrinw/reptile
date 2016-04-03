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
             * 分页
             * @examples
             *   html:
             *   <ul>
             *       <li ng-repeat="item in option.resultList">{{item}}</li>
             *   </ul>
             *
             *   <remote-paging option="option"></remote-paging>
             *
             *   script:
             *   class TestCtrl{
             *      public option:PagingOption = {};//分页配置
             *      constructor(public TestResource) {
             *          this.option.resource = TestResource;//赋值给分页配置
             *      }
             *   }
             *
             */
            app_1.default.directive("remotePaging", function () {
                var defaults = {
                    method: 'get',
                    limit: 10,
                    total: 0,
                    resultList: [],
                    limitList: [10, 20, 30, 40, 50],
                    pagingSize: 5,
                    params: null,
                    hideDesc: false,
                    invokeParam: "paging",
                    reloadAll: false
                };
                return {
                    templateUrl: 'scripts/__base/directives/paging/paging.html',
                    scope: {
                        option: "=" //配置对象 {Paging}
                    },
                    link: function (scope, el, attr) {
                        var option = scope.option = lodash_1.default.defaults(scope.option || {}, defaults);
                        var pagingSize = option.pagingSize;
                        scope.$watch('option.pagingSize', function (newVal, oldVal) {
                            if (oldVal && newVal != oldVal) {
                                pagingSize = newVal;
                                option.goToPage('first', true);
                            }
                        });
                        var limit = option.limit;
                        scope.$watch('option.limit', function (newVal, oldVal) {
                            if (oldVal && newVal != oldVal) {
                                limit = newVal;
                                option.goToPage('first', true);
                            }
                        });
                        var loading;
                        //读取对应页面的数据
                        scope.goToPage = function (page, reload) {
                            if (page < 0 || (option.totalPage && page >= option.totalPage) || (page == option.currentPage && !reload)) {
                                return;
                            }
                            var data = {
                                current: page,
                                limit: limit,
                                method: option.method,
                                reload: option.reloadAll || !!reload,
                                total: option.total,
                                params: angular.toJson(option.params || {})
                            };
                            loading = true;
                            option.resource[option.method](option.invokeParam, data, function (result) {
                                option.currentPage = result.current;
                                option.total = result.total;
                                option.totalPage = result.totalPage;
                                option.resultList = lodash_1.default.map(result.data || [], function (item) {
                                    return new option.resource(item);
                                });
                                initPageList(option.totalPage, option.currentPage);
                                loading = false;
                            }, function () {
                                loading = false;
                            });
                        };
                        //监听是否有resource资源准备就绪
                        scope.$watch('option.resource', function () {
                            option.resource && scope.goToPage(0, true);
                        });
                        //刷新数据
                        option.goToPage = function (page, reload) {
                            if (!option.resource || loading)
                                return; //不要重复加载,跳出方法
                            switch (page) {
                                case "first":
                                    scope.goToPage(0, reload);
                                    break;
                                case "last":
                                    scope.goToPage(option.totalPage - 1, reload);
                                    break;
                                default:
                                    scope.goToPage(option.currentPage, reload);
                            }
                        };
                        //重新加载
                        option.reload = function (reload) {
                            scope.goToPage(option.currentPage, reload);
                        };
                        //读取显示范围
                        scope.getDataRange = function (to) {
                            var currentPage = option.currentPage, limit = option.limit, total = option.total;
                            if (to) {
                                return currentPage * limit + limit > total ? total : currentPage * limit + limit; //结束
                            }
                            else {
                                //from
                                return currentPage * limit + 1; //开始
                            }
                        };
                        var startFix, endFix, list = [];
                        function initPageList(totalPage, currentPage) {
                            list = scope.pageList = [];
                            startFix = endFix = Math.floor(pagingSize / 2);
                            var test = startFix * 2 + 1;
                            if (test > pagingSize)
                                startFix -= 1;
                            if (test < pagingSize)
                                endFix += 1;
                            var _sf = 0, _ef = 0, start = currentPage - startFix, end = endFix + currentPage + 1;
                            if (start < 0) {
                                _sf = 0 - start;
                                start = 0;
                            }
                            if (end >= totalPage) {
                                _ef = end - totalPage;
                                end = totalPage;
                            }
                            start = start - _ef;
                            end = end + _sf;
                            start = start < 0 ? 0 : start;
                            end = end > totalPage ? totalPage : end;
                            for (; start < end; start++) {
                                list.push(start);
                            }
                        }
                    }
                };
            });
        }
    }
});
//# sourceMappingURL=paging.js.map