package com.famrut.farmer_management_api.repository;

import com.famrut.farmer_management_api.entity.Village;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.List;

public interface VillageRepository
        extends JpaRepository<Village, Long> {

    boolean existsByBlockIdAndVillageCode(
            Long blockId,
            String villageCode
    );

    List<Village>
    findAllByBlockIdAndActiveTrueOrderByVillageNameAsc(
            Long blockId
    );

    Optional<Village> findById(Long id);
}