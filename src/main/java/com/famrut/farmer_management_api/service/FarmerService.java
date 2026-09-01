package com.famrut.farmer_management_api.service;
import com.famrut.farmer_management_api.repository.FarmerRepository;
import org.springframework.stereotype.Service;
import com.famrut.farmer_management_api.entity.Farmer;
import java.util.List;
import com.famrut.farmer_management_api.exception.FarmerNotFoundException;
import com.famrut.farmer_management_api.dto.FarmerRequest;
import com.famrut.farmer_management_api.dto.FarmerResponse;
import com.famrut.farmer_management_api.mapper.FarmerMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.PageImpl;
import com.famrut.farmer_management_api.entity.Farmer;
import com.famrut.farmer_management_api.specification.FarmerSpecification;
import org.springframework.data.jpa.domain.Specification;
import com.famrut.farmer_management_api.dto.FarmerSearchCriteria;
import com.famrut.farmer_management_api.entity.FarmerStatus;
import org.springframework.transaction.annotation.Transactional;


@Service
public class FarmerService {

    private final FarmerRepository farmerRepository;
    private final FarmerMapper farmerMapper;

    public FarmerService(
            FarmerRepository farmerRepository,
            FarmerMapper farmerMapper) {

        this.farmerRepository = farmerRepository;
        this.farmerMapper = farmerMapper;
    }

    // private final FarmerMapper farmerMapper;

    //     public FarmerService(FarmerMapper farmerMapper) {
    //         this.farmerMapper = farmerMapper;
    //     }

    public FarmerResponse createFarmer(FarmerRequest request) {

    Farmer farmer = farmerMapper.toEntity(request);

    if (farmer.getStatus() == null) {
        farmer.setStatus(FarmerStatus.ACTIVE);
    }

    Farmer savedFarmer = farmerRepository.save(farmer);

    return farmerMapper.toResponse(savedFarmer);
}

   public Page<FarmerResponse> getAllFarmers(Pageable pageable) {

    return farmerRepository.findAll(pageable)
            .map(farmerMapper::toResponse);
    }

public Farmer getFarmerById(Long id) {
    return farmerRepository.findById(id).orElseThrow(() -> new FarmerNotFoundException(id));
}

   public FarmerResponse updateFarmer(
        Long id,
        FarmerRequest request) {

    return farmerRepository.findById(id)
            .map(farmer -> {

                farmerMapper.updateEntity(request, farmer);

                Farmer updatedFarmer =
                        farmerRepository.save(farmer);

                return farmerMapper.toResponse(updatedFarmer);
            })
            .orElseThrow(() ->
                    new FarmerNotFoundException(id));
}

    public void deleteFarmer(Long id) {
        Farmer farmer = farmerRepository.findById(id).orElseThrow(() -> new FarmerNotFoundException(id));
        farmerRepository.delete(farmer);
    }

    // public Page<FarmerResponse> searchFarmers(
    //     String name,
    //     Pageable pageable) {

    // return farmerRepository
    //         .findByNameContainingIgnoreCase(name, pageable)
    //         .map(farmerMapper::toResponse);
    // }

    // public Page<FarmerResponse> searchFarmersByEmail(
    //     String email,
    //     Pageable pageable) {

    // return farmerRepository
    //         .findByEmailContainingIgnoreCase(email, pageable)
    //         .map(farmerMapper::toResponse);
    // }

    public Page<FarmerResponse> searchFarmers(
        FarmerSearchCriteria criteria,
        Pageable pageable){

    Specification<Farmer> specification = null;

    if (criteria.getName() != null && !criteria.getName().isBlank()) {
       specification = FarmerSpecification.hasName(criteria.getName());
    }

    if (criteria.getEmail() != null && !criteria.getEmail().isBlank()) {
    specification = specification == null
            ? FarmerSpecification.hasEmail(criteria.getEmail())
            : specification.and(
                    FarmerSpecification.hasEmail(criteria.getEmail())
            );
    }
    if (criteria.getPhoneNumber() != null
        && !criteria.getPhoneNumber().isBlank()) {

    specification = specification == null
            ? FarmerSpecification.hasPhoneNumber(criteria.getPhoneNumber())
            : specification.and(
                    FarmerSpecification.hasPhoneNumber(
                            criteria.getPhoneNumber()
                    )
            );
        }

        if (criteria.getStatus() != null) {

    specification = specification == null
            ? FarmerSpecification.hasStatus(criteria.getStatus())
            : specification.and(
                    FarmerSpecification.hasStatus(criteria.getStatus())
            );
    }

    if (specification == null) {
        return farmerRepository
                .findAll(pageable)
                .map(farmerMapper::toResponse);
    }

    return farmerRepository
            .findAll(specification, pageable)
            .map(farmerMapper::toResponse);
}

public Farmer createFarmer(Farmer farmer) {

    if (farmer.getStatus() == null) {
        farmer.setStatus(FarmerStatus.ACTIVE);
    }

    return farmerRepository.save(farmer);
}
}