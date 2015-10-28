<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>

<div align="center">
<h2>
    Something's wrong!
</h2>

<c:url var="addAction" value="/areas/add"></c:url>



 
<form:form action="${addAction}" modelAttribute="area">
	<form:hidden path="id"/>
	<table>
        <tr>
            <td>
                <form:label path="name">
                    <spring:message text="Name"/>
                </form:label>
            </td>
            <td>
            	<form:input path="name"/>
            
            </td>
            <td>
          
            <form:errors path="name" class = "help-inline"/>
            </td>
            <td colspan="2">
                <c:if test="${!empty area.name}">
                    <input type="submit"
                           value="<spring:message text="Edit Area"/>"/>
                </c:if>
                <c:if test="${empty area.name}">
                    <input type="submit"
                           value="<spring:message text="Add Area"/>"/>
                </c:if>
            </td>
        </tr>
    </table>
</form:form>
</div>
<br>
