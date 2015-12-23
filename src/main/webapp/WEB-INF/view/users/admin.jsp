<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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
	<li role="presentation">
		<a href="${pageContext.request.contextPath}/signUp">
		   <spring:message code="crsms.admin.createNew" />
		</a>
	</li>
</ul>

<div class="tab-content">
	<div id="search" class="container-fluid ">
		<div class="nav navbar-nav ">
			<form id="itemsperpage" class="navbar-form navbar " method="GET">
				<label for="itemsperpage" class="control-label"> <spring:message
						code="crsms.admin.rows" />
				</label> <input id="itemsperpage" type="text" class="form-control"
					name="itemsperpage" value="${itemsperpage}"
					placeholder=<spring:message code="crsms.admin.rows" />>
			</form>
		</div>

		<div class="nav navbar-nav navbar-right">
			<form id="searchForm" class="navbar-form navbar search" role="search"
				method="GET">
				<input id="keyWord" type="text" class="form-control" name="keyWord"
					value="${keyWord}">
				<button type="button" onclick="ClearField();"
					class="btn btn-default">
					<span class="glyphicon glyphicon-remove"></span>
				</button>
				<button type="submit" id="submit" class="btn btn-default">
					<span class="glyphicon glyphicon-search"></span> <strong><spring:message
							code="crsms.button.search" /></strong>
				</button>
			</form>
		</div>
	</div>
	<!-- all users pane -->
	<div role="tabpanel" class="tab-pane fade in active active" id="get-all-pane">
		<jsp:include page="allusers.jsp" />
	</div>
	<!-- Teacher request pane -->
	<div role="tabpanel" class="tab-pane fade" id="approve-request-pane">
		<jsp:include page="requests.jsp" />
	</div>
	<!-- history request pane -->
	<div role="tabpanel" class="tab-pane fade" id="history-request-pane">
		<jsp:include page="history.jsp" />
	</div>
</div>




