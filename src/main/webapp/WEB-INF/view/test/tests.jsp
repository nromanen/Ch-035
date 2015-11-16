
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<table class="table table-bordered table-hover">
	<thead>
		<tr class=active>
			<th><spring:message code="crsms.tests.text.tests.name" /></th>
			<th width="15%"><spring:message code="crsms.text.controls" /></th>
		</tr>
	</thead>
	<tbody class="panel-group" id="accordion">
		<c:forEach var="test" items="${tests}">
			<tr id="hover-${test.id}">
				<td>
				<div class="hover-div">
					<div class="full-div" data-toggle="collapse" data-target="#${test.id}" data-parrent="#accordion">
						<i class="glyphicon glyphicon-list-alt">&nbsp</i>${test.name}
						</div>
					</div>
						<div id="${test.id}" class="collapse collapse-off" data-parent="hover-${test.id}">
							<br>
							<blockquote class="orangeQuote">
								<div class="panel-heading">
									<p class="panelHeaderText">
										<spring:message code="crsms.tests.test.test.questions" />
										<a role="button" class="text-primary pull-right close-div-button"><i class="glyphicon glyphicon-chevron-up"></i>
										<spring:message code="crsms.tests.close.accordion" />
										</a>
										<a value="${test.id}" id = "add-question-${test.id}" role="button" class="text-primary pull-right question-add" 
										   data-toggle="modal" data-target="#my-modal" data-toggle="tooltip" title="<spring:message code="crsms.tests.tooltip.add.question" />">
											<i class="glyphicon glyphicon-plus"></i><spring:message code="crsms.tests.add.question" />&nbsp
										</a>
									</p>
								</div>
								<div class="panel-body" id ="questions-${test.id}">
									<c:forEach var="question" items="${test.questions}">
										<div>
											<ul class="list-group" >
												<li class="list-group-item list-group-item-warning">
												<c:url var="questionById" value="${test.id}/questions/${question.id}/" />
													<a href="${questionById}" class="list-group-item-warning">
														${question.text}
														&nbsp
														<a href="#" class="nonUnderlineDelete pull-right" data-toggle="tooltip" title="<spring:message code="crsms.tests.tooltip.delete.question" />" value="${question.id}">
															<i class="glyphicon glyphicon-trash"></i></a>
														
														<a href="#" class="nonUnderlineEdit pull-right" data-toggle="tooltip" title="<spring:message code="crsms.tests.tooltip.edit.question" />" value="${question.id}">
															<i class="glyphicon glyphicon-pencil"></i>&nbsp</a>								
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
						<a href="${editTest}" class="btn btn-primary btn-sm" data-toggle="tooltip" title="<spring:message code="crsms.tests.tooltip.edit" />"> 
							<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
						</a>

						<c:url var="deleteTest" value="${test.id}/delete" />
						<a href="${deleteTest}" class="btn btn-danger btn-sm" data-toggle="tooltip" title="<spring:message code="crsms.tests.tooltip.delete" />"
							value="${test.id}"> 
							<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>	
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
<a class="btn btn-primary" role="button" onClick="history.go(-1);return true;">${backButton}</a>

<c:url var="createTest" value="add" />
<a class="btn btn-primary" href="${createTest}"><spring:message code="crsms.tests.create.new" /></a>

<!-- Modal -->
<div class="modal fade" id="my-modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				<h4 class = "align-center" id="orangeQuote"><spring:message code="crsms.tests.text.add.question" /></h4>
			</div>
			<div class="modal-body">
			
				<form:form modelAttribute="question" method="POST" class="form-horizontal" id="modal-form">
					<form:input path="id" id ="id" type="hidden" />
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
				  
				  	<c:set var="questionWord">
				      <spring:message code="crsms.question.word" />
				    </c:set>
				    <c:set var="questionTitle">
				      <spring:message code="crsms.question.title" />
				    </c:set>
				    <c:set var="answerVersion">
				      <spring:message code="crsms.answer.version" />
				    </c:set>
				  
					  <div class="form-group">
					    <label for="text" class="col-sm-2 control-label">${questionWord}:</label>
					    <div class="col-sm-10">
					      <form:textarea rows="3" path="text" id="text" class="form-control clear-textarea" placeholder="${questionTitle}" />
					      <form:errors path="text" cssClass = "label label-danger" />			      
					    </div>		    
					  </div>
					  
					<%-- 
					<c:forEach var="answer" items = "${question.answers}" varStatus = "answerStatus">
					  <div class="form-group">
					  	<label for="answer.text" class="col-sm-2 control-label"><spring:message code="crsms.tests.answer" />&nbsp#${answerStatus.index}:</label>
					    <div class="col-sm-10">
					      <form:textarea path="answer.text" id="answer${answerStatus.index}" class="form-control clear-textarea" placeholder="${answerVersion}" />
					      <form:errors path="answer.text" cssClass = "label label-danger" />
					      <div class="checkbox-inline">
						      <form:checkbox path="answer.correct"/> <spring:message code="crsms.tests.correct.answer" />
						   </div>			      
					    </div>
					  </div>
					  </c:forEach>
					  --%>
					 
					  	<br>
 					  <div class="form-group">
					  	<label for="text" class="col-sm-2 control-label"><spring:message code="crsms.tests.answer" />&nbsp#1:</label>
					    <div class="col-sm-10">
					      <form:textarea path="text" id="answer1" class="form-control clear-textarea" placeholder="${answerVersion}" />
					      <form:errors path="text" cssClass = "label label-danger" />		
					      <div class="checkbox-inline">
						      <input type="checkbox"> <spring:message code="crsms.tests.correct.answer" />
						      <!-- <input type="checkbox" name="answer1" value="yes"> <spring:message code="crsms.tests.correct.answer" /> -->
						  </div>
					    </div>
					  </div>
					  
					 <div class="form-group">
					  	<label for="text" class="col-sm-2 control-label"><spring:message code="crsms.tests.answer" />&nbsp#2:</label>
					    <div class="col-sm-10">
					      <form:textarea path="text" id="answer2" class="form-control clear-textarea" placeholder="${answerVersion}" />
					      <form:errors path="text" cssClass = "label label-danger" />
					      <div class="checkbox-inline">
						      <input type="checkbox"> <spring:message code="crsms.tests.correct.answer" />
						   </div>				      
					    </div>
					  </div>
					  
					  <div class="form-group">
					  	<label for="text" class="col-sm-2 control-label"><spring:message code="crsms.tests.answer" />&nbsp#3:</label>
					    <div class="col-sm-10">
					      <form:textarea path="text" id="answer3" class="form-control clear-textarea" placeholder="${answerVersion}" />
					      <form:errors path="text" cssClass = "label label-danger" />		
					      <div class="checkbox-inline">
						      <input type="checkbox"> <spring:message code="crsms.tests.correct.answer" />
						   </div>		      
					    </div>
					  </div>
					  
					  <div class="form-group">
					  	<label for="text" class="col-sm-2 control-label"><spring:message code="crsms.tests.answer" />&nbsp#4:</label>
					    <div class="col-sm-10">
					      <form:textarea path="text" id="answer4" class="form-control clear-textarea" placeholder="${answerVersion}" />
					      <form:errors path="text" cssClass = "label label-danger" />	
					      <div class="checkbox-inline">
						      <input type="checkbox"> <spring:message code="crsms.tests.correct.answer" />
						   </div>			      
					    </div>
					  </div>
					
					  <div class="form-group">
					    <div class="col-sm-offset-2 col-sm-10">
					      <span class="btn btn-primary pull-right" id="modal-form-submit"><spring:message code="crsms.button.save" /></span>
					    </div>
					  </div>
				</form:form>
				
			</div>
		</div>
	</div>
</div>