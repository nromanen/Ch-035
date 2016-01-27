<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<form:form id="userAnswerForm" modelAttribute = "userAnswerFormDto" class="form-horizontal" method="POST" >
	<input id="finished" type="hidden" name="finished" value="false" />
	<input id="nextIndex" type="hidden" name="nextIndex" value="
		<c:choose>
			<c:when test="${0 < questionIndex && questionIndex < questionCount}">
	 			${questionIndex + 1}
	 		</c:when>
	 		<c:otherwise>${questionCount}</c:otherwise>
 		</c:choose>
	" />
	<form:hidden path = "testResultId"/>
	<form:hidden path = "questionId"/>
	<div class="form-group">
		<div class="col-sm-offset-2 col-sm-10" >
			<nav>
			  <ul class="pagination">
			  	<c:forEach var="i" begin="1" end="${questionCount}">
					<li 
						class=" 
			  		 		<c:if test="${i == questionIndex}">
			  		 			<c:out value=" active " />
			  		 		</c:if>
			  		 		
						"
					>
			  		 	<a class="indexLink" style="
			  		 		<c:choose>
								<c:when test="${isAnsweredQuestions[i-1] == true}">
						 			<c:out value=" color: #449D44; " />
						 		</c:when>
						 		<c:otherwise>
									<c:out value=" color: #A94442; " />
								</c:otherwise>
					 		</c:choose>
			  		 	"
			  		 	
			  		 	href="<c:out value="${i}" />">
			  		 		<b><c:out value="${i}" /></b>
					 	</a>
					</li>
			  	</c:forEach>
				<li>
					<button id="finisButton" class="btn btn-default btn-primary"><spring:message code="crsms.test.text.tests.finish" /></button>
				</li>
			  </ul>
			</nav>
		</div>
	</div>
	<div class="form-group">
		<div class="col-sm-offset-2 col-sm-10" >
			<p><b>${question.text}</b></p>
		</div>
	</div>
	<c:forEach var="answer" items="${question.answers}">
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10" >
				<div class="checkbox">
					<label>
						<form:checkbox path="checkedAnswerIds" value="${answer.id}" /> ${answer.text}
					</label>
				</div>
			</div>
		</div>
	</c:forEach>
</form:form>

<!-- finished modal window -->
<div class="modal fade" id="finish-confirmation-modal" >
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title"><spring:message code = "crsms.tests.text.confirmation" /></h4>
      </div>
      <div class="modal-body">
        <p style="font-size: 15px; font-weight: 600;">
        	<spring:message code = "crsms.tests.msg.confirm.finish" /> 
        </p>
      </div>
      <div class="modal-footer">
      	<button id="btn-modal-finish-test" class="btn btn-primary"><spring:message code = "crsms.test.text.tests.finish" /></button>
        <button type="button" class="btn btn-default" data-dismiss="modal"><spring:message code = "crsms.button.cancel" /></button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->