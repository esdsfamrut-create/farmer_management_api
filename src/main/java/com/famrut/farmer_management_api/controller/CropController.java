package com.famrut.farmer_management_api.controller;

import com.famrut.farmer_management_api.dto.ApiResponse;
import com.famrut.farmer_management_api.dto.CropRequest;
import com.famrut.farmer_management_api.dto.CropResponse;
import com.famrut.farmer_management_api.service.CropService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1")
public class CropController {

    private final CropService cropService;

    public CropController(CropService cropService) {
        this.cropService = cropService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/crops")
    public ResponseEntity<ApiResponse<CropResponse>> createCrop(
            @Valid @RequestBody CropRequest request) {

        CropResponse cropResponse =
                cropService.createCrop(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        new ApiResponse<>(
                                201,
                                "Crop created successfully",
                                cropResponse
                        )
                );
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/crops/{id}")
    public ResponseEntity<ApiResponse<CropResponse>> updateCrop(
            @PathVariable Long id,
            @Valid @RequestBody CropRequest request) {

        CropResponse updatedCrop =
                cropService.updateCrop(id, request);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        200,
                        "Crop updated successfully",
                        updatedCrop
                )
        );
    }

    @GetMapping("/crops")
    public ResponseEntity<ApiResponse<List<CropResponse>>> getAllCrops() {

        List<CropResponse> crops =
                cropService.getAllActiveCrops();

        return ResponseEntity.ok(
                new ApiResponse<>(
                        200,
                        "Crops retrieved successfully",
                        crops
                )
        );
    }

    @GetMapping("/crops/{id}")
    public ResponseEntity<ApiResponse<CropResponse>> getCropById(
            @PathVariable Long id) {

        CropResponse cropResponse =
                cropService.getCropById(id);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        200,
                        "Crop retrieved successfully",
                        cropResponse
                )
        );
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/crops/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteCrop(
            @PathVariable Long id) {

        cropService.deactivateCrop(id);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        200,
                        "Crop deactivated successfully"
                )
        );
    }
}