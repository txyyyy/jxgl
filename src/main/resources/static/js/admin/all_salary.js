var userId = $("#userId").val();
var salary_pageNum = 1, pageSize = 10, salary_pages = 0;
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
$(document).ready(function () {
    init();
});
/**
 * 初始化函数
 */
function init() {
    setMonth();
    getSalary();
    showMask();
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
        beforeSend: function () {
            showMask();
        },
        url: "/admin/getAllSalaryByMonth",     //后台请求的数据
        data: {
            "pageSize": pageSize,
            "pageNum": salary_pageNum,
            "month": month

        },
        type: "post",                  //请求方式
        async: true,                   //是否异步请求
        success: function (data) {      //如果请求成功，返回数据。
            $("#table_salary tbody").html("");
            salary_pages = data.pages;
            var list_ = data.list;
            for (var i = 1; i < list_.length+1; i++) {
                var content = list_[i - 1];
                var totalPay = "<span style='color: red'>暂无数据</span>";
                var lateCutDay="<span style='color: red'>暂无数据</span>";
                var overTimePay="<span style='color: red'>暂无数据</span>";
                var finalPay="<span style='color: red'>暂无数据</span>";
                if(content.totalPay!=null){
                    totalPay=content.totalPay;
                }
                if(content.lateCutDay!=null){
                    lateCutDay=content.lateCutDay;
                }
                if(content.overTimePay!=null){
                    overTimePay=content.overTimePay;
                }
                if(content.finalPay!=null){
                    finalPay=content.finalPay;
                }
                var trHTML = "<tr>"
                    + "<td>" + content.userId + "</td>"
                    + "<td>" + content.userName + "</td>"
                    +"<td>" + content.departmentName + "</td>"
                    + "<td>" + totalPay+ "</td>"
                    +"<td>" + lateCutDay + "</td>"
                    +"<td>" + overTimePay +"</td>"
                    +"<td>" + finalPay + "</td>"
                    +"</tr>";
                $("#table_salary tbody").append(trHTML);//在table最后面添加一行
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
$(".salary").click(function () {
    var data = $(this).html();
    switch (data) {
        case "首页":
            if (salary_pageNum == 1) {
                break;
            }
            salary_pageNum = 1;
            getSalary();
            break;
        case "上一页":
            if (salary_pageNum == 1) {
                break;
            }
            salary_pageNum--;
            getSalary();
            break;
        case "下一页":
            if (salary_pageNum == salary_pages) {
                break;
            }
            salary_pageNum++;
            getSalary();
            break;
        case "尾页":
            if (salary_pageNum == salary_pages) {
                break;
            }
            salary_pageNum = salary_pages;
            getSalary();
            break;
    }
});

//****************************************************************************************************