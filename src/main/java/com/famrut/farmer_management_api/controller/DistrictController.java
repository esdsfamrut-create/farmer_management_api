package com.famrut.farmer_management_api.controller;

import com.famrut.farmer_management_api.dto.ApiResponse;
import com.famrut.farmer_management_api.dto.DistrictRequest;
import com.famrut.farmer_management_api.dto.DistrictResponse;
import com.famrut.farmer_management_api.dto.DistrictStatusRequest;
import com.famrut.farmer_management_api.service.DistrictService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class DistrictController {

    private final DistrictService districtService;

    public DistrictController(
            DistrictService districtService) {

        this.districtService = districtService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/districts")
    public ResponseEntity<ApiResponse<DistrictResponse>>
    createDistrict(
            @Valid @RequestBody DistrictRequest request) {

        DistrictResponse district =
                districtService.create(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                    new ApiResponse<>(
                        201,
                        "District created successfully",
                        district
                    )
                );
    }

    @GetMapping("/districts/{id}")
    public ResponseEntity<ApiResponse<DistrictResponse>>
    getDistrict(@PathVariable Long id) {

        DistrictResponse district =
                districtService.getById(id);

        return ResponseEntity.ok(
                new ApiResponse<>(
                    200,
                    "District fetched successfully",
                    district
                )
        );
    }

    @GetMapping("/states/{stateId}/districts")
    public ResponseEntity<ApiResponse<List<DistrictResponse>>>
    getDistrictsByState(
            @PathVariable Long stateId) {

        List<DistrictResponse> districts =
                districtService.getByState(stateId);

        return ResponseEntity.ok(
                new ApiResponse<>(
                    200,
                    "Districts fetched successfully",
                    districts
                )
        );
    }

    @PreAuthorize("hasRole('ADMIN')")
@PutMapping("/districts/{id}")
public ResponseEntity<ApiResponse<DistrictResponse>>
updateDistrict(
        @PathVariable Long id,
        @Valid @RequestBody DistrictRequest request) {

            DistrictResponse district =
                    districtService.update(id, request);

            return ResponseEntity.ok(
                    new ApiResponse<>(
                            200,
                            "District updated successfully",
                            district
                    )
            );
        }

        @PreAuthorize("hasRole('ADMIN')")
@PatchMapping("/districts/{id}/status")
public ResponseEntity<ApiResponse<DistrictResponse>>
updateDistrictStatus(
        @PathVariable Long id,
        @Valid @RequestBody DistrictStatusRequest request) {

            DistrictResponse district =
                    districtService.updateStatus(
                            id,
                            request.getActive()
                    );

            return ResponseEntity.ok(
                    new ApiResponse<>(
                            200,
                            request.getActive()
                                    ? "District activated successfully"
                                    : "District deactivated successfully",
                            district
                    )
            );
        }
}