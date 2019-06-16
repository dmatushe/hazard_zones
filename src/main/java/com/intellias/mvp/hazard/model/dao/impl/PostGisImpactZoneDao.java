package com.intellias.mvp.hazard.model.dao.impl;

import com.intellias.mvp.hazard.model.dao.ImpactZoneDao;
import com.intellias.mvp.hazard.model.entity.ImpactZone;
import com.intellias.mvp.hazard.model.util.SQLConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostGisImpactZoneDao implements ImpactZoneDao {
    private Connection connection;

    public PostGisImpactZoneDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean create(ImpactZone entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public ImpactZone findById(long id) {
        ImpactZone zone = null;
        try (PreparedStatement stm = connection.prepareStatement(SQLConnectionManager.getProperty("by_id_hazard_zones"))) {
            stm.setLong(1, id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                zone = parseFromResultSet(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return zone;
    }

    @Override
    public List<ImpactZone> findAll() {
        List<ImpactZone> result = new ArrayList<>();
        try (Statement stm = connection.createStatement()){
            ResultSet rs = stm.executeQuery(SQLConnectionManager.getProperty("all_hazard_zones"));
            while (rs.next()){
                result.add(parseFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean update(ImpactZone entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean delete(long id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void close() throws Exception {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private ImpactZone parseFromResultSet(ResultSet rs) throws SQLException {
        ImpactZone result = new ImpactZone();
        result.setId(rs.getLong("gid"));
        result.setClassType(rs.getString("class"));
        result.setRadius_m(rs.getInt("radius_m"));

        return result;
    }
}


