<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/include/taglib.jsp"%>

<script type="text/javascript"
	src="${path}/resource/js/validate_js/validate.js"></script>

<form:form id="dataForm" method="POST" modelAttribute="dataTypeModel"
	class="form-horizontal form-inline" role="form">
	<input type="hidden" name="dataType.id"
		value="${dataTypeModel.dataType.id}" />
	<div class="form-group col-sm-5" style="margin-top: 10px">
		<label for="firstname" class="col-sm-4 control-label">提示信息：</label>
		<div class="col-sm-4">
			<input type="text" class="form-control easyui-validatebox"
				name="dataType.errorMess" data-options="required:true"
				validType="data_baseType"
				value="${dataTypeModel.dataType.errorMess}">
		</div>
	</div>
	<div class="form-group col-sm-5" style="margin-top: 10px">
		<label for="firstname" class="col-sm-4 control-label">数据长度：</label>
		<div class="col-sm-4">
			<input type="text" class="form-control easyui-validatebox"
				name="dataType.dataLength" data-options="required:true"
				validType="data_baseType"
				value="${dataTypeModel.dataType.dataLength}">
		</div>
	</div>
	<div class="form-group col-sm-5" style="margin-top: 10px">
		<label for="firstname" class="col-sm-4 control-label">正则表达式：</label>
		<div class="col-sm-4">
			<input type="text" class="form-control easyui-validatebox"
				name="dataType.regex" data-options="required:true"
				validType="data_baseType" value="${dataTypeModel.dataType.regex}">
		</div>
	</div>
	<div class="form-group col-sm-5" style="margin-top: 10px">
		<label for="firstname" class="col-sm-4 control-label">类型名称：</label>
		<div class="col-sm-4">
			<input type="text" class="form-control easyui-validatebox"
				name="dataType.typeName" data-options="required:true"
				validType="data_baseType" value="${dataTypeModel.dataType.typeName}">
		</div>
	</div>
	<div class="form-group col-sm-5" style="margin-top: 10px">
		<label for="firstname" class="col-sm-4 control-label">数据类型码：</label>
		<div class="col-sm-4">
			<input type="text" class="form-control easyui-validatebox"
				name="dataType.typeCode" data-options="required:true"
				validType="data_baseType" value="${dataTypeModel.dataType.typeCode}">
		</div>
	</div>
</form:form>

<script>
	
</script>