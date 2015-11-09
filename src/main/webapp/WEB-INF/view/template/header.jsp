
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
<div id="language-picker" class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
	<div class = "align-right">
		<div class="btn-group" role="group" aria-label="...">
			<a href = "?lang=en" class = "btn btn-default">EN</a>
			<a href = "?lang=uk" class = "btn btn-default">UA</a>
		</div>
	</div>
</div>