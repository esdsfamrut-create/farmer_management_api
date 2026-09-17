package com.famrut.farmer_management_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class SubDistrictRequest {

    @NotBlank(message = "Sub-district code is required")
    @Size(max = 20)
    private String subDistrictCode;

    @NotBlank(message = "Sub-district name is required")
    @Size(max = 100)
    private String subDistrictName;

    @NotNull(message = "District id is required")
    private Long districtId;

    public String getSubDistrictCode() {
        return subDistrictCode;
    }

    public void setSubDistrictCode(String subDistrictCode) {
        this.subDistrictCode = subDistrictCode;
    }

    public String getSubDistrictName() {
        return subDistrictName;
    }

    public void setSubDistrictName(String subDistrictName) {
        this.subDistrictName = subDistrictName;
    }

    public Long getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Long districtId) {
        this.districtId = districtId;
    }
}