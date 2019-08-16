angular.module('ionicApp', ['ionic', 'ionicApp.controllers', 'ionicApp.services', 'toaster', 'ngCordova'])
    .run(["$ionicPlatform", "$state", "$rootScope", "$ionicHistory", "$ionicLoading", 'toaster',
        function ($ionicPlatform, $state, $rootScope, $ionicHistory, $ionicLoading, toaster) {

            $ionicPlatform.registerBackButtonAction(function (e) {
                if ($state.current.name.indexOf("purchasingManage") > -1) {
                    var sendData = {
                        actionType: rnConfig.closeWindow,
                        info: {}
                    };
                    if (window.WebViewBridge) {
                        window.WebViewBridge.send(JSON.stringify(sendData));
                    }
                    ;

                } else {
                    $ionicHistory.backView() ? ($rootScope.isBack = !0, $ionicHistory.goBack()) : (console.log("返回故障---"), $state.go("purchasingManage"));
                }
            }, 101);

            $rootScope.showLoad = function () {
                $ionicLoading.show({
                    duration: 15e3
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
        .state("purchasingManage", {  //采购管理
            url: "/purchasingManage",
            templateUrl: "templates/purchasingManage.html",
            controller: "purchasingManageCtrl"
        })
        .state("project", {  //项目
            url: "/project/:type",
            templateUrl: "templates/project.html",
            controller: "projectCtrl"
        })
        .state("purchase-schedule", {  //采购进度列表
            url: "/purchase-schedule/:id/:name",
            templateUrl: "templates/purchase-schedule.html",
            controller: "purchaseScheduleCtrl"
        })
        .state("purchase-schedule-add", {  //采购进度添加
            url: "/purchase-schedule-add/:id/:name",
            templateUrl: "templates/purchase-schedule-add.html",
            controller: "purchaseScheduleAddCtrl"
        })
        .state("purchase-schedule-des", {  //采购进度详情
            url: "/purchase-schedule-des",
            templateUrl: "templates/purchase-schedule-des.html",
            controller: "purchaseScheduleDesCtrl"
        })
        .state("monitor-log", {    //监控日志列表
            url: "/monitor-log/:id/:name",
            templateUrl: "templates/monitor-log.html",
            controller: "monitorLogCtrl"
        })
        .state("monitor-log-add", {  //添加监控日志
            url: "/monitor-log-add/:id/:name",
            templateUrl: "templates/monitor-log-add.html",
            controller: "monitorLogAddCtrl"
        })
        .state("monitor-log-des", {  //监控日志详情
            url: "/monitor-log-des",
            templateUrl: "templates/monitor-log-des.html",
            controller: "monitorLogDesCtrl"
        })

        .state("logistics-damaged", {    //物流破损列表
            url: "/logistics-damaged/:id/:name",
            templateUrl: "templates/logistics-damaged.html",
            controller: "logisticsDamagedCtrl"
        })
        .state("logistics-damaged-add", {  //物流破损添加
            url: "/logistics-damaged-add/:id/:name/:boxSingleId",
            templateUrl: "templates/logistics-damaged-add.html",
            controller: "logisticsDamagedAddCtrl"
        })
        .state("logistics-damaged-des", {  //物流破损详情
            url: "/logistics-damaged-des",
            templateUrl: "templates/logistics-damaged-des.html",
            controller: "logisticsDamagedDesCtrl"
        })
        .state("logistics-code", {
            url: "/logistics-code/:boxSingleId",
            templateUrl: "templates/logistics-code.html",
            controller: "logisticsCodeCtrl"
        })
    if (account) {
        $urlRouterProvider.otherwise("purchasingManage");
    }
}]);
