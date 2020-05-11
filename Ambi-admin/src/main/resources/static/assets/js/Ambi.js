'use strict';
// Class definition


//统计文件信息
var CountAll = function() {

    // 自定义的初始化文件
    var start = function(){
        console.log("INit ");
        $.ajax({
            type: "GET",
            cache: false,
            async:false,    //异步
            url: ctx + "system/main",
            dataType: "html",
            success: function (res) {
                $('#kt_content').html($(res));
            }
        });
        return;
    }




    return {
        // Public functions
        init: function() {
            // init dmeo
            start();
        },
    };
}();


jQuery(document).ready(function() {
    CountAll.init();
});
