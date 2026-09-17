package com.famrut.farmer_management_api.mapper;
import org.springframework.stereotype.Component;

import com.famrut.farmer_management_api.dto.CropRequest;
import com.famrut.farmer_management_api.dto.CropResponse;
import com.famrut.farmer_management_api.entity.Crop;


@Component
public class CropMapper {

    public CropResponse toResponse(Crop crop) {

        CropResponse response = new CropResponse();

        // map all fields here
        response.setId(crop.getId());
        response.setCropName(crop.getCropName());
        response.setCropVariety(crop.getCropVariety());
        response.setNitrogen(crop.getNitrogen());
        response.setPhosphorus(crop.getPhosphorus());
        response.setPotassium(crop.getPotassium());
        response.setSodium(crop.getSodium());
        response.setSoilType(crop.getSoilType());
        response.setActive(crop.isActive());

        return response;
    }

    public Crop toEntity(CropRequest response) {
        Crop crop = new Crop();

        // map all fields here
      //  crop.setId(response.getId());
        crop.setCropName(response.getCropName());
        crop.setCropVariety(response.getCropVariety());
        crop.setNitrogen(response.getNitrogen());
        crop.setPhosphorus(response.getPhosphorus());
        crop.setPotassium(response.getPotassium());
        crop.setSodium(response.getSodium());
        crop.setSoilType(response.getSoilType());
        crop.setActive(true);

        return crop;
    }
}