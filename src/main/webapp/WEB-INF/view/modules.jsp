<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="container">
	<table class = "table table-bordered">
		<thead>
			<tr class = "bg-primary">
				<th>ID</th>
				<th>Module name</th>
				<th>Description</th>
				<th>Delete</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var = "module" items = "${modules}">
				<tr>
					<th>${module.id}</th>
					<td>
						<c:url var = "editModule" value = "module/${module.id}/edit" />
						<a href = "${editModule}">${module.name}</a>
					</td>
					<td>${module.description}</td>
					<td>
						<c:url var = "deleteModule" value = "module/${module.id}/delete" />
						<a href = "${deleteModule}">delete</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
	<c:url var = "createModule" value = "module/new" />
	<a class = "btn btn-primary btn-lg pull-right" href = "${createModule}">Create new module</a>
</div>