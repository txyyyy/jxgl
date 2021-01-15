var userId = $("#userId").val();
var jx_pageNum = 1, pageSize = 10, jx_pages = 0;
$(document).ready(function () {
    //createMask();
    init();
});
//创建遮罩层函数体
function createMask(){
    var node=document.createElement('div');
    node.setAttribute('id','backdrop');
    node.style="position:fixed;top:0;left:0;right:0;bottom:0;z-index:1000;background-color:rgba(0,0,0,0.3);";
    node.style.display="none";
    var html='<div style="position: fixed; top: 40%; left: 50%; z-index: 1001;">';
    html+='<div style="text-align:center;">';
    html+='<div class="spinner">'
    html+='<div class="spinner-container container1">'
    html+=' <div class="circle1"></div>'
    html+='<div class="circle2"></div>'
    html+='<div class="circle3"></div>'
    html+=' <div class="circle4"></div>'
    html+='</div>'
    html+=' <div class="spinner-container container2">'
    html+=' <div class="circle1"></div>'
    html+='<div class="circle2"></div>'
    html+='<div class="circle3"></div>'
    html+=' <div class="circle4"></div>'
    html+='</div>'

    html+='<div class="spinner-container container3">'
    html+=' <div class="circle1"></div>'
    html+='<div class="circle2"></div>'
    html+='<div class="circle3"></div>'
    html+=' <div class="circle4"></div>'
    html+='</div>'
    html+='</div>'
    html+='<div style="padding-left:10px;font-size:16px;color:#FFF; ">数据加载中...</div>';
    html+='</div>';
    html+='</div>';
    node.innerHTML=html;
    var body=document.querySelector('body');
    body.appendChild(node);
//		        $("#backdrop").trigger('create');

}
//开启遮罩层函数体

function showMask(){
    var backdrop=document.getElementById('backdrop');
    backdrop.style.display='block';
}



//关闭遮罩层函数体
function closeMask(){
    var backdrop=document.getElementById('backdrop');
    backdrop.style.display='none';
}



//页面初始化完成，关闭遮罩
document.onreadystatechange = function(){
    if(document.readyState == "complete"){
        closeMask();
    }
}
/**
 * 初始化函数
 */
function init() {
    setMonth();
    getJxMsg();

    showMask();


}
$("#selectByMonth").click(function () {
    getJxMsg();

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
function getJxMsg() {
    var month = $("#jxMonth").val()
    $("#monthNow").html(month);
    $.ajax({
        beforeSend: function () {
            showMask();
        },
        url: "/admin/getAllCheckCountInfo",     //后台请求的数据
        data: {
            "pageSize": pageSize,
            "pageNum": jx_pageNum,
            "month": month

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
            },
        complete:function () {
            closeMask();
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