package com.famrut.farmer_management_api.repository;

import com.famrut.farmer_management_api.entity.State;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StateRepository
        extends JpaRepository<State, Long> {

    Optional<State> findByStateCode(String stateCode);

    boolean existsByStateCode(String stateCode);

    List<State> findAllByActiveTrueOrderByStateNameAsc();
}