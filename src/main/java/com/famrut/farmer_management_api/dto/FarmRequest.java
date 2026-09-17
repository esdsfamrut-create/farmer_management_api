package com.famrut.farmer_management_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;    

public class FarmRequest {

    @NotBlank(message = "Farm name is required")
    @Size(max = 150, message = "Farm name must not exceed 150 characters")
    private String farmName;

    @NotNull(message = "Area is required")
    private BigDecimal area;

    @NotBlank(message = "Area unit is required")
    @Size(max = 20, message = "Area unit must not exceed 20 characters")
    private String areaUnit;

    private String polygon;

    private boolean active;

    // Getters and Setters

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

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}   