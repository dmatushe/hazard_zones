package com.intellias.mvp.hazard.model.dto;

import org.postgis.PGgeometry;

import java.util.Objects;

public class HazardAreaView {
    private Long id;
    private Integer classInt;
    private Double radiusM;
    private PGgeometry geom;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getClassInt() {
        return classInt;
    }

    public void setClassInt(Integer classInt) {
        this.classInt = classInt;
    }

    public Double getRadiusM() {
        return radiusM;
    }

    public void setRadiusM(Double radiusM) {
        this.radiusM = radiusM;
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
        HazardAreaView that = (HazardAreaView) o;
        return id.equals(that.id) &&
                Objects.equals(classInt, that.classInt) &&
                Objects.equals(radiusM, that.radiusM) &&
                geom.equals(that.geom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, classInt, radiusM, geom);
    }

    @Override
    public String toString() {
        return "HazardAreaView{" +
                "id=" + id +
                ", classInt=" + classInt +
                ", radiusM=" + radiusM +
                ", geom=" + geom +
                '}';
    }
}
