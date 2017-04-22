<!DOCTYPE html>
<html lang="zh-cn">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
    <meta name="renderer" content="webkit">
    <title>News details</title>

    <link rel="stylesheet" href="http://www.pintuer.com/css/pintuer.css">
    <link rel="stylesheet" href="css/admin.css">
    <script src="http://www.pintuer.com/js/jquery.js"></script>
    <script src="http://www.pintuer.com/js/pintuer.js"></script>
    <script src="http://www.pintuer.com/js/respond.js"></script>
    <script src="js/admin.js"></script>
    <link type="image/x-icon" href="http://www.pintuer.com/favicon.ico" rel="shortcut icon"/>
    <link href="http://www.pintuer.com/favicon.ico" rel="bookmark icon"/>
</head>

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
                    <a class="button button-little bg-main" href="#">前台首页</a>
                    <a class="button button-little bg-yellow" href="login.html">注销登录</a>
                </span>
            <ul class="nav nav-inline admin-nav">
                <li>
                    <a href="index.html" class="icon-home"> 开始</a>
                    <ul>
                        <li><a href="system.html">系统设置</a></li>
                        <li><a href="content.html">内容管理</a></li>
                        <li><a href="#">订单管理</a></li>
                        <li class="active"><a href="#">会员管理</a></li>
                        <li><a href="#">文件管理</a></li>
                        <li><a href="#">栏目管理</a></li>
                    </ul>
                </li>
                <li class="active">
                    <a href="system.html" class="icon-cog"> 系统</a>
                    <ul>
                        <li><a href="#">全局设置</a></li>
                        <li class="active"><a href="#">系统设置</a></li>
                        <li><a href="#">会员设置</a></li>
                        <li><a href="#">积分设置</a></li>
                    </ul>
                </li>
                <li>
                    <a href="content.html" class="icon-file-text"> 内容</a>
                    <ul>
                        <li><a href="#">添加内容</a></li>
                        <li class="active"><a href="#">内容管理</a></li>
                        <li><a href="#">分类设置</a></li>
                        <li><a href="#">链接管理</a></li>
                    </ul>
                </li>
                <li><a href="#" class="icon-shopping-cart"> 订单</a></li>
                <li><a href="#" class="icon-user"> 会员</a></li>
                <li><a href="#" class="icon-file"> 文件</a></li>
                <li><a href="#" class="icon-th-list"> 栏目</a></li>
            </ul>
        </div>
        <div class="admin-bread">
            <span>您好，admin，欢迎您的光临。</span>
            <ul class="bread">
                <li><a href="index.html" class="icon-home"> 开始</a></li>
                <li><a href="system.html">设置</a></li>
                <li>系统设置</li>
            </ul>
        </div>
    </div>
</div>

<div class="admin">

    <div class="tab">
        <div class="tab-head">
            <strong>系统设置</strong>
            <ul class="tab-nav">
                <li class="active"><a href="#tab-set">查看详情</a></li>

            </ul>
        </div>
        <div class="tab-body">
            <br/>
            <div class="tab-panel active" id="tab-set">
                <form method="post" class="form-x" action="system.html">
                    <div class="form-group">
                        <div class="label">
                            <label>文档详情</label>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="label">
                            <label for="sitename">网站名称</label>
                        </div>
                        <div class="field">
                            <input type="text" class="input" id="sitename" name="sitename" size="50" placeholder="网站名称"
                                   data-validate="required:请填写你网站的名称"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="label">
                            <label>内容详情</label>
                        </div>
                        <div class="field">
                            <textarea class="input" rows="5" cols="50" placeholder="请填写维护说明"
                                      data-validate="required:请填写维护说明"></textarea>
                        </div>
                    </div>
                    <div class="form-button">
                        <button class="button bg-main" type="submit">提交</button>
                    </div>
                </form>
            </div>
            <div class="tab-panel" id="tab-email">邮件设置</div>
            <div class="tab-panel" id="tab-upload">上传设置</div>
            <div class="tab-panel" id="tab-visit">访问限制</div>
        </div>
    </div>
</div>


</body>

</html>