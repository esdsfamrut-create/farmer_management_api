package com.famrut.farmer_management_api.dto;

public class SubDistrictResponse {

    private Long id;
    private String subDistrictCode;
    private String subDistrictName;

    private Long districtId;
    private String districtName;

    private Long stateId;
    private String stateName;

    private boolean active;

    public SubDistrictResponse(
            Long id,
            String subDistrictCode,
            String subDistrictName,
            Long districtId,
            String districtName,
            Long stateId,
            String stateName,
            boolean active) {

        this.id = id;
        this.subDistrictCode = subDistrictCode;
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

    public String getSubDistrictCode() {
        return subDistrictCode;
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