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
<c:set var="language" value="${not empty sessionScope.language ? sessionScope.language : 'en_US'}"
       scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="text"/>
<html>
<head>
    <title><fmt:message key="text.title.main"/></title>

    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/bootstrap.css"/>">
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/leaflet/leaflet.css"/>">
    <link rel="stylesheet" type="text/js" href="<c:url value="/resources/js/bootstrap.js"/>">


    <script type="text/javascript" src="/resources/leaflet/leaflet.js"></script>
    <script src="/resources/jquery-3.4.1.min.js"></script>
    <script src="/resources/heatmap.js-develop\build\heatmap.js"></script>
    <script src="/resources/heatmap.js-develop\plugins\leaflet-heatmap\leaflet-heatmap.js"></script>
    <script src="/resources/Leaflet.MousePosition-master\src\L.Control.MousePosition.js"></script>


<%--    <script  src="/resources/hazard_point.geojson"></script>--%>
<%--    <script  src="/resources/hazard_pol.geojson"></script>--%>
<%--    <script  src="hazard_point.geojson"></script>--%>
<%--    <script  src="hazard_pol.geojson"></script>--%>

</head>

<body>

<jstl:set var="main_geojson" value="${requestScope.main_geojson}">
    <%--!!!!!!!!!!!!!  TRANSFER MAIN GEOJSON HERE--%>
</jstl:set>
<jstl:set var="hazard_object" value="${requestScope.result_geojson}">
    <%--!!!!!!!!!!!!!  TRANSFER RESULT GEOJSON HERE--%>
</jstl:set>
<jstl:set var="hazard_object_point" value="${requestScope.result_stat_geojson}">
    <%--!!!!!!!!!!!!!  TRANSFER RESULT GEOJSON HERE--%>
</jstl:set>

<%--HEADER--%>
<jsp:include page="template/header.jsp"/>

<%-- ALARMS --%>
<jstl:if test="${not empty requestScope.input_error}">
    <div class="alert alert-danger" role="alert"><fmt:message key="text.alert.input.error"/></div>
</jstl:if>

<%-- CONTENT --%>
<%--<div class="bg">--%>
    <div class="row">
        <div class="col-8">
            <div id="mapid">
                <script>
                    <%--    <p>${result_geojson}</p>         <p>${main_geojson}</p>--%>
                    // var map = L.map('mapid').setView([48, 24], 10);
                    // map.locate({setView: true, maxZoom: 16});
                    // L.tileLayer('http://{s}.tile.osm.org/{z}/{x}/{y}.png', {attribution: '&copy; <a href="http://osm.org/copyright">OpenStreetMap</a> contributors'}).addTo(map);


                    var map = L.map('mapid').setView([49.84166667,24.03138889], 11);
                    //map.locate({setView: true, maxZoom: 16});
                    var OSM=L.tileLayer('http://{s}.tile.osm.org/{z}/{x}/{y}.png', {
                        attribution: '&copy; <a href="http://osm.org/copyright">OpenStreetMap</a> contributors'
                    }).addTo(map);

                </script>

                <jstl:if test="${not empty requestScope.result_geojson}">
<%--                    <p>${requestScope.result_geojson}</p>--%>
                   <script>
                       // L.marker([49.84166667,24.03138889]).addTo(map)
                       var ZoneLayer = L.geoJson(${hazard_object}, {
                           style: function(feature) {
                               return {color: '#e75025',
                                   "opacity": 1};
                           }
                       }).addTo(map);


                   </script>
                <script>

<%--                    var PointLayer = L.geoJson(${hazard_object_point}, {--%>
<%--                        pointToLayer: function(feature, latlng) {--%>
<%--                            return new L.circleMarker(latlng, {radius: 0.1, fillOpacity: 0.30});--%>
<%--                        }--%>
<%--                    });--%>

                    //var BuildingLayer = L.geoJson(buildings, {
                    //	pointToLayer: function(feature, latlng) {
                    //        return new L.circleMarker(latlng, {radius: 5, fillOpacity: 0.30});
                    //    }
                    //}).addTo(map);



                    //BuildingLayer.eachLayer(function (layer) {
                    //		layer.bindPopup("<p class='vector-popup'>"+"Population:"+layer.feature.properties.f_00_06cou); +"</p>"
                    //	});


                    <%--var ZoneLayer = L.geoJson(${hazard_object}, {--%>
                    <%--    style: function(feature) {--%>
                    <%--        return {color: '#e75025',--%>
                    <%--            "opacity": 0};--%>
                    <%--    }--%>
                    <%--});--%>

                    <%--ZoneLayer.eachLayer(function (layer) {--%>
                    <%--    Children=layer.feature.properties.f_00_06cou+layer.feature.properties.f_07_17cou+layer.feature.properties.m_00_06cou+layer.feature.properties.m_07_17cou--%>
                    <%--    Famale=layer.feature.properties.f_00_06cou+layer.feature.properties.f_07_17cou+layer.feature.properties.f_18_24cou+layer.feature.properties.f_25_59cou+layer.feature.properties.f_60_xxcou--%>
                    <%--    Male=layer.feature.properties.m_00_06cou+layer.feature.properties.m_07_17cou+layer.feature.properties.m_18_24cou+layer.feature.properties.m_25_59cou+layer.feature.properties.m_60_xxcou--%>
                    <%--    Total=layer.feature.properties.sum_population--%>
                    <%--    Affected_area=layer.feature.properties.area_sq_km--%>
                    <%--    Affected_buildings=layer.feature.properties.building_count--%>
                    <%--    Buses=Math.round(Total/40)--%>

                    <%--    layer.bindPopup("<p class='vector-popup'>"+"Children:"+Children+'<br>'+"Famale:"+Famale+'<br>'+"Male:"+Male+'<br>'+"Total:"+Total+'<br>'+"Affected area:"+Affected_area+" sq km"+'<br>'+"Affected buildings:"+Affected_buildings+'<br>'+"Buses for evacuation:"+Buses); +"</p>"--%>
                    <%--});--%>


                    <%--heat_data=[];--%>
                    <%--PointLayer.eachLayer(function (layer) {--%>
                    <%--    heat_data.push({lat: layer.feature.geometry.coordinates[1], lng:layer.feature.geometry.coordinates[0], count: 3, radius:layer.feature.properties.radius_m*0.000008983156203658});--%>
                    <%--});--%>


                    <%--var cfg_Heat = {--%>
                    <%--    // radius should be small ONLY if scaleRadius is true (or small radius is intended)--%>
                    <%--    // if scaleRadius is false it will be the constant radius used in pixels--%>
                    <%--    radius: 'radius',--%>
                    <%--    "maxOpacity": .8,--%>
                    <%--    // scales the radius based on map zoom--%>
                    <%--    "scaleRadius": true,--%>
                    <%--    // if set to false the heatmap uses the global maximum for colorization--%>
                    <%--    // if activated: uses the data maximum within the current map boundaries--%>
                    <%--    //   (there will always be a red spot with useLocalExtremas true)--%>
                    <%--    "useLocalExtrema": true,--%>
                    <%--    // which field name in your data represents the latitude - default "lat"--%>
                    <%--    latField: 'lat',--%>
                    <%--    // which field name in your data represents the longitude - default "lng"--%>
                    <%--    lngField: 'lng',--%>
                    <%--    // which field name in your data represents the data value - default "value"--%>
                    <%--    valueField: 'count'--%>
                    <%--};--%>
                    <%--var heatmapLayer = new HeatmapOverlay(cfg_Heat);--%>


                    <%--var Hazard_Heat = {--%>
                    <%--    max: 8,--%>
                    <%--    data: heat_data--%>
                    <%--};--%>


                    <%--heatmapLayer.setData(Hazard_Heat);--%>

                    <%--var HazardLayers=L.featureGroup([heatmapLayer,ZoneLayer]).addTo(map);--%>


                    <%--var baseMaps = {--%>
                    <%--    'Open Street Map' :OSM--%>
                    <%--};--%>
                    <%--var overlayMaps = {--%>
                    <%--    "Hazard objects":HazardLayers--%>

                    <%--};--%>
                    <%--var LayersControl=L.control.layers(baseMaps,overlayMaps).addTo(map);--%>

                    <%--var scale= L.control.scale().addTo(map);--%>
                    <%--//L.map('map', {attributionControl: false});--%>

                    <%--$('.leaflet-control-attribution').hide()--%>
                    <%--L.control.attribution ({--%>
                    <%--    position: 'bottomright',--%>
                    <%--    prefix: '<span class="AttributionClass"><a href="https://www.intellias.com/?_ga=2.235557121.1497087251.1561312612-378117350.1559806357">Intellias</a></span>'+' | '+'<span class="AttributionClass"><a href="http://leafletjs.com/">Leaflet</a></span>'--%>
                    <%--}).addTo(map);--%>

                    <%--L.control.mousePosition({--%>
                    <%--    position : 'bottomright',--%>
                    <%--    collapsed : false,--%>
                    <%--    separator:'   '--%>
                    <%--}).addTo(map);--%>


                </script>
                </jstl:if>
            </div>

        </div>
        <%--<div class="col-1"></div>--%>
        <div class="col-4" id="cust3">
            <div class="cust2">
                <form method="POST" class="register-form" id="register-form"
                      action="${pageContext.request.contextPath}/hazard?action=calculate">
                    <br/>
                    <br/>
                    <br/>
                    <br/>
                    <div class="form-group">
                        <label for="hazard_object"><fmt:message key="text.choose.an.object"/></label>
                        <select class="form-control" name="object_id" id="hazard_object">
                            <c:forEach items="${requestScope.hazard_objects}" var="hazard_object">
                                <jstl:choose>
                                    <jstl:when test="${language == 'uk_UA'}">
                                        <option value="${hazard_object.gid}">${hazard_object.name}</option>
                                    </jstl:when>
                                    <jstl:otherwise>
                                        <option value="${hazard_object.gid}">${hazard_object.nameEng}</option>
                                    </jstl:otherwise>
                                </jstl:choose>
                            </c:forEach>
                        </select>
                    </div>
                    <br/>
                    <div class="form-group form-button">
                        <div align="center">
                            <input type="submit" name="calculate" id="calculate" class="btn-outline-dark"
                                   value="<fmt:message key="text.calculate.button"/>"/>
                        </div>
                    </div>
                </form>
                <jstl:if test="${not empty requestScope.result_population}">
                    <div class="row">

                        <div class="col-5">
                            <p> <fmt:message key="text.impact.population"/>  ${requestScope.result_population.total}</p>
                            <p>   <fmt:message key="text.children"/>:</p>
                            <p>   <fmt:message key="text.female"/>:</p>
                            <p>   <fmt:message key="text.male"/>:</p>
                            <p>   <fmt:message key="text.area"/>:</p>
                            <p>   <fmt:message key="text.buildings"/>:</p>
                            <p>   <fmt:message key="text.buses"/>:</p>
                        </div>
                        <div class="col-1"></div>
                        <div class="col-4">
                            <p>${requestScope.result_population.children}</p>
                            <p>${requestScope.result_population.female}</p>
                            <p>${requestScope.result_population.male}</p>
                            <p>${requestScope.result_population.area}</p>
                            <p>${requestScope.result_population.buildings}</p>
                            <p>${requestScope.result_population.buses}</p>
                        </div>
                    </div>
                </jstl:if>
            </div>
        </div>
    </div>
<%--</div>--%>


<%--FOOTER--%>
<%--<jsp:include page="template/footer.jsp"/>--%>
<!-- Make sure you put this AFTER Leaflet's CSS -->

</body>
</html>
