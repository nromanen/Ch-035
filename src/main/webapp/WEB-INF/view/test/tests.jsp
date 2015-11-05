<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<table class="table table-bordered table-hover">
	<thead>
		<tr class="success">
			<th><spring:message code="crsms.text.name" /></th>
			<th width="15%"><spring:message code="crsms.text.controls" /></th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="test" items="${tests}">
			<tr data-toggle="collapse" data-target=#${test.id}>
				<td><a href="#"><i class="glyphicon glyphicon-list-alt">&nbsp</i><strong>${test.name}</strong></a>
					<div id=${test.id} class="collapse">
						<br>
						<blockquote class="greenQuote">
							<div class="panel-heading">
								<p class="panelHeaderText">
									Test questions
								<a role="button" class="text-success pull-right"><i class="glyphicon glyphicon-chevron-up"></i>Close</a>
								<a role="button" class="text-success pull-right" data-toggle="modal" data-target="#myModal">
									<i class="glyphicon glyphicon-plus"></i>Add&nbsp
								</a>
								</p>
							</div>
							<div class="panel-body">
						<c:forEach var="question" items="${test.questions}">
						<ul class="list-group">
							<div class="list-group-item list-group-item-warning">
								<i class="glyphicon glyphicon-question-sign">&nbsp</i>${question.text}
							</div>
						</ul>
						</c:forEach>
								</div>
						</blockquote>
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


<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				<h4 class="modal-title" id="myModalLabel">Add question</h4>
			</div>
			<div class="modal-body">
				I wanna add a new questions!
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-success" data-dismiss="modal">Close</button>
				<button type="button" class="btn btn-success">Save</button>
			</div>
		</div>
	</div>
</div>