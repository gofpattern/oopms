var isIE = navigator.userAgent.indexOf("MSIE") > 1;
var isChrome = navigator.userAgent.indexOf("Chrome") > 1;
var isFirefox = navigator.userAgent.indexOf("Firefox") > 1;

if(isIE || isChrome) {
    document.onkeydown = function () {
        var e = event || window.event;
        var keyASCII = parseInt(e.keyCode, 10);
        var src = e.srcElement;
        return checkKey(keyASCII, src);
    }
}
if(isFirefox) {
    document.onkeypress = function(ev){
        var e = ev || window.event;
        var keyASCII = parseInt(e.keyCode, 10);
        var src = e.target;
        return checkKey(keyASCII, src);
    }
}
function checkKey(keyASCII, src){
    var tag = src.tagName.toUpperCase();
    switch (keyASCII){
        case 8 : /*backspace key*/
            if(src.readOnly || src.disabled || (tag != "INPUT" && tag != "TEXTAREA")) {
                return false;
            }
            if(src.type) {
                var type = ("" + src.type).toUpperCase();
                return type != "CHECKBOX" && type != "RADIO" && type != "BUTTON";
            }
            break;
        //case 13 : /*enter key*/
            //return false;
            //break;
        
        default:
        return true;
    }
}