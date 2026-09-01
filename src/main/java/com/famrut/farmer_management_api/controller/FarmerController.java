package com.famrut.farmer_management_api.controller;
import com.famrut.farmer_management_api.entity.Farmer;
import com.famrut.farmer_management_api.service.FarmerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import com.famrut.farmer_management_api.dto.FarmerRequest;
import com.famrut.farmer_management_api.dto.FarmerResponse;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.ResponseEntity;
import com.famrut.farmer_management_api.dto.FarmerSearchCriteria;
import com.famrut.farmer_management_api.config.PageableConfig;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.PageImpl;
import com.famrut.farmer_management_api.service.FarmerSearchService;

@RestController
public class FarmerController{

    private final FarmerService farmerService;

    public FarmerController(
        FarmerService farmerService,
        FarmerSearchService farmerSearchService) {

    this.farmerService = farmerService;
    this.farmerSearchService = farmerSearchService;
    }

    private final FarmerSearchService farmerSearchService;

   @GetMapping("/api/v1/farmers")
    public Page<FarmerResponse> getAllFarmers(
            FarmerSearchCriteria criteria,
            Pageable pageable) {

       farmerSearchService. validateSort(pageable);

        return farmerService.searchFarmers(criteria, pageable);
    }

    @PostMapping("/api/v1/farmers")
    public FarmerResponse createFarmer(@Valid @RequestBody FarmerRequest request) {
        return farmerService.createFarmer(request);
    }

    @GetMapping("/api/v1/farmers/{id}")
    public Farmer getFarmerById(@PathVariable Long id) {
        return farmerService.getFarmerById(id);
    }

    @PutMapping("/api/v1/farmers/{id}")
    public FarmerResponse updateFarmer(
            @PathVariable Long id,
            @Valid @RequestBody FarmerRequest request) {

        return farmerService.updateFarmer(id, request);
    }

    @DeleteMapping("/api/v1/farmers/{id}")
    public void deleteFarmer(@PathVariable Long id) {
        farmerService.deleteFarmer(id);
    }

    @GetMapping("/api/v1/farmers/search")
    public Page<FarmerResponse> searchFarmers(
              FarmerSearchCriteria criteria,
            Pageable pageable) {
        

        return farmerService.searchFarmers(criteria, pageable);
    }

    // @GetMapping("/api/v1/farmers/search/email")
    // public Page<FarmerResponse> searchFarmersByEmail(
    //         @RequestParam String email,
    //         Pageable pageable) {

    //     return farmerService.searchFarmersByEmail(email, pageable);
    // }




    
   
}