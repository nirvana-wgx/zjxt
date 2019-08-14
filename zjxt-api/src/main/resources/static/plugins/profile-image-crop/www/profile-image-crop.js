 

cordova.define("profile.image.crop.ProfileImageCrop.ProfileImageCrop", function(require, exports, module) { 
    var ProfileImageCrop = function() {
        
    };
        
        
    ProfileImageCrop.prototype.crop = function(options) {
        if(!options){
            options = {};
        }
        return new window.Promise(function(resolve, reject) {
           cordova.exec(resolve, reject, "ProfileImageCrop", "crop", [options]);
        });
    };
    
    window.ProfileImageCrop = new ProfileImageCrop();
});

  