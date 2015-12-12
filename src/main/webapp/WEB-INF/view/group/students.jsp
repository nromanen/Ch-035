<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div>
	<c:url var = "addStudents" value = "add" />
	<a id = "add-students-btn" class = "btn btn-primary btn-create" href = "${addStudents}"><spring:message code="crsms.groups.text.add.students"/></a>
</div>

<table class = "table table-bordered table-hover">
	<thead>
		<tr class = "active">
			<th class = "text-center number-cell">#</th>
			<th class = "text-center"><spring:message code = "crsms.userProfile.fName"/></th>
			<th class = "text-center"><spring:message code = "crsms.userProfile.lName"/></th>
			<th class = "text-center"><spring:message code = "crsms.userProfile.email"/></th>
			<th class = "text-center management-cell"><spring:message code = "crsms.text.controls"/></th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var = "student" items = "${students}" varStatus="currentStudent">
			<tr>
				<td class = "text-center">${currentStudent.count}</td>
				<td>${student.firstName}</td>
				<td>${student.lastName}</td>
				<td>${student.email}</td>
				<td class = "text-center">
					<c:url var = "removeStudent" value = "${student.id}/remove" />
					<span data-toggle = "tooltip" title="<spring:message code="crsms.button.delete" />">
						<a href = "${removeStudent}" class = "btn btn-danger btn-sm">
							<i class="fa fa-trash-o fa-lg"></i>
						</a>
					</span>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>