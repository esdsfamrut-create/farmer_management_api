package com.famrut.farmer_management_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class VillageRequest {

    @NotBlank(message = "Village code is required")
    @Size(max = 20)
    private String villageCode;

    @NotBlank(message = "Village name is required")
    @Size(max = 150)
    private String villageName;

    @NotNull(message = "Block id is required")
    private Long blockId;

    public String getVillageCode() {
        return villageCode;
    }

    public void setVillageCode(String villageCode) {
        this.villageCode = villageCode;
    }

    public String getVillageName() {
        return villageName;
    }

    public void setVillageName(String villageName) {
        this.villageName = villageName;
    }

    public Long getBlockId() {
        return blockId;
    }

    public void setBlockId(Long blockId) {
        this.blockId = blockId;
    }
}