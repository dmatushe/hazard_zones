package com.intellias.mvp.hazard.model.dto;

import org.postgis.PGgeometry;

import java.util.Objects;

public class StatView {
    private Long id;
    private int class_int;
    private double radius_m;
    private double area_sq_km;
    private int building_count;
    private int f_00_06cou;
    private int f_07_17cou;
    private int f_18_24cou;
    private int f_25_59cou;
    private int f_60_xxcou;
    private int m_00_06cou;
    private int m_07_17cou;
    private int m_18_24cou;
    private int m_25_59cou;
    private int m_60_xxcou;
    private int sum_population;
    private PGgeometry geom;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getClass_int() {
        return class_int;
    }

    public void setClass_int(int class_int) {
        this.class_int = class_int;
    }

    public double getRadius_m() {
        return radius_m;
    }

    public void setRadius_m(double radius_m) {
        this.radius_m = radius_m;
    }

    public double getArea_sq_km() {
        return area_sq_km;
    }

    public void setArea_sq_km(double area_sq_km) {
        this.area_sq_km = area_sq_km;
    }

    public int getBuilding_count() {
        return building_count;
    }

    public void setBuilding_count(int building_count) {
        this.building_count = building_count;
    }

    public int getF_00_06cou() {
        return f_00_06cou;
    }

    public void setF_00_06cou(int f_00_06cou) {
        this.f_00_06cou = f_00_06cou;
    }

    public int getF_07_17cou() {
        return f_07_17cou;
    }

    public void setF_07_17cou(int f_07_17cou) {
        this.f_07_17cou = f_07_17cou;
    }

    public int getF_18_24cou() {
        return f_18_24cou;
    }

    public void setF_18_24cou(int f_18_24cou) {
        this.f_18_24cou = f_18_24cou;
    }

    public int getF_25_59cou() {
        return f_25_59cou;
    }

    public void setF_25_59cou(int f_25_59cou) {
        this.f_25_59cou = f_25_59cou;
    }

    public int getF_60_xxcou() {
        return f_60_xxcou;
    }

    public void setF_60_xxcou(int f_60_xxcou) {
        this.f_60_xxcou = f_60_xxcou;
    }

    public int getM_00_06cou() {
        return m_00_06cou;
    }

    public void setM_00_06cou(int m_00_06cou) {
        this.m_00_06cou = m_00_06cou;
    }

    public int getM_07_17cou() {
        return m_07_17cou;
    }

    public void setM_07_17cou(int m_07_17cou) {
        this.m_07_17cou = m_07_17cou;
    }

    public int getM_18_24cou() {
        return m_18_24cou;
    }

    public void setM_18_24cou(int m_18_24cou) {
        this.m_18_24cou = m_18_24cou;
    }

    public int getM_25_59cou() {
        return m_25_59cou;
    }

    public void setM_25_59cou(int m_25_59cou) {
        this.m_25_59cou = m_25_59cou;
    }

    public int getM_60_xxcou() {
        return m_60_xxcou;
    }

    public void setM_60_xxcou(int m_60_xxcou) {
        this.m_60_xxcou = m_60_xxcou;
    }

    public int getSum_population() {
        return sum_population;
    }

    public void setSum_population(int sum_population) {
        this.sum_population = sum_population;
    }

    public PGgeometry getGeom() {
        return geom;
    }

    public void setGeom(PGgeometry geom) {
        this.geom = geom;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StatView statView = (StatView) o;
        return id.equals(statView.id) &&
                geom.equals(statView.geom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, geom);
    }

    @Override
    public String toString() {
        return "StatView{" +
                "id=" + id +
                ", class_int=" + class_int +
                ", radius_m=" + radius_m +
                ", area_sq_km=" + area_sq_km +
                ", building_count=" + building_count +
                ", f_00_06cou=" + f_00_06cou +
                ", f_07_17cou=" + f_07_17cou +
                ", f_18_24cou=" + f_18_24cou +
                ", f_25_59cou=" + f_25_59cou +
                ", f_60_xxcou=" + f_60_xxcou +
                ", m_00_06cou=" + m_00_06cou +
                ", m_07_17cou=" + m_07_17cou +
                ", m_18_24cou=" + m_18_24cou +
                ", m_25_59cou=" + m_25_59cou +
                ", m_60_xxcou=" + m_60_xxcou +
                ", sum_population=" + sum_population +
                ", geom=" + geom +
                '}';
    }
}
