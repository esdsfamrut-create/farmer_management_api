package com.famrut.farmer_management_api.repository;

import com.famrut.farmer_management_api.entity.Farm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.List;

/**
 * FarmRepository
 */
@Repository
public interface FarmRepository extends JpaRepository<Farm, Long> {

    public List<Farm> findAllByFarmerIdAndActiveTrueOrderByFarmNameAsc(Long farmerId);

    Optional<Farm> findByIdAndFarmerId(Long id, Long farmerId);

}

    
