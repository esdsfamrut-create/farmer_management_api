package com.famrut.farmer_management_api.repository;

import com.famrut.farmer_management_api.entity.District;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DistrictRepository
        extends JpaRepository<District, Long> {

    boolean existsByStateIdAndDistrictCode(
            Long stateId,
            String districtCode
    );

    List<District>
    findAllByStateIdAndActiveTrueOrderByDistrictNameAsc(
            Long stateId
    );
}