angular.module('ionicApp', ['ionic', 'ionicApp.controllers', 'ionicApp.services', 'toaster', 'ngCordova'])
    .run(["$ionicPlatform", "$state", "$rootScope", "$ionicHistory", "$ionicLoading", 'toaster',
        function ($ionicPlatform, $state, $rootScope, $ionicHistory, $ionicLoading, toaster) {

            $ionicPlatform.registerBackButtonAction(function (e) {
                if ($state.current.name.indexOf("siteManage") > -1) {
                    var sendData = {
                        actionType: rnConfig.closeWindow,
                        info: {}
                    };
                    if (window.WebViewBridge) {
                        window.WebViewBridge.send(JSON.stringify(sendData));
                    }
                    ;

                } else {
                    $ionicHistory.backView() ? ($rootScope.isBack = !0, $ionicHistory.goBack()) : (console.log("返回故障---"), $state.go("siteManage"));
                }
            }, 101);

            $rootScope.showLoad = function () {
                $ionicLoading.show({
                    duration: 30e3
                })
            }

            $rootScope.hideLoad = function () {
                $ionicLoading.hide();
            }
            $rootScope.showError = function () {
                console.error("加载失败");
                toaster.pop('', "加载失败", "", 2000);

            }
            $rootScope.showToast = function (msg) {
                console.log(msg)
                toaster.pop('', msg, "", 2000);

            }
            $rootScope.getUserInfo = function () {
                var userInfo = window.sessionStorage["userInfo"];

                $rootScope.userInfo = userInfo ? JSON.parse(userInfo) : null
            }
            $rootScope.getUserInfo();

        }

    ]).config(["$stateProvider", "$urlRouterProvider", "$ionicConfigProvider", "$compileProvider", function ($stateProvider, $urlRouterProvider, $ionicConfigProvider, $compileProvider) {
    $ionicConfigProvider.platform.ios.tabs.style("standard");
    $ionicConfigProvider.platform.ios.tabs.position("bottom");
    $ionicConfigProvider.platform.android.tabs.style("standard");
    $ionicConfigProvider.platform.android.tabs.position("bottom");
    $ionicConfigProvider.platform.ios.navBar.alignTitle("center");
    $ionicConfigProvider.platform.android.navBar.alignTitle("center");
    $ionicConfigProvider.platform.ios.views.transition("ios");
    $ionicConfigProvider.platform.android.views.transition("android");
    $ionicConfigProvider.platform.android.scrolling.jsScrolling(true);
    $ionicConfigProvider.backButton.text("");
    $ionicConfigProvider.backButton.previousTitleText(!1);
    $ionicConfigProvider.views.maxCache(5);
    $compileProvider.aHrefSanitizationWhitelist(/^\s*(https?|http|ftp|mailto|chrome-extension|tel|file):/);
    $stateProvider
        .state("siteManage", {   //现场管理
            url: "/siteManage",
            templateUrl: "templates/siteManage.html",
            controller: "siteManageCtrl"
        })

        .state("project", {  //现场拍照
            url: "/project/:type",
            templateUrl: "templates/project.html",
            controller: "projectCtrl"
        })
        .state("scene-photo", {  //现场拍照详情
            url: "/scene-photo/:id/:name",
            templateUrl: "templates/scene-photo.html",
            controller: "scenePhotoCtrl"
        })
        .state("scene-photo-add", {  //现场拍照添加
            url: "/scene-photo-ddd/:id/:name",
            templateUrl: "templates/scene-photo-add.html",
            controller: "scenePhotoAddCtrl"
        })
        .state("doc-upload", {  //文档上传
            url: "/doc-upload/:id",
            templateUrl: "templates/doc-upload.html",
            controller: "docUploadCtrl"
        })
        .state("doc-browsing", {  //文档浏览
            url: "/doc-browsing/:id/:name",
            templateUrl: "templates/doc-browsing.html",
            controller: "docBrowsingCtrl"
        })
        .state("doc-browsing-des", {  //文档上传
            url: "/doc-browsing-des/:id",
            templateUrl: "templates/doc-browsing-des.html",
            controller: "docBrowsingDesCtrl"
        })
        .state("project-schedule", {    //施工进度列表
            url: "/project-schedule/:id/:name",
            templateUrl: "templates/project-schedule.html",
            controller: "projectScheduleCtrl"
        })
        .state("project-schedule-add", {  //施工进度添加
            url: "/project-schedule-add/:id/:name",
            templateUrl: "templates/project-schedule-add.html",
            controller: "projectScheduleAddCtrl"
        })
        .state("project-schedule-des", {  //施工进度详情
            url: "/project-schedule-des",
            templateUrl: "templates/project-schedule-des.html",
            controller: "projectScheduleDesCtrl"
        })

    if (account) {
        $urlRouterProvider.otherwise("siteManage");
    }
}]);
