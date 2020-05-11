'use strict';
// Class definition


//统计文件信息
var CountAll = function() {

    // demo initializer
    var start = function(id){
        try{
            var iframe = document.getElementById(id);
            iframe.height = 0;
            if(iframe.attachEvent){
                console.log("attachEvent")
                iframe.attachEvent("onload", function(){
                    iframe.height =  iframe.contentWindow.document.documentElement.scrollHeight;
                });
                return;
            }else{
                console.log("onload")
                iframe.height = iframe.contentDocument.body.scrollHeight;
                iframe.onload = function(){
                    iframe.height = iframe.contentDocument.body.scrollHeight;
                };
                return;
            }
        }catch(e){
            throw new Error('setIframeHeight Error');
        }
    }


    var changeFrame = function () {
        $('.kt-menu__subnav>.kt-menu__item>.kt-menu__link').on('click', menuItem);
        // alert("sss");
    }



    function menuItem() {

        var dataUrl = $(this).attr('href');

        var iframe = document.getElementById("iframe-page-content");
        iframe.src = "http://localhost:85/" + dataUrl;
        alert(iframe.src);
        return false;
    }


    return {
        // Public functions
        init: function() {
            // init dmeo
            start("iframe-page-content");
            changeFrame();
        },
    };
}();


jQuery(document).ready(function() {
    CountAll.init();
});
