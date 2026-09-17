package com.famrut.farmer_management_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class DistrictRequest {

    @NotBlank(message = "District code is required")
    @Size(max = 20)
    private String districtCode;

    @NotBlank(message = "District name is required")
    @Size(max = 100)
    private String districtName;

    @NotNull(message = "State id is required")
    private Long stateId;

    public String getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public Long getStateId() {
        return stateId;
    }

    public void setStateId(Long stateId) {
        this.stateId = stateId;
    }
}