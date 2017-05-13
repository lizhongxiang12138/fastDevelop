<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/include/taglib.jsp"%>

<script type="text/javascript" src="${path}/resource/js/validate_js/validate.js"></script>

<form:form id="dataForm" method="POST" modelAttribute="documentModel" class="form-horizontal form-inline" role="form">  	
	<input type="hidden" name="document.id" value="${documentModel.document.id}"/>
 	<div class="form-group">
      <label for="firstname" class="col-sm-4 control-label">文件描述：</label>
      <div class="col-sm-8">
         <input type="text" class="form-control easyui-validatebox"  name="document.fileDescribe" 
         	data-options="required:true" validType="data_baseType" value="${documentModel.document.fileDescribe}">
      </div>
   	</div>
 	<div class="form-group">
      <label for="firstname" class="col-sm-4 control-label">文件路径：</label>
      <div class="col-sm-8">
         <input type="text" class="form-control easyui-validatebox"  name="document.filePath" 
         	data-options="required:true" validType="data_baseType" value="${documentModel.document.filePath}">
      </div>
   	</div>
 	<div class="form-group">
      <label for="firstname" class="col-sm-4 control-label">创建时间：</label>
      <div class="col-sm-8">
         <input type="text" class="form-control easyui-validatebox"  name="document.createTime" 
         	data-options="required:true" validType="data_baseType" value="${documentModel.document.createTime}">
      </div>
   	</div>
 	<div class="form-group">
      <label for="firstname" class="col-sm-4 control-label">业务名称：</label>
      <div class="col-sm-8">
         <input type="text" class="form-control easyui-validatebox"  name="document.serviceName" 
         	data-options="required:true" validType="data_baseType" value="${documentModel.document.serviceName}">
      </div>
   	</div>
 	<div class="form-group">
      <label for="firstname" class="col-sm-4 control-label">最后浏览时间：</label>
      <div class="col-sm-8">
         <input type="text" class="form-control easyui-validatebox"  name="document.lastBrowseTime" 
         	data-options="required:true" validType="data_baseType" value="${documentModel.document.lastBrowseTime}">
      </div>
   	</div>
 	<div class="form-group">
      <label for="firstname" class="col-sm-4 control-label">文档说明：</label>
      <div class="col-sm-8">
         <input type="text" class="form-control easyui-validatebox"  name="document.documentDeclare" 
         	data-options="required:true" validType="data_baseType" value="${documentModel.document.documentDeclare}">
      </div>
   	</div>
 	<div class="form-group">
      <label for="firstname" class="col-sm-4 control-label">业务ID：</label>
      <div class="col-sm-8">
         <input type="text" class="form-control easyui-validatebox"  name="document.serviceID" 
         	data-options="required:true" validType="data_baseType" value="${documentModel.document.serviceID}">
      </div>
   	</div>
 	<div class="form-group">
      <label for="firstname" class="col-sm-4 control-label">最后浏览用户：</label>
      <div class="col-sm-8">
         <input type="text" class="form-control easyui-validatebox"  name="document.lastBrowseUser" 
         	data-options="required:true" validType="data_baseType" value="${documentModel.document.lastBrowseUser}">
      </div>
   	</div>
 	<div class="form-group">
      <label for="firstname" class="col-sm-4 control-label">文件ID：</label>
      <div class="col-sm-8">
         <input type="text" class="form-control easyui-validatebox"  name="document.id" 
         	data-options="required:true" validType="data_baseType" value="${documentModel.document.id}">
      </div>
   	</div>
 	<div class="form-group">
      <label for="firstname" class="col-sm-4 control-label">创建用户ID：</label>
      <div class="col-sm-8">
         <input type="text" class="form-control easyui-validatebox"  name="document.createUserID" 
         	data-options="required:true" validType="data_baseType" value="${documentModel.document.createUserID}">
      </div>
   	</div>
 	<div class="form-group">
      <label for="firstname" class="col-sm-4 control-label">文档标题：</label>
      <div class="col-sm-8">
         <input type="text" class="form-control easyui-validatebox"  name="document.documentTitle" 
         	data-options="required:true" validType="data_baseType" value="${documentModel.document.documentTitle}">
      </div>
   	</div>
 	<div class="form-group">
      <label for="firstname" class="col-sm-4 control-label">浏览人数统计：</label>
      <div class="col-sm-8">
         <input type="text" class="form-control easyui-validatebox"  name="document.browseCount" 
         	data-options="required:true" validType="data_baseType" value="${documentModel.document.browseCount}">
      </div>
   	</div>
 	<div class="form-group">
      <label for="firstname" class="col-sm-4 control-label">最后浏览用户ID：</label>
      <div class="col-sm-8">
         <input type="text" class="form-control easyui-validatebox"  name="document.lastBrowseUserID" 
         	data-options="required:true" validType="data_baseType" value="${documentModel.document.lastBrowseUserID}">
      </div>
   	</div>
 	<div class="form-group">
      <label for="firstname" class="col-sm-4 control-label">创建用户：</label>
      <div class="col-sm-8">
         <input type="text" class="form-control easyui-validatebox"  name="document.createUser" 
         	data-options="required:true" validType="data_baseType" value="${documentModel.document.createUser}">
      </div>
   	</div>
 	<div class="form-group">
      <label for="firstname" class="col-sm-4 control-label">更新时间：</label>
      <div class="col-sm-8">
         <input type="text" class="form-control easyui-validatebox"  name="document.updateTime" 
         	data-options="required:true" validType="data_baseType" value="${documentModel.document.updateTime}">
      </div>
   	</div>
 </form:form>  

<script>
	
</script>