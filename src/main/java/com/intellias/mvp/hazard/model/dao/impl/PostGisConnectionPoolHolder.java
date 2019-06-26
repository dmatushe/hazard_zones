package com.intellias.mvp.hazard.model.dao.impl;

import com.intellias.mvp.hazard.model.dao.ConnectionPoolHolder;
import com.intellias.mvp.hazard.model.util.ConnectionDBManager;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class PostGisConnectionPoolHolder implements ConnectionPoolHolder {
    private static BasicDataSource ds = new BasicDataSource();

    static {
        ds.setUrl(ConnectionDBManager.getProperty("url"));
        ds.setUsername(ConnectionDBManager.getProperty("user"));
        ds.setPassword(ConnectionDBManager.getProperty("pass"));
//        ds.setMinIdle(Integer.valueOf(ConnectionDBManager.getProperty("min.idle")));
//        ds.setMaxIdle(Integer.valueOf(ConnectionDBManager.getProperty("max.idle")));
//        ds.setMaxOpenPreparedStatements(Integer.valueOf(ConnectionDBManager.getProperty("max.open.prepare.statement")));
    }

    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

    private PostGisConnectionPoolHolder() {
    }
}


