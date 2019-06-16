package com.intellias.mvp.hazard.model.service;

import com.intellias.mvp.hazard.model.entity.HazardObjects;
import com.intellias.mvp.hazard.model.entity.ImpactZone;

import java.util.List;

public interface JsonService {
    String parseToMainGeoJson();
    List <HazardObjects> findAllObjects();
    List <ImpactZone> findAllZones();
}
