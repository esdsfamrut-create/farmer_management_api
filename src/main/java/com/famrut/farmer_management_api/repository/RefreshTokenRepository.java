package com.famrut.farmer_management_api.repository;

import com.famrut.farmer_management_api.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.List;

public interface RefreshTokenRepository
        extends JpaRepository<RefreshToken, Long> {

    Optional<RefreshToken> findByToken(String token);
    List<RefreshToken> findAllByFarmerIdAndRevokedFalse(Long farmerId);
}