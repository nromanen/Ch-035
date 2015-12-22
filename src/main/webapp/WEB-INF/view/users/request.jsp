<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<form:form modelAttribute="request" method="POST" class="form-horizontal" >
		
<div class="container">
  <h2>User request approving:</h2>
  <p>Select approve/decline</p>
  
    <div class="radio">
      <label><input type="radio" name="approve" value="${approve = 'true'}">approve</label>
    </div>
    <div class="radio">
      <label><input type="radio" name= "approve" value="${approve = 'false'}">decline</label>
    </div>

</div>
	<div class="form-group">
		<div class="col-sm-offset-2 col-sm-4">
			<c:set var="requestSave">
				<spring:message code="crsms.request.edit.save" />
			</c:set>
			<input type="submit" value="${requestSave}" class="btn btn-primary" />
		</div>
	</div>
</form:form>
