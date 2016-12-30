<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/include/taglib.jsp"%>

<script type="text/javascript"
	src="${path}/resource/js/validate_js/validate.js"></script>

<form:form id="dataForm" method="POST" modelAttribute="historicalRelicModel" class="form-horizontal form-inline" role="form">  	
	<input type="hidden" name="historicalRelic.id" value="${historicalRelicModel.historicalRelic.id}"/>
 	<div class="form-group col-sm-5" style="margin-top: 10px">
		      <label for="firstname" class="col-sm-4 control-label">年代：</label>
		      <div class="col-sm-4">
		         <input type="text" class="form-control easyui-validatebox"  name="historicalRelic.historicalTime" 
		         	data-options="required:true" validType="data_baseType" value="${historicalRelicModel.historicalRelic.historicalTime}">
		      </div>
   	</div>
 	<div class="form-group col-sm-5" style="margin-top: 10px">
		      <label for="firstname" class="col-sm-4 control-label">出土时间：</label>
		      <div class="col-sm-4">
		         <input type="text" class="form-control easyui-validatebox"  name="historicalRelic.comUpTime" 
		         	data-options="required:true" validType="data_baseType" value="${historicalRelicModel.historicalRelic.comUpTime}">
		      </div>
   	</div>
 	<div class="form-group col-sm-5" style="margin-top: 10px">
		      <label for="firstname" class="col-sm-4 control-label">文物类型：</label>
		      <div class="col-sm-4">
		         <input type="text" class="form-control easyui-validatebox"  name="historicalRelic.type" 
		         	data-options="required:true" validType="data_baseType" value="${historicalRelicModel.historicalRelic.type}">
		      </div>
   	</div>
 	<div class="form-group col-sm-5" style="margin-top: 10px">
		      <label for="firstname" class="col-sm-4 control-label">文物ID：</label>
		      <div class="col-sm-4">
		         <input type="text" class="form-control easyui-validatebox"  name="historicalRelic.id" 
		         	data-options="required:true" validType="data_baseType" value="${historicalRelicModel.historicalRelic.id}">
		      </div>
   	</div>
 	<div class="form-group col-sm-5" style="margin-top: 10px">
		      <label for="firstname" class="col-sm-4 control-label">标题图片：</label>
		      <div class="col-sm-4">
		         <input type="text" class="form-control easyui-validatebox"  name="historicalRelic.titleImageID" 
		         	data-options="required:true" validType="data_baseType" value="${historicalRelicModel.historicalRelic.titleImageID}">
		      </div>
   	</div>
 	<div class="form-group col-sm-5" style="margin-top: 10px">
		      <label for="firstname" class="col-sm-4 control-label">文物简介：</label>
		      <div class="col-sm-4">
		         <input type="text" class="form-control easyui-validatebox"  name="historicalRelic.intro" 
		         	data-options="required:true" validType="data_baseType" value="${historicalRelicModel.historicalRelic.intro}">
		      </div>
   	</div>
 	<div class="form-group col-sm-5" style="margin-top: 10px">
		      <label for="firstname" class="col-sm-4 control-label">展览地点：</label>
		      <div class="col-sm-4">
		         <input type="text" class="form-control easyui-validatebox"  name="historicalRelic.showAddress" 
		         	data-options="required:true" validType="data_baseType" value="${historicalRelicModel.historicalRelic.showAddress}">
		      </div>
   	</div>
 	<div class="form-group col-sm-5" style="margin-top: 10px">
		      <label for="firstname" class="col-sm-4 control-label">展览时间：</label>
		      <div class="col-sm-4">
		         <input type="text" class="form-control easyui-validatebox"  name="historicalRelic.showTime" 
		         	data-options="required:true" validType="data_baseType" value="${historicalRelicModel.historicalRelic.showTime}">
		      </div>
   	</div>
 	<div class="form-group col-sm-5" style="margin-top: 10px">
		      <label for="firstname" class="col-sm-4 control-label">外形描述：</label>
		      <div class="col-sm-4">
		         <input type="text" class="form-control easyui-validatebox"  name="historicalRelic.outlineDescribe" 
		         	data-options="required:true" validType="data_baseType" value="${historicalRelicModel.historicalRelic.outlineDescribe}">
		      </div>
   	</div>
 	<div class="form-group col-sm-5" style="margin-top: 10px">
		      <label for="firstname" class="col-sm-4 control-label">文物标题：</label>
		      <div class="col-sm-4">
		         <input type="text" class="form-control easyui-validatebox"  name="historicalRelic.title" 
		         	data-options="required:true" validType="data_baseType" value="${historicalRelicModel.historicalRelic.title}">
		      </div>
   	</div>
 	<div class="form-group col-sm-5" style="margin-top: 10px">
		      <label for="firstname" class="col-sm-4 control-label">文物类型ID：</label>
		      <div class="col-sm-4">
		         <input type="text" class="form-control easyui-validatebox"  name="historicalRelic.typeID" 
		         	data-options="required:true" validType="data_baseType" value="${historicalRelicModel.historicalRelic.typeID}">
		      </div>
   	</div>
 </form:form>  

<script>
	
</script>