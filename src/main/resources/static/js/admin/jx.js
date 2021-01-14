var userId = $("#userId").val();
var jx_pageNum = 1, pageSize = 10, jx_pages = 0;
$(document).ready(function () {
    init();
});
/**
 * 初始化函数
 */
function init() {

    getJxMsg();
}

function getJxMsg() {
    var month = $("#jxMonth").val()
    $.ajax({
        url: "/admin/getAllCheckCountInfo",     //后台请求的数据
        data: {
            "pageSize": pageSize,
            "pageNum": jx_pageNum,
            "month": "2021-01"

        },
        type: "post",                  //请求方式
        async: true,                   //是否异步请求
        success: function (data) {      //如果请求成功，返回数据。
            $("#table_jx tbody").html("");
            jx_pages = data.pages;
             var list_ = data.list;
             for (var i = 1; i < list_.length+1; i++) {
                 var jxDto = list_[i - 1];
                 var checks=jxDto.checkInfos;
                 var trCount="<tr><td>"+"姓名:"+jxDto.userName+"&nbsp;&nbsp;&nbsp;&nbsp;"+"部门:"+jxDto.departmentName+""+jxDto.queqinNum+""+jxDto.overWorkNum+""+
                     jxDto.leaveNum+""+jxDto.lateNum+""+jxDto.beforeNum+"&nbsp;&nbsp;&nbsp;&nbsp;"+"当前月:"+jxDto.month+"</td></tr>"
                    $("#table_jx tbody").append(trCount);
                 for(var j = 1;j<checks.length+1;j++){
                     var trHTML = "<tr>"
                         + "<td>" + checks[j-1].signDate + "</td>"
                         +"<td>" + checks[j-1].signInTime + "</td>"
                         +"<td>" + checks[j-1].signInStatus+ "</td>"
                         +"<td>" + checks[j-1].signOutTime + "</td>"
                         +"<td>" + checks[j-1].signOutStatus + "</td>"
                         +"</tr>";
                     $("#table_jx tbody").append(trHTML);//在table最后面添加一行
                 }
             }


            }

    });
}
/**
 * 我的请假表翻页
 */
$(".jx").click(function () {
    var data = $(this).html();
    switch (data) {
        case "首页":
            if (jx_pageNum == 1) {
                break;
            }
            jx_pageNum = 1;
            getJxMsg();
            break;
        case "上一页":
            if (jx_pageNum == 1) {
                break;
            }
            jx_pageNum--;
            getJxMsg();
            break;
        case "下一页":
            if (jx_pageNum == jx_pages) {
                break;
            }
            jx_pageNum++;
            getJxMsg();
            break;
        case "尾页":
            if (jx_pageNum == jx_pages) {
                break;
            }
            jx_pageNum = jx_pages;
            getJxMsg();
            break;
    }
});

//****************************************************************************************************