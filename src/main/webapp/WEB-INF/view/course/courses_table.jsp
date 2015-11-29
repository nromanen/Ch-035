<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div class = "container">
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
</div>

<div id = "courses-table-wrapper" class = "container">
	<table class="table table-bordered table-hover">
		<thead>
			<tr class = "active">
				<th class = "text-center"><spring:message code="crsms.courses.text.name" /></th>
				<%-- <th class = "text-center"><spring:message code="crsms.courses.text.startDate" /></th> --%>
				<th class = "text-center"><spring:message code="crsms.courses.text.duration" /></th>
				<th class = "text-center"><spring:message code="crsms.courses.text.open" /></th>
				<th class = "text-center"><spring:message code="crsms.courses.text.area" /></th>
				<th class = "text-center"><spring:message code="crsms.courses.text.modules" /></th>
				<th class = "text-center"><spring:message code="crsms.courses.text.groups" /></th>
				<th class = "text-center"><spring:message code="crsms.courses.text.management" /></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="course" items="${courses}">
				<tr>
					<td><c:out value="${course.name}"/></td>
					<%-- <td><joda:format pattern="dd.MM.yyyy" value="${course.startDate}"  /></td> --%>
					<td>${course.duration} <spring:message code="crsms.courses.text.weeks" /></td>
					<td align="center">
						<c:choose>
							<c:when test="${course.open == 'true'}">
						    	<span class="glyphicon glyphicon-ok-circle text-success"></span>
							</c:when>
							<c:otherwise>
						    	<span class = "glyphicon glyphicon-ban-circle text-danger"></span>
							</c:otherwise>
						</c:choose>
	
					</td>
					<td>${course.area.name}</td>
					<td>
						<div align="center">
							<a class="btn btn-primary btn-sm" href="${course.id}/modules/"><spring:message code="crsms.courses.text.modules" /></a>
						</div>
					</td>
					<td>
						<div align="center">
							<a class="btn btn-primary btn-sm" href="${course.id}/groups/"><spring:message code="crsms.courses.text.groups" /></a>
						</div>
					</td>
					<td>
						<div align="center">
							<c:if test="${!course.published}">
								<a href="${course.id}/publish"
								class="btn btn-primary btn-sm"
								data-toggle="tooltip"
								title="<spring:message code="crsms.courses.button.publish" />"
							>
								<span class="glyphicon glyphicon-pushpin" ></span>
							</a>
							</c:if>
							<a href="${course.id}/edit"
								class="btn btn-primary btn-sm"
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
</div>

<div class = "container">
	<c:url var="createModule" value="add" />
	<a class="btn btn-primary" href="${createModule}"><spring:message code="crsms.courses.button.create" /></a>
</div>