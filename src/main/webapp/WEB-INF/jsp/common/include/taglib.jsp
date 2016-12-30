<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="my" uri="/WEB-INF/tag/myTag.tld" %>

<c:set var="path" value="${pageContext.request.contextPath}"/>
<c:set var="path_page" value="${pageContext.request.contextPath}/webPage"/>
<c:set var="pathSys" value="${pageContext.request.contextPath}/sys"/>
<c:set var="pathSys_page" value="${pageContext.request.contextPath}/webPage/sys"/>
<c:set var="sys_project" value="${pageContext.request.contextPath}/webPage/sys/project"/>
<c:set var="pathResource_tools" value="${pageContext.request.contextPath}/resource/tools"/>
<c:set var="pathResource" value="${pageContext.request.contextPath}/resource"/>