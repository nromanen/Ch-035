<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<html>
<head>
  <title>Test Page</title>
  <style type="text/css">
    .tg {
      border-collapse: collapse;
      border-spacing: 0;
      border-color: #ccc;
    }

    .tg td {
      font-family: Arial, sans-serif;
      font-size: 14px;
      padding: 10px 5px;
      border-style: solid;
      border-width: 1px;
      overflow: hidden;
      word-break: normal;
      border-color: #ccc;
      color: #333;
      background-color: #fff;
    }

    .tg th {
      font-family: Arial, sans-serif;
      font-size: 14px;
      font-weight: normal;
      padding: 10px 5px;
      border-style: solid;
      border-width: 1px;
      overflow: hidden;
      word-break: normal;
      border-color: #ccc;
      color: #333;
      background-color: #f0f0f0;
    }

    .tg .tg-4eph {
      background-color: #f9f9f9
    }
  </style>
</head>
<body>
<div align="center">
  <h2>
    Add new Test
  </h2>



  <form:form action="tests/add" modelAttribute="test" method = "POST">
    <table>
      <c:if test="${not empty test.name}">
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
      </tr>
      <tr>
        <td>
          <form:label path="module.id">
            <spring:message text="Module ID"/>
          </form:label>
        </td>
        <td>
          <form:input path="module.id"/>
        </td>
      </tr>
      <tr align="center">
        <td colspan="2">
          <c:if test="${not empty test.name}">
            <br>
            <input type="submit"
                   value="<spring:message text="Edit Test"/>"/>
          </c:if>
          <c:if test="${empty test.name}">
            <br>
            <input type="submit"
                   value="<spring:message text="Add Test"/>"/>
          </c:if>
        </td>
      </tr>
    </table>
  </form:form>
</div>
<br>

<div align="center">
  <h3>Tests List</h3>
  <c:if test="${not empty tests}">
    <table class="tg">
      <tr>
        <th width="80">Test ID</th>
        <th width="120">Test Name</th>
        <th width="120">Module</th>
        <th width="60">Edit</th>
        <th width="60">Delete</th>
      </tr>
      <c:forEach items="${tests}" var="test">
        <tr>
          <td align="center">${test.id}</td>
          <td align="center">${test.name}</td>
          <td align="center">${test.module.name}</td>
          <td align="center"><a href="<c:url value='tests/${test.id}/edit' />">Edit</a></td>
          <td align="center"><a href="<c:url value='tests/${test.id}/remove' />">Delete</a></td>
        </tr>
      </c:forEach>
    </table>
  </c:if>
</div>
</body>
</html>