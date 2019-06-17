package com.intellias.mvp.hazard.model.entity;

import org.postgis.Geometry;
import org.postgis.PGgeometry;

import java.util.Objects;

public class HazardObjects {
    private Long gid;
    private String name;
    private String classType;
    private String city;
    private String street;
    private String houseNum;
    private Integer classInt;
    private PGgeometry geom;

    public Long getGid() {
        return gid;
    }

    public void setGid(Long gid) {
        this.gid = gid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassType() {
        return classType;
    }

    public void setClassType(String classType) {
        this.classType = classType;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouseNum() {
        return houseNum;
    }

    public void setHouseNum(String houseNum) {
        this.houseNum = houseNum;
    }

    public Integer getClassInt() {
        return classInt;
    }

    public void setClassInt(Integer classInt) {
        this.classInt = classInt;
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
        if (!(o instanceof HazardObjects)) return false;
        HazardObjects objects = (HazardObjects) o;
        return gid.equals(objects.gid) &&
                Objects.equals(name, objects.name) &&
                geom.equals(objects.geom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gid, name, geom);
    }

    @Override
    public String toString() {
        return "HazardObjects{" +
                "gid=" + gid +
                ", name='" + name + '\'' +
                ", classType='" + classType + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", houseNum=" + houseNum +
                ", classInt=" + classInt +
                ", geom=" + geom +
                '}';
    }
}


