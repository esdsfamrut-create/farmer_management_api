package com.famrut.farmer_management_api.repository;

import com.famrut.farmer_management_api.entity.FarmCrop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FarmCropRepository
        extends JpaRepository<FarmCrop, Long> {

   List<FarmCrop> findAllByFarmIdAndFarmFarmerIdAndActiveTrueOrderByIdDesc(
            Long farmId,
            Long farmerId
    );
   
   Optional<FarmCrop> findByIdAndFarmFarmerIdAndActiveTrue(
            Long id,
            Long farmerId
    );

}