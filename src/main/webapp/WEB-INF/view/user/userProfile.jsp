<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Profile page</title>

<link
	href="//ajax.googleapis.com/ajax/libs/jqueryui/1.11.1/themes/ui-darkness/jquery-ui.min.css"
	rel="stylesheet">
<script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script
	src="//ajax.googleapis.com/ajax/libs/jqueryui/1.11.1/jquery-ui.min.js"></script>

<script>
		$(function() {
			var dialog = $("#dialog-form").dialog({
				autoOpen : false,
				height : 300,
				width : 350,
				modal : true,
				buttons : {
					Change : function() {
						document.changePass.submit();
					},
					Cancel : function() {
						$(this).dialog("close");
					}
				},
			});
			
			$('form#changePass').submit(function(){
				var cPass = $("input#currentPass").val();
				var pass = $("input#password").val();
				if(pass==cPass){
					alert("Incorrect current password");
					$(this).dialog("close");
					submit = false;
				};
			});
			
			$("#changePass").button().on("click", function() {
				dialog.dialog("open");
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
					<h4 class="modal-title" id="myModalLabel">Change password</h4>
				</div>
				<div class="modal-body">
					<table>
						<tr>
							<td>Current Password</td>
							<td><input type="password" name="currentPassword"></td>
						</tr>
						<tr>
							<td>New password</td>
							<td><input type="password" name="newPassword"></td>
						</tr>
					</table>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					<button type="button" class="btn btn-primary">Save changes</button>
				</div>
			</div>
		</div>
	</div>
	<h2>Welcome! It's your personal page!</h2>
	<form action="changePass" name="changePass" id="changePass"
		method="POST">
		<input type="hidden" id="id" value="<%=request.getAttribute("id")%>">
			<div id="dialog-form" title="Change password">
			<table id="table">
				<tr>
					<td width="*">Current Password<font size="3" color="red">
							* </font></td>
					<td width="60%"><input type="password" name="currentPass" required 
						id="currentPass" size="25" oninvalid="setCustomValidity('Enter correct password')"><br></td>
				</tr>
				<tr>
					<td width="*">New Password<font size="3" color="red"> *
					</font></td>
					<td width="60%"><input type="password" name="nPass"
						id="nPass" size="25" required
						pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,}"
						oninvalid="setCustomValidity('Enter correct password')"><br></td>
				</tr>
			</table>
		</div>
	</form>
	<form action="submitUserInfo" name="userInformation" method="POST">
	<input type="hidden" name="${_csrf.parameterName}"
		value="${_csrf.token}" /> 
	<input type="hidden" name="id"
		value="<%=request.getAttribute("id")%>">
		<table id="table">
			<tr>
				<td width="*">First Name <font size="3" color="red"> * </font></td>
				<td width="60%"><input name="fName" id="fName" size="25"
					required pattern="^[A-Z][a-z]{3,12}$"><br></td>
			</tr>
			<tr>
				<td width="*">Second Name <font size="3" color="red"> *
				</font></td>
				<td width="60%"><input name="sName" id="sName" size="25"
					required pattern="^[A-Z][a-z]{3,12}$"><br></td>
			</tr>
			<tr>
				<td width="*">Email</td>
				<td width="60%"><input type="email" name="email" id="email"
					size="25" value=<%=request.getAttribute("email")%> readonly><br></td>
			</tr>
			<tr>
				<td width="*"></td>
				<td width="60%" align="right">
					<button type="button" class="btn btn-small" data-toggle="modal" data-target=".bs-example-modal-sm">Change password</button>
				<br></td>
			</tr>
			<tr align="right" bgcolor="#2e6da4">
				<td colspan="2">
					<input type="submit" value="Save"> 
				</td>
			</tr>
		</table>
	</form>

</body>
</html>