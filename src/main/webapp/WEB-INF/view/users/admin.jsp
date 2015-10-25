<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Management Page</title>
</head>
<body>
	<div align="right">
	<c:if test="${pageContext.request.userPrincipal.name != null}">
		<h5>
			${pageContext.request.userPrincipal.name}, welcome to User Management Page.
	<a href=<c:url value="/logout" />>logout</a>
		</h5>
	</c:if>
		</div>
	<br />
	<table class="table">
	<caption>List of Users</caption>
	<thead>
		<th>Id</th>
		<th>E-mail</th>
		<th>Password</th>
		<th>Role</th>
		</thead>
	<tbody>
		<c:forEach items="${users}" var="user">
			<tr>
				<td>${user.id}</td>
				<td>${user.email}</td>
				<td>${user.password}</td>
				<td>${user.role}</td>
				<td><a class="btn btn-primary btn-xs" href="admin/edit/${user.id}">edit</a></td>
				<td><a class="btn btn-danger btn-xs" href="admin/delete/${user.id}">delete</a></td>
			</tr>
		</c:forEach>
	</tbody>
</table>
	<br/>
	<a class="btn btn-success" href="admin/adduser/">add user</a>
</body>
</html>