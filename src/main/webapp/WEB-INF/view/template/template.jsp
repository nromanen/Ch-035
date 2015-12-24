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
<c:if test="${isXmas}">
	<tiles:importAttribute name="santaLogo" />
	<c:url value="${santaLogo}" var="santaLogoUrl" />
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
	<c:if test="${isXmas}">
		<tiles:importAttribute name="holidayCss"/>
		<link rel="stylesheet" type="text/css" href="<c:url value="${holidayCss}"/>">
	</c:if>
	<!-- end stylesheets -->
	
	<!-- scripts-->
	<c:forEach var="script" items="${javascripts}">
		<script type="text/javascript" src="<c:url value="${script}"/>"></script>
	</c:forEach>
	<c:if test="${isXmas}">
		<tiles:importAttribute name="jquerySnowfallSrc"/>
		<c:url value="${jquerySnowfallSrc}" var="jquerySnowfallUrl" />
		<script type="text/javascript" src="<c:url value="${jquerySnowfallSrc}dist/snowfall.jquery.min.js"/>"></script>
	</c:if>
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
    	<div id="page-title"><span>${headerTitle}</span></div>
	    <tiles:insertAttribute name="breadcrumbs"></tiles:insertAttribute>
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
<script type="text/javascript">
var crsmsGlobalHelper = {
		
}
</script>
<c:if test="${isXmas}">
<script type="text/javascript">
$(document).ready(function(e) {
	var helper = crsmsGlobalHelper;
	
	// santa logo
	helper.santaLogoUrl = "${santaLogoUrl}";
	var logoContainer = $('#logo');
	logoContainer.prepend('<div id="logo-xmas-img-cover"></div>');
	logoContainer.children('a').first()
		.prepend('<img id="logo-xmas-img" class="logo-img" src="' + helper.santaLogoUrl + '" />');
	$('#logo-default-img').addClass('opacity-0');
	
	// let it snow!!!
	helper.jquerySnowfall = {
		url: "${jquerySnowfallUrl}",
		flakeImg: "${jquerySnowfallUrl}images/flake.png",
		flakeBtnImg: "${jquerySnowfallUrl}images/flake-btn.png",
		flakeShadowed: "${jquerySnowfallUrl}images/flake-shadowed.png",
		headerBackground: "#B8CBD8",
	};
	var letItSnowBtn = 
		$('<button id="let-it-snow" class="btn btn-default">'
			+ '<img src="' + helper.jquerySnowfall.flakeBtnImg + '" />'
		+ '</button>').prependTo('#page-title');
	letItSnowBtn.attr('snow-mode', 'off');
	$('#page-title').addClass('collectonme');
	$('#footer').addClass('collectonme');
	letItSnowBtn.click(function(e) {
		$(document).snowfall('clear');
		if (letItSnowBtn.attr('snow-mode') === 'off') {
			$('#header').css('background', helper.jquerySnowfall.headerBackground);
			$(document).snowfall({shadow : true, round : true,  minSize: 5, maxSize:8, flakeCount : 75});
			letItSnowBtn.attr('snow-mode', 'round');
			return;
		}
		if (letItSnowBtn.attr('snow-mode') === 'round') {
			$(document).snowfall({image: helper.jquerySnowfall.flakeImg, minSize: 10, maxSize:32});
			letItSnowBtn.attr('snow-mode', 'img')
			return;
		}
		if (letItSnowBtn.attr('snow-mode') === 'img') {
			$(document).snowfall({collection : '.collectonme', flakeCount : 250,});
			letItSnowBtn.attr('snow-mode', 'collectonme')
			return;
		}
		if (letItSnowBtn.attr('snow-mode') === 'collectonme') {
			$('#header').css('background', '');
			letItSnowBtn.attr('snow-mode', 'off')
			return;
		}
	});
});
</script>
</c:if>
</html>