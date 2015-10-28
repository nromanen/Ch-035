<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>

<c:set var = "areaName"><spring:message code = "crsms.text.name"/></c:set>

<div align="center">
<h2><spring:message code = "crsms.area.list"/></h2>
<c:if test="${!empty getAllAreas}">
    <table class="tg">
        <tr>
            <th width="40" align="center"><spring:message code = "crsms.text.id"/></th>
            <th width="120">${areaName}</th>
            <th width="100" align="center"><spring:message code = "crsms.button.edit"/></th>
            <th width="100" align="center"><spring:message code = "crsms.button.delete"/></th>
        </tr>
        <c:forEach items="${getAllAreas}" var="area">
            <tr>
                <td>${area.id}</td>
                <td>${area.name}</td>
                <td align="center"><a href="<c:url value='/areas/${area.id}/edit' />"><spring:message code = "crsms.button.edit"/></a></td>
                <td align="center"><a href="<c:url value='/areas/${area.id}/delete' />"><spring:message code = "crsms.button.delete"/></a></td>
            </tr>
        </c:forEach>
    </table>
</c:if>
</div>
<br>
<div align="center">
<h3>
    <spring:message code = "crsms.area.add"/>
</h3>

<c:url var="addAction" value="/areas/add"></c:url>
<form:form action="${addAction}" modelAttribute="area">
	<form:hidden path="id"/>
	<table>
        <tr>
            <td>
            	<form:input path="name" placeholder = "${areaName}"/>
            </td>
            <td>
            <form:errors path="name" />
            </td>
        </tr>
        <tr align="center">
            <td colspan="2">
                <c:if test="${!empty area.name}">
                    <input type="submit"
                           value="<spring:message code="crsms.area.edit"/>"/>
                </c:if>
                <c:if test="${empty area.name}">
                    <input type="submit"
                           value="<spring:message code="crsms.area.add"/>"/>
                </c:if>
            </td>
        </tr>
    </table>
</form:form>
</div>