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
					<th class="hide">${user.id}</th>
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
<div class="paginationlogic">
	<ul class="pagination">
		<c:if test="${pagenumber == 1}">
			<li class="disabled"><a href="<c:url value="#"/>"> 
				<spring:message	code="crsms.paginationlogic.navigation.previous" />
			</a></li>
		</c:if>
		<c:if test="${pagenumber > 1}">
			<li><a	href="<c:url value="/admin?pagenumber=${pagenumber - 1}"/>"> 
				<spring:message	code="crsms.paginationlogic.navigation.previous" />
			</a></li>
		</c:if>
		<c:forEach var="p" begin="1" end="${lastpagenumber}">
			<c:choose>
				<c:when test="${p == pagenumber}">
				<li class = "active">
				</c:when>
				<c:otherwise><li></c:otherwise>
			</c:choose>
			<a href="<c:url value="/admin?pagenumber=${p}"/>">
			<c:out	value="${p}" /></a></li>
			
			
		</c:forEach>

		<c:if test="${pagenumber == lastpagenumber}">
			<li class="disabled"><a
				href="<c:url value="/admin?pagenumber=${pagenumber}"/>"
				class="disabled"> <spring:message
						code="crsms.paginationlogic.navigation.next" />
			</a></li>
		</c:if>
		<c:if test="${pagenumber < lastpagenumber}">
			<li><a
				href="<c:url value="/admin?pagenumber=${pagenumber + 1}"/>"> <spring:message
						code="crsms.paginationlogic.navigation.next" />
			</a></li>
		</c:if>
	</ul>
</div>
<!-- End Paging block -->

<br />
<c:url var="createUser" value="admin/adduser/" />
<a class="btn btn-success" href="${createUser}"><spring:message
		code="crsms.admin.createNew" /></a>


