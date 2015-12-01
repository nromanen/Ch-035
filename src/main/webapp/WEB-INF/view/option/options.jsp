<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<form class="form-horizontal" method="POST">
	<div class="form-group">
		<label for="${options[storageTypeOptionKey]}" class="col-sm-2 control-label"><spring:message code="crsms.option.${storageTypeOptionKey}" /></label>
		<div class="col-sm-4">
			<select class="form-control" name="${storageTypeOptionKey}" >
				<c:forEach var="storageType" items="${storageTypes}" >
				    <option ${storageTypeOptionCurrent == storageType ? 'selected' : ''} value="${storageType}">${storageType}</option>
				</c:forEach>
			</select>
		</div>
	</div>
	<div class="form-group col-sm-2">
		<input type="submit" class="btn btn-primary" value="<spring:message code="crsms.button.save" />" />
	</div>
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
 </form>
