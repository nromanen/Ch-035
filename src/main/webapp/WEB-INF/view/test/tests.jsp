<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

	<table class="table table-bordered table-hover">
		<thead>
			<tr class="success">
				<th><spring:message code="crsms.tests.name" /></th>
				
				<th colspan="2"><spring:message code="crsms.tests.management" /></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="test" items="${tests}">
				<tr class="active">
					<td><i class="glyphicon glyphicon-list-alt">&nbsp</i>${test.name}</td>
					
					<td>
						<div align="center">
							<c:url var="editTest" value="${test.id}/edit" />
							<a href="${editTest}" class="btn btn-success btn-sm"
								data-toggle="tooltip"
								title="<spring:message code="crsms.tests.edit" />"> <span
								class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
							</a>

							<c:url var="deleteTest" value="${test.id}/delete" />
							<a href="${deleteTest}"
								class="btn btn-danger btn-sm"
								data-toggle="tooltip"
								title="<spring:message code="crsms.tests.delete" />"
								value="${test.id}"> <span class="glyphicon glyphicon-remove"
								aria-hidden="true"></span>
							</a>
						</div>
					</td>

				</tr>
			</c:forEach>
		</tbody>
	</table>

	<c:url var="createTest" value="add" />
	<a class="btn btn-success pull-left" href="${createTest}"><spring:message
			code="crsms.tests.createNew" /></a>