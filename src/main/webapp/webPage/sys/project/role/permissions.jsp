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

<style type="text/css">
.my_lable {
	width: 30px;
	text-align: left;
	display: inline-block;
}

.tree-file {
	background-image: url() !important;
}
</style>

</head>
<body id="cc" class="easyui-layout">
	<!--
	作者：953934680@qq.com
	时间：2016-03-18
	描述：首页
-->
	
	<div data-options="region:'center'"
		style="padding: 5px; background: #eee;">
		
		<div>
			<input id=roleId type="hidden" value="${requestScope.role.id }"/>
			${requestScope.role.roleName } 》》》》 权限资源管理
		</div>
		
		<!-- 数据 -->
		<div style="clear: both;">
			<div class="easyui-panel" style="width: 95%; height: 350px;"
				title="权限资源"
				data-options="iconCls:'glyphicon glyphicon-folder-open'">
				<ul id="tt2"></ul>
				<div id="mm" class="easyui-menu" style="width: 120px;">
					<div onclick="permissionsDetail()" data-options="iconCls:'icon-edit'">修改资源权限</div>
				</div>
			</div>
			<div>
				<button onclick="submitPermissions()" type="button" class="btn btn-primary">提交</button>
			</div>
		</div>
		<!-- 数据 end-->

	</div>


	<!-- 数据添加修改页面 -->
	<div id="permissionDetailDialog" class="modal fade" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog modal-lg" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">权限管理</h4>
				</div>
				<div id=modal_content class="modal-body">
					<iframe id="permissionDetailFrame" src="" width="100%" height="350px"></iframe>
				</div>
			</div>
		</div>
	</div>
	<!-- 数据添加修改页面   end -->

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
		function permissionsDetail() {
			var node = $('#tt2').tree('getSelected');
			$('#permissionDetailDialog').modal({
				show : true
			});
			alert(node.id+':'+node.text);
			var href =  '${pathSys}/project/role/permissions.action?role.id=${role.id }&role.roleName=${role.roleName}'
				+'&menu.id='+node.id+'&menu.text='+node.text+'&request=permissionsDetailPage';
			$('#permissionDetailFrame').attr('src',href);
		}


		$(function() {
			//菜单初始化
			$('#tt2').tree({
				url : '${path }/sys/project/menu/getmenus.action',
				animate : true,
				checkbox:true,
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
		
		/*
			提交权限
		*/
		function submitPermissions(){
			var selNodes=$('#tt2').tree('getChecked');
			var indNodes = $('#tt2').tree('getChecked','indeterminate');
			for(var i in indNodes){
				selNodes.push(indNodes[i]);
			}
			alert(selNodes.length);
		}
	</script>

</body>
</html>