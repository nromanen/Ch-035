<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Spring test</title>
</head>
<body>
	<div align="center">
		<h1>${message}</h1>
		<br>
		<h2>Enter 2 digits:</h2>
		<form method="post">
			<input name="value1" type="text" /> + <input name="value2"
				type="text" /> <br> <br> <input type="submit"
				value="Calculate" />
		</form>
	</div>
</body>
</html>