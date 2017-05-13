<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/include/taglib.jsp"%>
<!doctype html>
<html>

<head>
  <meta charset="utf-8"/>
  <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
  <title>后台管理系统</title>
  <meta name="description" content="这是一个 index 页面"/>
  <meta name="keywords" content="index"/>
  <meta name="viewport" content="width=device-width, initial-scale=1"/>
  <meta name="renderer" content="webkit"/>
  <meta http-equiv="Cache-Control" content="no-siteapp" />
  <link rel="icon" type="image/png" href="${iconUrl }">
  <link rel="apple-touch-icon-precomposed" href="${pathResource_tools }/assets/i/app-icon72x72@2x.png">
  <meta name="apple-mobile-web-app-title" content="Amaze UI" />
  <link rel="stylesheet" href="${pathResource_tools }/assets/css/amazeui.min.css" />
  <link rel="stylesheet" href="${pathResource_tools }/assets/css/admin.css">
  <link rel="stylesheet" href="${pathResource_tools }/assets/css/app.css">
  
  <style>
  /*S-登录框样式*/	
  .am-form-group input{
  	height:50px;
  }
  /*E-登录框样式*/	
  </style>
</head>

<body data-type="login">

  <div class="am-g myapp-login">
	<div class="myapp-login-logo-block  tpl-login-max">
		<div class="myapp-login-logo-text">
			<div class="myapp-login-logo-text">
				<i class="am-icon-skyatlas"></i>博物馆后台管理系统	
			</div>
		</div>

		<div class="login-font">
			<i>登录界面 </i>
		</div>
		<div class="am-u-sm-10 login-am-center">
			<form class="am-form" action="${pathSys }/project/authUser/login.action" method="post">
					<div class="am-form-group">
						<input name="authUser.userName" type="text" class="" id="doc-ipt-email-1" placeholder="输入用户名">
					</div>
					<div class="am-form-group">
						<input name="authUser.userPassword" type="password" class="" id="doc-ipt-pwd-1" placeholder="输入密码">
					</div>
					<p><button type="submit" class="am-btn am-btn-default">登录</button></p>
					<span style="color: #f00;">${errorMess }</span>
			</form>
		</div>
	</div>
</div>

  <script src="${pathResource_tools }/assets/js/jquery-2.1.1.js"></script>
  <script src="${pathResource_tools }/assets/js/amazeui.min.js"></script>
  <script src="assets/js/app.js"></script>
  
</body>
</html>