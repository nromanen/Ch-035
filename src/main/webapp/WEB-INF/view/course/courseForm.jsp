<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<form:form modelAttribute = "course" class="form-horizontal" method="POST" >
	<form:input path = "id" type = "hidden"/>
	<div class="form-group">
		<label for="name"  class="col-sm-2 control-label"><spring:message code="crsms.courses.text.name" /></label>
		<div class="col-sm-10" >
			<form:input path="name" id="name" class="form-control" />
			<form:errors path = "name" cssClass = "label label-danger" />
		</div>
	</div>
	<div class="form-group">
		<label for="description" class="col-sm-2 control-label"><spring:message code="crsms.courses.text.description" /></label>
		<div class="col-sm-10" >
			<form:textarea path="description" id="description" class="form-control" />
			<form:errors path = "description" cssClass = "label label-danger" />
		</div>
	</div>
	
	<div class="form-group">
		<label for="area" class="col-sm-2 control-label"><spring:message code="crsms.courses.text.area" /></label>
		<div class="col-sm-10" >
			<select id="areas" name="areaId">
    			<c:forEach var="carentArea" items="${areas}">
        			<option value="${carentArea.id}" 
        				<c:if test="${course.area.id == carentArea.id}">
   							<c:out value="selected"/>
						</c:if>
        			>
        				${carentArea.name}
        			</option>
    			</c:forEach>
			</select>
		</div>
	</div>
	
	<div class="form-group">
		<label for="startDate" class="col-sm-2 control-label"><spring:message code="crsms.courses.text.startDate" /></label>
		<div class="col-sm-10" >
			<div class='input-group date' id='datetimepicker1' >
				<form:input path="startDate" id="startDate" class="form-control" placeholder="dd/MM/yyyy" readonly="true" />
				<span class="input-group-addon">
					<i class="glyphicon glyphicon-calendar"></i>
				</span>
			</div>
		</div>
	</div>
	<div class="form-group">
		<label for="duration" class="col-sm-2 control-label"><spring:message code="crsms.courses.text.duration" /></label>
		<div class="col-sm-2" >
			<div class="input-group" >
			<form:input path="weekDuration" id="duration" class="form-control " />
			<div class="input-group-addon"><spring:message code="crsms.courses.text.weeks" /></div> 
			</div>
		</div>
	</div>
	<div class="form-group">
		<div class="col-sm-offset-2 col-sm-10" >
			<div class="checkbox">
				<label>
					<form:checkbox path="open" id="open" /> <spring:message code="crsms.courses.text.open" />
				</label>
			</div>
		</div>
	</div>
	<div class="form-group">
    	<div class="col-sm-offset-2 col-sm-10">
      		<button type="submit" class="btn btn-default btn-success"><spring:message code="crsms.button.save" /></button>
    	</div>
	</div>
</form:form>