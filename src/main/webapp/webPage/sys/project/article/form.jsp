<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/include/taglib.jsp"%>

<script type="text/javascript"
	src="${path}/resource/js/validate_js/validate.js"></script>

<form:form id="dataForm" method="POST" modelAttribute="articleModel" class="form-horizontal form-inline" role="form">  	
	<input type="hidden" name="article.id" value="${articleModel.article.id}"/>
 	<div class="form-group col-sm-5" style="margin-top: 10px">
		      <label for="firstname" class="col-sm-4 control-label">摘要：</label>
		      <div class="col-sm-4">
		         <input type="text" class="form-control easyui-validatebox"  name="article.articleIntro" 
		         	data-options="required:true" validType="data_baseType" value="${articleModel.article.articleIntro}">
		      </div>
   	</div>
 	<div class="form-group col-sm-5" style="margin-top: 10px">
		      <label for="firstname" class="col-sm-4 control-label">内容：</label>
		      <div class="col-sm-4">
		         <input type="text" class="form-control easyui-validatebox"  name="article.content" 
		         	data-options="required:true" validType="data_baseType" value="${articleModel.article.content}">
		      </div>
   	</div>
 	<div class="form-group col-sm-5" style="margin-top: 10px">
		      <label for="firstname" class="col-sm-4 control-label">关键字：</label>
		      <div class="col-sm-4">
		         <input type="text" class="form-control easyui-validatebox"  name="article.keyword" 
		         	data-options="required:true" validType="data_baseType" value="${articleModel.article.keyword}">
		      </div>
   	</div>
 	<div class="form-group col-sm-5" style="margin-top: 10px">
		      <label for="firstname" class="col-sm-4 control-label">评论数：</label>
		      <div class="col-sm-4">
		         <input type="text" class="form-control easyui-validatebox"  name="article.commentCount" 
		         	data-options="required:true" validType="data_baseType" value="${articleModel.article.commentCount}">
		      </div>
   	</div>
 	<div class="form-group col-sm-5" style="margin-top: 10px">
		      <label for="firstname" class="col-sm-4 control-label">录入者：</label>
		      <div class="col-sm-4">
		         <input type="text" class="form-control easyui-validatebox"  name="article.record" 
		         	data-options="required:true" validType="data_baseType" value="${articleModel.article.record}">
		      </div>
   	</div>
 	<div class="form-group col-sm-5" style="margin-top: 10px">
		      <label for="firstname" class="col-sm-4 control-label">文章栏目：</label>
		      <div class="col-sm-4">
		         <input type="text" class="form-control easyui-validatebox"  name="article.type" 
		         	data-options="required:true" validType="data_baseType" value="${articleModel.article.type}">
		      </div>
   	</div>
 	<div class="form-group col-sm-5" style="margin-top: 10px">
		      <label for="firstname" class="col-sm-4 control-label">完整标题：</label>
		      <div class="col-sm-4">
		         <input type="text" class="form-control easyui-validatebox"  name="article.fullTitle" 
		         	data-options="required:true" validType="data_baseType" value="${articleModel.article.fullTitle}">
		      </div>
   	</div>
 	<div class="form-group col-sm-5" style="margin-top: 10px">
		      <label for="firstname" class="col-sm-4 control-label">标题图片ID：</label>
		      <div class="col-sm-4">
		         <input type="text" class="form-control easyui-validatebox"  name="article.titleImageID" 
		         	data-options="required:true" validType="data_baseType" value="${articleModel.article.titleImageID}">
		      </div>
   	</div>
 	<div class="form-group col-sm-5" style="margin-top: 10px">
		      <label for="firstname" class="col-sm-4 control-label">添加时间：</label>
		      <div class="col-sm-4">
		         <input type="text" class="form-control easyui-validatebox"  name="article.createTime" 
		         	data-options="required:true" validType="data_baseType" value="${articleModel.article.createTime}">
		      </div>
   	</div>
 	<div class="form-group col-sm-5" style="margin-top: 10px">
		      <label for="firstname" class="col-sm-4 control-label">标题：</label>
		      <div class="col-sm-4">
		         <input type="text" class="form-control easyui-validatebox"  name="article.title" 
		         	data-options="required:true" validType="data_baseType" value="${articleModel.article.title}">
		      </div>
   	</div>
 	<div class="form-group col-sm-5" style="margin-top: 10px">
		      <label for="firstname" class="col-sm-4 control-label">是否置顶：</label>
		      <div class="col-sm-4">
		         <input type="text" class="form-control easyui-validatebox"  name="article.top" 
		         	data-options="required:true" validType="data_baseType" value="${articleModel.article.top}">
		      </div>
   	</div>
 	<div class="form-group col-sm-5" style="margin-top: 10px">
		      <label for="firstname" class="col-sm-4 control-label">文章栏目ID：</label>
		      <div class="col-sm-4">
		         <input type="text" class="form-control easyui-validatebox"  name="article.typeID" 
		         	data-options="required:true" validType="data_baseType" value="${articleModel.article.typeID}">
		      </div>
   	</div>
 	<div class="form-group col-sm-5" style="margin-top: 10px">
		      <label for="firstname" class="col-sm-4 control-label">副标题：</label>
		      <div class="col-sm-4">
		         <input type="text" class="form-control easyui-validatebox"  name="article.subhead" 
		         	data-options="required:true" validType="data_baseType" value="${articleModel.article.subhead}">
		      </div>
   	</div>
 	<div class="form-group col-sm-5" style="margin-top: 10px">
		      <label for="firstname" class="col-sm-4 control-label">作者：</label>
		      <div class="col-sm-4">
		         <input type="text" class="form-control easyui-validatebox"  name="article.author" 
		         	data-options="required:true" validType="data_baseType" value="${articleModel.article.author}">
		      </div>
   	</div>
 	<div class="form-group col-sm-5" style="margin-top: 10px">
		      <label for="firstname" class="col-sm-4 control-label">评论：</label>
		      <div class="col-sm-4">
		         <input type="text" class="form-control easyui-validatebox"  name="article.comment" 
		         	data-options="required:true" validType="data_baseType" value="${articleModel.article.comment}">
		      </div>
   	</div>
 </form:form>  

<script>
	
</script>