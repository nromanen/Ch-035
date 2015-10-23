<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  pageEncoding="ISO-8859-1"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Welcome page</title>
</head>
<body>
	Greeting : ${greeting}
	
	<br>
	Dear <strong>${user}</strong>, this is a welcome page.
	<a href="<c:url value="/logout" />">logout</a>
</body>
</html>