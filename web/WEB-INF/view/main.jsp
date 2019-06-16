<%--
  Created by IntelliJ IDEA.
  User: UVlad
  Date: 02.06.2019
  Time: 13:20
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
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/bootstrap.css"/>">
    <link rel="stylesheet" type="text/js" href="<c:url value="/resources/js/bootstrap.js"/>">
</head>

<body>

<jstl:set var="main_geojson" value="${requestScope.main_geojson}">
    <%--!!!!!!!!!!!!!  TRANSFER MAIN GEOJSON HERE--%>
</jstl:set>
<jstl:set var="result_geojson" value="${requestScope.result_geojson}">
    <%--!!!!!!!!!!!!!  TRANSFER RESULT GEOJSON HERE--%>
</jstl:set>

<%--HEADER--%>
<jsp:include page="template/header.jsp"/>

<%-- ALARMS --%>
<jstl:if test="${not empty requestScope.input_error}">
    <div class="alert alert-danger" role="alert"><fmt:message key="text.alert.input.error"/></div>
</jstl:if>

<%-- CONTENT --%>
<div class="bg">
    <div class="row">
        <div class="col-7">
            <%--LEAFLET CODE--%>
        </div>
        <div class="col-1"></div>
        <div class="col-3">
            <form method="POST" class="register-form" id="register-form"
                  action="${pageContext.request.contextPath}/hazard?action=calculate">
                <br/>
                <br/>
                <div class="form-group">
                    <label for="hazard_object"><fmt:message key="text.choose.an.object"/></label>
                    <select class="form-control" name="hazard_object" id="hazard_object">
                        <c:forEach items="${requestScope.hazard_objects}" var="hazard_object">
                            <option value="${requestScope.gid}">${requestScope.name}</option>
                        </c:forEach>
                    </select>
                </div>
                <br/>
                <br/>
                <div class="form-group">
                    <label for="hazard_class"><fmt:message key="text.choose.class"/></label>
                    <select class="form-control" name="hazard_class" id="hazard_class">
                        <c:forEach items="${requestScope.hazard_classes}" var="hazard_class">
                            <option value="${requestScope.id}">${requestScope.classType}</option>
                        </c:forEach>
                    </select>
                </div>
                <br/>
                <br/>
                <div class="form-group form-button">
                    <input type="submit" name="calculate" id="calculate" class="form-submit"
                           value="<fmt:message key="text.calculate.button"/>"/>
                </div>
            </form>
            <jstl:if test="${not empty requestScope.result_data}">
                <p><fmt:message key="text.calculation.result.button"/> ${requestScope.calculation_result}</p>
            </jstl:if>
        </div>
        <jstl:if test="${not empty requestScope.result_population}">
            <p><fmt:message key="text.impact.population"/> ${requestScope.result_population}</p>
        </jstl:if>
        <div class="col-1"></div>
    </div>
</div>


<%--FOOTER--%>
<%--<jsp:include page="template/footer.jsp"/>--%>
</body>
</html>
