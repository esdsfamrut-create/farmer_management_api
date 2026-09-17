package com.famrut.farmer_management_api.dto;
import jakarta.validation.constraints.NotNull;

public class CropRequest {
    @NotNull(message = "Crop name is required")
    private String cropName;
    @NotNull(message = "Crop variety is required")
    private String cropVariety;
    private Double nitrogen;
    private Double phosphorus;
    private Double potassium;   
    private Double sodium;
    private String soilType;
 
    public String getCropName() {
        return cropName;
    }   

    public void setCropName(String cropName) {
        this.cropName = cropName;
    }

    public String getCropVariety() {
        return cropVariety;
    }

    public void setCropVariety(String cropVariety) {
        this.cropVariety = cropVariety;
    }

    public Double getNitrogen() {
        return nitrogen;
    }

    public void setNitrogen(Double nitrogen) {
        this.nitrogen = nitrogen;
    }

    public Double getPhosphorus() {
        return phosphorus;
    }

    public void setPhosphorus(Double phosphorus) {
        this.phosphorus = phosphorus;
    }

    public Double getPotassium() {
        return potassium;
    }

    public void setPotassium(Double potassium) {
        this.potassium = potassium;
    }

    public Double getSodium() {
        return sodium;
    }


    public void setSodium(Double sodium) {
        this.sodium = sodium;
    }

    public String getSoilType() {
        return soilType;
    }

    public void setSoilType(String soilType) {
        this.soilType = soilType;
    }

    

}