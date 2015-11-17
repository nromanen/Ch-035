<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="unit-dashboard container-fluid">
	<div class="col-md-4 unit-dashboard-item">
		<p><spring:message code="crsms.text.status" /></p>
		<h3><spring:message code="${course.open ? 
					'crsms.courses.text.opened' : 'crsms.courses.text.closed'}" /></h3>
	</div>
	<div class="col-md-4 unit-dashboard-item">
		<p><spring:message code="crsms.text.date" /></p>
		<h3><joda:format value="${course.startDate}" pattern="dd.MM.yyyy"/> - <joda:format value="${courseEndDate}" pattern="dd.MM.yyyy"/></h3>
	</div>
	<div class="col-md-4 unit-dashboard-item">
		<p><spring:message code="crsms.courses.text.modules.count" /></p>
		<h3>${fn:length(course.modules)}</h3>
	</div>
</div>

<div id="course-wrapper" class="container">
	<h4 class="course-title"><spring:message code="crsms.courses.text.description" /></h4>
	<p class="course-desc">${course.description}</p>
</div>

<div id="course-modules-wrapper">
	<c:forEach var="module" items="${course.modules}">
	<div class="course-module-grid container">
		<h3 class="module-title">${module.name}</h3>
		<p class="module-desc">${module.description}</p>
		<div class="module-tests">
			<c:forEach var="moduleTest" items="${module.tests}">
			<div class="module-test">${moduleTest.name}</div>
			</c:forEach>
		</div>
		<div class="module-resources">
			<c:forEach var="moduleResource" items="${module.resources}">
			<div class="module-resource-wrapper">
				<a href="/crsms/resources/downloadfile?filename=${moduleResource.name}" class="module-resource-link">${moduleResource.name}</a>			
			</div>
			</c:forEach>
		</div>
	</div>
	</c:forEach>
</div>

