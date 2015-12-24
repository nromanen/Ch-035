<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div class="modal-content">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal">&times;</button>
		<h4 class="modal-title">
			<b><spring:message code="crsms.admin.modal.edit.header" /></b>
		</h4>
	</div>
	<div class="modal-body">
		<form:form modelAttribute="user" method="POST" class="form-horizontal">
			<form:input path="id" type="hidden" />

			<div class="form-group ">
				<c:set var="userEmail">
					<spring:message code="crsms.createuser.email" />
				</c:set>
				<label for="email" class="col-sm-2 control-label">${userEmail}:
				</label>
				<div class="col-sm-4">
					<form:input path="email" id="email" class="form-control"
						placeholder="${userEmail}" readonly ="true"/>
					<form:errors path="email" cssClass="label label-danger" />
				</div>
			</div>

			<div class="form-group">
				<c:set var="userPassword">
					<spring:message code="crsms.createuser.password" />
				</c:set>
				<label for="password" class="col-sm-2 control-label">${userPassword}:
				</label>
				
				<div class="col-sm-4">
					<form:input type="password" path="password" id="password"
						class="form-control" placeholder="${userPassword}" readonly ="true" />
					<form:errors path="password" cssClass="label label-danger" />
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
			
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-4">
					<div class="checkbox">
						<label for="isEnabled"> <form:checkbox path="isEnabled"
								id="isEnabled" /> <spring:message
								code="crsms.createuser.isEnabled" />
						</label>
					</div>
				</div>
			</div>
			
			<div class="modal-footer">
				<c:set var="editSave">
					<spring:message code="crsms.button.save" />
				</c:set>
				<input type="submit" value="${editSave}" class="btn btn-primary" />
				<button type="button" class="btn btn-success btn-default"
					data-dismiss="modal" aria-hidden="true"><spring:message code="crsms.button.cancel" /></button>
			</div>
		</form:form>
	</div>
	<!-- /.modal-body -->
</div>
<!-- /.modal-content -->
