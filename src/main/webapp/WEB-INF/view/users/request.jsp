<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


<form:form modelAttribute="request" method="POST" class="form-horizontal" >
	<form:input path="id" type="hidden" />

	<div class="form-group " >
		<c:set var="userEmail">
			<spring:message code="crsms.createuser.email" />
		</c:set>
		<label for="user.email" class="col-sm-2 control-label">${userEmail}:
		</label>
		<div class="col-sm-4">
			<form:input path="user.email" id="user.email" class="form-control"
				placeholder="${userEmail}" />
			<form:errors path="user.email" cssClass="label label-danger" />
		</div>
	</div>

	<div class="form-group">
		<div class="col-sm-offset-2 col-sm-4">
			<div class="checkbox">
				<label for="user.isEnabled">
					<form:checkbox path="user.isEnabled" id="user.isEnabled" />
					<spring:message code="crsms.createuser.isEnabled" />
				</label>
			</div>
		</div>
	</div>

	<div class="form-group">
		<div class="col-sm-offset-2 col-sm-4">
			<c:set var="userSave">
				<spring:message code="crsms.createuser.save" />
			</c:set>
			<input type="submit" value="${userSave}" class="btn btn-primary" />
		</div>
	</div>
</form:form>
