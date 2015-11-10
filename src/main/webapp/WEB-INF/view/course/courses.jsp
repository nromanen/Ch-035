<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<div>
	<a class="course-add" href="add" >
		<i class="fa fa-plus-square-o" data-toggle="tooltip" 
			title="<spring:message code = "crsms.courses.button.create_new_course" />"></i>
	</a>
</div>
<div class="container">
<c:forEach var="course" items="${courses}">
	<div class="course-grid col-lg-4 col-md-4 col-sm-6 col-xs-12">
		<div class="ribbon-corner ${course.open ? 
					'ribbon-corner-blue' : 'ribbon-corner-grey'}">
			<span>
				<spring:message code="${course.open ? 
					'crsms.courses.text.opened' : 'crsms.courses.text.closed'}" />
			</span>
		</div>
		<div class="course-inner-wrapper">
			<div class="course-info">
				<h3 class="course-title"><a href="${course.id}">${course.name}</a></h3>
				<p class="course-desc">${course.description}</p>
				<div class="course-date">
					<b><spring:message code="crsms.courses.text.startDate" /></b>: ${course.startDate.dayOfMonth}.${course.startDate.monthOfYear}.${course.startDate.year}
				</div>
			</div>			
			<div class="course-control">
				<sec:authorize access="hasAnyRole('ROLE_STUDENT', 'ROLE_ANONYMOUS')">
					<c:choose>
					<c:when test="${pageContext.request.userPrincipal.name != null}">
						<div class="text-left course-enroll pull-left">
						<button class="btn btn-default"><strong><spring:message code="crsms.courses.button.enroll" /></strong></button>
					</div>
					</c:when>
					<c:otherwise>
						<div class="text-left course-enroll pull-left">
						<button class="btn btn-default disabled" data-toggle="tooltip" data-placement="bottom"
						title="<spring:message code="crsms.text.signin.unsigned" />">
						<strong><spring:message code="crsms.courses.button.enroll" /> </strong></button>
					</div>
					</c:otherwise>
					</c:choose>
				</sec:authorize>
				<div class="text-right course-detailed">
					<a href="${course.id}" class="btn btn-default"><strong><spring:message code="crsms.button.detailed" /></strong></a>
				</div>
			</div>
		</div>
	</div>
</c:forEach>
</div>

<!--  
<table class="table table-bordered table-hover">
	<thead>
		<tr class="success">
			<th><spring:message code="crsms.courses.text.name" /></th>
			<th><spring:message code="crsms.courses.text.startDate" /></th>
			<th><spring:message code="crsms.courses.text.duration" /></th>
			<th><spring:message code="crsms.courses.text.open" /></th>
			<th><spring:message code="crsms.courses.text.area" /></th>
			<th><spring:message code="crsms.courses.text.modules" /></th>
			<th><spring:message code="crsms.courses.text.management" /></th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="course" items="${courses}">
			<tr>
				<td><c:out value="${course.name}"/></td>
				<td><joda:format pattern="dd.MM.yyyy" value="${course.startDate}"  /></td>
				<td>${course.weekDuration} <spring:message code="crsms.courses.text.weeks" /></td>
				<td align="center">
					<c:choose>
						<c:when test="${course.open == 'true'}">
					    	<span 	class="glyphicon glyphicon-ok-circle text-success" data-toggle="tooltip"
					    			title="<spring:message code="crsms.text.true" />" >
			    			</span>
						</c:when>
						<c:otherwise>
					    	<span 	class="glyphicon glyphicon-ban-circle text-danger" data-toggle="tooltip"
					    			title="<spring:message code="crsms.text.false" />" >
			    			</span>
						</c:otherwise>
					</c:choose>

				</td>
				<td>${course.area.name}</td>
				<td>
					<div align="center">
						<a class="btn btn-success btn-sm" href="${course.id}/modules/"><spring:message code="crsms.courses.text.modules" /></a>
					</div>
				</td>
				<td>
					<div align="center">
						<a href="${course.id}/edit"
							class="btn btn-success btn-sm"
							data-toggle="tooltip"
							title="<spring:message code="crsms.button.edit" />"
						>
							<span class="glyphicon glyphicon-pencil" ></span>
						</a>
						<a 	href="${course.id}/delete"
							class="btn btn-danger btn-sm"
							data-toggle="tooltip"
							title="<spring:message code="crsms.button.delete" />"
						>
							<span class="glyphicon glyphicon-remove" ></span>
						</a>
					</div>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
-->

	
	
