<%@ taglib uri="http://www.springframework.org/tags/form"  prefix="form"%>

<div class="container">
	<h2>Create new module</h2>
	
	<form:form modelAttribute = "module" method = "POST" class = "form-horizontal">
		<form:input path = "id" type = "hidden"/>
		
		<div class="form-group">
			<label for = "name" class="col-sm-2 control-label">Module name: </label>
			<div class="col-sm-10">
				<form:input path = "name" id = "name" 
							class="form-control" placeholder="Module name"/>
			</div>
		</div>
		
		<div class="form-group">
			<label for = "description" class="col-sm-2 control-label">Description: </label>
			<div class="col-sm-10">
				<form:textarea path = "description" name = "description" 
								class="form-control" placeholder="Module description"/>
			</div>
		</div>
		
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<input type = "submit" value = "Submit" class="btn btn-default"/>
			</div>
		</div>		
	</form:form>
</div>