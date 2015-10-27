<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Profile page</title>

<script type="text/javascript">

	$(document).ready(function() {
		$("#changePasswordBtn").click(function(){
			var url = "changePassword?currentPass=" + $("#currentPass").val() 
					+ "&newPassword=" + $("#newPassword").val()
					+ "&_csrf=" + $("#csrf").val();
		    $.ajax({
	            type: "GET",
	            url: url,
	            success : function(response){
	            	if (response == "Fail") {
	            		alert("Current password are not valid");
	            	} else {
	            		$("#closeModalBtn").click();
	            	}
	            },
	            error : function(){
	                alert("Failed to submit form");
	            }
	        });
	    });
	});
    
</script>
</head>

<body>
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
									id="newPassword" size="25" required
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

	<h2><spring:message code = "crsms.userProfile.header"/></h2>

	<div class="container">
		<div class="row h2">
			<div class="col-md-4 col-md-offset-0">
				<spring:message code = "crsms.userProfile.header"/><br>
				<br>
			</div>
		</div>
		<div class="row">
			<div class="col-md-offset-0">
				<form id="user-information" action="submitUserInfo"
					name="userInformation" method="POST" class="form-horizontal">
					<div class="form-group">
						<label for="fName" class="col-md-2"><spring:message code = "crsms.userProfile.fName"/></label>
						<div class="col-md-2">
							<input type="text" class="form-control" id="fName" name="fName"
								placeholder="First name" required pattern="^[A-Z][a-z]{3,12}$">
						</div>
					</div>
					<div class="form-group">
						<label for="sName" class="col-md-2"><spring:message code = "crsms.userProfile.sName"/></label>
						<div class="col-md-2">
							<input type="text" class="form-control" id="sName" name="sName"
								placeholder="Second name" required pattern="^[A-Z][a-z]{3,12}$">
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
				</form>
			</div>
		</div>
	</div>

</body>
</html>