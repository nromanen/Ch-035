<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="joda" uri="http://www.joda.org/joda/time/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<table class="table table-bordered table-hover">
	<thead>
		<tr class="active">
			<th class="hide"><spring:message code="crsms.text.id" /></th>
			<th><spring:message code="crsms.admin.email" /></th>
			<th><spring:message code="crsms.admin.userinfo.lastname" /></th>
			<th><spring:message code="crsms.admin.userinfo.firstname" /></th>
			<th><spring:message code="crsms.admin.teacher.request.date" /></th>
			<th><spring:message code="crsms.admin.teacher.review.date" /></th>
			<th><spring:message code="crsms.admin.teacher.request.approved" /></th>
			<th><spring:message code="crsms.admin.management" /></th>

		</tr>
	</thead>
	<tbody>
		<c:forEach items="${requests}" var="request">
			<tr>
				<td class="hide">${request.id}</td>
				<td class="nameCell">${request.user.email}</td>
				<td class="nameCell">${request.user.userInfo.firstName}</td>
				<td class="nameCell">${request.user.userInfo.lastName}</td>
				<td class="managementCell"><joda:format pattern="dd.MM.yyyy"
						value="${request.requestedDate}" /></td>
				<td class="managementCell"><joda:format pattern="dd.MM.yyyy"
						value="${request.reviewdDate}" /></td>
				<td class="managementCell"><c:choose>
						<c:when test="${request.approved == 'true'}">
							<span class="glyphicon glyphicon-ok-circle text-success"
								data-toggle="tooltip"
								title="<spring:message code="crsms.admin.enabled" />"> </span>
						</c:when>
						<c:otherwise>
							<span class="glyphicon glyphicon-ban-circle text-danger"
								data-toggle="tooltip"
								title="<spring:message code="crsms.admin.disabled" />"> </span>
						</c:otherwise>
					</c:choose></td>
				<td class="managementCell">
							<c:url var="editRequest"	value="/admin/request/${request.id}/edit" /> 
								<a href="#modalApprove_${request.id}" class="btn btn-primary btn-sm" 
									data-toggle="modal"
									data-toggle="tooltip"
									title="<spring:message code="crsms.button.edit" />">
									<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
								</a>
								<!-- Modal -->
									<div id="modalApprove_${request.id}" class="modal fade" role="dialog">
									  <div class="modal-dialog modal-sm-2">
									    <div class="modal-content">
									      <div class="modal-header">
									        <button type="button" class="close" data-dismiss="modal">&times;</button>
									       <h4 class="modal-title"> <b>User request approving:</b></h4>
									      </div>
									      <form:form modelAttribute="request" method="POST" action="${editRequest}" class="form-horizontal" >
										      <div class="modal-body">
												  <p>Select approve/decline</p>
												    <div class="radio">
												      <label><input type="radio" name="approve" value="${approve = 'true'}">approve</label>
												    </div>
												    <div class="radio">
												      <label><input type="radio" name= "approve" value="${approve = 'false'}">decline</label>
												    </div>
										      </div>
										       <div class="modal-footer">
													<c:set var="requestSave">
														<spring:message code="crsms.request.edit.save" />
													</c:set>
													<input type="submit" value="${requestSave}" class="btn btn-primary" />
												    <button type="button" class="btn btn-success btn-default" data-dismiss="modal" aria-hidden="true" >Cancel</button>
										      </div>
									      </form:form>
									    </div><!-- /.modal-content -->
									  </div><!-- /.modal-dialog -->
									</div>
									<!-- End modal -->
						</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<div class="text-center">
	<c:if test="${empty requests}">
		<div class="alert alert-danger">
			<strong><spring:message code="crsms.admin.search.notfound" /></strong>
		</div>
	</c:if>
</div>