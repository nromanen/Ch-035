<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="container">
	<table class = "table table-bordered">
		<thead>
			<tr class = "bg-primary">
				<th>ID</th>
				<th>Module name</th>
				<th>Description</th>
				<th colspan = "2">Management</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var = "module" items = "${modules}">
				<tr>
					<th>${module.id}</th>
					<td>${module.name}</td>
					<td>${module.description}</td>
					<td class = "managementCell">
						<c:url var = "editModule" value = "modules/${module.id}/edit" />
						<a href = "${editModule}"><span class = "glyphicon glyphicon-pencil"></span> edit</a>
					</td>
					<td class = "managementCell">
						<c:url var = "deleteModule" value = "modules/${module.id}/delete" />
						<a href = "${deleteModule}"><span class = "glyphicon glyphicon-remove"></span> delete</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
	<c:url var = "createModule" value = "modules/add" />
	<a class = "btn btn-primary btn-lg pull-right" href = "${createModule}">Create new module</a>
</div>