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

        $.ajax({
            url: "/employee/insertEmoloyeeLeave",     //后台请求的数据
            data: {
                "jobNumber": userId,
                "leaveTime": leaveDay,
                "leaveDuration": leaveDuration,
                "leaveReason": leaveDesc
            },
            type: "post",                  //请求方式
            async: true,                   //是否异步请求
            success: function (data) {      //如果请求成功，返回数据。
                alert("发起请假申请成功！");
                getLeaveMsg();
            },
            error:function () {
                alert("发起请假申请失败！");
            }

        });


})

function getLeaveMsg() {
    $.ajax({
        url: "/employee/getEmployeeLeave",     //后台请求的数据
        data: {
            "pageSize": pageSize,
            "pageNum": leave_pageNum,
            "userId": userId

        },
        type: "post",                  //请求方式
        async: true,                   //是否异步请求
        success: function (data) {      //如果请求成功，返回数据。
            $("#table_leave tbody").html("");
            leave_pages = data.pages;
            var list_ = data.list;
            for (var i = 1; i < list_.length+1; i++) {
                var content = list_[i - 1];
                var status="";
                if(content.status=0){
                    status="待审核";
                }else if(content.status=1){
                    status="已通过";
                }else if(content.status=2){
                    status="未通过";
                }
                var trHTML = "<tr>"
                    + "<td>" + content.leaveTime + "</td>"
                    +"<td>" + content.leaveReason + "</td>"
                    +"<td>" + content.leaveDuration+ "</td>"
                    +"<td>" + status + "</td>"
                    +"</tr>";
                $("#table_leave tbody").append(trHTML);//在table最后面添加一行
            }
        }
    });
}
/**
 * 我的请假表翻页
 */
$(".leave").click(function () {
    var data = $(this).html();
    switch (data) {
        case "首页":
            if (leave_pageNum == 1) {
                break;
            }
            leave_pageNum = 1;
            getLeaveMsg();
            break;
        case "上一页":
            if (leave_pageNum == 1) {
                break;
            }
            leave_pageNum--;
            getLeaveMsg();
            break;
        case "下一页":
            if (leave_pageNum == leave_pages) {
                break;
            }
            leave_pageNum++;
            getLeaveMsg();
            break;
        case "尾页":
            if (leave_pageNum == leave_pages) {
                break;
            }
            leave_pageNum = leave_pages;
            getLeaveMsg();
            break;
    }
});

//****************************************************************************************************