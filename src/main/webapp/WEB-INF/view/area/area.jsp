<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>

<c:set var = "areaName"><spring:message code = "crsms.text.name"/></c:set>

<div align="center">
<h2><spring:message code = "crsms.area.list"/></h2>
<c:if test="${!empty getAllAreas}">
    <table class = "table table-bordered table-hover">
        <tr class = "success">
            <th width="1200">${areaName}</th>
            <th><spring:message code="crsms.courses.text.management" /></th>
        </tr>
        <c:forEach items="${getAllAreas}" var="area">
            <tr>
                <td>${area.name}</td>
                <td>
					<div align="center">
						<a href = "areas/${area.id}/edit" 
						class = "btn btn-success btn-sm"
						data-toggle = "tooltip"
						title="<spring:message code="crsms.button.edit" />" >
						<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
					</a>
						<a 	href="areas/${area.id}/delete"
							class="btn btn-danger btn-sm" 
							data-toggle="tooltip"
							title="<spring:message code="crsms.button.delete" />"
						>
							<span class="glyphicon glyphicon-remove"></span>		
						</a>
					</div>
				</td>
            </tr>
        </c:forEach>
    </table>
</c:if>
</div>
<c:url var="addAction" value="/areas/add"></c:url>
<form:form action="${addAction}" modelAttribute="area" method="POST">
	<form:hidden path="id"/>
	<table>
        <tr>
            <td>
            	<form:input path="name" class="form-control" placeholder = "${areaName}"/>
            </td>
            <td width = "20">
            </td>
            <td colspan="2" >
                <c:if test="${!empty area.name}">
                    <input type="submit" class="btn btn-success"
                           value="<spring:message code="crsms.area.edit"/>"/>
                </c:if>
                <c:if test="${empty area.name}">
                    <input type="submit" class="btn btn-success"
                           value="<spring:message code="crsms.area.add"/>"/>
                </c:if>
            </td>
        </tr>
    </table>
</form:form>