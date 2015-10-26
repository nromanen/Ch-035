<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>

<div align="center">
<h2>
    Add Direction
</h2>

<c:url var="addAction" value="/direction/add"></c:url>

<form:form action="${addAction}" commandName="direction">
    <table>
        <c:if test="${!empty direction.name}">
            <tr>
                <td>
                    <form:label path="id">
                        <spring:message text="ID"/>
                    </form:label>
                </td>
                <td>
                    <form:input path="id" readonly="true" size="8" disabled="true"/>
                    <form:hidden path="id"/>
                </td>
            </tr>
        </c:if>
        <tr>
            <td>
                <form:label path="name">
                    <spring:message text="Name"/>
                </form:label>
            </td>
            <td>
                <form:input path="name"/>
            </td>
            <td colspan="2">
                <c:if test="${!empty direction.name}">
                    <input type="submit"
                           value="<spring:message text="Edit Direction"/>"/>
                </c:if>
                <c:if test="${empty direction.name}">
                    <input type="submit"
                           value="<spring:message text="Add Direction"/>"/>
                </c:if>
            </td>
        </tr>
    </table>
</form:form>
</div>
<br>
<div align="center">
<h3>Directions List</h3>
<c:if test="${!empty getAllDirections}">
    <table class="tg">
        <tr>
            <th width="80">Direction ID</th>
            <th width="120">Direction Name</th>
            <th width="60">Edit</th>
            <th width="60">Delete</th>
        </tr>
        <c:forEach items="${getAllDirections}" var="direction">
            <tr>
                <td align="center">${direction.id}</td>
                <td>${direction.name}</td>
                <td>
                	<form:form method="POST" commandname="person-entity" action="process-person.html">  
									<table>  
									    <tbody><tr>  
									        <td><form:label path="firstName">Name:</form:label></td>  
									        <td><form:input path="firstName"></form:input></td>  
									    </tr>  
									    <tr>  
									        <td><form:label path="age">Age:</form:label></td>  
									        <td><form:input path="age"></form:input></td>  
									    </tr>  
									    <tr>  
									        <td colspan="2">  
									            <input type="submit" value="Submit">  
									        </td>  
									        <td></td>  
									        <td></td>  
									    </tr>  
									</tbody></table>    
									</form:form>  
                </td>
                <td align="center"><a href="<c:url value='/direction/remove/${direction.id}' />">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
</c:if>
</div>
>