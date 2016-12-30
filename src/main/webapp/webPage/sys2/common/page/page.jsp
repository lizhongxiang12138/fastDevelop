<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/include/taglib.jsp"%>
<!------------------------------------------------------- 分页 -------------------------------------------------------------->
			<div style="margin:0 auto; text-align:center;">
				<div style="clear:both; display:inline-block;margin:0 auto; font-size:14px;">
				       <ul class="pagination">
				       	  <c:choose>
						  	<c:when test="${page.pageNo==1 }">
						  		<li><a>&laquo;</a></li>
						  	</c:when>
						  	<c:otherwise>
						  		<li><a href="javascript:doSearch(${page.pageNo-1 })">&laquo;</a></li>
						  	</c:otherwise>
						  </c:choose>
				       	  <c:forEach begin="1" end="7" varStatus="index">
				       	  <c:choose>
				       	  <c:when test="${page.last > 7 }">
							  <c:choose>
							  	<c:when test="${page.pageNo<=3||page.pageNo>=(page.last-2)}">
							  		<c:choose>
							  		<c:when test="${index.index==4 }">
							  			<li><a>...&nbsp;...</a></li>
							  		</c:when>
							  		<c:when test="${index.index > 4 }">
							  			<li class="<c:if test="${page.pageNo==page.last-(7-index.index) }"> active </c:if>" ><a href="javascript:doSearch(${page.last-(7-index.index)})">${page.last-(7-index.index)}</a></li>
							  		</c:when>
							  		<c:when test="${index.index < 4 }">
							  			<li class="<c:if test="${page.pageNo==index.index }"> active </c:if>" ><a href="javascript:doSearch(${index.index })">${index.index }</a></li>
							  		</c:when>
							  		</c:choose>
							  	</c:when>
							  	<c:otherwise>
							  		<c:choose>
							  		<c:when test="${index.index==1}">
							  			<li class="<c:if test="${page.pageNo==1 }"> active </c:if>" ><a href="javascript:doSearch(1)">1</a></li>
							  		</c:when>
							  		<c:when test="${index.index==2 || index.index==6 }">
							  			<li><a>...</a></li>
							  		</c:when>
							  		<c:when test="${index.index==7}">
							  			<li class="<c:if test="${page.pageNo==page.last}"> active </c:if>" ><a href="javascript:doSearch(${page.last })">${page.last }</a></li>
							  		</c:when>
							  		<c:otherwise>
							  			<li class="<c:if test="${page.pageNo==page.pageNo+(index.index-3) }"> active </c:if>" ><a href="javascript:doSearch(${page.pageNo+(index.index-3)})">${page.pageNo+(index.index-3)}</a></li>
							  		</c:otherwise>
							  		</c:choose>
							  	</c:otherwise>
							  </c:choose>
						  </c:when>
						  <c:when test="${page.last <= 7 }">
						  	<c:if test="${index.index<=page.last }"><li class="<c:if test="${page.pageNo==index.index }"> active </c:if>" ><a href="javascript:doSearch(${index.index })">${index.index }</a></li></c:if>
						  </c:when>
						  </c:choose>
						  </c:forEach>
						  <li><a>&nbsp;&nbsp;&nbsp;共${page.count }条记录&nbsp;&nbsp;&nbsp;每页${page.pageSize }条记录</a></li>
						  <c:choose>
						  	<c:when test="${page.pageNo==page.last }">
						  		<li><a>&raquo;</a></li>
						  	</c:when>
						  	<c:otherwise>
						  		<li><a href="javascript:doSearch(${page.pageNo+1 })">&raquo;</a></li>
						  	</c:otherwise>
						  </c:choose>
						</ul>
						
				</div>
			</div>
			<!--------------------------------------------------------------------- 分页 end -------------------------------------------------------------->