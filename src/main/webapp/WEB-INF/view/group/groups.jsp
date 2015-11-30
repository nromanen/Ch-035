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
			<th class = "text-center"><spring:message code = "crsms.groups.text.startDate"/></th>
			<th class = "text-center management-cell">Add student</th>
			<th class = "text-center management-cell"><spring:message code = "crsms.text.controls"/></th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var = "group" items = "${groups}">
			<tr>
				<th class = "hide">${group.id}</th>
				<td>${group.name}</td>
				<td><joda:format pattern="dd.MM.yyyy" value="${group.startDate}"  /></td>
				<td class = "text-center">
					<button class = "btn btn-primary btn-sm"
							data-toggle="modal"
							data-target="#addStudentModal">
						Add student
					</button>
				</td>
				<td class = "text-center">
					<c:url var = "editGroup" value = "${group.id}/edit" />
					<a href = "${editGroup}" 
						class = "btn btn-primary btn-sm"
						data-toggle = "tooltip"
						title="<spring:message code="crsms.button.edit" />" >
						<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
					</a>
					
					<c:url var = "deleteGroup" value = "${group.id}/delete" />
					<a href = "${deleteGroup}"
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

<!-- Add student modal -->
<div class="modal fade" id="addStudentModal" tabindex="-1" role="dialog" aria-labelledby="addStudentModalLabel">
	<div class="modal-dialog modal-lg" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				<h4 class="modal-title" id="addStudentModalLabel">Modal title</h4>
			</div>
			<div class="modal-body">
				<div class = "row">
					<div class = "col-md-6">
						<div>Enter emails here:</div>
						<textarea class="form-control"></textarea>
					</div>
					<div class = "col-md-6">
						<div class = "row">
							<div class = "col-sm-8">
								Select students from other group:
							</div>
							<div class = "col-sm-4">
								<select id = "groups" class = "form-control">
								</select>
							</div>
						</div>
						<select id = "students" class="form-control" multiple>
						</select>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<button id="btn-enroll" type="button" class="btn btn-primary">
					Submit
				</button>
				 <button type="button" class="btn btn-default" data-dismiss="modal">
				 	<spring:message code = "crsms.button.close" />
				 </button>
			</div>
		</div>
	</div>
</div>