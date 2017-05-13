<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/include/taglib.jsp"%>

<script type="text/javascript"
	src="${path}/resource/js/validate_js/validate.js"></script>

<form:form id="dataForm" method="POST" modelAttribute="articleTypeModel" class="form-horizontal form-inline" role="form">  	
	<input type="hidden" name="articleType.id" value="${articleTypeModel.articleType.id}"/>
 	<div class="form-group col-sm-5" style="margin-top: 10px">
		      <label for="firstname" class="col-sm-4 control-label">文章栏目名称：</label>
		      <div class="col-sm-4">
		         <input type="text" class="form-control easyui-validatebox"  name="articleType.name" 
		         	data-options="required:true" validType="data_baseType" value="${articleTypeModel.articleType.name}">
		      </div>
   	</div>
 	<div class="form-group col-sm-5" style="margin-top: 10px">
		      <label for="firstname" class="col-sm-4 control-label">状态：</label>
		      <div class="col-sm-4">
		         <input type="text" class="form-control easyui-validatebox"  name="articleType.state" 
		         	data-options="required:true" validType="data_baseType" value="${articleTypeModel.articleType.state}">
		      </div>
   	</div>
 </form:form>  

<script>
	
</script>