<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<html>
<body>
	<h1>Title : ${title}</h1>
	<h1>Message : ${message}</h1>

	<c:url value="/logout" var="logoutUrl" />
	<form action="${logoutUrl}" method="post" id="logoutForm">
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
	</form>
	<script>
		function formSubmit() {
			document.getElementById("logoutForm").submit();
		}
	</script>
	<c:if test="${pageContext.request.userPrincipal.name != null}">
		<h2>
			Welcome : ${pageContext.request.userPrincipal.name} | <a
				href="javascript:formSubmit()"> Logout</a>
		</h2>
	</c:if>

	<table border="1" cellpadding="2" cellspacing="2">
		<tr>
			<th>Id</th>
			<th>Name</th>
			<th>Password</th>
			<th>Role</th>
		</tr>
		<c:forEach items="${userlist}" var="user"></c:forEach>
		<tr>
			<td>${user.id}</td>
			<td>${user.email}</td>
			<td>${user.password}</td>
			<td>${user.role}</td>
			<td><a href="<c:url value='/edit/${user.id}' />">Edit</a></td>
			<td><a href="<c:url value='/remove/${user.id}' />">Delete</a></td>
		</tr>
	</table>
</body>
</html>