<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
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
				<td>${course.weekDuration} weeks</td>
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
						<a class="btn btn-warning btn-sm" href="courses/${course.id}/modules/"><spring:message code="crsms.courses.text.modules" /></a>
					</div>
				</td>
				<td>
					<div align="center">
						<a href="courses/${course.id}/edit"
							class="btn btn-primary btn-sm" 
							data-toggle="tooltip"
							title="<spring:message code="crsms.button.edit" />"
						>
							<span class="glyphicon glyphicon-pencil" />
						</a>
						<a 	href="courses/${course.id}/delete"
							class="btn btn-danger btn-sm" 
							data-toggle="tooltip"
							title="<spring:message code="crsms.button.delete" />"
						>
							<span class="glyphicon glyphicon-remove" />
							
						</a>
					</div>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<a class="btn btn-success pull-left" href="courses/add"><spring:message code="crsms.courses.button.create_new_course" /></a>