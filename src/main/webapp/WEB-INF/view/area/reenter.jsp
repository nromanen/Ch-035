<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>

<div align="center">
<h2>
    <spring:message code = "crsms.area.add"/>
</h2>
</div>

<c:url var="addAction" value="/areas/add"></c:url>
<c:set var = "areaName"><spring:message code = "crsms.text.name"/></c:set>

<form:form action="${addAction}" modelAttribute="area">
<div align="center">
	<form:hidden path="id"/>
	<table>
        <tr>
            <td>
            	<form:input path="name" placeholder = "${areaName}" class="form-control"/>
            </td>
            <td>
            	<form:errors path="name" class="label label-danger" />
            </td>
         </tr>
         </table>
</div>         
<br>
<div align = "center">
	<c:set var="backButton">
					<spring:message code="crsms.createtest.backButton" />
	</c:set>
	<a class="btn btn-success" role="button"
					onClick="history.go(-1);return true;">${backButton}</a>         
    <input type="submit" class="btn btn-success"
                           value="<spring:message code="crsms.button.save"/>"/>
</div>
</form:form>
