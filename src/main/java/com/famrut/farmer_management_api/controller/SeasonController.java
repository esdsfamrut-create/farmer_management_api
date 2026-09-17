package com.famrut.farmer_management_api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.*;

import com.famrut.farmer_management_api.dto.ApiResponse;
import com.famrut.farmer_management_api.dto.SeasonRequest;
import com.famrut.farmer_management_api.dto.SeasonResponse;
import com.famrut.farmer_management_api.dto.SeasonStatusRequest;
import com.famrut.farmer_management_api.service.SeasonService;
import com.famrut.farmer_management_api.dto.SeasonStatusRequest;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1")
/**
 * SeasonController
 */
public class SeasonController {

    private final SeasonService seasonService;

    public SeasonController(SeasonService seasonService) {
        this.seasonService = seasonService;
    }

    // Your task: implement ONLY this endpoint first
    @GetMapping("/seasons")
    public ResponseEntity<ApiResponse<List<SeasonResponse>>> getAllSeasons() {

        // call seasonService.getAllActiveSeasons()
        // wrap result in ApiResponse
        // return HTTP 200
        List<SeasonResponse> responses = seasonService.getAllActiveSeasons();

        return ResponseEntity.ok(new ApiResponse<>(200, "fetched list successfully", responses));
    }

    @GetMapping("/seasons/{id}")
    public ResponseEntity<ApiResponse<SeasonResponse>> getSeasonById(
            @PathVariable Long id) {

        // 1. seasonService.getSeasonById(id)
        // 2. ApiResponse<>(200, "...", response)
        // 3. ResponseEntity.ok(...)

        SeasonResponse response = seasonService.getSeasonById(id);

        return ResponseEntity.ok(new ApiResponse<>(200, "fetched season successfully", response));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/seasons")
    public ResponseEntity<ApiResponse<SeasonResponse>> createSeason(@Valid @RequestBody SeasonRequest seasonRequest) {
        SeasonResponse response = seasonService.createSeason(seasonRequest);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(201, "created season successfully", response));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/seasons/{id}")
    public ResponseEntity<ApiResponse<SeasonResponse>> updateSeason(@PathVariable Long id,@Valid @RequestBody SeasonRequest seasonRequest) {
        SeasonResponse response = seasonService.updateSeason(id,seasonRequest);
        return ResponseEntity.ok(new ApiResponse<>(200, "update season successfully", response));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/seasons/{id}/status")
    public ResponseEntity<ApiResponse<SeasonResponse>> updateStatusSeason(@PathVariable Long id, @Valid @RequestBody SeasonStatusRequest request) {
        SeasonResponse response = seasonService.updateStatus(id,request.getActive());
        return ResponseEntity.ok(new ApiResponse<>(200, "update season successfully", response));
    }
}