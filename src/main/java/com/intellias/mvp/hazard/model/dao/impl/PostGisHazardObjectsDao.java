package com.intellias.mvp.hazard.model.dao.impl;

import com.intellias.mvp.hazard.model.dao.DaoFactory;
import com.intellias.mvp.hazard.model.dao.HazardObjectsDao;
import com.intellias.mvp.hazard.model.dto.HazardAreaView;
import com.intellias.mvp.hazard.model.dto.PopulationStatistic;
import com.intellias.mvp.hazard.model.dto.StatView;
import com.intellias.mvp.hazard.model.entity.HazardObjects;
import com.intellias.mvp.hazard.model.util.SQLConnectionManager;
import org.postgis.PGgeometry;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostGisHazardObjectsDao implements HazardObjectsDao {
    private Connection connection;

    public PostGisHazardObjectsDao(Connection connection) {
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
        try (Statement stm = connection.createStatement()) {
            ResultSet rs = stm.executeQuery(SQLConnectionManager.getProperty("all_lviv_hazard_objects"));
            while (rs.next()) {
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
    public HazardAreaView calculateZones(Long objectId) {
        HazardAreaView view = null;
        try (PreparedStatement stm = connection.prepareStatement(SQLConnectionManager.getProperty("view_hazard_area_view"))) {
            stm.setLong(1, objectId);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                view = parseFromResultView(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return view;
    }

    @Override
    public PopulationStatistic calculateImpactPopulation(Long objectId) {
        //TODO ПРОВЕРИТЬ ВЬЮШКУ НА ВСТАВЛЯЕМЫЕ ЗНАЧЕНИЯ
        PopulationStatistic result = null;
        try (PreparedStatement stm = connection.prepareStatement(SQLConnectionManager.getProperty("view_hazard_area_stat_view"))) {
            stm.setLong(1, objectId);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                result = parseFromResultPopulation(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public StatView calculateStatZones(Long objectId) {
        StatView result = null;
        try (PreparedStatement stm = connection.prepareStatement(SQLConnectionManager.getProperty("area_stat_view"))) {
            stm.setLong(1, objectId);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                result = parseFromAreaStatView(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return result;
    }

    private StatView parseFromAreaStatView(ResultSet rs) throws SQLException {
        StatView result = new StatView();
        result.setId(rs.getLong("id"));
        result.setClass_int(rs.getInt("class_int"));
        result.setRadius_m(rs.getInt("radius_m"));
        result.setArea_sq_km(rs.getDouble("area_sq_km"));
        result.setBuilding_count(rs.getInt("building_count"));
        result.setF_00_06cou(rs.getInt("f_00_06cou"));
        result.setF_07_17cou(rs.getInt("f_07_17cou"));
        result.setF_18_24cou(rs.getInt("f_18_24cou"));
        result.setF_25_59cou(rs.getInt("f_25_59cou"));
        result.setF_60_xxcou(rs.getInt("f_60_xxcou"));
        result.setM_00_06cou(rs.getInt("m_00_06cou"));
        result.setM_07_17cou(rs.getInt("m_07_17cou"));
        result.setM_18_24cou(rs.getInt("m_18_24cou"));
        result.setM_25_59cou(rs.getInt("m_25_59cou"));
        result.setM_60_xxcou(rs.getInt("m_60_xxcou"));
        result.setSum_population(rs.getInt("sum_population"));
        result.setGeom((PGgeometry) rs.getObject("geom"));

   return result;
    }

    private HazardObjects parseFromResultSet(ResultSet rs) throws SQLException {
        HazardObjects result = new HazardObjects();
        result.setGid(rs.getLong("id"));
        result.setName(rs.getString("name"));
        result.setNameEng(rs.getString("name_eng"));
        result.setClassType(rs.getString("class"));
        result.setCity(rs.getString("city"));
        result.setStreet(rs.getString("street"));
        result.setHouseNum(rs.getString("house_num"));
        result.setClassInt(rs.getInt("class_int"));
        PGgeometry geom = (PGgeometry) rs.getObject("geom");
        result.setGeom(geom);

        return result;
    }

    private HazardAreaView parseFromResultView(ResultSet rs) throws SQLException {
        HazardAreaView result = new HazardAreaView();
        result.setId(rs.getLong("id"));
        result.setClassInt(rs.getInt("class_int"));
        result.setRadiusM(rs.getDouble("radius_m"));
        PGgeometry geom = (PGgeometry) rs.getObject("geom");
        result.setGeom(geom);
        return result;
    }

    private PopulationStatistic parseFromResultPopulation(ResultSet rs) throws SQLException {
        PopulationStatistic result = new PopulationStatistic();
        Integer male = rs.getInt("m_18_24cou") + rs.getInt("m_25_59cou") + rs.getInt("m_60_xxcou");
        Integer female = rs.getInt("f_18_24cou") + rs.getInt("f_25_59cou") + rs.getInt("f_60_xxcou");
        Integer children = rs.getInt("f_00_06cou") + rs.getInt("f_07_17cou") + rs.getInt("m_07_17cou") + rs.getInt("m_00_06cou");
        Double area = rs.getDouble("area_sq_km");
        String tmp = String.format("%.2g%n", area);
        result.setMale(male);
        result.setFemale(female);
        result.setArea(Double.parseDouble(tmp.replace(",", ".")));
        result.setChildren(children);
        result.setBuildings(rs.getInt("building_count"));
        result.setTotal(female + male + children);
        result.setBuses((female + male + children) / 45);
        return result;
    }

//    public static void main(String[] args) {
//        StatView obj = DaoFactory.getInstance().getHazardObjectsDao().calculateStatZones(1L);
//        System.out.println(obj.getGeom().toString());
//        String res = obj.getGeom().toString();
//        //
//        System.out.println(res.substring((res.indexOf("(")+2), res.lastIndexOf(")")-1).replace(",", "],[").replace(" ", ", "));
//    }
}


