<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<c:url var = "createModule" value = "add" />
<a class = "btn btn-primary btn-create" href = "${createModule}"><spring:message code="crsms.modules.button.create"/></a>

<table class = "table table-bordered table-hover">
	<thead>
		<tr class = "active">
			<th class = "hide"><spring:message code = "crsms.text.id"/></th>
			<th><spring:message code = "crsms.text.name"/></th>
			<th><spring:message code = "crsms.modules.text.content"/></th>
			<th><spring:message code = "crsms.text.controls"/></th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var = "module" items = "${modules}">
			<tr>
				<th class = "hide">${module.id}</th>
				<td class = "nameCell"><c:out value="${module.name}"/></td>
				<td class = "contentCell text-center">
					<c:url var = "showTests" value = "${module.id}/tests/" />
					<a href = "${showTests}" 
						class = "btn btn-primary btn-sm">
						<span class="glyphicon glyphicon-list-alt" aria-hidden="true"></span>
						<spring:message code="crsms.modules.text.tests" />
					</a>
					
					<c:url var = "showResources" value = "${module.id}/resources/" />
					<a href = "${showResources}" 
						class = "btn btn-primary btn-sm">
						<span class="glyphicon glyphicon-folder-open" aria-hidden="true"></span>
						<spring:message code="crsms.modules.text.resources" />
					</a>
				</td>
				<td class = "managementCell text-center">
					<c:url var = "editModule" value = "${module.id}/edit" />
					<a href = "${editModule}" 
						class = "btn btn-primary btn-sm"
						data-toggle = "tooltip"
						title="<spring:message code="crsms.button.edit" />" >
						<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
					</a>
					
					<c:url var = "deleteModule" value = "${module.id}/delete" />
					<a href = "${deleteModule}"
						class = "btn btn-danger btn-sm"
						data-toggle = "tooltip"
						title="<spring:message code="crsms.button.delete" />" >
						<i class="fa fa-trash-o fa-lg"></i>
					</a>
					
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>

<c:url var = "createModule" value = "add" />
<a class = "btn btn-primary" href = "${createModule}"><spring:message code="crsms.modules.button.create"/></a>