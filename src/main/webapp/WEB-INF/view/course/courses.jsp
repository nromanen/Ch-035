<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<tiles:insertAttribute name="jquery-validation-messages"></tiles:insertAttribute>

<div class = "container">
	<sec:authorize access="isAuthenticated() and !hasAnyRole('MANAGER')">
		<div id = "navigation" class = "pull-left">
			<ul class="nav nav-pills">
			  <li role="presentation" class = "${param.show == 'all' || empty param.show ? 'active' : '' }">
				  <a href="/crsms/courses/?show=all"><spring:message code = "crsms.courses.text.all" /></a>
			  </li>
			  <li role="presentation" class = "${param.show == 'my' ? 'active' : '' }">
			  	<a href="/crsms/courses/?show=my"><spring:message code = "crsms.courses.text.my" /></a>
			  </li>
			</ul>
		</div>
	</sec:authorize>
	
	<div id="courses-nav" class="container text-right">
		<div class="errorTxt"></div>
	<c:if test="${empty courses}"><strong><spring:message code = "crsms.courses.message.not.found"/></strong></c:if>
	<form id = "searchForm" class="navbar-form navbar search" role="search" action="search" method="GET">
		<input id = "search" type="text" class="form-control" name="searchWord" value = "${searchWord}" >
		    <button type="button" onclick="ClearField();" class="btn btn-default">
		    	<span class="glyphicon glyphicon-remove"></span>
		    </button>
	    <button type="submit" id="submit" class="btn btn-default"><span class="glyphicon glyphicon-search"></span>
	    	<strong><spring:message code = "crsms.button.search" /></strong>
	    </button>
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
