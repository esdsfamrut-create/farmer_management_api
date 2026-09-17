package com.famrut.farmer_management_api.dto;

import com.famrut.farmer_management_api.entity.FarmCropStatus;
import jakarta.validation.constraints.NotNull;

public class FarmCropStatusRequest {

    @NotNull(message = "Status is required")
    private FarmCropStatus status;

    public FarmCropStatus getStatus() {
        return status;
    }

    public void setStatus(FarmCropStatus status) {
        this.status = status;
    }
}