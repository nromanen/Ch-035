<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<form:form modelAttribute = "userAnswerFormDto" class="form-horizontal" method="POST" >
	<form:hidden path = "testResultId"/>
	<form:hidden path = "questionId"/>
	<%-- <form:select path="answerIds"
		items="${question.answers}"
		itemValue="id"
		itemLabel="text">
	</form:select> --%>
	<div class="form-group">
		<div class="col-sm-offset-2 col-sm-10" >
			<nav>
			  <ul class="pagination">
			  	<c:forEach var="i" begin="1" end="${questionCount}">
					<li 
						class=" 
			  		 		<c:if test="${i == questionIndex}">
			  		 			<c:out value="active" />
			  		 		</c:if>
						"
					>
			  		 	<a href="<c:out value="${i}" />">
			  		 		<c:out value="${i}" />
					 	</a>
					</li>
			  	</c:forEach>
			
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
						<form:checkbox path="answerIds" value="${answer.id}" /> ${answer.text}
					</label>
				</div>
			</div>
		</div>
	</c:forEach>
	<div class="form-group">
		<div class="col-sm-offset-2 col-sm-10">
			<button type="submit" class="btn btn-default btn-success"><spring:message code="crsms.button.next" /></button>
		</div>
	</div>
</form:form>