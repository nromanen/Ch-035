<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Student page</title>
</head>
<body>
	Dear <strong>${user}</strong>, Welcome to Student Page.
	<a href="<c:url value="/logout" />">logout</a>
	<a href="/crsms/userProfile"> Personal profile </a>
</body>
</html>