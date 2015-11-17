<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div id = "breadcrumbs">
	<ol class = "breadcrumb">
		<c:forEach var = "breadcrumb" items = "${breadcrumbs}" varStatus = "currentBreadcrumb">
			<c:choose>
				<c:when test = "${currentBreadcrumb.last}">
					<li class = "active"><spring:message code = "${breadcrumb.value}"/></li>
				</c:when>
				<c:otherwise>
					<li><a href = "${breadcrumb.key}"><spring:message code = "${breadcrumb.value}"/></a></li>
				</c:otherwise>
			</c:choose>
		</c:forEach>
	</ol>
</div>