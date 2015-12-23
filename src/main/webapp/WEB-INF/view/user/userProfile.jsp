<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

	<tiles:insertAttribute name="jquery-validation-messages"></tiles:insertAttribute>
	<div class="modal fade" id="imageModal">
    	<div class="modal-dialog modal-lg">
        	<div class="modal-content">
            	<div class="modal-header">
                	<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                	<h4 class="modal-title"><spring:message code = "crsms.userProfile.selectPhoto"/></h4>
            	</div>
           		<div class="modal-body text-center" id="avatarModalBody">
            		<img src="" id="avatarImage" width="400" height="400">
            	</div>
            	<div class="modal-footer">
                	<button type="button" class="btn btn-default" data-dismiss="modal"><spring:message code = "crsms.userProfile.modclose"/></button>
                	<button type="button" class="btn btn-primary" id="imageSelectBtn"><spring:message code = "crsms.userProfile.save"/></button>
            	</div>
        	</div>
    	</div>
	</div>
	<input type="hidden" id="selectedImage" value="">
	<div class="hidden">
    	<input type="file" id="imageInput" accept="image/*" >
    	<canvas id="imageCanvas"></canvas>
		<canvas id="bufferedCanvas" width="400" height="400" ></canvas>
	</div>

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
				<div id="form_errors" class="alert alert-danger fade in" style="display:none"> </div>
					<form action="changePassword" id="changePasswordForm" method="POST">
						<table>
							<tr>
								<td><spring:message code = "crsms.userProfile.modCurPass"/></td>
								<td><input type="password" name="currentPass" required
									id="currentPass"></td>
							</tr>
							<tr>
								<td><spring:message code = "crsms.userProfile.modNewPass"/></td>
								<td><a title="<spring:message code = "crsms.signUp.password.tip.title"/>"
									data-toggle="popover" data-trigger="focus"
									data-content="<spring:message code = "crsms.signUp.password.tip.text"/>">
									<input type="password" name="newPassword" 
									id="newPassword" required
									pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,}"></a>
									<label id="newPassword-error" class="errorTxt" for="newPassword"></label>
								</td>
							</tr>
							<tr>
								<td><spring:message code = "crsms.signUp.confPassword"/></td>
								<td><input type="password" name="confPassword" 
									id="confPassword" required></td>
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
				<form:form modelAttribute="userInfo" id="user-information" action="submitUserInfo" name="userInformation" method="POST" class="form-horizontal">
					<form:input path="id" type="hidden" class="form-control" name="id" />
					<div class="form-group">
						<label for="image" class="col-md-2"><spring:message code = "crsms.userProfile.image"/></label>
						<div class="col-md-2">
							<c:choose>
								<c:when test="${ empty userInfo.image }">
									<c:url var="avatarUrl" value="/resources/images/default_logo.png" />
									<c:set var="imgSrc" value="${ avatarUrl }" />
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
						<div class="col-md-3 col-md-offset-2">
							<h6><spring:message code = "crsms.userProfile.imageProportions"/></h6>
							<input 	type="button" id="showImagePopupBtn" class="btn btn-default"
							value = <spring:message code = "crsms.userProfile.selectPhoto"/> >
						</div>
					</div>
					<div class="form-group">
						<label for="firstName" class="col-md-2"><spring:message code = "crsms.userProfile.fName"/></label>
						<div class="col-md-2">
							<form:input path="firstName" type="text" class="form-control" id="firstName" name="firstName"
								placeholder="First name" required="true"  />
							<form:errors path = "firstName" cssClass = "label label-danger" />
						</div>
					</div>
					<div class="form-group">
						<label for="lastName" class="col-md-2"><spring:message code = "crsms.userProfile.lName"/></label>
						<div class="col-md-2">
							<form:input path="lastName" type="text" class="form-control" id="lastName" name="lastName"
								placeholder="Last name" required="true" />
							<form:errors path = "lastName" cssClass = "label label-danger" />
						</div>
					</div>
					<div class="form-group">
						<label for="email" class="col-md-2"><spring:message code = "crsms.userProfile.email"/></label>
						<div class="col-md-2">
							<input type="email" class="form-control" id="email" name="email" value="${userInfo.user.email}" readonly>
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