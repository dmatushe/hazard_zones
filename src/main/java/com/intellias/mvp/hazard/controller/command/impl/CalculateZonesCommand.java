package com.intellias.mvp.hazard.controller.command.impl;

import com.intellias.mvp.hazard.controller.command.Command;
import com.intellias.mvp.hazard.model.service.CalculateHazardZones;
import com.intellias.mvp.hazard.model.service.impl.DefaultCalculateHazardZones;
import com.intellias.mvp.hazard.model.util.PathManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CalculateZonesCommand implements Command {
    private static CalculateHazardZones calculateHazardZones;

    public CalculateZonesCommand() {
        calculateHazardZones = DefaultCalculateHazardZones.getInstance();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        //TODO
        //Считать объект и тип ситуации. Передать на сервис, там вызвать запрос к бд, обработать результат
        //Вывести на главную новый геогсон и количество пострадавших.
        Long objectId = Long.valueOf(request.getParameter("hazard_object"));
        Long zoneId = Long.valueOf(request.getParameter("hazard_class"));
        String resultGeoJson = calculateHazardZones.calculateZones(objectId, zoneId);
        Integer impactPopulation = calculateHazardZones.calculateImpactPopulation(objectId, zoneId);

        request.setAttribute("result_geojson", resultGeoJson);
        request.setAttribute("result_population", impactPopulation);
        return PathManager.getProperty("path.page.main");
    }
}


