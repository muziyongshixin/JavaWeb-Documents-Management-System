/**
 * Created by 32706 on 2017/4/5.
 */
var xmlHttp=false;
function createXMLHttpRequest()
{
    if (window.ActiveXObject)  //在IE浏览器中创建XMLHttpRequest对象
    {
        try{
            xmlHttp=new ActiveXObject("Msxml2.XMLHTTP");
        }
        catch(e){
            try{
                xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
            }
            catch(ee){
                xmlHttp=false;
            }
        }
    }
    else if (window.XMLHttpRequest) //在非IE浏览器中创建XMLHttpRequest对象
    {
        try{
            xmlHttp = new XMLHttpRequest();
        }
        catch(e){
            xmlHttp=false;
        }
    }
}

function addNews(username,table,destination)
{

    createXMLHttpRequest();   //调用创建XMLHttpRequest对象的方法
    xmlHttp.onreadystatechange=callback;   //设置回调函数
    var myDate = new Date();
    var nowtime=myDate.Format("yyyy-MM-dd hh:mm:ss");
    var idtime=myDate.Format("yyyyMMddhhmmss");
    alert("正在发布新文章，请稍后......"+nowtime);

    var newsid=idtime+username;
    var head=document.getElementById("check_head").value;
    var content=document.getElementById("check_content").value;
    var publish_time=nowtime;
    var author=username;
    var newstype=document.getElementById("check_type").value;
    var url="InsertOneNews?table="+table+"&newsid="+newsid+"&head="+head+"&publish_time="+publish_time+"&author="+author+"&newstype="+newstype;

    xmlHttp.open("post",url,true);      //向服务器端发送请求
    xmlHttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=utf8");
    xmlHttp.send("content="+content);
    function callback()
    {
        if(xmlHttp.readyState==4)
        {
            if(xmlHttp.status==200)
            {

                var data= xmlHttp.responseText;
                data=data.toString();
                if(data=="successful")
                {

                    alert("发布成功");
                    window.location.href=destination;
                }
                else if(data=="failed")
                {
                    alert("发布失败，请重试");
                    location.reload();
                }


            }
        }
    }

}


function test() {

    var myDate = new Date();
    var nowtime=myDate.Format("yyyy-MM-dd hh:mm:ss");
    alert("正在发布新文章，请稍后......"+nowtime);
    var time=myDate.Format("yyyyMMddhhmmss");
    alert(time);
}

Date.prototype.Format = function(format){

    var o = {

        "M+" : this.getMonth()+1, //month

        "d+" : this.getDate(), //day

        "h+" : this.getHours(), //hour

        "m+" : this.getMinutes(), //minute

        "s+" : this.getSeconds(), //second

        "q+" : Math.floor((this.getMonth()+3)/3), //quarter

        "S" : this.getMilliseconds() //millisecond

    }

    if(/(y+)/.test(format)) {

        format = format.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));

    }

    for(var k in o) {

        if(new RegExp("("+ k +")").test(format)) {

            format = format.replace(RegExp.$1, RegExp.$1.length==1 ? o[k] : ("00"+ o[k]).substr((""+ o[k]).length));

        }

    }

    return format;

}
