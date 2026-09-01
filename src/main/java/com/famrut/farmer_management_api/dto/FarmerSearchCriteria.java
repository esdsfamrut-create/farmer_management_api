package com.famrut.farmer_management_api.dto;
import com.famrut.farmer_management_api.entity.FarmerStatus;
public class FarmerSearchCriteria {

    private String name;
    private String email;
    private String phoneNumber;
    private FarmerStatus status;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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

    public FarmerStatus getStatus() {
    return status;
    }
    public void setStatus(FarmerStatus status) {
        this.status = status;
    }
}