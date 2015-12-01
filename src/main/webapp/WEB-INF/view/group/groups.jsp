<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.joda.org/joda/time/tags" prefix="joda"%>

<div id = "success-alert" class="alert alert-success alert-dismissible fade in hide" role="alert">
	<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">×</span></button>
	<div id = "all-subscribed">
		<spring:message code="crsms.groups.message.subscribed.all" />
	</div>
	<div id = "not-all-subscribed">
		<div>
			<spring:message code="crsms.groups.message.subscribed.count" />
			<span id = "subscribed-users-count"></span>
		</div>
		<spring:message code="crsms.groups.message.not.subscribed" />
		<div id = "not-subscribed-users"></div>
		<spring:message code="crsms.groups.message.not.subscribed.reason" />
	</div>
</div>

<c:url var = "createGroup" value = "add" />
<a class = "btn btn-primary btn-create" href = "${createGroup}"><spring:message code="crsms.groups.title.add"/></a>
				
<table class = "table table-bordered table-hover">
	<thead>
		<tr class = "active">
			<th class = "hide"><spring:message code = "crsms.text.id"/></th>
			<th class = "text-center"><spring:message code = "crsms.text.name"/></th>
			<th class = "text-center management-cell"><spring:message code = "crsms.groups.text.startDate"/></th>
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
					<span data-toggle = "tooltip" data-trigger="hover" title="<spring:message code="crsms.groups.text.add.students" />">
						<button class = "btn btn-primary btn-sm"
							data-toggle="modal"
							data-target="#addStudentModal"
							data-group-id="${group.id}">
							<i class="fa fa-user-plus"></i>
						</button>
					</span>
					
					<c:url var = "editGroup" value = "${group.id}/edit" />
					<span data-toggle = "tooltip" title="<spring:message code="crsms.button.edit" />">
						<a href = "${editGroup}" class = "btn btn-primary btn-sm">
							<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
						</a>
					</span>
					
					<c:url var = "deleteGroup" value = "${group.id}/delete" />
					<span data-toggle = "tooltip" title="<spring:message code="crsms.button.edit" />">
						<a href = "${deleteGroup}" class = "btn btn-danger btn-sm">
							<i class="fa fa-trash-o fa-lg"></i>
						</a>
					</span>
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
				<h4 class="modal-title" id="addStudentModalLabel"><spring:message code = "crsms.groups.text.add.students"/></h4>
			</div>
			<div class="modal-body">
				<input type = "hidden" id = "crsf-token" value = "${_csrf.token}">
				<div class = "row">
					<div class = "col-md-5">
						<spring:message code = "crsms.groups.message.enter.emails"/>
					</div>
					<div class = "col-md-2"></div>
					<div class = "col-md-5">
						<spring:message code = "crsms.groups.message.from.other.group"/>
					</div>
				</div>
				<div class = "row">
					<div class = "col-md-5">
						<textarea id = "emails" rows = "7" class="form-control"></textarea>
					</div>
					<div class = "col-md-2 text-center">
						<br><br>
						<div>
							<button id = "add-all-btn" class = "btn btn-sm btn-default form-control">
								<spring:message code = "crsms.groups.button.add.all"/>
							</button>
						</div>
						<br>
						<div>
							<button id = "add-btn" class = "btn btn-sm btn-default form-control">
								<spring:message code = "crsms.groups.button.add"/>
							</button>
						</div>
					</div>
					<div class = "col-md-5">
						<select id = "groups" class = "form-control"></select>
						<select id = "students" class="form-control" multiple>
						</select>
						<div class = "row">
							<div class = "col-sm-6">
								<button id = "select-all-btn" class = "btn btn-sm btn-default form-control">
									<spring:message code = "crsms.groups.button.select.all"/>
								</button>
							</div>
							<div class = "col-sm-6">
								<button id = "clear-selection-btn" class = "btn btn-sm btn-default form-control">
									<spring:message code = "crsms.groups.button.select.clear"/>
								</button>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<button id="submit-btn" type="button" class="btn btn-primary">
					<spring:message code = "crsms.groups.button.submit"/>
				</button>
				 <button type="button" class="btn btn-default" data-dismiss="modal">
				 	<spring:message code = "crsms.button.close" />
				 </button>
			</div>
		</div>
	</div>
</div>