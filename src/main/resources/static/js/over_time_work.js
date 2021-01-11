var overwork_pageNum = 1, pageSize = 10, overwork_pages = 0;
var userId = $("#userId").val();
$(document).ready(function () {
    init();
});
/**
 * 初始化函数
 */
function init() {
    overworkMsg();
}
$("#submitOverWork").click(function () {
var overWorkdesc = $("#overWorkdesc").val()
    console.log(overWorkdesc)
})
function overworkMsg() {
    $.ajax({
        url: "/employee/getOverWorkMsg",     //后台请求的数据
        data: {
            "pageSize": pageSize,
            "pageNum": overwork_pageNum,
            "userId": userId
        },
        type: "post",                  //请求方式
        async: true,                   //是否异步请求
        success: function (data) {      //如果请求成功，返回数据。
            $("#table_overwork tbody").html("");
            overwork_pages = data.pages;
            var list_ = data.list;
            for (var i = 1; i < list_.length+1; i++) {
                var content = list_[i - 1];
                var trHTML = "<tr>"
                    + "<td>" + content.workDay + "</td>"
                    + "<td>" + content.userId + "</td>"
                    +"<td>" + content.userName + "</td>"
                    + "<td>" + content.workDesc + "</td>"
                    +"<td>" + content.isRestDay + "</td>"
                    +"<td>" + content.workTime + "</td>"
                    +"<td>" + content.status + "</td>"
                    +"</tr>";
                $("#table_overwork tbody").append(trHTML);//在table最后面添加一行
            }
        },
    });
}
/**
 * 加班信息表翻页
 */
$(".overwork").click(function () {
    var data = $(this).html();
    switch (data) {
        case "首页":
            if (overwork_pageNum == 1) {
                break;
            }
            overwork_pageNum = 1;
            overworkMsg();
            break;
        case "上一页":
            if (overwork_pageNum == 1) {
                break;
            }
            overwork_pageNum--;
            overworkMsg();
            break;
        case "下一页":
            if (overwork_pageNum == overwork_pages) {
                break;
            }
            overwork_pageNum++;
            overworkMsg();
            break;
        case "尾页":
            if (overwork_pageNum == overwork_pages) {
                break;
            }
            overwork_pageNum = overwork_pages;
            overworkMsg();
            break;
    }
});

//****************************************************************************************************