<%@ page import="com.lut.beans.NewsRealese" %>
<%@ page import="dao.NewsRealeseDao" %>
<%@ page import="java.util.ArrayList" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="zh-cn">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
    <meta name="renderer" content="webkit">
    <title>用户空间</title>

    <link rel="stylesheet" href="http://www.pintuer.com/css/pintuer.css">
    <link rel="stylesheet" href="css/admin.css">
    <script src="http://www.pintuer.com/js/jquery.js"></script>
    <script src="http://www.pintuer.com/js/pintuer.js"></script>

    <script type=text/javascript src=js/query.js></script>
    <script type="text/javascript" src="js/authorize.js"></script>
    <script src="js/admin.js"></script>
    <link type="image/x-icon" href="http://www.pintuer.com/favicon.ico" rel="shortcut icon"/>
    <link href="http://www.pintuer.com/favicon.ico" rel="bookmark icon"/>
</head>


<% String username = (String) session.getAttribute("username");
    NewsRealeseDao newsRealeseDao = new NewsRealeseDao();
    ArrayList newsRealese = newsRealeseDao.UserQueryAllNews(username,"news");
%>

<body>
<div class="lefter">
    <div class="logo">
        <a href="http://www.pintuer.com" target="_blank"><img src="images/logo.png" alt="后台管理系统"/></a>
    </div>
</div>
<div class="righter nav-navicon" id="admin-nav">
    <div class="mainer">
        <div class="admin-navbar">
					<span class="float-right">
                    <a class="button button-little bg-main" href="content_user.jsp">回到首页</a>
                    <a class="button button-little bg-yellow" href="login_user.jsp">注销登录</a>
                </span>
            <ul class="nav nav-inline admin-nav">
                <li >
                    <a href="content_user.jsp" class="icon-file-text">查看文章</a>
                    <ul>
                        <li><a href="#">添加内容</a></li>
                        <li><a href="#">内容管理</a></li>
                        <li><a href="#">分类设置</a></li>
                        <li><a href="#">链接管理</a></li>
                    </ul>
                </li>

                <li class="active"><a href="Authorization_user.jsp" class="icon-file">文章授权</a></li>


                <li>
                    <a href="AddNews_user.jsp" class="icon-cog">发布文章</a>
                    <ul>
                        <li><a href="#">全局设置</a></li>
                        <li class="active"><a href="#">系统设置</a></li>
                        <li><a href="#">会员设置</a></li>
                        <li><a href="#">积分设置</a></li>
                    </ul>
                </li>

                <li><a href="Dustbin_user.jsp" class="icon-th-list">回收站</a></li>

            </ul>
        </div>
        <div class="admin-bread">
            <span>您好，${username}，欢迎您的光临。</span>
            <ul class="bread">

                <li><a href="content.html"> </a></li>
            </ul>
        </div>
    </div>
</div>

<div class="admin">

    <div class="panel admin-panel">
        <div class="panel-head"><strong>我的文章列表</strong></div>
        <div class="padding border-bottom">
            <input type="button" class="button button-small checkall" name="checkall" checkfor="id" value="全选"/>
        </div>
        <table class="table table-hover">
            <tr>
                <th width="45">选择</th>
                <th width="300">标题</th>
                <th width="100">时间</th>
                <th width="100">类别</th>
                <th width="150">操作</th>
            </tr>

            <%
                for (int i = 0; i < newsRealese.size(); i++) {
                    NewsRealese tem = (NewsRealese) newsRealese.get(i);
            %>
            <tr>
                <td>
                    <input type="checkbox" name="id" value="<%=i%>>"/>
                </td>
                <td><%=tem.getHead()%>
                </td>
                <td><%=tem.getPublish_time()%>
                </td>
                <td><%=tem.getNewstype()%>
                </td>
                <td>

                    <a class="button border-yellow button-little" href="#" onclick="authorizeNews('<%=tem.getNewsId()%>')">授权文章</a>
                    </td>
            </tr>
            <%
                }
            %>

        </table>
        <div class="panel-foot text-center">
            <ul class="pagination">
                <li><a href="#">上一页</a></li>
            </ul>
            <ul class="pagination pagination-group">
                <li class="active"><a href="#">1</a></li>
                <li><a href="#">2</a></li>
                <li><a href="#">3</a></li>
                <li><a href="#">4</a></li>
                <li><a href="#">5</a></li>
            </ul>
            <ul class="pagination">
                <li><a href="#">下一页</a></li>
            </ul>
        </div>

    </div>

    <br>
    <br>


    <div class="panel admin-panel">
        <div class="panel-head"><strong>待授权用户</strong></div>
        <div class="tab">

            <div class="tab-body">
                <br/>
                <div class="tab-panel active" id="tab-set">



                    <div class="form-group">
                        <div class="field">
                            <textarea class="input" id="user_list" rows="5" cols="50" placeholder="填写待授权用户，使用‘，’分割不同的用户  eg：user1，user2" value=""></textarea>
                        </div>
                    </div>


                </div>

            </div>
        </div>


    </div>

    <br/>
</div>


</body>

</html>