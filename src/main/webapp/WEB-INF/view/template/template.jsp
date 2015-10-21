<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<tiles:importAttribute name="javascripts"/>
<tiles:importAttribute name="stylesheets"/>

<!DOCTYPE html>
<html>

<!-- head -->
<head>

    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="author" content="Softserve Ch-035">
    <meta name="description" content="SOME DESC">
    
    <!-- title -->
    <title><tiles:insertAttribute name="title"></tiles:insertAttribute></title>
    <!-- end title -->
    
    <!-- stylesheets -->
	<c:forEach var="css" items="${stylesheets}">
		<link rel="stylesheet" type="text/css" href="<c:url value="${css}"/>">
	</c:forEach>
	<!-- end stylesheets -->
	
	<!-- scripts-->
	<c:forEach var="script" items="${javascripts}">
		<script type="text/javascript" src="<c:url value="${script}"/>"></script>
	</c:forEach>
	<!-- end scripts -->
    
</head>
<!-- end head -->

<body>

    <!--[if lt IE 10]>
        <p class="alert alert-warning">
            Warning: You are using an unsupported version of Internet Explorer. We recommend using Internet Explorer
            10+. If you are a Windows XP user you'll need to download an alternative browsers such as FireFox, Chrome,
            Opera, or Safari. 
        </p>
    <![endif]-->

    <!-- header -->
    <div id="header">
        <tiles:insertAttribute name="header"></tiles:insertAttribute>
    </div>
    <!-- end header  -->
    
    <!-- menu -->
    <div id="menu">
        <tiles:insertAttribute name="menu"></tiles:insertAttribute>
    </div>
    <!-- end menu  -->

    <!-- content -->
    <div id="content">
        <tiles:insertAttribute name="content"></tiles:insertAttribute>
    </div>
    <!-- end content -->

    <!-- footer -->
    <div id="footer">
        <tiles:insertAttribute name="footer"></tiles:insertAttribute>
    </div>
    <!-- end footer -->

</body>
</html>