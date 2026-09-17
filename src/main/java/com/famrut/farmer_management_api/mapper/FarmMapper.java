package com.famrut.farmer_management_api.mapper;

import com.famrut.farmer_management_api.dto.FarmResponse;
import com.famrut.farmer_management_api.entity.Farm;
import org.springframework.stereotype.Component;

@Component
public class FarmMapper {

    public FarmResponse toFarmResponse(Farm farm) {

        FarmResponse response = new FarmResponse();

        response.setId(farm.getId());
        response.setFarmName(farm.getFarmName());
        response.setArea(farm.getArea());
        response.setAreaUnit(farm.getAreaUnit());
        response.setPolygon(farm.getPolygon());
        response.setActive(farm.isActive());

        if (farm.getFarmer() != null) {
            response.setFarmerId(farm.getFarmer().getId());
            response.setFarmerName(farm.getFarmer().getName());
        }

        return response;
    }
}