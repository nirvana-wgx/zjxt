angular.module('ionicApp', ['ionic', 'ionicApp.controllers', 'ionicApp.services', 'toaster'])
    .run(["$ionicPlatform", "$rootScope", "$ionicLoading", 'toaster',
        function ($ionicPlatform, $rootScope, $ionicLoading, toaster) {

            $ionicPlatform.registerBackButtonAction(function (e) {
                var sendData = {
                    actionType: rnConfig.closeWindow,
                    info: {}
                };
                if (window.WebViewBridge) {
                    window.WebViewBridge.send(JSON.stringify(sendData));
                }
                ;
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
        .state("threeCategoryRegister-info", { //三类人员登记
            url: "/threeCategoryRegister-info",
            templateUrl: "templates/threeCategoryRegister-info.html",
            controller: "threeCategoryRegisterInfoCtrl"
        })
        .state("constructionEquipmentRegister-info", { //施工机具/仪表登记
            url: "/constructionEquipmentRegister-info",
            templateUrl: "templates/constructionEquipmentRegister-info.html",
            controller: "constructionEquipmentRegisterInfoCtrl"
        })
        .state("keyManagePersonRegister-info", { //主要管理人员登记
            url: "/keyManagePersonRegister-info",
            templateUrl: "templates/keyManagePersonRegister-info.html",
            controller: "keyManagePersonRegisterInfoCtrl"
        })
        .state("specialOperatorsRegister-info", { //特种作业人员登记
            url: "/specialOperatorsRegister-info",
            templateUrl: "templates/specialOperatorsRegister-info.html",
            controller: "specialOperatorsRegisterInfoCtrl"
        })
        .state("staff", {
            url: "/staff",
            templateUrl: "templates/staff.html",
            controller: "staffCtrl"
        })
        .state("notice-info", {
            url: "/notice-info",
            templateUrl: "templates/notice-info.html",
            controller: "noticeInfoCtrl"
        })
    if (paramType == "staff") {
        $urlRouterProvider.otherwise("staff");
    }
    if (paramType == "threeCategoryRegister") {
        $urlRouterProvider.otherwise("threeCategoryRegister-info");
    }
    if (paramType == "constructionEquipmentRegister") {
        $urlRouterProvider.otherwise("constructionEquipmentRegister-info");
    }
    if (paramType == "keyManagePersonRegister") {
        $urlRouterProvider.otherwise("keyManagePersonRegister-info");
    }
    if (paramType == "specialOperatorsRegister") {
        $urlRouterProvider.otherwise("specialOperatorsRegister-info");
    }
    if (paramType == "noticeinfo") {
        $urlRouterProvider.otherwise("notice-info");
    }
}]);
