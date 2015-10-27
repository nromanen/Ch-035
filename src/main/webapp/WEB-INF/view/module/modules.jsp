<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<table class = "table table-bordered table-hover">
	<thead>
		<tr class = "success">
			<th><spring:message code = "crsms.modules.id"/></th>
			<th><spring:message code = "crsms.modules.name"/></th>
			<th><spring:message code = "crsms.modules.description"/></th>
			<th><spring:message code = "crsms.modules.management"/></th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var = "module" items = "${modules}">
			<tr class = "active">
				<th>${module.id}</th>
				<td>${module.name}</td>
				<td>${module.description}</td>
				<td class = "managementCell">
					<c:url var = "editModule" value = "${module.id}/edit" />
					<a href = "${editModule}" 
						class = "btn btn-success btn-sm"
						data-toggle = "tooltip"
						title="<spring:message code="crsms.modules.edit" />" >
						<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
					</a>
					
					<c:url var = "deleteModule" value = "${module.id}/delete" />
					<a href = "${deleteModule}"
						class = "btn btn-danger btn-sm"
						data-toggle = "tooltip"
						title="<spring:message code="crsms.modules.delete" />" >
						<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
					</a>
					
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>

<c:url var = "createModule" value = "add" />
<a class = "btn btn-success" href = "${createModule}"><spring:message code="crsms.modules.createNew"/></a>