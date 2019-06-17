package com.intellias.mvp.hazard.model.service.impl;

import com.intellias.mvp.hazard.model.dao.DaoFactory;
import com.intellias.mvp.hazard.model.dao.HazardObjectsDao;
import com.intellias.mvp.hazard.model.dao.ImpactZoneDao;
import com.intellias.mvp.hazard.model.entity.HazardObjects;
import com.intellias.mvp.hazard.model.entity.ImpactZone;
import com.intellias.mvp.hazard.model.service.JsonService;
import org.postgis.PGgeometry;

import java.util.List;

public class DefaultJsonService implements JsonService {

    private static volatile JsonService jsonService;
    private static HazardObjectsDao hazardObjectsDao;
    private static ImpactZoneDao impactZoneDao;

    private DefaultJsonService() {
        hazardObjectsDao = DaoFactory.getInstance().getHazardObjectsDao();
        impactZoneDao = DaoFactory.getInstance().getImpactZoneDao();
    }

    public static JsonService getInstance() {
        JsonService localInstance = jsonService;
        if (localInstance == null) {
            synchronized (DefaultJsonService.class) {
                localInstance = jsonService;
                if (localInstance == null) {
                    jsonService = new DefaultJsonService();
                }
            }
        }
        return jsonService;
    }

    @Override
    public String parseToMainGeoJson() {
        List<HazardObjects> allObjects = hazardObjectsDao.findAll();
        String coordinates = null;
        StringBuilder geojson = new StringBuilder();
        geojson.append("{\n" +
                "\"type\": \"FeatureCollection\",\n" +
                "\"name\": \"main\",\n" +
                "\"crs\": { \"type\": \"name\", \"properties\": { \"name\": \"urn:ogc:def:crs:EPSG::32635\" } },\n" +
                "\"features\": [");

        for (int i = 0; i < allObjects.size() - 1; i++) {

            coordinates = parseCoordinatesFromGeom(allObjects.get(i).getGeom());

            geojson.append(
                    "{ \"type\": \"Feature\", \"properties\": {" +
                            " \"id\": " +  allObjects.get(i).getGid()+  "," +
                            " \"name\": " + "\""+ allObjects.get(i).getName() + "\""+ "," +
                            " \"class\": " + "\""+ allObjects.get(i).getClassType() + "\""+ "," +
                            " \"city\": " + "\""+ allObjects.get(i).getCity()+ "\"" + "," +
                            " \"street\": " + "\""+ allObjects.get(i).getStreet()+ "\"" + "," +
                            " \"house_num\": " + "\""+ allObjects.get(i).getHouseNum()+ "\"" + "," +
                            " \"class_int\": " + allObjects.get(i).getClassInt() + "}," +
                            "\"geometry\": { \"type\": \"MultiPoint\", \"coordinates\": [ [ " + coordinates + " ] ] } }"
            );
            geojson.append(",");
        }
//For the last one row
        coordinates = parseCoordinatesFromGeom(allObjects.get(allObjects.size() - 1).getGeom());
        geojson.append(
                "{ \"type\": \"Feature\", \"properties\": {" +
                        " \"id\": " +  allObjects.get(allObjects.size()-1).getGid()+  "," +
                        " \"name\": " + "\""+ allObjects.get(allObjects.size()-1).getName() + "\""+ "," +
                        " \"class\": " + "\""+ allObjects.get(allObjects.size()-1).getClassType() + "\""+ "," +
                        " \"city\": " + "\""+ allObjects.get(allObjects.size()-1).getCity()+ "\"" + "," +
                        " \"street\": " + "\""+ allObjects.get(allObjects.size()-1).getStreet()+ "\"" + "," +
                        " \"house_num\": " + "\""+ allObjects.get(allObjects.size()-1).getHouseNum()+ "\"" + "," +
                        " \"class_int\": " + allObjects.get(allObjects.size()-1).getClassInt() + "}," +
                        "\"geometry\": { \"type\": \"MultiPoint\", \"coordinates\": [ [ " + coordinates + " ] ] } }"
        );


        geojson.append("]\n" +
                "}\n");
//создаёт стринг для базового слоя с объектами
        return geojson.toString();
    }

    @Override
    public List<HazardObjects> findAllObjects() {
        return hazardObjectsDao.findAll();
    }

    @Override
    public List<ImpactZone> findAllZones() {
        return impactZoneDao.findAll();
    }

    private String parseCoordinatesFromGeom(PGgeometry geom) {
        //from SRID=32635;MULTIPOINT(287956.4124043531 5527308.630701397)
        //to 287956.4124043531,5527308.630701397
        String coordinates = geom.toString();
        return coordinates.substring(coordinates.indexOf("(") + 1, coordinates.indexOf(")")).replace(" ", ", ");
    }
}

