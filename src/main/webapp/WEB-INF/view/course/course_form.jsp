<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<tiles:insertAttribute name="jquery-validation-messages"></tiles:insertAttribute>

<form:form id="course-form" modelAttribute = "courseJsonDto" class="form-horizontal" method="POST" >
	<form:hidden path = "id"/>
	<form:hidden path = "ownerEmail"/>
	<form:hidden path = "published"/>
	
	<div class="form-group">
		<label for="name"  class="col-sm-2 control-label"><spring:message code="crsms.courses.text.name" /></label>
		<div class="col-sm-10" >
			<form:input path="name" id="name" name="name" class="form-control" />
			<form:errors path = "name" cssClass = "label label-danger" />
		</div>
	</div>
	
	<div class="form-group">
		<label for="description" class="col-sm-2 control-label"><spring:message code="crsms.courses.text.description" /></label>
		<div class="col-sm-10" >
			<form:textarea path="description" id="description" name="description" class="form-control" />
			<form:errors path = "description" cssClass = "label label-danger" />
		</div>
	</div>

	<div class="form-group">
		<label for="area" class="col-sm-2 control-label"><spring:message code="crsms.courses.text.area" /></label>
		<div class="col-sm-10" >
			<select id="areas" name="areaId">
				<c:forEach var="currentArea" items="${areas}">
					<option value="${currentArea.id}"
							<c:if test="${courseJsonDto.area.id == currentArea.id}">
								<c:out value="selected"/>
							</c:if>
							>
							${currentArea.name}
					</option>
				</c:forEach>
			</select>
		</div>
	</div>
	
	<div class="form-group">
		<label for="duration" class="col-sm-2 control-label"><spring:message code="crsms.courses.text.duration" /></label>
		<div class="col-sm-2" >
			<div class="input-group" >
				<form:input path="duration" id="duration" name="duration" class="form-control " />
				<div class="input-group-addon"><spring:message code="crsms.courses.text.weeks" /></div>
			</div>
			<label id="duration-error" class="errorTxt" for="duration"></label>
			<form:errors path = "duration" cssClass = "label label-danger" />
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
			<button type="submit" id="save" class="btn btn-primary"><spring:message code="crsms.button.save" /></button>
		</div>
	</div>
</form:form>