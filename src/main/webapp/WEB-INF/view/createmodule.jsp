<%@ taglib uri="http://www.springframework.org/tags/form"  prefix="form"%>

<div class="container">	
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
				<div class="checkbox">
					<label for = "available">
						<form:checkbox path="available" id = "available"/> Available
					</label>
				</div>
			</div>
		</div>
		
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<input type = "submit" value = "Save" class="btn btn-default"/>
			</div>
		</div>		
	</form:form>
</div>