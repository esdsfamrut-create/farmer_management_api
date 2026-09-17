package com.famrut.farmer_management_api.dto;
import com.famrut.farmer_management_api.entity.FarmerStatus;
import com.famrut.farmer_management_api.entity.FarmerRole;

public class FarmerResponse {

    private Long id;
    private String name;
    private String email;
    private String phoneNumber;
    private FarmerStatus status;
    private Long villageId;
    private String villageName;
    private Long blockId;
    private String blockName;
    private Long subDistrictId;
    private String subDistrictName;
    private Long districtId;
    private String districtName;
    private Long stateId;
    private String stateName;
    private FarmerRole role;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public FarmerStatus getStatus() {
        return status;
    }

    public void setStatus(FarmerStatus status) {
        this.status = status;
    }

    public Long getVillageId() {
        return villageId;
    }

    public void setVillageId(Long villageId) {
        this.villageId = villageId;
    }

    public String getVillageName() {
        return villageName;
    }

    public void setVillageName(String villageName) {
        this.villageName = villageName;
    }

    public Long getSubDistrictId() {
        return subDistrictId;
    }

    public void setSubDistrictId(Long subDistrictId) {
        this.subDistrictId = subDistrictId;
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

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public Long getStateId() {
        return stateId;
    }

    public void setStateId(Long stateId) {
        this.stateId = stateId;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public Long getBlockId() {
        return blockId;
    }

    public void setBlockId(Long blockId) {
        this.blockId = blockId;
    }

    public String getBlockName() {
        return blockName;
    }

    public void setBlockName(String blockName) {
        this.blockName = blockName;
    }

    public FarmerRole getRole() {
        return role;
    }

    public void setRole(FarmerRole role) {
        this.role = role;
    }   
}