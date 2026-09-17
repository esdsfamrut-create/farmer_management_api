package com.famrut.farmer_management_api.controller;

import com.famrut.farmer_management_api.dto.ApiResponse;
import com.famrut.farmer_management_api.dto.BlockRequest;
import com.famrut.farmer_management_api.dto.BlockResponse;
import com.famrut.farmer_management_api.dto.BlockStatusRequest;
import com.famrut.farmer_management_api.service.BlockService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class BlockController {

    private final BlockService blockService;

    public BlockController(BlockService blockService) {
        this.blockService = blockService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/blocks")
    public ResponseEntity<ApiResponse<BlockResponse>> create(
            @Valid @RequestBody BlockRequest request) {

        BlockResponse result =
                blockService.create(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        new ApiResponse<>(
                                201,
                                "Block created successfully",
                                result
                        )
                );
    }

    
    @GetMapping("/blocks/{id}")
    public ResponseEntity<ApiResponse<BlockResponse>> getById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                new ApiResponse<>(
                        200,
                        "Block fetched successfully",
                        blockService.getById(id)
                )
        );
    }

    @GetMapping("/sub-districts/{subDistrictId}/blocks")
    public ResponseEntity<ApiResponse<List<BlockResponse>>>
    getBySubDistrict(
            @PathVariable Long subDistrictId) {

        return ResponseEntity.ok(
                new ApiResponse<>(
                        200,
                        "Blocks fetched successfully",
                        blockService.getBySubDistrict(
                                subDistrictId
                        )
                )
        );
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/blocks/{id}")
    public ResponseEntity<ApiResponse<BlockResponse>> update(
            @PathVariable Long id,
            @Valid @RequestBody BlockRequest request) {

        return ResponseEntity.ok(
                new ApiResponse<>(
                        200,
                        "Block updated successfully",
                        blockService.update(id, request)
                )
        );
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/blocks/{id}/status")
    public ResponseEntity<ApiResponse<BlockResponse>>
    updateStatus(
            @PathVariable Long id,
            @Valid @RequestBody BlockStatusRequest request) {

        BlockResponse result =
                blockService.updateStatus(
                        id,
                        request.getActive()
                );

        return ResponseEntity.ok(
                new ApiResponse<>(
                        200,
                        request.getActive()
                                ? "Block activated successfully"
                                : "Block deactivated successfully",
                        result
                )
        );
    }
}