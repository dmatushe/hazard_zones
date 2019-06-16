<%--
  Created by IntelliJ IDEA.
  User: vushakova
  Date: 5/30/2019
  Time: 1:34 PM
  To change this template use File | Settings | File Templates.
--%>

<?xml version="1.0" encoding="UTF-8" ?>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty sessionScope.language ? sessionScope.language : 'uk_UA'}"
       scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="text"/>
<html>
<head>
    <title><fmt:message key="text.title.main"/></title>
    <link rel="stylesheet" type="text/css" href="<c:url value="../../resources/css/bootstrap.css"/>">
    <link rel="stylesheet" type="text/js" href="<c:url value="../../resources/js/bootstrap.js"/>">
</head>

<body>
<%--HEADER--%>
<jsp:include page="template/header.jsp"/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<div class="row">
    <div class="col-5"></div>
    <div class="col-2">
        <div class="btn-block">
            <div align="center">
                <a class="btn btn-outline-dark btn-block"
                   style="background-color: #5a6268; color: #c8cbcf" role="button"
                   href="${pageContext.request.contextPath}/hazard?action=main">
                    <div align="center"><fmt:message key="text.go.on"/></div>
                </a>
            </div>
        </div>

    </div>
    <div class="col-5"></div>
</div>


<%--FOOTER--%>
<%--<jsp:include page="template/footer.jsp"/>--%>
</body>
</html>
