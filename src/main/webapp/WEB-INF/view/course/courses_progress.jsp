<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>



<div  class = "container">
	<div class=" row col-xs-4">
		<c:choose>
			<c:when test="${ empty courses.user.userInfo.image }">
				<c:url var="avatarUrl" value="/resources/images/default_logo.png" />
				<c:set var="imgSrc" value="${ avatarUrl }" />
			</c:when>
			<c:otherwise>
				<c:set var="imgSrc" value="${ courses.user.userInfo.image }" />
			</c:otherwise>
		</c:choose>
		<img  src="${ imgSrc }" class="form-control" id="imagePreview" alt="Avatar" style="width:170px;height:170px;" />
		<b>
			<c:out value="${courses.user.userInfo.firstName}"/> 
			<c:out value="${courses.user.userInfo.lastName}"/>
		</b>
	</div>
	<dl class="dl-horizontal row col-xs-4">
	  <dt><spring:message code="crsms.courses.text.all_curses" /></dt>
	  <dd>${courses.allCurses}</dd>
	  <dt><spring:message code="crsms.courses.text.passed_curses" /></dt>
	  <dd>${courses.passedCurses}</dd>
	  <dt><spring:message code="crsms.courses.text.failed_curses" /></dt>
	  <dd>${courses.failedCurses}</dd>
	  <dt><spring:message code="crsms.courses.text.continued_curses" /></dt>
	  <dd>${courses.continuedCurses}</dd>
	</dl>
	
	<dl class="dl-horizontal row col-xs-4">
	  <dt><spring:message code="crsms.text.score" /></dt>
	  <dd>${courses.score} / ${courses.maxScore}</dd>
	  <dt><spring:message code="crsms.courses.text.average_score" /></dt>
	  <dd>${courses.progress}%</dd>
	</dl>
</div>
<div id = "courses-table-wrapper" class = "container">

	<table class="table table-bordered table-hover">
		<caption align="top"><h3 align="center"><b><spring:message code="crsms.courses.text.your_courses" /></b></h3></caption>
		<thead>	
			<tr class = "active">
			<th class = "text-center"><spring:message code="crsms.text.status" /></th>
				<th class = "text-center"><spring:message code="crsms.courses.text.name" /></th>
				<th class = "text-center"><spring:message code="crsms.courses.text.area" /></th>
				<th class = "text-center"><spring:message code="crsms.courses.text.modules" /></th>
				<th class = "text-center"><spring:message code="crsms.text.score" /></th>
				<th class = "text-center"><spring:message code="crsms.courses.text.management" /></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="course" items="${courses.courseViewDtos}">
				<tr>
					<td align="center">
						<c:choose>
							<c:when test="${course.passedModules == course.allModule }">
								<i class="fa fa-lg fa-check text-success"
									data-toggle="tooltip" data-placement="top" title="<spring:message code="crsms.courses.text.status.passed" />"
								></i> 
							</c:when>
							<c:when test="${course.failedModule > 0 }">
								<i class="fa fa-lg fa-times text-danger"
									data-toggle="tooltip" data-placement="top" title="<spring:message code="crsms.courses.text.status.failed" />"
								></i> 
							</c:when>
							<c:otherwise>
								<i class="fa fa-lg fa-book text-primary"
									data-toggle="tooltip" data-placement="top" title="<spring:message code="crsms.courses.text.status.continue" />"
								></i> 
							</c:otherwise>
						</c:choose>
					</td>
					<td>
						<a  href="<c:url value="/courses/${course.id}" />">
							<c:out value="${course.name}"/>
						</a>
					</td>
					<td>${course.area.name}</td>
					<td>
						<div class="progress" align="center">
							<div class="progress-bar progress-bar-success" 
						  		style="width: <c:out value="${course.passedModules*100.0 / course.allModule}"/>%"
						  		data-toggle="tooltip" data-placement="top" title="<spring:message code="crsms.courses.text.passed_modules" />"
					  		>
						  		<span>${course.passedModules}</span>
						 	</div>
							<div class="progress-bar progress-bar-danger" 
								style="width: <c:out value="${course.failedModule*100.0 / course.allModule}"/>%"
								data-toggle="tooltip" data-placement="top" title="<spring:message code="crsms.courses.text.failed_module" />"
							>
								<span>${course.failedModule}</span>
							</div>
							<span
								data-toggle="tooltip" data-placement="top" title="<spring:message code="crsms.courses.text.no_passed_modules" />"
							>
								<c:out value="${course.allModule - course.failedModule - course.passedModules}"/>
							</span>
						</div>
					</td>
					<td align="center"><b>${course.score}/${course.totalScore}</b></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>