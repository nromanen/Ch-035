<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<input type = "hidden" id = "api-url" value = "${initParam['apiUrl']}">

<div id = "test-results-table-wrapper" class = "container">
    <table class="table table-bordered table-hover">
        <thead>
        <tr class = "active">
            <th class = "text-center"><spring:message code="crsms.test.results.text.student" /></th>
            <th class = "text-center"><spring:message code="crsms.test.results.text.test.name" /></th>
            <th class = "text-center"><spring:message code="crsms.test.results.text.group" /></th>
            <th class = "text-center"><spring:message code="crsms.test.results.text.score" /></th>
            <th class = "text-center"><spring:message code="crsms.test.results.text.actions" /></th>
            <%-- <th class = "text-center"><spring:message code="crsms.courses.text.startDate" /></th> --%>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="result" items="${results}">
            <tr>
                <td><c:out value="${result.user.userInfo.firstName}"/> <c:out value="${result.user.userInfo.lastName}"/></td>
                    <%-- <td><joda:format pattern="dd.MM.yyyy" value="${course.startDate}"  /></td> --%>
                <td>${result.test.name}</td>
                <td>${group.name}</td>
                <td class = "text-center">${result.score} / 100.0</td>
                <td>
                    <div align="center">
                        <c:url var="clearScore" value="${result.id}" />
                        <button data-clearscoreurl="${clearScore}" class="btn btn-warning btn-sm clear-score-button" ${result.complete ? '' : 'disabled'}>
                            <span class="glyphicon glyphicon-list-alt" aria-hidden="true"></span> <spring:message code="crsms.test.results.text.btn.clear" />
                        </button>
                    </div>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<div class="modal fade" id="clear-score-confirmation-modal" >
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><spring:message code = "crsms.tests.text.confirmation" /></h4>
            </div>
            <div class="modal-body">
                <p style="font-size: 15px; font-weight: 600;">
                    <spring:message code = "crsms.test.results.text.confirm.score" />
                </p>
            </div>
            <form:form id="clear-score-form">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            </form:form>
            <div class="modal-footer">
                <button id="btn-modal-clear-score" class="btn btn-primary"><spring:message code = "crsms.test.results.text.btn.clear" /></button>
                <button type="button" class="btn btn-default" data-dismiss="modal"><spring:message code = "crsms.button.cancel" /></button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->