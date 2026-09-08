package com.famrut.farmer_management_api.repository;

import com.famrut.farmer_management_api.entity.OtpVerification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OtpVerificationRepository
        extends JpaRepository<OtpVerification, Long> {

    Optional<OtpVerification> findTopByPhoneNumberAndVerifiedFalseOrderByIdDesc(
            String phoneNumber
    );

}