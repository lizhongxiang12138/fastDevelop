<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/include/taglib.jsp"%>
<html class="no-js">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="description" content="">
<meta name="keywords" content="">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>管理员管理界面</title>

<!-- Set render engine for 360 browser -->
<meta name="renderer" content="webkit">

<!-- No Baidu Siteapp-->
<meta http-equiv="Cache-Control" content="no-siteapp" />

<link rel="icon" type="image/png"
	href="/YNPT-store/assets/i/favicon.png">

<!-- Add to homescreen for Chrome on Android -->
<meta name="mobile-web-app-capable" content="yes">
<link rel="icon" sizes="192x192"
	href="/YNPT-store/assets/i/app-icon72x72@2x.png">

<!-- Add to homescreen for Safari on iOS -->
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="apple-mobile-web-app-title" content="Amaze UI" />
<link rel="apple-touch-icon-precomposed"
	href="/YNPT-store/assets/i/app-icon72x72@2x.png">

<!-- Tile icon for Win8 (144x144 + tile color) -->
<meta name="msapplication-TileImage"
	content="/YNPT-store/assets/i/app-icon72x72@2x.png">
<meta name="msapplication-TileColor" content="#0e90d2">


<link rel="shortcut icon" href="favicon.ico">
<link href="${pathSys_page}/css/bootstrap.min.css?v=3.3.5"
	rel="stylesheet">
<link href="${pathSys_page}/css/font-awesome.min.css?v=4.4.0"
	rel="stylesheet">
<link href="${pathSys_page}/css/animate.min.css" rel="stylesheet">
<link href="${pathSys_page}/css/style.min.css?v=4.0.0" rel="stylesheet">
<link rel="stylesheet" type="text/css"
	href="${pathResource }/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="${pathResource }/easyui/themes/icon.css">
<!--easyui import end-->

<link rel="stylesheet" href="${pathResource }/css/sys/myEasyUI.css">

</head>
<body id="cc" class="easyui-layout">
	
	<h1>测试页面</h1>

	<!--<![endif]-->
	<!--[if lte IE 8 ]>
		<script src="http://libs.baidu.com/jquery/1.11.3/jquery.min.js"></script>
		<script src="http://cdn.staticfile.org/modernizr/2.8.3/modernizr.js"></script>
		<script src="/YNPT-store/assets/js/amazeui.ie8polyfill.min.js"></script>
	<![endif]-->
	<script type="text/javascript"
		src="${pathResource }/easyui/jquery.min.js"></script>
	<script src="${pathSys_page}/js/bootstrap.min.js?v=3.3.5"></script>
	<script src="${pathSys_page}/js/plugins/metisMenu/jquery.metisMenu.js"></script>
	<script
		src="${pathSys_page}/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>
	<script src="${pathSys_page}/js/plugins/layer/layer.min.js"></script>
	<script src="${pathSys_page}/js/hplus.min.js?v=4.0.0"></script>
	<script type="text/javascript" src="${pathSys_page}/js/contabs.min.js"></script>
	<script src="${pathSys_page}/js/plugins/pace/pace.min.js"></script>
	<script type="text/javascript"
		src="${pathResource }/easyui/jquery.easyui.min.js"></script>

	<script>
	var dataStr= {users:[]};
	dataStr.users.push({userName:'111',userPassword:'111'});
	dataStr.users.push({userName:'222',userPassword:'222'});
	alert(JSON.stringify(dataStr));
	
	$.ajax({
		url:'http://127.0.0.1:8089/fastDevelop/sys/project/test/test.action',
		dataType:'json',
		type:'post',
		contentType:'application/json;charset=utf-8',
		data:JSON.stringify(dataStr),
		success:function(data){
			
		},
		error:function(){
			
		}
	});
		
	</script>

</body>
</html>