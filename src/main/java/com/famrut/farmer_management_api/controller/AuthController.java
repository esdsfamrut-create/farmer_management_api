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

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final OtpService otpService;
    private final FarmerService farmerService;

    public AuthController(OtpService otpService, FarmerService farmerService) {
        this.otpService = otpService;
        this.farmerService = farmerService;
    }

    @PostMapping("/send-otp")
    public ResponseEntity<String> sendOtp(
            @Valid @RequestBody SendOtpRequest request) {

        otpService.sendOtp(request);

        return ResponseEntity.ok("OTP sent successfully");
    }

    @PostMapping("/verify-otp")
public ResponseEntity<AuthResponse> verifyOtp(
        @Valid @RequestBody VerifyOtpRequest request) {

    AuthResponse response = otpService.verifyOtp(request);

    return ResponseEntity.ok(response);
}

    @PostMapping("/register")
    public ResponseEntity<FarmerResponse> registerFarmer(
        @Valid @RequestBody FarmerRegistrationRequest request) {

    VerifyOtpRequest otpRequest = new VerifyOtpRequest();
    otpRequest.setPhoneNumber(request.getPhoneNumber());

    return ResponseEntity.ok(
            farmerService.createFarmer(
                    new FarmerRequest()
            )
    );
    }
}