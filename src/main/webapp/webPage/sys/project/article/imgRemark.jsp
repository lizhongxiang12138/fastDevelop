
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/include/taglib.jsp"%>
<html>

<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="renderer" content="webkit">
<meta http-equiv="Cache-Control" content="no-siteapp" />
<title>H+ 后台主题UI框架 - 主页</title>

<meta name="keywords" content="H+后台主题,后台bootstrap框架,会员中心主题,后台HTML,响应式后台">
<meta name="description"
	content="H+是一个完全响应式，基于Bootstrap3最新版本开发的扁平化主题，她采用了主流的左右两栏式布局，使用了Html5+CSS3等现代技术">

<!--[if lt IE 8]>
    <meta http-equiv="refresh" content="0;ie.html" />
    <![endif]-->

<link rel="shortcut icon" href="favicon.ico">
<link rel="stylesheet" type="text/css" href="${pathSys_page}/css/bootstrap.min.css?v=3.3.5" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="${pathSys_page}/css/font-awesome.min.css?v=4.4.0" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="${pathSys_page}/css/animate.min.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="${pathSys_page}/css/style.min.css?v=4.0.0" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="${pathResource}/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pathResource}/easyui/themes/icon.css">
<!--easyui import end-->

<!--引入CSS-->
<link rel="stylesheet" type="text/css" href="${pathResource_tools }/webuploader/webuploader.css">


<script src="${pathSys_page}/js/jquery.min.js?v=2.1.4"></script>
<script type="text/javascript" src="${pathResource}/easyui/jquery.min.js"></script>
<script src="${pathSys_page}/js/bootstrap.min.js?v=3.3.5"></script>
<script src="${pathSys_page}/js/plugins/metisMenu/jquery.metisMenu.js"></script>
<script src="${pathSys_page}/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>
<script src="${pathSys_page}/js/plugins/layer/layer.min.js"></script>
<script src="${pathSys_page}/js/hplus.min.js?v=4.0.0"></script>
<script type="text/javascript" src="${pathSys_page}/js/contabs.min.js"></script>
<script src="${pathSys_page}/js/plugins/pace/pace.min.js"></script>
<script type="text/javascript" src="${pathResource}/easyui/jquery.easyui.min.js"></script>

<!-- 引入分页插件样式-->
<link rel="stylesheet" type="text/css"
	href="${pathResource_tools}/pageNavigator/myPagination.css">
<script src="${pathResource_tools}/pageNavigator/jquery.myPagination.js"></script>
<!-- 引入分页插件  end-->

</head>

<body>
	<script type="text/javascript" src="${path}/resource/js/validate_js/validate.js"></script>

	<form:form id="dataForm" method="POST" modelAttribute="documentModel" class="form-horizontal form-inline" role="form">
		<input type="hidden" name="document.id" value="${documentModel.document.id}" />
		<div style="width:100%"> 
			<label for="firstname" style="width: 15%;float:left" class="control-label">图片描述：</label>
			<div style="width: 80%;float:left;">
				<textarea type="text" class="form-control easyui-validatebox"
					name="document.remark" data-options="required:true"
					validType="data_baseType" style="width:100%;height: 100px;"
					>${documentModel.document.remark}</textarea>
			</div>
		</div>
	</form:form>
	
	<p style="text-align:center;">
		<button type="button" onclick="ajaxSubmit('#dataForm','${path}/sys/project/article/updateImgRemark.action');">提交</button>
	</p>

	<!--<![endif]-->
<!--[if lte IE 8 ]>
<script src="http://libs.baidu.com/jquery/1.11.3/jquery.min.js"></script>
<script src="http://cdn.staticfile.org/modernizr/2.8.3/modernizr.js"></script>
<script src="/YNPT-store/assets/js/amazeui.ie8polyfill.min.js"></script>
<![endif]-->

	<script>
		/*编辑数据表单提交事件
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
						//$('#addDialog').dialog('close');
						$.messager.progress('close'); // 如果提交成功则隐藏进度条
						parent.closeDialog('imgRemark');
					}
				}
			});
		}
		/*
			批量删除事件
		 */
		function deleteDatas() {
			var ids = $("#datasForm [name='ids']:checked");
			alert(ids.length);
			if (ids.length == 0) {
				alert("至少选择一条数据");
				return;
			}
			$("#datasForm").submit();
		}
	</script>

</body>

</html>