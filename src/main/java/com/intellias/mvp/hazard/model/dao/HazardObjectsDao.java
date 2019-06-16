package com.intellias.mvp.hazard.model.dao;

import com.intellias.mvp.hazard.model.entity.HazardObjects;

public interface HazardObjectsDao extends GenericDao <HazardObjects> {
    String calculateZones(Long objectId, Long impactZoneId);
    Integer calculateImpactPopulation(Long objectId, Long zoneId);

}
