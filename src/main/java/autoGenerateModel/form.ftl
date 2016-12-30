<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/include/taglib.jsp"%>

<script type="text/javascript"
	src="${validate_js}"></script>

<form:form id="dataForm" method="POST" modelAttribute="${modelName}" class="form-horizontal form-inline" role="form">  	
	<input type="hidden" name="${idName}" value="${idValue_EL}"/>
	<#list fields as f>
 	<div class="form-group col-sm-5" style="margin-top: 10px">
		      <label for="firstname" class="col-sm-4 control-label">${f.name_CH}：</label>
		      <div class="col-sm-4">
		         <input type="text" class="form-control easyui-validatebox"  name="${f.name}" 
		         	data-options="required:true" validType="${f.validType_id}" value="${f.value_EL }">
		      </div>
   	</div>
   	</#list>
 </form:form>  

<script>
	
</script>