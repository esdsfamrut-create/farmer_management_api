package com.famrut.farmer_management_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class BlockRequest {

    @NotBlank(message = "Block code is required")
    @Size(max = 20)
    private String blockCode;

    @NotBlank(message = "Block name is required")
    @Size(max = 100)
    private String blockName;

    @NotNull(message = "Sub-district id is required")
    private Long subDistrictId;

    public String getBlockCode() {
        return blockCode;
    }

    public void setBlockCode(String blockCode) {
        this.blockCode = blockCode;
    }

    public String getBlockName() {
        return blockName;
    }

    public void setBlockName(String blockName) {
        this.blockName = blockName;
    }

    public Long getSubDistrictId() {
        return subDistrictId;
    }

    public void setSubDistrictId(Long subDistrictId) {
        this.subDistrictId = subDistrictId;
    }
}