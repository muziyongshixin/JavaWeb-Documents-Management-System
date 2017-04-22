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

function queryInfo( id,table)
{
    id=id.toString();
    createXMLHttpRequest();   //调用创建XMLHttpRequest对象的方法
    xmlHttp.onreadystatechange=callback;   //设置回调函数
    var url="QueryOneNews?table="+table+"&newsid="+id;

    alert("查询编号为："+id+"的文章详情？");
    xmlHttp.open("post",url,true);      //向服务器端发送请求
    xmlHttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=utf8");
    xmlHttp.send(null);
    function callback()
    {
        if(xmlHttp.readyState==4)
        {
            if(xmlHttp.status==200)
            {
                var data= xmlHttp.responseText;

                var parameters=data.split("||");
                var id=parameters[0]
                var head=parameters[1];
                var author=parameters[2];
                var time=parameters[3];
                var type=parameters[4]
                var content=parameters[5];


                document.getElementById("check_id").value=id;
                document.getElementById("check_head").value=head;
                document.getElementById("check_author").value=author;
                document.getElementById("check_time").value=time;
                document.getElementById("check_type").value=type;
                document.getElementById("check_content").value=content;


            }
        }
    }
}


function checkpass() {//审核通过

    createXMLHttpRequest();   //调用创建XMLHttpRequest对象的方法
    xmlHttp.onreadystatechange=callback;   //设置回调函数
    var newsid=document.getElementById("check_id").value;
    var url="checkNews?newsid="+newsid;
    xmlHttp.open("post",url,true);      //向服务器端发送请求
    xmlHttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=utf8");
    xmlHttp.send(null);
    function callback()
    {
        if(xmlHttp.readyState==4)
        {
            if(xmlHttp.status==200)
            {
                alert("审核通过，已完成");
                location.reload();
            }
        }
    }
}