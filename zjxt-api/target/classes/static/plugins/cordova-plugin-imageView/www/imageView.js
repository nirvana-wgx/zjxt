cordova.define("cordova.plugin.imageView.imageView", function (require, exports, module) {
    var ImageView = function () {

    };

    ImageView.prototype.openBrowser = function (successCallback, errorCallback, options) {
        if (!options) {
            options = {};
        }
        var params = {
            displayActionButton: options.displayActionButton ? options.displayActionButton : false,
            displayNavArrows: options.displayNavArrows ? options.displayNavArrows : false,
            enableGrid: options.enableGrid ? options.enableGrid : false,
            startIndex: options.startIndex ? options.startIndex : 0,
            data: options.data
        };
        return cordova.exec(successCallback, errorCallback, "ImageView", "openBrowser", [params]);
    };

    window.ImageView = new ImageView();

});
	