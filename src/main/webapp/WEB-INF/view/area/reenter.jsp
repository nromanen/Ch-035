<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>

<div align="center">
<h2>
    <spring:message code = "crsms.text.error"/>
</h2>

<c:url var="addAction" value="/areas/add"></c:url>
<c:set var = "areaName"><spring:message code = "crsms.text.name"/></c:set>

<form:form action="${addAction}" modelAttribute="area">
	<form:hidden path="id"/>
	<table>
        <tr>
            <td>
            	<form:input path="name" placeholder = "${areaName}"/>
            </td>
            <td>
            	<form:errors path="name" class = "help-inline"/>
            </td>
         </tr>
         <tr align = "center">
            <td colspan="2">
                <c:if test="${!empty area.name}">
                    <input type="submit"
                           value="<spring:message code="crsms.button.save"/>"/>
                </c:if>
                <c:if test="${empty area.name}">
                    <input type="submit"
                           value="<spring:message code="crsms.button.save"/>"/>
                </c:if>
            </td>
        </tr>
    </table>
</form:form>
</div>
<br>
