package com.intellias.mvp.hazard.controller.command.impl;

import com.intellias.mvp.hazard.controller.command.Command;
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

        //TODO
        //Считать объект и тип ситуации. Передать на сервис, там вызвать запрос к бд, обработать результат
        //Вывести на главную новый геогсон и количество пострадавших.
        Long objectId = Long.parseLong(request.getParameter("object_id"));
        Long zoneId = Long.parseLong(request.getParameter("class_id"));
        String resultGeoJson = calculateHazardZones.calculateZones(objectId, zoneId);
        Integer impactPopulation = calculateHazardZones.calculateImpactPopulation(objectId, zoneId);


        request.setAttribute("result_geojson", resultGeoJson);
        request.setAttribute("result_population", impactPopulation);



       //Again fill in the dropdown menus
        List<HazardObjects> allObjects = jsonService.findAllObjects();
        List<ImpactZone> allZones = jsonService.findAllZones();

        request.setAttribute("hazard_objects", allObjects);
        request.setAttribute("hazard_classes", allZones);


        return PathManager.getProperty("path.page.main");
    }
}


