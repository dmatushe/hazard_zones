package com.intellias.mvp.hazard.controller.command.impl;

import com.intellias.mvp.hazard.controller.command.Command;
import com.intellias.mvp.hazard.model.dao.DaoFactory;
import com.intellias.mvp.hazard.model.entity.HazardObjects;
import com.intellias.mvp.hazard.model.entity.ImpactZone;
import com.intellias.mvp.hazard.model.service.CalculateHazardZones;
import com.intellias.mvp.hazard.model.service.JsonService;
import com.intellias.mvp.hazard.model.service.impl.DefaultJsonService;
import com.intellias.mvp.hazard.model.util.PathManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class MainCommand implements Command {
    private static JsonService jsonService;

    public MainCommand() {
        jsonService = DefaultJsonService.getInstance();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
//Обращаемся к сервису, который создаёт изначальный гсон с объектами.
        String mainGeoJson = jsonService.parseToMainGeoJson();
//нужно вывести для выпадающих списков все объекты из базы и типы ситуаций.
        List<HazardObjects> allObjects = jsonService.findAllObjects();
       // List<ImpactZone> allZones = jsonService.findAllZones();

        request.setAttribute("hazard_objects", allObjects);
       // request.setAttribute("hazard_classes", allZones);
        request.setAttribute("main_geojson", mainGeoJson);
        return PathManager.getProperty("path.page.main");
    }

    public static void main(String[] args) {
        JsonService jsonService = DefaultJsonService.getInstance();
       List<HazardObjects> all = jsonService.findAllObjects();
       // List<HazardObjects> res = DaoFactory.getInstance().getHazardObjectsDao().findAll();
        System.out.println(all);
    }
}


