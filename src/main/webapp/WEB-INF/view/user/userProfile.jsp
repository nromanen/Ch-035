<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

	<div class="modal fade bs-example-modal-sm" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
		<div class="modal-dialog modal-sm">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel"><spring:message code = "crsms.userProfile.changePassword"/></h4>
				</div>
				<div class="modal-body">
					<form action="changePassword" id="changePasswordForm" method="POST">
						<table>
							<tr>
								<td><spring:message code = "crsms.userProfile.modCurPass"/></td>
								<td><input type="password" name="currentPass" required
									id="currentPass"></td>
							</tr>
							<tr>
								<td><spring:message code = "crsms.userProfile.modNewPass"/></td>
								<td><input type="password" name="newPassword" 
									id="newPassword" required
									pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,}"></td>
							</tr>
						</table>
						<input type="hidden" id="csrf" name="${_csrf.parameterName}"
							value="${_csrf.token}"/>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" id="closeModalBtn" class="btn btn-default" data-dismiss="modal"><spring:message code = "crsms.userProfile.modclose"/></button>
					<button type="button" id="changePasswordBtn" class="btn btn-primary"><spring:message code = "crsms.userProfile.changePassword"/></button>
				</div>					
			</div>
		</div>
	</div>

	<div class="container">
		<div class="row">
			<div class="col-md-offset-0">
				<div class="form-group">
					<input type="file" accept="image/*" id="pic">
				</div>	
				<form:form modelAttribute="userInfo" id="user-information" action="submitUserInfo" name="userInformation" method="POST" class="form-horizontal">
					<div class="form-group">
						<label for="image" class="col-md-2">Image</label>
						<div class="col-md-2">
							<c:choose>
								<c:when test="${ empty userInfo.image }">
									<c:url var="avatarUrl" value="/resources/images/def_avatar.png" />
									<c:set var="imgSrc" value="data:image/png;base64,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" />
								</c:when>
								<c:otherwise>
									<c:set var="imgSrc" value="${ userInfo.image }" />
								</c:otherwise>
							</c:choose>
							<input type="image" src="${ imgSrc }" class="form-control" id="imagePreview" alt="Avatar" style="width:170px;height:170px;" />
							<form:input path="image" type="hidden" class="form-control" id="image" name="image" value="${ imgSrc }" />
						</div>
					</div>	
										
					<div class="form-group">
						<label for="firstName" class="col-md-2"><spring:message code = "crsms.userProfile.fName"/></label>
						<div class="col-md-2">
							<form:input path="firstName" type="text" class="form-control" id="firstName" name="firstName"
								placeholder="First name" required="true" pattern="^[A-Z][a-z]*"  />
							<form:errors path = "firstName" cssClass = "label label-danger" />
						</div>
					</div>
					<div class="form-group">
						<label for="lastName" class="col-md-2"><spring:message code = "crsms.userProfile.lName"/></label>
						<div class="col-md-2">
							<form:input path="lastName" type="text" class="form-control" id="lastName" name="lastName"
								placeholder="Last name" required="true" pattern="^[A-Z][a-z]*"  />
							<form:errors path = "lastName" cssClass = "label label-danger" />
						</div>
					</div>
					<div class="form-group">
						<label for="email" class="col-md-2"><spring:message code = "crsms.userProfile.email"/></label>
						<div class="col-md-2">
							<input type="email" class="form-control" id="email" name="email" value="${ email }" readonly>
						</div>
					</div>
					<div class="form-group">
						<div class="col-md-3 col-md-offset-2">
							<button type="button" class="btn btn-small" data-toggle="modal"
								data-target=".bs-example-modal-sm"><spring:message code = "crsms.userProfile.changePassword"/></button>
						</div>
					</div>

					<div class="col-md-4 col-md-offset-3">
						<button type="submit" class="btn btn-default"><spring:message code = "crsms.userProfile.save"/></button>
					</div>
					<input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}"/>
				</form:form>
			</div>
		</div>
	</div>