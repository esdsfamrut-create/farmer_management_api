package com.famrut.farmer_management_api.controller;

import com.famrut.farmer_management_api.dto.ApiResponse;
import com.famrut.farmer_management_api.dto.SubDistrictRequest;
import com.famrut.farmer_management_api.dto.SubDistrictResponse;
import com.famrut.farmer_management_api.dto.SubDistrictStatusRequest;
import com.famrut.farmer_management_api.service.SubDistrictService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class SubDistrictController {

    private final SubDistrictService subDistrictService;

    public SubDistrictController(
            SubDistrictService subDistrictService) {

        this.subDistrictService = subDistrictService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/sub-districts")
    public ResponseEntity<ApiResponse<SubDistrictResponse>>
    create(
            @Valid @RequestBody
            SubDistrictRequest request) {

        SubDistrictResponse result =
                subDistrictService.create(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        new ApiResponse<>(
                                201,
                                "Sub-district created successfully",
                                result
                        )
                );
    }

    @GetMapping("/sub-districts/{id}")
    public ResponseEntity<ApiResponse<SubDistrictResponse>>
    getById(@PathVariable Long id) {

        return ResponseEntity.ok(
                new ApiResponse<>(
                        200,
                        "Sub-district fetched successfully",
                        subDistrictService.getById(id)
                )
        );
    }

    @GetMapping("/districts/{districtId}/sub-districts")
    public ResponseEntity<ApiResponse<List<SubDistrictResponse>>>
    getByDistrict(
            @PathVariable Long districtId) {

        return ResponseEntity.ok(
                new ApiResponse<>(
                        200,
                        "Sub-districts fetched successfully",
                        subDistrictService
                                .getByDistrict(districtId)
                )
        );
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/sub-districts/{id}")
    public ResponseEntity<ApiResponse<SubDistrictResponse>>
    update(
            @PathVariable Long id,
            @Valid @RequestBody
            SubDistrictRequest request) {

        return ResponseEntity.ok(
                new ApiResponse<>(
                        200,
                        "Sub-district updated successfully",
                        subDistrictService.update(id, request)
                )
        );
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/sub-districts/{id}/status")
    public ResponseEntity<ApiResponse<SubDistrictResponse>>
    updateStatus(
            @PathVariable Long id,
            @Valid @RequestBody
            SubDistrictStatusRequest request) {

        SubDistrictResponse result =
                subDistrictService.updateStatus(
                        id,
                        request.getActive()
                );

        return ResponseEntity.ok(
                new ApiResponse<>(
                        200,
                        request.getActive()
                                ? "Sub-district activated successfully"
                                : "Sub-district deactivated successfully",
                        result
                )
        );
    }
}