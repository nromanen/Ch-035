<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<form:form modelAttribute="request" method="POST" class="form-horizontal" >
	
	
<%-- 
	<div class="form-group " >
		<c:set var="userEmail">
			<spring:message code="crsms.createuser.email" />
		</c:set>
		<label for="${request.user.email}" class="col-sm-2 control-label">${userEmail}:
		</label>
		<div class="col-sm-4">
			<form:input path="request.user.email" id="request.user.email" class="form-control"
				placeholder="${userEmail}" />
		</div>
	</div>

	<div class="form-group">
		<c:set var="userRole">
			<spring:message code="crsms.createuser.role" />
		</c:set>
		<label for="role" class="col-sm-2 control-label">${userRole}:
		</label>
		<div class="col-sm-4">
			<form:select path="role" items="${roles}" itemValue="id"
				itemLabel="name" class="form-control input-sm" />
			<form:errors path="role" cssClass="label label-danger" />
		</div>
	</div>



 --%>



	<form:input path="id" type="hidden" />
	<div class="form-group">
		<div class="col-sm-offset-2 col-sm-4">
			<div class="checkbox">
				<label for="approved">
					<form:checkbox path="approved" id="approved" />
					<spring:message code="crsms.request.edit.approved" />
				</label>
			</div>
		</div>
	</div>

	<div class="form-group">
		<div class="col-sm-offset-2 col-sm-4">
			<c:set var="userSave">
				<spring:message code="crsms.request.edit.save" />
			</c:set>
			<input type="submit" value="${userSave}" class="btn btn-primary" />
		</div>
	</div>
</form:form>
