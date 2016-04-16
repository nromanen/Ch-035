
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<tiles:insertAttribute name="jquery-validation-messages"></tiles:insertAttribute>

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
					<div class="full-div test-name" data-toggle="collapse" data-target="#${test.id}" data-parrent="#accordion">
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
										   data-toggle="modal" data-target="#add-question" data-toggle="tooltip" title="<spring:message code="crsms.tests.tooltip.add.question" />">
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
															<a href="#"  class="nonUnderlineDelete pull-right" data-toggle="tooltip" title="<spring:message code="crsms.tests.tooltip.delete.question" />" value="${question.id}">
															<i class="glyphicon glyphicon-trash"></i></a>

															<c:url var="editQuestion" value="${test.id}/questions/${question.id}/edit" />
															<a href="${editQuestion}" id="btn-edit-question-${question.id}" class="nonUnderlineEdit pull-right btn-edit-question" data-toggle="tooltip" title="<spring:message code="crsms.tests.tooltip.edit.question" />" value="${question.id}">
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
						<button data-deleteurl="${deleteTest}" class="btn btn-danger btn-sm btn-delete-test" data-toggle="tooltip" title="<spring:message code="crsms.tests.tooltip.delete" />"
							value="${test.id}"> 
							<i class="fa fa-trash-o fa-lg"></i>
						</button>
					</div>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
	
<c:url var="createTest" value="add" />
<a class="btn btn-primary" href="${createTest}"><spring:message code="crsms.tests.create.new" /></a>

<!-- Add questions with answers modal window. -->
<div class="modal fade" id="add-question" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				<h4 class = "align-center" id="orangeQuote"><spring:message code="crsms.tests.text.add.question" /></h4>
			</div>
			<div class="modal-body">
			
				<form:form modelAttribute="question" method="POST" class="form-horizontal" id="add-question-form">
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
					 
					  <br>
 					  <div class="form-group">
					  	<label for="text" class="col-sm-2 control-label"><spring:message code="crsms.tests.answer" />&nbsp#1:</label>
					    <div class="col-sm-10">
					      <form:textarea path="answers[0].text" name="answers[0].text" value="" id="answer1"
					 				     class="form-control clear-textarea" placeholder="${answerVersion}" />
					      <form:errors path="text" cssClass = "label label-danger" />		
					      <div id="answer1-error" class="errorTxt"></div>
					      <div class="checkbox-inline">
						      <input type="checkbox" form = "add-question-form" name="answers[0].correct"
						      		 id="answers0" class="answer"> <spring:message code="crsms.tests.correct.answer" />
						  </div>
					    </div>
					  </div>
					  
					 <div class="form-group">
					  	<label for="text" class="col-sm-2 control-label"><spring:message code="crsms.tests.answer" />&nbsp#2:</label>
					    <div class="col-sm-10">
					      <form:textarea path="answers[1].text" name="answers[1].text" value="" id="answer2" class="form-control clear-textarea" placeholder="${answerVersion}" />
					      <form:errors path="text" cssClass = "label label-danger" />
					      <div id="answer2-error" class="errorTxt"></div>
					      <div class="checkbox-inline">
						      <input type="checkbox" form = "add-question-form" name="answers[1].correct" id="answers1"> <spring:message code="crsms.tests.correct.answer" />
						   </div>				      
					    </div>
					  </div>
					  
					  <div class="form-group">
					  	<label for="text" class="col-sm-2 control-label"><spring:message code="crsms.tests.answer" />&nbsp#3:</label>
					    <div class="col-sm-10">
					      <form:textarea path="answers[2].text" name="answers[2].text" value="" id="answer3" class="form-control clear-textarea" placeholder="${answerVersion}" />
					      <form:errors path="text" cssClass = "label label-danger" />	
					      <div id="answer3-error" class="errorTxt"></div>	
					      <div class="checkbox-inline">
						      <input type="checkbox" form = "add-question-form" name="answers[2].correct" id="answers2"> <spring:message code="crsms.tests.correct.answer" />
						   </div>		      
					    </div>
					  </div>
					  
					  <div class="form-group">
						<label for="text" class="col-sm-2 control-label"><spring:message code="crsms.tests.answer" />&nbsp#4:</label>
						<div class="col-sm-10">
						  <form:textarea path="answers[3].text" name="answers[3].text" value="" id="answer4" class="form-control clear-textarea" placeholder="${answerVersion}" />
						  <form:errors path="text" cssClass = "label label-danger" />
						  <div id="answer4-error" class="errorTxt"></div>
						  <div class="checkbox-inline">
							  <input type="checkbox" form = "add-question-form" name="answers[3].correct" id="answers3"> <spring:message code="crsms.tests.correct.answer" />
						  </div>
						  <br>
						  <label id="answers[0].correct-error" class="errorTxt" for="answers[0].correct" style="display: inline-block;"></label>			      
					    </div>
					  </div>

					<div class="form-group">
					    <div class="col-sm-offset-2 col-sm-10">
					      <span class="btn btn-primary pull-right" id="add-question-form-submit"><spring:message code="crsms.button.save" /></span>
					    </div>
					  </div>
				</form:form>
				
			</div>
		</div>
	</div>
</div>

<!-- Edit questions with answers modal window. -->
<div class="modal fade" id="edit-question" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				<h4 class = "align-center" id="orangeQuote"><spring:message code="crsms.tests.text.edit.question" /></h4>
			</div>
			<div class="modal-body">

				<form:form modelAttribute="question" method="POST" class="form-horizontal" id="edit-question-form">
					<form:input path="id" id ="id" type="hidden" />
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
					<input type="hidden" name="id" value="${id}"/>

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

					<br>
					<div class="form-group">
						<label for="text" class="col-sm-2 control-label"><spring:message code="crsms.tests.answer" />&nbsp#1:</label>
						<div class="col-sm-10">
							<form:textarea path="answers[0].text" name="answers[0].text" value="" id="answer1"
										   class="form-control clear-textarea" placeholder="${answerVersion}" />
							<form:errors path="text" cssClass = "label label-danger" />
							<div id="answer1-error" class="errorTxt"></div>
							<div class="checkbox-inline">
								<input type="checkbox" form = "edit-question-form" name="answers[0].correct"
									   id="answers0" class="answer"> <spring:message code="crsms.tests.correct.answer" />
							</div>
						</div>
					</div>

					<div class="form-group">
						<label for="text" class="col-sm-2 control-label"><spring:message code="crsms.tests.answer" />&nbsp#2:</label>
						<div class="col-sm-10">
							<form:textarea path="answers[1].text" name="answers[1].text" value="" id="answer2" class="form-control clear-textarea" placeholder="${answerVersion}" />
							<form:errors path="text" cssClass = "label label-danger" />
							<div id="answer2-error" class="errorTxt"></div>
							<div class="checkbox-inline">
								<input type="checkbox" form = "edit-question-form" name="answers[1].correct" id="answers1"> <spring:message code="crsms.tests.correct.answer" />
							</div>
						</div>
					</div>

					<div class="form-group">
						<label for="text" class="col-sm-2 control-label"><spring:message code="crsms.tests.answer" />&nbsp#3:</label>
						<div class="col-sm-10">
							<form:textarea path="answers[2].text" name="answers[2].text" value="" id="answer3" class="form-control clear-textarea" placeholder="${answerVersion}" />
							<form:errors path="text" cssClass = "label label-danger" />
							<div id="answer3-error" class="errorTxt"></div>
							<div class="checkbox-inline">
								<input type="checkbox" form = "edit-question-form" name="answers[2].correct" id="answers2"> <spring:message code="crsms.tests.correct.answer" />
							</div>
						</div>
					</div>

					<div class="form-group">
						<label for="text" class="col-sm-2 control-label"><spring:message code="crsms.tests.answer" />&nbsp#4:</label>
						<div class="col-sm-10">
							<form:textarea path="answers[3].text" name="answers[3].text" value="" id="answer4" class="form-control clear-textarea" placeholder="${answerVersion}" />
							<form:errors path="text" cssClass = "label label-danger" />
							<div id="answer4-error" class="errorTxt"></div>
							<div class="checkbox-inline">
								<input type="checkbox" form = "edit-question-form" name="answers[3].correct" id="answers3"> <spring:message code="crsms.tests.correct.answer" />
							</div>
							<br>
							<label id="answers[0].correct-error" class="errorTxt" for="answers[0].correct" style="display: inline-block;"></label>
						</div>
					</div>

					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<span class="btn btn-primary pull-right" id="edit-question-form-submit"><spring:message code="crsms.button.save" /></span>
						</div>
					</div>
				</form:form>

			</div>
		</div>
	</div>
</div>

<!-- Delete test modal window -->
<div class="modal fade" id="delete-confirmation-modal" >
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title"><spring:message code = "crsms.tests.text.confirmation" /></h4>
      </div>
      <div class="modal-body">
        <p style="font-size: 15px; font-weight: 600;">
        	<spring:message code = "crsms.tests.msg.confirm.delete" /> 
        	<span class="test-delete-msg"></span>
        </p>
      </div>
      <div class="modal-footer">
      	<button id="btn-modal-delete-test" class="btn btn-danger"><spring:message code = "crsms.button.delete" /></button>
        <button type="button" class="btn btn-default" data-dismiss="modal"><spring:message code = "crsms.button.cancel" /></button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->