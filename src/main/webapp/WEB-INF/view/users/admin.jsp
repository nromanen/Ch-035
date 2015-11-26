<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<c:set var = "order"/>
<c:if test="${sessionScope['direction'] == null || sessionScope['direction'] == 'asc'}">
<c:set var = "order" value = "desc"/>
</c:if>
<c:if test="${!empty users}">
<div id="search" class="container text-right">
		<div class="errorTxt"></div>
	<c:if test="${empty users}"><strong><spring:message code = "crsms.admin.search.notfound"/></strong></c:if>
	<form id = "searchForm" class="navbar-form navbar search" role="search" action="search" method="GET">
		<input id = "keyWord" type="text" class="form-control" name="keyWord" value = "${keyWord}" >
	    <button type="submit" id="submit" class="btn btn-default">
	    	<span class="glyphicon glyphicon-search"></span>
	    	<strong><spring:message code = "crsms.button.search" /></strong>
	    </button>
	</form>
</div>
	<table class="table table-bordered table-hover">
		<thead>
			<tr class="active">
				<th class = "hide"><spring:message code="crsms.text.id" /></th>
				<th>
					<spring:message code="crsms.admin.email" />
					<a href="<c:url value="?page=${page}&sortparam=email&direction=${order}"/>">
					<i class="glyphicon glyphicon-sort" aria-hidden="true"></i>
					</a>
				</th>
				<th>
					<spring:message code="crsms.admin.userinfo.lastname" />
					<a href="<c:url value="?page=${page}&sortparam=lastName&direction=${order}"/>">
					<i class="glyphicon glyphicon-sort" aria-hidden="true"></i>
					</a>
				</th>
				<th>
					<spring:message code="crsms.admin.userinfo.firstname" />
					<a href="<c:url value="?page=${page}&sortparam=firstName&direction=${order}"/>">
					<i class="glyphicon glyphicon-sort" aria-hidden="true"></i>
					</a>
				</th>
				<th>
					<spring:message code="crsms.admin.role" />
					<a href="<c:url value="?page=${page}&sortparam=role&direction=${order}"/>">
					<i class="glyphicon glyphicon-sort" aria-hidden="true"></i>
					</a>
				</th>
				<th>
					<spring:message code="crsms.admin.isenabled" />
					<a href="<c:url value="?page=${page}&sortparam=isEnabled&direction=${order}"/>">
					<i class="glyphicon glyphicon-sort" aria-hidden="true"></i>
					</a>
				</th>
				<th colspan="2"><spring:message code="crsms.admin.management" /></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${users}" var="user">
				<tr>
					<th class = "hide">${user.id}</th>
					<td class="nameCell">${user.email}</td>
					<td class="nameCell">${user.userInfo.lastName}</td>
					<td class="nameCell">${user.userInfo.firstName}</td>
					<td class="managementCell">${user.role.name}</td>
					<td class="managementCell">${user.isEnabled}</td> 
					<td class="managementCell">
						<c:url var="editUser"	value="/admin/${user.id}/edit" /> 
							<a href="${editUser}" class="btn btn-primary btn-sm" 
								data-toggle="tooltip"
								title="<spring:message code="crsms.button.edit" />">
								<span	class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
							</a>
					</td>
					<td class="managementCell">
						<c:url var="deleteUser"	value="/admin/${user.id}/delete" /> 
							<a href="${deleteUser}"
								class="btn btn-danger btn-sm" 
								data-toggle="tooltip"
								title="<spring:message code="crsms.button.delete" />"> 
								<span	class="fa fa-trash-o fa-lg" aria-hidden="true"></span>
							</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</c:if>

<!-- Paging block -->
<div class="paginationlogic">
	<ul class="pagination">
		<c:choose>
			<c:when test="${page == 1}">
				<li class="disabled">
					<a href="<c:url value="#"/>" 
						data-toggle="tooltip"
						title="<spring:message code="crsms.paginationlogic.tooltip.first" />"> 
						<spring:message	code="crsms.paginationlogic.navigation.first" />
					</a>
				</li>
				<li class="disabled">
					<a href="<c:url value="#"/>" 
						data-toggle="tooltip"
						title="<spring:message code="crsms.paginationlogic.tooltip.previous" />"> 
						<spring:message	code="crsms.paginationlogic.navigation.previous" />
					</a>
				</li>
			</c:when>
			<c:when test="${page > 1}">
				<li>				
					<a href="<c:url value="/admin?page=${1}&sortparam=${sessionScope['sortparam']}
											&direction=${sessionScope['direction']}"/>" 
						data-toggle="tooltip"
						title="<spring:message code="crsms.paginationlogic.tooltip.first" />"> 
						<spring:message	code="crsms.paginationlogic.navigation.first" />
					</a>
				</li>
				<li>
					<a	href="<c:url value="/admin?page=${page - 1}&sortparam=${sessionScope['sortparam']}
											&direction=${sessionScope['direction']}"/>" 
						data-toggle="tooltip"
						title="<spring:message code="crsms.paginationlogic.tooltip.previous" />"> 
						<spring:message	code="crsms.paginationlogic.navigation.previous" />
					</a>
				</li>
			</c:when>
		</c:choose>
			<c:forEach var="p" begin="1" end="${lastpage}">
				<li	<c:choose>
						<c:when test="${p == page}"> 
							class = "active"
						</c:when>
					</c:choose>>
					<a href="<c:url value="/admin?page=${p}&sortparam=${sessionScope['sortparam']}
											&direction=${sessionScope['direction']}"/>">
						<c:out	value="${p}"/>
					</a>
				</li>			
			</c:forEach>
			<c:choose>
			<c:when test="${page == lastpage}">
				<li class="disabled">
					<a	href="<c:url value="#"/>"	
						data-toggle="tooltip"
						title="<spring:message code="crsms.paginationlogic.tooltip.next" />"> 
						<spring:message	code="crsms.paginationlogic.navigation.next" />
					</a>
				</li>
				<li class="disabled">
					<a href="<c:url value="#"/>" 
						data-toggle="tooltip"
						title="<spring:message code="crsms.paginationlogic.tooltip.last" />"> 
						<spring:message	code="crsms.paginationlogic.navigation.last" />
					</a>
				</li>
			</c:when>		
			<c:when test="${page < lastpage}">
				<li>
					<a	href="<c:url value="/admin?page=${page + 1}&sortparam=${sessionScope['sortparam']}
											&direction=${sessionScope['direction']}"/>" 
						data-toggle="tooltip"
						title="<spring:message code="crsms.paginationlogic.tooltip.next" />">
					 	<spring:message	code="crsms.paginationlogic.navigation.next" />
					</a>
				</li>
				<li>
					<a href="<c:url value="/admin?page=${lastpage}&sortparam=${sessionScope['sortparam']}
											&direction=${sessionScope['direction']}"/>"  
						data-toggle="tooltip"
						title="<spring:message code="crsms.paginationlogic.tooltip.last" />"> 
						<spring:message	code="crsms.paginationlogic.navigation.last" />
					</a>
				</li>
			</c:when>
		</c:choose>
	</ul>
</div>
<!-- End Paging block -->

<c:url var="createUser" value="adduser" />
	<a class="btn btn-primary btn-sm" href="${pageContext.request.contextPath}/signUp" data-toggle="tooltip"
				title="<spring:message code="crsms.admin.createNew" />">
				<spring:message	code="crsms.admin.createNew" />
	</a>
		


