<!DOCTYPE html>
<html>
<head>
<title>Sign Up</title>

</head>
<body>
	<h2>
		Create your account <br>
	</h2>
	<form action="submitUser" name="registration" method="POST">
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
		<table id="table">
			<tr>
				<td width="*">Email<font size="3" color="red"> * </font></td>
				<td width="60%"><input type="email" name="email" id="email"
					size="25" oninvalid="setCustomValidity('Enter correct name')"
					required><br></td>
			</tr>
			<tr>
				<td width="*">Password<font size="3" color="red"> * </font></td>
				<td width="60%"><input type="password" name="password"
					id="password" size="25" required
					pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,}"
					oninvalid="setCustomValidity('Enter correct password')"><br></td>
			</tr>
			<tr>
				<td width="*">Confirm Password<font size="3" color="red">
						* </font></td>
				<td width="60%"><input type="password" name="confPassword"
					id="confPassword" size="25" oninvalid="setCustomValidity('Enter correct password')"><br></td>
			</tr>
			<tr align="right" bgcolor="#29A259">
				<td colspan="2"><input type="submit" value="Sign up"></td>
			</tr>
		</table>
	</form>
</body>
</html>
