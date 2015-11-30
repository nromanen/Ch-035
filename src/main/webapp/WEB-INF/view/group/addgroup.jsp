<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<tiles:insertAttribute name="jquery-validation-messages"></tiles:insertAttribute>

<form:form modelAttribute = "groupFormDto" id="group-form" method = "POST" class = "form-horizontal">
	<form:hidden path = "id"/>
	<form:hidden path = "courseId"/>
	
	<div class="form-group">
		<label for = "name" class="col-sm-2 control-label"><spring:message code="crsms.text.name" /></label>
		<div class="col-sm-10">
			<form:input path = "name" id = "name" name="name" class="form-control" placeholder="Name"/>
		</div>
	</div>
	
	<div class="form-group">
		<label for="startDate" class="col-sm-2 control-label"><spring:message code="crsms.groups.text.startDate" /></label>
		<div class="col-sm-10" >
			<div class='input-group date' id='datetimepicker1' >
				<form:input path="startDate" id="startDate" name="startDate" class="form-control" placeholder="dd/MM/yyyy" readonly="true" />
				<span class="input-group-addon">
					<i class="glyphicon glyphicon-calendar"></i>
				</span>
			</div>
		</div>
	</div>
	
	<div class="form-group">
		<div class="col-sm-offset-2 col-sm-10">
			<input type = "submit" value = "Submit" class="btn btn-primary"/>
		</div>
	</div>	
</form:form>