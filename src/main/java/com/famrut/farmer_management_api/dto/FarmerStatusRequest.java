package com.famrut.farmer_management_api.dto;

import com.famrut.farmer_management_api.entity.FarmerStatus;
import jakarta.validation.constraints.NotNull;

public class FarmerStatusRequest {

    @NotNull(message = "Status is required")
    private FarmerStatus status;

    public FarmerStatus getStatus() {
        return status;
    }

    public void setStatus(FarmerStatus status) {
        this.status = status;
    }
}