<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.joda.org/joda/time/tags" prefix="joda"%>

<c:url var = "createGroup" value = "add" />
<a class = "btn btn-primary btn-create" href = "${createGroup}"><spring:message code="crsms.groups.title.add"/></a>
				
<table class = "table table-bordered table-hover">
	<thead>
		<tr class = "active">
			<th class = "hide"><spring:message code = "crsms.text.id"/></th>
			<th class = "text-center"><spring:message code = "crsms.text.name"/></th>
			<th class = "text-center management-cell"><spring:message code = "crsms.bc.students"/></th>
			<th class = "text-center management-cell"><spring:message code = "crsms.groups.text.startDate"/></th>
			<th class = "text-center management-cell"><spring:message code = "crsms.text.controls"/></th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var = "group" items = "${groups}">
			<tr>
				<th class = "hide">${group.id}</th>
				<td id = "${group.id}" class = "clickable">${group.name}</td>
				<td class = "text-center">
					<a href="${group.id}/students/" class = "btn btn-primary btn-sm">
						<spring:message code = "crsms.bc.students"/>
					</a>
				</td>
				<td class = "text-center"><joda:format pattern="dd.MM.yyyy" value="${group.startDate}"  /></td>
				<td class = "text-center">
					<c:url var = "editGroup" value = "${group.id}/edit" />
					<span data-toggle = "tooltip" title="<spring:message code="crsms.button.edit" />">
						<a href = "${group.id}" class = "btn btn-primary btn-sm">
							<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
						</a>
					</span>
					
					<c:url var = "deleteGroup" value = "${group.id}/delete" />
					<span data-toggle = "tooltip" title="<spring:message code="crsms.button.delete" />">
						<a href = "${deleteGroup}" class = "btn btn-danger btn-sm">
							<i class="fa fa-trash-o fa-lg"></i>
						</a>
					</span>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
