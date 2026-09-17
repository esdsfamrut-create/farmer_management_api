package com.famrut.farmer_management_api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.access.prepost.PreAuthorize;

import com.famrut.farmer_management_api.dto.LanguageResponse;
import com.famrut.farmer_management_api.service.LanguageService;
import java.util.List;

import org.springframework.http.ResponseEntity;

import com.famrut.farmer_management_api.dto.ApiResponse;
import com.famrut.farmer_management_api.dto.LanguageRequest;

import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.HttpStatus;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1")
/**
 * LanguageController
 */
public class LanguageController {

    private final LanguageService languageService;

    public LanguageController(LanguageService languageService){
        this.languageService=languageService;
    }


    @GetMapping("/languages")
    public ResponseEntity<ApiResponse<List<LanguageResponse>>> getLanguages() {
         List<LanguageResponse> languageResponse=languageService.getAllActiveLanguages();

        return ResponseEntity.ok(new ApiResponse<>(200,"Fetched List Successfully",languageResponse));
    }

    @GetMapping("/languages/{id}")
    public ResponseEntity<ApiResponse<LanguageResponse>> getLanguagesById(@PathVariable Long id) {
         LanguageResponse languageResponse=languageService.getLanguageById(id);

        return ResponseEntity.ok(new ApiResponse<>(200,"Fetched Language Successfully",languageResponse));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/languages")
    public  ResponseEntity<ApiResponse<LanguageResponse>> createLanguage(@Valid @RequestBody LanguageRequest languageRequest){
        LanguageResponse languageResponse=languageService.createLanguage(languageRequest);

        return ResponseEntity.status(HttpStatus.CREATED)
        .body(new ApiResponse<>(201,"Language Created Successfully",languageResponse));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/languages/{id}")
    public  ResponseEntity<ApiResponse<LanguageResponse>> updateLanguage(@PathVariable Long id,@Valid @RequestBody LanguageRequest languageRequest){
        LanguageResponse languageResponse=languageService.updateLanguage(id,languageRequest);

        return ResponseEntity.ok(new ApiResponse<>(200,"Language Updated Successfully",languageResponse));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/languages/{id}/status")
    public ResponseEntity<ApiResponse<LanguageResponse>> updateLanguageStatus(
            @PathVariable Long id, @RequestParam boolean active) {
        LanguageResponse languageResponse=languageService.updateStatus(id, active);

        return ResponseEntity.ok(new ApiResponse<>(200,
                active ? "Language activated successfully" : "Language deactivated successfully",
                languageResponse));
    }

}