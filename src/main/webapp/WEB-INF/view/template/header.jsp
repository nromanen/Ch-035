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
		<span id="logo-app-title" class="inline-block"><spring:message code = "${appTitle}" /></span>
	</a>
<!-- end logo -->
</div>

 <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">	
	<div id = "language-picker" class="text-right">
		<div class = "align-right">
			<div class="btn-group" role="group" aria-label="...">
				<c:url var="langEN" value="">
					<c:forEach items="${param}" var="entry">
					    <c:if test="${entry.key != 'lang'}">
					        <c:param name="${entry.key}" value="${entry.value}" />
					    </c:if>
					</c:forEach>
					<c:param name="lang" value="en" />
				</c:url>
				<a href = "${langEN}" class = "btn btn-default">EN</a>
				
				<c:url var="langUK" value="">
					<c:forEach items="${param}" var="entry">
					    <c:if test="${entry.key != 'lang'}">
					        <c:param name="${entry.key}" value="${entry.value}" />
					    </c:if>
					</c:forEach>
					<c:param name="lang" value="uk" />
				</c:url>
				<a href = "${langUK}" class = "btn btn-default">UA</a>
			</div>
		</div>
	</div>

	<div id="securityprincipal" align="right">
		<c:choose>
			<c:when test="${pageContext.request.userPrincipal.name != null}">
				<div class="dropdown">
					<a id="dLabel" data-target="#"
						href="<c:url value="/courses/?show=my" />" data-toggle="dropdown"
						role="button" aria-haspopup="true" aria-expanded="false"> <b>${pageContext.request.userPrincipal.name}</b>
						<span class="caret"></span>
					</a>
					<ul class="dropdown-menu dropdown-menu-right">
						<li><a href="<c:url value="/courses/?show=my" />"> <spring:message
									code="crsms.courses.text.title.list" /></a></li>
						<li><a href="<c:url value="/userProfile" />"> <spring:message
									code="crsms.userProfile.profile" /></a></li>
						<li><a href="<c:url value="/signout" />"><spring:message
									code="crsms.button.signout" /></a></li>
					</ul>
				</div>
			</c:when>
			<c:otherwise>
				<h5>
					<spring:message code="crsms.text.signin.unsigned" />
				<a href=<c:url value="/signin" />> <spring:message
						code="crsms.button.signin" />
				</a>
				</h5>
				
			</c:otherwise>
		</c:choose>
	</div>


</div>