<!DOCTYPE html>
<html lang="zh-cn">

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
		<meta name="renderer" content="webkit">
		<title>拼图后台管理-后台管理</title>
		<!--
        	作者：大火兔 1979788761@qq.com
        	时间：2015-11-17
        	描述：使用官网CSS/JS同步最新版
        -->
		<link rel="stylesheet" href="http://www.pintuer.com/css/pintuer.css">
		<link rel="stylesheet" href="css/admin.css">
		<script src="http://www.pintuer.com/js/jquery.js"></script>
		<script src="http://www.pintuer.com/js/pintuer.js"></script>

		<script src="js/admin.js"></script>
		<script type=text/javascript src=js/addNews.js></script>

		<link type="image/x-icon" href="http://www.pintuer.com/favicon.ico" rel="shortcut icon" />
		<link href="http://www.pintuer.com/favicon.ico" rel="bookmark icon" />
	</head>

	<body>
		<div class="lefter">
			<div class="logo">
				<a href="http://www.pintuer.com" target="_blank"><img src="images/logo.png" alt="后台管理系统" /></a>
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
						<li >
							<a href="content_user.jsp" class="icon-file-text">查看文章</a>
							<ul>
								<li><a href="#">添加内容</a></li>
								<li><a href="#">内容管理</a></li>
								<li><a href="#">分类设置</a></li>
								<li><a href="#">链接管理</a></li>
							</ul>
						</li>

                        <li ><a href="Authorization_user.jsp" class="icon-file">文章授权</a></li>
						<li class="active">
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

						<li>  </li>
					</ul>
				</div>
			</div>
		</div>

		<div class="admin">

			<div class="tab">
				<div class="tab-head">
					<strong>发布新文章</strong>
				</div>
				<div class="tab-body">
					<br />
					<div class="tab-body">
						<br/>
						<div class="tab-panel active" id="tab-set">



								<div class="form-group">

									<div class="field">
										<label>文章标题</label> <input type="text" class="input" id="check_head" size="50"
																   value=""/>
									</div>
									<br>
									<br>
									<br>


									<div class="field">
										<label>文章类型</label> <input type="text" class="input" id="check_type" size="50"
																   value=""/>
									</div>
									<br>
									<br>
									<br>

								</div>

								<div class="form-group">

									<div class="field">
										<label>内容详情</label>
										<textarea class="input" id="check_content" rows="20" cols="50" value=""></textarea>
									</div>


								</div>

								<div class="form-button" align="left">
									<button class="button bg-main" onclick="addNews('${username}','check_pending','content_user.jsp')">提交发布</button>
								</div>




						</div>

					</div>

				</div>
			</div>
		</div>


	</body>

</html>