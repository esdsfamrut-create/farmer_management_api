package com.famrut.farmer_management_api.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;


@Entity
@Table(name = "farms")
public class Farm {

    // PRACTICE 1:
    // id
    // Long
    // primary key
    // auto increment
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    // PRACTICE 2:
    // farmName
    // String
    // database column farm_name
    // required
    // max length 150
    @Column(name = "farm_name", nullable = false, length = 150)
    private String farmName;


    // PRACTICE 3:
    // area
    // BigDecimal
    // required
    @Column(nullable = false)
    private BigDecimal area;


    // PRACTICE 4:
    // areaUnit
    // String
    // column area_unit
    // required
    // max length 20
    @Column(name = "area_unit", nullable = false, length = 20)
    private String areaUnit;


    @Column(name = "polygon", columnDefinition = "TEXT")
    private String polygon;


    // Farmer relationship
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "farmer_id", nullable = false)
    private Farmer farmer;

    @Column(nullable = false)
    private boolean active = true;


    // Getters and Setters
    public Long getId() {
        return id;
    }

    public String getFarmName() {
        return farmName;
    }

    public void setFarmName(String farmName) {
        this.farmName = farmName;
    }

    public BigDecimal getArea() {
        return area;
    }

    public void setArea(BigDecimal area) {
        this.area = area;
    }

    public String getAreaUnit() {
        return areaUnit;
    }

    public void setAreaUnit(String areaUnit) {
        this.areaUnit = areaUnit;
    }

    public String getPolygon() {
        return polygon;
    }

    public void setPolygon(String polygon) {
        this.polygon = polygon;
    }

    public Farmer getFarmer() {
        return farmer;
    }

    public void setFarmer(Farmer farmer) {
        this.farmer = farmer;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}