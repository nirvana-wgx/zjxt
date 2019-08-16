angular.module("ionicApp.controllers", [])
//主页控制台
    .controller("appMain", ["$state", "$rootScope", "$ionicHistory", "$scope",
        function ($state, $rootScope, $ionicHistory, $scope) {
            $scope.toClose = function () {
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
            }
            $scope.toBigPic = function (pos) {
                var pswpElement = document.querySelectorAll('.pswp')[0];
                var items = [];
                var getItems = function () {
                    var aDiv = document.getElementById("imgs");
                    if (aDiv.hasChildNodes()) {
                        for (var i = 0; i < aDiv.children.length; i++) {
                            var img = aDiv.children;
                            var item = {
                                src: img[i].src,
                                w: img[i].naturalWidth,
                                h: img[i].naturalHeight
                            };
                            items.push(item);
                            console.log(i + "===child====" + (item.src));
                            console.log(i + "===child====" + (item.w));
                            console.log(i + "===child====" + (item.h));
                        }
                    }
                };

                getItems();

                // define options (if needed)
                var options = {
                    // history & focus options are disabled on CodePen
                    history: false,
                    focus: false,
                    index: pos,
                    showAnimationDuration: 0,
                    hideAnimationDuration: 0

                };

                var gallery = new PhotoSwipe(pswpElement, PhotoSwipeUI_Default, items, options);
                gallery.init();
            }
        }])
    //采购管理
    .controller("purchasingManageCtrl", ["$scope", "home", "$state", "appMain", "$rootScope",
        function ($scope, home, $state, appMain, $rootScope) {
            $scope.menuList = [];
            $scope.getHomeData = function () {
                home.getHomeData().then(function (data) {
                    $scope.menuList = data.purchasingList;
                })
            }

            $scope.getAccount = function () {
                home.getAccount().then(function (data) {
                    if (data.code == 1) {
                        $rootScope.userInfo = data.userInfo;
                        appMain.setSession({
                            key: "userInfo",
                            data: data.userInfo
                        })
                        $scope.getHomeData();
                    } else {
                        $scope.showToast(data.msg);
                    }
                })
            }

            $scope.jumpPage = function (type) {
                if (type == "logistics-code") {
                    $scope.scanStart();
                } else {
                    $state.go("project", {
                        type: type
                    });
                }
            }

            //扫一扫
            $scope.scanStart = function () {
                var sendData = {
                    actionType: rnConfig.doQRScan,
                    info: {}
                };

                if (window.WebViewBridge) {
                    window.WebViewBridge.send(JSON.stringify(sendData));
                }
                ;
                // doQRScanCallback('a8c90137-f48e-4d31-bd1e-98b26fcec6ce');
            };

            window.doQRScanCallback = function (str) {
                $state.go("logistics-code", {
                    boxSingleId: str
                })
            }

            $scope.getAccount();
        }])
    //项目列表
    .controller("projectCtrl", ["$scope", "$state", "project", "$stateParams",
        function ($scope, $state, project, $stateParams) {
            $scope.porjectList = [];
            $scope.pageNo = 1;
            $scope.pageSize = 10;
            $scope.nltip = 0;
            $scope.title = "";
            $scope.type = "";

            if ($stateParams.type == "scene-photo") {
                $scope.title = "现场拍照";
            } else if ($stateParams.type == "doc-browsing") {
                $scope.title = "文档浏览";
            } else if ($stateParams.type == "project-schedule") {
                $scope.title = "施工进度反馈";
            } else if ($stateParams.type == "purchase-schedule") {
                $scope.title = "项目采购进度反馈";
            } else if ($stateParams.type == "monitor-log") {
                $scope.title = "监制日志";
            } else if ($stateParams.type == "logistics-damaged") {
                $scope.title = "物流破损反馈";
            }
            $scope.doRefreshProjectList = function () {
                $scope.pageNo = 1;
                project.getPojectList($stateParams.type, $scope.userInfo.id, $scope.userInfo.prjId, $scope.pageNo, $scope.pageSize).then(function (data) {
                    if ($scope.nltip) {
                        $scope.$broadcast("scroll.refreshComplete");
                    }
                    $scope.nltip = 2;
                    if (data.code == 1) {

                        $scope.porjectList = data.list;
                        if (data.list.length > $scope.pageSize - 1) {
                            $scope.showScroll = true;
                            $scope.pageNo++;
                        } else {
                            $scope.nltip = 1;
                        }
                    } else {
                        $scope.showToast(data.msg);
                    }
                }, function () {
                    if ($scope.nltip) {
                        $scope.$broadcast("scroll.refreshComplete");
                    }
                    $scope.nltip = 2;
                })
            }

            $scope.loadMoreProjectList = function () {
                project.getPojectList($stateParams.type, $scope.userInfo.id, $scope.userInfo.PrjId, $scope.pageNo, $scope.pageSize).then(function (data) {
                    if (data.code == 1) {
                        $scope.porjectList = $scope.porjectList.concat(data.list);
                        if (data.list.length < $scope.pageSize || data.list.length < 1) {
                            $scope.showScroll = false;
                            $scope.nltip = 1;
                        }
                        $scope.pageNo++;
                    } else {
                        $scope.showToast(data.msg);
                    }
                    $scope.$broadcast("scroll.infiniteScrollComplete");
                }, function () {
                    $scope.$broadcast("scroll.infiniteScrollComplete");
                })
            }

            $scope.doRefreshProjectList();
            $scope.jumpPage = function (id, name) {
                $state.go($stateParams.type, {
                    id: id,
                    name: name
                });
            }
        }])
    //采购进度反馈
    .controller("purchaseScheduleCtrl", ["$scope", "$state", "$stateParams", "purchaseSchedule", "$rootScope", "appMain",
        function ($scope, $state, $stateParams, purchaseSchedule, $rootScope, appMain) {
            $scope.scenePhotoList = [];
            $scope.pageNo = 1;
            $scope.pageSize = 10;
            $scope.nltip = 0;
            $scope.title = $stateParams.name;
            $scope.doRefreshScenePhotoList = function () {
                $scope.pageNo = 1;
                purchaseSchedule.getList($scope.userInfo.id, $stateParams.id, $scope.pageNo, $scope.pageSize).then(function (data) {
                    if ($scope.nltip) {
                        $scope.$broadcast("scroll.refreshComplete");
                    }
                    $scope.nltip = 2;
                    $scope.scenePhotoList = [];
                    if (data.code == 1) {
                        for (var i = 0; i < data.list.length; i++) {
                            if (data.list[i].photoFile) {
                                data.list[i].photoFile = data.list[i].photoFile.split(',');
                            }
                            console.log(data.list[i].photoFile)
                            $scope.scenePhotoList.push(data.list[i])
                        }
                        if (data.list.length > $scope.pageSize - 1) {
                            $scope.showScroll = true;
                            $scope.pageNo++;
                        } else {
                            $scope.nltip = 1;
                        }
                    } else {
                        $scope.showToast(data.msg);
                    }
                }, function () {
                    if ($scope.nltip) {
                        $scope.$broadcast("scroll.refreshComplete");
                    }
                    $scope.nltip = 2;
                })
            }

            $scope.loadMoreScenePhotoList = function () {
                purchaseSchedule.getList($scope.userInfo.id, $stateParams.id, $scope.pageNo, $scope.pageSize).then(function (data) {
                    if (data.code == 1) {
                        for (var i = 0; i < data.list.length; i++) {
                            console.log(data.list[i]);
                            if (data.list[i].photoFile) {
                                data.list[i].photoFile = data.list[i].photoFile.split(',');
                                console.log(data.list[i].photoFil);
                            }
                            $scope.scenePhotoList.push(data.list[i])
                        }
                        if (data.list.length < $scope.pageSize || data.list.length < 1) {
                            $scope.showScroll = false;
                            $scope.nltip = 1;
                        }
                        $scope.pageNo++;
                    } else {
                        $scope.showToast(data.msg);
                    }
                    $scope.$broadcast("scroll.infiniteScrollComplete");
                }, function () {
                    $scope.$broadcast("scroll.infiniteScrollComplete");
                })
            }

            $scope.doRefreshScenePhotoList();
            $scope.jumpAdd = function () {
                $state.go("purchase-schedule-add", {
                    id: $stateParams.id,
                    name: $stateParams.name
                });
            }

            $scope.jumpDetail = function (data) {
                console.log(1);
                appMain.setSession({
                    key: 'purchaseScheduleDes',
                    data: data
                })
                $state.go("purchase-schedule-des");
            }

            var listenerFresh = $rootScope.$on("purchaseSchedule", function (event, data) {
                $scope.doRefreshScenePhotoList();
            })

            $scope.$on('$destroy', function () {
                listenerFresh();
                listenerFresh = null;
            })
        }])

    //采购进度反馈添加
    .controller("purchaseScheduleAddCtrl", ["$scope", "$stateParams", "purchaseSchedule", "$rootScope", "$timeout", "constants",
        function ($scope, $stateParams, purchaseSchedule, $rootScope, $timeout, constants) {
            $scope.imgList = []
            $scope.addPurchase = {
                orgId: $scope.userInfo.groupId,
                companyId: 'a1b10168-61a9-44b5-92ca-c5659456deb5',
                flowPhase: 'Start',
                supplierId: '',
                supplierName: '',
                submitUser: $scope.userInfo.id,
                submitName: $scope.userInfo.name,
                projectInfoId: $stateParams.id,
                projectInfoName: $stateParams.name,
                contract: '',//合同ID
                contractName: '', //合同名称
                contractCode: '', //合同编号
                equipCode: '', //设备编码
                equipName: '', //设备名称
                equipId: '', //设备ID
                equipNo: '', //设备位号
                phase: '', //步骤
                projress: '',//进度
                totalProjress: '',//累计进度
                createUserId: $scope.userInfo.id,
                createUser: $scope.userInfo.name
            }

            $scope.select = {
                contract: '', //选择的合同信息
                equipment: '',  //选择的设备名称
                step: ''   //步骤
            }

            //获取合同列表
            $scope.getContractList = function () {
                purchaseSchedule.getContractList($stateParams.id).then(function (data) {
                    $scope.contractList = data.list;
                    if ($scope.contractList.length > 0) {
                        $scope.select.contract = $scope.contractList[0];
                        $scope.getEquipmentList($scope.contractList[0].contractId);
                    }
                })
            }
            //获取设备名称
            $scope.getEquipmentList = function (contractId) {
                purchaseSchedule.getEquipmentList(contractId).then(function (data) {
                    $scope.equipmentList = data.list;
                    if ($scope.equipmentList.length > 0) {
                        $scope.select.equipment = $scope.equipmentList[0];
                    }
                })
            }


            //获取设备名称
            $scope.getStepList = function () {
                purchaseSchedule.getStepList($stateParams.id).then(function (data) {
                    $scope.stepListList = data.list;
                    if ($scope.stepListList.length > 0) {
                        $scope.select.step = $scope.stepListList[0];
                    }
                })
            }

            $scope.selectContract = function () {
                $scope.getEquipmentList($scope.select.contract.contractId);
            }

            $scope.submit = function () {
                $scope.addPurchase.contract = $scope.select.contract.contractId;//合同ID
                $scope.addPurchase.contractName = $scope.select.contract.contractName; //合同名称
                $scope.addPurchase.contractCode = $scope.select.contract.contractCode; //合同编号
                $scope.addPurchase.supplierId = $scope.select.contract.supplier; //供应商ID
                $scope.addPurchase.supplierName = $scope.select.contract.supplierName; //供应商名称
                $scope.addPurchase.equipCode = $scope.select.equipment.code; //设备编码
                $scope.addPurchase.equipName = $scope.select.equipment.name; //设备名称
                $scope.addPurchase.equipId = $scope.select.equipment.equipmentId; //设备ID
                $scope.addPurchase.equipNo = $scope.select.equipment.no; //设备位号
                $scope.addPurchase.phase = $scope.select.step.code;//步骤
                $scope.addPurchase.phaseName = $scope.select.step.name;//步骤
                $scope.addPurchase.photoFile = $scope.imgList.join();
                $scope.showLoad();
                purchaseSchedule.procurementAdd($scope.addPurchase).then(function (data) {
                    $scope.hideLoad();
                    if (data.code == 1) {
                        $rootScope.$emit('purchaseSchedule');
                        $scope.toClose();
                    }
                    $scope.showToast(data.msg);
                })
            }
            $scope.getContractList();
            $scope.getStepList();

            $scope.removeImg = function (img) {
                $scope.imgList.splice($scope.imgList.indexOf(img), 1);
            }

            $scope.selectPhotoType = function () {
                var sendData = {
                    actionType: rnConfig.selectImage,
                    info: {
                        url: constants.getRoot().uploadFile,
                        multipleImage: true
                    }
                };
                if (window.WebViewBridge) {
                    window.WebViewBridge.send(JSON.stringify(sendData));
                }
                ;
                //var time = new Date().getTime()
                // selectImageCallback("images/add-img-icon.png?"+ time); 
            }


            window.selectImageCallback = function (str) {
                $timeout(function () {
                    var strList = str.split(",");
                    for (var i = 0; i < strList.length; i++) {
                        var imgArr = strList[i].split("_");
                        var imgStr = imgArr[0] + '_' + imgArr[1];
                        $scope.imgList.push(imgStr);
                    }
                }, 300);
            }
        }])

    //采购进度反馈详情
    .controller("purchaseScheduleDesCtrl", ["$scope", "appMain", function ($scope, appMain) {
        $scope.apurchaseDes = appMain.getSession('purchaseScheduleDes');
    }])
    //监制日志
    .controller("monitorLogCtrl", ["$scope", "$state", "$stateParams", "monitorLog", "$rootScope", "appMain",
        function ($scope, $state, $stateParams, monitorLog, $rootScope, appMain) {
            $scope.scenePhotoList = [];
            $scope.pageNo = 1;
            $scope.pageSize = 10;
            $scope.nltip = 0;
            $scope.title = $stateParams.name;
            $scope.doRefreshScenePhotoList = function () {
                $scope.pageNo = 1;
                monitorLog.getList($scope.userInfo.id, $stateParams.id, $scope.pageNo, $scope.pageSize).then(function (data) {
                    if ($scope.nltip) {
                        $scope.$broadcast("scroll.refreshComplete");
                    }
                    $scope.nltip = 2;
                    $scope.scenePhotoList = [];
                    if (data.code == 1) {
                        for (var i = 0; i < data.list.length; i++) {
                            if (data.list[i].supervisoryPhoto) {
                                data.list[i].supervisoryPhoto = data.list[i].supervisoryPhoto.split(',');
                            }
                            $scope.scenePhotoList.push(data.list[i])
                        }
                        if (data.list.length > $scope.pageSize - 1) {
                            $scope.showScroll = true;
                            $scope.pageNo++;
                        } else {
                            $scope.nltip = 1;
                        }
                    } else {
                        $scope.showToast(data.msg);
                    }
                }, function () {
                    if ($scope.nltip) {
                        $scope.$broadcast("scroll.refreshComplete");
                    }
                    $scope.nltip = 2;
                })
            }

            $scope.loadMoreScenePhotoList = function () {
                monitorLog.getList($scope.userInfo.id, $stateParams.id, $scope.pageNo, $scope.pageSize).then(function (data) {
                    if (data.code == 1) {
                        for (var i = 0; i < data.list.length; i++) {
                            if (data.list[i].supervisoryPhoto) {
                                data.list[i].supervisoryPhoto = data.list[i].supervisoryPhoto.split(',');
                            }
                            $scope.scenePhotoList.push(data.list[i])
                        }
                        if (data.list.length < $scope.pageSize || data.list.length < 1) {
                            $scope.showScroll = false;
                            $scope.nltip = 1;
                        }
                        $scope.pageNo++;
                    } else {
                        $scope.showToast(data.msg);
                    }
                    $scope.$broadcast("scroll.infiniteScrollComplete");
                }, function () {
                    $scope.$broadcast("scroll.infiniteScrollComplete");
                })
            }

            $scope.doRefreshScenePhotoList();
            $scope.jumpAdd = function () {
                $state.go("monitor-log-add", {
                    id: $stateParams.id,
                    name: $stateParams.name
                });
            }

            $scope.jumpDetail = function (data) {
                appMain.setSession({
                    key: 'monitorLogDes',
                    data: data
                })
                $state.go("monitor-log-des");
            }

            var listenerFresh = $rootScope.$on("monitorLog", function (event, data) {
                $scope.doRefreshScenePhotoList();
            })

            $scope.$on('$destroy', function () {
                listenerFresh();
                listenerFresh = null;
            })
        }])

    //监制日志添加
    .controller("monitorLogAddCtrl", ["$scope", "$stateParams", "monitorLog", "appMain", "$rootScope", "$timeout", "constants",
        function ($scope, $stateParams, monitorLog, appMain, $rootScope, $timeout, constants) {
            $scope.imgList = []
            $scope.addMonitor = {
                projectInfoId: $stateParams.id,
                projectInfoName: $stateParams.name,
                orgId: $scope.userInfo.groupId,
                companyId: 'a1b10168-61a9-44b5-92ca-c5659456deb5',
                flowPhase: 'Start',
                status: "Submited",
                supervisePeople: $scope.userInfo.id,
                supervisePeopleName: $scope.userInfo.name,
                contract: '',//合同ID
                contractName: '', //合同名称
                contractCode: '', //合同编号
                equipCode: '', //设备编码
                equipmentName: '', //设备名称
                equipment: '', //设备ID
                createUserId: $scope.userInfo.id,
                createUser: $scope.userInfo.name,
                date: appMain.getDateStr(0, 'YY-MM-DD'),  //监制日期
                supplier: '',	  //供应商ID
                supplierName: '',	  //供应商名称
                workContent: '',   //当日主要工作内容
                question: '',	    //存在的主要问题
                controlResult: '',	//处理情况
                weather: '' //天气
            }

            $scope.select = {
                contract: '', //选择的合同信息
                equipment: '',  //选择的设备名称
                step: '',   //步骤
                weather: ''  //天气
            }

            //获取合同列表
            $scope.getContractList = function () {
                monitorLog.getContractList($stateParams.id).then(function (data) {
                    $scope.contractList = data.list;
                    if ($scope.contractList.length > 0) {
                        $scope.select.contract = $scope.contractList[0];
                        $scope.getEquipmentList($scope.contractList[0].contractId);
                    }
                })
            }
            //获取设备名称
            $scope.getEquipmentList = function (contractId) {
                monitorLog.getEquipmentList(contractId).then(function (data) {
                    $scope.equipmentList = data.list;
                    if ($scope.equipmentList.length > 0) {
                        $scope.select.equipment = $scope.equipmentList[0];
                    }
                })
            }
            $scope.getWeatherList = function () {
                monitorLog.getWeatherList().then(function (data) {
                    $scope.weatherList = data.list;
                    if ($scope.weatherList.length > 0) {
                        $scope.select.weather = $scope.weatherList[0];
                    }
                })
            }

            $scope.selectContract = function () {
                console.log($scope.select.contract)
                $scope.getEquipmentList($scope.select.contract.contractId);
            }

            $scope.submit = function () {
                $scope.addMonitor.contract = $scope.select.contract.contractId;//合同ID
                $scope.addMonitor.contractName = $scope.select.contract.contractName; //合同名称
                $scope.addMonitor.contractCode = $scope.select.contract.contractCode; //合同编号
                $scope.addMonitor.equipCode = $scope.select.equipment.code; //设备编码
                $scope.addMonitor.equipmentName = $scope.select.equipment.name; //设备名称
                $scope.addMonitor.equipment = $scope.select.equipment.id; //设备ID
                $scope.addMonitor.supplier = $scope.select.contract.supplier;//步骤
                $scope.addMonitor.supplierName = $scope.select.contract.supplierName;//步骤
                $scope.addMonitor.weather = $scope.select.weather.code; //设备名称
                $scope.addMonitor.supervisoryPhoto = $scope.imgList.join();

                $scope.showLoad();
                monitorLog.monitorAdd($scope.addMonitor).then(function (data) {
                    $scope.hideLoad();
                    if (data.code == 1) {
                        $rootScope.$emit('monitorLog');
                        $scope.toClose();
                    }
                    $scope.showToast(data.msg);
                })
            }
            $scope.getContractList();
            $scope.getWeatherList();

            $scope.removeImg = function (img) {
                $scope.imgList.splice($scope.imgList.indexOf(img), 1);
            }

            $scope.selectPhotoType = function () {
                var sendData = {
                    actionType: rnConfig.selectImage,
                    info: {
                        url: constants.getRoot().uploadFile,
                        multipleImage: true
                    }
                };
                if (window.WebViewBridge) {
                    window.WebViewBridge.send(JSON.stringify(sendData));
                }
                ;
                //  var time = new Date().getTime()
                //  selectImageCallback("images/add-img-icon.png?"+ time); 
            }


            window.selectImageCallback = function (str) {
                $timeout(function () {
                    var strList = str.split(",");
                    for (var i = 0; i < strList.length; i++) {
                        var imgArr = strList[i].split("_");
                        var imgStr = imgArr[0] + '_' + imgArr[1];
                        $scope.imgList.push(imgStr);
                    }
                }, 300);
            }
        }])

    //监控日志详情
    .controller("monitorLogDesCtrl", ["$scope", "appMain", function ($scope, appMain) {
        $scope.monitorLogDes = appMain.getSession('monitorLogDes');
    }])
    //物流破损
    .controller("logisticsDamagedCtrl", ["$scope", "$state", "$stateParams", "logisticsDamaged", "$rootScope", "appMain",
        function ($scope, $state, $stateParams, logisticsDamaged, $rootScope, appMain) {
            // $stateParams.id = 'a72800ed-ea79-4b91-b7dd-41455162d9e3';
            $scope.scenePhotoList = [];
            $scope.pageNo = 1;
            $scope.pageSize = 10;
            $scope.nltip = 0;
            $scope.title = $stateParams.name;
            $scope.doRefreshScenePhotoList = function () {
                $scope.pageNo = 1;
                logisticsDamaged.getList($scope.userInfo.id, $stateParams.id, $scope.pageNo, $scope.pageSize).then(function (data) {
                    if ($scope.nltip) {
                        $scope.$broadcast("scroll.refreshComplete");
                    }
                    $scope.nltip = 2;
                    $scope.scenePhotoList = [];
                    if (data.code == 1) {
                        for (var i = 0; i < data.list.length; i++) {
                            if (data.list[i].damagePic) {
                                data.list[i].damagePic = data.list[i].damagePic.split(',');
                            }
                            $scope.scenePhotoList.push(data.list[i])
                        }
                        if (data.list.length > $scope.pageSize - 1) {
                            $scope.showScroll = true;
                            $scope.pageNo++;
                        } else {
                            $scope.nltip = 1;
                        }
                    } else {
                        $scope.showToast(data.msg);
                    }
                }, function () {
                    if ($scope.nltip) {
                        $scope.$broadcast("scroll.refreshComplete");
                    }
                    $scope.nltip = 2;
                })
            }

            $scope.loadMoreScenePhotoList = function () {
                logisticsDamaged.getList($scope.userInfo.id, $stateParams.id, $scope.pageNo, $scope.pageSize).then(function (data) {
                    if (data.code == 1) {
                        for (var i = 0; i < data.list.length; i++) {
                            if (data.list[i].damagePic) {
                                data.list[i].damagePic = data.list[i].damagePic.split(',');
                            }
                            $scope.scenePhotoList.push(data.list[i])
                        }
                        if (data.list.length < $scope.pageSize || data.list.length < 1) {
                            $scope.showScroll = false;
                            $scope.nltip = 1;
                        }
                        $scope.pageNo++;
                    } else {
                        $scope.showToast(data.msg);
                    }
                    $scope.$broadcast("scroll.infiniteScrollComplete");
                }, function () {
                    $scope.$broadcast("scroll.infiniteScrollComplete");
                })
            }

            $scope.doRefreshScenePhotoList();
            $scope.jumpAdd = function () {
                $scope.scanStart();

            }

            $scope.jumpDetail = function (data) {
                appMain.setSession({
                    key: 'logisticsDamagedDes',
                    data: data
                })
                $state.go("logistics-damaged-des");
            }

            //扫一扫
            $scope.scanStart = function () {
                var sendData = {
                    actionType: rnConfig.doQRScan,
                    info: {}
                };

                if (window.WebViewBridge) {
                    window.WebViewBridge.send(JSON.stringify(sendData));
                }
                ;

                // doQRScanCallback('a8c90137-f48e-4d31-bd1e-98b26fcec6ce');
            };

            window.doQRScanCallback = function (str) {
                $state.go("logistics-damaged-add", {
                    id: $stateParams.id,
                    name: $stateParams.name,
                    boxSingleId: str
                })
            }
            var listenerFresh = $rootScope.$on("logisticsDamaged", function (event, data) {
                $scope.doRefreshScenePhotoList();
            })

            $scope.$on('$destroy', function () {
                listenerFresh();
                listenerFresh = null;
            })
        }])

    //物流破损添加
    .controller("logisticsDamagedAddCtrl", ["$scope", "$stateParams", "logisticsDamaged", "appMain", "$rootScope", "$timeout", "constants",
        function ($scope, $stateParams, logisticsDamaged, appMain, $rootScope, $timeout, constants) {
            $scope.imgList = []
            $scope.addLogisticsDamaged = {
                projectInfoId: $stateParams.id,
                projectInfoName: $stateParams.name,
                createUserId: $scope.userInfo.id,
                createUser: $scope.userInfo.name,
                boxSingle: "",//	箱单ID
                boxSingleName: "",//		箱单号
                damageDescription: "",//		破损说明
                registDate: appMain.getDateStr(0, 'YY-MM-DD'),//		破损记录时间
            }
            $scope.getBoxBillInfo = function () {
                $scope.showLoad();
                logisticsDamaged.getBoxBillInfo($stateParams.boxSingleId).then(function (data) {
                    $scope.hideLoad();
                    if (data.code == 1) {
                        $scope.addLogisticsDamaged.sendBatchName = data.data.sendBatchName;
                        $scope.addLogisticsDamaged.boxSingleName = data.data.boxSingleName;
                        $scope.addLogisticsDamaged.boxSingle = data.data.boxSingle;
                        $scope.addLogisticsDamaged.mainMaterialNameCH = data.data.mainMaterialNameCH;
                        $scope.addLogisticsDamaged.mainMaterialNameEN = data.data.mainMaterialNameEN;
                        $scope.addLogisticsDamaged.transportWay = data.data.transportWay;
                    } else {
                        $scope.showToast(data.msg);
                    }
                })
            }

            $scope.submit = function () {
                $scope.showLoad();
                $scope.addLogisticsDamaged.damagePic = $scope.imgList.join();
                logisticsDamaged.add($scope.addLogisticsDamaged).then(function (data) {
                    $scope.hideLoad();
                    if (data.code == 1) {
                        $rootScope.$emit('logisticsDamaged');
                        $scope.toClose();
                    }
                    $scope.showToast(data.msg);
                })
            }

            $scope.getBoxBillInfo();

            $scope.removeImg = function (img) {
                $scope.imgList.splice($scope.imgList.indexOf(img), 1);
            }

            $scope.selectPhotoType = function () {
                var sendData = {
                    actionType: rnConfig.selectImage,
                    info: {
                        url: constants.getRoot().uploadFile,
                        multipleImage: true
                    }
                };
                if (window.WebViewBridge) {
                    window.WebViewBridge.send(JSON.stringify(sendData));
                }
                ;
                //  var time = new Date().getTime()
                //  selectImageCallback("images/add-img-icon.png?"+ time); 
            }


            window.selectImageCallback = function (str) {
                $timeout(function () {
                    var strList = str.split(",");
                    for (var i = 0; i < strList.length; i++) {
                        var imgArr = strList[i].split("_");
                        var imgStr = imgArr[0] + '_' + imgArr[1];
                        $scope.imgList.push(imgStr);
                    }
                }, 300);
            }
        }])

    //物流破损详情
    .controller("logisticsDamagedDesCtrl", ["$scope", "appMain", function ($scope, appMain) {
        $scope.logisticsDamagedDes = appMain.getSession('logisticsDamagedDes');
    }])
    //物流二维码
    .controller("logisticsCodeCtrl", ["$scope", "$stateParams", "logisticsDamaged", "appMain", "$timeout", "constants",
        function ($scope, $stateParams, logisticsDamaged, appMain, $timeout, constants) {
            $scope.imgList = []
            $scope.logisticsCode = {
                projectInfoId: $stateParams.id,
                projectInfoName: $stateParams.name,
                createUserId: $scope.userInfo.id,
                createUser: $scope.userInfo.name,
                boxSingle: "",//	箱单ID
                boxSingleName: "",//		箱单号
                damageDescription: "",//		破损说明
                registDate: appMain.getDateStr(0, 'YY-MM-DD'),//		破损记录时间
                remark: '',
                storageWay: '',  //存储方式
                storeageAddress: '', //存放地点
                sendBatchName: '',
                num: ''
            }
            $scope.getBoxBillInfo = function () {
                $scope.showLoad();
                logisticsDamaged.logisticsDamagedBoxId($stateParams.boxSingleId).then(function (data) {
                    $scope.hideLoad();
                    if (data.code == 1) {
                        $scope.logisticsCode = angular.extend($scope.logisticsCode, data.data);
                        $scope.logisticsCode.createUserId = $scope.userInfo.id,
                            $scope.logisticsCode.createUser = $scope.userInfo.name
                    } else {
                        $scope.showToast(data.msg);
                    }
                })
            }

            $scope.getBoxBillNum = function () {
                logisticsDamaged.getBoxBillNum($stateParams.boxSingleId).then(function (data) {
                    if (data.code == 1) {
                        $scope.logisticsCode.num = data.data;
                    }
                })
            }

            $scope.submit = function (state) {
                $scope.showLoad();
                $scope.logisticsCode.photo = $scope.imgList.join();
                logisticsDamaged.addLogistics(state, $scope.logisticsCode).then(function (data) {
                    $scope.hideLoad();
                    if (data.code == 1) {

                        $scope.toClose();
                    }
                    $scope.showToast(data.msg);
                })
            }

            $scope.getBoxBillInfo();
            $scope.getBoxBillNum();

            $scope.removeImg = function (img) {
                $scope.imgList.splice($scope.imgList.indexOf(img), 1);
            }

            $scope.selectPhotoType = function () {
                var sendData = {
                    actionType: rnConfig.selectImage,
                    info: {
                        url: constants.getRoot().uploadFile,
                        multipleImage: true
                    }
                };
                if (window.WebViewBridge) {
                    window.WebViewBridge.send(JSON.stringify(sendData));
                }
                ;
                //  var time = new Date().getTime()
                //  selectImageCallback("images/add-img-icon.png?"+ time); 
            }

            window.selectImageCallback = function (str) {
                $timeout(function () {
                    var strList = str.split(",");
                    for (var i = 0; i < strList.length; i++) {
                        var imgArr = strList[i].split("_");
                        var imgStr = imgArr[0] + '_' + imgArr[1];
                        $scope.imgList.push(imgStr);
                    }
                }, 300);
            }
        }])

 
 