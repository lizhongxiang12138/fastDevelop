<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/include/taglib.jsp"%>

<!doctype html>
<html>

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>博物馆后台管理系统</title>
<meta name="description" content="这是一个 index 页面">
<meta name="keywords" content="index">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="renderer" content="webkit">
<meta http-equiv="Cache-Control" content="no-siteapp" />
<link rel="icon" type="image/png" href="${iconUrl }">
<link rel="apple-touch-icon-precomposed" href="assets/i/app-icon72x72@2x.png">
<meta name="apple-mobile-web-app-title" content="Amaze UI" />
<link href="${pathSys_page}/css/bootstrap.min.css?v=3.3.5" rel="stylesheet">
<link rel="stylesheet" href="assets/css/amazeui.min.css" />
<link rel="stylesheet" href="assets/css/admin.css">
<link rel="stylesheet" href="assets/css/app.css">

<script src="assets/js/jquery-2.1.1.js"></script>
<script src="assets/js/echarts.min.js"></script>


<!-- easyui -->
<link rel="stylesheet" type="text/css" href="${pathResource }/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pathResource }/easyui/themes/icon.css">
<!-- easyui -->

<link rel="stylesheet" href="${pathResource }/css/sys/myEasyUI.css">

<style type="text/css">
	/* S-logo 标题 */
	.logo-text{color:rgba(0, 0, 0, 0.42);font-size: 3.4rem;font-weight: 700;font-family:YaHei;}
	.tabs{border-color: #1C1F23;}
	/* E-logo 标题 */
	
	/*  S-右侧选项卡  */
	.tabs-header,.tabs-wrap,.tabs-scroller-left, .tabs-scroller-right, .tabs-tool, .tabs, .tabs-panels, .tabs li a.tabs-inner, .tabs li.tabs-selected a.tabs-inner, .tabs-header-bottom .tabs li.tabs-selected a.tabs-inner, .tabs-header-left .tabs li.tabs-selected a.tabs-inner, .tabs-header-right .tabs li.tabs-selected a.tabs-inner {
   		border-color: rgba(0, 0, 0, 0.42);
	}
	.tabs-header, .tabs-tool {background-color: #EEE;}
	/*  E-右侧选项卡  */
</style>

</head>

<body data-type="index">


	<header class="am-topbar am-topbar-inverse admin-header">

		<div class="am-collapse am-topbar-collapse" id="topbar-collapse">
		<div class="am-topbar-brand">
			<a href="javascript:;" class="tpl-logo"> 
				<img src="${pathResource}/img/logo.png" alt="">
			</a>
		</div>
		<span class="logo-text">博物馆后台管理系统</span>

			<ul
				class="am-nav am-nav-pills am-topbar-nav am-topbar-right admin-header-list tpl-header-list">
				
				<li class="am-dropdown" data-am-dropdown data-am-dropdown-toggle>
					<a class="am-dropdown-toggle tpl-header-list-link"
					href="javascript:;"> <span class="am-icon-comment-o"></span> 消息
						<span class="am-badge tpl-badge-danger am-round">9</span></span>
				</a>
					<ul class="am-dropdown-content tpl-dropdown-content">
						<li class="tpl-dropdown-content-external">
							<h3>
								你有 <span class="tpl-color-danger">9</span> 条新消息
							</h3>
							<a href="###">全部</a>
						</li>
						<li><a href="#" class="tpl-dropdown-content-message"> <span
								class="tpl-dropdown-content-photo"> <img
									src="assets/img/user02.png" alt="">
							</span> <span class="tpl-dropdown-content-subject"> <span
									class="tpl-dropdown-content-from"> 禁言小张 </span> <span
									class="tpl-dropdown-content-time">10分钟前 </span>
							</span> <span class="tpl-dropdown-content-font"> Amaze UI 的诞生，依托于
									GitHub 及其他技术社区上一些优秀的资源；Amaze UI 的成长，则离不开用户的支持。 </span>
						</a> <a href="#" class="tpl-dropdown-content-message"> <span
								class="tpl-dropdown-content-photo"> <img
									src="assets/img/user03.png" alt="">
							</span> <span class="tpl-dropdown-content-subject"> <span
									class="tpl-dropdown-content-from"> Steam </span> <span
									class="tpl-dropdown-content-time">18分钟前</span>
							</span> <span class="tpl-dropdown-content-font"> 为了能最准确的传达所描述的问题，
									建议你在反馈时附上演示，方便我们理解。 </span>
						</a></li>

					</ul>
				</li>
				
				<li class="am-hide-sm-only"><a href="javascript:;"
					id="admin-fullscreen" class="tpl-header-list-link"><span
						class="am-icon-arrows-alt"></span> <span class="admin-fullText">开启全屏</span></a></li>

				<li class="am-dropdown" data-am-dropdown data-am-dropdown-toggle>
					<a class="am-dropdown-toggle tpl-header-list-link"
					href="javascript:;"> <span class="tpl-header-list-user-nick">欢迎：${sessionScope.loginUser.userName }</span><span
						class="tpl-header-list-user-ico"> <img
							src="assets/img/user01.png"></span>
				</a>
					<ul class="am-dropdown-content">
						<li><a href="#"><span class="am-icon-bell-o"></span> 资料</a></li>
						<li><a href="#"><span class="am-icon-cog"></span> 设置</a></li>
						<li><a href="${pathSys }/project/authUser/logout.action"><span class="am-icon-power-off"></span>
								退出</a></li>
					</ul>
				</li>
				<li><a href="###" class="tpl-header-list-link"><span
						class="am-icon-sign-out tpl-header-list-ico-out-size"></span></a></li>
			</ul>
		</div>
	</header>







	<div class="tpl-page-container tpl-page-header-fixed" style="height: 85%">


		<div class="tpl-left-nav tpl-left-nav-hover">
			<div class="tpl-left-nav-title">菜单</div>
			<div class="tpl-left-nav-list">
				<!-- 菜单 -->
				<ul id="menu"></ul>
				<!-- 菜单end -->
			</div>
		</div>





		<div class="tpl-content-wrapper" style="height: 100%" >
			<div id="contentTabs" class="easyui-tabs" style="width:100%;height: 100%">   
		      	<div title="首页" style="padding:20px;display:none;">   
			        <img src="${pathResource }/img/welcome.jpg" style="width:100%;height:100%"/>   
			    </div>
			</div>  
		</div>

	</div>


	
	<script src="assets/js/amazeui.min.js"></script>
	<script type="text/javascript" src="${pathResource }/easyui/jquery.easyui.min.js"></script>
    
    <script>
    	/**
    		添加内容选项卡
    	*/
    	function addContentTab(menuNode){
    		$("#contentTabs").tabs(
    			'add',{
    				title:menuNode.text,
    				content:'<div style="width: 100%;height: 99%">'
			    				+'<iframe src="${path }'+menuNode.url+'" style="width: 100%;height: 99%"></iframe>'
				   			+'</div>',
    				closable:true
    			}		
    		);
    	}
		$(function() {
			//菜单初始化
			$('#menu').tree({
				url : '${path }/sys/project/authUser/getmenus.action',
				animate : true,
				onClick:function(node){
					if(node.url!="#"){
						addContentTab(node);
					}
				}
			});

		});	
	</script>

</body>
</html>