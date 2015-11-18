<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<form:form modelAttribute="test" method="POST" class="form-horizontal">
	<form:input path="id" type="hidden" />
	<div class="form-group">
		<c:set var="testName">
			<spring:message code="crsms.createtest.name" />
		</c:set>
		<label for="name" class="col-sm-2 control-label">${testName}:</label>
		<div class="col-sm-10">
			<form:input path="name" id="name" class="form-control" placeholder="${testName}" />
			<form:errors path = "name" cssClass = "label label-danger" />
		</div>
	</div>
	<div class="form-group">
		<div class="col-sm-offset-2 col-sm-10">
			<div class="checkbox">
				<label for="available">
					<form:checkbox path="available" id="available" />
					<spring:message code="crsms.createtest.available" />
				</label>
			</div>
		</div>
	</div>
	<div class="form-group">
		<div class="col-sm-offset-2 col-sm-10">
			<c:set var="testSave">
				<spring:message code="crsms.createtest.save" />
			</c:set>
			<input type="submit" value="${testSave}" class="btn btn-primary" />
		</div>
	</div>
</form:form>