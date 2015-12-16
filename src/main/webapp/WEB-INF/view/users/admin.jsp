<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<c:set var = "order" value = "asc"/>
<c:if test="${direction == null ||direction == 'asc'}">
<c:set var = "order" value = "desc"/>
</c:if>
<ul class="nav nav-tabs" role="tablist">
	<li role="presentation" class="active">
		<a id="get-all-tab"
		   href="#get-all-pane"
		   aria-controls="get-all-pane"
		   role="tab"
		   data-toggle="tab">
			<spring:message code = "crsms.admin.pane.get.all"/> 
			<b><span class="badge badge-info">${rowscount}</span></b>
		</a>
	</li>
	<c:if test="${usersToApproveCount != 0}">
		<li role="presentation">
			<a id = "approve-request-tab"
			   href="#approve-request-pane"
			   aria-controls="approve-request-pane"
			   role="tab"
			   data-toggle="tab">
				<spring:message code = "crsms.admin.pane.teacher.request"/>
				<b><span class="badge badge-warning" >${usersToApproveCount}</span></b>
			</a>
		</li>
	</c:if>
	<c:if test="${teacherRequestsCount != 0}">
		<li role="presentation">
			<a id = "history-request-tab"
			   href="#history-request-pane"
			   aria-controls="history-request-pane"
			   role="tab"
			   data-toggle="tab">
				<spring:message code = "crsms.admin.pane.teacher.history"/> 
				<b><span class="badge badge-info" >${teacherRequestsCount}</span></b>
			</a>
		</li>
	</c:if>
</ul>
	<div class="tab-content">			
<div id="search" class="container ">
	<div class="nav navbar-nav navbar-left">
		<form id = "rowSet" class="navbar-form navbar "  method="GET">					
			<input id = "itemsperpage" type="text" class="form-control" name="itemsperpage" value = "${itemsperpage}"
				placeholder=<spring:message code="crsms.admin.rows" />>
		</form>
	</div>

	<div class="nav navbar-nav navbar-right">
	<form id = "searchForm" class="navbar-form navbar search" role="search"  method="GET">					
		<input id = "keyWord" type="text" class="form-control" name="keyWord" value = "${keyWord}" >
		<button type="button" onclick="ClearField();" class="btn btn-default">
		    	<span class="glyphicon glyphicon-remove"></span>
		</button>
	    <button type="submit" id="submit" class="btn btn-default">
	    	<span class="glyphicon glyphicon-search"></span>
	    	<strong><spring:message code = "crsms.button.search" /></strong>
	    </button>
	</form>
	</div>
</div>
<div role="tabpanel" class="tab-pane fade in active active" id="get-all-pane">	
	<table class="table table-bordered table-hover">
		<thead>
			<tr class="active">
				<th class = "hide"><spring:message code="crsms.text.id" /></th>
				<th>
					<spring:message code="crsms.admin.email" />
					<a href="<c:url value="?sortparam=email&direction=${order}&keyWord=${keyWord}&itemsperpage=${itemsperpage}"/>">
						<c:choose>
							<c:when test="${sortparam == 'email' && direction == 'asc'}">
								<i class="fa fa-sort-alpha-desc fa-lg"></i>
							</c:when>
							<c:when test="${sortparam == 'email' && direction == 'desc'}">
								<i class="fa fa-sort-alpha-asc fa-lg"></i>
							</c:when>
							<c:otherwise>
							<i class="fa fa-sort fa-lg"></i>
							</c:otherwise>
						</c:choose>
					</a>
				</th>
				<th>
					<spring:message code="crsms.admin.userinfo.lastname" />
					<a href="<c:url value="?sortparam=userInfo.lastName&direction=${order}&keyWord=${keyWord}&itemsperpage=${itemsperpage}"/>">
						<c:choose>
							<c:when test="${sortparam == 'userInfo.lastName' && direction == 'asc'}">
								<i class="fa fa-sort-alpha-desc fa-lg"></i>
							</c:when>
							<c:when test="${sortparam == 'userInfo.lastName' && direction == 'desc'}">
								<i class="fa fa-sort-alpha-asc fa-lg"></i>
							</c:when>
							<c:otherwise>
							<i class="fa fa-sort fa-lg"></i>
							</c:otherwise>
						</c:choose>
					</a>
				</th>
				<th>
					<spring:message code="crsms.admin.userinfo.firstname" />
					<a href="<c:url value="?sortparam=userInfo.firstName&direction=${order}&keyWord=${keyWord}&itemsperpage=${itemsperpage}"/>">
					<c:choose>
							<c:when test="${sortparam == 'userInfo.firstName' && direction == 'asc'}">
								<i class="fa fa-sort-alpha-desc fa-lg"></i>
							</c:when>
							<c:when test="${sortparam == 'userInfo.firstName' && direction == 'desc'}">
								<i class="fa fa-sort-alpha-asc fa-lg"></i>
							</c:when>
							<c:otherwise>
							<i class="fa fa-sort fa-lg"></i>
							</c:otherwise>
						</c:choose>
					</a>
				</th>
				<th>
					<spring:message code="crsms.admin.role" />
					<a href="<c:url value="?sortparam=role.name&direction=${order}&keyWord=${keyWord}&itemsperpage=${itemsperpage}"/>">
					<c:choose>
						<c:when test="${sortparam == 'role.name' && direction == 'asc'}">
							<i class="fa fa-sort-alpha-desc fa-lg"></i>
						</c:when>
						<c:when test="${sortparam == 'role.name' && direction == 'desc'}">
							<i class="fa fa-sort-alpha-asc fa-lg"></i>
						</c:when>
						<c:otherwise>
						<i class="fa fa-sort fa-lg"></i>
						</c:otherwise>
					</c:choose>
					</a>
				</th>
				<th>
					<spring:message code="crsms.admin.isenabled" />
					<a href="<c:url value="?sortparam=isEnabled&direction=${order}&keyWord=${keyWord}&itemsperpage=${itemsperpage}"/>">
						<c:choose>
							<c:when test="${sortparam == 'isEnabled' && direction == 'asc'}">
								<i class="fa fa-sort-alpha-desc fa-lg"></i>
							</c:when>
							<c:when test="${sortparam == 'isEnabled' && direction == 'desc'}">
								<i class="fa fa-sort-alpha-asc fa-lg"></i>
							</c:when>
							<c:otherwise>
							<i class="fa fa-sort fa-lg"></i>
							</c:otherwise>
						</c:choose>
					</a>
				</th>
				<th><spring:message code="crsms.admin.management" /></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${users}" var="user">
				<tr>
					<td class = "hide">${user.id}</td>
					<td class="nameCell">${user.email}</td>
					<td class="nameCell">${user.userInfo.lastName}</td>
					<td class="nameCell">${user.userInfo.firstName}</td>
					<td class="managementCell">${user.role.name}</td>
					<td class="managementCell" align="center">
					<c:choose>
							<c:when test="${user.isEnabled == 'true'}">
						    	<span class="glyphicon glyphicon-ok-circle text-success"
							    	data-toggle="tooltip"
							    	title="<spring:message code="crsms.admin.enabled" />">
						    	</span>
							</c:when>
							<c:otherwise>
						    	<span class = "glyphicon glyphicon-ban-circle text-danger"
						    		data-toggle="tooltip"
							    	title="<spring:message code="crsms.admin.disabled" />">
							    </span>
							</c:otherwise>
						</c:choose>
					</td> 
					<td class="managementCell">
						<c:url var="editUser"	value="/admin/${user.id}/edit" /> 
							<a href="${editUser}" class="btn btn-primary btn-sm" 
								data-toggle="tooltip"
								title="<spring:message code="crsms.button.edit" />">
								<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
							</a>
					
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
	<div class="errorTxt text-center" >
		<c:if test="${empty users}"><strong><spring:message code = "crsms.admin.search.notfound"/></strong></c:if>
	</div>
	<c:url var="createUser" value="adduser" />
	<a class="btn btn-primary btn-sm" href="${pageContext.request.contextPath}/signUp" data-toggle="tooltip"
				title="<spring:message code="crsms.admin.createNew" />">
				<spring:message	code="crsms.admin.createNew" />
	</a>
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
											&direction=${sessionScope['direction']}&keyWord=${keyWord}&itemsperpage=${itemsperpage}"/>" 
						data-toggle="tooltip"
						title="<spring:message code="crsms.paginationlogic.tooltip.first" />"> 
						<spring:message	code="crsms.paginationlogic.navigation.first" />
					</a>
				</li>
				<li>
					<a	href="<c:url value="/admin?page=${page - 1}&sortparam=${sessionScope['sortparam']}
											&direction=${sessionScope['direction']}&keyWord=${keyWord}&itemsperpage=${itemsperpage}"/>" 
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
											&direction=${sessionScope['direction']}&keyWord=${keyWord}&itemsperpage=${itemsperpage}"/>">
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
											&direction=${sessionScope['direction']}&keyWord=${keyWord}&itemsperpage=${itemsperpage}"/>" 
						data-toggle="tooltip"
						title="<spring:message code="crsms.paginationlogic.tooltip.next" />">
					 	<spring:message	code="crsms.paginationlogic.navigation.next" />
					</a>
				</li>
				<li>
					<a href="<c:url value="/admin?page=${lastpage}&sortparam=${sessionScope['sortparam']}
											&direction=${sessionScope['direction']}&keyWord=${keyWord}&itemsperpage=${itemsperpage}"/>"  
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
</div>

	<!-- Teacher request pane -->
	<div role="tabpanel"class="tab-pane fade" id="approve-request-pane">	
	<table class="table table-bordered table-hover">
			<thead>
				<tr class="active">
					<th class = "hide"><spring:message code="crsms.text.id" /></th>
					<th><spring:message code="crsms.admin.email" /></th>
					<th><spring:message code="crsms.admin.userinfo.lastname" /></th>
					<th><spring:message code="crsms.admin.userinfo.firstname" /></th>
					<th><spring:message code="crsms.admin.teacher.request.date" /></th>
					<th><spring:message code="crsms.admin.teacher.request.approved" /></th>
					<th><spring:message code="crsms.admin.management" /></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${usersToApprove}" var="userToApprove">
					<tr>
						<td class = "hide">${userToApprove.id}</td>
						<td class="nameCell">${userToApprove.email}</td>
						<td class="nameCell">${userToApprove.userInfo.lastName}</td>
					<td class="nameCell">${userToApprove.userInfo.firstName}</td>
						<td class="managementCell">${userToApprove.teacherRequest.requestedDate}</td>
						<td class="managementCell">
							<c:choose>
								<c:when test="${userToApprove.teacherRequest.approved == 'true'}">
							    	<span class="glyphicon glyphicon-ok-circle text-success"
								    	data-toggle="tooltip"
								    	title="<spring:message code="crsms.admin.enabled" />">
							    	</span>
								</c:when>
								<c:otherwise>
							    	<span class = "glyphicon glyphicon-ban-circle text-danger"
							    		data-toggle="tooltip"
								    	title="<spring:message code="crsms.admin.disabled" />">
								    </span>
								</c:otherwise>
							</c:choose>
						</td>
						<td class="managementCell">
							<c:url var="editRequest"	value="/admin/request/${userToApprove.teacherRequest.id}/edit" /> 
								<a href="${editRequest}" class="btn btn-primary btn-sm" 
									data-toggle="tooltip"
									title="<spring:message code="crsms.button.edit" />">
									<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
								</a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="errorTxt text-center" >
		<c:if test="${empty usersToApprove}"><strong><spring:message code = "crsms.admin.search.notfound"/></strong></c:if>
		</div>
	</div>
	<!-- history request pane -->
	<div role="tabpanel"class="tab-pane fade" id="history-request-pane">
		<table class="table table-bordered table-hover">
			<thead>
				<tr class="active">
					<th class="hide"><spring:message code="crsms.text.id" /></th>
					<th><spring:message code="crsms.admin.email" /></th>
					<th><spring:message code="crsms.admin.userinfo.lastname" /></th>
					<th><spring:message code="crsms.admin.userinfo.firstname" /></th>
					<th><spring:message code="crsms.admin.teacher.request.date" /></th>
					<th><spring:message code="crsms.admin.teacher.review.date" /></th>
					<th><spring:message code="crsms.admin.management" /></th>
					<th><spring:message code="crsms.admin.teacher.request.approved" /></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${requests}" var="request">
					<tr>
						<td class="hide">${request.id}</td>
						<td class="nameCell">${request.user.email}</td>
						<td class="nameCell">${request.user.userInfo.firstName}</td>
						<td class="nameCell">${request.user.userInfo.lastName}</td>
						<td class="managementCell">${request.requestedDate}</td>
						<td class="managementCell">${request.reviewdDate}</td>
						<td class="managementCell">${request.approved}</td>
						<td class="managementCell">
							<c:url var="editRequest"	value="/admin/request/${request.id}/edit" /> 
								<a href="${editRequest}" class="btn btn-primary btn-sm" 
									data-toggle="tooltip"
									title="<spring:message code="crsms.button.edit" />">
									<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
								</a>
						
							<c:url var="deleteRequest"	value="/admin/request/${request.id}/delete" /> 
								<a href="${deleteRequest}"
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
		<c:if test="${empty requests}"><strong><spring:message code = "crsms.admin.search.notfound"/></strong></c:if>
	</div>
</div>
	
		


