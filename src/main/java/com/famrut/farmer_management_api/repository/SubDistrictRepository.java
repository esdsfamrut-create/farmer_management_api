package com.famrut.farmer_management_api.repository;

import com.famrut.farmer_management_api.entity.SubDistrict;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubDistrictRepository
        extends JpaRepository<SubDistrict, Long> {

    boolean existsByDistrictIdAndSubDistrictCode(
            Long districtId,
            String subDistrictCode
    );

    List<SubDistrict>
    findAllByDistrictIdAndActiveTrueOrderBySubDistrictNameAsc(
            Long districtId
    );
}