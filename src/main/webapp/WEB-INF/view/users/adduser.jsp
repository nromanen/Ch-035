<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div class="container">	
	<form:form modelAttribute = "user" method = "POST" class = "form-horizontal">
		<form:input path = "id" type = "hidden"/>
		
		<div class="form-group">
			<label for = "name" class="col-sm-2 control-label">email:</label>
			<div class="col-sm-10">
				<form:input path = "email" id = "email"	class="form-control"/>
			</div>
		</div>
		
		<div class="form-group">
			<label for = "password" class="col-sm-2 control-label">password: </label>
			<div class="col-sm-10">
				<form:textarea path = "password" name = "password" class="form-control"/>
			</div>
		</div>
		
		
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<input type = "submit" value = save class="btn btn-default"/>
			</div>
		</div>		
	</form:form>
</div>