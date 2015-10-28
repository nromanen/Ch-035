<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>

<div align="center">
<h2>
    <spring:message code = "crsms.area.add"/>
</h2>

<c:url var="addAction" value="/areas/add"></c:url>
<c:set var = "areaName"><spring:message code = "crsms.text.name"/></c:set>

<form:form action="${addAction}" modelAttribute="area">
	<form:hidden path="id"/>
	<table>
        <tr>
            <td>
            	<form:input path="name" placeholder = "${areaName}" class="form-control"/>
            </td>
            <td>
            	<form:errors path="name" class="text-danger" />
            </td>
         </tr>
         </table>
</div>         
         <br>
<div align = "center">         
         <input type="submit" class="btn btn-success"
                           value="<spring:message code="crsms.button.save"/>"/>
         </form:form>
</div>
<br>
