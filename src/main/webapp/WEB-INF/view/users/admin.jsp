<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<div align="right">
	<c:if test="${pageContext.request.userPrincipal.name != null}">
		<h5>
			You're logged in as <b>${pageContext.request.userPrincipal.name}</b> <a href=<c:url value="/logout" />>logout</a>
		</h5>
	</c:if>
</div>
<br />
<c:if test="${!empty users}">
<table class="table table-bordered">
	<caption>List of Users</caption>
	<thead>
		<tr class="bg-primary">
			<th><spring:message code="crsms.admin.id" /></th>
			<th><spring:message code="crsms.admin.email" /></th>
			<th><spring:message code="crsms.admin.password" /></th>
			<th><spring:message code="crsms.admin.role" /></th>
			<th colspan="2"><spring:message code="crsms.admin.management" /></th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${users}" var="user">
			<tr>
				<td>${user.id}</td>
				<td>${user.email}</td>
				<td>${user.password}</td>
				<td>${user.role}</td>

				<td class="managementCell">
				<c:url var="editUser"	value="admin/edit/${user.id}" /> 
				<a href="${editUser}">
				 <span class="glyphicon glyphicon-pencil"></span> 
						 <spring:message code="crsms.admin.edit"/>
				</a></td>

				<td class="managementCell">
				<c:url var="deleteUser"	value="admin/delete/${user.id}" /> 
				<a href="${deleteUser}">
				 <span class="glyphicon glyphicon-remove"></span> 
						 <spring:message code="crsms.admin.delete" />
				</a></td>
			</tr>
		</c:forEach>
	</tbody>
</table>
</c:if>
<br />
<c:url var = "createUser" value = "admin/adduser/" />
<a class = "btn btn-primary btn-lg pull-right" href = "${createUser}"><spring:message code="crsms.admin.createNew"/></a>


