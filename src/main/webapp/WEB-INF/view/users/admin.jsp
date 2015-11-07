<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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
<nav class="navbar navbar-default">
    <div class="container-fluid">
       <form class="navbar-form navbar-left" role="search">
        <div class="form-group">
          <input type="text" class="form-control" placeholder="Search">
        </div>
        <button type="submit" class="btn btn-default"><span class="glyphicon glyphicon-search"></span> Find</button>
      </form>
      <ul class="nav navbar-nav navbar-right">
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"> Rows<span class="caret"></span></a>
          <ul class="dropdown-menu">
            <li><a href="#">5</a></li>
            <li><a href="#">10</a></li>
            <li><a href="#">15</a></li>
          </ul>
        </li>
      </ul>
    </div>
</nav>
	<table class="table table-bordered table-hover">
		<thead>
			<tr class="success">
				<th class="hide"><spring:message code="crsms.text.id" /></th>
				<th><spring:message code="crsms.admin.email" /><a href="<c:url value="?sortby=email"/>"><span class="glyphicon glyphicon-sort"></span></a></th>
				<th><spring:message code="crsms.admin.password" /></th>
				<th><spring:message code="crsms.admin.role" /><a href="<c:url value="?sortby=role"/>"><span class="glyphicon glyphicon-sort"></span></a></th>
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
			</a>
			</li>
		</c:if>
		<c:if test="${pagenumber > 1}">
			<li><a	href="<c:url value="/admin?pagenumber=${pagenumber - 1}"/>"> 
					<spring:message	code="crsms.paginationlogic.navigation.previous" />
				</a>
			</li>
		</c:if>
		<c:forEach var="p" begin="1" end="${lastpagenumber}">
			<li	<c:choose>
					<c:when test="${p == pagenumber}"> 
						class = "active"
					</c:when>
				</c:choose>>
				<a href="<c:url value="/admin?pagenumber=${p}"/>">
				<c:out	value="${p}" />
				</a>
			</li>			
		</c:forEach>
		<c:if test="${pagenumber == lastpagenumber}">
			<li class="disabled">
				<a	href="<c:url value="/admin?pagenumber=${pagenumber}"/>"> 
						<spring:message	code="crsms.paginationlogic.navigation.next" />
				</a>
			</li>
		</c:if>
		<c:if test="${pagenumber < lastpagenumber}">
			<li>
				<a	href="<c:url value="/admin?pagenumber=${pagenumber + 1}"/>"> 
						<spring:message	code="crsms.paginationlogic.navigation.next" />
				</a>
			</li>
		</c:if>
	</ul>
</div>
<!-- End Paging block -->

<c:url var="createUser" value="admin/adduser/" />
<a class="btn btn-success" href="${createUser}"><spring:message
		code="crsms.admin.createNew" /></a>


