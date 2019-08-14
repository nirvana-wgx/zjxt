
angular.module("ionicApp.controllers", [])
//主页控制台
.controller("appMain",["$state", "$rootScope", "$ionicHistory","$scope",
function($state, $rootScope,$ionicHistory,$scope) {
  $scope.toClose = function() {
    if ($state.current.name.indexOf("siteManage") > -1 ) {
        var sendData={
          actionType:rnConfig.closeWindow,
          info:{}
        };
        if(window.WebViewBridge) {
            window.WebViewBridge.send(JSON.stringify(sendData));
        };
    
      } else {
        $ionicHistory.backView() ? ($rootScope.isBack = !0, $ionicHistory.goBack()) : (console.log("返回故障---"), $state.go("siteManage"));
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

//现场管理
.controller("siteManageCtrl",["$scope","home","$state","$rootScope","appMain",
    function($scope,home,$state,$rootScope,appMain){
    $scope.type = '';
    $scope.menuList = [];
    
    $scope.getHomeData = function() {
        home.getHomeData().then(function(data) {
        $scope.menuList = data.siteList;
 
        }) 
    }

    $scope.getAccount = function() {
        home.getAccount().then(function(data) {
            if(data.code==1) {
                $rootScope.userInfo = data.userInfo;
                appMain.setSession({
                key:"userInfo",
                data:data.userInfo
                })
                $scope.getHomeData();
            } else {
            $scope.showToast(data.msg);
            }
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
      //doQRScanCallback('a8d40115-e4ab-4322-90d7-9ff7ef04c977');
    };

    window.doQRScanCallback = function(str) {
    
    $state.go("doc-upload",{
      id:str
    })
  } 
  $scope.getAccount();
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
            data.list[i].scenePhoto = data.list[i].scenePhoto.split(',');
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
            data.list[i].scenePhoto = data.list[i].scenePhoto.split(',');
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
.controller("scenePhotoAddCtrl",["$scope", "$stateParams","scenePhoto","$rootScope","$timeout","constants", 
  function($scope, $stateParams,scenePhoto, $rootScope,$timeout,constants){
    $scope.imgList = []
    $scope.addPhoto = {
      projectInfoId:$stateParams.id,
      projectInfoName:$stateParams.name,
      photoCategory:'',
      explain:'',
      scenePhoto:angular.toJson($scope.imgList),
      createUserId:$scope.userInfo.id,
      createUser:$scope.userInfo.name,
      subProject:'',
      subProjectName:''
    }
 
  $scope.removeImg = function(img) {
    $scope.imgList.splice($scope.imgList.indexOf(img),1);
  }
  $scope.select = {
    photocategor:'',
    subProject:''
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

   $scope.getVersionList = function() {
    scenePhoto.getVersionList($stateParams.id).then(function(data) {
      $scope.subProjectList = data.list;
      if($scope.subProjectList.length>0) {
        $scope.select.subProject = $scope.subProjectList[0];
      }
    }) 
   }

  $scope.getPhotoCategory();
  $scope.getVersionList();
  $scope.selectPhotoType = function () {
    var sendData={
      actionType:rnConfig.selectImage,
      info:{
        url:constants.getRoot().uploadFile,
        multipleImage:true
      }
    };
    
    if(window.WebViewBridge) {
      window.WebViewBridge.send(JSON.stringify(sendData));
    };
   // var time = new Date().getTime()
    //selectImageCallback("images/add-img-icon.png?"+ time); 
  }

  window.selectImageCallback = function(str) {
    $timeout(function(){
      var strList = str.split(",");
      for(var i =0 ; i<strList.length;i++) {
      var imgArr = strList[i].split("_");
      var imgStr  = imgArr[0]+'_'+imgArr[1];
      $scope.imgList.push(imgStr);
      }
    },300);
  }
   

  $scope.submit = function() {
    if($scope.select.photocategor) {
      $scope.addPhoto.photoCategory = $scope.select.photocategor.code;
    }
    if($scope.select.subProject) {
      $scope.addPhoto.subProject = $scope.select.subProject.id;
      $scope.addPhoto.subProjectName = $scope.select.subProject.name;
    }
    $scope.addPhoto.scenePhoto = $scope.imgList.join();
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
.controller("docUploadCtrl",["$scope", "$stateParams","doc","$timeout","constants",
  function($scope, $stateParams,doc,$timeout,constants){
    $scope.imgList = []
    $scope.otherImgList = [];
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
   
      doc.addDocImg($stateParams.id,  $scope.imgList.join()).then(function(data) {
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
          url:constants.getRoot().uploadFile,
          multipleImage:true
        }
      };
      if(window.WebViewBridge) {
        window.WebViewBridge.send(JSON.stringify(sendData));
      };
      // var time = new Date().getTime()
      // selectImageCallback("images/add-img-icon.png?"+ time); 
    }

    window.selectImageCallback = function(str) {
      $timeout(function(){
        var strList = str.split(",");
        for(var i =0 ; i<strList.length;i++) {
        var imgArr = strList[i].split("_");
        var imgStr  = imgArr[0]+'_'+imgArr[1];
        $scope.imgList.push(imgStr);
        }
      },300);
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
      doc.getDocTree($stateParams.id,$scope.userInfo.id).then(function(data) {
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

//文档预览详情
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
          $scope.imgList = data.data.split(",");
        }
        
      } else {
        $scope.showToast(data.msg);
      }
      $scope.hideLoad();
    }) 
  }
  $scope.uploadDocDetail();
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
            data.list[i].attach = data.list[i].attach.split(',');
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
            data.list[i].attach = data.list[i].attach.split(',');
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
.controller("projectScheduleAddCtrl",["$scope","$stateParams","projectSchedule","$rootScope","$timeout","constants",
 function($scope,$stateParams,projectSchedule ,$rootScope,$timeout,constants){
  $scope.imgList = [ ]
  $scope.addProjectSchedule = {
    orgId:$scope.userInfo.groupId,
    companyId:'a1b10168-61a9-44b5-92ca-c5659456deb5',
    flowPhase:'Start',
    state:"Unconfirm",
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
    name:"",   //作业ID
    nameName:""  //作业名称
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
    $scope.addProjectSchedule.process = $scope.select.process.id;	//作业ID
    $scope.addProjectSchedule.name = $scope.select.process.id;	//作业ID
    $scope.addProjectSchedule.nameName = $scope.select.process.name;	//作业ID
    $scope.addProjectSchedule.processName=$scope.select.process.name;	//作业名称
    $scope.addProjectSchedule.subProjectName=$scope.select.subWork.name;//子项目名称
    $scope.addProjectSchedule.subSectionName=$scope.select.subWork.name;//分部工程名称
    $scope.addProjectSchedule.attach = $scope.imgList.join();
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
    var sendData={
      actionType:rnConfig.selectImage,
      info:{
        url:constants.getRoot().uploadFile,
        multipleImage:true
      }
    };
    if(window.WebViewBridge) {
      window.WebViewBridge.send(JSON.stringify(sendData));
    };
    //  var time = new Date().getTime()
    //  selectImageCallback("images/add-img-icon.png?"+ time); 
  }

  window.selectImageCallback = function(str) {
    $timeout(function(){
      var strList = str.split(",");
      for(var i =0 ; i<strList.length;i++) {
      var imgArr = strList[i].split("_");
      var imgStr  = imgArr[0]+'_'+imgArr[1];
      $scope.imgList.push(imgStr);
      }
    },300);
  }
}])

//施工进度反馈详情
.controller("projectScheduleDesCtrl",["$scope","appMain",function($scope,appMain){
  $scope.projectScheduleDes = appMain.getSession('projectScheduleDes');
   
}])

 
 