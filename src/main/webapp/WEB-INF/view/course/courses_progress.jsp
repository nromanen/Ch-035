<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<sec:authentication var="user" property="principal" />


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
				<th class = "text-center"><spring:message code="crsms.courses.text.groups" /></th>
				<th class = "text-center"><spring:message code="crsms.courses.text.area" /></th>
				<th class = "text-center"><spring:message code="crsms.courses.text.modules" /></th>
				<th class = "text-center"><spring:message code="crsms.text.score" /></th>
				<c:if test="${user.username == courses.user.email}">
					<th class = "text-center"><spring:message code="crsms.courses.text.management" /></th>
				</c:if>
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
					<td>${course.group.name}</td>
					<td>${course.area.name}</td>
					
					<td >
						<div class="progress" align="center" style="margin-bottom: 0px;" >
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
					<c:if test="${user.username == courses.user.email}">
						<td align="center">
							<a id="btn-leave" type="button" class="btn btn-danger" href="/crsms/groups/${course.group.id}/leave" >
								<spring:message code = "crsms.courses.button.leave" />
							</a>
						</td>
					</c:if>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>

<!-- Unsubscribe modal -->
<div class="modal fade" id="unsubscribeModal" tabindex="-1" role="dialog" aria-labelledby="unsubscribeModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				<h4 class="modal-title" id="unsubscribeModalLabel"><spring:message code = "crsms.courses.button.leave" /></h4>
			</div>
			<div class="modal-body">
				<spring:message code = "crsms.courses.message.confirm.leave" />
			</div>
			<div class="modal-footer">
				<button id="btn-leave" type="button" class="btn btn-primary">
					<spring:message code = "crsms.courses.button.leave" />
				</button>
				 <button type="button" class="btn btn-default" data-dismiss="modal">
				 	<spring:message code = "crsms.button.close" />
				 </button>
			</div>
		</div>
	</div>
</div>