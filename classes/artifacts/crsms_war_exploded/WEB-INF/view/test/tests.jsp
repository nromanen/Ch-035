<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<table class="table table-bordered table-hover">
	<thead>
		<tr class="success">
			<th><spring:message code="crsms.text.name" /></th>
			<th><spring:message code="crsms.question.text.content" /></th>
			<th colspan="2"><spring:message code="crsms.text.controls" /></th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="test" items="${tests}">
			<tr class="active">
				<td><i class="glyphicon glyphicon-list-alt">&nbsp</i>${test.name}</td>
				<td>
					<div align="center">
						<c:url var="showQuestions" value="${test.id}/questions/" />
						<a class="btn btn-success" href="${showQuestions}">
							<spring:message code="crsms.question.text.questions.list" />
						</a>
					</div>
				</td>
				<td>
					<div align="center">
						<c:url var="editTest" value="${test.id}/edit" />
						<a href="${editTest}" class="btn btn-success btn-sm"
							data-toggle="tooltip"
							title="<spring:message code="crsms.button.edit" />"> 
							<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
						</a>

						<c:url var="deleteTest" value="${test.id}/delete" />
						<a href="${deleteTest}"
							class="btn btn-danger btn-sm"
							data-toggle="tooltip"
							title="<spring:message code="crsms.button.delete" />"
							value="${test.id}"> <span class="glyphicon glyphicon-remove"
							aria-hidden="true"></span>
						</a>
					</div>
				</td>

			</tr>
		</c:forEach>
	</tbody>
</table>
	
<c:set var="backButton">
	<spring:message code="crsms.createtest.backButton" />
</c:set>
<a class="btn btn-success" role="button" onClick="history.go(-1);return true;">${backButton}</a> 

<c:url var="createTest" value="add" />
<a class="btn btn-success" href="${createTest}"><spring:message code="crsms.tests.createNew" /></a>