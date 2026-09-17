package com.famrut.farmer_management_api.controller;

import com.famrut.farmer_management_api.dto.SendOtpRequest;
import com.famrut.farmer_management_api.service.OtpService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.famrut.farmer_management_api.dto.VerifyOtpRequest;
import com.famrut.farmer_management_api.entity.Farmer;
import com.famrut.farmer_management_api.dto.FarmerRegistrationRequest;
import com.famrut.farmer_management_api.dto.FarmerRequest;
import com.famrut.farmer_management_api.dto.FarmerResponse;
import com.famrut.farmer_management_api.service.FarmerService;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import com.famrut.farmer_management_api.dto.AuthResponse;
import com.famrut.farmer_management_api.entity.Farmer;
import com.famrut.farmer_management_api.dto.RefreshTokenRequest;
import com.famrut.farmer_management_api.dto.TokenRefreshResponse;
import com.famrut.farmer_management_api.entity.Farmer;
import jakarta.validation.Valid;
import com.famrut.farmer_management_api.entity.RefreshToken;
import com.famrut.farmer_management_api.entity.FarmerStatus;
import com.famrut.farmer_management_api.service.RefreshTokenService;
import com.famrut.farmer_management_api.service.JwtService;
import com.famrut.farmer_management_api.dto.LogoutRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import com.famrut.farmer_management_api.dto.ApiResponse;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final OtpService otpService;
    private final FarmerService farmerService;
    private final RefreshTokenService refreshTokenService;
    private final JwtService jwtService;

    public AuthController(OtpService otpService, FarmerService farmerService, RefreshTokenService refreshTokenService, JwtService jwtService   ) {
        this.otpService = otpService;
        this.farmerService = farmerService;
        this.refreshTokenService = refreshTokenService;
        this.jwtService = jwtService;
    }

    @PostMapping("/send-otp")
    public ResponseEntity<ApiResponse<String>> sendOtp(
            @Valid @RequestBody SendOtpRequest request) {

        otpService.sendOtp(request);

        return ResponseEntity.ok(new ApiResponse<>(200, "OTP sent successfully"));
    }

    @PostMapping("/verify-otp")
        public ResponseEntity<ApiResponse<AuthResponse>> verifyOtp(
                @Valid @RequestBody VerifyOtpRequest request) {

        AuthResponse response = otpService.verifyOtp(request);

        return ResponseEntity.ok(new ApiResponse<>(200, "OTP verified successfully", response));
        }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<FarmerResponse>> registerFarmer(
        @Valid @RequestBody FarmerRegistrationRequest request) {

    VerifyOtpRequest otpRequest = new VerifyOtpRequest();
    otpRequest.setPhoneNumber(request.getPhoneNumber());

    return ResponseEntity.ok(new ApiResponse<>(200, "Farmer registered successfully", farmerService.createFarmer(new FarmerRequest())));                
    }

    @PostMapping("/refresh")
    public ResponseEntity<ApiResponse<TokenRefreshResponse>> refreshToken(
            @Valid @RequestBody RefreshTokenRequest request) {

        RefreshToken oldRefreshToken =
                refreshTokenService
                        .validateRefreshToken(
                                request.getRefreshToken()
                        );

        Farmer farmer =
                oldRefreshToken.getFarmer();

        if (farmer.getStatus() != FarmerStatus.ACTIVE) {
            throw new IllegalArgumentException(
                    "Farmer account is not active"
            );
        }

        String newAccessToken =
                jwtService.generateToken(farmer);

        RefreshToken newRefreshToken =
                refreshTokenService
                        .rotateRefreshToken(
                                oldRefreshToken
                        );

        return ResponseEntity.ok(new ApiResponse<>(200, "Token refreshed successfully", new TokenRefreshResponse(
                newAccessToken,
                newRefreshToken.getToken()
        )));
    }

    @PostMapping("/logout")
    public ResponseEntity<ApiResponse<Void>> logout(
            @Valid @RequestBody LogoutRequest request) {

        refreshTokenService.revokeByToken(
                request.getRefreshToken()
        );

        return ResponseEntity.ok(new ApiResponse<>(200, "Logged out successfully"));
    }

    @PostMapping("/logout-all")
        public ResponseEntity<ApiResponse<Void>> logoutAll(
                Authentication authentication) {

        Long farmerId =
                Long.valueOf(authentication.getName());

        refreshTokenService
                .revokeAllForFarmer(farmerId);

        return ResponseEntity.ok(new ApiResponse<>(200, "Logged out of all sessions successfully"));
        }
}