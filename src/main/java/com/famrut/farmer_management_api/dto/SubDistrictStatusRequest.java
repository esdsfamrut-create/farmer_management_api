package com.famrut.farmer_management_api.dto;

import jakarta.validation.constraints.NotNull;

public class SubDistrictStatusRequest {

    @NotNull(message = "Active status is required")
    private Boolean active;

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}