package com.famrut.farmer_management_api.service;

import com.famrut.farmer_management_api.dto.FarmCropRequest;
import com.famrut.farmer_management_api.entity.Crop;
import com.famrut.farmer_management_api.entity.FarmCrop;
import com.famrut.farmer_management_api.entity.FarmCropStatus;
import com.famrut.farmer_management_api.mapper.FarmCropMapper;
import com.famrut.farmer_management_api.entity.Farm;
import org.springframework.stereotype.Service;
import com.famrut.farmer_management_api.dto.FarmCropResponse;
import com.famrut.farmer_management_api.dto.FarmCropStatusRequest;
import com.famrut.farmer_management_api.repository.FarmCropRepository;
import com.famrut.farmer_management_api.repository.FarmRepository;
import com.famrut.farmer_management_api.repository.CropRepository;

import java.util.List;

@Service
public class FarmCropService {

    private final FarmCropRepository farmCropRepository;
    private final FarmRepository farmRepository;
    private final CropRepository cropRepository;
    private final FarmCropMapper farmCropMapper;

    public FarmCropService(  FarmCropRepository farmCropRepository,
     FarmRepository farmRepository,
    CropRepository cropRepository,
     FarmCropMapper farmCropMapper){
        this.cropRepository=cropRepository;
        this.farmCropMapper=farmCropMapper;
        this.farmCropRepository=farmCropRepository;
        this.farmRepository=farmRepository;
     }
    
     public FarmCropResponse createFarmCrop(
        Long farmerId,
        FarmCropRequest request) {
        
                    Farm farm=farmRepository.findByIdAndFarmerId(request.getFarmId(),farmerId)
                    .orElseThrow(()-> new RuntimeException("Farm not found with id: " + request.getFarmId()));
                     Crop crop=cropRepository.findById(request.getCropId())
                    .orElseThrow(()-> new RuntimeException("Crop not found with id: " + request.getCropId()));
                    FarmCrop farmCrop=new FarmCrop();
                    farmCrop.setFarm(farm);
                    farmCrop.setCrop(crop);
                    farmCrop.setActive(true);
                    farmCrop.setAreaUnit(request.getAreaUnit());
                    farmCrop.setCultivatedArea(request.getCultivatedArea());
                    farmCrop.setSeason(request.getSeason());
                    farmCrop.setSowingDate(request.getSowingDate());
                    farmCrop.setStatus(FarmCropStatus.SOWN);
        FarmCrop savefarmCrop=farmCropRepository.save(farmCrop);

        return farmCropMapper.toResponse(savefarmCrop);

    }

    public FarmCropResponse getFarmCropById( Long farmCropId,
        Long farmerId){

FarmCrop farmCrop =
        farmCropRepository
                .findByIdAndFarmFarmerIdAndActiveTrue(
                        farmCropId,
                        farmerId
                )
                .orElseThrow(() ->
                        new RuntimeException(
                                "Farm crop not found with id: "
                                        + farmCropId
                        )
                );
        return farmCropMapper.toResponse(farmCrop);
    }

   public FarmCropResponse updateFarmCrop(  Long farmCropId,
        Long farmerId,
        FarmCropRequest request){
FarmCrop farmCrop =
        farmCropRepository
                .findByIdAndFarmFarmerIdAndActiveTrue(
                        farmCropId,
                        farmerId
                )
                .orElseThrow(() ->
                        new RuntimeException(
                                "Farm crop not found with id: "
                                        + farmCropId
                        )
                );        farmCrop.setAreaUnit(request.getAreaUnit());
        farmCrop.setSeason(request.getSeason());
        farmCrop.setSowingDate(request.getSowingDate());
        farmCrop.setCultivatedArea(request.getCultivatedArea());
                    Farm farm=farmRepository.findByIdAndFarmerId(request.getFarmId(),farmerId)
                    .orElseThrow(()-> new RuntimeException("Farm not found with id: " + request.getFarmId()));
                     Crop crop=cropRepository.findById(request.getCropId())
                    .orElseThrow(()-> new RuntimeException("Crop not found with id: " + request.getCropId()));
                
                    farmCrop.setFarm(farm);
                    farmCrop.setCrop(crop);
             FarmCrop reponse=farmCropRepository.save(farmCrop);
                    return farmCropMapper.toResponse(reponse);
   }

   public void deactivateFarmCrop( Long farmCropId,
        Long farmerId){
FarmCrop farmCrop =
        farmCropRepository
                .findByIdAndFarmFarmerIdAndActiveTrue(
                        farmCropId,
                        farmerId
                )
                .orElseThrow(() ->
                        new RuntimeException(
                                "Farm crop not found with id: "
                                        + farmCropId
                        )
                );        
         farmCrop.setActive(false);
             FarmCrop reponse=farmCropRepository.save(farmCrop);
   }

   public List<FarmCropResponse> getFarmCrops(
        Long farmId,
        Long farmerId) {

    return farmCropRepository
            .findAllByFarmIdAndFarmFarmerIdAndActiveTrueOrderByIdDesc(
                    farmId,
                    farmerId
            )
            .stream()
            .map(farmCropMapper::toResponse)
            .toList();
}

public FarmCropResponse updateStatusFarmCrop(  Long farmCropId,
        Long farmerId,
        FarmCropStatusRequest request){
FarmCrop farmCrop =
        farmCropRepository
                .findByIdAndFarmFarmerIdAndActiveTrue(
                        farmCropId,
                        farmerId
                )
                .orElseThrow(() ->
                        new RuntimeException(
                                "Farm crop not found with id: "
                                        + farmCropId
                        )
                );       
                 farmCrop.setStatus(request.getStatus());
       
             FarmCrop reponse=farmCropRepository.save(farmCrop);
                    return farmCropMapper.toResponse(reponse);
   }
}