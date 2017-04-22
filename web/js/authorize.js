/**
 * Created by 32706 on 2017/4/5.
 */
var xmlHttp = false;
function createXMLHttpRequest() {
    if (window.ActiveXObject)  //在IE浏览器中创建XMLHttpRequest对象
    {
        try {
            xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
        }
        catch (e) {
            try {
                xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
            }
            catch (ee) {
                xmlHttp = false;
            }
        }
    }
    else if (window.XMLHttpRequest) //在非IE浏览器中创建XMLHttpRequest对象
    {
        try {
            xmlHttp = new XMLHttpRequest();
        }
        catch (e) {
            xmlHttp = false;
        }
    }
}

function authorizeNews(newsid) {

    createXMLHttpRequest();   //调用创建XMLHttpRequest对象的方法
    xmlHttp.onreadystatechange = callback;   //设置回调函数

    var user_list = document.getElementById("user_list").value;

    if (confirm("确定将文章" + newsid + "授权给以下用户？" + user_list)) {
        var url = "Authorize?newsid=" + newsid;

        xmlHttp.open("post", url, true);      //向服务器端发送请求
        xmlHttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf8");
        xmlHttp.send("&user_list=" + user_list);
        function callback() {
            if (xmlHttp.readyState == 4) {
                var data = xmlHttp.responseText;
                alert(data);
                data = data.toString();
                if (data.contains("successful")) {

                    alert(data);
                    location.reload();
                }
                else if (data.contains("failed")) {
                    alert(data);
                    location.reload();
                }


            }
        }
    }
    else
        return false;


}


