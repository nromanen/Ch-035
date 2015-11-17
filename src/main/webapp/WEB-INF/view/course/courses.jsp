<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<div class = "container">
	<sec:authorize access="isAuthenticated()">
		<div id = "navigation" class = "pull-left">
			<ul class="nav nav-pills">
			  <li role="presentation" class = "${param.show == 'all' || empty param.show ? 'active' : '' }">
				  <a href="?show=all"><spring:message code = "crsms.courses.text.all" /></a>
			  </li>
			  <li role="presentation" class = "${param.show == 'my' ? 'active' : '' }">
			  	<a href="?show=my"><spring:message code = "crsms.courses.text.my" /></a>
			  </li>
			</ul>
		</div>
	</sec:authorize>
	
	<div id="courses-nav" class="text-right">
		<form id = "search-form" class="navbar-form navbar search" role="search" action="search" method="GET">
			<input type="text" class="form-control" name="searchWord" >
		    <button type="submit" class="btn btn-default"><span class="glyphicon glyphicon-search"></span>
		    <strong><spring:message code = "crsms.button.search" /></strong></button>
		</form>
	</div>
</div>

<div id = "courses-table" class="container">
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
			</div>
			<div class="course-date">
					<b><spring:message code="crsms.courses.text.startDate" /></b>: <joda:format value="${course.startDate}" pattern="dd.MM.yyyy"/>
			</div>			
			<div class="course-control">
				<sec:authorize access="hasAnyRole('STUDENT')">
					<div class="text-left course-enroll pull-left">
						<c:choose>
							<c:when test="${userCoursesId.contains(course.id)}">
								<a href = "${course.id}/leave" class="btn btn-default " >
									<strong><spring:message code="crsms.courses.button.leave" /></strong>
								</a>
							</c:when>
							<c:otherwise>
								<a href = "${course.id}/enroll" class="btn btn-default ${!course.open ? 'disabled' : ''}">
									<strong><spring:message code="crsms.courses.button.enroll" /></strong>
								</a>
							</c:otherwise>
						</c:choose>
					</div>
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
				<td>${course.duration} <spring:message code="crsms.courses.text.days" /></td>
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

	
	
