<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/include/taglib.jsp"%>


<form id="menuForm" action="" method="post" class="form-horizontal"
	role="form">
	<input type="hidden" name="menu.id" value="${menuModel.menu.id }" />
	<div class="form-group col-md-6">
		<label for="firstname" class="col-sm-4 control-label">名称</label>
		<div class="col-sm-8">
			<input type="text" class="form-control easyui-validatebox"
				id="firstname" name="menu.text" data-options="required:true"
				value="${menuModel.menu.text }">
		</div>
	</div>
	<div class="form-group col-md-6">
		<label for="firstname" class="col-sm-4 control-label">等级</label>
		<div class="col-sm-8">
			<select class="form-control easyui-validatebox" type="text"
				name="menu.menuGrade" data-options="required:true">
				<option value="0"
					<c:if test="${menuModel.menu.menuGrade=='0' }">selected="selected"</c:if>>根菜单</option>
				<option value="1"
					<c:if test="${menuModel.menu.menuGrade=='1' }">selected="selected"</c:if>>子菜单</option>
			</select>
		</div>
	</div>
	<div class="form-group col-md-6">
		<label for="firstname" class="col-sm-4 control-label">父级菜单</label>
		<div class="col-sm-8">
			<my:Select name="menu.menuParent"
				beanClassName="com.my.project.entity.TbMenu" value="id"
				valueName="text" where="1=1"
				selectValue="${menuModel.menu.menuParent }" />
		</div>
	</div>
	<div class="form-group col-md-6">
		<label for="firstname" class="col-sm-4 control-label">菜单链接</label>
		<div class="col-sm-8">
			<c:choose>
				<c:when test="${menuModel.menu.url!=null&&menuModel.menu.url!=''}">
					<input type="text" class="form-control" name="menu.url"
						value="${menuModel.menu.url }" />
				</c:when>
				<c:otherwise>
					<input type="text" class="form-control" name="menu.url" value="#" />
				</c:otherwise>
			</c:choose>
		</div>
	</div>
	<div class="form-group col-md-6">
		<label for="firstname" class="col-sm-4 control-label">菜单图标</label>
		<div class="col-sm-8">
			<input type="text" class="form-control easyui-validatebox"
				name="menu.iconCls" value="${menuModel.menu.iconCls }" /> <span
				class="${menuModel.menu.iconCls  }"></span>
			<button onclick="showBrowseIconDialog()" id="browse_icon"
				type="button" class="btn btn-w-m btn-success">浏览可用图标</button>
		</div>
	</div>

</form>


<script>
/*
 *	打开浏览可用图标窗口
 */
function showBrowseIconDialog() {
	$('#browse_iconDialog').modal({
		show : true
	});
}
/*
 *	关闭浏览可用图标窗口
 */
function closeBrowseIconDialog() {
	$('#browse_iconDialog').modal({
		show : false
	});
}

</script>