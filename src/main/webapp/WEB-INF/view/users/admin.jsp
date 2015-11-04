<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<div align="right">
	<c:if test="${pageContext.request.userPrincipal.name != null}">
		<h5>
			<spring:message code="crsms.text.login.signedas" />
			<b>${pageContext.request.userPrincipal.name}</b> <a
				href=<c:url value="/logout" />><spring:message
					code="crsms.button.log.out" /></a>
		</h5>
	</c:if>
</div>
<br />
<c:if test="${!empty users}">
	<table class="table table-bordered table-hover">
		<caption>
			<spring:message code="crsms.admin.caption" />
		</caption>
		<thead>
			<tr class="success">
				<th class="hide"><spring:message code="crsms.text.id" /></th>
				<th><spring:message code="crsms.admin.email" /></th>
				<th><spring:message code="crsms.admin.password" /></th>
				<th><spring:message code="crsms.admin.role" /></th>
				<th colspan="2"><spring:message code="crsms.admin.management" /></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${users}" var="user">
				<tr class="active">
					<th >${user.id}</th>
					<td>${user.email}</td>
					<td>${user.password}</td>
					<td>${user.role}</td>
					<td class="managementCell"><c:url var="editUser"
							value="admin/edit/${user.id}" /> <a href="${editUser}"
						class="btn btn-success btn-sm" data-toggle="tooltip"
						title="<spring:message code="crsms.button.edit" />"> <span
							class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
					</a></td>
					<td class="managementCell"><c:url var="deleteUser"
							value="admin/delete/${user.id}" /> <a href="${deleteUser}"
						class="btn btn-danger btn-sm" data-toggle="tooltip"
						title="<spring:message code="crsms.button.delete" />"> <span
							class="glyphicon glyphicon-remove" aria-hidden="true"></span>

					</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</c:if>
<!-- Paging block -->
<div class="pagination">
	
		<c:if test="${page == 1}">
			<a href="<c:url value="#"/>">«</a>
		</c:if>
		<c:if test="${page > 1}">
			<a href="<c:url value="/admin?page=${page-1}"/>">«</a>
		</c:if>
		<c:forEach var="i" begin="1" end="${totalpages}">
			<a href="<c:url value="/admin?page=${i}"/>"><c:out value="${i}" /></a>
		</c:forEach>
		<c:if test="${page < totalpages}">
			<a href="<c:url value="/admin?page=${page+1}"/>">»</a>
		</c:if>
</div>
<!-- End Paging block -->
<br />
<c:url var="createUser" value="admin/adduser/" />
<a class="btn btn-success" href="${createUser}"><spring:message
		code="crsms.admin.createNew" /></a>


