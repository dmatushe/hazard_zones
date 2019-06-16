package com.intellias.mvp.hazard.model.dao;

import com.intellias.mvp.hazard.model.dao.impl.PostGisDaoFactory;

/**
 * The {@code DaoFactory} class is an Abstract class to get instances of DAO
 *
 * @author Vladlena Ushakova
 */
public abstract class DaoFactory {

    private static volatile DaoFactory daoFactory;

    public abstract HazardObjectsDao getHazardObjectsDao();

    public abstract ImpactZoneDao getImpactZoneDao();



    public static DaoFactory getInstance() {
        DaoFactory localInstance = daoFactory;
        if (localInstance == null) {
            synchronized (DaoFactory.class) {
                localInstance = daoFactory;
                if (localInstance == null) {
                    daoFactory = new PostGisDaoFactory();
                }
            }
        }
        return daoFactory;
    }
}


