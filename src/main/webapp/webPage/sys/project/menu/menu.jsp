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
	<!--
	作者：953934680@qq.com
	时间：2016-03-18
	描述：首页
-->
	<div data-options="region:'center'"
		style="padding: 5px; background: #eee;">
		<!-- 条件 -->
		<div>

			<button type="button" class="btn btn-primary"
				onclick="
				        	location.href='${path }/sys/project/menu/ExportModel.action'
				        ">下载Excel模板</button>


			<button type="button" class="btn btn-primary"
				onclick="
				         	location.href='${path }/sys/project/menu/ExportDatas.action'
				         ">导出数据到Excel</button>


			<button type="button" class="btn btn-primary"
				onclick="
			         	$('#importDatas').modal({show:true});
				">从Excel导入数据</button>

			<button type="button" class="btn btn-primary"
				onclick="location.replace(location.href)">刷新页面</button>
		</div>
		<!-- 条件  end-->

		<!-- 数据 -->
		<div style="clear: both;">
			<div class="easyui-panel" style="width: 300px; height: 200px;"
				title="菜单管理"
				data-options="iconCls:'glyphicon glyphicon-folder-open',tools:[
							{
								iconCls:'icon-add',
								handler:function(){
									
									$('#formMenuDialog').modal({show:true});
									$('#myfromMenu').panel({    
										    href:'${path }/sys/project/menu/form.action', 
										}); 
								}
							},{
								iconCls:'icon-edit',
								handler:function(){alert('edit')}
							}]">
				<ul id="tt2"></ul>
				<div id="mm" class="easyui-menu" style="width: 120px;">
					<div onclick="edit()" data-options="iconCls:'icon-add'">编辑</div>
					<div onclick="deleteData()" data-options="iconCls:'icon-remove'">删除</div>
				</div>


			</div>
		</div>
		<!-- 数据 end-->

	</div>


	<!-- 数据添加修改页面 -->
	<div id="formMenuDialog" class="modal fade" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog modal-lg" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">菜单编辑页面</h4>
				</div>
				<div id=modal_content class="modal-body">
					<!-- 菜单编辑窗口 -->
					<div id="myfromMenu" class="easyui-panel"
						style="width: 800px; height: 200px; border: 0"></div>

					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
						<button type="button" class="btn btn-primary"
							onclick="
	        		ajaxSubmit('#menuForm','${path }/sys/project/menu/save.action');
	        		">保存</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- 数据添加修改页面   end -->

	<!-- 浏览可用图标窗口 -->
	<div id="browse_iconDialog" class="modal fade" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel">
		<div class="modal-dialog modal-lg" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">可用图标浏览</h4>
				</div>
				<div id=modal_content class="modal-body">
					<iframe src="${pathSys_page }/glyphicons.html" width="100%"
						height="600px"></iframe>
				</div>
			</div>
		</div>
	</div>
	<!-- 浏览可用图标窗口  end -->


	<!-- 数据导入页面-->
	<div id="importDatas" class="modal fade" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog modal-lg" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">数据导入</h4>
				</div>
				<div id=modal_content class="modal-body">
					<!-- 菜单编辑窗口 -->
					<iframe src="${sys_project}/menu/importDatas.jsp"></iframe>
				</div>
			</div>
		</div>
	</div>
	<!-- 数据导入页面   end -->


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
		/**
		 * 删除数据
		 */
		function deleteData() {
			var node = $('#tt2').tree('getSelected');
			alert("呵呵");
			$.messager
					.confirm(
							"删除",
							"确定删除菜单：\"" + node.text + "\"吗？",
							function(btn) {
								if (btn == true) {
									$
											.ajax({
												url : '${path }/sys/project/menu/delete.action',
												type : 'post',
												dataType : 'json',
												data : {
													'menu.id' : node.id
												},
												success : function(data) {
													if (data.mess == "success") {
														location
																.replace(location);
													}
												},
												error : function() {
													$.messager.show("出错了！");
												}
											});
								}
							});
		}
		/*
		 *	编辑
		 */
		function edit() {

			var node = $('#tt2').tree('getSelected');
			$('#formMenuDialog').modal({
				show : true
			});
			$('#myfromMenu').panel(
					{
						href : '${path }/sys/project/menu/form.action?menu.id='
								+ node.id,
					});
		}

		$(function() {
			$('#tt').tree({
				url : "${path }/sys/project/menu/getmenus.action"
			});
		});

		$(function() {
			//菜单初始化
			$('#tt2').tree({
				url : '${path }/sys/project/menu/getmenus.action',
				animate : true,
				onContextMenu : function(e, node) {
					e.preventDefault();
					// 查找节点
					$('#tt2').tree('select', node.target);
					// 显示快捷菜单
					$('#mm').menu('show', {
						left : e.pageX,
						top : e.pageY
					});
				}
			});

		});

		/*注册菜单数据提交事件
		  id:表单的id
		  url：提交的地址
		 */
		function ajaxSubmit(id, url) {

			$(id).form('submit', {
				url : url,
				onSubmit : function() {
					var isValid = $(this).form('validate');
					if (isValid) {
						$.messager.progress(); // 显示进度条
					}
					return isValid; // 返回false终止表单提交
				},
				success : function(data) {
					var data = eval('(' + data + ')');
					if ("success" == data.mess) {
						$('#addDialog').dialog('close');
						$('#formMenuDialog').modal({
							show : false
						});
						//$.messager.progress('close');	// 如果提交成功则隐藏进度条
						location.replace(location);
					}
				}
			});
		}
	</script>

</body>
</html>