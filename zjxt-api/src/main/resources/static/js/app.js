angular.module('ionicApp', ['ionic', 'ionicApp.controllers', 'ionicApp.services','toaster', 'ngCordova'])
  .run(["$ionicPlatform", "$state", "$rootScope", "$ionicHistory", "$ionicLoading", 'toaster',
    function($ionicPlatform, $state, $rootScope,$ionicHistory, $ionicLoading, toaster) {
       
      $ionicPlatform.registerBackButtonAction(function(e) {
        if ($state.current.name.indexOf("home") > -1 ||$state.current.name.indexOf("login") > -1) {
          var sendData={
            actionType:rnConfig.closeWindow,
            info:{}
          };
          if(window.WebViewBridge) {
              window.WebViewBridge.send(JSON.stringify(sendData));
          };
      
        } else {
          $ionicHistory.backView() ? ($rootScope.isBack = !0, $ionicHistory.goBack()) : (console.log("返回故障---"), $state.go("home"));
        }
      }, 101);

      $rootScope.showLoad = function() {
        $ionicLoading.show({
          duration: 15e3
        })
      }

      $rootScope.hideLoad = function() {
        $ionicLoading.hide();
      }
      $rootScope.showError = function() {
        console.error("加载失败");
        toaster.pop('',"加载失败","",2000);
      
      }
      $rootScope.showToast = function(msg) {
        console.log(msg)
        toaster.pop('',msg,"",2000);
      
      }

      $rootScope.getUserInfo = function() {
        var userInfo = window.sessionStorage["userInfo"];
        $rootScope.userInfo = userInfo ? JSON.parse(userInfo) : null
      }
      $rootScope.getUserInfo();
    
    }

  ]).config(["$stateProvider", "$urlRouterProvider", "$ionicConfigProvider", "$compileProvider",function($stateProvider, $urlRouterProvider, $ionicConfigProvider, $compileProvider) {
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
    .state("login",{   //首页
      url:'/login',
      templateUrl:"templates/login.html",
      controller:"loginCtl"
    })
    .state("home",{   //首页
      url:'/home',
      templateUrl:"templates/home.html",
      controller:"homeCtl"
    })
    .state("siteManage", {   //现场管理
      url:"/siteManage",
      templateUrl:"templates/siteManage.html",
      controller:"siteManageCtrl"
    })
    .state("purchasingManage",{  //采购管理
      url:"/purchasingManage",
      templateUrl:"templates/purchasingManage.html",
      controller:"purchasingManageCtrl"
    })
    .state("project", {  //现场拍照
      url:"/project/:type",
      templateUrl:"templates/project.html",
      controller:"projectCtrl"
    })
    .state("scene-photo", {  //现场拍照详情
      url:"/scene-photo/:id/:name",
      templateUrl:"templates/scene-photo.html",
      controller:"scenePhotoCtrl"
    })
    .state("scene-photo-add", {  //现场拍照添加
      url:"/scene-photo-ddd/:id/:name",
      templateUrl:"templates/scene-photo-add.html",
      controller:"scenePhotoAddCtrl"
    })
    .state("doc-upload", {  //文档上传
      url:"/doc-upload/:id",
      templateUrl:"templates/doc-upload.html",
      controller:"docUploadCtrl"
    })
    .state("doc-browsing", {  //文档浏览
      url:"/doc-browsing/:id/:name",
      templateUrl:"templates/doc-browsing.html",
      controller:"docBrowsingCtrl"
    })
    .state("doc-browsing-des", {  //文档上传
      url:"/doc-browsing-des/:id",
      templateUrl:"templates/doc-browsing-des.html",
      controller:"docBrowsingDesCtrl"
    })
    .state("purchase-schedule",{  //采购进度列表
      url:"/purchase-schedule/:id/:name",
      templateUrl:"templates/purchase-schedule.html",
      controller:"purchaseScheduleCtrl"
    })
    .state("purchase-schedule-add",{  //采购进度添加
      url:"/purchase-schedule-add/:id/:name",
      templateUrl:"templates/purchase-schedule-add.html",
      controller:"purchaseScheduleAddCtrl"
    })
    .state("purchase-schedule-des",{  //采购进度详情
      url:"/purchase-schedule-des",
      templateUrl:"templates/purchase-schedule-des.html",
      controller:"purchaseScheduleDesCtrl"
    })
    .state("monitor-log",{    //监控日志列表
      url:"/monitor-log/:id/:name",
      templateUrl:"templates/monitor-log.html",
      controller:"monitorLogCtrl"
    })
    .state("monitor-log-add",{  //添加监控日志
      url:"/monitor-log-add/:id/:name",
      templateUrl:"templates/monitor-log-add.html",
      controller:"monitorLogAddCtrl"
    })
    .state("monitor-log-des",{  //监控日志详情
      url:"/monitor-log-des",
      templateUrl:"templates/monitor-log-des.html",
      controller:"monitorLogDesCtrl"
    })
    .state("project-schedule",{    //施工进度列表
      url:"/project-schedule/:id/:name",
      templateUrl:"templates/project-schedule.html",
      controller:"projectScheduleCtrl"
    })
    .state("project-schedule-add",{  //施工进度添加
      url:"/project-schedule-add/:id/:name",
      templateUrl:"templates/project-schedule-add.html",
      controller:"projectScheduleAddCtrl"
    })
    .state("project-schedule-des",{  //施工进度详情
      url:"/project-schedule-des",
      templateUrl:"templates/project-schedule-des.html",
      controller:"projectScheduleDesCtrl"
    })
    .state("logistics-damaged",{    //物流破损列表
      url:"/logistics-damaged/:id/:name",
      templateUrl:"templates/logistics-damaged.html",
      controller:"logisticsDamagedCtrl"
    })
    .state("logistics-damaged-add",{  //物流破损添加
      url:"/logistics-damaged-add/:id/:name/:boxSingleId",
      templateUrl:"templates/logistics-damaged-add.html",
      controller:"logisticsDamagedAddCtrl"
    })
    .state("logistics-damaged-des",{  //监控日志详情
      url:"/logistics-damaged-des",
      templateUrl:"templates/logistics-damaged-des.html",
      controller:"logisticsDamagedDesCtrl"
    })
    .state("logistics-code",{
      url:"/logistics-code/:boxSingleId",
      templateUrl:"templates/logistics-code.html",
      controller:"logisticsCodeCtrl"
    })
    .state("threeCategoryRegister-info", { //三类人员登记
      url:"/threeCategoryRegister-info/:id",
      templateUrl:"templates/threeCategoryRegister-info.html",
      controller:"threeCategoryRegisterInfoCtrl"
    })
    .state("constructionEquipmentRegister-info", { //施工机具/仪表登记
      url:"/constructionEquipmentRegister-info/:id",
      templateUrl:"templates/constructionEquipmentRegister-info.html",
      controller:"constructionEquipmentRegisterInfoCtrl"
    })
    .state("keyManagePersonRegister-info", { //主要管理人员登记
      url:"/keyManagePersonRegister-info/:id",
      templateUrl:"templates/keyManagePersonRegister-info.html",
      controller:"keyManagePersonRegisterInfoCtrl"
    })
    .state("specialOperatorsRegister-info", { //特种作业人员登记
      url:"/specialOperatorsRegister-info/:id",
      templateUrl:"templates/specialOperatorsRegister-info.html",
      controller:"specialOperatorsRegisterInfoCtrl"
    })
    
    $urlRouterProvider.otherwise("login");
    
  }]);
