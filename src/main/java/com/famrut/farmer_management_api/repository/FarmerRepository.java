package com.famrut.farmer_management_api.repository;

import com.famrut.farmer_management_api.entity.Farmer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import java.util.Optional;

public interface FarmerRepository extends JpaRepository<Farmer, Long>,
        JpaSpecificationExecutor<Farmer> {
Optional<Farmer> findByPhoneNumber(String phoneNumber);
}