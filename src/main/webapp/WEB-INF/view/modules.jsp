<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<table border = "1">
	<tr>
		<td>ID</td>
		<td>Module name</td>
		<td>Description</td>
		<td>Control</td>
	</tr>
	<c:forEach var = "module" items = "${modules}">
		<tr>
			<td>${module.id}</td>
			<td>${module.name}</td>
			<td>${module.description}</td>
		</tr>
	</c:forEach>
</table>

<a href = "/crsms/createmodule">Create new module</a>