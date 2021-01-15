var userId = $("#userId").val();
var salary_pageNum = 1, pageSize = 10, salary_pages = 0;
$(document).ready(function () {
    init();
});
/**
 * 初始化函数
 */
function init() {
    setMonth();
    getSalary();
}
$("#selectByMonth").click(function () {
    getSalary();

})
function setMonth() {
    var month=new Date();
    var result="";
    var date = month.toLocaleDateString();
    var last=date.lastIndexOf("/");
    var before=date.indexOf("/");
    if(last-before==2){
        result= date.substring(0,before)+"-0"+date.substring(5,6);
    }else {
        result=date.substring(0,before)+"-"+date.substring(5,7);
    }
    $("#jxMonth").val(result);
}
function getSalary() {
    var month = $("#jxMonth").val()
    $("#monthNow").html(month);
    $.ajax({
        url: "/admin/",     //后台请求的数据
        data: {
            "pageSize": pageSize,
            "pageNum": salary_pageNum,
            "month": month

        },
        type: "post",                  //请求方式
        async: true,                   //是否异步请求
        success: function (data) {      //如果请求成功，返回数据。
            $("#table_jx tbody").html("");
            salary_pages = data.pages;
            var list_ = data.list;
            for (var i = 1; i < list_.length+1; i++) {
                var jxDto = list_[i - 1];
                var checks=jxDto.checkInfos;
                var trCount="<tr style='background-color: rgba(238, 211, 210,0.3)'><td>"+"姓名:"+jxDto.userName+"&nbsp;&nbsp;&nbsp;&nbsp;"+"部门:"+jxDto.departmentName+"</td>"+
                    "<td>缺勤次数:<span style='color: red'>"+jxDto.queqinNum+"</span>&nbsp;&nbsp;&nbsp;加班次数:<span style='color:blue'>"+jxDto.overWorkNum+"</span></td>"+"<td>请假次数<span style='color:green'>"+
                    jxDto.leaveNum+"</span>&nbsp;&nbsp;&nbsp;迟到次数:<span style='color: orange'>"+jxDto.lateNum+"</span>"+"</td>"+"<td>早退次数:<span style='color: orange'>"+jxDto.beforeNum+"</span></td>"+"<td>"+"当前月:"+jxDto.month+"</span></td></tr>"
                $("#table_jx tbody").append(trCount);
                for(var j = 1;j<checks.length+1;j++){
                    var signInStatus ="----";
                    var signOutStatus="----";
                    if(checks[j-1].signInStatus==0){
                        signInStatus="<span style='color: red'>缺勤</span>";
                    }else if(checks[j-1].signInStatus==2){
                        signInStatus="<span style='color: darkorange'>迟到</span>";
                    }else if(checks[j-1].signInStatus==3){
                        signInStatus="<span style='color: darkgreen'>请假</span>";
                    }
                    if(checks[j-1].signOutStatus==0){
                        signOutStatus="<span style='color: red'>缺勤</span>";
                    }else if(checks[j-1].signOutStatus==2){
                        signOutStatus="<span style='color: darkorange'>早退</span>";
                    }else if(checks[j-1].signOutStatus==3){
                        signOutStatus="<span style='color: darkgreen'>请假</span>";
                    }
                    var trHTML = "<tr>"
                        + "<td>" + checks[j-1].signDate + "</td>"
                        +"<td>" + checks[j-1].signInTime + "</td>"
                        +"<td>" + signInStatus+ "</td>"
                        +"<td>" + checks[j-1].signOutTime + "</td>"
                        +"<td>" + signOutStatus + "</td>"
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
            if (salary_pageNum == 1) {
                break;
            }
            salary_pageNum = 1;
            getJxMsg();
            break;
        case "上一页":
            if (salary_pageNum == 1) {
                break;
            }
            salary_pageNum--;
            getJxMsg();
            break;
        case "下一页":
            if (salary_pageNum == salary_pages) {
                break;
            }
            salary_pageNum++;
            getJxMsg();
            break;
        case "尾页":
            if (salary_pageNum == salary_pages) {
                break;
            }
            salary_pageNum = salary_pages;
            getJxMsg();
            break;
    }
});

//****************************************************************************************************