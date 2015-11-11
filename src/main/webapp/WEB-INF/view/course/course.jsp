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
<div id="course-outer-wrapper">
	<div id="course-wrapper" class="container">
		<h4 class="course-title"><spring:message code="crsms.courses.text.description" /></h4>
		<p class="course-desc">${course.description}</p>
	</div>
</div>
