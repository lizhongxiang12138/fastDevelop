<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/include/taglib.jsp"%>
<html>
<head>
<title>excle数据导入</title>
<meta name="decorator" content="default" />
<script type="text/javascript" src="${pathResource_tools}/jquery-fileupload/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="${pathResource_tools}/jquery-fileupload/jquery.ui.widget.js"></script>
<script type="text/javascript" src="${pathResource_tools}/jquery-fileupload/jquery.iframe-transport.js"></script>
<script type="text/javascript" src="${pathResource_tools}/jquery-fileupload/jquery.fileupload.js"></script>
</head>
<body>
	<div class="width-35">
		<input id="file" type="file" multiple name="file" />
	</div>
	<script type="text/javascript">
		$(function() {//限制只能上传一个文件
			$("#file")
					.fileupload(
							{
								url : "${path }/sys/project/menu/importDatas.action",
								//sequentialUploads : true,
								singleFileUploads: false,
								dataType : "json",
								progressall : function(e,data) {
									/* loading('正在提交，请稍等...'); */
								},
								add:function(e,data){
									var se = confirm("确定要导入数据："+data.files[0].name+"？");
									if(se==true){
										data.submit();
									}else{
										location.replace(location);
										return;
									}
								},
								done : function(e, data) {
									var d = data.result;
									alert(d.mess);
								},
							});
		});
	</script>
</body>
</html>