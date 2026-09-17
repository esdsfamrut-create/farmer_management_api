package com.famrut.farmer_management_api.service;

import com.famrut.farmer_management_api.dto.LanguageRequest;
import com.famrut.farmer_management_api.dto.LanguageResponse;
import com.famrut.farmer_management_api.entity.Language;
import com.famrut.farmer_management_api.mapper.LanguageMapper;
import com.famrut.farmer_management_api.repository.LanguageRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LanguageService {

    private final LanguageRepository languageRepository;
    private final LanguageMapper languageMapper;

    public LanguageService(
            LanguageRepository languageRepository,
            LanguageMapper languageMapper) {

        this.languageRepository = languageRepository;
        this.languageMapper = languageMapper;
    }

   public LanguageResponse createLanguage(LanguageRequest request) {

        if (languageRepository.existsByCodeIgnoreCase(
                request.getCode().trim().toLowerCase())) {

            throw new IllegalArgumentException(
                    "Language code already exists: " + request.getCode()
            );
        }

        Language language =
                languageMapper.toEntity(request);

        Language savedLanguage =
                languageRepository.save(language);

        return languageMapper.toResponse(savedLanguage);
    }

    public List<LanguageResponse> getAllActiveLanguages() {

        return languageRepository
                .findAllByActiveTrueOrderByNameAsc()
                .stream()
                .map(languageMapper::toResponse)
                .toList();
    }

    public LanguageResponse getLanguageById(Long id) {

        // PRACTICE:
        // findById
        // if missing -> exception
        // map response
        return languageMapper.toResponse(languageRepository.findById(id) .orElseThrow(() ->
                new RuntimeException("Language not found with id: " + id)
        ));
    }

    public LanguageResponse updateLanguage(
        Long id,
        LanguageRequest request) {

    Language language = languageRepository
            .findById(id)
            .orElseThrow(() ->
                    new RuntimeException(
                            "Language not found with id: " + id
                    )
            );

    String newCode =
            request.getCode().trim().toLowerCase();

    boolean codeChanged =
            !language.getCode().equalsIgnoreCase(newCode);

    if (codeChanged &&
            languageRepository.existsByCodeIgnoreCase(newCode)) {

        throw new IllegalArgumentException(
                "Language code already exists: "
                        + request.getCode()
        );
    }

    languageMapper.updateEntity(
            request,
            language
    );

    Language savedLanguage =
            languageRepository.save(language);

    return languageMapper.toResponse(savedLanguage);
}

    public LanguageResponse updateStatus(
            Long id,
            boolean active) {

        // PRACTICE:
        // find existing
        // set active
        // save
        // return response

          Language language = languageRepository
            .findById(id)
            .orElseThrow(() ->
                    new RuntimeException(
                            "Language not found with id: " + id
                    )
            );

            language.setActive(active);

            return languageMapper.toResponse(languageRepository.save(language));
    }
}