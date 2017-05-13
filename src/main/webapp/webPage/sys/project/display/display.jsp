
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
<link rel="stylesheet" type="text/css"
	href="${pathSys_page}/css/bootstrap.min.css?v=3.3.5" rel="stylesheet">
<link rel="stylesheet" type="text/css"
	href="${pathSys_page}/css/font-awesome.min.css?v=4.4.0"
	rel="stylesheet">
<link rel="stylesheet" type="text/css"
	href="${pathSys_page}/css/animate.min.css" rel="stylesheet">
<link rel="stylesheet" type="text/css"
	href="${pathSys_page}/css/style.min.css?v=4.0.0" rel="stylesheet">
<link rel="stylesheet" type="text/css"
	href="${pathResource}/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="${pathResource}/easyui/themes/icon.css">
<!--easyui import end-->

<script src="${pathSys_page}/js/jquery.min.js?v=2.1.4"></script>
<script type="text/javascript"
	src="${pathResource}/easyui/jquery.min.js"></script>
<script src="${pathSys_page}/js/bootstrap.min.js?v=3.3.5"></script>
<script src="${pathSys_page}/js/plugins/metisMenu/jquery.metisMenu.js"></script>
<script
	src="${pathSys_page}/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>
<script type="text/javascript" src="${pathSys_page}/js/contabs.min.js"></script>
<script src="${pathSys_page}/js/plugins/pace/pace.min.js"></script>
<script type="text/javascript"
	src="${pathResource}/easyui/jquery.easyui.min.js"></script>

<!-- 引入分页插件样式-->
<link rel="stylesheet" type="text/css"
	href="${pathResource_tools}/pageNavigator/myPagination.css">
<script src="${pathResource_tools}/pageNavigator/jquery.myPagination.js"></script>
<!-- 引入分页插件  end-->

</head>

<body>
	<!-- 查询条件------------------------------------------------------- 开始 ---------------->
	<div style="margin-top: 10px;">
		<form class="formCondition" id="search_condition"
			action="${path}/sys/project/historicalRelic/dataForPage.action">
			<!--查询条件-->
			<!-- 查询条件-------------------------------------------------------------开始------------------ -->
			<div class="rows">
				<div class="panel panel-default panel-primary col-md-12">
					<div class="panel-heading">查询条件</div>
					<div class="panel-body">
						<div>
							<input type="hidden" id="pgNo" name="pgNo" value="1" />
							<div class="form-group col-sm-4 ">
								<label for="firstname" class="col-sm-4 control-label">陈列名称</label>
								<div class="col-sm-6">
									<input type="text" class="form-control"
										name="historicalRelic.title" value="${historicalRelicModel.historicalRelic.title}"
										placeholder="请输入">
								</div>
							</div>
							<div class="form-group col-sm-4 ">
								<label for="firstname" class="col-sm-4 control-label">展览地点</label>
								<div class="col-sm-6">
									<input type="text" class="form-control"
										name="historicalRelic.showAddress" value="${historicalRelicModel.historicalRelic.showAddress}"
										placeholder="请输入">
								</div>
							</div>
							<div class="form-group col-sm-4 ">
								<label for="firstname" class="col-sm-4 control-label">陈列介绍</label>
								<div class="col-sm-6">
									<input type="text" class="form-control"
										name="historicalRelic.intro" value="${historicalRelicModel.historicalRelic.intro}"
										placeholder="请输入">
								</div>
							</div>
							<div class="form-group col-sm-4 ">
								<label for="firstname" class="col-sm-4 control-label">文物类型ID</label>
								<div class="col-sm-6">
									<input type="text" class="form-control"
										name="historicalRelic.typeID" value="${historicalRelicModel.historicalRelic.typeID}"
										placeholder="请输入">
								</div>
							</div>
							<div class="form-group col-sm-4 ">
								<label for="firstname" class="col-sm-4 control-label">展览时间</label>
								<div class="col-sm-6">
									<input type="text" class="form-control"
										name="historicalRelic.showTime" value="${historicalRelicModel.historicalRelic.showTime}"
										placeholder="请输入">
								</div>
							</div>
							<div class="form-group col-sm-4 ">
								<label for="firstname" class="col-sm-4 control-label">文物类型</label>
								<div class="col-sm-6">
									<input type="text" class="form-control"
										name="historicalRelic.type" value="${historicalRelicModel.historicalRelic.type}"
										placeholder="请输入">
								</div>
							</div>
							<div class="form-group col-sm-4 ">
								<label for="firstname" class="col-sm-4 control-label">外形描述</label>
								<div class="col-sm-6">
									<input type="text" class="form-control"
										name="historicalRelic.outlineDescribe" value="${historicalRelicModel.historicalRelic.outlineDescribe}"
										placeholder="请输入">
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- 查询条件-------------------------------------------------------------结束------------------ -->

			<!--查询条件-->

			<!--操作按钮 1-->
			<div style="margin: 10px 0px; clear: both;" class="col-sm-12">
				<button type="button" class="btn btn-default"
					onclick="location.replace(location.href)">刷新</button>
				<button type="button" class="btn btn-default" data-toggle="modal"
					data-target="#myModal"
					onclick="location.href='${pathSys}/project/historicalRelic/form.action?historicalRelic.culturalRelicId=2'">添加</button>
				<button type="button" class="btn btn-primary"
					onclick="$('#search_condition #pgNo').val(1);$('#search_condition').submit();">查询</button>
			</div>
			<!--操作按钮 1-->
			<div style="margin: 10px 0px; clear: both;" class="col-sm-12">
				<button type="button" class="btn btn-primary"
					onclick="
			         	location.href='${path}/sys/project/historicalRelic/ExportModel.action'
			         ">下载Excel模板</button>

				<button type="button" class="btn btn-primary"
					onclick="
			         	location.href='${path}/sys/project/historicalRelic/ExportDatas.action'
			         ">导出数据到Excel</button>

				<button type="button" class="btn btn-primary"
					onclick="$('#importDatas').modal({show:true})">从Excel导入数据</button>

				<button type="button" class="btn btn-primary"
					onclick="deleteDatas()">批量删除数据</button>
			</div>
			<!--操作按钮 end-->
		</form>
	</div>
	<!-- 查询条件------------------------------------------------------- 结束 ---------------->


	<!-- 数据-------------------------------------------------------------开始------------------ -->
	<div class="rows">
		<div class="panel panel-default panel-primary col-md-12">
			<div class="panel-heading">陈列数据</div>
			<div class="panel-body">

				<!-- 数据内容开始了 ----------------------------------------->
				<form id="datasForm"
					action="${path}/sys/project/historicalRelic/delete.action" method="post">
					<table class="table table-striped">
						<thead>
							<tr>
								<th><a
									onclick="
										var checkedFlag = $('#datasForm [name=\'ids\']').prop('checked');
										$('#datasForm [name=\'ids\']').prop('checked',!checkedFlag);
									">全选</a></th>
								<th>陈列名称</th>
								<th>展览地点</th>
								<th>展览时间</th>
								<th>陈列类型</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${page.item}" var="historicalRelic">
								<tr>
									<td><input type="checkbox" name="ids" value="${historicalRelic.id}" />
										<a
										href="${path}/sys/project/historicalRelic/delete.action?ids=${historicalRelic.id}&historicalRelic.culturalRelicId=2">删除</a>
										<a href="${path}/sys/project/historicalRelic/form.action?historicalRelic.id=${historicalRelic.id}&historicalRelic.culturalRelicId=2">编辑</a>
									</td>
									<td>${historicalRelic.title}</td>
									<td>${historicalRelic.showAddress}</td>
									<td>${historicalRelic.showTime}</td>
									<td>${historicalRelic.typeID}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</form>
				<!-- 数据内容结束了 ------------------------------------------------------------------------->

			</div>
		</div>
	</div>
	<!-- 数据-------------------------------------------------------------结束------------------ -->

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
					<!-- 数据导入主体 -->
					<iframe src="${path_page}/sys/project/historicalRelic/importDatas.jsp"></iframe>
					<!-- 数据导入主体  end-->
				</div>
			</div>
		</div>
	</div>
	<!-- 数据导入页面   end -->

	<!-- 分页开始了--------------------------------------------------------------------------------->
	<div style="clear: both;">
		<div style="height: 30px"></div>
		<script>
			$(function() {
				var totalpg = 1;
				if('0'!='${page.totalPg}'){
					totalpg=${page.totalPg};
				}
				$("#pagination").myPagination(
						{
							currPage : ${page.pgNo},
							pageCount : totalpg,
							pageSize : ${page.pgSize},
							cssStyle : 'meneame'
						});
				$("#pagination a").click(function() {
					var pgNo = $(this).attr("title");
					$("#search_condition #pgNo").val(pgNo);
					$("#search_condition").submit();
				});
				$("#pagination input").keydown(function(k) {
					if(k.which==13){//如果输入了回车键则跳转到输入页面
						$("#search_condition #pgNo").val($('#pagination input').val());
						$("#search_condition").submit();
					}
				});
			});
		</script>
		<div id="pagination"></div>
	</div>
	<!-- 分页结束了 --------------------------------------------------------------------------------->

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
						$('#formDialog').modal({
							show : false
						});
						$.messager.progress('close'); // 如果提交成功则隐藏进度条
						location.reload();
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