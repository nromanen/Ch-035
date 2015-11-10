<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<table class="table table-bordered table-hover">
	<thead>
		<tr class="success">
			<th><spring:message code="crsms.text.name" /></th>
			<th width="15%"><spring:message code="crsms.text.controls" /></th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="test" items="${tests}">
			<tr data-toggle="collapse" data-target="#${test.id}">
				<td><div class="hover-div" id="hover-${test.id}"><i class="glyphicon glyphicon-list-alt">&nbsp</i><strong>${test.name}</strong></div>
					<div id="${test.id}" class="collapse div-show">
						<br>
						<blockquote class="greenQuote">
							<div class="panel-heading">
								<p class="panelHeaderText">
									Test questions
								<a role="button" class="text-success pull-right"><i class="glyphicon glyphicon-chevron-up"></i>Close</a>
								<a value="${test.id}" role="button" class="text-success pull-right question-add" data-toggle="modal" data-target="#my-modal">
									<i class="glyphicon glyphicon-plus"></i>Add&nbsp
								</a>
								</p>
							</div>
							<div class="panel-body" id ="questions-${test.id}">
								<c:forEach var="question" items="${test.questions}">
								<div id="info">
									<ul class="list-group" >
										<li class="list-group-item list-group-item-warning">
										<c:url var="questionById" value="${test.id}/questions/${question.id}/" />
										<a href="${questionById}" class="list-group-item-warning">
											${question.text}
											&nbsp
											<a href="#" class="nonUnderlineDelete pull-right"><i class="glyphicon glyphicon-trash"></i></a>
											<a href="#" class="nonUnderlineEdit pull-right"><i class="glyphicon glyphicon-pencil"></i>&nbsp</a>								
										</a>
										</li>
									</ul>
								</div>
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
<div class="modal fade" id="my-modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				<h4 class="modal-title" id="myModalLabel">Add question</h4>
			</div>
			<div class="modal-body">	
			
				<form:form modelAttribute="question" method="POST" class="form-horizontal" id="modalForm">
				  <form:input path="id" id ="id" type="hidden" />
				  <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
				  <div class="form-group">
				    <c:set var="questionTitle">
				      <spring:message code="crsms.question.title" />
				    </c:set>
				    <label for="text" class="col-sm-2 control-label">${questionTitle}:</label>
				    <div class="col-sm-10">
				      <form:textarea rows="3" path="text" id="text" class="form-control clear-textarea" placeholder="${questionTitle}" />
				      <form:errors path="text" cssClass = "label label-danger" />
				    </div>
				  </div>
				
				  <div class="form-group">
				    <div class="col-sm-offset-2 col-sm-10">
				      <span class="btn btn-success" id="modal-form-submit"><spring:message code="crsms.button.save" /></span>
				    </div>
				  </div>
				</form:form>
				
			</div>

		</div>
	</div>
</div>