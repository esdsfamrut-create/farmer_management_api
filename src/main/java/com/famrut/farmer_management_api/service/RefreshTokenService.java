package com.famrut.farmer_management_api.service;

import com.famrut.farmer_management_api.entity.Farmer;
import com.famrut.farmer_management_api.entity.RefreshToken;
import com.famrut.farmer_management_api.repository.RefreshTokenRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;
import com.famrut.farmer_management_api.entity.FarmerStatus;
import com.famrut.farmer_management_api.service.JwtService;
import java.util.List;

@Service
public class RefreshTokenService {

    private static final long REFRESH_TOKEN_DAYS = 7;

    private final RefreshTokenRepository refreshTokenRepository;
        private final JwtService jwtService;

    public RefreshTokenService(
            RefreshTokenRepository refreshTokenRepository,
                        JwtService jwtService) {

        this.refreshTokenRepository = refreshTokenRepository;
        this.jwtService = jwtService;
    }

    public RefreshToken createRefreshToken(Farmer farmer) {

        RefreshToken refreshToken = new RefreshToken();

        refreshToken.setToken(
                UUID.randomUUID().toString()
        );

        refreshToken.setFarmer(farmer);

        refreshToken.setCreatedAt(
                LocalDateTime.now()
        );

        refreshToken.setExpiresAt(
                LocalDateTime.now()
                        .plusDays(REFRESH_TOKEN_DAYS)
        );

        refreshToken.setRevoked(false);

        return refreshTokenRepository.save(
                refreshToken
        );
    }

    public RefreshToken findByToken(String token) {

    return refreshTokenRepository
            .findByToken(token)
            .orElseThrow(() ->
                    new IllegalArgumentException(
                            "Invalid refresh token"
                    )
            );
}

            public void revokeToken(
                RefreshToken refreshToken) {

            refreshToken.setRevoked(true);

            refreshTokenRepository.save(
                    refreshToken
            );
        }

    public RefreshToken rotateRefreshToken(
            RefreshToken oldRefreshToken) {

        revokeToken(oldRefreshToken);

        return createRefreshToken(
                oldRefreshToken.getFarmer()
        );
    }

    public RefreshToken validateRefreshToken(
        String token) {

    RefreshToken refreshToken =
            findByToken(token);

    if (refreshToken.isRevoked()) {
        throw new IllegalArgumentException(
                "Refresh token has been revoked"
        );
    }

    if (refreshToken.getExpiresAt()
            .isBefore(LocalDateTime.now())) {

        throw new IllegalArgumentException(
                "Refresh token has expired"
        );
    }

    return refreshToken;
}

public Farmer getFarmerFromValidRefreshToken(
        String token) {

    RefreshToken refreshToken =
            validateRefreshToken(token);

    Farmer farmer = refreshToken.getFarmer();

    if (farmer.getStatus() != FarmerStatus.ACTIVE) {
        throw new IllegalArgumentException(
                "Farmer account is not active"
        );
    }

    return farmer;
}

    public void revokeByToken(String token) {

        RefreshToken refreshToken =
                validateRefreshToken(token);

        revokeToken(refreshToken);
    }
    public void revokeAllForFarmer(Long farmerId) {

    List<RefreshToken> tokens =
                refreshTokenRepository
                        .findAllByFarmerIdAndRevokedFalse(farmerId);

        for (RefreshToken token : tokens) {
                token.setRevoked(true);
        }

        refreshTokenRepository.saveAll(tokens);
        }
}