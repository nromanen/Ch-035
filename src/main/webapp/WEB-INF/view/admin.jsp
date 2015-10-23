<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Admin page</title>
</head>
<body>
	Dear <strong>${user}</strong>, Welcome to Admin Page.
	<a href=<c:url value="/logout" />>logout</a>
	<br/>
	<h2>List of Users</h2>	
	<table border="3" cellpadding="8" cellspacing="4">
		<tr>
			<th>Id</th><th>E-mail</th><th>Password</th><th>Role</th><th></th><th></th>
		</tr>
		<c:forEach items="${users}" var="user">
			<tr>
			<td>${user.id}</td>
			<td>${user.email}</td>
			<td>${user.password}</td>
			<td>${user.role}</td>
			<td><a href="<c:url value='/${user.id}' />">edit</a></td>
			<td><a href="<c:url value='/${user.id}' />">delete</a></td>
			</tr>
		</c:forEach>
	</table>
	<br/>

</body>
</html>