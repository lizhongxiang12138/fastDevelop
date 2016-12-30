<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/include/taglib.jsp"%>


<form:form id="dataForm" method="POST" modelAttribute="generateCodeModel" class="form-horizontal form-inline" role="form">  	
	<input type="hidden" name="generateCode.id" value="${generateCodeModel.generateCode.id}"/>
 	<div class="form-group">
      <label for="firstname" class="col-sm-4 control-label">类全名：</label>
      <div class="col-sm-8">
         <input type="text" class="form-control easyui-validatebox"  name="generateCode.className" 
         	data-options="required:true" value="${generateCodeModel.generateCode.className}">
      </div>
   	</div>
 	<div class="form-group">
      <label for="firstname" class="col-sm-4 control-label">模块名称：</label>
      <div class="col-sm-8">
         <input type="text" class="form-control easyui-validatebox"  name="generateCode.moduleName" 
         	data-options="required:true" value="${generateCodeModel.generateCode.moduleName}">
      </div>
   	</div>
   	<div class="form-group">
      <label for="firstname" class="col-sm-4 control-label">模块子包名：</label>
      <div class="col-sm-8">
         <input type="text" class="form-control easyui-validatebox"  name="generateCode.packageChildName" 
         	data-options="required:true" value="${generateCodeModel.generateCode.packageChildName}">
      </div>
   	</div>
 </form:form>  

<script>
	
</script>