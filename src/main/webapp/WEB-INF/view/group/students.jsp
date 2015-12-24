<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="/WEB-INF/taglib/CrsmsTaglib.tld" prefix="crsms" %>

<div>
	<c:url var = "addStudents" value = "add" />
	<a id = "add-students-btn" class = "btn btn-primary btn-create" href = "${addStudents}">
		<spring:message code="crsms.groups.text.add.students"/>
	</a>
	
	<div class="dropdown pull-right">
	<button class="btn btn-default dropdown-toggle" type="button" data-toggle="dropdown">
		<spring:message code="crsms.groups.button.display.limit"/>
		<span class="caret"></span>
	</button>
	<ul id = "limit-select" class="dropdown-menu">
	</ul>
	</div>
</div>

<div id="table-wrapper">
	<table class = "table table-bordered table-hover">
		<thead>
			<tr class = "active">
				<th class = "text-center fl-name">
					<c:set var="fName"><spring:message code = "crsms.userProfile.fName"/></c:set>
					<crsms:sort text="${fName}" sortBy="firstName"/>
				</th>
				<th class = "text-center fl-name">
					<c:set var="lName"><spring:message code = "crsms.userProfile.lName"/></c:set>
					<crsms:sort text="${lName}" sortBy="lastName"/>
				</th>
				<th class = "text-center">
					<c:set var="email"><spring:message code = "crsms.userProfile.email"/></c:set>
					<crsms:sort text="${email}" sortBy="email"/>
				</th>
				<th class = "text-center management-cell"><spring:message code = "crsms.text.controls"/></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var = "student" items = "${students}">
				<tr>
					<td>
						<a  href="<c:url value="/courses/progress/${student.id}" />">
							${student.firstName}
						</a>
					</td>
					<td>
						<a  href="<c:url value="/courses/progress/${student.id}" />">
							${student.lastName}
						</a>
					</td>
					<td>
						<a  href="<c:url value="/courses/progress/${student.id}" />">
							${student.email}
						</a>
					</td>
					
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
</div>

<crsms:pagination count="${count}" limit="${limit}"/>
