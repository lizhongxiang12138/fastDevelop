<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/include/taglib.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
pageContext.setAttribute("basePath", basePath);
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<%-- <link rel="stylesheet" type="text/css" href="${basePath}css/sys/css.css" /> --%>
<link rel="stylesheet" type="text/css" href="${basePath}css/sys/pageGroup.css" />
<%-- <script type="text/javascript" src="${basePath}js/sys/jquery.min.js"></script>
<script type="text/javascript" src="${basePath}js/sys/jquery-1.8.3.min.js"></script> --%>
</head>

<body onload="onloadPage()">
	<div class="paging">
		<!-------------------------------------------分页----------------------------------------------------------------->
		<div id="pageGro" class="cb">
			共${page.pgNo }/${page.totalPg}页
			<c:if test="${page.pgNo==1}">
			<div class="pageUp" onclick="toPageNum(${page.pgNo-1})">上一页</div>
			</c:if>
		    <div class="pageList">
		        <ul>
		            
		        </ul>
		    </div>
		    <c:if test="${page.pgNo!=page.totalPg}">
		    <div class="pageDown" onclick="toPageNum(${page.pgNo+1})">下一页</div>
		    </c:if>
		</div>
		<!-------------------------------------------END 分页----------------------------------------------------------------->
	</div>	
	
</body>
<script>
/**
 * 初始化分页
 */
function onloadPage(){
	var totalPg=${page.totalPg};
	var pgNo=${page.pgNo};
	$("#pageGro ul").empty();//清除ul中所有元素
	if(totalPg>5){//如果总页数大于5
		if(pgNo<3){
			for(i=1;i<=5;i++){
				if(i==pgNo){
					$("#pageGro ul").append("<li onclick='toPageNum("+i+")' style='background-color: #303030;'>"+i+"</li>");
				}else{
					$("#pageGro ul").append("<li onclick='toPageNum("+i+")'>"+i+"</li>");
				}	
			}
		}else if((totalPg-pgNo)<3){
			for(i=4;i>=0;i--){
				if((totalPg-i)==pgNo){
					$("#pageGro ul").append("<li onclick='toPageNum("+(totalPg-i)+")' style='background-color: #303030;'>"+(totalPg-i)+"</li>");
				}else{
					$("#pageGro ul").append("<li onclick='toPageNum("+(totalPg-i)+")'>"+(totalPg-i)+"</li>");
				}	
			}
			return;
		}else{
			for(i=0;i<5;i++){
				if((pgNo-2+i)==pgNo){
					$("#pageGro ul").append("<li onclick='toPageNum("+(pgNo-2+i)+")' style='background-color: #303030;'>"+(pgNo-2+i)+"</li>");
				}else{
					$("#pageGro ul").append("<li onclick='toPageNum("+(pgNo-2+i)+")'>"+(pgNo-2+i)+"</li>");
				}	
			}
		}
	}else{//总页数小于5则显示所有页面
		for(i=1;i<=totalPg;i++){
			if(i==pgNo){
				$("#pageGro ul").append("<li onclick='toPageNum("+i+")' style='background-color: #303030;'>"+i+"</li>");
			}else{
				$("#pageGro ul").append("<li onclick='toPageNum("+i+")'>"+i+"</li>");
			}
		}
	}
}
/**
 * 跳转到指定的页码
 */
function toPageNum(pageNum){
	window.location.href=url+"?pgNo="+pageNum;
}
</script>
</html>