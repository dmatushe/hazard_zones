<?xml version="1.0" encoding="UTF-8" ?>
<%@ page session="true" isELIgnored="false" contentType="text/html; charset=UTF-8" language="java"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="language" value="${not empty sessionScope.language ? sessionScope.language : 'uk_UA'}"
       scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="text"/>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/bootstrap.css"/>">
    <link rel="stylesheet" type="text/js" href="<c:url value="/resources/js/bootstrap.js"/>">
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark sticky-top" style="background-color: #5a6268">
    <a class="navbar-brand" href="${pageContext.request.contextPath}/hazard?action=main"><i>Hazard zones</i></a>
    <%-- Collapse button --%>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#basicExampleNav"
            aria-controls="basicExampleNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav ml-auto ">
                    <li class="nav-item">
                        <a class="nav-link"
                           href="${pageContext.request.contextPath}/hazard?action=change_language&language=en_US"><fmt:message
                                key="text.lang.eng"/></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link"
                           href="${pageContext.request.contextPath}/hazard?action=change_language&language=uk_UA"><fmt:message
                                key="text.lang.ukr"/></a>
                    </li>
                </ul>
            </div>


</nav>

<%-- JS --%>
<%--<script src="/resources/vendor/jquery/jquery.min.js"></script>--%>
<%--<script src="/resources/js/main.js"></script>--%>
<script src="/resources/js/bootstrap.js" type="text/javascript"></script>
</body>
</html>
