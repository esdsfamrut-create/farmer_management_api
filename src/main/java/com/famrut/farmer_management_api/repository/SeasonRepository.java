package com.famrut.farmer_management_api.repository;

import com.famrut.farmer_management_api.entity.Season;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
/**
 * SeasonRepository
 */
public interface SeasonRepository extends JpaRepository<Season, Long>  {

    boolean existsByCodeIgnoreCase(String code);

    List<Season> findAllByActiveTrueOrderByNameAsc();
    Optional<Season> findById(Long id);
} 