package com.intellias.mvp.hazard.model.dao.impl;

import com.intellias.mvp.hazard.model.dao.DaoFactory;
import com.intellias.mvp.hazard.model.dao.HazardObjectsDao;
import com.intellias.mvp.hazard.model.dao.ImpactZoneDao;

import java.sql.Connection;
import java.sql.SQLException;

public class PostGisDaoFactory extends DaoFactory {

    @Override
    public HazardObjectsDao getHazardObjectsDao() {
        return new PostGisHazardObjectsDao(getConnection());
    }

    @Override
    public ImpactZoneDao getImpactZoneDao() {
        return new PostGisImpactZoneDao(getConnection());
    }

    private Connection getConnection() {
        try {
            return PostGisConnectionPoolHolder.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}


