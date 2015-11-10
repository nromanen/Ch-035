<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<tiles:importAttribute name="logo"/>
<tiles:importAttribute name="app-title" toName="appTitle" />
<c:url var="homeLink" value="/" />

<!-- logo -->
<div id="logo" class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
	<a href="${homeLink}">
		<img id="logo-img" src="<c:url value="${logo}"/>" />
		<span id="logo-app-title"><spring:message code = "${appTitle}" /></span>
	</a>
<!-- end logo -->
</div>

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