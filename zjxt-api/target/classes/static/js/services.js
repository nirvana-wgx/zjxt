angular.module("ionicApp.services", []).factory("root", function() {
  return null;
}).factory("appMain", ["constants", "$http", "$q", function(constants, $http, $q ) {
  return {
    setSession: function(e) {
      e.data && (e.data = JSON.stringify(e.data)), window.sessionStorage[e.key] = e.data
    },
    getSession: function(e) {
      var http = window.sessionStorage[e];
      return http ? JSON.parse(http) : null
    },
    setLocal: function(e) {
      e.data && (e.data = JSON.stringify(e.data)), window.localStorage[e.key] = e.data
    },
    getLocal: function(e) {
      var http = window.localStorage[e];
      return http ? JSON.parse(http) : null
    },
    trim:function(str) {
      return str.replace(/(\s*$)/g,"");
    },
    //获取时间
    getDateStr:function(dayCount, format, currentDate) {
        var dd = new Date();
        if(currentDate) {
          dd = new Date(currentDate);
        }
        dd.setDate(dd.getDate()+dayCount);
        var y = dd.getFullYear();
        var m = dd.getMonth()+1;
        var d = dd.getDate();
        var h = dd.getHours();
        var i = dd.getMinutes();
        if(m<10) {
          m = "0"+m;
        }
        if(d<10) {
          d = "0"+d;
        }
        if(h<10) {
          h = "0"+h;
        }
        if(i<10) {
          i = "0"+i;
        }
        if(format.indexOf("YY")>-1) {
          format= format.replace("YY", y);
        }

        if(format.indexOf("MM")>-1) {
          format=format.replace("MM", m);
        }

        if(format.indexOf("DD")>-1) {
          format=format.replace("DD", d);
        }

        if(format.indexOf("HH")>-1) {
          format=format.replace("HH", h);
        }
        if(format.indexOf("II")>-1) {
          format=format.replace("II", i);
        }
        return format;
    }
     
  }
}])
.factory("verify", function() {
  return {
    /**
     * 数据格式化
     * @param  string 传入当前数据
     * @param string price,tons
     */
    clearNoNum:function(value,str){
        if(!value) {
          return;
        }
        value = value.toString(); 
        value = value.replace(/[^\d.]/g,"");  //清除“数字”和“.”以外的字符  
        value = value.replace(/\.{2,}/g,"."); //只保留第一个. 清除多余的  
        value = value.replace(".","$#$").replace(/\./g,"").replace("$#$",".");
        if(str!=="") {
          if(str=="price") {
            var decimalRe = /^(\-)*(\d+)\.(\d{2}).*$/;
            value = value.replace(decimalRe,'$1$2.$3');//只能输入两个小数
          } else if(str=="tons") {
            var decimalRe = /^(\-)*(\d+)\.(\d{4}).*$/;
            value = value.replace("kg",'$1$2.$3');//只能输入4个小数
          } else if(str=="kg") {
            var decimalRe = /^(\-)*(\d+)\.(\d{3}).*$/;
            value = value.replace("kg",'$1$2.$3');//只能输入3个小数
          }else {
            var decimalRe = /^(\-)*(\d+)*$/;
            value = value.replace(decimalRe,'$1$2');//不能输入小数
          }
        }
        if(value.indexOf(".")< 0 && value !=""){//以上已经过滤，此处控制的是如果没有小数点，首位不能为类似于 01、02的金额 
            value= parseFloat(value); 
        } 
        return value;
    },
    
    clearNoNumalphabet:function(value) {
      if(!value) {
         return;
     }
     var reg=/[\u4E00-\u9FA5]/g;
     value=value.replace(reg,'');
     value=value.replace(/[&\|\\\*^%$#@\-/:，,。、？?]/g,"");
     return value;
     },
    phone:function(str) {
      var myreg =/^1[3|4|5|7|8][0-9]{9}$/; 
      if(myreg.test(str)) {
        return true;
      }
      return false;
    },
    trim:function(str) {
      return str.replace(/(\s*$)/g,"");
    },
    empty:function(str) {
      if(str==undefined  || this.trim(str)=="") {
        return true;
      }
      return false;
    } 
  }
})
.factory('Interceptor', ["$q","$injector",function($q,$injector) {
  return {
    request: function(config) {
      config.headers = config.headers || {};
      config.headers['Content-Type'] = 'application/x-www-form-urlencoded; charset=UTF-8';
      return config;
    },
    requestError: function(rejection) {
      return $q.reject(rejection);
    },
    response: function(response) {
      if(response.data && response.data.code==0) {
        window.localStorage["token"] = response.data.token;
      }
      if(response.data && response.data.code==2) {
          state = $injector.get('$state'); 
          state.go("login");
      }
      return response;
    },
    responseError: function(response) {
      var rootScope = $injector.get('$rootScope'); 
      if(response.status==0 || response.status==404) {
        rootScope.showToast("网络错误，请稍后再试。");
      } else if(response.status==500 && response.data.code==-1) {
        state = $injector.get('$state'); 
        state.go("login");
      } else {
        rootScope.showToast("服务器错误,错误代码:"+response.status);
      }
      rootScope.hideLoad();  
      return $q.reject(response);
    }
  }
}])
.config(["$httpProvider", function($httpProvider) {
  $httpProvider.interceptors.push('Interceptor');
}])
.factory("constants", ["$http", "$q", function($http, $q) {
  return {
    getRoot: function() {
      var t = {};
      var url = '/xtm-api';
      //用户信息
      var userServices = url+"/user";
      //项目信息
      var projectServices = url+"/project";
      //现场照片类型
      var scenePhotoServices = url+"/scenePhoto";
      //文档服务
      var docServices = url+"/doc";
      //采购进度反馈
      var procurementProgress = url+"/procurementProgress";
      //监控日志
      var  monitorLog = url+"/monitorLog";
      //施工进度反馈
      var constructionProgress = url+"/constructionProgress";
      //破损记录
      var logisticsDamaged  = url+"/logisticsDamaged";

      var scanServices = url +'/scan';
      

      var uploadFile = url +"/uploadFile";
    
      //用户登录
      t.login = userServices +"/login";
      //获取用户信息 
      t.getUserInfo = userServices +"/getUserInfo";
      t.getAccount = userServices +"/getAccount";
      //获取项目列表
      t.projectList = projectServices +"/getPojectList";
      //获取现场拍照类型
      t.photoCategory  = scenePhotoServices +"/getPhotoCategory";
      //添加现场拍照
      t.addScenePhoto = scenePhotoServices +"/add";
      //获取现场拍照列表
      t.scenePhotoList = scenePhotoServices +"/list";
      //获取现场拍照子项
      t.sceneVersionList= scenePhotoServices +"/getVersionList";
      
      //获取文档目录
      t.getDocTree =  docServices +"/getTree";
      //获取文档列表
      t.getFolderIdList =  docServices +"/getFolderIdList";
      //文档信息
      t.uploadDocDetail =  docServices +"/uploadDocDetail";
      //添加文档图片
      t.addDocImg =  docServices +"/addDocImg";
      t.getDocImgByDocId = docServices +"/getDocImgByDocId";

      //获取采购进度列表
      t.getProcurementProgressList =  procurementProgress +"/getProcurementProgressList";
      //获取采购进度合同列表
      t.procurementContractList = procurementProgress +"/getContractList";
      //获取采购进度设备列表
      t.procurementEquipmentList = procurementProgress +"/getEquipmentList";
       //获取采购进度步骤列表
      t.procurementStepList = procurementProgress +"/getStepList";
      //添加采购进度数据
      t.procurementAdd = procurementProgress +"/add";
     
      //获取监控日志列表
      t.monitorLogList = monitorLog +"/getMonitorLog?"; 
      //获取监控日志合同列表
      t.monitorLogContractList = monitorLog +"/getContractList?";
      //获取监控日志设备列表
      t.monitorLogEquipmentList = monitorLog +"/getEquipmentList?";
      //获取天气信息
      t.monitorLogWeatherList = monitorLog +"/getWeatherList?";
      //监控日志添加
      t.monitorAdd = monitorLog +"/add?";

      //获取施工进度列表
      t.constructionProgressList =  constructionProgress +"/getConstructionProgressList";
      //获取施工进度子项目列表
      t.constructionSubProjectList = constructionProgress +"/getSubProjectList";
      //获取施工进分部工程列表
      t.constructionSubWorkList = constructionProgress +"/getSubWorkList";
      //获取采购进度施工作业列表
      t.constructionProcessList = constructionProgress +"/getProcessList";
      //获取采购进度施工作业添加
      t.constructionAdd = constructionProgress +"/add";

      //获取物流破损列表
      t.logisticsDamagedList = logisticsDamaged +"/getLogisticsDamagedList";
      //根据单号获取物流信息
      t.logisticsDamagedBoxBillInfo = logisticsDamaged +"/getBoxBillInfo";
      //获取物流破损添加
      t.logisticsDamagedAdd = logisticsDamaged +"/add"; 
      //物流二维码
      t.logisticsDamagedBoxId = logisticsDamaged +"/getLogisticsInfoByBoxSingleId?"; 
      t.logisticsBoxBillNum = logisticsDamaged +"/getBoxBillNum?"; 
      t.logisticsDamaged = logisticsDamaged;

     
      t.getStaff = scanServices+"/getStaff";
      t.getStaffHead = scanServices+"/getStaffHead";
      t.getKey = scanServices+"/getKey";     //主要管理人员登记
      t.getConstruction = scanServices+"/getConstruction";  //施工机具/仪表登记
      t.getThree = scanServices+"/getThree";  //三类人员登记
      t.getSpecial = scanServices+"/getSpecial";  //特种作业人员登记
      t.getNotice = scanServices+"/getNotice";//质量方针

      //t.uploadFile = 'http://192.168.0.79:8091/api/Files/0/UploadAttachmentWithSnap';
       t.uploadFile = 'http://pmip.cie-cn.com:8082/api/Files/0/UploadAttachmentWithSnap';
    // t.downloadImg = 'http://192.168.0.79:8091/Files/';
     t.downloadImg = 'http://pmip.cie-cn.com:8082/Files/';
      return t;
    },
    serializeData: function ( data ) { 
        // If this is not an object, defer to native stringification.
        if ( ! angular.isObject( data ) ) { 
            return( ( data == null ) ? "" : data.toString() ); 
        }            
        var buffer = [];            
        // Serialize each key in the object.
        for ( var name in data ) { 
            if ( ! data.hasOwnProperty( name ) ) { 
                continue; 
            }            
            var value = data[ name ];            
            buffer.push(
                encodeURIComponent( name ) + "=" + encodeURIComponent( ( value == null ) ? "" : value )
            ); 
        }            
        // Serialize the buffer and clean it up for transportation.
        var source = buffer.join( "&" ).replace( /%20/g, "+" ); 
        return( source ); 
    }
  }
}])//主页服务
.factory("home",["constants", "$http", "$q", function(constants, $http, $q ) {
  return {
    //获取菜单
    getHomeData: function() {
       var defer = $q.defer();
      $http({
          method:'get',
          url:"data/menu.json",
      }).success(function(data){
          defer.resolve(data);
      }).error(function(data){
          defer.reject(data)
      });
      return defer.promise;
    },getLogin: function(loginName,password) {
      var params = {
        "loginName":loginName,
        "password":SHA1(password)
      }
      var defer = $q.defer();
      $http({
          method:'post',
          url:constants.getRoot().login,
          data: constants.serializeData(params),
          timeout: 15e3
      }).success(function(data,status,headers,config){
          defer.resolve(data);
      }).error(function(data,status,headers,config){
          defer.reject(data)
      });
      return defer.promise;
    },getUserInfo:function() {
      var params = {
        "userId":userId
      }
      var defer = $q.defer();
      $http({
          method:'post',
          url:constants.getRoot().getUserInfo,
          data: constants.serializeData(params),
          timeout: 15e3
      }).success(function(data,status,headers,config){
          defer.resolve(data);
      }).error(function(data,status,headers,config){
          defer.reject(data)
      });
      return defer.promise;
    },getAccount:function() {
      var params = {
        "account":account
      }
      var defer = $q.defer();
      $http({
          method:'post',
          url:constants.getRoot().getAccount,
          data: constants.serializeData(params),
          timeout: 15e3
      }).success(function(data ){
          defer.resolve(data);
      }).error(function(data ){
          defer.reject(data)
      });
      return defer.promise;
    }
  }
}]).factory("project",["constants", "$http", "$q", function(constants, $http, $q ) {
  return {
    getPojectList: function(type,userId,PrjId,pageNum,count) {
      var params = {
        type:type,
        userId:userId,
        PrjId:PrjId,
        pageNum:pageNum,
        count:count
      }
      var defer = $q.defer();
      $http({
          method:'post',
          url:constants.getRoot().projectList,
          data: constants.serializeData(params),
          timeout: 15e3
      }).success(function(data,status,headers,config){
          defer.resolve(data);
      }).error(function(data,status,headers,config){
          defer.reject(data)
      });
      return defer.promise;
    }
  }
}])
.factory("scenePhoto",["constants", "$http", "$q", function(constants, $http, $q ) {
  return {
    getPhotoCategory: function( ) {
      var defer = $q.defer();
      $http({
          method:'post',
          url:constants.getRoot().photoCategory,
          timeout: 15e3
      }).success(function(data,status,headers,config){
          defer.resolve(data);
      }).error(function(data,status,headers,config){
          defer.reject(data)
      });
      return defer.promise;
    },
    addScenePhoto : function(params) {
      var defer = $q.defer();
      $http({
          method:'post',
          url:constants.getRoot().addScenePhoto,
          data: constants.serializeData(params),
          timeout: 15e3
      }).success(function(data,status,headers,config){
          defer.resolve(data);
      }).error(function(data,status,headers,config){
          defer.reject(data)
      });
      return defer.promise;
    },
    getList : function(userId,projectInfoId,pageNum,count) {
      var params = {
        userId:userId,
        projectInfoId:projectInfoId,
        pageNum:pageNum,
        count:count
      }
      var defer = $q.defer();
      $http({
        method:'post',
        url:constants.getRoot().scenePhotoList,
        data: constants.serializeData(params),
        timeout: 15e3
      }).success(function(data,status,headers,config){
          defer.resolve(data);
      }).error(function(data,status,headers,config){
          defer.reject(data)
      });
      return defer.promise;
    },

    getVersionList: function(projectInfoId) {
      var params = {
        projectInfoId:projectInfoId
         
      }
      var defer = $q.defer();
      $http({
        method:'post',
        url:constants.getRoot().sceneVersionList,
        data: constants.serializeData(params),
        timeout: 15e3
      }).success(function(data ){
          defer.resolve(data);
      }).error(function(data){
          defer.reject(data)
      });
      return defer.promise;
    }
  }
}])
//文档服务
.factory("doc",["constants", "$http", "$q", function(constants, $http, $q ) {
  return {
    getDocTree: function(projectId,code) {
      var defer = $q.defer();
      var params = {
        projectId:projectId,
        userId:code
      }
      $http({
          method:'post',
          url:constants.getRoot().getDocTree,
          data: constants.serializeData(params),
          timeout: 15e3
      }).success(function(data,status,headers,config){
          defer.resolve(data);
      }).error(function(data,status,headers,config){
          defer.reject(data)
      });
      return defer.promise;
    },
    getFolderIdList : function(folderId) {
      var defer = $q.defer();
      var params = {
        folderId:folderId
      }
      $http({
          method:'post',
          url:constants.getRoot().getFolderIdList,
          data: constants.serializeData(params),
          timeout: 15e3
      }).success(function(data,status,headers,config){
          defer.resolve(data);
      }).error(function(data,status,headers,config){
          defer.reject(data)
      });
      return defer.promise;
    },
    uploadDocDetail : function(docId) {
      var params = {
        docId:docId
      }
      var defer = $q.defer();
      $http({
        method:'post',
        url:constants.getRoot().uploadDocDetail,
        data: constants.serializeData(params),
        timeout: 15e3
      }).success(function(data,status,headers,config){
          defer.resolve(data);
      }).error(function(data,status,headers,config){
          defer.reject(data)
      });
      return defer.promise;
    },

    getDocImgByDocId: function(docId) {
      var params = {
        docId:docId
      }
      var defer = $q.defer();
      $http({
        method:'post',
        url:constants.getRoot().getDocImgByDocId,
        data: constants.serializeData(params),
        timeout: 15e3
      }).success(function(data,status,headers,config){
          defer.resolve(data);
      }).error(function(data,status,headers,config){
          defer.reject(data)
      });
      return defer.promise;
    },
    addDocImg : function(docId,otherFiel) {
      var params = {
        docId:docId,
        otherFiel:otherFiel
      }
      var defer = $q.defer();
      $http({
        method:'post',
        url:constants.getRoot().addDocImg,
        data: constants.serializeData(params),
        timeout: 15e3
      }).success(function(data,status,headers,config){
          defer.resolve(data);
      }).error(function(data,status,headers,config){
          defer.reject(data)
      });
      return defer.promise;
    }
  }
}])
//采购进度
.factory("purchaseSchedule",["constants", "$http", "$q", function(constants, $http, $q ) {
  return {
    
    procurementAdd: function( params) {
      var defer = $q.defer();
    
      $http({
          method:'post',
          data: constants.serializeData(params),
          url:constants.getRoot().procurementAdd,
          timeout: 15e3
      }).success(function(data,status,headers,config){
          defer.resolve(data);
      }).error(function(data,status,headers,config){
          defer.reject(data)
      });
      return defer.promise;
    },
    getContractList: function( projectInfoId) {
      var defer = $q.defer();
      var params = {
        projectInfoId:projectInfoId
      }
      $http({
          method:'post',
          data: constants.serializeData(params),
          url:constants.getRoot().procurementContractList,
          timeout: 15e3
      }).success(function(data,status,headers,config){
          defer.resolve(data);
      }).error(function(data,status,headers,config){
          defer.reject(data)
      });
      return defer.promise;
    },
    
    getEquipmentList: function( contractId) {
      var defer = $q.defer();
      var params = {
        contractId:contractId
      }
      $http({
          method:'post',
          data: constants.serializeData(params),
          url:constants.getRoot().procurementEquipmentList,
          timeout: 15e3
      }).success(function(data,status,headers,config){
          defer.resolve(data);
      }).error(function(data,status,headers,config){
          defer.reject(data)
      });
      return defer.promise;
    },
    getStepList: function( projectInfoId) {
      var defer = $q.defer();
      var params = {
        projectInfoId:projectInfoId
      }
      $http({
          method:'post',
          data: constants.serializeData(params),
          url:constants.getRoot().procurementStepList,
          timeout: 15e3
      }).success(function(data,status,headers,config){
          defer.resolve(data);
      }).error(function(data,status,headers,config){
          defer.reject(data)
      });
      return defer.promise;
    },
    getList : function(userId,projectInfoId,pageNum,count) {
      var params = {
        userId:userId,
        projectInfoId:projectInfoId,
        pageNum:pageNum,
        count:count
      }
      var defer = $q.defer();
      $http({
        method:'post',
        url:constants.getRoot().getProcurementProgressList,
        data: constants.serializeData(params),
        timeout: 15e3
      }).success(function(data,status,headers,config){
          defer.resolve(data);
      }).error(function(data,status,headers,config){
          defer.reject(data)
      });
      return defer.promise;
    }
  }
}])

//监控日志
.factory("monitorLog",["constants", "$http", "$q", function(constants, $http, $q ) {
  return {
    
    monitorAdd: function( params) {
      var defer = $q.defer();
    
      $http({
          method:'post',
          data: constants.serializeData(params),
          url:constants.getRoot().monitorAdd,
          timeout: 15e3
      }).success(function(data,status,headers,config){
          defer.resolve(data);
      }).error(function(data,status,headers,config){
          defer.reject(data)
      });
      return defer.promise;
    },
    getContractList: function( projectInfoId) {
      var defer = $q.defer();
      var params = {
        projectInfoId:projectInfoId
      }
      $http({
          method:'post',
          data: constants.serializeData(params),
          url:constants.getRoot().monitorLogContractList,
          timeout: 15e3
      }).success(function(data,status,headers,config){
          defer.resolve(data);
      }).error(function(data,status,headers,config){
          defer.reject(data)
      });
      return defer.promise;
    },
    
    getEquipmentList: function( contractId) {
      var defer = $q.defer();
      var params = {
        contractId:contractId
      }
      $http({
          method:'post',
          data: constants.serializeData(params),
          url:constants.getRoot().monitorLogEquipmentList,
          timeout: 15e3
      }).success(function(data,status,headers,config){
          defer.resolve(data);
      }).error(function(data,status,headers,config){
          defer.reject(data)
      });
      return defer.promise;
    },
    
    getWeatherList: function() {
      var defer = $q.defer();
      $http({
        method:'post',
        url:constants.getRoot().monitorLogWeatherList,
        timeout: 15e3
      }).success(function(data){
          defer.resolve(data);
      }).error(function(data){
          defer.reject(data)
      });
      return defer.promise;
    },
   
    getList : function(userId,projectInfoId,pageNum,count) {
      var params = {
        userId:userId,
        projectInfoId:projectInfoId,
        pageNum:pageNum,
        count:count
      }
      var defer = $q.defer();
      $http({
        method:'post',
        url:constants.getRoot().monitorLogList,
        data: constants.serializeData(params),
        timeout: 15e3
      }).success(function(data,status,headers,config){
          defer.resolve(data);
      }).error(function(data,status,headers,config){
          defer.reject(data)
      });
      return defer.promise;
    }
  }
}])

//施工进度
.factory("projectSchedule",["constants", "$http", "$q", function(constants, $http, $q ) {
  return {
    add: function( params) {
      var defer = $q.defer();
    
      $http({
          method:'post',
          data: constants.serializeData(params),
          url:constants.getRoot().constructionAdd,
          timeout: 15e3
      }).success(function(data,status,headers,config){
          defer.resolve(data);
      }).error(function(data,status,headers,config){
          defer.reject(data)
      });
      return defer.promise;
    },
    
    getProcessList: function( projectInfoId,subWorkId) {
      var defer = $q.defer();
      var params = {
        projectInfoId:projectInfoId,
        subWorkId:subWorkId
      }
      $http({
          method:'post',
          data: constants.serializeData(params),
          url:constants.getRoot().constructionProcessList,
          timeout: 15e3
      }).success(function(data,status,headers,config){
          defer.resolve(data);
      }).error(function(data,status,headers,config){
          defer.reject(data)
      });
      return defer.promise;
    },
    getSubWorkList: function( projectInfoId,subProjectId) {
      var defer = $q.defer();
      var params = {
        projectInfoId:projectInfoId,
        subProjectId:subProjectId
      }
      $http({
          method:'post',
          data: constants.serializeData(params),
          url:constants.getRoot().constructionSubWorkList,
          timeout: 15e3
      }).success(function(data,status,headers,config){
          defer.resolve(data);
      }).error(function(data,status,headers,config){
          defer.reject(data)
      });
      return defer.promise;
    },
   
    getSubProjectList: function( projectInfoId) {
      var defer = $q.defer();
      var params = {
        projectInfoId:projectInfoId
      }
      $http({
          method:'post',
          data: constants.serializeData(params),
          url:constants.getRoot().constructionSubProjectList,
          timeout: 15e3
      }).success(function(data,status,headers,config){
          defer.resolve(data);
      }).error(function(data,status,headers,config){
          defer.reject(data)
      });
      return defer.promise;
    },
   
    getList : function(userId,projectInfoId,pageNum,count) {
      var params = {
        userId:userId,
        projectInfoId:projectInfoId,
        pageNum:pageNum,
        count:count
      }
      var defer = $q.defer();
      $http({
        method:'post',
        url:constants.getRoot().constructionProgressList,
        data: constants.serializeData(params),
        timeout: 15e3
      }).success(function(data,status,headers,config){
          defer.resolve(data);
      }).error(function(data,status,headers,config){
          defer.reject(data)
      });
      return defer.promise;
    }
  }
}])
 
 //物流破损记录
.factory("logisticsDamaged",["constants", "$http", "$q", function(constants, $http, $q ) {
  return {
    add: function( params) {
      var defer = $q.defer();
      $http({
          method:'post',
          data: constants.serializeData(params),
          url:constants.getRoot().logisticsDamagedAdd,
          timeout: 15e3
      }).success(function(data,status,headers,config){
          defer.resolve(data);
      }).error(function(data,status,headers,config){
          defer.reject(data)
      });
      return defer.promise;
    },
    getBoxBillInfo : function(boxSingle) {
      var params = {
        boxSingle:boxSingle
      }
      var defer = $q.defer();
      $http({
        method:'post',
        url:constants.getRoot().logisticsDamagedBoxBillInfo,
        data: constants.serializeData(params),
        timeout: 15e3
      }).success(function(data,status,headers,config){
          defer.resolve(data);
      }).error(function(data,status,headers,config){
          defer.reject(data)
      });
      return defer.promise;
    },
    getList : function(userId,projectInfoId,pageNum,count) {
      var params = {
        userId:userId,
        projectInfoId:projectInfoId,
        pageNum:pageNum,
        count:count
      }
      var defer = $q.defer();
      $http({
        method:'post',
        url:constants.getRoot().logisticsDamagedList,
        data: constants.serializeData(params),
        timeout: 15e3
      }).success(function(data,status,headers,config){
          defer.resolve(data);
      }).error(function(data,status,headers,config){
          defer.reject(data)
      });
      return defer.promise;
    },
    logisticsDamagedBoxId:function(boxSingle) {
      var params = {
        boxSingleId:boxSingle
      }
      var defer = $q.defer();
      $http({
        method:'post',
        url:constants.getRoot().logisticsDamagedBoxId,
        data: constants.serializeData(params),
        timeout: 15e3
      }).success(function(data,status,headers,config){
          defer.resolve(data);
      }).error(function(data,status,headers,config){
          defer.reject(data)
      });
      return defer.promise;
    },
    getBoxBillNum:function(boxSingleId) {
      var params = {
        boxSingleId:boxSingleId
      }
      var defer = $q.defer();
      $http({
        method:'post',
        url:constants.getRoot().logisticsBoxBillNum,
        data: constants.serializeData(params),
        timeout: 15e3
      }).success(function(data,status,headers,config){
          defer.resolve(data);
      }).error(function(data,status,headers,config){
          defer.reject(data)
      });
      return defer.promise;
    },
    addLogistics:function(state,param) {
      var url =  constants.getRoot().logisticsDamaged;
      var params = {
        boxSingle:param.boxSingle,
        boxSingleName:param.boxSingleName,
        sendBatch:param.sendBatch,
        sendBatchName:param.sendBatchName,
        mainMaterCode:param.mainMaterCode,
        mainMaterialNameCH:param.mainMaterialNameCH,
        mainMaterialNameEN:param.mainMaterialNameEN,
        remark:param.remark,
        packageStyle:param.packageStyle,
        createUserId:param.createUserId	,
        createUser:param.createUser,	 
        photo:param.photo,
        currentPosition:param.currentPosition	 
      }
      
      if(state==1 && state=="1.0") {
        params.installDate=param.registDate //装货时间
        url = url +'/addShipment?';
      }
      if(state==2 && state=="2.0") {
        params.discharge=param.registDate  //卸船时间
        url = url +'/addUnloading?';
      }
      if(state==3 && state=="3.0") {
        params.storageWay = param.storageWay	  //存储方式
        params.receiveDate 	= param.registDate//接收时间
        params.storeageAddress	 = param.storeageAddress //存放地点
        url = url +'/addSceneCollect?';
      }
      if(state==4 && state=="4.0") {
        params.openBoxDate = param.registDate  //开箱时间
        url = url +'/addOpenBoxAudit?';
      }
      
      console.log(params)
      var defer = $q.defer();
      $http({
        method:'post',
        url:url,
        data: constants.serializeData(params),
        timeout: 15e3
      }).success(function(data,status,headers,config){
          defer.resolve(data);
      }).error(function(data,status,headers,config){
          defer.reject(data)
      });
      return defer.promise;
    }
  }
}])

.factory("scan", ["constants", "$http", "$q", function(constants, $http, $q ) {
  return {
    getStaffHead:function(UserID) {
      var params = {
        "UserID":UserID 
      }
      var defer = $q.defer();
      $http({
          method:'post',
          url:constants.getRoot().getStaffHead,
          data: constants.serializeData(params),
          timeout: 15e3
      }).success(function(data){
          defer.resolve(data);
      }).error(function(data){
          defer.reject(data)
      });
      return defer.promise;
    },
    getStaff: function(UserID,RoleCode) {
      var params = {
        "UserID":UserID,
        "RoleCode":RoleCode 
      }
      var defer = $q.defer();
      $http({
          method:'post',
          url:constants.getRoot().getStaff,
          data: constants.serializeData(params),
          timeout: 15e3
      }).success(function(data){
          defer.resolve(data);
      }).error(function(data){
          defer.reject(data)
      });
      return defer.promise;
    },
    getNotice: function(paramId) {
        var params = {
          "paramId":paramId,
        }
        var defer = $q.defer();
        $http({
            method:'post',
            url:constants.getRoot().getNotice,
            data: constants.serializeData(params),
            timeout: 15e3
        }).success(function(data){
            defer.resolve(data);
        }).error(function(data){
            defer.reject(data)
        });
        return defer.promise;
      },
    getKey: function(id) {
      var params = {
        "id":id 
      }
      var defer = $q.defer();
      $http({
          method:'post',
          url:constants.getRoot().getKey,
          data: constants.serializeData(params),
          timeout: 15e3
      }).success(function(data){
          defer.resolve(data);
      }).error(function(data){
          defer.reject(data)
      });
      return defer.promise;
    },
    getConstruction: function(id) {
      var params = {
        "id":id 
      }
      var defer = $q.defer();
      $http({
          method:'post',
          url:constants.getRoot().getConstruction,
          data: constants.serializeData(params),
          timeout: 15e3
      }).success(function(data){
          defer.resolve(data);
      }).error(function(data){
          defer.reject(data)
      });
      return defer.promise;
    } ,
    getThree: function(id) {
      var params = {
        "id":id 
      }
      var defer = $q.defer();
      $http({
          method:'post',
          url:constants.getRoot().getThree,
          data: constants.serializeData(params),
          timeout: 15e3
      }).success(function(data){
          defer.resolve(data);
      }).error(function(data){
          defer.reject(data)
      });
      return defer.promise;
    },

    getSpecial: function(id) {
      var params = {
        "id":id 
      }
      var defer = $q.defer();
      $http({
          method:'post',
          url:constants.getRoot().getSpecial,
          data: constants.serializeData(params),
          timeout: 15e3
      }).success(function(data){
          defer.resolve(data);
      }).error(function(data){
          defer.reject(data)
      });
      return defer.promise;
    }
  }
}])
.filter("dateTime",[function(){
  return function(e){
    if(!e) {
      return '';
    }
    var date = new Date(e);
    var y = date.getFullYear();
    var m = date.getMonth()+1;
    var d = date.getDate();
    var h = date.getHours();
    var i = date.getMinutes();
    var s = date.getSeconds();
    if(m<10) {
       m = '0'+m;
    }
    if(d<10) {
      d = "0"+d;
    }
    if(h<10) {
      h = "0"+h;
    }
    if(i<10) {
      i = "0"+i;
    }
    if(s<10) {
      s = "0"+s;
    }
    return y+"-"+m+"-"+d;
  }
}]).filter("color",[function(){
  return function(e){
    var r=Math.floor(Math.random()*256);
    var g=Math.floor(Math.random()*256);
    var b=Math.floor(Math.random()*256);
    return "rgb("+r+','+g+','+b+")";
 
  }
}]).filter("name",[function(){
  return function(e){
    if(e) {
      return e.substring(e.length-2);
    }
 
  }
}]).filter("downImg",["constants",function(constants){
  return function(e){
    if(e) {
     
      return  constants.getRoot().downloadImg+e;
    }
 
  }
}]).directive('actualSrc', function () {
  return{
    link: function postLink(scope, element, attrs) {
      attrs.$observe('actualSrc', function(newVal, oldVal){
        element.attr("src","data:image/gif;base64,iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAYAAAAfFcSJAAAADUlEQVQImWNgYGBgAAAABQABh6FO1AAAAABJRU5ErkJggg==");
        if(newVal){
          img = new Image();
          img.src = newVal;
          angular.element(img).bind('load', function () {
            element.attr("src", newVal);
          });
          
          angular.element(img).bind('error', function() {
            if(attrs.error) {
              element.attr('src', attrs.error);	
            } else {
              element.attr('src',"data:image/gif;base64,iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAYAAAAfFcSJAAAADUlEQVQImWNgYGBgAAAABQABh6FO1AAAAABJRU5ErkJggg==");
            }
          })
        }
      });

    }
  }
});
 
