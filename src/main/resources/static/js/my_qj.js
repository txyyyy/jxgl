var userId = $("#userId").val();
var leave_pageNum = 1, pageSize = 10, leave_pages = 0;
$(document).ready(function () {
    init();
});
/**
 * 初始化函数
 */
function init() {

    getLeaveMsg();
}
$("#leaveDuration").blur(function () {
    var leaveDuration = $("#leaveDuration").val();
    if(parseInt(leaveDuration)!=NaN && leaveDuration%1 === 0){

    }else {
        alert("请输入整数天数的请假时长!");
        $("#leaveDuration").focus();
    }
})
$("#submitLeave").click(function () {
    var leaveDay = $("#leaveDay").val();
    var leaveDuration = $("#leaveDuration").val();
    var leaveDesc = $("#leaveDesc").val();
    if(parseInt(leaveDuration)!=NaN && leaveDuration%1 === 0){

    }else {
        alert("不是整数");
    }
    // $.ajax({
    //     url: "/employee/insertOverWork",     //后台请求的数据
    //     data: {
    //         "userId": userId,
    //         "workDesc": overWorkdesc,
    //         "workDay": workDay,
    //         "workTime": workTime,
    //         "isRestDay": isRestDay
    //     },
    //     type: "post",                  //请求方式
    //     async: true,                   //是否异步请求
    //     success: function (data) {      //如果请求成功，返回数据。
    //         $("#overWorkdesc").val("");
    //         $("#workDay").val("");
    //         $("input[type='radio']").removeAttr('checked')
    //         alert("发起加班申请成功！");
    //         overworkMsg();
    //     },
    //     error:function () {
    //         alert("发起加班失败！");
    //     }
    //
    // });

})

function getLeaveMsg() {

}