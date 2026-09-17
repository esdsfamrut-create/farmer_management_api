package com.famrut.farmer_management_api.dto;

public class VillageResponse {

    private Long id;
    private String villageCode;
    private String villageName;

    private Long blockId;
    private String blockName;

    private Long subDistrictId;
    private String subDistrictName;

    private Long districtId;
    private String districtName;

    private Long stateId;
    private String stateName;

    private boolean active;

    public VillageResponse(
            Long id,
            String villageCode,
            String villageName,
            Long blockId,
            String blockName,
            Long subDistrictId,
            String subDistrictName,
            Long districtId,
            String districtName,
            Long stateId,
            String stateName,
            boolean active) {

        this.id = id;
        this.villageCode = villageCode;
        this.villageName = villageName;
        this.blockId = blockId;
        this.blockName = blockName;
        this.subDistrictId = subDistrictId;
        this.subDistrictName = subDistrictName;
        this.districtId = districtId;
        this.districtName = districtName;
        this.stateId = stateId;
        this.stateName = stateName;
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public String getVillageCode() {
        return villageCode;
    }

    public String getVillageName() {
        return villageName;
    }

    public Long getBlockId() {
        return blockId;
    }

    public String getBlockName() {
        return blockName;
    }

    public Long getSubDistrictId() {
        return subDistrictId;
    }

    public String getSubDistrictName() {
        return subDistrictName;
    }

    public Long getDistrictId() {
        return districtId;
    }

    public String getDistrictName() {
        return districtName;
    }

    public Long getStateId() {
        return stateId;
    }

    public String getStateName() {
        return stateName;
    }

    public boolean isActive() {
        return active;
    }
}