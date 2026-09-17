package com.famrut.farmer_management_api.controller;

import com.famrut.farmer_management_api.dto.ApiResponse;
import com.famrut.farmer_management_api.dto.FarmCropRequest;
import com.famrut.farmer_management_api.dto.FarmCropResponse;
import com.famrut.farmer_management_api.dto.FarmCropStatusRequest;
import com.famrut.farmer_management_api.service.FarmCropService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1")
public class FarmCropController {

    private final FarmCropService farmCropService;

    public FarmCropController(FarmCropService farmCropService){
        this.farmCropService=farmCropService;
    }

    @PostMapping("/farm-crops")
    public ResponseEntity<ApiResponse<FarmCropResponse>> createFarmcrop(@Valid @RequestBody FarmCropRequest request,Authentication authentication){
        Long farmerId = Long.valueOf(authentication.getName());
        FarmCropResponse response=farmCropService.createFarmCrop(farmerId, request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ApiResponse<>(201, "created successfully", response));
    }



    @GetMapping("/farms/{farmId}/farm-crops")
    public ResponseEntity<ApiResponse<List<FarmCropResponse>>> getFarmCrop(@PathVariable Long farmId,Authentication authentication){
        Long farmerId = Long.valueOf(authentication.getName());
        List<FarmCropResponse> response=farmCropService.getFarmCrops(farmId, farmerId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ApiResponse<>(200, "Fetched Crop List successfully", response));
    } 

    @DeleteMapping("/farm-crops/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteFarmCrop(@PathVariable Long id,Authentication authentication){
        Long farmerId = Long.valueOf(authentication.getName());

        farmCropService.deactivateFarmCrop(id, farmerId);
                return ResponseEntity.ok(new ApiResponse<>(200, "FarmCrop deleted successfully", null));

    }

    @PutMapping("/farm-crops/{id}")
    public ResponseEntity<ApiResponse<FarmCropResponse>> updateFarmcrop(@PathVariable Long id,@Valid @RequestBody FarmCropRequest request,Authentication authentication){
        Long farmerId = Long.valueOf(authentication.getName());
        FarmCropResponse response=farmCropService.updateFarmCrop(id,farmerId, request);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ApiResponse<>(200, "Updated successfully", response));
    }

    @PatchMapping("/farm-crops/{id}/status")
    public ResponseEntity<ApiResponse<FarmCropResponse>> updateStatusFarmcrop(@PathVariable Long id,@Valid @RequestBody FarmCropStatusRequest request,Authentication authentication){
        Long farmerId = Long.valueOf(authentication.getName());
        FarmCropResponse response=farmCropService.updateStatusFarmCrop(id,farmerId, request);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ApiResponse<>(200, "Updated successfully", response));
    }
}