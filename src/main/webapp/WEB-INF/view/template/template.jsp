<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<tiles:importAttribute name="javascripts"/>
<tiles:importAttribute name="stylesheets"/>
<tiles:importAttribute name="favicon"/>
<c:if test="${empty pageTitle}">
	<tiles:importAttribute name="title" toName="pageTitle" />
	<spring:message code = "${pageTitle}" var="pageTitle" />
</c:if>
<c:if test="${empty headerTitle}">
	<tiles:importAttribute name="header-title" toName="headerTitle" />
	<spring:message code = "${headerTitle}" var="headerTitle" />
</c:if>



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
    <title>${pageTitle}</title>
    <!-- end title -->
    
    <link rel="shortcut icon" href="<c:url value="${favicon}"/>" type="image/x-icon" />
    
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
    <header id="header">
    	<div class="container">
    	  <tiles:insertAttribute name="header"></tiles:insertAttribute>
    	</div>
	</header>
    <!-- end header  -->
    
    <!-- menu -->
    <div id="menu">
        <tiles:insertAttribute name="menu"></tiles:insertAttribute>
    </div>
    <!-- end menu  -->

    <!-- content -->
    <main id="main-body">
    	<div id="page-title">${headerTitle}</div>
    	<div id="main-container" class="<tiles:insertAttribute name="content-container-class"></tiles:insertAttribute>">
          <tiles:insertAttribute name="content"></tiles:insertAttribute>
        </div>
    </main>
    <!-- end content -->

    <!-- footer -->
    <footer id="footer" class="" >
    	<div class="container">
          <tiles:insertAttribute name="footer"></tiles:insertAttribute>
        </div>
    </footer>
    <!-- end footer -->
    
    <!-- Scroll To Top -->
    <button id="scroll-to-top" class="btn btn-warning"><i class="fa fa-chevron-up"></i></button>
    <!-- end Scroll To Top -->
	
</body>
</html>