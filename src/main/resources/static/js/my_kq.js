var userId = $("#userId").val();
var checkInfo_pageNum = 1, pageSize = 10, checkInfo_pages = 0;
$(document).ready(function () {
    init();
});
/**
 * 初始化函数
 */
function init() {

    getDate();
    myCheckMsg();
    checkSignTime();
    checkStatus();
}
function getDate() {
    //获取当前时间
    var date = new Date();
    //格式化为本地时间格式
    var date1 = date.toLocaleString();
    //获取div
    var div1 = document.getElementById("thisTime");
    //将时间写入div
    div1.innerHTML = date1;
}
setInterval("getDate()",1000);
function myCheckMsg() {
    $.ajax({
        url: "/employee/getCheckInfoByUserId",     //后台请求的数据
        data: {
            "pageSize": pageSize,
            "pageNum": checkInfo_pageNum,
            "userId": userId
        },
        type: "post",                  //请求方式
        async: true,                   //是否异步请求
        success: function (data) {      //如果请求成功，返回数据。

            $("#table_checkInfo tbody").html("");
            checkInfo_pages = data.pages;
            var list_ = data.list;
            for (var i = 1; i < list_.length+1; i++) {
                var content = list_[i - 1];
                $("#userName").html(content.userName);
                $("#department").html(content.departmentName);
                var signInStatus ="";
                var signOutStatus="";

                if(content.signInStatus == 0){
                    signInStatus="<span style='color:red'>缺勤</span>";
                }else if(content.signInStatus == 1){
                    signInStatus="<span>已打卡</span>";
                }else if(content.signInStatus == 2){
                    signInStatus="<span style='color:darkorange'>迟到</span>";
                }else if(content.signInStatus == 3) {
                    signInStatus="<span style='color:green'>请假</span>";
                }
                if(content.signOutStatus == 0) {
                    signOutStatus="<span style='color:red'>缺勤</span>";
                }else if(content.signOutStatus == 1 ) {
                    signOutStatus="<span>已打卡</span>";
                }else if(content.signOutStatus == 2 ) {
                    signOutStatus="<span style='color:darkorange'>早退</span>";
                }else if(content.signOutStatus == 3 ) {
                    signOutStatus="<span style='color:green'>请假</span>";
                }

                var trHTML = "<tr>"
                    + "<td>" + content.signDate + "</td>"
                    + "<td>" + content.signInTime + "</td>"
                    +"<td>" + signInStatus + "</td>"
                    +"<td>" + content.signOutTime+ "</td>"
                    +"<td>" + signOutStatus + "</td>"
                    +"</tr>";
                $("#table_checkInfo tbody").append(trHTML);//在table最后面添加一行
            }
        },
    });
}

/**
 * 考勤信息表翻页
 */
$(".checkInfo").click(function () {
    var data = $(this).html();
    switch (data) {
        case "首页":
            if (checkInfo_pageNum == 1) {
                break;
            }
            checkInfo_pageNum = 1;
            myCheckMsg();
            break;
        case "上一页":
            if (checkInfo_pageNum == 1) {
                break;
            }
            checkInfo_pageNum--;
            myCheckMsg();
            break;
        case "下一页":
            if (checkInfo_pageNum == checkInfo_pages) {
                break;
            }
            checkInfo_pageNum++;
            myCheckMsg();
            break;
        case "尾页":
            if (checkInfo_pageNum == checkInfo_pages) {
                break;
            }
            checkInfo_pageNum = checkInfo_pages;
            myCheckMsg();
            break;
    }
});

//****************************************************************************************************
function checkStatus(){
    var signInStatus=0;
    var signOutStatus=0;
    $.ajax({
        url: "/employee/checkSignInStatus",     //后台请求的数据
        data: {
            "userId": userId
        },
        type: "post",                  //请求方式
        async: true,                   //是否异步请求
        success: function (data) {      //如果请求成功，返回数据。
            signInStatus=data;
            if(data==1){
                $("#noSignIn").html("已打卡");
                $("#signIn").css("display","none");
                $("#noSignIn").css("display","inline-block");
                $("#signIn").css({'background-color':'darkgrey','color':'grey'});
            }else if(data==2){
                $("#noSignIn").html("已打卡");
                $("#signIn").css("display","none");
                $("#noSignIn").css("display","inline-block");
                $("#isLate").css("display","inline-block");
                $("#signIn").css({'background-color':'darkgrey','color':'grey'});
            }else if(data==0){
                $("#noSignOut").css("display","inline-block");
                $("#signOut").css("display","none");
            }
        },
    });
    $.ajax({
        url: "/employee/checkSignOutStatus",     //后台请求的数据
        data: {
            "userId": userId
        },
        type: "post",                  //请求方式
        async: true,                   //是否异步请求
        success: function (data) {      //如果请求成功，返回数据。
            signOutStatus=data;
            if(data==1){
                $("#signOut").html("已打卡");
                $("#signOut").css("cursor","default");
                $("#signOut").css({'background-color':'darkgrey','color':'grey'});
            }else if(data==2){
                $("#signOut").html("已打卡");
                $("#signOut").css("cursor","default");
                $("#isBefore").css("display","inline-block");
                $("#signOut").css({'background-color':'darkgrey','color':'grey'});
            }
        },
    });


}
function checkSignTime() {
    var startSignInTime = new Date();   //允许签到的开始时间
    startSignInTime.setHours(7);
    startSignInTime.setMinutes(0);
    startSignInTime.setSeconds(0);
    var startSignInTimes=startSignInTime.getTime();
    var endSignInTime = new Date();     //允许签到的结束时间
    endSignInTime.setHours(11);
    endSignInTime.setMinutes(59);
    endSignInTime.setSeconds(59);
    var endSignInTimes = endSignInTime.getTime();
    var timeNow = new Date().getTime();
    if(startSignInTimes<timeNow && timeNow<endSignInTimes){

    }else {
        $("#signIn").css("display","none");
        $("#noSignIn").css("display","inline-block");
    }
    var startSignOutTime = new Date();//允许签退的开始时间
    startSignOutTime.setHours(16);
    startSignOutTime.setMinutes(0);
    startSignOutTime.setSeconds(0);
    var startSignOutTimes=startSignOutTime.getTime();
    var endSignOutTime = new Date(); //允许签退的结束时间
    endSignOutTime.setHours(21);
    endSignOutTime.setMinutes(0);
    endSignOutTime.setSeconds(0);
    var endSignOutTimes=endSignOutTime.getTime();
    if(startSignOutTimes<timeNow && timeNow<endSignOutTimes){

    }else {
        $("#signOut").css("display","none");
        $("#noSignOut").css("display","inline-block");
    }
}
$("#signIn").click(function () {

        if($("#signIn").css("display")=="inline-block"){
            $.ajax({
                url: "/employee/signIn",     //后台请求的数据
                data: {
                    "userId": userId,
                },
                type: "post",                  //请求方式
                async: true,                   //是否异步请求
                success: function (data) {      //如果请求成功，返回数据。
                    alert("打卡成功！");
                    checkStatus();
                },
                error:function () {
                    alert("打卡失败！");
                    checkStatus();
                }

            });
        }



})
$("#signOut").click(function () {

    if($("#signOut").css("display")=="inline-block"){
        $.ajax({
            url: "/employee/signOut",     //后台请求的数据
            data: {
                "userId": userId,
            },
            type: "post",                  //请求方式
            async: true,                   //是否异步请求
            success: function (data) {      //如果请求成功，返回数据。
                alert("打卡成功！");
                checkStatus();
            },
            error:function () {
                alert("打卡失败！");
                checkStatus();
            }

        });
    }



})