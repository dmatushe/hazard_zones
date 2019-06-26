package com.intellias.mvp.hazard.model.service;

import com.intellias.mvp.hazard.model.dto.HazardAreaView;
import com.intellias.mvp.hazard.model.dto.PopulationStatistic;

public interface CalculateHazardZones  {
    String calculateZones(Long objectId);
    PopulationStatistic calculateImpactPopulation(Long objectId);

    String calculateStatZones(Long objectId);
}


