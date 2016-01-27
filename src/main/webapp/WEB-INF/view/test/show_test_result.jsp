<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>




<div id="allUserAnswerAndQuestion">
	<div class="form-group">
		<div class="col-sm-offset-2 col-sm-10" >
			<h3><spring:message code="crsms.text.score" />: <b><fmt:formatNumber type="number" maxFractionDigits="2" value="${testResaltScore}" /> </b> / 100</h3>
		</div>
	</div>
	<c:forEach var="userAnswerAndQuestion" items="${userAnswerAndQuestionList}" >
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10" >
				<p><b>${userAnswerAndQuestion.question.text}</b></p>
			</div>
		</div>
		
		<c:forEach var="answer" items="${userAnswerAndQuestion.question.answers}">
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10" >
					<div class="checkbox">
						<label>
							<span type="checkbox" 
								class="
									<c:choose>
										<c:when test="${userAnswerAndQuestion.userAnswerForm.checkedAnswerIds.contains(answer.id)}">
								 			<c:out value=" fa fa-check-square-o correctAnswer" />
								 		</c:when>
								 		<c:otherwise>
											<c:out value=" fa fa-square-o incorrectAnswer" />
										</c:otherwise>
							 		</c:choose>
				  		 		"
							></span>
							${answer.text}
							
							<span class=" glyphicon 
								<c:choose>
									<c:when test="${userAnswerAndQuestion.userAnswerForm.checkedAnswerIds.contains(answer.id) && answer.correct}">
							 			<c:out value=" glyphicon-ok-circle text-success correctAnswer" />
							 		</c:when>
							 		<c:when test="${userAnswerAndQuestion.userAnswerForm.checkedAnswerIds.contains(answer.id) && !answer.correct}">
										<c:out value=" glyphicon-ban-circle text-danger incorrectAnswer" />
									</c:when>
						 		</c:choose>
							"></span>
						</label>
					</div>
				</div>
			</div>
		</c:forEach>
	</c:forEach>
</div>
