angular.module("ionicApp.controllers", [])
//主页控制台
.controller("appMain", ["$scope","$state","$ionicHistory","$rootScope", "constants", 
  function($scope, $state, $ionicHistory, $rootScope,constants) {
  $scope.toClose = function() {
    if ($state.current.name.indexOf("home") > -1) {
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
  }

  //查看图片
  $scope.viewPic = function(picUrl,$event) {
    if($event) {
      $event.stopPropagation();
    }
    var param ={
      startIndex:0,
      data:[{
          thumb:picUrl,
          src:picUrl
      }]
    }
    window.ImageView.openBrowser(null, null,param);
  }

  //查看多图片
  $scope.viewMultiPic = function(index,picUrlList,$event) {
    var param ={
      startIndex:index,
      data:[]
    }
    for(var i=0; i<picUrlList.length;i++) {
      var img  = constants.getRoot().downloadImg+'newFileName='+picUrlList[i].newFileName;
      param.data.push( {
        thumb:img,
        src:img
      })
    }
    console.log(param)
    window.ImageView.openBrowser(null, null,param);
  }
}])

//登录页
.controller("loginCtl",["$scope","$state","home","$rootScope","appMain",function($scope,$state,home,$rootScope,appMain){
  $scope.loginName = '10628';
  $scope.password = '103941';   //103941  698E3706C8A24F2EEB7830BCF3FF8FEF3F0D511F
  $scope.login = function() {
    $scope.showLoad();
    home.getLogin($scope.loginName, $scope.password).then(function(data) {
      if(data.code==1) {
        $rootScope.userInfo = data.userInfo;
        appMain.setSession({
          key:"userInfo",
          data:data.userInfo
        })
        $state.go("home");
      }
      $scope.hideLoad();
    }) 
   }
}])

//主页
.controller("homeCtl",["$scope","$state",function($scope,$state){
  $scope.jumpType = function (index) {
    if(index==1) {
      $state.go("siteManage");
    } else {
      $state.go("purchasingManage");
    }
  }
}])

//现场管理
.controller("siteManageCtrl",["$scope","home","$state","$cordovaBarcodeScanner",
  function($scope,home,$state,$cordovaBarcodeScanner){
  $scope.type = '';
  $scope.menuList = [];
 
  $scope.getHomeData = function() {
    home.getHomeData().then(function(data) {
      $scope.menuList = data.siteList;
      console.log($scope.menuList)
    }) 
  }

  $scope.jumpPage = function(type) {
    if(type=="doc-upload") {
      $scope.scanStart();
    } else {
      $state.go("project",{
        type:type
      });
    }
  }

 //扫一扫
 $scope.scanStart = function () {
   //文档id   a8d40115-e4ab-4322-90d7-9ff7ef04c977 

  var sendData={
    actionType:rnConfig.doQRScan,
    info:{}
  };

  if(window.WebViewBridge) {
      window.WebViewBridge.send(JSON.stringify(sendData));
  };

  
  // $cordovaBarcodeScanner.scan().then(function (barcodeData) {
  //      $state.go("doc-upload",{
  //               id:barcodeData.text
  //              })
  //   }, function (error) {
  //     $scope.showToast("扫码失败");
  //   });
  };

  window.doQRScanCallback = function(str) {
    $state.go("doc-upload",{
      id:str
    })
  } 
 
  $scope.getHomeData();
}])

//采购管理
.controller("purchasingManageCtrl",["$scope","home","$state","$cordovaBarcodeScanner",function($scope,home,$state,$cordovaBarcodeScanner){
  $scope.menuList = [];
  $scope.getHomeData = function() {
    home.getHomeData().then(function(data) {
      $scope.menuList = data.purchasingList;
    })
    
  }
 
  $scope.getHomeData();
  $scope.jumpPage = function(type) {
    if(type=="logistics-code") {
      $scope.scanStart();
    } else {
      $state.go("project",{
        type:type
      });
    }
  }

    //扫一扫
  $scope.scanStart = function () {
    //物流boxSingleId  a8c90137-f48e-4d31-bd1e-98b26fcec6ce
    $cordovaBarcodeScanner.scan().then(function (barcodeData) {
        state.go("logistics-code",{
            boxSingleId:barcodeData.text
        });
      }, function (error) {
        $scope.showToast("扫码失败");
      });
    };
}])

 //项目列表
.controller("projectCtrl",["$scope","$state","project","$stateParams",
  function($scope,$state,project,$stateParams){
  $scope.porjectList = [];
  $scope.pageNo = 1;
  $scope.pageSize = 10;
  $scope.nltip = 0;
  $scope.title = "";
  $scope.type  = "";
 
  if($stateParams.type == "scene-photo") {
    $scope.title = "现场拍照";
  } else if($stateParams.type == "doc-browsing") {
    $scope.title = "文档浏览";
  } else if($stateParams.type == "project-schedule") {
    $scope.title = "施工进度反馈";
  }  else if($stateParams.type == "purchase-schedule") {
    $scope.title = "项目采购进度反馈";
  } else if($stateParams.type == "monitor-log") {
    $scope.title = "监制日志";
  } else if($stateParams.type == "logistics-damaged") {
    $scope.title = "物流破损";
  }
  $scope.doRefreshProjectList = function() {
    $scope.pageNo = 1;
    project.getPojectList($stateParams.type,$scope.userInfo.id,$scope.userInfo.prjId,$scope.pageNo,$scope.pageSize).then(function(data) {
      if($scope.nltip) {
        $scope.$broadcast("scroll.refreshComplete");
      }
      $scope.nltip = 2;
      if(data.code==1) {
        
        $scope.porjectList= data.list;
        if(data.list.length>$scope.pageSize-1) {
          $scope.showScroll = true;
          $scope.pageNo++;
        } else {
           $scope.nltip = 1;
        }
      } else {
        $scope.showToast(data.msg);
      }
    }, function() {
      if($scope.nltip) {
        $scope.$broadcast("scroll.refreshComplete");
      }
      $scope.nltip = 2;
    })
  }

  $scope.loadMoreProjectList = function() {
    project.getPojectList($stateParams.type,$scope.userInfo.id,$scope.userInfo.PrjId,$scope.pageNo,$scope.pageSize).then(function(data) {
      if(data.code==1) {
        $scope.porjectList=$scope.porjectList.concat(data.list);
        if(data.list.length <$scope.pageSize || data.list.length <1) {
          $scope.showScroll = false;
          $scope.nltip = 1;
        }
        $scope.pageNo++;
      } else {
        $scope.showToast(data.msg);
      }
      $scope.$broadcast("scroll.infiniteScrollComplete");
    },function() {
      $scope.$broadcast("scroll.infiniteScrollComplete");
    })
  }

  $scope.doRefreshProjectList();
  $scope.jumpPage = function(id,name) {
    $state.go($stateParams.type,{
      id:id,
      name:name
    });
   }
}])

 //现在拍照详情
.controller("scenePhotoCtrl",["$scope","$state","$stateParams","scenePhoto","$rootScope",
  function($scope, $state,$stateParams,scenePhoto,$rootScope){
  $scope.scenePhotoList = [];
  $scope.pageNo = 1;
  $scope.pageSize = 10;
  $scope.nltip = 0;
  $scope.title = $stateParams.name;
  $scope.doRefreshScenePhotoList = function() {
    $scope.pageNo = 1;
    scenePhoto.getList($scope.userInfo.id,$stateParams.id,$scope.pageNo,$scope.pageSize).then(function(data) {
      if($scope.nltip) {
        $scope.$broadcast("scroll.refreshComplete");
      }
      $scope.nltip = 2;
      $scope.scenePhotoList = [];
      if(data.code==1) {
        for(var i =0 ; i<data.list.length; i++) {
          if(data.list[i].scenePhoto) {
            data.list[i].scenePhoto = JSON.parse(data.list[i].scenePhoto)
          }
          $scope.scenePhotoList.push(data.list[i])
        }
        if(data.list.length>$scope.pageSize-1) {
          $scope.showScroll = true;
          $scope.pageNo++;
        } else {
           $scope.nltip = 1;
        }
      } else {
        $scope.showToast(data.msg);
      }
    }, function() {
      if($scope.nltip) {
        $scope.$broadcast("scroll.refreshComplete");
      }
      $scope.nltip = 2;
    })
  }

  $scope.loadMoreScenePhotoList = function() {
    scenePhoto.getList($scope.userInfo.id,$stateParams.id,$scope.pageNo,$scope.pageSize).then(function(data) {
       if(data.code==1) {
        for(var i =0 ; i<data.list.length; i++) {
          if(data.list[i].scenePhoto) {
            data.list[i].scenePhoto = JSON.parse(data.list[i].scenePhoto)
          }
          $scope.scenePhotoList.push(data.list[i])
        }
        if(data.list.length <$scope.pageSize || data.list.length <1) {
          $scope.showScroll = false;
          $scope.nltip = 1;
        }
        $scope.pageNo++;
      } else {
        $scope.showToast(data.msg);
      }
      $scope.$broadcast("scroll.infiniteScrollComplete");
    },function() {
      $scope.$broadcast("scroll.infiniteScrollComplete");
    })
  }

  $scope.doRefreshScenePhotoList();
  $scope.jumpAdd = function() {
    $state.go("scene-photo-add",{
      id:$stateParams.id,
      name:$stateParams.name
    });
   }

  var listenerFresh = $rootScope.$on("scenePhoto", function(event, data) {
    $scope.doRefreshScenePhotoList();
  })

  $scope.$on('$destroy',function() {
    listenerFresh();
    listenerFresh = null;
  })
}])

//现在拍照添加
.controller("scenePhotoAddCtrl",["$scope","$ionicActionSheet","$stateParams","scenePhoto","$cordovaCamera","$cordovaImagePicker","$ionicLoading","constants","$ionicPopup","$cordovaFileTransfer","$rootScope", function($scope,$ionicActionSheet,$stateParams,scenePhoto, $cordovaCamera,$cordovaImagePicker,
    $ionicLoading,constants,$ionicPopup,$cordovaFileTransfer,$rootScope){
    $scope.imgList = []
    $scope.addPhoto = {
      projectInfoId:$stateParams.id,
      projectInfoName:$stateParams.name,
      photoCategory:'',
      explain:'',
      scenePhoto:angular.toJson($scope.imgList),
      createUserId:$scope.userInfo.id,
      createUser:$scope.userInfo.name
    }
 
  $scope.removeImg = function(img) {
    $scope.imgList.splice($scope.imgList.indexOf(img),1);
  }
  $scope.select = {
    photocategor:''
  }

  $scope.photocategorList = [];

  $scope.getPhotoCategory = function() {
    scenePhoto.getPhotoCategory().then(function(data) {
      $scope.photocategorList = data.list;
      if($scope.photocategorList.length>0) {
        $scope.select.photocategor = $scope.photocategorList[0];
      }
    }) 
   }

  $scope.getPhotoCategory();

  $scope.selectPhotoType = function () {
    $ionicActionSheet.show({
      cssClass: "share-popu",
      buttons:[{ text: '拍照' }, { text: '从手机相册选择' }],
      cancelText: '取消',
      buttonClicked: function(index) {
        if(index == 0){
          $scope.takePhoto();  
        }else if(index == 1){
          $scope.pickImage();
        }
        return true;
      }
    });
  }
   /**
   * 从相机上传照片
   */
  $scope.takePhoto = function(){
    var options = {
      destinationType : Camera.DestinationType.FILE_URI,
      sourceType : Camera.PictureSourceType.CAMERA,
      correctOrientation:true
    };
    $cordovaCamera.getPicture(options).then(function(imageURL){
      upload(imageURL);
    },function (error){
       $scope.showToast("拍照失败");
    })
  }

  /**
   * 从文件选择照片
   */
  $scope.pickImage = function () {
      var options = {
          maximumImagesCount: 1,
          width: 800,
          height: 800,
          quality: 50
      };
      $cordovaImagePicker.getPictures(options).then(function (results) {
        for(var i = 0; i<results.length; i++) {
          //判断正反面添加图片
         upload(results[i]);
        }
      }, function (error) {
          $scope.showToast("选择失败");
      });
  }

  //上传图片
   function upload(picUrl){
    $ionicLoading.show({
      template: '<i class="upload"><ion-spinner icon="android"></ion-spinner>上传图片中...</i>'
    });
    var uri = encodeURI(constants.getRoot().uploadFile);
      var options = new FileUploadOptions();
      options.fileKey = "fileName";
      options.fileName = picUrl.substr(picUrl.lastIndexOf('/')+1);
      options.mimeType = "text/plain";
      var  headers = {'headerParam':'headerValue','equipNo' : window.localStorage['UUID'] || ''}; //ab5f6c786342d2cf
      options.headers = headers;
      $cordovaFileTransfer.upload(uri,picUrl,options).then(function(o){
      $ionicLoading.hide();
        var n = JSON.parse(o.response);
        if(n.attList && n.attList.length>0) {
          $scope.imgList.push({
            "oldFileName":n.attList[0].oldFileName,
            "newFileName":n.attList[0].newFileName
          })
        }
      },function(o){
        $ionicLoading.hide(), $ionicPopup.show({
        template: "图片上传失败,是否重试？",
        title: "提示信息",
        buttons: [{
          text: "取消"
        }, {
          text: "<b>重试</b>",
          type: "button-positive",
          onTap: function(e) {
            $ionicLoading.hide();
            upload(picUrl);
          }
        }]
      })
    })
  }

  $scope.submit = function() {
    if($scope.select.photocategor) {
      $scope.addPhoto.photoCategory = $scope.select.photocategor.code;
    }
    $scope.addPhoto.scenePhoto = angular.toJson($scope.imgList)
    $scope.showLoad();
    scenePhoto.addScenePhoto($scope.addPhoto).then(function(data) {
      $scope.hideLoad();
      if(data.code==1) {
        $rootScope.$emit('scenePhoto');
        $scope.toClose();
      }
      $scope.showToast(data.msg);
    })
  }
}])

//上传文档详情
.controller("docUploadCtrl",["$scope","$ionicActionSheet","$stateParams","doc","$cordovaCamera", "$cordovaImagePicker","$ionicLoading","constants","$ionicPopup","$cordovaFileTransfer",
  function($scope,$ionicActionSheet,$stateParams,doc,$cordovaCamera,$cordovaImagePicker,
    $ionicLoading,constants,$ionicPopup,$cordovaFileTransfer){
    $scope.imgList = []
    $scope.docDetail = null;
    $scope.uploadDocDetail = function() {
      $scope.showLoad();
      doc.uploadDocDetail($stateParams.id).then(function(data) {
        if(data.code==1) {
          $scope.docDetail = data.data;
        } else {
          $scope.showToast(data.msg);
        }
        $scope.hideLoad();
      }) 
    }

    $scope.uploadDocDetail();
 

    $scope.submit = function() {
      $scope.showLoad();
      doc.addDocImg($stateParams.id, angular.toJson($scope.imgList)).then(function(data) {
        $scope.hideLoad();
        if(data.code==1) {
          $scope.toClose();
        }
        $scope.showToast(data.msg);
      })
    }

    $scope.removeImg = function(img) {
      $scope.imgList.splice($scope.imgList.indexOf(img),1);
    }

    $scope.selectPhotoType = function () {

      var sendData={
        actionType:rnConfig.selectImage,
        info:{
          url:constants.getRoot().uploadFile
        }
      };
      if(window.WebViewBridge) {
        window.WebViewBridge.send(JSON.stringify(sendData));
      };
  
      // $ionicActionSheet.show({
      //   cssClass: "share-popu",
      //   buttons:[{ text: '拍照' }, { text: '从手机相册选择' }],
      //   cancelText: '取消',
      //   buttonClicked: function(index) {
      //     if(index == 0){
      //       $scope.takePhoto();  
      //     }else if(index == 1){
      //       $scope.pickImage();
      //     }
      //     return true;
      //   }
      // });
    }

    window.selectImageCallback = function(str) {
      if(str.attList && str.attList.length>0) {
        $scope.imgList.push({
          "oldFileName":n.attList[0].oldFileName,
          "newFileName":n.attList[0].newFileName
        })
      }
    }

    /**
     * 从相机上传照片
     */
    $scope.takePhoto = function(){
      var options = {
        destinationType : Camera.DestinationType.FILE_URI,
        sourceType : Camera.PictureSourceType.CAMERA,
        correctOrientation:true
      };
      $cordovaCamera.getPicture(options).then(function(imageURL){
        upload(imageURL);
      },function (error){
        $scope.showToast("拍照失败");
      })
    }

    /**
     * 从文件选择照片
     */
    $scope.pickImage = function () {
        var options = {
            maximumImagesCount: 1,
            width: 800,
            height: 800,
            quality: 50
        };
        $cordovaImagePicker.getPictures(options).then(function (results) {
          for(var i = 0; i<results.length; i++) {
            //判断正反面添加图片
          upload(results[i]);
          }
        }, function (error) {
            $scope.showToast("选择失败");
        });
    }

  //上传图片
  function upload(picUrl){
    $ionicLoading.show({
      template: '<i class="upload"><ion-spinner icon="android"></ion-spinner>上传图片中...</i>'
    });
    var uri = encodeURI(constants.getRoot().uploadFile);
      var options = new FileUploadOptions();
      options.fileKey = "fileName";
      options.fileName = picUrl.substr(picUrl.lastIndexOf('/')+1);
      options.mimeType = "text/plain";
      var  headers = {'headerParam':'headerValue','equipNo' : window.localStorage['UUID'] || ''}; //ab5f6c786342d2cf
      options.headers = headers;
      $cordovaFileTransfer.upload(uri,picUrl,options).then(function(o){
      $ionicLoading.hide();
        var n = JSON.parse(o.response);
        if(n.attList && n.attList.length>0) {
          $scope.imgList.push({
            "oldFileName":n.attList[0].oldFileName,
            "newFileName":n.attList[0].newFileName
          })
        }
      },function(o){
        $ionicLoading.hide(), $ionicPopup.show({
        template: "图片上传失败,是否重试？",
        title: "提示信息",
        buttons: [{
          text: "取消"
        }, {
          text: "<b>重试</b>",
          type: "button-positive",
          onTap: function(e) {
            $ionicLoading.hide();
            upload(picUrl);
          }
        }]
      })
    })
  }
}])

//文档预览
.controller("docBrowsingCtrl",["$scope","$stateParams","doc","$state",
  function($scope,$stateParams,doc,$state){
    $scope.showLevel = 1;
    $scope.levelData = [
      {
        id:'all',
        name:"文档列表"
      }
    ]
    $scope.docTreeAll = null;
    $scope.docTreeList = [];
    $scope.FolderIdList = [];
    $scope.getDocTree = function() {
      $scope.showLoad();
      doc.getDocTree($stateParams.id,$scope.userInfo.code).then(function(data) {
        if(data.code==1) {
          $scope.docTreeAll = data.list;
          getTreeOne(data.list);
          if(data.list.length==0) {
            $scope.nltip = 1;
          }
         
        } else {
          $scope.showToast(data.msg);
        }
        $scope.hideLoad();
      }) 
    }

    getTreeOne = function(data)  {
      $scope.docTreeList = [];
      for(var i=0; i<data.length; i++) {
        var fullidArr = data[i].fullid.split(".");
        if(fullidArr.length==2) {
          $scope.docTreeList.push(data[i])
        }
        $scope.showLevel = 1;
      }
    }

    $scope.nextLevel= function(id,name) {
      var list = $scope.docTreeAll;
      $scope.docTreeList = [];
      $scope.levelData[$scope.showLevel] = {
        id:id,
        name:name
      }
      for(var i=0; i<list.length; i++) {
        if(list[i].parentId==id) {
          $scope.docTreeList.push(list[i]);
        }
      }
      $scope.showLevel++;
      if($scope.docTreeList.length==0) {
        $scope.getFolderIdList(id);
      }
    }

    $scope.preLevel = function(value,index) {
      if(value=="all") {
        getTreeOne($scope.docTreeAll);
      } else {
        var list = $scope.docTreeAll;
        $scope.docTreeList = [];
        for(var i=0; i<list.length; i++) {
          if(list[i].parentId==value) {
            $scope.docTreeList.push(list[i])
          }
        }
      }
      $scope.showLevel = index;
      $scope.nltip = 0;
      if($scope.docTreeList.length==0) {
        $scope.getFolderIdList(value);
      }
    }

    $scope.getFolderIdList = function(folderId) {
      $scope.showLoad();
      doc.getFolderIdList(folderId).then(function(data) {
        if(data.code==1) {
          $scope.FolderIdList = data.list;
          $scope.nltip=1;
        } else {
          $scope.showToast(data.msg);
        }
        $scope.hideLoad();
      }) 
    }

    $scope.jumpDetail = function(id) {
      $state.go("doc-browsing-des",{
        id:id
      });
    }


    $scope.getDocTree();
}])

.controller("docBrowsingDesCtrl",["$scope","doc","$stateParams",function($scope,doc,$stateParams){
  $scope.docDetail = null;
  $scope.uploadDocDetail = function() {
    $scope.showLoad();
    doc.uploadDocDetail($stateParams.id).then(function(data) {
      if(data.code==1) {
        $scope.docDetail = data.data;
      } else {
        $scope.showToast(data.msg);
      }
      $scope.getDocImgByDocId();
      $scope.hideLoad();
    }) 
  }

  $scope.getDocImgByDocId = function() {
    $scope.showLoad();
    doc.getDocImgByDocId($stateParams.id).then(function(data) {
      if(data.code==1) {
        if(data.data && data.data.length>0) {
          $scope.imgList = JSON.parse(data.data);
        }
        
      } else {
        $scope.showToast(data.msg);
      }
      $scope.hideLoad();
    }) 
  }
  $scope.uploadDocDetail();
}])

 //采购进度反馈详情
.controller("purchaseScheduleCtrl",["$scope","$state","$stateParams","purchaseSchedule","$rootScope","appMain",
  function($scope, $state,$stateParams,purchaseSchedule,$rootScope,appMain){
    $scope.scenePhotoList = [];
    $scope.pageNo = 1;
    $scope.pageSize = 10;
    $scope.nltip = 0;
    $scope.title = $stateParams.name;
    $scope.doRefreshScenePhotoList = function() {
      $scope.pageNo = 1;
      purchaseSchedule.getList($scope.userInfo.id,$stateParams.id,$scope.pageNo,$scope.pageSize).then(function(data) {
        if($scope.nltip) {
          $scope.$broadcast("scroll.refreshComplete");
        }
        $scope.nltip = 2;
        $scope.scenePhotoList = [];
        if(data.code==1) {
          for(var i =0 ; i<data.list.length; i++) {
            if(data.list[i].photoFile) {
              data.list[i].photoFile = JSON.parse(data.list[i].photoFile)
            }
            console.log(data.list[i].photoFile)
            $scope.scenePhotoList.push(data.list[i])
          }
          if(data.list.length>$scope.pageSize-1) {
            $scope.showScroll = true;
            $scope.pageNo++;
          } else {
            $scope.nltip = 1;
          }
        } else {
          $scope.showToast(data.msg);
        }
      }, function() {
        if($scope.nltip) {
          $scope.$broadcast("scroll.refreshComplete");
        }
        $scope.nltip = 2;
      })
    }

    $scope.loadMoreScenePhotoList = function() {
      purchaseSchedule.getList($scope.userInfo.id,$stateParams.id,$scope.pageNo,$scope.pageSize).then(function(data) {
        if(data.code==1) {
          for(var i =0 ; i<data.list.length; i++) {
            console.log(data.list[i]);
            if(data.list[i].photoFile) {
              data.list[i].photoFile = JSON.parse(data.list[i].photoFile)
              console.log(data.list[i].photoFil);
            }
            $scope.scenePhotoList.push(data.list[i])
          }
          if(data.list.length <$scope.pageSize || data.list.length <1) {
            $scope.showScroll = false;
            $scope.nltip = 1;
          }
          $scope.pageNo++;
        } else {
          $scope.showToast(data.msg);
        }
        $scope.$broadcast("scroll.infiniteScrollComplete");
      },function() {
        $scope.$broadcast("scroll.infiniteScrollComplete");
      })
    }

    $scope.doRefreshScenePhotoList();
    $scope.jumpAdd = function() {
      $state.go("purchase-schedule-add",{
        id:$stateParams.id,
        name:$stateParams.name
      });
    }

    $scope.jumpDetail = function(data) {
      console.log(1);
      appMain.setSession({
        key:'purchaseScheduleDes',
        data:data
      })
      $state.go("purchase-schedule-des");
    }

    var listenerFresh = $rootScope.$on("purchaseSchedule", function(event, data) {
      $scope.doRefreshScenePhotoList();
    })
  
    $scope.$on('$destroy',function() {
      listenerFresh();
      listenerFresh = null;
    })
}])

//采购进度反馈添加
.controller("purchaseScheduleAddCtrl",["$scope","$ionicActionSheet","$stateParams","purchaseSchedule","$cordovaCamera",
  "$cordovaImagePicker","$ionicLoading","constants","$ionicPopup","$cordovaFileTransfer","$rootScope",
  function($scope,$ionicActionSheet,$stateParams,purchaseSchedule,$cordovaCamera,$cordovaImagePicker,
    $ionicLoading,constants,$ionicPopup,$cordovaFileTransfer,$rootScope){

    $scope.imgList = []
    $scope.addPurchase = {
      projectInfoId:$stateParams.id,
      projectInfoName:$stateParams.name,
      contract:'',//合同ID
      contractName:'', //合同名称
      contractCode:'', //合同编号
      equipCode:'', //设备编码
      equipName:'', //设备名称
      equipId:'', //设备ID
      phase:'', //步骤
      projress:'',//进度
      totalProjress:'',//累计进度
      createUserId:$scope.userInfo.id,
      createUser:$scope.userInfo.name
    }

    $scope.select = {
      contract:'', //选择的合同信息
      equipment:'',  //选择的设备名称
      step:''   //步骤
    }

    //获取合同列表
    $scope.getContractList = function() {
      purchaseSchedule.getContractList($stateParams.id).then(function(data) {
        $scope.contractList = data.list;
        if($scope.contractList.length>0) {
          $scope.select.contract = $scope.contractList[0];
          $scope.getEquipmentList($scope.contractList[0].contractId);
        }
      }) 
    }
    //获取设备名称
    $scope.getEquipmentList =  function(contractId) {
      purchaseSchedule.getEquipmentList(contractId).then(function(data) {
        $scope.equipmentList = data.list;
        if($scope.equipmentList.length>0) {
          $scope.select.equipment = $scope.equipmentList[0];
        }
      }) 
    }

 
    //获取设备名称
    $scope.getStepList =  function() {
      purchaseSchedule.getStepList($stateParams.id).then(function(data) {
        $scope.stepListList = data.list;
        if($scope.stepListList.length>0) {
          $scope.select.step = $scope.stepListList[0];
        }
      }) 
    }
    
    $scope.selectContract= function() {
      console.log($scope.select.contract)
      $scope.getEquipmentList($scope.select.contract.contractId);
    }

    $scope.submit = function() {
      $scope.addPurchase.contract = $scope.select.contract.contractId;//合同ID
      $scope.addPurchase.contractName = $scope.select.contract.contractName; //合同名称
      $scope.addPurchase.contractCode = $scope.select.contract.contractCode; //合同编号
      $scope.addPurchase.equipCode = $scope.select.equipment.code; //设备编码
      $scope.addPurchase.equipName = $scope.select.equipment.name; //设备名称
      $scope.addPurchase.equipId = $scope.select.equipment.id; //设备ID
      $scope.addPurchase.phase = $scope.select.step.code;//步骤
      $scope.addPurchase.phaseName = $scope.select.step.name;//步骤
      $scope.addPurchase.photoFile = angular.toJson($scope.imgList)
      $scope.showLoad();
      purchaseSchedule.procurementAdd($scope.addPurchase).then(function(data) {
        $scope.hideLoad();
         if(data.code==1) {
          $rootScope.$emit('purchaseSchedule');
          $scope.toClose();
         }
         $scope.showToast(data.msg);
      }) 
    }
    $scope.getContractList();
    $scope.getStepList();

    $scope.removeImg = function(img) {
      $scope.imgList.splice($scope.imgList.indexOf(img),1);
    }
  
    $scope.selectPhotoType = function () {
      $ionicActionSheet.show({
        cssClass: "share-popu",
        buttons:[{ text: '拍照' }, { text: '从手机相册选择' }],
        cancelText: '取消',
        buttonClicked: function(index) {
          if(index == 0){
            $scope.takePhoto();  
          }else if(index == 1){
            $scope.pickImage();
          }
          return true;
        }
      });
    }
     /**
     * 从相机上传照片
     */
    $scope.takePhoto = function(){
      var options = {
        destinationType : Camera.DestinationType.FILE_URI,
        sourceType : Camera.PictureSourceType.CAMERA,
        correctOrientation:true
      };
      $cordovaCamera.getPicture(options).then(function(imageURL){
        upload(imageURL);
      },function (error){
         $scope.showToast("拍照失败");
      })
    }
  
    /**
     * 从文件选择照片
     */
    $scope.pickImage = function () {
        var options = {
            maximumImagesCount: 1,
            width: 800,
            height: 800,
            quality: 50
        };
        $cordovaImagePicker.getPictures(options).then(function (results) {
          for(var i = 0; i<results.length; i++) {
            //判断正反面添加图片
           upload(results[i]);
          }
        }, function (error) {
            $scope.showToast("选择失败");
        });
    }
  
    //上传图片
    function upload(picUrl){
      $ionicLoading.show({
        template: '<i class="upload"><ion-spinner icon="android"></ion-spinner>上传图片中...</i>'
      });
      var uri = encodeURI(constants.getRoot().uploadFile);
        var options = new FileUploadOptions();
        options.fileKey = "fileName";
        options.fileName = picUrl.substr(picUrl.lastIndexOf('/')+1);
        options.mimeType = "text/plain";
        var  headers = {'headerParam':'headerValue','equipNo' : window.localStorage['UUID'] || ''}; //ab5f6c786342d2cf
        options.headers = headers;
        $cordovaFileTransfer.upload(uri,picUrl,options).then(function(o){
        $ionicLoading.hide();
          var n = JSON.parse(o.response);
          if(n.attList && n.attList.length>0) {
            $scope.imgList.push({
              "oldFileName":n.attList[0].oldFileName,
              "newFileName":n.attList[0].newFileName
            })
          }
        },function(o){
          $ionicLoading.hide(), $ionicPopup.show({
          template: "图片上传失败,是否重试？",
          title: "提示信息",
          buttons: [{
            text: "取消"
          }, {
            text: "<b>重试</b>",
            type: "button-positive",
            onTap: function(e) {
              $ionicLoading.hide();
              upload(picUrl);
            }
          }]
        })
      })
    }
}])

//采购进度反馈详情
.controller("purchaseScheduleDesCtrl",["$scope","appMain", function($scope,appMain){
  $scope.apurchaseDes = appMain.getSession('purchaseScheduleDes');
}])

//监制日志
.controller("monitorLogCtrl",["$scope","$state","$stateParams","monitorLog","$rootScope","appMain",
  function($scope, $state,$stateParams,monitorLog,$rootScope,appMain){
    $scope.scenePhotoList = [];
    $scope.pageNo = 1;
    $scope.pageSize = 10;
    $scope.nltip = 0;
    $scope.title = $stateParams.name;
    $scope.doRefreshScenePhotoList = function() {
      $scope.pageNo = 1;
      monitorLog.getList($scope.userInfo.id,$stateParams.id,$scope.pageNo,$scope.pageSize).then(function(data) {
        if($scope.nltip) {
          $scope.$broadcast("scroll.refreshComplete");
        }
        $scope.nltip = 2;
        $scope.scenePhotoList = [];
        if(data.code==1) {
          for(var i =0 ; i<data.list.length; i++) {
            if(data.list[i].supervisoryPhoto) {
              data.list[i].supervisoryPhoto = JSON.parse(data.list[i].supervisoryPhoto)
            }
            $scope.scenePhotoList.push(data.list[i])
          }
          if(data.list.length>$scope.pageSize-1) {
            $scope.showScroll = true;
            $scope.pageNo++;
          } else {
            $scope.nltip = 1;
          }
        } else {
          $scope.showToast(data.msg);
        }
      }, function() {
        if($scope.nltip) {
          $scope.$broadcast("scroll.refreshComplete");
        }
        $scope.nltip = 2;
      })
    }

    $scope.loadMoreScenePhotoList = function() {
      monitorLog.getList($scope.userInfo.id,$stateParams.id,$scope.pageNo,$scope.pageSize).then(function(data) {
        if(data.code==1) {
          for(var i =0 ; i<data.list.length; i++) {
            if(data.list[i].supervisoryPhoto) {
              data.list[i].supervisoryPhoto = JSON.parse(data.list[i].supervisoryPhoto)
            }
            $scope.scenePhotoList.push(data.list[i])
          }
          if(data.list.length <$scope.pageSize || data.list.length <1) {
            $scope.showScroll = false;
            $scope.nltip = 1;
          }
          $scope.pageNo++;
        } else {
          $scope.showToast(data.msg);
        }
        $scope.$broadcast("scroll.infiniteScrollComplete");
      },function() {
        $scope.$broadcast("scroll.infiniteScrollComplete");
      })
    }

    $scope.doRefreshScenePhotoList();
    $scope.jumpAdd = function() {
      $state.go("monitor-log-add",{
        id:$stateParams.id,
        name:$stateParams.name
      });
    }

    $scope.jumpDetail = function(data) {
      appMain.setSession({
        key:'monitorLogDes',
        data:data
      })
      $state.go("monitor-log-des");
    }

    var listenerFresh = $rootScope.$on("monitorLog", function(event, data) {
      $scope.doRefreshScenePhotoList();
    })
  
    $scope.$on('$destroy',function() {
      listenerFresh();
      listenerFresh = null;
    })
}])

//监制日志添加
.controller("monitorLogAddCtrl",["$scope","$ionicActionSheet","$stateParams","monitorLog","appMain","$cordovaCamera",
  "$cordovaImagePicker","$ionicLoading","constants","$ionicPopup","$cordovaFileTransfer","$rootScope",
  function($scope,$ionicActionSheet,$stateParams,monitorLog,appMain,$cordovaCamera,$cordovaImagePicker,
    $ionicLoading,constants,$ionicPopup,$cordovaFileTransfer,$rootScope){
    $scope.imgList = []
    $scope.addMonitor = {
      projectInfoId:$stateParams.id,
      projectInfoName:$stateParams.name,
      contract:'',//合同ID
      contractName:'', //合同名称
      contractCode:'', //合同编号
      equipCode:'', //设备编码
      equipmentName:'', //设备名称
      equipment:'', //设备ID
      createUserId:$scope.userInfo.id,
      createUser:$scope.userInfo.name,
      date:appMain.getDateStr(0,'YY-MM-DD'),  //监制日期
      supplier:'',	  //供应商ID
      supplierName:'',	  //供应商名称
      workContent:'',   //当日主要工作内容
      question:'',	    //存在的主要问题
      controlResult:'',	//处理情况
    }
 
    $scope.select = {
      contract:'', //选择的合同信息
      equipment:'',  //选择的设备名称
      step:''   //步骤
    }

    //获取合同列表
    $scope.getContractList = function() {
      monitorLog.getContractList($stateParams.id).then(function(data) {
        $scope.contractList = data.list;
        if($scope.contractList.length>0) {
          $scope.select.contract = $scope.contractList[0];
          $scope.getEquipmentList($scope.contractList[0].contractId);
        }
      }) 
    }
    //获取设备名称
    $scope.getEquipmentList =  function(contractId) {
      monitorLog.getEquipmentList(contractId).then(function(data) {
        $scope.equipmentList = data.list;
        if($scope.equipmentList.length>0) {
          $scope.select.equipment = $scope.equipmentList[0];
        }
      }) 
    }

    $scope.selectContract= function() {
      console.log($scope.select.contract)
      $scope.getEquipmentList($scope.select.contract.contractId);
    }

    $scope.submit = function() {
      $scope.addMonitor.contract = $scope.select.contract.contractId;//合同ID
      $scope.addMonitor.contractName = $scope.select.contract.contractName; //合同名称
      $scope.addMonitor.contractCode = $scope.select.contract.contractCode; //合同编号
      $scope.addMonitor.equipCode = $scope.select.equipment.code; //设备编码
      $scope.addMonitor.equipmentName = $scope.select.equipment.name; //设备名称
      $scope.addMonitor.equipment = $scope.select.equipment.id; //设备ID
      $scope.addMonitor.supplier = $scope.select.contract.supplier;//步骤
      $scope.addMonitor.supplierName = $scope.select.contract.supplierName;//步骤
      $scope.addMonitor.supervisoryPhoto = angular.toJson($scope.imgList)

      $scope.showLoad();
      monitorLog.monitorAdd($scope.addMonitor).then(function(data) {
        $scope.hideLoad();
         if(data.code==1) {
          $rootScope.$emit('monitorLog');
          $scope.toClose();
         }
         $scope.showToast(data.msg);
      }) 
    }
    $scope.getContractList();
    
    $scope.removeImg = function(img) {
      $scope.imgList.splice($scope.imgList.indexOf(img),1);
    }
  
    $scope.selectPhotoType = function () {
      $ionicActionSheet.show({
        cssClass: "share-popu",
        buttons:[{ text: '拍照' }, { text: '从手机相册选择' }],
        cancelText: '取消',
        buttonClicked: function(index) {
          if(index == 0){
            $scope.takePhoto();  
          }else if(index == 1){
            $scope.pickImage();
          }
          return true;
        }
      });
    }
     /**
     * 从相机上传照片
     */
    $scope.takePhoto = function(){
      var options = {
        destinationType : Camera.DestinationType.FILE_URI,
        sourceType : Camera.PictureSourceType.CAMERA,
        correctOrientation:true
      };
      $cordovaCamera.getPicture(options).then(function(imageURL){
        upload(imageURL);
      },function (error){
         $scope.showToast("拍照失败");
      })
    }
  
    /**
     * 从文件选择照片
     */
    $scope.pickImage = function () {
        var options = {
            maximumImagesCount: 1,
            width: 800,
            height: 800,
            quality: 50
        };
        $cordovaImagePicker.getPictures(options).then(function (results) {
          for(var i = 0; i<results.length; i++) {
            //判断正反面添加图片
           upload(results[i]);
          }
        }, function (error) {
            $scope.showToast("选择失败");
        });
    }
  
    //上传图片
    function upload(picUrl){
      $ionicLoading.show({
        template: '<i class="upload"><ion-spinner icon="android"></ion-spinner>上传图片中...</i>'
      });
      var uri = encodeURI(constants.getRoot().uploadFile);
        var options = new FileUploadOptions();
        options.fileKey = "fileName";
        options.fileName = picUrl.substr(picUrl.lastIndexOf('/')+1);
        options.mimeType = "text/plain";
        var  headers = {'headerParam':'headerValue','equipNo' : window.localStorage['UUID'] || ''}; //ab5f6c786342d2cf
        options.headers = headers;
        $cordovaFileTransfer.upload(uri,picUrl,options).then(function(o){
        $ionicLoading.hide();
          var n = JSON.parse(o.response);
          if(n.attList && n.attList.length>0) {
            $scope.imgList.push({
              "oldFileName":n.attList[0].oldFileName,
              "newFileName":n.attList[0].newFileName
            })
          }
        },function(o){
          $ionicLoading.hide(), $ionicPopup.show({
          template: "图片上传失败,是否重试？",
          title: "提示信息",
          buttons: [{
            text: "取消"
          }, {
            text: "<b>重试</b>",
            type: "button-positive",
            onTap: function(e) {
              $ionicLoading.hide();
              upload(picUrl);
            }
          }]
        })
      })
    }
}])

//采购进度反馈详情
.controller("monitorLogDesCtrl",["$scope","appMain",function($scope,appMain){
  $scope.monitorLogDes = appMain.getSession('monitorLogDes');
}])

//施工进度反馈
.controller("projectScheduleCtrl",["$scope","$state","$stateParams","projectSchedule","$rootScope","appMain",
  function($scope, $state,$stateParams,projectSchedule,$rootScope,appMain){
  //  $stateParams.id ="a7a60108-5624-4302-aab0-d46aae12190e";
  $scope.scenePhotoList = [];
  $scope.pageNo = 1;
  $scope.pageSize = 10;
  $scope.nltip = 0;
  $scope.title = $stateParams.name;
  $scope.doRefreshScenePhotoList = function() {
    $scope.pageNo = 1;
    projectSchedule.getList($scope.userInfo.id,$stateParams.id,$scope.pageNo,$scope.pageSize).then(function(data) {
      if($scope.nltip) {
        $scope.$broadcast("scroll.refreshComplete");
      }
      $scope.nltip = 2;
      $scope.scenePhotoList = [];
      if(data.code==1) {
        for(var i =0 ; i<data.list.length; i++) {
          if(data.list[i].attach ) {
         
            data.list[i].attach = JSON.parse(data.list[i].attach)
          }
          $scope.scenePhotoList.push(data.list[i])
        }
        if(data.list.length>$scope.pageSize-1) {
          $scope.showScroll = true;
          $scope.pageNo++;
        } else {
          $scope.nltip = 1;
        }
      } else {
        $scope.showToast(data.msg);
      }
    }, function() {
      if($scope.nltip) {
        $scope.$broadcast("scroll.refreshComplete");
      }
      $scope.nltip = 2;
    })
  }

  $scope.loadMoreScenePhotoList = function() {
    projectSchedule.getList($scope.userInfo.id,$stateParams.id,$scope.pageNo,$scope.pageSize).then(function(data) {
      if(data.code==1) {
        for(var i =0 ; i<data.list.length; i++) {
          if(data.list[i].attach) {
            data.list[i].attach = JSON.parse(data.list[i].attach)
          }
          $scope.scenePhotoList.push(data.list[i])
        }
        if(data.list.length <$scope.pageSize || data.list.length <1) {
          $scope.showScroll = false;
          $scope.nltip = 1;
        }
        $scope.pageNo++;
      } else {
        $scope.showToast(data.msg);
      }
      $scope.$broadcast("scroll.infiniteScrollComplete");
    },function() {
      $scope.$broadcast("scroll.infiniteScrollComplete");
    })
  }

  $scope.doRefreshScenePhotoList();
  $scope.jumpAdd = function() {
    $state.go("project-schedule-add",{
      id:$stateParams.id,
      name:$stateParams.name
    });
  }

  $scope.jumpDetail = function(data) {
    appMain.setSession({
      key:'projectScheduleDes',
      data:data
    })
    $state.go("project-schedule-des");
  }

  var listenerFresh = $rootScope.$on("projectSchedule", function(event, data) {
    $scope.doRefreshScenePhotoList();
  })

  $scope.$on('$destroy',function() {
    listenerFresh();
    listenerFresh = null;
  })
}])

//施工进度反馈添加
.controller("projectScheduleAddCtrl",["$scope","$ionicActionSheet","$stateParams","projectSchedule","$cordovaCamera",
  "$cordovaImagePicker","$ionicLoading","constants","$ionicPopup","$cordovaFileTransfer","$rootScope",
 function($scope,$ionicActionSheet,$stateParams,projectSchedule,$cordovaCamera,
 $cordovaImagePicker,$ionicLoading,constants,$ionicPopup,$cordovaFileTransfer,$rootScope){
  $scope.imgList = [ ]
  $scope.addProjectSchedule = {
    projectInfoId:$stateParams.id,
    projectInfoName:$stateParams.name,
    createUserId:$scope.userInfo.id,
    createUser:$scope.userInfo.name,
    finishWeight:"",	//项目完成率
    totalFinishWeight:"",	//累计完成率
    process:"",	//作业ID
    processName:"",	//作业名称
    subProjectName:"",	//子项目名称
    subSectionName:"",	//分部工程名称
  }
  $scope.select = {
    subProject:'', //选择子项
    subWork:'',  //分部
    process:''   //施工作业
  }

  // 获取子项列表
  $scope.getSubProjectList = function() {
    projectSchedule.getSubProjectList($stateParams.id).then(function(data) {
      $scope.subProjectList = data.list;
      if($scope.subProjectList.length>0) {
        $scope.select.subProject = $scope.subProjectList[0];
        $scope.getSubWorkList();
      }
    }) 
  }
  //获取分部
  $scope.getSubWorkList =  function() {
    projectSchedule.getSubWorkList($stateParams.id,$scope.select.subProject.id).then(function(data) {
      $scope.subWorkList = data.list;
      if($scope.subWorkList.length>0) {
        $scope.select.subWork = $scope.subWorkList[0];
        $scope.getProcessList();
      }
    }) 
  }

  //获取分部
  $scope.getProcessList =  function() {
    projectSchedule.getProcessList($stateParams.id,$scope.select.subWork.id).then(function(data) {
      $scope.processList = data.list;
      if($scope.processList.length>0) {
        $scope.select.process = $scope.processList[0];
      }
    }) 
  }
 
  $scope.selectSubProject= function() {
    $scope.getSubWorkList();
  }

  $scope.selectSubWork= function() {
    $scope.getProcessList();
  }
 
  $scope.submit = function() {
    $scope.addProjectSchedule.process=$scope.select.process.id;	//作业ID
    $scope.addProjectSchedule.processName=$scope.select.process.name;	//作业名称
    $scope.addProjectSchedule.subProjectName=$scope.select.subWork.name;//子项目名称
    $scope.addProjectSchedule.subSectionName=$scope.select.subWork.name;//分部工程名称
    $scope.addProjectSchedule.attach = angular.toJson($scope.imgList)
    $scope.showLoad();
    projectSchedule.add($scope.addProjectSchedule).then(function(data) {
      $scope.hideLoad();
       if(data.code==1) {
        $rootScope.$emit('projectSchedule');
        $scope.toClose();
       }
       $scope.showToast(data.msg);
    }) 
  }
  $scope.getSubProjectList();

  $scope.removeImg = function(img) {
    $scope.imgList.splice($scope.imgList.indexOf(img),1);
  }

  $scope.selectPhotoType = function () {
    $ionicActionSheet.show({
      cssClass: "share-popu",
      buttons:[{ text: '拍照' }, { text: '从手机相册选择' }],
      cancelText: '取消',
      buttonClicked: function(index) {
        if(index == 0){
          $scope.takePhoto();  
        }else if(index == 1){
          $scope.pickImage();
        }
        return true;
      }
    });
  }
   /**
   * 从相机上传照片
   */
  $scope.takePhoto = function(){
    var options = {
      destinationType : Camera.DestinationType.FILE_URI,
      sourceType : Camera.PictureSourceType.CAMERA,
      correctOrientation:true
    };
    $cordovaCamera.getPicture(options).then(function(imageURL){
      upload(imageURL);
    },function (error){
       $scope.showToast("拍照失败");
    })
  }

  /**
   * 从文件选择照片
   */
  $scope.pickImage = function () {
      var options = {
          maximumImagesCount: 1,
          width: 800,
          height: 800,
          quality: 50
      };
      $cordovaImagePicker.getPictures(options).then(function (results) {
        for(var i = 0; i<results.length; i++) {
          //判断正反面添加图片
         upload(results[i]);
        }
      }, function (error) {
          $scope.showToast("选择失败");
      });
  }

  //上传图片
  function upload(picUrl){
    $ionicLoading.show({
      template: '<i class="upload"><ion-spinner icon="android"></ion-spinner>上传图片中...</i>'
    });
    var uri = encodeURI(constants.getRoot().uploadFile);
      var options = new FileUploadOptions();
      options.fileKey = "fileName";
      options.fileName = picUrl.substr(picUrl.lastIndexOf('/')+1);
      options.mimeType = "text/plain";
      var  headers = {'headerParam':'headerValue','equipNo' : window.localStorage['UUID'] || ''}; //ab5f6c786342d2cf
      options.headers = headers;
      $cordovaFileTransfer.upload(uri,picUrl,options).then(function(o){
      $ionicLoading.hide();
        var n = JSON.parse(o.response);
        if(n.attList && n.attList.length>0) {
          $scope.imgList.push({
            "oldFileName":n.attList[0].oldFileName,
            "newFileName":n.attList[0].newFileName
          })
        }
      },function(o){
        $ionicLoading.hide(), $ionicPopup.show({
        template: "图片上传失败,是否重试？",
        title: "提示信息",
        buttons: [{
          text: "取消"
        }, {
          text: "<b>重试</b>",
          type: "button-positive",
          onTap: function(e) {
            $ionicLoading.hide();
            upload(picUrl);
          }
        }]
      })
    })
  }
}])

//施工进度反馈详情
.controller("projectScheduleDesCtrl",["$scope","appMain",function($scope,appMain){
  $scope.projectScheduleDes = appMain.getSession('projectScheduleDes');
}])

//物流破损
.controller("logisticsDamagedCtrl",["$scope","$state","$stateParams","logisticsDamaged","$rootScope","$cordovaBarcodeScanner","appMain",
  function($scope, $state,$stateParams,logisticsDamaged,$rootScope,$cordovaBarcodeScanner,appMain){
   // $stateParams.id = 'a72800ed-ea79-4b91-b7dd-41455162d9e3';
    $scope.scenePhotoList = [];
    $scope.pageNo = 1;
    $scope.pageSize = 10;
    $scope.nltip = 0;
    $scope.title = $stateParams.name;
    $scope.doRefreshScenePhotoList = function() {
      $scope.pageNo = 1;
      logisticsDamaged.getList($scope.userInfo.id,$stateParams.id,$scope.pageNo,$scope.pageSize).then(function(data) {
        if($scope.nltip) {
          $scope.$broadcast("scroll.refreshComplete");
        }
        $scope.nltip = 2;
        $scope.scenePhotoList = [];
        if(data.code==1) {
          for(var i =0 ; i<data.list.length; i++) {
            if(data.list[i].damagePic) {
              data.list[i].damagePic = JSON.parse(data.list[i].damagePic)
            }
            $scope.scenePhotoList.push(data.list[i])
          }
          if(data.list.length>$scope.pageSize-1) {
            $scope.showScroll = true;
            $scope.pageNo++;
          } else {
            $scope.nltip = 1;
          }
        } else {
          $scope.showToast(data.msg);
        }
      }, function() {
        if($scope.nltip) {
          $scope.$broadcast("scroll.refreshComplete");
        }
        $scope.nltip = 2;
      })
    }

    $scope.loadMoreScenePhotoList = function() {
      logisticsDamaged.getList($scope.userInfo.id,$stateParams.id,$scope.pageNo,$scope.pageSize).then(function(data) {
        if(data.code==1) {
          for(var i =0 ; i<data.list.length; i++) {
            if(data.list[i].damagePic) {
              data.list[i].damagePic = JSON.parse(data.list[i].damagePic)
            }
            $scope.scenePhotoList.push(data.list[i])
          }
          if(data.list.length <$scope.pageSize || data.list.length <1) {
            $scope.showScroll = false;
            $scope.nltip = 1;
          }
          $scope.pageNo++;
        } else {
          $scope.showToast(data.msg);
        }
        $scope.$broadcast("scroll.infiniteScrollComplete");
      },function() {
        $scope.$broadcast("scroll.infiniteScrollComplete");
      })
    }

    $scope.doRefreshScenePhotoList();
    $scope.jumpAdd = function() {
      $scope.scanStart();
      
    }

    $scope.jumpDetail = function(data) {
      appMain.setSession({
        key:'logisticsDamagedDes',
        data:data
      })
      $state.go("logistics-damaged-des");
    }

    //扫一扫
    $scope.scanStart = function () {
      // $state.go("logistics-damaged-add",{
      //   id:$stateParams.id,
      //   name:$stateParams.name,
      //   boxSingleId:'a8c90137-f48e-4d31-bd1e-98b26fcec6ce'
      // });
      $cordovaBarcodeScanner.scan().then(function (barcodeData) {
          if(barcodeData.text) {
            $state.go("logistics-damaged-add",{
              id:$stateParams.id,
              name:$stateParams.name,
              boxSingleId:barcodeData.text
            });
          }
        }, function (error) {
          $scope.showToast("扫码失败");
        });
    };

    var listenerFresh = $rootScope.$on("logisticsDamaged", function(event, data) {
      $scope.doRefreshScenePhotoList();
    })
  
    $scope.$on('$destroy',function() {
      listenerFresh();
      listenerFresh = null;
    })
}])

//物流破损添加
.controller("logisticsDamagedAddCtrl",["$scope","$ionicActionSheet","$stateParams","logisticsDamaged","appMain","$cordovaCamera",
  "$cordovaImagePicker","$ionicLoading","constants","$ionicPopup","$cordovaFileTransfer","$rootScope",
 function($scope,$ionicActionSheet,$stateParams,logisticsDamaged,appMain,$cordovaCamera,$cordovaImagePicker,
  $ionicLoading,constants,$ionicPopup,$cordovaFileTransfer,$rootScope){
  $scope.imgList = []
   $scope.addLogisticsDamaged = {
    projectInfoId:$stateParams.id,
    projectInfoName:$stateParams.name,
    createUserId:$scope.userInfo.id,
    createUser:$scope.userInfo.name,
    boxSingle:"",//	箱单ID
    boxSingleName:"",//		箱单号
    damageDescription:"",//		破损说明
    registDate:appMain.getDateStr(0,'YY-MM-DD'),//		破损记录时间
    
  }
  $scope.getBoxBillInfo =  function() {
    $scope.showLoad();
    logisticsDamaged.getBoxBillInfo($stateParams.boxSingleId).then(function(data) {
      $scope.hideLoad();
      if(data.code==1) {
        $scope.addLogisticsDamaged =  angular.extend($scope.addLogisticsDamaged, data.data);
      } else {
        $scope.showToast(data.msg);
      }
    }) 
  }

  $scope.submit = function() {
    $scope.showLoad();
    $scope.addLogisticsDamaged.damagePic = angular.toJson($scope.imgList)
    logisticsDamaged.add($scope.addLogisticsDamaged).then(function(data) {
      $scope.hideLoad();
       if(data.code==1) {
        $rootScope.$emit('logisticsDamaged');
        $scope.toClose();
       }
       $scope.showToast(data.msg);
    }) 
  }
 
  $scope.getBoxBillInfo();

  $scope.removeImg = function(img) {
    $scope.imgList.splice($scope.imgList.indexOf(img),1);
  }

  $scope.selectPhotoType = function () {
    $ionicActionSheet.show({
      cssClass: "share-popu",
      buttons:[{ text: '拍照' }, { text: '从手机相册选择' }],
      cancelText: '取消',
      buttonClicked: function(index) {
        if(index == 0){
          $scope.takePhoto();  
        }else if(index == 1){
          $scope.pickImage();
        }
        return true;
      }
    });
  }
   /**
   * 从相机上传照片
   */
  $scope.takePhoto = function(){
    var options = {
      destinationType : Camera.DestinationType.FILE_URI,
      sourceType : Camera.PictureSourceType.CAMERA,
      correctOrientation:true
    };
    $cordovaCamera.getPicture(options).then(function(imageURL){
      upload(imageURL);
    },function (error){
       $scope.showToast("拍照失败");
    })
  }

  /**
   * 从文件选择照片
   */
  $scope.pickImage = function () {
      var options = {
          maximumImagesCount: 1,
          width: 800,
          height: 800,
          quality: 50
      };
      $cordovaImagePicker.getPictures(options).then(function (results) {
        for(var i = 0; i<results.length; i++) {
          //判断正反面添加图片
         upload(results[i]);
        }
      }, function (error) {
          $scope.showToast("选择失败");
      });
  }

  //上传图片
  function upload(picUrl){
    $ionicLoading.show({
      template: '<i class="upload"><ion-spinner icon="android"></ion-spinner>上传图片中...</i>'
    });
    var uri = encodeURI(constants.getRoot().uploadFile);
      var options = new FileUploadOptions();
      options.fileKey = "fileName";
      options.fileName = picUrl.substr(picUrl.lastIndexOf('/')+1);
      options.mimeType = "text/plain";
      var  headers = {'headerParam':'headerValue','equipNo' : window.localStorage['UUID'] || ''}; //ab5f6c786342d2cf
      options.headers = headers;
      $cordovaFileTransfer.upload(uri,picUrl,options).then(function(o){
      $ionicLoading.hide();
        var n = JSON.parse(o.response);
        if(n.attList && n.attList.length>0) {
          $scope.imgList.push({
            "oldFileName":n.attList[0].oldFileName,
            "newFileName":n.attList[0].newFileName
          })
        }
      },function(o){
        $ionicLoading.hide(), $ionicPopup.show({
        template: "图片上传失败,是否重试？",
        title: "提示信息",
        buttons: [{
          text: "取消"
        }, {
          text: "<b>重试</b>",
          type: "button-positive",
          onTap: function(e) {
            $ionicLoading.hide();
            upload(picUrl);
          }
        }]
      })
    })
  }
}])

//物流破损详情
.controller("logisticsDamagedDesCtrl",["$scope","appMain",function($scope,appMain){
    $scope.logisticsDamagedDes = appMain.getSession('logisticsDamagedDes');
}])


//物流二维码
.controller("logisticsCodeCtrl",["$scope","$ionicActionSheet","$stateParams","logisticsDamaged","appMain","$cordovaCamera",
  "$cordovaImagePicker","$ionicLoading","constants","$ionicPopup","$cordovaFileTransfer","$rootScope",
 function($scope,$ionicActionSheet,$stateParams,logisticsDamaged,appMain,$cordovaCamera,$cordovaImagePicker,
  $ionicLoading,constants,$ionicPopup,$cordovaFileTransfer,$rootScope){
  $scope.imgList = []
   $scope.logisticsCode = {
    projectInfoId:$stateParams.id,
    projectInfoName:$stateParams.name,
    createUserId:$scope.userInfo.id,
    createUser:$scope.userInfo.name,
    boxSingle:"",//	箱单ID
    boxSingleName:"",//		箱单号
    damageDescription:"",//		破损说明
    registDate:appMain.getDateStr(0,'YY-MM-DD'),//		破损记录时间
    remark:'',
    rstorageWay:'',  //存储方式
    rstoreageAddress:'' //存放地点

  }
  $scope.getBoxBillInfo =  function() {
    $scope.showLoad();
    logisticsDamaged.logisticsDamagedBoxId($stateParams.boxSingleId).then(function(data) {
      $scope.hideLoad();
      if(data.code==1) {
        $scope.logisticsCode =  angular.extend($scope.logisticsCode, data.data);
        $scope.logisticsCode.createUserId=$scope.userInfo.id,
        $scope.logisticsCode.createUser=$scope.userInfo.name
      } else {
        $scope.showToast(data.msg);
      }
    }) 
  }

  $scope.submit = function(state) {
    $scope.showLoad();
    $scope.logisticsCode.photo = angular.toJson($scope.imgList)
    logisticsDamaged.addLogistics(state,$scope.logisticsCode).then(function(data) {
      $scope.hideLoad();
       if(data.code==1) {
   
        $scope.toClose();
       }
       $scope.showToast(data.msg);
    }) 
  }
 
  $scope.getBoxBillInfo();

  $scope.removeImg = function(img) {
    $scope.imgList.splice($scope.imgList.indexOf(img),1);
  }

  $scope.selectPhotoType = function () {
    $ionicActionSheet.show({
      cssClass: "share-popu",
      buttons:[{ text: '拍照' }, { text: '从手机相册选择' }],
      cancelText: '取消',
      buttonClicked: function(index) {
        if(index == 0){
          $scope.takePhoto();  
        }else if(index == 1){
          $scope.pickImage();
        }
        return true;
      }
    });
  }
   /**
   * 从相机上传照片
   */
  $scope.takePhoto = function(){
    var options = {
      destinationType : Camera.DestinationType.FILE_URI,
      sourceType : Camera.PictureSourceType.CAMERA,
      correctOrientation:true
    };
    $cordovaCamera.getPicture(options).then(function(imageURL){
      upload(imageURL);
    },function (error){
       $scope.showToast("拍照失败");
    })
  }

  /**
   * 从文件选择照片
   */
  $scope.pickImage = function () {
      var options = {
          maximumImagesCount: 1,
          width: 800,
          height: 800,
          quality: 50
      };
      $cordovaImagePicker.getPictures(options).then(function (results) {
        for(var i = 0; i<results.length; i++) {
          //判断正反面添加图片
         upload(results[i]);
        }
      }, function (error) {
          $scope.showToast("选择失败");
      });
  }

  //上传图片
  function upload(picUrl){
    $ionicLoading.show({
      template: '<i class="upload"><ion-spinner icon="android"></ion-spinner>上传图片中...</i>'
    });
    var uri = encodeURI(constants.getRoot().uploadFile);
      var options = new FileUploadOptions();
      options.fileKey = "fileName";
      options.fileName = picUrl.substr(picUrl.lastIndexOf('/')+1);
      options.mimeType = "text/plain";
      var  headers = {'headerParam':'headerValue','equipNo' : window.localStorage['UUID'] || ''}; //ab5f6c786342d2cf
      options.headers = headers;
      $cordovaFileTransfer.upload(uri,picUrl,options).then(function(o){
      $ionicLoading.hide();
        var n = JSON.parse(o.response);
        if(n.attList && n.attList.length>0) {
          $scope.imgList.push({
            "oldFileName":n.attList[0].oldFileName,
            "newFileName":n.attList[0].newFileName
          })
        }
      },function(o){
        $ionicLoading.hide(), $ionicPopup.show({
        template: "图片上传失败,是否重试？",
        title: "提示信息",
        buttons: [{
          text: "取消"
        }, {
          text: "<b>重试</b>",
          type: "button-positive",
          onTap: function(e) {
            $ionicLoading.hide();
            upload(picUrl);
          }
        }]
      })
    })
  }
}])

 //三类人员登记控制台
.controller("threeCategoryRegisterInfoCtrl",["$scope","$state","$stateParams", "threecategoryregister",
  function($scope, $state, $stateParams,threecategoryregister){
  console.log($stateParams)
  
  $scope.getInfo= function() {
    threecategoryregister.getInfo($stateParams.id).then(function(data) {
      if(data.code==-1) {
        $scope.showToast(data.msg);
        return;
      }
      $scope.info = data.info;
    },function(err) {
      $scope.showError();
    })
  }
  $scope.getInfo();
}])

 //施工机具/仪表登记
.controller("constructionEquipmentRegisterInfoCtrl",["$scope","$state","$stateParams", "constructionEquipmentRegister",
  function($scope, $state, $stateParams,constructionEquipmentRegister){
  $scope.getInfo= function() {
    constructionEquipmentRegister.getInfo($stateParams.id).then(function(data) {
      if(data.code==-1) {
        $scope.showToast(data.msg);
        return;
      }
      $scope.info = data.info;
    },function(err) {
      $scope.showError();
    })
  }
  $scope.getInfo();
}])

//主要管理人员登记
.controller("keyManagePersonRegisterInfoCtrl",["$scope","$state","$stateParams", "keyManagePersonRegister",
  function($scope, $state, $stateParams,keyManagePersonRegister){ 
  $scope.getInfo= function() {
    keyManagePersonRegister.getInfo($stateParams.id).then(function(data) {
      if(data.code==-1) {
        $scope.showToast(data.msg);
        return;
      }
      $scope.info = data.info;
    },function(err) {
      $scope.showError();
    })
  }
  $scope.getInfo();
}])

//特种作业人员登记
.controller("specialOperatorsRegisterInfoCtrl",["$scope","$state","$stateParams", "specialOperatorsRegister",
  function($scope, $state, $stateParams,specialOperatorsRegister){
  $scope.getInfo= function() {
    specialOperatorsRegister.getInfo($stateParams.id).then(function(data) {
      if(data.code==-1) {
        $scope.showToast(data.msg);
        return;
      }
      $scope.info = data.info;
    },function(err) {
      $scope.showError();
    })
  }
  $scope.getInfo();
}])
.directive('actualSrc', function () {
  return{
    link: function postLink(scope, element, attrs) {
      attrs.$observe('actualSrc', function(newVal, oldVal){
        element.attr("src","data:image/gif;base64,iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAYAAAAfFcSJAAAADUlEQVQImWNgYGBgAAAABQABh6FO1AAAAABJRU5ErkJggg==");
        if(newVal){
          img = new Image();
          newVal = newVal.replace("8090",80);
          img.src = newVal;
          angular.element(img).bind('load', function () {
            element.attr("src", newVal);
          });
          
          angular.element(img).bind('error', function() {
            if(attrs.error) {
              element.attr('src', attrs.error);	
            } else {
              element.attr('src',"img/head.png");
            }
            
          })
        }
      });

    }
  }
})