<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/include/taglib.jsp"%>

<script type="text/javascript"
	src="${path}/resource/js/validate_js/validate.js"></script>

<form:form id="dataForm" method="POST" modelAttribute="personModel" class="form-horizontal form-inline" role="form">  	
	<input type="hidden" name="person.id" value="${personModel.person.id}"/>
 	<div class="form-group col-sm-5" style="margin-top: 10px">
		      <label for="firstname" class="col-sm-4 control-label">姓名：</label>
		      <div class="col-sm-4">
		         <input type="text" class="form-control easyui-validatebox"  name="person.name" 
		         	data-options="required:true" validType="data_baseType" value="${personModel.person.name}">
		      </div>
   	</div>
 </form:form>  

<script>
	
</script>