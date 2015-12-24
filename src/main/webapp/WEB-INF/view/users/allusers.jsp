<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var = "order" value = "asc"/>
<c:if test="${direction == null ||direction == 'asc'}">
<c:set var = "order" value = "desc"/>
</c:if>
<c:set var="sortParams" 
value="sortparam=${sessionScope['sortparam']}&direction=${sessionScope['direction']}&keyWord=${keyWord}&itemsperpage=${itemsperpage}" />
<table class="table table-bordered table-hover">
		<thead>
			<tr class="active">
				<th class = "hide"><spring:message code="crsms.text.id" /></th>
				<th>
					<spring:message code="crsms.admin.email" />
					<a href="<c:url value="?page=${page}&sortparam=email&direction=${order}&keyWord=${keyWord}&itemsperpage=${itemsperpage}"/>">
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
					<a href="<c:url value="?page=${page}&sortparam=userInfo.lastName&direction=${order}&keyWord=${keyWord}&itemsperpage=${itemsperpage}"/>">
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
					<a href="<c:url value="?page=${page}&sortparam=userInfo.firstName&direction=${order}&keyWord=${keyWord}&itemsperpage=${itemsperpage}"/>">
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
					<a href="<c:url value="?page=${page}&sortparam=role.name&direction=${order}&keyWord=${keyWord}&itemsperpage=${itemsperpage}"/>">
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
					<a href="<c:url value="?page=${page}&sortparam=isEnabled&direction=${order}&keyWord=${keyWord}&itemsperpage=${itemsperpage}"/>">
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
					<c:url var="editUser"	value="/private/admin/${user.id}" /> 
							<a 	href="${editUser}" class="btn btn-primary btn-sm"
								data-toggle="modal"
								data-target="#modalEdit_${user.id}"
								data-toggle="tooltip"
								title="<spring:message code="crsms.button.edit" />">
								<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
							</a>
								<!-- edit user modal window -->
									<div id="modalEdit_${user.id}" class="modal fade " role="dialog">
									  <div class="modal-dialog modal-sm-2">
									    <div class="modal-content">
												<!-- edituser.jsp -->
									    </div>
									  </div>
									</div>
									<!-- End modal -->
						 	<c:url var="deleteUser"	value="/private/admin/${user.id}/delete" />  
							<a 	href="#modalDeleteConfirm_${user.id}" class="btn btn-danger btn-sm"
								data-toggle="modal"
								data-toggle="tooltip"
								title="<spring:message code="crsms.button.delete" />"> 
								<span	class="fa fa-trash-o fa-lg" aria-hidden="true"></span>
							</a>
							<!-- delete user modal window-->
							<div id="modalDeleteConfirm_${user.id}" class="modal fade" role="dialog">
							  <div class="modal-dialog modal-sm">
							    <div class="modal-content">
							      <div class="modal-header">
							        <button type="button" class="close" data-dismiss="modal">&times;</button>
							       <h4 class="modal-title"> <b><spring:message code="crsms.admin.modal.delete.header"/></b></h4>
							      </div>
							      <div class="modal-body">
							        <spring:message code="crsms.admin.modal.delete.body" arguments="${user.email}" /> 
							      </div>
							      <div class="modal-footer">
							      <a class="btn btn-danger btn-default" aria-hidden="true"
							         href= "${deleteUser}"><spring:message code="crsms.button.delete" /></a>
							      <button type="button" class="btn btn-success btn-default" data-dismiss="modal" aria-hidden="true" >Cancel</button>
							      </div>
							    </div>
							  </div>
							</div>
							<!-- End modal -->
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<c:if test="${empty users}">
		<div class="text-center" >
			<div class="alert alert-danger">
	 		 <strong><spring:message code = "crsms.admin.search.notfound"/></strong>
			</div>
		</div>
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
					<a href="<c:url value="?page=${1}&${sortParams}"/>" 
						data-toggle="tooltip"
						title="<spring:message code="crsms.paginationlogic.tooltip.first" />"> 
						<spring:message	code="crsms.paginationlogic.navigation.first" />
					</a>
				</li>
				<li>
					<a	href="<c:url value="?page=${page - 1}&${sortParams}"/>" 
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
					<a href="<c:url value="?page=${p}&${sortParams}"/>">
						 <c:out	value="${p}"/>
					</a>
				</li>			
			</c:forEach>
			<c:choose>
			<c:when test="${page < lastpage}">
				<li>
					<a	href="<c:url value="?page=${page + 1}&${sortParams}"/>" 
						data-toggle="tooltip"
						title="<spring:message code="crsms.paginationlogic.tooltip.next" />">
					 	<spring:message	code="crsms.paginationlogic.navigation.next" />
					</a>
				</li>
				<li>
					<a href="<c:url value="?page=${lastpage}&${sortParams}"/>"  
						data-toggle="tooltip"
						title="<spring:message code="crsms.paginationlogic.tooltip.last" />"> 
						<spring:message	code="crsms.paginationlogic.navigation.last" />
					</a>
				</li>
			</c:when>
			<c:otherwise>
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
			</c:otherwise>
		</c:choose>
	</ul>
</div>
<!-- End Paging block -->