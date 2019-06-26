package com.intellias.mvp.hazard.model.dao;

import com.intellias.mvp.hazard.model.dto.HazardAreaView;
import com.intellias.mvp.hazard.model.dto.PopulationStatistic;
import com.intellias.mvp.hazard.model.dto.StatView;
import com.intellias.mvp.hazard.model.entity.HazardObjects;

public interface HazardObjectsDao extends GenericDao <HazardObjects> {
    HazardAreaView calculateZones(Long objectId);
    PopulationStatistic calculateImpactPopulation(Long objectId);

    StatView calculateStatZones(Long objectId);
}
