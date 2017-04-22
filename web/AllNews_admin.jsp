<%@ page import="com.lut.beans.NewsRealese" %>
<%@ page import="dao.NewsRealeseDao" %>
<%@ page import="java.util.ArrayList" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>管理员界面||查看所有文章</title>
    <link rel="stylesheet" type="text/css" href="css/style.css"/>
    <link href='http://fonts.googleapis.com/css?family=Belgrano' rel='stylesheet' type='text/css'>
    <!-- jQuery file -->
    <script src="js/jquery.min.js"></script>
    <script src="js/jquery.tabify.js" type="text/javascript" charset="utf-8"></script>
    <script type="text/javascript">
        var $ = jQuery.noConflict();
        $(function () {
            $('#tabsmenu').tabify();
            $(".toggle_container").hide();
            $(".trigger").click(function () {
                $(this).toggleClass("active").next().slideToggle("slow");
                return false;
            });
        });
    </script>

    <script type=text/javascript src=js/query.js></script>
</head>

<% String administorName = (String) session.getAttribute("username");
    NewsRealeseDao newsRealeseDao = new NewsRealeseDao();
    ArrayList newsRealese = newsRealeseDao.AdministorQueryAllNews(administorName);
%>
<body>
<div id="panelwrap">

    <div class="header">
        <div class="title"><a href="#">欢迎${username}登录本系统</a></div>

        <div class="header_right">Welcome ${username}, <a href="#" class="settings">Settings</a> <a href="login_admin.jsp"
                                                                                                    class="logout">Logout</a>
        </div>

        <div class="menu">
            <ul>
                <li><a href="check_pending.jsp" >文档审核</a></li>
                <li><a href="AllNews_admin.jsp" class="selected">查看所有文章</a></li>
                <li><a href="AddNews_admin.jsp">发布文章</a></li>

            </ul>
        </div>

    </div>

    <div class="submenu">
        <ul>
            <li><a href="#" class="selected">settings</a></li>
            <li><a href="#">users</a></li>
            <li><a href="#">categories</a></li>
            <li><a href="#">edit section</a></li>
            <li><a href="#">templates</a></li>
        </ul>
    </div>

    <div class="center_content">

        <div id="right_wrap">
            <div id="right_content">
                <h2>所有文章列表</h2>


                <table id="rounded-corner">
                    <thead>
                    <tr>

                        <th>文档编号</th>
                        <th>标题</th>
                        <th>发布日期</th>
                        <th>文档类别</th>
                        <th>作者</th>
                        <th>查看文档详情</th>
                        <th>删除</th>
                    </tr>
                    </thead>
                    <tfoot>
                    <tr>
                        <td colspan="12">以上是所有文章</td>
                    </tr>
                    </tfoot>
                    <tbody>
                    <%
                        for (int i = 0; i < newsRealese.size(); i++) {
                            NewsRealese tem = (NewsRealese) newsRealese.get(i);
                    %>
                    <tr class="odd">

                        <td id="now_newsid"><%=tem.getNewsId()%>
                        </td>
                        <td><%=tem.getHead()%>
                        </td>
                        <td><%=tem.getPublish_time()%>
                        </td>
                        <td><%=tem.getNewstype()%>
                        </td>
                        <td><%=tem.getIssueuser()%>
                        </td>
                        <td><a href="#"><img src="images/edit.png" alt="" title="" border="0"
                                             onclick="queryInfo('<%=tem.getNewsId()%>','news')"/></a>
                        </td>

                        <td><a href="#"><img src="images/trash.gif" alt="" title="" border="0"
                                             onclick="javascrtpt:window.location.href = 'DeleteOneNews?destination=AllNews_admin.jsp&table=news&newsid=<%=tem.getNewsId()%>'"/></a>
                        </td>
                    </tr>


                    <%
                        }
                    %>
                    </tbody>
                </table>


                <ul id="tabsmenu" class="tabsmenu">
                    <li class="active"><a href="#tab1">文章详情</a></li>

                </ul>
                <div id="tab1" class="tabcontent">

                    <div class="form">


                        <div class="form_row">
                            <label>编号:</label>
                            <input type="text" class="form_input" id="check_id" value="" readonly/>
                        </div>

                        <div class="form_row">
                            <label>标题:</label>
                            <input type="text" class="form_input" id="check_head" value="" readonly/>
                        </div>

                        <div class="form_row">
                            <label>作者:</label>
                            <input type="text" class="form_input" id="check_author" value="" readonly/>
                        </div>

                        <div class="form_row">
                            <label>时间:</label>
                            <input type="text" class="form_input" id="check_time" value="" readonly/>
                        </div>

                        <div class="form_row">
                            <label>类型:</label>
                            <input type="text" class="form_input" id="check_type" value="" readonly/>
                        </div>

                        <div class="form_row">
                            <label>内容:</label>
                            <textarea rows="100" class="form_textarea" id="check_content"  value="" readonly></textarea>
                        </div>

                        <div class="clear"></div>
                    </div>
                </div>





















                <!--以下暂时无用------------------------------------------------------------------------>








                <div class="toogle_wrap">
                    <div class="trigger"><a href="#">Java Web 文档管理系统</a></div>

                    <div class="toggle_container">
                        <p>
                            在本页面您能够查看所有的待审核文档，您可以点击“文档详情”按钮来查看文档的详细信息；
                            对于可以通过的文档可以点击“审核通过”按钮或者使用列表中的审核通过按钮通过该文档；
                            对于不能通过的文档，您可以删除该文档，该文档将无法通过审核并将从系统中删除。
                        </p>
                    </div>
                </div>

            </div>
        </div><!-- end of right content-->


        <div class="sidebar" id="sidebar">
            <h2>Browse categories</h2>

            <ul>
                <li><a href="#" class="selected">Main page</a></li>
                <li><a href="#">Template settings</a></li>
                <li><a href="#">Add page</a></li>
                <li><a href="#">Edit section</a></li>
                <li><a href="#">Templates</a></li>
                <li><a href="#">Clients</a></li>
            </ul>

            <h2>Page Section</h2>

            <ul>
                <li><a href="#">Edit section</a></li>
                <li><a href="#">Templates</a></li>
                <li><a href="#">Clients</a></li>
                <li><a href="#">Docs and info</a></li>
            </ul>

            <h2>User Settings</h2>

            <ul>
                <li><a href="#">Edit user</a></li>
                <li><a href="#">Add users</a></li>
                <li><a href="#">Manage users</a></li>
                <li><a href="#">Help</a></li>
            </ul>

            <h2>Text Section</h2>
            <div class="sidebar_section_text">
                Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et
                dolore magna aliqua.
            </div>

        </div>


        <div class="clear"></div>
    </div> <!--end of center_content-->



</div>


</body>
</html>