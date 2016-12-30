<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/include/taglib.jsp"%>


<form:form id="dataForm" method="POST" modelAttribute="roleModel" class="form-horizontal form-inline" role="form">  	
	<input type="hidden" name="role.id" value="${roleModel.role.id }"/>
 	<div class="form-group">
      <label for="firstname" class="col-sm-4 control-label">角色名称：</label>
      <div class="col-sm-8">
         <input type="text" class="form-control easyui-validatebox" id="firstname"  name="role.roleName" 
         	data-options="required:true" value="${roleModel.role.roleName }">
      </div>
   	</div>
   	
   	<div class="ibox float-e-margins">
         <div class="ibox-title">
             <h5>角色权限</h5>
         </div>
         <div class="ibox-content">
           <form:checkboxes path="menuList" items="${menus}" itemValue="id" itemLabel="text"/> 
         </div>
     </div>
 </form:form>  

<script>
	
</script>