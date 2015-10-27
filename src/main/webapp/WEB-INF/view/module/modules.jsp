<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<table class = "table table-bordered table-hover">
	<thead>
		<tr class = "success">
			<th><spring:message code = "crsms.text.id"/></th>
			<th><spring:message code = "crsms.text.name"/></th>
			<th><spring:message code = "crsms.text.description"/></th>
			<th><spring:message code = "crsms.modules.text.content"/></th>
			<th><spring:message code = "crsms.text.controls"/></th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var = "module" items = "${modules}">
			<tr class = "active">
				<th>${module.id}</th>
				<td>${module.name}</td>
				<td>${module.description}</td>
				<td class = "managementCell">
					<c:url var = "showTests" value = "${module.id}/tests/" />
					<a href = "${showTests}" 
						class = "btn btn-warning btn-sm">
						<span class="glyphicon glyphicon-list-alt" aria-hidden="true"></span>
						<spring:message code="crsms.modules.text.tests" />
					</a>
					
					<c:url var = "showResources" value = "${module.id}/resources/" />
					<a href = "${showResources}" 
						class = "btn btn-warning btn-sm">
						<span class="glyphicon glyphicon-folder-open" aria-hidden="true"></span>
						<spring:message code="crsms.modules.text.resources" />
					</a>
				</td>
				<td class = "managementCell">
					<c:url var = "editModule" value = "${module.id}/edit" />
					<a href = "${editModule}" 
						class = "btn btn-success btn-sm"
						data-toggle = "tooltip"
						title="<spring:message code="crsms.button.edit" />" >
						<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
					</a>
					
					<c:url var = "deleteModule" value = "${module.id}/delete" />
					<a href = "${deleteModule}"
						class = "btn btn-danger btn-sm"
						data-toggle = "tooltip"
						title="<spring:message code="crsms.button.delete" />" >
						<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
					</a>
					
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>

<c:set var="backButton">
	<spring:message code="crsms.createtest.backButton" />
</c:set>
	<a class="btn btn-success" role="button" onClick="history.go(-1);return true;">${backButton}</a>

<c:url var = "createModule" value = "add" />
<a class = "btn btn-success" href = "${createModule}"><spring:message code="crsms.modules.button.create"/></a>