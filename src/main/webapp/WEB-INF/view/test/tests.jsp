<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<div class="container">
    <table class="table table-bordered">
        <thead>
        <tr class="bg-primary">
            <th><spring:message code="crsms.tests.id"/></th>
            <th><spring:message code="crsms.tests.name"/></th>
            <th colspan="2"><spring:message code="crsms.tests.management"/></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="test" items="${tests}">
            <tr>
                <th>${test.id}</th>
                <td>${test.name}</td>
                <td class="managementCell">
                    <c:url var="editTest" value="${test.id}/edit"/>
                    <a href="${editTest}">
                        <span class="glyphicon glyphicon-pencil"></span> <spring:message code="crsms.tests.edit"/>
                    </a>
                </td>
                <td class="managementCell">
                    <c:url var="deleteTest" value="${test.id}/delete"/>
                    <a href="${deleteTest}">
                        <span class="glyphicon glyphicon-remove"></span> <spring:message code="crsms.tests.delete"/>
                    </a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <c:url var="createTest" value="add"/>
    <a class="btn btn-primary btn-lg pull-right" href="${createTest}"><spring:message code="crsms.tests.createNew"/></a>
</div>