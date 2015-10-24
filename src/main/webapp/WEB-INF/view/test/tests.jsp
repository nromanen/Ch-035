<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div class="container">
	<table class = "table table-bordered">
		<thead>
		<tr class = "bg-primary">
			<th><spring:message code="crsms.modules.id"/></th>
			<th><spring:message code="crsms.modules.name"/></th>
			<th><spring:message code="crsms.modules.description"/></th>
			<th colspan = "2"><spring:message code="crsms.modules.management"/></th>
		</tr>
		</thead>
		<tbody>
		<c:forEach var = "module" items = "${modules}">
			<tr>
				<th>${module.id}</th>
				<td>${module.name}</td>
				<td>${module.description}</td>
				<td class = "managementCell">
					<c:url var = "editModule" value = "${module.id}/edit" />
					<a href = "${editModule}">
						<span class = "glyphicon glyphicon-pencil"></span> <spring:message code="crsms.modules.edit"/>
					</a>
				</td>
				<td class = "managementCell">
					<c:url var = "deleteModule" value = "${module.id}/delete" />
					<a href = "${deleteModule}">
						<span class = "glyphicon glyphicon-remove"></span> <spring:message code="crsms.modules.delete"/>
					</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>

	<c:url var = "createModule" value = "add" />
	<a class = "btn btn-primary btn-lg pull-right" href = "${createModule}"><spring:message code="crsms.modules.createNew"/></a>
</div>