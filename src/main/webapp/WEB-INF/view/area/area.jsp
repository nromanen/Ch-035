<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ page session="false" %>

<c:set var = "areaName"><spring:message code = "crsms.text.name"/></c:set>
<tiles:insertAttribute name="jquery-validation-messages"></tiles:insertAttribute>

<div align="center">
<c:if test="${!empty getAllAreas}">
    <table class = "table table-bordered table-hover">
        <tr class = "active">
            <th width="1200">${areaName}</th>
            <th><spring:message code="crsms.courses.text.management" /></th>
        </tr>
        <c:forEach items="${getAllAreas}" var="area">
            <tr>
                <td>${area.name}</td>
                <td>
					<div align="center">

						<a href = "/crsms/areas/${area.id}/edit" 
							class = "btn btn-primary btn-sm"
							data-toggle = "tooltip"
							title = "<spring:message code="crsms.button.edit" />" >
							<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
						</a>

						<a href = "/crsms/areas/${area.id}/delete"
							class = "btn btn-danger btn-sm" 
							data-toggle = "tooltip"
							title = "<spring:message code="crsms.button.delete" />" >
							<span class="fa fa-trash-o fa-lg"></span>		
						</a>
					</div>
				</td>
            </tr>
        </c:forEach>
    </table>
</c:if>
</div>

<c:url var="addAction" value="/areas/add"></c:url>
<form:form id="saveArea" action="${addAction}" modelAttribute="area" method="POST" onsubmit="return submitForm();">
	<form:hidden path="id"/>
	<table>
        <tr>
            <td>
            	<form:input name="name" path="name" class="form-control" placeholder = "${areaName}"/>
            </td>
            <td width = "20">
            </td>
            <td colspan="2" >
                <c:if test="${!empty area.name}">
                    <input type="submit" class="btn btn-primary"
                           value="<spring:message code="crsms.area.edit"/>" id="btn"/>
                </c:if>
                <c:if test="${empty area.name}">
                    <input type="submit" class="btn btn-primary"
                           value="<spring:message code="crsms.area.add"/>" id="btn"/>
                </c:if>
            </td>
        </tr>
        <tr>
        	<td>
        		<div class="errorTxt"></div>
        	</td>
        </tr>
    </table>
</form:form>