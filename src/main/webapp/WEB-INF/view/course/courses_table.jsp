<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<input type = "hidden" id = "api-url" value = "${initParam['apiUrl']}">

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
							<a class="btn btn-primary btn-sm" href="${course.id}/modules/">
								<span class="glyphicon glyphicon-list-alt" aria-hidden="true"></span> <spring:message code="crsms.courses.text.modules" />
							</a>
						</div>
					</td>
					<td>
						<div align="center">
							<a class="btn btn-primary btn-sm" href="${course.id}/groups/">
								<span class="glyphicon glyphicon-list-alt" aria-hidden="true"></span> <spring:message code="crsms.courses.text.groups" />
							</a>
						</div>
					</td>
					<td>
						<div align="center">
							<c:choose>
								<c:when test="${!course.published}">
									<span data-toggle = "tooltip" 
										  data-trigger = "hover"
										  title = "<spring:message code="crsms.courses.button.publish" />">
										<button class = "btn btn-primary btn-sm publish-btn"
												data-toggle = "modal"
												data-target="#publishModal"
												data-course-id = "${course.id}">
											<span class="glyphicon glyphicon-pushpin" ></span>
										</button>
									</span>
								</c:when>
								<c:otherwise>
									<button class = "btn btn-default btn-sm published-btn">
										<span data-toggle = "tooltip"
										  title = "<spring:message code="crsms.courses.text.published" />" 
										  class="glyphicon glyphicon-pushpin" ></span>
									</button>
								</c:otherwise>
							</c:choose>
							<a href="${course.id}"
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
	<a class="btn btn-primary" id="add" href="${createModule}"><spring:message code="crsms.courses.button.create" /></a>
</div>

<!-- Publish modal -->
<div class="modal fade" id="publishModal" tabindex="-1" role="dialog" aria-labelledby="publishModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				<h4 class="modal-title" id="publishModalLabel"><spring:message code="crsms.courses.text.publishing" /></h4>
			</div>
			<div class="modal-body">
				<div id = "confirm-publishing">
					<spring:message code="crsms.courses.message.confirm.publishing" />
				</div>
				<div id = "no-groups">
					<spring:message code="crsms.courses.message.no.groups" />
				</div>
			</div>
			<div class="modal-footer">
				<a id = "create-group-btn" class = "btn btn-primary">
							<spring:message code="crsms.courses.button.create.group" />
				</a>
				<button id="publish-btn" type="button" class="btn btn-primary">
					<spring:message code="crsms.courses.button.publish" />
				</button>
				 <button type="button" class="btn btn-default" data-dismiss="modal">
				 	<spring:message code = "crsms.button.close" />
				 </button>
			</div>
		</div>
	</div>
</div>