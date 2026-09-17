package com.famrut.farmer_management_api.service;
import com.famrut.farmer_management_api.dto.CropResponse;
import com.famrut.farmer_management_api.entity.Crop;
import com.famrut.farmer_management_api.mapper.CropMapper;
import com.famrut.farmer_management_api.repository.CropRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.Optional;
import com.famrut.farmer_management_api.exception.CropNotFoundException;
import com.famrut.farmer_management_api.dto.CropRequest;


@Service
public class CropService {

    private final CropRepository cropRepository;
    private final CropMapper cropMapper;


    public CropService(CropRepository cropRepository, CropMapper cropMapper) {
        this.cropRepository = cropRepository;
        this.cropMapper = cropMapper;
    }

    public List<CropResponse> getAllActiveCrops() {
        List<Crop> crops = cropRepository.findAllByActiveTrueOrderByCropNameAsc();
        return crops.stream()
                .map(cropMapper::toResponse)
                .toList();
    }

    public CropResponse getCropById(Long id) {
        Crop crop = cropRepository.findById(id)
                .orElseThrow(() -> new CropNotFoundException("Crop not found with id: " + id));
        return cropMapper.toResponse(crop);
    }

    public CropResponse createCrop(CropRequest request) {
        Crop crop = cropMapper.toEntity(request);
        if (cropRepository.existsByCropNameAndCropVariety(crop.getCropName(), crop.getCropVariety())) {
            throw new IllegalArgumentException("Crop with the same name and variety already exists.");
        }
        Crop savedCrop = cropRepository.save(crop);
        return cropMapper.toResponse(savedCrop);
    }      
    
    public CropResponse updateCrop(Long id, CropRequest request) {
        Crop existingCrop = cropRepository.findById(id)
                .orElseThrow(() -> new CropNotFoundException("Crop not found with id: " + id));
        Crop updatedCrop = cropMapper.toEntity(request);

        if (!existingCrop.getCropName().equals(updatedCrop.getCropName()) ||
            !existingCrop.getCropVariety().equals(updatedCrop.getCropVariety())) {
            if (cropRepository.existsByCropNameAndCropVariety(updatedCrop.getCropName(), updatedCrop.getCropVariety())) {
                throw new IllegalArgumentException("Another crop with the same name and variety already exists.");
            }
        }

        existingCrop.setCropName(updatedCrop.getCropName());
        existingCrop.setCropVariety(updatedCrop.getCropVariety());
        existingCrop.setNitrogen(updatedCrop.getNitrogen());
        existingCrop.setPhosphorus(updatedCrop.getPhosphorus());
        existingCrop.setPotassium(updatedCrop.getPotassium());
        existingCrop.setSodium(updatedCrop.getSodium());
        existingCrop.setSoilType(updatedCrop.getSoilType());
        existingCrop.setActive(updatedCrop.isActive());

        Crop savedCrop = cropRepository.save(existingCrop);
        return cropMapper.toResponse(savedCrop);
    }

    public void deactivateCrop(Long id) {
        Crop crop = cropRepository.findById(id)
                .orElseThrow(() -> new CropNotFoundException("Crop not found with id: " + id));
        crop.setActive(false);
        cropRepository.save(crop);
    }

}