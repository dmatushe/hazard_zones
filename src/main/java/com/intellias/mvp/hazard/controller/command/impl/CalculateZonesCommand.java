package com.intellias.mvp.hazard.controller.command.impl;

import com.intellias.mvp.hazard.controller.command.Command;
import com.intellias.mvp.hazard.model.dto.PopulationStatistic;
import com.intellias.mvp.hazard.model.entity.HazardObjects;
import com.intellias.mvp.hazard.model.entity.ImpactZone;
import com.intellias.mvp.hazard.model.service.CalculateHazardZones;
import com.intellias.mvp.hazard.model.service.JsonService;
import com.intellias.mvp.hazard.model.service.impl.DefaultCalculateHazardZones;
import com.intellias.mvp.hazard.model.service.impl.DefaultJsonService;
import com.intellias.mvp.hazard.model.util.PathManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class CalculateZonesCommand implements Command {
    private static CalculateHazardZones calculateHazardZones;
    private static JsonService jsonService;

    public CalculateZonesCommand() {
        calculateHazardZones = DefaultCalculateHazardZones.getInstance();
        jsonService = DefaultJsonService.getInstance();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        Long objectId = Long.parseLong(request.getParameter("object_id"));
        String resultGeoJson = calculateHazardZones.calculateZones(objectId);
        String resultStatGeoJson = calculateHazardZones.calculateStatZones(objectId);
        PopulationStatistic populationStatistic = calculateHazardZones.calculateImpactPopulation(objectId);

        //area_stat_view_point
        request.setAttribute("result_stat_geojson", resultStatGeoJson);
        //area_stat_view
        request.setAttribute("result_geojson", resultGeoJson);

        request.setAttribute("result_population", populationStatistic);


        //  Again fill in the dropdown menu
        List<HazardObjects> allObjects = jsonService.findAllObjects();
        request.setAttribute("hazard_objects", allObjects);
        return PathManager.getProperty("path.page.main");
    }
}


