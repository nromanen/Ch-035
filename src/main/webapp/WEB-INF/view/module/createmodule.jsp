<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<form:form modelAttribute = "module" method = "POST" class = "form-horizontal">
	<form:hidden path = "id"/>
	
	<div class="form-group">
		<c:set var = "moduleName"><spring:message code = "crsms.createmodule.name"/></c:set>
		<label for = "name" class="col-sm-2 control-label">${moduleName}: </label>
		<div class="col-sm-10">
			<form:input path = "name" id = "name" 
						class="form-control" placeholder="${moduleName}"/>
		</div>
	</div>
	
	<div class="form-group">
		<c:set var = "moduleDescription"><spring:message code = "crsms.createmodule.description"/></c:set>
		<label for = "description" class="col-sm-2 control-label">${moduleDescription}: </label>
		<div class="col-sm-10">
			<form:textarea path = "description" name = "description" 
							class="form-control" placeholder="${moduleDescription}"/>
		</div>
	</div>
	
	<div class="form-group">
		<div class="col-sm-offset-2 col-sm-10">
			<div class="checkbox">
				<label for = "available">
					<form:checkbox path="available" id = "available"/> <spring:message code="crsms.createmodule.available"/>
				</label>
			</div>
		</div>
	</div>
	
	<div class="form-group">
		<div class="col-sm-offset-2 col-sm-10">
			<c:set var = "moduleSave"><spring:message code = "crsms.createmodule.save"/></c:set>
			<input type = "submit" value = "${moduleSave}" class="btn btn-success"/>
		</div>
	</div>		
</form:form>