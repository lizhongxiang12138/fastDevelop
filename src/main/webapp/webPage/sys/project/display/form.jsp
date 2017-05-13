
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

<!--引入CSS-->
<link rel="stylesheet" type="text/css" href="${pathResource_tools }/webuploader/webuploader.css">

<script src="${pathSys_page}/js/jquery.min.js?v=2.1.4"></script>
<script type="text/javascript" src="${pathResource}/easyui/jquery.min.js"></script>
<script src="${pathSys_page}/js/bootstrap.min.js?v=3.3.5"></script>
<script src="${pathSys_page}/js/plugins/metisMenu/jquery.metisMenu.js"></script>
<script src="${pathSys_page}/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>
<script type="text/javascript" src="${pathSys_page}/js/contabs.min.js"></script>
<script src="${pathSys_page}/js/plugins/pace/pace.min.js"></script>
<script type="text/javascript" src="${pathResource}/easyui/jquery.easyui.min.js"></script>
<!-- 中文支持 -->
<script type="text/javascript" src="${pathResource}/easyui/locale/easyui-lang-zh_CN.js"></script>

<!-- 引入分页插件样式-->
<link rel="stylesheet" type="text/css"
	href="${pathResource_tools}/pageNavigator/myPagination.css">
<script src="${pathResource_tools}/pageNavigator/jquery.myPagination.js"></script>
<!-- 引入分页插件  end-->

</head>

<body>
	<script type="text/javascript" src="${path}/resource/js/validate_js/validate.js"></script>
	<script type="text/javascript" src="${path}/resource/js/validate_js/basicValidate.js"></script>

	<form:form id="dataForm" method="POST"
		modelAttribute="historicalRelicModel"
		class="form-horizontal form-inline" role="form">
		<input type="hidden" name="historicalRelic.id" value="${historicalRelicModel.historicalRelic.id}" />
		
		
		<!-- S-文物首图 -->
		<div class="form-group" style="margin-top: 10px; width: 100%">

			<label for="firstname" class="control-label"
				style="width: 20%; float: left;">标题图片：</label>
			<div style="width: 75%; float: left;">
				<input type="hidden" id="titleImageID" name="historicalRelic.titleImageID"
					value="${historicalRelicModel.historicalRelic.titleImageID}">
				<div id="uploader" class="wu-example">
					<!--用来存放文件信息-->
					<div id="thelist" class="uploader-list">

						<c:if test="${!empty historicalRelicModel.historicalRelic.titleImageID}">
							<!-- S-标题图片信息 -->
							<div id="${historicalRelicModel.historicalRelic.titleImageID}"
								class="file-item thumbnail" style="width: 100px; height: 120px;">
								<div class="progress-bar" role="progressbar" aria-valuenow="60"
									aria-valuemin="0" aria-valuemax="100"
									style="width: 0%; height: 10px">
									<span class="sr-only"></span>
								</div>
								<img src="${titleImg.fileRelativePath }"
									style="width: 100px; height: 100px;" />
								<p class="info" style="text-align: center"></p>
							</div>
							<!-- E-标题图片信息 -->
						</c:if>

					</div>
					<div class="btns">
						<div id="picker" style="float: left;">选择文件</div>
						<button id="deleteTitleImg" class="btn btn-default" type='button'
							style="background-color: #f00; color: #fff;">删除</button>
					</div>
				</div>
			</div>
			<!--引入JS-->
			<script type="text/javascript" src="${pathResource_tools }/webuploader/webuploader.js"></script>
			<script>
				$(function() {
					/*
						查看文章是否有标题图片了，有标题图片则显示删除图片按钮，没有则显示选择文件按钮
					 */
					var titleImgID = $('#titleImageID').val();//标题图片id
					var titleImgFileId = titleImgID;//标题图片文件id

					//注册删除标题图片点击事件
					$('#deleteTitleImg').click(function() {
						deleteTitleImg();
					})
					/*
						删除标题图片
					 */
					function deleteTitleImg() {
						if ('' != $.trim(titleImgID)) {
							$
									.ajax({
										type : 'get',
										url : '${pathSys}/project/historicalRelic/deleteImgGroup.action?ids='
												+ titleImgID,
										dataTypt : 'json',
										success : function(data) {
											$('#picker').show();//显示选择图片按钮
											$('#deleteTitleImg').hide();//隐藏删除图图片按钮
											if ("success" == data.mess) {
												$('#titleImageID').val('');
												titleImgID = '';
												if ('' != $.trim(titleImgFileId)) {
													var $li = $('#thelist').find('#'+ titleImgFileId);
													$li.remove();
													var fileArr = uploader.getFiles();
													for (var i = 0; i < fileArr.length; i++) {
														//若果存在文件就移除列队
														var f = fileArr[i];
														if (titleImgFileId == f.id)
															uploader.removeFile(f.id,true);
													}
												}
											}
										},
										error : function() {
											alert('出现了错误')
										},
										async : false,
										beforeSend : function() {

										}
									});
						}
					}

					//初始化webUploader	
					var uploader = WebUploader.create({

								// swf文件路径
								swf : '${pathResource_tools }/webuploader/Uploader.swf',

								// 文件接收服务端。
								server : '${pathSys}/project/historicalRelic/uploadeImg.action',

								// 选择文件的按钮。可选。
								// 内部根据当前运行是创建，可能是input元素，也可能是flash.
								pick : {
									id : '#picker',
									multiple : false
								},
								//压缩图片
								compress :{
									width: 1200,
								    height: 780,

								    // 图片质量，只有type为`image/jpeg`的时候才有效。
								    quality: 90,

								    // 是否允许放大，如果想要生成小图的时候不失真，此选项应该设置为false.
								    allowMagnify: false,

								    // 是否允许裁剪。
								    crop: false,

								    // 是否保留头部meta信息。
								    preserveHeaders: true,

								    // 如果发现压缩后文件大小比原来还大，则使用原来图片
								    // 此属性可能会影响图片自动纠正功能
								    noCompressIfLarger: false,

								    // 单位字节，如果图片大小小于此值，不会采用压缩。
								    compressSize: 0
								},
								auto : true,
								fileVal : 'documentFile',
								//单文件上传
								fileNumLimit : 1,
								// 只允许选择图片文件。
								accept : {
								    title: 'Images',
								    extensions: 'jpg,jpeg',
								    mimeTypes: 'image/*'
								}
							});
					// 当有文件添加进来的时候
					uploader.on(
									'fileQueued',
									function(file) {
										var $li = $('<div id="'+file.id+'" class="file-item thumbnail"style="width:100px;height:140px;">'
												+ '<div class="progress-bar" role="progressbar" aria-valuenow="60"'
												+ 'aria-valuemin="0" aria-valuemax="100" style="width: 0%;height: 20px">'
												+ '<span class="sr-only" style="text-align:center;">等待上传</span>'
												+ '</div>'
												+ '<img>'
												+ '<p class="info" style="text-align:center">'
												+ file.name + '</p>' + '</div>'), $img = $li
												.find('img');

										// $list为容器jQuery实例
										$('#thelist').html($li);
										titleImgFileId = file.id;
										// 创建缩略图
										// 如果为非图片文件，可以不用调用此方法。
										// thumbnailWidth x thumbnailHeight 为 100 x 100
										uploader.makeThumb(file,function(error, src) {
															if (error) {
																$img.replaceWith('<span>不能预览</span>');
																return;
															}

															$img.attr('src',src);
														}, 100, 100);
									});
					// 文件上传过程中创建进度条实时显示。
					uploader.on('uploadProgress', function(file, percentage) {
						var $li = $('#' + file.id);
						var $percent = $li.find('.progress-bar');
						$percent.css('width', percentage * 100 + '%');
					});

					// 文件上传成功，给item添加成功class, 用样式标记上传成功。
					uploader.on('uploadSuccess', function(file, data) {
						var $li = $('#' + file.id);
						$('#titleImageID').val(data.documentId);
						var $percent = $li.find('.progress-bar');
						$('#titleImageID').val(data.documentId);
						titleImgID = data.documentId;
						$percent.replaceWith('<span>文件上传成功</span>');
						$('#picker').hide();//隐藏选择图片按钮
						$('#deleteTitleImg').show();//显示删除按钮
					});

					// 文件上传失败，显示上传出错。
					uploader.on('uploadError', function(file) {
						var $li = $('#' + file.id);
						var $percent = $li.find('.progress-bar');
						$percent.replaceWith('<span>文件上传失败</span>');
					});

					// 完成上传完了，成功或者失败，先删除进度条。
					uploader.on('uploadComplete', function(file) {
						var $li = $('#' + file.id);
						var $percent = $li.find('.progress-bar');
						$percent.css('width', '0%');
					});
					//错误时
					uploader.on('error', function(error){
						if('Q_TYPE_DENIED'==error) alert("只能上传jpg格式图片");
					});

					if ('' != $.trim(titleImgID)) {
						$('#picker').hide();
					} else {
						$('#deleteTitleImg').hide();
					}
				});
			</script>
		</div>
		<!-- E-文物首图 -->
		
		
		
		<!-- S-展出日期时间设置 -->
		
		<!-- S-开始展出日期 -->
		<div class="form-group col-sm-5" style="margin-top: 10px">
			<label for="firstname" class="col-sm-4 control-label">开始展览日期：</label>
			<div class="col-sm-4">
					<input id="startDate" type="text" class="easyui-datebox form-control easyui-validatebox"
						name="historicalRelic.startDate" 
						validType=""
						value="${historicalRelicModel.historicalRelic.startDate}">				
			</div>
		</div>
		<!-- E-开始展出日期 -->
		
		<!-- S-结束展出日期 -->
		<div class="form-group col-sm-5" style="margin-top: 10px">
			<label for="firstname" class="col-sm-4 control-label">结束展览日期：</label>
			<div class="col-sm-4">
					<input type="text" class="easyui-datebox form-control easyui-validatebox"
						name="historicalRelic.endDate"
						validType="endDate['startDate']"
						value="${historicalRelicModel.historicalRelic.endDate}">				
			</div>
		</div>
		<!-- E-结束展出日期 -->
		
		<!-- S-开始展出时间 -->
		<div class="form-group col-sm-5" style="margin-top: 10px">
			<label for="firstname" class="col-sm-4 control-label">开始展览时间：</label>
			<div class="col-sm-4">
				<input id="startTime" type="text" class="easyui-timespinner form-control easyui-validatebox"
					name="historicalRelic.startTime" data-options=""
					validType="data_baseType"
					value="${historicalRelicModel.historicalRelic.startTime}">
			</div>
		</div>
		<!-- E-开始展出时间 -->
		
		<!-- S-结束展出时间 -->
		<div class="form-group col-sm-5" style="margin-top: 10px">
			<label for="firstname" class="col-sm-4 control-label">结束展览时间：</label>
			<div class="col-sm-4">
				<input type="text" class="easyui-timespinner form-control easyui-validatebox"
					name="historicalRelic.endTime" data-options=""
					validType="endTime['startTime']"
					value="${historicalRelicModel.historicalRelic.endTime}">
			</div>
		</div>
		<!-- E-结束展出时间 -->
		<!-- E-展出日期时间设置 -->
		
		
		
		<div class="form-group col-sm-5" style="margin-top: 10px">
			<label for="firstname" class="col-sm-4 control-label">展览地点：</label>
			<div class="col-sm-4">
				<input type="text" class="form-control easyui-validatebox"
					name="historicalRelic.showAddress" data-options="required:true"
					validType="data_baseType"
					value="${historicalRelicModel.historicalRelic.showAddress}">
			</div>
		</div>
		<div class="form-group col-sm-5" style="margin-top: 10px">
			<label for="firstname" class="col-sm-4 control-label">陈列名称：</label>
			<div class="col-sm-4">
				<input type="text" class="form-control easyui-validatebox"
					name="historicalRelic.title" data-options="required:true"
					validType="data_baseType"
					value="${historicalRelicModel.historicalRelic.title}">
			</div>
		</div>
		<div class="form-group col-sm-5" style="margin-top: 10px">
			<label for="firstname" class="col-sm-4 control-label">陈列类型：</label>
			<div class="col-sm-4">
				<my:Select name="historicalRelic.typeID"
	         		className="form-control easyui-validatebox"
	         		dataOptions="required:true"
					beanClassName="com.my.project.entity.HistoricType" value="id"
					valueName="typeName" where="type=2"
					selectValue="${historicalRelicModel.historicalRelic.typeID }" />
			</div>
		</div>
		
		
		<!-- S-陈列介绍 -->
		<div class="form-group" style="margin-top: 10px; width: 100%">
			<p style="text-align: center;font-weight: 700;font-size: 14px">陈列介绍</p>
			<div style="width: 85%;margin: 2px auto;">
				<!-- 加载编辑器的容器 -->
				<script id="container" name="historicalRelic.intro" type="text/plain"
					style="height: 300px;">
					${historicalRelicModel.historicalRelic.intro}
				</script>
				<!-- 配置文件 -->
				<script type="text/javascript"
					src="${path_page }/sys/project/display/ueditor/ueditor.config.js"></script>
				<!-- 编辑器源码文件 -->
				<script type="text/javascript"
					src="${path_page }/sys/project/display/ueditor/ueditor.all.js"></script>
				<!-- 实例化编辑器 -->
				<script type="text/javascript">
					var ue = UE.getEditor('container');
				</script>
			</div>
		</div>
		<!-- E-陈列介绍  -->
		
		<!-- S-文物图片组-->
		<div class="form-group" style="margin-top: 10px; width: 100%">

			<label for="firstname" class="control-label"
				style="width: 20%; float: left;">图片组：</label>
			<div id='img_group' style="width: 75%; float: left;">



				<!-- S-文章图片组预览 -->
				<div id="uploaderImg" class="wu-example" style="width: 100%;">

					<!--用来存放文件信息-->
					<div id="img_group_list" class="uploader-list">
						<c:forEach items="${imgGroup }" var="imgs">
							<!-- S-图片列表 -->
							<div id="${imgs.id }" class="file-item thumbnail"
								style="width: 100px; height: 130px; float: left; margin-left: 10px;">

								<!-- 图片 -->
								<img src="${imgs.fileRelativePath }"
									style="width: 100ps; height: 100px" />

								<!-- S-上传进度条 -->
								<div class="progress-bar" role="progressbar" aria-valuenow="60"
									aria-valuemin="0" aria-valuemax="100"
									style="width: 0%; height: 5px; margin-left: 10px">
									<span class="sr-only"></span>
								</div>
								<!-- E-上传进度条 -->

								<p class="info" style="text-align: center">${imgs.documentTitle }</p>

								<!-- S-图片组对应图片id -->
								<input type="hidden" name="imgGroup" value="${imgs.id }">
								<!-- E-图片组对应图片id -->

							</div>
							<!-- E-图片列表 -->
						</c:forEach>

					</div>

					<!-- S-操作按钮 -->
					<div class="btns" style="clear: both;">
						<div id="add_img" style="float: left;">添加图片</div>
					</div>
					<!-- E-操作按钮 -->

				</div>
				<!-- E-文章图片组预览 -->

			</div>
			<!-- E-文章图片组（html）-->




			<!--引入JS-->
			<script type="text/javascript"
				src="${pathResource_tools }/webuploader/webuploader.js"></script>
			<script>
				$(function() {
					document.getElementById("img_group_list").oncontextmenu = function(
							e) {
						return false;
					}
					var imgGroup_size = "${fn:length(imgGroup)}";
					var fileNumLimit = 10 - imgGroup_size;
					//初始化webUploader	
					var uploaderImg = WebUploader
							.create({

								// swf文件路径
								swf : '${pathResource_tools }/webuploader/Uploader.swf',

								// 文件接收服务端。
								server : '${pathSys}/project/historicalRelic/uploadeImg.action',

								// 选择文件的按钮。可选。
								// 内部根据当前运行是创建，可能是input元素，也可能是flash.
								pick : '#add_img',
								prepareNextFile : true,
								
								//压缩图片
								compress :{
									width: 800,
								    height: 400,

								    // 图片质量，只有type为`image/jpeg`的时候才有效。
								    quality: 90,

								    // 是否允许放大，如果想要生成小图的时候不失真，此选项应该设置为false.
								    allowMagnify: false,

								    // 是否允许裁剪。
								    crop: false,

								    // 是否保留头部meta信息。
								    preserveHeaders: true,

								    // 如果发现压缩后文件大小比原来还大，则使用原来图片
								    // 此属性可能会影响图片自动纠正功能
								    noCompressIfLarger: false,

								    // 单位字节，如果图片大小小于此值，不会采用压缩。
								    compressSize: 0
								},
								
								auto : true,
								fileVal : 'documentFile',
								//最多上传十张图
								fileNumLimit : fileNumLimit,
								// 只允许选择图片文件。
								accept :{
								    title: 'Images',
								    extensions: 'jpg,jpeg',
								    mimeTypes: 'image/*'
								}
							});
					// 当有文件添加进来的时候
					uploaderImg
							.on(
									'fileQueued',
									function(file) {
										var $li = $('<div id="'+file.id+'" class="file-item thumbnail"'+
													'style="width: 100px; height: 130px;float:left;margin-left:10px">'
												+ '<!-- 图片 -->'
												+ '<img class="img_l"/>'
												+ '<!-- S-上传进度条 -->'
												+ '<div class="progress-bar" role="progressbar" aria-valuenow="60"'
												+ 'aria-valuemin="0" aria-valuemax="100"'
												+ 'style="width: 0%; height: 5px">'
												+ '<span class="sr-only"></span>'
												+ '</div>'
												+ '<!-- E-上传进度条 -->'
												+ '<p class="info" style="text-align: center">'
												+ file.name + '</p>' + '</div>');

										// $list为容器jQuery实例
										$('#img_group_list').append($li);

										// 创建缩略图
										// 如果为非图片文件，可以不用调用此方法。
										// thumbnailWidth x thumbnailHeight 为 100 x 100
										uploaderImg
												.makeThumb(
														file,
														function(error, src) {
															/*获取预览图片*/
															var $img = $li
																	.find('.img_l');
															if (error) {
																$img
																		.replaceWith('<span>不能预览</span>');
																return;
															}
															$img.attr('src',
																	src);
														}, 100, 100);
										//注册点击事件
										registerRightMenuClick()
									});
					// 文件上传过程中创建进度条实时显示。
					uploaderImg.on('uploadProgress',
							function(file, percentage) {
								var $li = $('#' + file.id);
								var $percent = $li.find('.progress-bar');
								$percent.css('width', percentage * 100 + '%');
							});

					// 文件上传成功，给item添加成功class, 用样式标记上传成功。
					uploaderImg .on(
									'uploadSuccess',
									function(file, data) {
										var $li = $('#' + file.id);
										var $percent = $li.find('.progress-bar');
										$percent.replaceWith('<span>文件上传成功</span>');
										/* 添加img对应id的input */
										$li.append('<input type="hidden" name="imgGroup" value="'+data.documentId+'">')
									});

					// 文件上传失败，显示上传出错。
					uploaderImg.on('uploadError', function(file) {
						var $li = $('#' + file.id);
						var $percent = $li.find('.progress-bar');
						$percent.replaceWith('<span>文件上传失败</span>');
					});
					
					//错误时
					uploaderImg.on('error', function(error){
						if('Q_TYPE_DENIED'==error) alert("只能上传jpg格式图片");
					});

					// 完成上传完了，成功或者失败，先删除进度条。
					uploaderImg.on('uploadComplete', function(file) {
						var $li = $('#' + file.id);
						var $percent = $li.find('.progress-bar');
						$percent.css('width', '0%');
					});
					var fileId = '';
					var imgId = ''
					/*
						点击显示菜单
					 */
					function registerRightMenuClick() {
						$('#img_group_list').find('.file-item').click(
								function(e) {
									var xx = e.pageX;
									var yy = e.pageY;
									fileId = $(this).attr('id');
									imgId = $(this).find('input').val();
									$('#right_menu').menu('show', {
										left : xx,
										top : yy
									});
								});
					}
					registerRightMenuClick();
					/*
						选项点击
					 */
					$('#right_menu').menu(
							{
								onClick : function(item) {
									var id = $(item).attr('id');
									if ("delectFile" == id) {
										delectFile(fileId, imgId);
									}
									if ("editDescription" == id) {
										$('#importDatas').modal({
											show : true
										});
										$('#imgRemarkPage').attr(
												'src',
												'${pathSys}/project/historicalRelic/remarkImgPage.action?document.id='
														+ imgId);
									}
								}
							});

					/*
						删除图片的方法
					 */
					function delectFile(fileId, imgid) {
						$
								.ajax({
									type : 'get',
									url : '${pathSys}/project/historicalRelic/deleteImgGroup.action?ids='
											+ imgid,
									dataTypt : 'json',
									success : function(data) {
										if ("success" == data.mess) {
											var $li = $('#img_group_list')
													.find('#' + fileId);
											$li.remove();
											var fileArr = uploaderImg
													.getFiles();
											for (var i = 0; i < fileArr.length; i++) {
												//若果存在文件就移除列队
												var f = fileArr[i];
												if (fileId == f.id)
													uploaderImg.removeFile(
															fileId, true);
											}
										}
									},
									error : function() {
										alert('出现了错误')
									},
									async : false,
									beforeSend : function() {

									}
								});
					}

				});
			</script>
		</div>
		<!-- E-文物图组 -->
		
	</form:form>
	
	<!-- S-提交按钮 -->
	<p style="text-align: center;">
		<button type="button" onclick="history.go(-1);">返回</button>
		<button type="button"
			onclick="ajaxSubmit('#dataForm','${path}/sys/project/historicalRelic/save.action?historicalRelic.culturalRelicId=2');">保存</button>
	</p>
	<!-- E-提交按钮 -->

	<!-- S-右击菜单 -->
	<div id="right_menu" class="easyui-menu" style="width: 120px;">
		<div id="delectFile">删除图片</div>
		<div id="editDescription" onclick="$('#imgRemark').modal({show:true})">编辑文件描述</div>
	</div>
	<!-- E-右击菜单 -->

	<!-- 图片描述页面-->
	<div id="imgRemark" class="modal fade" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog modal-lg" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">图片描述</h4>
				</div>
				<div id=modal_content class="modal-body">
					<!-- 数据导入主体 -->
					<iframe id="imgRemarkPage" src="" style="width: 100%;"></iframe>
					<!-- 数据导入主体  end-->
				</div>
			</div>
		</div>
	</div>
	<!-- 图片描述页面   end -->
	


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
						$.messager.progress('close'); // 如果提交成功则隐藏进度条
						history.go(-1);
					}else{
						$.messager.progress('close'); // 如果提交成功则隐藏进度条
						alert(data.mess);
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
		/*
		关闭dialog
		dialogId : dialog的Id
		 */
		function closeDialog(dialogId) {
			$("#" + dialogId).modal('hide');
		}
	</script>

</body>

</html>