<%-- 
    Document   : notebook
    Created on : 2016-5-19, 17:32:33
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
                <style type="text/css">
    #e {
        position: absolute;
        top: 0;
        right: 0;
        bottom: 0;
        left: 0;
        font-size: 16px;
    }
</style>
    </head>
    <body>
        <h1>记事本</h1>
<div id="e"></div>
<script src="http://d1n0x3qji82z53.cloudfront.net/src-min-noconflict/ace.js"></script>
<script src="http://code.jquery.com/jquery-1.9.0.min.js"></script>
<script>
    var myKey = "SecretKeyz";
    $(document).ready(function () {
        var e = ace.edit("e");
        e.setTheme("ace/theme/solarized_light");
        e.getSession().setMode("ace/mode/markdown");
    });
</script>
    </body>
</html>
