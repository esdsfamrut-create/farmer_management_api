package com.famrut.farmer_management_api.mapper;

import org.springframework.stereotype.Component;

import com.famrut.farmer_management_api.dto.LanguageRequest;
import com.famrut.farmer_management_api.dto.LanguageResponse;
import com.famrut.farmer_management_api.entity.Language;

//import org.springframework.stereotype.Component;

@Component
public class LanguageMapper {

    public LanguageResponse toResponse(Language language) {

        LanguageResponse response = new LanguageResponse();

        // PRACTICE:
        // id
        // code
        // name
        // nativeName
        // active

        response.setId(language.getId());
        response.setCode(language.getCode());
        response.setName(language.getName());
        response.setActive(language.isActive());
        response.setNativeName(language.getNativeName());

        return response;
    }

    public Language toEntity(LanguageRequest request) {

        Language language = new Language();

        // PRACTICE:
        // code
        // name
        // nativeName
        // active = true

        language.setName(request.getName());
        language.setCode(request.getCode().trim().toLowerCase());
        language.setNativeName(request.getNativeName());
        language.setActive(true);
        return language;
    }

    public void updateEntity(
            LanguageRequest request,
            Language language) {

        // PRACTICE:
        // update code
        // update name
        // update nativeName
        //
        // do NOT change active here

        language.setName(request.getName());
        language.setCode(request.getCode().trim().toLowerCase());
        language.setNativeName(request.getNativeName());
    }
}