<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<tiles:insertAttribute name="jquery-validation-messages"></tiles:insertAttribute>

<form:form modelAttribute = "moduleJsonDto" id="module-form" method = "POST" class = "form-horizontal">
	<form:hidden path = "id"/>
	<form:hidden path = "courseId"/>
	
	<div class="form-group">
		<c:set var = "moduleName"><spring:message code = "crsms.createmodule.name"/></c:set>
		<label for = "name" class="col-sm-2 control-label">${moduleName}: </label>
		<div class="col-sm-10">
			<form:input path = "name" id = "name" name="name"
						class="form-control" placeholder="${moduleName}"/>
			<form:errors path = "name" cssClass = "label label-danger" />
		</div>
	</div>
	
	<div class="form-group">
		<c:set var = "moduleDescription"><spring:message code = "crsms.text.description"/></c:set>
		<label for = "description" class="col-sm-2 control-label">${moduleDescription}: </label>
		<div class="col-sm-10">
			<form:textarea path = "description" name = "description" 
							class="form-control" placeholder="${moduleDescription}" rows="10"/>
			<form:errors path = "description" cssClass = "label label-danger" />
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
			<c:set var = "moduleSave"><spring:message code = "crsms.button.save"/></c:set>
			<input type = "submit" id = "save" value = "${moduleSave}" class="btn btn-primary"/>
		</div>
	</div>		
</form:form>