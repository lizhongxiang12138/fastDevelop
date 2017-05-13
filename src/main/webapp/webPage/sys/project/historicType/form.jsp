<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/include/taglib.jsp"%>

<script type="text/javascript"
	src="${path}/resource/js/validate_js/validate.js"></script>

<form:form id="dataForm" method="POST" modelAttribute="historicTypeModel" class="form-horizontal form-inline" role="form">  	
	<input type="hidden" name="historicType.id" value="${historicTypeModel.historicType.id}"/>
 	<div class="form-group col-sm-5" style="margin-top: 10px">
		      <label for="firstname" class="col-sm-4 control-label">类型名称：</label>
		      <div class="col-sm-4">
		         <input type="text" class="form-control easyui-validatebox"  name="historicType.typeName" 
		         	data-options="required:true" validType="data_baseType" value="${historicTypeModel.historicType.typeName}">
		      </div>
   	</div>
 	<div class="form-group col-sm-5" style="margin-top: 10px">
		      <label for="firstname" class="col-sm-4 control-label">类型：</label>
		      <div class="col-sm-4">
		         <form:select data-options="required:true" cssClass="form-control easyui-validatebox" path="historicType.type" items="${types }"></form:select>
		      </div>
   	</div>
 </form:form>  

<script>
	
</script>