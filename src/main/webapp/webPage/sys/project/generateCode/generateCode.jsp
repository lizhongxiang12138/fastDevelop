
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
<script type="text/javascript" src="${pathResource}/easyui/jquery.min.js"></script>
<script src="${pathSys_page}/js/bootstrap.min.js?v=3.3.5"></script>
<script src="${pathSys_page}/js/plugins/metisMenu/jquery.metisMenu.js"></script>
<script
	src="${pathSys_page}/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>
<script src="${pathSys_page}/js/plugins/layer/layer.min.js"></script>
<script src="${pathSys_page}/js/hplus.min.js?v=4.0.0"></script>
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
			action="${path}/sys/project/generateCode/dataForPage.action">
			<!--查询条件-->
			<div>
				<input type="hidden" id="pgNo" name="pgNo" value="1" />
				<div class="form-group" class="col-sm-4 ">
					<label for="firstname" class="col-sm-1 control-label">类全名</label>
					<div class="col-sm-2">
						<input type="text" class="form-control"
							name="generateCode.className" value="${generateCodeModel.generateCode.className}"
							placeholder="请输入">
					</div>
				</div>
				<div class="form-group" class="col-sm-4 ">
					<label for="firstname" class="col-sm-1 control-label">模块名称</label>
					<div class="col-sm-2">
						<input type="text" class="form-control"
							name="generateCode.moduleName" value="${generateCodeModel.generateCode.moduleName}"
							placeholder="请输入">
					</div>
				</div>
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
								    href:'${path}/sys/project/generateCode/form.action', 
								});
			         	">添加</button>
				<button type="button" class="btn btn-primary"
					onclick="$('#search_condition #pgNo').val(1);$('#search_condition').submit();">查询</button>
			</div>
			<!--操作按钮 1-->
			<div style="margin: 10px 0px; clear: both;" class="col-sm-12">
				<button type="button" class="btn btn-primary"
					onclick="
			         	location.href='${path}/sys/project/generateCode/ExportModel.action'
			         ">下载Excel模板</button>

				<button type="button" class="btn btn-primary"
					onclick="
			         	location.href='${path}/sys/project/generateCode/ExportDatas.action'
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
	        		ajaxSubmit('#dataForm','${path}/sys/project/generateCode/save.action');
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
					action="${path}/sys/project/generateCode/delete.action" method="post">
					<table class="table table-striped">
						<thead>
							<tr>
								<th><a
									onclick="
										var checkedFlag = $('#datasForm [name=\'ids\']').prop('checked');
										$('#datasForm [name=\'ids\']').prop('checked',!checkedFlag);
									">全选</a></th>
								<th>状态</th>
								<th>类全名</th>
								<th>模块名称</th>
								<th>模块子包名</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${page.item}" var="generateCode">
								<tr>
									<td><input type="checkbox" name="ids" value="${generateCode.id}" />
										<a>详情</a>
										<a
										href="${path}/sys/project/generateCode/delete.action?ids=${generateCode.id}">删除</a>
										<a href="#"
										onclick="
		        			         		$('#formDialog').modal({show:true});
		        							$('#formInit').panel({    
		        								    href:'${path}/sys/project/generateCode/form.action?generateCode.id=${generateCode.id}', 
		        								});
	                        			">编辑</a>
	                        			<c:if test="${generateCode.state!=-1 }">
	                        			<a href="javascript:lock('${generateCode.id}')" style="color:#f00;">锁定</a>
	                        			<a href="javascript:generateCode('${generateCode.id}')">生成模块</a>
	                        			</c:if>
	                        		<%-- 	<c:if test="${generateCode.state==-1 }">
	                        			<a href="javascript:removeLock('${generateCode.id}')">解锁</a>
	                        			</c:if> --%>
									</td>
									<td>${stateMap[generateCode.state] }</td>
									<td>${generateCode.className}</td>
									<td>${generateCode.moduleName}</td>
									<td>${generateCode.packageChildName}</td>
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
					<iframe src="${path_page}/sys/project/generateCode/importDatas.jsp"></iframe>
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
				$("#pagination").myPagination(
						{
							currPage : '${page.pgNo}',
							pageCount : '${page.totalPg}' == '0' ? '1'
									: '${page.totalPg}',
							pageSize : '${page.pgSize}',
							cssStyle : 'meneame'
						});
				$("#pagination a").click(function() {
					var pgNo = $(this).attr("title");
					$("#search_condition #pgNo").val(pgNo);
					$("#search_condition").submit();
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
	
	<script>
		/*
			代码生成请求
		*/
		function generateCode(id){
			var flag = confirm("如该模块已经生成过代码，则会覆盖代码。确定要生成代码吗？");
			if(!flag){
				return;
			}else{
				$.ajax({
					type:"get",
					url:"${path}/sys/project/generateCode/generateCode.action?generateCode.id="+id,
					async:false,
					success:function(data){
						if(data.mess=="success"){
							alert("代码生成成功！");
						}else{
							alert("代码生成没有成功！");
						}
					},
					error:function(){
						alert("系统出错了~我们正在加紧抢修中!");
					}
				});
			}
		}
		/*
			解锁
		*/
		function removeLock(id){
			$.ajax({
				type:"get",
				url:"${path}/sys/project/generateCode/removeLock.action?generateCode.id="+id,
				async:false,
				dataType:"json",
				success:function(data){
					if("success"==data.mess){
						$.messager.progress('close'); // 如果提交成功则隐藏进度条
						location.reload();
					}else{
						alert(data.mess);
					}
				},
				error:function(){
					alert("系统出错啦，我们正在加紧抢修！");
				},
				beforeSend:function(XMLHttpRequest){
					$.messager.progress(); // 显示进度条
				}
			});
		}
		/*
			锁定
		*/
		function lock(id){
			$.ajax({
				type:"get",
				url:"${path}/sys/project/generateCode/lock.action?generateCode.id="+id,
				async:false,
				dataType:"json",
				success:function(data){
					if("success"==data.mess){
						$.messager.progress('close'); // 如果提交成功则隐藏进度条
						location.reload();
					}else{
						alert(data.mess);
					}
				},
				error:function(){
					alert("系统出错啦，我们正在加紧抢修！");
				},
				beforeSend:function(XMLHttpRequest){
					$.messager.progress(); // 显示进度条
				}
			});
		}
	</script>

</body>

</html>
