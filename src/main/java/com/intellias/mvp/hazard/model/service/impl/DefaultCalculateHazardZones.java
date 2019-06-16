package com.intellias.mvp.hazard.model.service.impl;


import com.intellias.mvp.hazard.model.dao.DaoFactory;
import com.intellias.mvp.hazard.model.dao.HazardObjectsDao;
import com.intellias.mvp.hazard.model.service.CalculateHazardZones;

public class DefaultCalculateHazardZones implements CalculateHazardZones {
    private static volatile CalculateHazardZones calculateHazardZones;
    private static HazardObjectsDao hazardObjectsDao;

    private DefaultCalculateHazardZones(){
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

    public String calculateZones(Long objectId, Long impactZoneId){
        //принимает id зоны и опасности. Обращается в DAO, получает String
    return hazardObjectsDao.calculateZones(objectId, impactZoneId);
    }

    @Override
    public Integer calculateImpactPopulation(Long objectId, Long zoneId) {
        return hazardObjectsDao.calculateImpactPopulation(objectId, zoneId);
    }

}


