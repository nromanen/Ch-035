<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<table border = "1">
	<tr>
		<td>ID</td>
		<td>Module name</td>
		<td>Description</td>
		<td>Delete</td>
	</tr>
	<c:forEach var = "module" items = "${modules}">
		<tr>
			<td>${module.id}</td>
			<td><a href = "editmodule${module.id}">${module.name}</a></td>
			<td>${module.description}</td>
			<td><a href = "deletemodule${module.id}">delete</a></td>
		</tr>
	</c:forEach>
</table>

<a href = "/crsms/createmodule">Create new module</a>