package com.famrut.farmer_management_api.controller;

import com.famrut.farmer_management_api.dto.ApiResponse;
import com.famrut.farmer_management_api.dto.VillageRequest;
import com.famrut.farmer_management_api.dto.VillageResponse;
import com.famrut.farmer_management_api.dto.VillageStatusRequest;
import com.famrut.farmer_management_api.service.VillageService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class VillageController {

    private final VillageService villageService;

    public VillageController(VillageService villageService) {
        this.villageService = villageService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/villages")
    public ResponseEntity<ApiResponse<VillageResponse>> create(
            @Valid @RequestBody VillageRequest request) {

        VillageResponse result =
                villageService.create(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        new ApiResponse<>(
                                201,
                                "Village created successfully",
                                result
                        )
                );
    }

    @GetMapping("/villages/{id}")
    public ResponseEntity<ApiResponse<VillageResponse>> getById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                new ApiResponse<>(
                        200,
                        "Village fetched successfully",
                        villageService.getById(id)
                )
        );
    }

    @GetMapping("/blocks/{blockId}/villages")
    public ResponseEntity<ApiResponse<List<VillageResponse>>>
    getByBlock(@PathVariable Long blockId) {

        return ResponseEntity.ok(
                new ApiResponse<>(
                        200,
                        "Villages fetched successfully",
                        villageService.getByBlock(blockId)
                )
        );
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/villages/{id}")
    public ResponseEntity<ApiResponse<VillageResponse>> update(
            @PathVariable Long id,
            @Valid @RequestBody VillageRequest request) {

        return ResponseEntity.ok(
                new ApiResponse<>(
                        200,
                        "Village updated successfully",
                        villageService.update(id, request)
                )
        );
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/villages/{id}/status")
    public ResponseEntity<ApiResponse<VillageResponse>>
    updateStatus(
            @PathVariable Long id,
            @Valid @RequestBody VillageStatusRequest request) {

        VillageResponse result =
                villageService.updateStatus(
                        id,
                        request.getActive()
                );

        return ResponseEntity.ok(
                new ApiResponse<>(
                        200,
                        request.getActive()
                                ? "Village activated successfully"
                                : "Village deactivated successfully",
                        result
                )
        );
    }
}