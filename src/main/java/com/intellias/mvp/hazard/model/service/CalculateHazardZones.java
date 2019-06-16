package com.intellias.mvp.hazard.model.service;

public interface CalculateHazardZones  {
    String calculateZones(Long objectId, Long impactZoneId);
    Integer calculateImpactPopulation(Long objectId, Long zoneId);
}


