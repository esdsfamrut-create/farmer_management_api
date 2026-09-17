package com.famrut.farmer_management_api.mapper;
import org.springframework.stereotype.Component;
import com.famrut.farmer_management_api.dto.FarmerRequest;
import com.famrut.farmer_management_api.dto.FarmerResponse;
import com.famrut.farmer_management_api.entity.Farmer;
import com.famrut.farmer_management_api.entity.FarmerStatus;
import com.famrut.farmer_management_api.entity.FarmerRole;


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

        if (farmer.getRole() == null) {
            farmer.setRole(FarmerRole.FARMER);
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
        response.setRole(farmer.getRole());
        if (farmer.getVillage() != null) {

            var village = farmer.getVillage();
            var block = village.getBlock();
            var subDistrict = block.getSubDistrict();
            var district = subDistrict.getDistrict();
            var state = district.getState();

            response.setVillageId(village.getId());
            response.setVillageName(village.getVillageName());

            response.setBlockId(block.getId());
            response.setBlockName(block.getBlockName());

            response.setSubDistrictId(subDistrict.getId());
            response.setSubDistrictName(subDistrict.getSubDistrictName());

            response.setDistrictId(district.getId());
            response.setDistrictName(district.getDistrictName());

            response.setStateId(state.getId());
            response.setStateName(state.getStateName());
        }

        return response;
    }

    public void updateEntity(
            FarmerRequest request,
            Farmer farmer) {

        farmer.setName(request.getName());
        farmer.setEmail(request.getEmail());
        farmer.setPhoneNumber(request.getPhoneNumber());
    }
}