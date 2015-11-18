<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


<form:form modelAttribute="user" method="POST" class="form-horizontal">
	<form:input path="id" type="hidden" />

	<div class="form-group">
		<c:set var="userEmail">
			<spring:message code="crsms.createuser.email" />
		</c:set>
		<label for="email" class="col-sm-2 control-label">${userEmail}:
		</label>
		<div class="col-sm-10">
			<form:input path="email" id="email" class="form-control"
				placeholder="${userEmail}" />
			<form:errors path="email" cssClass="label label-danger" />
		</div>
	</div>

	<div class="form-group">
		<c:set var="userPassword">
			<spring:message code="crsms.createuser.password" />
		</c:set>
		<label for="password" class="col-sm-2 control-label">${userPassword}:
		</label>
		<div class="col-sm-10">
			<form:input type="password" path="password" id="password"
				class="form-control" placeholder="${userPassword}" />
			<form:errors path="password" cssClass="label label-danger" />
		</div>
	</div>

	<div class="form-group">
		<c:set var="userRole">
			<spring:message code="crsms.createuser.role" />
		</c:set>
		<label for="role" class="col-sm-2 control-label">${userRole}:
		</label>
		<div class="col-sm-10">
			<form:select path="role" items="${roles}" itemValue="id"
				itemLabel="name" class="form-control input-sm" />
			<form:errors path="role" cssClass="label label-danger" />
		</div>
	</div>

	<div class="form-group">
		<div class="col-sm-offset-2 col-sm-10">
			<c:set var="userSave">
				<spring:message code="crsms.createuser.save" />
			</c:set>
			<input type="submit" value="${userSave}" class="btn btn-primary" />
		</div>
	</div>
</form:form>
