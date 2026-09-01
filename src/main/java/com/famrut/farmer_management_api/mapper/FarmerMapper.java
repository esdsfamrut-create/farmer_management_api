package com.famrut.farmer_management_api.mapper;

import com.famrut.farmer_management_api.dto.FarmerRequest;
import com.famrut.farmer_management_api.dto.FarmerResponse;
import com.famrut.farmer_management_api.entity.Farmer;
import org.springframework.stereotype.Component;
import com.famrut.farmer_management_api.entity.FarmerStatus;

@Component
public class FarmerMapper {

    public Farmer toEntity(FarmerRequest request) {

        Farmer farmer = new Farmer();

        farmer.setName(request.getName());
        farmer.setEmail(request.getEmail());
        farmer.setPhoneNumber(request.getPhoneNumber());
        if (farmer.getStatus() == null) {
            farmer.setStatus(FarmerStatus.ACTIVE);
        }
        return farmer;
    }

    public FarmerResponse toResponse(Farmer farmer) {

        FarmerResponse response = new FarmerResponse();

        response.setId(farmer.getId());
        response.setName(farmer.getName());
        response.setEmail(farmer.getEmail());
        response.setPhoneNumber(farmer.getPhoneNumber());
        response.setStatus(farmer.getStatus());
        return response;
    }

    public void updateEntity(FarmerRequest request, Farmer farmer) {

    farmer.setName(request.getName());
    farmer.setEmail(request.getEmail());
    farmer.setPhoneNumber(request.getPhoneNumber());
    }
}