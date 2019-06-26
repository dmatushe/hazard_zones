package com.intellias.mvp.hazard.model.entity;

import java.util.Objects;

public class ImpactZone {
    private Long id;
    private String classType;
    private Integer radius_m;
    private Integer classInt;

    public Integer getClassInt() {
        return classInt;
    }

    public void setClassInt(Integer classInt) {
        this.classInt = classInt;
    }

    public String getClassType() {
        return classType;
    }

    public void setClassType(String classType) {
        this.classType = classType;
    }

    public Integer getRadius_m() {
        return radius_m;
    }

    public void setRadius_m(Integer radius_m) {
        this.radius_m = radius_m;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ImpactZone)) return false;
        ImpactZone that = (ImpactZone) o;
        return classType.equals(that.classType) &&
                radius_m.equals(that.radius_m);
    }

    @Override
    public int hashCode() {
        return Objects.hash(classType, radius_m);
    }

    @Override
    public String toString() {
        return "ImpactZone{" +
                "id=" + id +
                ", classType='" + classType + '\'' +
                ", radius_m=" + radius_m +
                ", classInt=" + classInt +
                '}';
    }
}


