angular.module("ionicApp.controllers", [])
//主页控制台
    .controller("appMain", ["$scope", function ($scope) {
        $scope.toClose = function () {
            var sendData = {
                actionType: rnConfig.closeWindow,
                info: {}
            };
            if (window.WebViewBridge) {
                window.WebViewBridge.send(JSON.stringify(sendData));
            }
            ;
        }
    }])

    //三类人员登记控制台
    .controller("threeCategoryRegisterInfoCtrl", ["$scope", "scan",
        function ($scope, scan) {
            $scope.getInfo = function () {
                $scope.showLoad();
                scan.getThree(paramId).then(function (data) {
                    $scope.hideLoad();
                    if (data.code == -1) {
                        $scope.showToast(data.msg);
                        return;
                    }
                    $scope.info = data.data;


                }, function (err) {
                    $scope.showError();
                    $scope.hideLoad();
                })
            }
            $scope.getInfo();
        }])

    //施工机具/仪表登记
    .controller("constructionEquipmentRegisterInfoCtrl", ["$scope", "scan",
        function ($scope, scan) {
            $scope.getInfo = function () {
                $scope.showLoad();
                scan.getConstruction(paramId).then(function (data) {
                    $scope.hideLoad();
                    if (data.code == -1) {
                        $scope.showToast(data.msg);
                        return;
                    }
                    $scope.info = data.data;

                }, function (err) {
                    $scope.showError();
                    $scope.hideLoad();
                })
            }
            $scope.getInfo();
        }])

    //主要管理人员登记
    .controller("keyManagePersonRegisterInfoCtrl", ["$scope", "scan",
        function ($scope, scan) {
            $scope.getInfo = function () {
                $scope.showLoad();
                scan.getKey(paramId).then(function (data) {
                    $scope.hideLoad();
                    if (data.code == -1) {
                        $scope.showToast(data.msg);
                        return;
                    }
                    $scope.info = data.data;

                }, function (err) {
                    $scope.showError();
                    $scope.hideLoad();
                })
            }
            $scope.getInfo();
        }])

    //特种作业人员登记
    .controller("specialOperatorsRegisterInfoCtrl", ["$scope", "scan",
        function ($scope, scan) {
            $scope.getInfo = function () {
                $scope.showLoad();
                scan.getSpecial(paramId).then(function (data) {
                    $scope.hideLoad();
                    if (data.code == -1) {
                        $scope.showToast(data.msg);
                        return;
                    }
                    $scope.info = data.data;

                }, function (err) {
                    $scope.showError();
                    $scope.hideLoad();
                })
            }
            $scope.getInfo();
        }])

    //特种作业人员登记
    .controller("staffCtrl", ["$scope", "scan", function ($scope, scan) {
        $scope.getInfo = function () {
            $scope.showLoad();
            scan.getStaff(UserID, RoleCode).then(function (data) {
                $scope.hideLoad();
                if (data.code == -1) {
                    $scope.showToast(data.msg);
                    return;
                }
                $scope.info = data.data[0];
                $scope.img = "http://pm.cie-cn.com:8090/MvcConfig/Image/GetUserPic?UserId=" + UserID;
            }, function (err) {
                $scope.showError();
                $scope.hideLoad();
            })
        }

        $scope.getStaffHead = function () {

            scan.getStaffHead(UserID).then(function (data) {

                if (data.code == -1) {
                    $scope.showToast(data.msg);
                    return;
                }

                $scope.img = "http://pm.cie-cn.com:8090/MvcConfig/Image/GetUserPic?UserId=" + data.data;
            }, function (err) {

            })
        }


        $scope.getInfo();
        // $scope.getStaffHead();
    }])

    //现场管理
    .controller("noticeInfoCtrl", ["$scope", "scan", function ($scope, scan) {
        $scope.getInfo = function () {
            $scope.showLoad();
            scan.getNotice(paramId).then(function (data) {
                $scope.hideLoad();
                if (data.code == -1) {
                    $scope.showToast(data.msg);
                    return;
                }
                $scope.info = data.data[0];
                $scope.img = "http://pm.cie-cn.com:8090/MvcConfig/Image/GetUserPic?UserId=" + UserID;
            }, function (err) {
                $scope.showError();
                $scope.hideLoad();
            })
        }
        $scope.getInfo();
        // $scope.getStaffHead();
    }])