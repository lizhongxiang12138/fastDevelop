
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
<script src="${pathSys_page}/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>
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
			action="${path}/sys/project/document/dataForPage.action">
			<!--查询条件-->
			<div>
				<input type="hidden" id="pgNo" name="pgNo" value="1" />
			</div>
			<!--查询条件-->

			<!--操作按钮 1-->
			<div style="margin: 10px 0px; clear: both;" class="col-sm-12">
				<button type="button" class="btn btn-default"
					onclick="location.replace(location.href)">刷新</button>
				<button type="button" class="btn btn-default" data-toggle="modal"
					data-target="#myModal"
					onclick="
			         		$('#formDialog').modal({show:true});
							$('#formInit').panel({    
								    href:'${path}/sys/project/dataType/form.action', 
								});
			         	">添加</button>
				<button type="button" class="btn btn-default" data-toggle="modal"
					data-target="#myModal"
					onclick="$('input[id=documentFile]').click();">上传文件</button>
				<button type="button" class="btn btn-primary"
					onclick="$('#search_condition #pgNo').val(1);$('#search_condition').submit();">查询</button>
				<br />
				<div class="panel">
					<div id="progressUpload" class="progress" style="height: 5px;">
						<div class="progress-bar" role="progressbar" aria-valuenow="60"
							aria-valuemin="0" aria-valuemax="100" style="width: 0%;">
							<span class="sr-only"></span>
						</div>
					</div>
				</div>
				<input id="documentFile" type="file" multiple name="documentFile"
					style="display: none" />
				<!-- 文件上传插件 -->
				<script type="text/javascript"
					src="${pathResource}/tools/jquery-fileupload/jquery.ui.widget.js"></script>
				<script type="text/javascript"
					src="${pathResource}/tools/jquery-fileupload/jquery.fileupload.js"></script>
				<!-- 文件上传插件 end-->
				<script type="text/javascript">
					$(function() {
						$("#documentFile")
								.fileupload(
										{
											url : "${path}/sys/project/document/save.action",
											sequentialUploads : true,
											dataType : "json",
											progressall : function(e, data) {
												var progress = parseInt(
														data.loaded
																/ data.total
																* 100, 10);
												$(
														"#progressUpload .progress-bar")
														.css('width',
																progress + '%');
											},
											done : function(e, data) {
												var d = data.result;
												if (d.mess == "success") {
													$(
															"#progressUpload .progress-bar")
															.css('width', '0%');
													location
															.replace(location.href);
												} else {
													alert(d.mess);
													$(
															"#progressUpload .progress-bar")
															.css('width', '0%');
												}
											}
										});
					});
				</script>
			</div>
			<!--操作按钮 1-->
			<div style="margin: 10px 0px; clear: both;" class="col-sm-12">
				<button type="button" class="btn btn-primary"
					onclick="
			         	location.href='${path}/sys/project/document/ExportModel.action'
			         ">下载Excel模板</button>

				<button type="button" class="btn btn-primary"
					onclick="
			         	location.href='${path}/sys/project/document/ExportDatas.action'
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

	<!-- 表单编辑对话框 ---------------------------------------------------开始-------------------->
	<div id="formDialog" class="modal fade" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" style="overflow: hidden;">
		<div class="modal-dialog modal-lg" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">菜单编辑页面</h4>
				</div>
				<div id=modal_content class="modal-body" style="overflow: hidden;">
					<!-- 菜单编辑窗口 -->
					<div id="formInit" class="easyui-panel"
						style="width: 800px; height: 200px; border: 0"></div>

					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
						<button type="button" class="btn btn-primary"
							onclick="
	        		ajaxSubmit('#dataForm','${path}/sys/project/document/save.action');
	        		">保存</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- 表单编辑对话框 ---------------------------------------------------结束-------------------->

	<!-- 数据-------------------------------------------------------------开始------------------ -->
	<div class="rows">
		<div class="panel panel-default panel-primary col-md-12">
			<div class="panel-heading">角色数据</div>
			<div class="panel-body">

				<!-- 数据内容开始了 ----------------------------------------->
				<form id="datasForm"
					action="${path}/sys/project/document/delete.action" method="post">
					<table class="table table-striped">
						<thead>
							<tr>
								<th><a
									onclick="
										var checkedFlag = $('#datasForm [name=\'ids\']').prop('checked');
										$('#datasForm [name=\'ids\']').prop('checked',!checkedFlag);
									">全选</a></th>
								<th>业务名称</th>
								<th>最后浏览时间</th>
								<th>更新时间</th>
								<th>文件描述</th>
								<th>最后浏览用户</th>
								<th>最后浏览用户ID</th>
								<th>业务ID</th>
								<th>创建时间</th>
								<th>文件ID</th>
								<th>创建用户</th>
								<th>文件路径</th>
								<th>文件相对路径</th>
								<th>文档说明</th>
								<th>文档标题</th>
								<th>浏览人数统计</th>
								<th>创建用户ID</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${page.item}" var="document">
								<tr>
									<td><input type="checkbox" name="ids"
										value="${document.id}" /> <a
										href="${path}/sys/project/document/delete.action?ids=${document.id}">删除</a>
										<a href="#"
										onclick="
		        			         		$('#formDialog').modal({show:true});
		        							$('#formInit').panel({    
		        								    href:'${path}/sys/project/document/form.action?document.id=${document.id}', 
		        								});
	                        			">编辑</a>
									</td>
									<td>${document.serviceName}</td>
									<td>${document.lastBrowseTime}</td>
									<td>${document.updateTime}</td>
									<td>${document.fileDescribe}</td>
									<td>${document.lastBrowseUser}</td>
									<td>${document.lastBrowseUserID}</td>
									<td>${document.serviceID}</td>
									<td>${document.createTime}</td>
									<td>${document.id}</td>
									<td>${document.createUser}</td>
									<td>${document.filePath}</td>
									<td>${document.fileRelativePath}</td>
									<td>${document.documentDeclare}</td>
									<td>${document.documentTitle}</td>
									<td>${document.browseCount}</td>
									<td>${document.createUserID}</td>
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
					<iframe src="${path_page}/sys/project/document/importDatas.jsp"></iframe>
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
				var totalpg = 0;
				if('0'!='${page.totalPg}'){
					totalpg=${page.totalPg};
				}
				$("#pagination").myPagination(
						{
							currPage : ${page.pgNo},
							pageCount : totalpg,
							pageSize : ${page.pgSize},
							cssStyle : 'meneame',
							callback :'paginCallback'
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