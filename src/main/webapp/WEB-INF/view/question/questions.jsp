<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<table class="table table-bordered table-hover">
  <thead>
  <tr class="success">
    <th><spring:message code="crsms.question.text" /></th>
    <th><spring:message code="crsms.question.text.content" /></th>
    <th colspan="2"><spring:message code="crsms.text.controls" /></th>
  </tr>
  </thead>
  <tbody>
  <c:forEach var="question" items="${questions}">
    <tr class="active">
      <td><i class="glyphicon glyphicon-question-sign">&nbsp</i>${question.text}</td>
      <td>
        <div align="center">
          <!-- Add answers -->
          <c:url var="showAnswers" value="${question.id}/answers/" />
          <a class="btn btn-success" href="${showAnswers}">
            <spring:message code="crsms.question.text.answers" />
          </a>
        </div>
      </td>
      <td>
        <div align="center">
          <c:url var="editQuestion" value="${question.id}/edit" />
          <a href="${editQuestion}" class="btn btn-success btn-sm"
             data-toggle="tooltip"
             title="<spring:message code="crsms.button.edit" />">
            <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
          </a>

          <c:url var="deleteQuestion" value="${question.id}/delete" />
          <a href="${deleteQuestion}"
             class="btn btn-danger btn-sm"
             data-toggle="tooltip"
             title="<spring:message code="crsms.button.delete" />"
             value="${question.id}"> <span class="glyphicon glyphicon-remove"
                                       aria-hidden="true"></span>
          </a>
        </div>
      </td>

    </tr>
  </c:forEach>
  </tbody>
</table>

<c:set var="backButton">
  <spring:message code="crsms.question.backButton" />
</c:set>
<a class="btn btn-success" role="button" onClick="history.go(-1);return true;">${backButton}</a>

<c:url var="createQuestion" value="add" />
<a class="btn btn-success" href="${createQuestion}"><spring:message code="crsms.question.createNew" /></a>
