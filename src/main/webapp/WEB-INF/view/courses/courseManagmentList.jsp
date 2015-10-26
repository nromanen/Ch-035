<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>
<table class="table">
	<caption>Courses</caption>
	<thead>
		<th>Name</th>
		<th>Start date</th>
		<th>Open</th>
		<th>direction</th>
		<th>command</th>
	</thead>
	<tbody>
		<c:forEach var="course" items="${courses}">
			<tr>
				<td><c:out value="${course.name}"/></td>
				<td><joda:format pattern="dd.MM.yyyy" value="${course.startDate}"  /></td>
				<td>${course.open}</td>
				<td></td>
				<td>
					<a class="btn btn-primary btn-xs" href="courses/${course.id}/edit">edit</a>
					<a class="btn btn-danger btn-xs" href="courses/${course.id}/delete">delete</a>
					
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<a class="btn btn-success" href="courses/add">new</a>