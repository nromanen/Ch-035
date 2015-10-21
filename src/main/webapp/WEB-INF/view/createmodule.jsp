<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form"  prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Create module</title>
</head>
<body>
	<h2>Create new module</h2>
	<form:form modelAttribute = "module" method = "POST" >
		<table>
			<tr>
				<td><label for "name">Module name: </label></td>
				<td><form:input path="name"/></td>
			</tr>
			
			<tr>
				<td><label for "description">Description: </label></td>
				<td><form:textarea path="description"/></td>
			</tr>
			
			<tr>
                <td>
					<input type="submit" value="Submit"/>
                </td>
            </tr>
		</table>
	</form:form>

</body>
</html>