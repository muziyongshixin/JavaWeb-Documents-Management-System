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

function ResumeNews( )
{
    createXMLHttpRequest();   //调用创建XMLHttpRequest对象的方法
    xmlHttp.onreadystatechange=callback;   //设置回调函数
    var newsid=document.getElementById("check_id").value;

    var url="ResumeNews?newsid="+newsid;

    if(confirm("确定恢复编号为："+newsid+"的文章？"))
    {
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
                    data=data.toString();
                    if(data=="successful")
                    {
                        alert("恢复成功");
                        window.location.href="content_user.jsp";
                    }
                    else
                    {
                        alert("恢复失败，请重试");
                        location.reload();

                    }

                }
            }
        }
    }
    else
        return false;

}


