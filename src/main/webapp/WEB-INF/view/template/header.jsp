<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<div class = "securityprincipal" align="right">
	<c:choose>
	<c:when test="${pageContext.request.userPrincipal.name != null}">
		<h5> <spring:message code="crsms.text.signin.signedas" />
			<b>${pageContext.request.userPrincipal.name}</b>
			<a	href=<c:url value="/signout" />>
				<spring:message	code="crsms.button.signout" />
			</a>
		</h5>
	</c:when>
	<c:otherwise>
	<h5> <spring:message code="crsms.text.signin.unsigned" /></h5>
	<a	href=<c:url value="/signin" />>
				<spring:message	code="crsms.button.signin" />
			</a>
	</c:otherwise>
	</c:choose>
</div>
<div id = "language-picker">
	<div class = "align-right">
		<div class="btn-group" role="group" aria-label="...">
			<a href = "?lang=en" class = "btn btn-default">EN</a>
			<a href = "?lang=uk" class = "btn btn-default">UA</a>
		</div>
	</div>
</div>