<%@ taglib uri="http://www.springframework.org/tags/form"  prefix="form"%>

<h2>Create new module</h2>
<form:form modelAttribute = "module" method = "POST" >
	<table border = "1">
		<tr>
			<td><label for = "name">Module name: </label></td>
			<td><form:input path = "name" id = "name"/></td>
		</tr>
		
		<tr>
			<td><label for = "description">Description: </label></td>
			<td><form:textarea path = "description" name = "description"/></td>
		</tr>
		<tr>
			<td><span>Resources: </span></td>
			<td></td>
		</tr>
		
		<tr>
			<td></td>
               <td>
				<input type = "submit" value = "Submit"/>
               </td>
           </tr>
	</table>
</form:form>