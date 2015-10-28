<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>

<div align="center">
<h2>
    Add Area
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
          
            <form:errors path="name" />
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
<div align="center">
<h3>Areas List</h3>
<c:if test="${!empty getAllAreas}">
    <table class="tg">
        <tr>
            <th width="80">Area ID</th>
            <th width="120">Area Name</th>
            <th width="60">Edit</th>
            <th width="60">Delete</th>
        </tr>
        <c:forEach items="${getAllAreas}" var="area">
            <tr>
                <td align="center">${area.id}</td>
                <td>${area.name}</td>
                <td align="center"><a href="<c:url value='/areas/${area.id}/edit' />">Edit</a></td>
                <td align="center"><a href="<c:url value='/areas/${area.id}/delete' />">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
</c:if>
</div>