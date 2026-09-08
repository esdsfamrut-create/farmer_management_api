package com.famrut.farmer_management_api.service;

import com.famrut.farmer_management_api.dto.SendOtpRequest;
import com.famrut.farmer_management_api.entity.OtpVerification;
import com.famrut.farmer_management_api.repository.OtpVerificationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.concurrent.ThreadLocalRandom;
import com.famrut.farmer_management_api.dto.VerifyOtpRequest;
import com.famrut.farmer_management_api.repository.FarmerRepository;
import com.famrut.farmer_management_api.entity.Farmer;
import com.famrut.farmer_management_api.dto.FarmerRegistrationRequest;
import com.famrut.farmer_management_api.entity.FarmerStatus;
import com.famrut.farmer_management_api.dto.AuthResponse;
import com.famrut.farmer_management_api.service.JwtService;

@Service
public class OtpService {

    private final FarmerRepository farmerRepository;
    private final OtpVerificationRepository otpVerificationRepository;
    private final JwtService jwtService;
      public OtpService(
        OtpVerificationRepository otpVerificationRepository,
        FarmerRepository farmerRepository,JwtService jwtService) {

    this.otpVerificationRepository = otpVerificationRepository;
    this.farmerRepository = farmerRepository;
    this.jwtService=jwtService;
}

    public void sendOtp(SendOtpRequest request) {

        String otp = generateOtp();

        OtpVerification verification = new OtpVerification();

        verification.setPhoneNumber(request.getPhoneNumber());
        verification.setOtp(otp);
        verification.setExpiresAt(LocalDateTime.now().plusMinutes(5));
        verification.setVerified(false);

        otpVerificationRepository.save(verification);

        System.out.println(
                "OTP for " + request.getPhoneNumber() + " is: " + otp
        );
    }

    private String generateOtp() {

        int otp = ThreadLocalRandom.current()
                .nextInt(100000, 1000000);

        return String.valueOf(otp);
    }


    public AuthResponse verifyOtp(VerifyOtpRequest request) {

        OtpVerification verification =
                otpVerificationRepository
                        .findTopByPhoneNumberAndVerifiedFalseOrderByIdDesc(
                                request.getPhoneNumber()
                        )
                        .orElseThrow(() ->
                                new IllegalArgumentException("OTP not found")
                        );

        if (verification.getExpiresAt().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("OTP has expired");
        }

        if (!verification.getOtp().equals(request.getOtp())) {
            throw new IllegalArgumentException("Invalid OTP");
        }

        verification.setVerified(true);
        verification.setVerifiedAt(LocalDateTime.now());

        otpVerificationRepository.save(verification);

        Farmer farmer = farmerRepository
                .findByPhoneNumber(request.getPhoneNumber())
                .orElse(null);

        /*
        * OTP is valid, but farmer does not exist yet.
        * Registration will be handled separately.
        */
        if (farmer == null) {
            return new AuthResponse(
                    "OTP verified. Farmer registration required.",
                    null,
                    null
            );
        }

        // Farmer exists → generate JWT
        String token = jwtService.generateToken(farmer);

        return new AuthResponse(
                "Login successful",
                token,
                farmer.getId()
        );
    }


    public Farmer registerFarmer(
        VerifyOtpRequest otpRequest,
        FarmerRegistrationRequest registrationRequest) {

    Farmer existingFarmer = farmerRepository
            .findByPhoneNumber(otpRequest.getPhoneNumber())
            .orElse(null);

    if (existingFarmer != null) {
        throw new IllegalArgumentException(
                "Farmer already registered with this phone number"
        );
    }

    Farmer farmer = new Farmer();

    farmer.setName(registrationRequest.getName());
    farmer.setEmail(registrationRequest.getEmail());
    farmer.setPhoneNumber(otpRequest.getPhoneNumber());
    farmer.setStatus(FarmerStatus.ACTIVE);

    return farmerRepository.save(farmer);
    }

    public Farmer registerFarmerAfterOtp(
        VerifyOtpRequest otpRequest,
        FarmerRegistrationRequest registrationRequest) {

    verifyOtp(otpRequest);

    if (farmerRepository.findByPhoneNumber(
            otpRequest.getPhoneNumber()).isPresent()) {

        throw new IllegalArgumentException(
                "Farmer already registered with this phone number"
        );
    }

    Farmer farmer = new Farmer();

    farmer.setName(registrationRequest.getName());
    farmer.setEmail(registrationRequest.getEmail());
    farmer.setPhoneNumber(otpRequest.getPhoneNumber());
    farmer.setStatus(FarmerStatus.ACTIVE);

    return farmerRepository.save(farmer);
    }
}