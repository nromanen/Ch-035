<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<form:form modelAttribute = "course" class="form-horizontal" method="POST" >
	<form:input path = "id" type = "hidden"/>
	<div class="form-group">
		<label for="name"  class="col-sm-2 control-label">Name:</label>
		<div class="col-sm-10" >
			<form:input path="name" id="name" class="form-control" />
		</div>
	</div>
	<div class="form-group">
		<label for="description" class="col-sm-2 control-label">Description</label>
		<div class="col-sm-10" >
			<form:textarea path="description" id="description" class="form-control" />
		</div>
	</div>
	
	<div class="form-group">
		<label for="area" class="col-sm-2 control-label">Area</label>
		<div class="col-sm-10" >
			<select id="areas" name="areaId">
    			<c:forEach var="carentArea" items="${areas}">
        			<option value="${carentArea.id}" >
        				${carentArea.name}
        			</option>
    			</c:forEach>
			</select>
		</div>
	</div>
	
	<div class="form-group">
		<label for="startDate" class="col-sm-2 control-label">Start date</label>
		<div class="col-sm-10" >
			<form:input path="startDate" id="startDate" class="form-control" placeholder="dd/MM/yyyy" />
		</div>
	</div>
	<div class="form-group">
		<label for="duration" class="col-sm-2 control-label">Duration</label>
		<div class="col-sm-2" >
			<div class="input-group" >
			<form:input path="weekDuration" id="duration" class="form-control " />
			<div class="input-group-addon">weeks</div> 
			</div>
		</div>
	</div>
	<div class="form-group">
		<div class="col-sm-offset-2 col-sm-10" >
			<div class="checkbox">
				<label>
					<form:checkbox path="open" id="open" /> Open
				</label>
			</div>
		</div>
	</div>
	<div class="form-group">
    	<div class="col-sm-offset-2 col-sm-10">
      		<button type="submit" class="btn btn-default">add</button>
    	</div>
	</div>
</form:form>