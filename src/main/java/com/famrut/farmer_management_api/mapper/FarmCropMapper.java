package com.famrut.farmer_management_api.mapper;

import org.springframework.stereotype.Component;

import com.famrut.farmer_management_api.dto.FarmCropResponse;
import com.famrut.farmer_management_api.entity.FarmCrop;

@Component
public class FarmCropMapper {

    public FarmCropResponse toResponse(FarmCrop farmCrop) {

        FarmCropResponse response = new FarmCropResponse();

        response.setId(farmCrop.getId());

        // Farm relationship
        if (farmCrop.getFarm() != null) {
            response.setFarmId(farmCrop.getFarm().getId());
            response.setFarmName(farmCrop.getFarm().getFarmName());
        }

        // Crop relationship
        if (farmCrop.getCrop() != null) {
            response.setCropId(farmCrop.getCrop().getId());
            response.setCropName(farmCrop.getCrop().getCropName());
            response.setCropVariety(farmCrop.getCrop().getCropVariety());
        }

        // remaining FarmCrop fields
        response.setCultivatedArea(farmCrop.getCultivatedArea());
        response.setSeason(farmCrop.getSeason());
        response.setSowingDate(farmCrop.getSowingDate());
        response.setAreaUnit(farmCrop.getAreaUnit());
        response.setStatus(farmCrop.getStatus().name());
        response.setActive(farmCrop.isActive());

        return response;
    }
}