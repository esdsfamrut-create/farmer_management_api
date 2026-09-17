package com.famrut.farmer_management_api.controller;

import com.famrut.farmer_management_api.dto.StateRequest;
import com.famrut.farmer_management_api.dto.StateResponse;
import com.famrut.farmer_management_api.service.StateService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import com.famrut.farmer_management_api.dto.ApiResponse;


import java.util.List;

@RestController
@RequestMapping("/api/v1/states")
public class StateController {

    private final StateService stateService;

    public StateController(
            StateService stateService) {

        this.stateService = stateService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<StateResponse>>> getStates() {

        return ResponseEntity.ok(new ApiResponse<>(200, "States retrieved successfully", stateService.getActiveStates()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<StateResponse>> getState(
            @PathVariable Long id) {

        return ResponseEntity.ok(new ApiResponse<>(200, "State retrieved successfully", stateService.getById(id)));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<ApiResponse<StateResponse>> createState(
            @Valid @RequestBody StateRequest request) {

        return ResponseEntity.ok(new ApiResponse<>(200, "State created successfully", stateService.create(request)));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<StateResponse>> updateState(
            @PathVariable Long id,
            @Valid @RequestBody StateRequest request) {

        return ResponseEntity.ok(new ApiResponse<>(200, "State updated successfully", stateService.update(id, request)));
    }
}