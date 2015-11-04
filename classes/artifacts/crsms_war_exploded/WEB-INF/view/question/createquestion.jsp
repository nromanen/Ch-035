<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<form:form modelAttribute="question" method="POST" class="form-horizontal">
  <form:input path="id" type="hidden" />
  <div class="form-group">
    <c:set var="questionTitle">
      <spring:message code="crsms.question.title" />
    </c:set>
    <label for="text" class="col-sm-2 control-label">${questionTitle}:</label>
    <div class="col-sm-10">
      <form:input path="text" id="text" class="form-control" placeholder="${questionTitle}" />
      <form:errors path="text" cssClass = "label label-danger" />
    </div>
  </div>

  <div class="form-group">
    <div class="col-sm-offset-2 col-sm-10">
      <c:set var="questionSave">
        <spring:message code="crsms.button.save" />
      </c:set>
      <c:set var="backButton">
        <spring:message code="crsms.question.backButton" />
      </c:set>
      <a class="btn btn-success" role="button"
         onClick="history.go(-1);return true;">${backButton}</a>
      <input type="submit" value="${questionSave}" class="btn btn-success" />
    </div>
  </div>
</form:form>