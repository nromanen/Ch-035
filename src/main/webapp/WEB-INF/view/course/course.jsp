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
		<p><spring:message code="crsms.courses.text.duration" /></p>
		<h3>${course.duration} <spring:message code="crsms.courses.text.weeks" /></h3>
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
		<h3 class="module-title">${module.name} 
			<c:if test="${module.complete}">
				<i class="fa fa-check text-success"></i>
				<b>(<c:out value="${module.score}" />/<c:out value="${module.totalScore}" />)</b>
	 		</c:if>
		</h3>
		<p class="module-desc">${module.description}</p>
		
		<div class="module-resources module-unit-grid col-md-6">
			<h4 class="module-units-title text-uppercase${fn:length(module.resources) == 0 ? ' hidden' : ''}"
				><spring:message code="crsms.modules.text.resources" /></h4>
			<c:forEach var="moduleResource" items="${module.resources}">
			<div class="module-resource">
				<c:choose>
					<c:when test="${moduleResource.type == 'EMBEDDED'}">
						<a class="btn btn-default" role="button" data-toggle="collapse" 
							href="#resource-${moduleResource.id}-collapse" aria-expanded="false" aria-controls="collapseExample"
							><i class="fa fa-lg fa-play-circle-o"></i> ${moduleResource.name}</a>
						<div class="collapse" id="resource-${moduleResource.id}-collapse">
							<div class="well">
						    	<div class="embed-responsive embed-responsive-16by9">
								  	<iframe class="embed-responsive-item" src="${moduleResource.path}"></iframe>
								</div>
						  	</div>
						</div>
					</c:when>
					<c:when test="${moduleResource.type == 'FILE'}">
						<c:url value="/resources/downloadfile" var="fileDownloadURL">
						  	<c:param name="id" value="${moduleResource.id}" />
						</c:url>
						<a class="btn btn-default" href="${fileDownloadURL}" 
							class="module-resource-link"><i class="fa fa-lg fa-download"></i> ${moduleResource.name}</a>
					</c:when>
				</c:choose>		
			</div>
			</c:forEach>
		</div>
		
		<div class="module-tests module-unit-grid col-md-6">
			<h4 class="module-units-title text-uppercase${fn:length(module.tests) == 0 ? ' hidden' : ''}"
				><spring:message code="crsms.modules.text.tests" /></h4>
				
			<c:forEach var="moduleTest" items="${module.tests}">
			<div class="module-test">
				<c:url var = "showTest" value = "${course.id}/modules/${module.id}/tests/${moduleTest.id}/show/1" />
				<a class="btn btn-default" href = "${showTest}" >
					<c:choose>	
						<c:when test="${moduleTest.complete}">
							<i class="fa fa-lg fa-check-square-o"></i> 
				 		</c:when>
				 		<c:otherwise>
				 			<i class="fa fa-lg fa-square-o"></i> 
						</c:otherwise>
			 		</c:choose>
				
					${moduleTest.name} 
					<c:if test="${moduleTest.complete}">
						<b>(<c:out value="${moduleTest.score}" />/100)</b>
			 		</c:if>
				</a>
			</div>
			</c:forEach>
		</div>
		
	</div>
	</c:forEach>
</div>

