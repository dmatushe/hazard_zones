package com.intellias.mvp.hazard.model.service.impl;

import com.intellias.mvp.hazard.model.dao.DaoFactory;
import com.intellias.mvp.hazard.model.dao.HazardObjectsDao;
import com.intellias.mvp.hazard.model.dao.ImpactZoneDao;
import com.intellias.mvp.hazard.model.entity.HazardObjects;
import com.intellias.mvp.hazard.model.entity.ImpactZone;
import com.intellias.mvp.hazard.model.service.JsonService;

import java.util.List;

public class DefaultJsonService implements JsonService {
//    private static volatile JsonService jsonService;
//    private static HazardObjectsDao hazardObjectsDao;
//    private static ImpactZoneDao impactZoneDao;
//
//    private DefaultJsonService(){
//        hazardObjectsDao = DaoFactory.getInstance().getHazardObjectsDao();
//        impactZoneDao = DaoFactory.getInstance().getImpactZoneDao();
//    }
//
//    public static JsonService getInstance() {
//        JsonService localInstance = jsonService;
//        if (localInstance == null) {
//            synchronized (DefaultJsonService.class) {
//                localInstance = jsonService;
//                if (localInstance == null) {
//                    jsonService = new DefaultJsonService();
//                }
//            }
//        }
//        return jsonService;
//    }
//

    private static volatile JsonService jsonService;
    private static HazardObjectsDao hazardObjectsDao;
    private static ImpactZoneDao impactZoneDao;

    private DefaultJsonService(){
        hazardObjectsDao = DaoFactory.getInstance().getHazardObjectsDao();
        impactZoneDao = DaoFactory.getInstance().getImpactZoneDao();
    }

    public static JsonService getInstance(){
        JsonService localInstance = jsonService;
        if (localInstance == null){
            synchronized (DefaultJsonService.class){
                localInstance = jsonService;
                if (localInstance == null){
                    jsonService = new DefaultJsonService();
                }
            }
        }
        return jsonService;
    }
    @Override
    public String parseToMainGeoJson() {
//создаёт стринг для базового слоя с объектами
        return null;
    }

    @Override
    public List<HazardObjects> findAllObjects() {
        return hazardObjectsDao.findAll();
    }

    @Override
    public List<ImpactZone> findAllZones() {
        return impactZoneDao.findAll();
    }

}


