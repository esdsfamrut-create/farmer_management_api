package com.famrut.farmer_management_api.controller;
import com.famrut.farmer_management_api.dto.FarmRequest;
import com.famrut.farmer_management_api.dto.FarmResponse;
import com.famrut.farmer_management_api.service.FarmService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import com.famrut.farmer_management_api.dto.ApiResponse;

@RestController
@RequestMapping("/api/v1")
public class FarmController {

    private final FarmService farmService;

    public FarmController(FarmService farmService) {
        this.farmService = farmService;
    }

    @GetMapping("farms/{id}")
    public ResponseEntity<ApiResponse<FarmResponse>> getFarmById(@PathVariable Long id, Authentication authentication) {
        Long farmerId = Long.valueOf(authentication.getName());
        FarmResponse farmResponse = farmService.getFarmById(id, farmerId);
        return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(
                        new ApiResponse<>(
                            200,
                            "Farm retrieved successfully",
                            farmResponse
                        )
                    );
    }

    @PostMapping("/farms")
    public ResponseEntity<ApiResponse<FarmResponse>> createFarm( Authentication authentication, @Valid @RequestBody FarmRequest farmRequest) {
         Long farmerId = Long.valueOf(authentication.getName());
        FarmResponse farmResponse = farmService.createFarm(farmerId, farmRequest);
        return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(
                        new ApiResponse<>(
                            201,
                            "Farm created successfully",
                            farmResponse
                        )
                    );
    }

   @PutMapping("/farms/{id}")
    public ResponseEntity<ApiResponse<FarmResponse>> updateFarm(
            @PathVariable Long id,
            Authentication authentication,
            @Valid @RequestBody FarmRequest farmRequest) {

        Long farmerId =
                Long.valueOf(authentication.getName());

        FarmResponse farmResponse =
                farmService.updateFarm(
                        id,
                        farmerId,
                        farmRequest
                );

        return ResponseEntity.ok(
                new ApiResponse<>(
                        200,
                        "Farm updated successfully",
                        farmResponse
                )
        );
    }

    @DeleteMapping("farms/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteFarm(@PathVariable Long id,Authentication authentication) {
       Long farmerId = Long.valueOf(authentication.getName());
        farmService.deleteFarm(id,farmerId);
             return ResponseEntity
                    .status(HttpStatus.OK )
                    .body(
                        new ApiResponse<>(
                            200,
                            "Farm deleted successfully",
                            null
                        )
                    );
    }

    @GetMapping("farms")
    public ResponseEntity<ApiResponse<List<FarmResponse>>> getAllFarms(Authentication authentication) {
        Long farmerId = Long.valueOf(authentication.getName());
        List<FarmResponse> farmResponses = farmService.getAllFarms(farmerId);
        return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(
                        new ApiResponse<>(
                            200,
                            "Farms retrieved successfully",
                            farmResponses
                        )
                    );
    }
}   