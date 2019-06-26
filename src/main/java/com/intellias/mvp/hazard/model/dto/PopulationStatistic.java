package com.intellias.mvp.hazard.model.dto;

import java.util.Objects;

public class PopulationStatistic {
    private Integer female;
    private Integer male;
    private Integer children;
    private Integer buildings;
    private Double area;
    private Integer total;
    private Integer buses;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getBuses() {
        return buses;
    }

    public void setBuses(Integer buses) {
        this.buses = buses;
    }

    public Integer getBuildings() {
        return buildings;
    }

    public void setBuildings(Integer buildings) {
        this.buildings = buildings;
    }

    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
    }

    public Integer getFemale() {
        return female;
    }

    public void setFemale(Integer female) {
        this.female = female;
    }

    public Integer getMale() {
        return male;
    }

    public void setMale(Integer male) {
        this.male = male;
    }

    public Integer getChildren() {
        return children;
    }

    public void setChildren(Integer children) {
        this.children = children;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PopulationStatistic that = (PopulationStatistic) o;
        return female.equals(that.female) &&
                male.equals(that.male) &&
                children.equals(that.children);
    }

    @Override
    public int hashCode() {
        return Objects.hash(female, male, children);
    }

    @Override
    public String toString() {
        return "PopulationStatistic{" +
                "female=" + female +
                ", male=" + male +
                ", children=" + children +
                ", buildings=" + buildings +
                ", area=" + area +
                '}';
    }
}
