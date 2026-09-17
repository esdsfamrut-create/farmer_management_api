package com.famrut.farmer_management_api.dto;

public class DistrictResponse {

    private Long id;
    private String districtCode;
    private String districtName;
    private Long stateId;
    private String stateName;
    private boolean active;

    public DistrictResponse(
            Long id,
            String districtCode,
            String districtName,
            Long stateId,
            String stateName,
            boolean active) {

        this.id = id;
        this.districtCode = districtCode;
        this.districtName = districtName;
        this.stateId = stateId;
        this.stateName = stateName;
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public String getDistrictCode() {
        return districtCode;
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