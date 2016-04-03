// Created by baihuibo on 16/3/24.
System.register(["annotation", "util"], function(exports_1, context_1) {
    "use strict";
    var __moduleName = context_1 && context_1.id;
    var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
        var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
        if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
        else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
        return c > 3 && r && Object.defineProperty(target, key, r), r;
    };
    var __metadata = (this && this.__metadata) || function (k, v) {
        if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
    };
    var annotation_1, util_1;
    var name, icon, route, PageCtrl;
    return {
        setters:[
            function (annotation_1_1) {
                annotation_1 = annotation_1_1;
            },
            function (util_1_1) {
                util_1 = util_1_1;
            }],
        execute: function() {
            name = "页面1";
            icon = "icon";
            route = "/manage";
            PageCtrl = (function () {
                function PageCtrl($scope, menu, TestPaging) {
                    this.$scope = $scope;
                    this.menu = menu;
                    this.paging = {};
                    this.users = this.queryUser();
                    this.name = "hello ctrl";
                    this.paging.resource = TestPaging;
                }
                PageCtrl.prototype.order = function () {
                    var _this = this;
                    util_1.sort(this.users, function (a, b) {
                        if (_this.sortFlag) {
                            return a.age - b.age;
                        }
                        return b.age - a.age;
                    });
                    this.sortFlag = !this.sortFlag;
                };
                PageCtrl.prototype.queryUser = function () {
                    var users = [];
                    for (var i = 0; i < 5; i++) {
                        var user = {
                            age: Math.ceil(Math.random() * 100),
                            name: Math.random().toString(32).slice(2)
                        };
                        users.push(user);
                    }
                    return users;
                };
                PageCtrl = __decorate([
                    annotation_1.Route({
                        route: route,
                        controllerAs: "T",
                        templateUrl: "scripts/views/page/page.html"
                    }),
                    annotation_1.Menu({ icon: icon, name: name, route: route }),
                    annotation_1.Controller, 
                    __metadata('design:paramtypes', [Object, Object, Object])
                ], PageCtrl);
                return PageCtrl;
            }());
            exports_1("PageCtrl", PageCtrl);
        }
    }
});
//# sourceMappingURL=PageCtrl.js.map