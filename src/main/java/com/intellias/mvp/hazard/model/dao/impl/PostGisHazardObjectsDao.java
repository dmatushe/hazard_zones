package com.intellias.mvp.hazard.model.dao.impl;

import com.intellias.mvp.hazard.model.dao.DaoFactory;
import com.intellias.mvp.hazard.model.dao.HazardObjectsDao;
import com.intellias.mvp.hazard.model.entity.HazardObjects;
import com.intellias.mvp.hazard.model.util.SQLConnectionManager;
import org.postgis.Geometry;
import org.postgis.Point;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostGisHazardObjectsDao implements HazardObjectsDao {
    private Connection connection;

    public PostGisHazardObjectsDao (Connection connection) {
        this.connection = connection;
    }


    @Override
    public boolean create(HazardObjects entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public HazardObjects findById(long id) {
        HazardObjects object = null;
        try (PreparedStatement stm = connection.prepareStatement(SQLConnectionManager.getProperty("by_id_lviv_hazard_objects"))) {
            stm.setLong(1, id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                object = parseFromResultSet(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return object;
    }

    @Override
    public List<HazardObjects> findAll() {
        List<HazardObjects> result = new ArrayList<>();
        try (Statement stm = connection.createStatement()){
            ResultSet rs = stm.executeQuery(SQLConnectionManager.getProperty("all_lviv_hazard_objects"));
            while (rs.next()){
                result.add(parseFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean update(HazardObjects entity) {
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

    @Override
    public String calculateZones(Long objectId, Long impactZoneId) {
        //ОСНОВНОЙ МЕТОД. ОБРАЩАЕМСЯ К БАЗЕ, СОЗДАЁМ ВЬЮШКУ, ЗАПИСЫВАЕМ ЕЁ В СТРИНГ
        return null;
    }

    @Override
    public Integer calculateImpactPopulation(Long objectId, Long zoneId) {
        //TODO ПРОВЕРИТЬ ВЬЮШКУ НА ВСТАВЛЯЕМЫЕ ЗНАЧЕНИЯ
        Integer result = 0;
        try (PreparedStatement stm = connection.prepareStatement(SQLConnectionManager.getProperty("view_hazard_area_stat_view"))){
            stm.setLong(1,objectId);
            stm.setLong(2, zoneId);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                result = rs.getInt("sum_population");
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }


        //СЧИТАЕТ КОЛИЧЕСТВО ПОСТРАДАВШИХ
        return result;
    }
    private HazardObjects parseFromResultSet(ResultSet rs) throws SQLException {
        HazardObjects result = new HazardObjects();
        result.setGid(rs.getLong("id"));
        result.setName(rs.getString("name"));
        result.setClassType(rs.getString("class"));
        result.setCity(rs.getString("city"));
        result.setStreet(rs.getString("street"));
        result.setHouseNum(rs.getString("house_num"));
        result.setClassInt(rs.getInt("class_int"));
      //TODO
         //result.setGeom(rs.getString("geom"));

        return result;
    }

//    public static void main(String[] args) {
//        HazardObjectsDao dao = DaoFactory.getInstance().getHazardObjectsDao();
//        List<HazardObjects> foo = dao.findAll();
//        System.out.println(foo);
//    }
}


