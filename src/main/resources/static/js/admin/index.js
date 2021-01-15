$(document).ready(function () {
    var userId=$("#userId").val();

    $.ajax({
        url : "/employee/getMyName?userId="+userId,     //后台请求的数据
        async : true,                   //是否异步请求
        success : function(data) {      //如果请求成功，返回数据。
            $("#myMsg").text(data.userName);
            $("#jx").attr("href","/admin/jx?userId="+data.userId);
            $("#salary").attr("href","/admin/allSalary?userId="+data.userId);
        },
    });
    document.getElementsByTagName('body')[0].style.height = window.innerHeight+'px';

});
