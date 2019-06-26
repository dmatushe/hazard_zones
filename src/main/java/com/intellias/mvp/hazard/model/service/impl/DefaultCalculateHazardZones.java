package com.intellias.mvp.hazard.model.service.impl;


import com.intellias.mvp.hazard.model.dao.DaoFactory;
import com.intellias.mvp.hazard.model.dao.HazardObjectsDao;
import com.intellias.mvp.hazard.model.dto.HazardAreaView;
import com.intellias.mvp.hazard.model.dto.PopulationStatistic;
import com.intellias.mvp.hazard.model.dto.StatView;
import com.intellias.mvp.hazard.model.service.CalculateHazardZones;


import java.io.*;

public class DefaultCalculateHazardZones implements CalculateHazardZones {
    private static volatile CalculateHazardZones calculateHazardZones;
    private static HazardObjectsDao hazardObjectsDao;

    private DefaultCalculateHazardZones() {
        hazardObjectsDao = DaoFactory.getInstance().getHazardObjectsDao();
    }

    public static CalculateHazardZones getInstance() {
        CalculateHazardZones localInstance = calculateHazardZones;
        if (localInstance == null) {
            synchronized (DefaultJsonService.class) {
                localInstance = calculateHazardZones;
                if (localInstance == null) {
                    calculateHazardZones = new DefaultCalculateHazardZones();
                }
            }
        }
        return calculateHazardZones;
    }

    public String calculateZones(Long objectId) {
        String result = null;
        HazardAreaView view = hazardObjectsDao.calculateZones(objectId);
        result = parseToJson(view);

        writeToFile(result);
        return result;
    }

    public String calculateStatZones(Long objectId) {
        String result = null;
        StatView view = hazardObjectsDao.calculateStatZones(objectId);
        result = parseToStatJson(view);

        writeToFilePoint(result);
        return result;

    }

    @Override
    public PopulationStatistic calculateImpactPopulation(Long objectId) {
        return hazardObjectsDao.calculateImpactPopulation(objectId);
    }

    private String parseToStatJson(StatView view) {
        String coordinates = parseCoordinates(view.getGeom().toString());
        StringBuilder result = new StringBuilder();
        result.append("{" +
                "  \"type\": \"FeatureCollection\"," +
                "  \"name\": \"hazard_area_stat_viev1\"," +
                "  \"crs\": { \"type\": \"name\", \"properties\": { \"name\": \"urn:ogc:def:crs:OGC:1.3:CRS84\" } }," +
                "  \"features\": [" +
                "    { \"type\": \"Feature\", " +
                "      \"properties\": { ");
        result.append("\"id\":" + view.getId() + ",");
        result.append("\"class_int\":" + view.getClass_int() + ",");
        result.append("\"radius_m\":" + view.getRadius_m() + ",");
        result.append("\"area_sq_km\":" + view.getArea_sq_km() + ",");
        result.append("\"building_count\":" + view.getBuilding_count() + ",");
        result.append("\"f_00_06cou\":" + view.getF_00_06cou() + ",");
        result.append("\"f_07_17cou\":" + view.getF_07_17cou() + ",");
        result.append("\"f_18_24cou\":" + view.getF_18_24cou() + ",");
        result.append("\"f_25_59cou\":" + view.getF_25_59cou() + ",");
        result.append("\"f_60_xxcou\":" + view.getF_60_xxcou() + ",");
        result.append("\"m_00_06cou\":" + view.getM_00_06cou() + ",");
        result.append("\"m_07_17cou\":" + view.getM_07_17cou() + ",");
        result.append("\"m_18_24cou\":" + view.getM_18_24cou() + ",");
        result.append("\"m_25_59cou\":" + view.getM_25_59cou() + ",");
        result.append("\"m_60_xxcou\":" + view.getM_60_xxcou() + ",");
        result.append("\"sum_population\":" + view.getSum_population() + "},");
        result.append("\"geometry\": { \"type\": \"Polygon\", \"coordinates\":\n" +
                "      [ [ [");
        result.append(coordinates);
        result.append("] ] ]," +
                "      } }" +
                "  ]" +
                "}");

        return result.toString();
    }

    private String parseCoordinates(String geom) {
        String res = geom;
        return res.substring((res.indexOf("(") + 2), res.lastIndexOf(")") - 1).replace(",", "],[").replace(" ", ", ");
    }

    private String parseToJson(HazardAreaView view) {
        String coordinates = parseGeom(view.getGeom().toString());
        StringBuilder result = new StringBuilder();

        result.append("{" +
                "  \"type\": \"FeatureCollection\"," +
                "  \"name\": \"hazard_area_stat_viev_point\"," +
                "  \"crs\": { \"type\": \"name\", \"properties\": { \"name\": \"urn:ogc:def:crs:OGC:1.3:CRS84\" } }," +
                "  \"features\": [" +
                "    { ");
        result.append("\"type\": \"Feature\", " +
                "      \"properties\": { ");
        result.append("\"id\":" + view.getId() + ",");
        result.append("\"class_int\":" + view.getClassInt() + ",");
        result.append("\"radius_m\":" + view.getRadiusM() + "},");
        result.append("\"geometry\": { " +
                "        \"type\": \"Point\", " +
                "        \"coordinates\":" + "["+coordinates+"]");
        result.append("}}" +
                "  ]" +
                "}");


        return result.toString();
    }

    private String parseGeom(String geom) {
        //   SRID=4326;POINT(24.054763820046162 49.866967946480756)
        return geom.substring(geom.indexOf("(") + 2, geom.lastIndexOf(")") - 1).replaceAll(" ", ", ");
    }


    private void writeToFilePoint(String result) {
        File file = new File("hazard_point.geojson");
        System.out.println(file.getAbsolutePath());
        // Создание файла
        try {
            // file.createNewFile();


            // Создание объекта FileWriter
            FileWriter writer = new FileWriter(file, false);

            // Запись содержимого в файл
            writer.write(result);
            writer.flush();
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeToFile(String result) {
        File file = new File("hazard_pol.geojson");
        System.out.println(file.getAbsolutePath());
        // Создание файла
        try {
            // file.createNewFile();


            // Создание объекта FileWriter
            FileWriter writer = new FileWriter(file, false);

            // Запись содержимого в файл
            writer.write(result);
            writer.flush();
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


