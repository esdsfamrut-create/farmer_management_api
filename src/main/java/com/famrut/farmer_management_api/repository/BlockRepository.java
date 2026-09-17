package com.famrut.farmer_management_api.repository;

import com.famrut.farmer_management_api.entity.Block;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BlockRepository
        extends JpaRepository<Block, Long> {

    boolean existsBySubDistrictIdAndBlockCode(
            Long subDistrictId,
            String blockCode
    );

    List<Block>
    findAllBySubDistrictIdAndActiveTrueOrderByBlockNameAsc(
            Long subDistrictId
    );
}