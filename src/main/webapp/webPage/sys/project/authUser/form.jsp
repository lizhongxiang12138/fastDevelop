<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/include/taglib.jsp"%>


<form:form id="dataForm" method="POST" modelAttribute="authUserModel" class="form-horizontal form-inline" role="form">  	
	<input type="hidden" name="authUser.id" value="${authUserModel.authUser.id}"/>
 	<div class="form-group col-sm-5" style="margin-top: 10px">
      <label for="firstname" class="col-sm-4 control-label">用户密码：</label>
      <div class="col-sm-4">
         <input type="text" class="form-control easyui-validatebox"  name="authUser.userPassword" 
         	data-options="required:true" value="${authUserModel.authUser.userPassword}">
      </div>
   	</div>
 	<div class="form-group col-sm-5" style="margin-top: 10px">
      <label for="firstname" class="col-sm-4 control-label">用户名称：</label>
      <div class="col-sm-4">
         <input type="text" class="form-control easyui-validatebox"  name="authUser.userName" 
         	data-options="required:true" value="${authUserModel.authUser.userName}">
      </div>
   	</div>
 	<div class="form-group col-sm-5" style="margin-top: 10px">
      <label for="firstname" class="col-sm-4 control-label">用户账号：</label>
      <div class="col-sm-4">
         <input type="text" class="form-control easyui-validatebox"  name="authUser.userAccount" 
         	data-options="required:true" value="${authUserModel.authUser.userAccount}">
      </div>
   	</div>
 	<div class="form-group col-sm-5" style="margin-top: 10px">
      <label for="firstname" class="col-sm-4 control-label">用户角色：</label>
      <div class="col-sm-4">
         <my:Select name="authUser.roleId"
         		className="form-control easyui-validatebox"
         		dataOptions="required:true"
				beanClassName="com.my.project.entity.TbRole" value="id"
				valueName="roleName" where="1=1"
				selectValue="${authUserModel.authUser.roleId }" />
      </div>
   	</div>
 </form:form>  

<script>
	
</script>