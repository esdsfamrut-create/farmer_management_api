package com.famrut.farmer_management_api.dto;

public class StateResponse {

    private Long id;
    private String stateCode;
    private String stateName;
    private boolean active;

    public StateResponse(
            Long id,
            String stateCode,
            String stateName,
            boolean active) {

        this.id = id;
        this.stateCode = stateCode;
        this.stateName = stateName;
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public String getStateCode() {
        return stateCode;
    }

    public String getStateName() {
        return stateName;
    }

    public boolean isActive() {
        return active;
    }
}