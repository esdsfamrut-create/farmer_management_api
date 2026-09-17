package com.famrut.farmer_management_api.mapper;

import org.springframework.stereotype.Component;
import com.famrut.farmer_management_api.dto.SeasonRequest;
import com.famrut.farmer_management_api.dto.SeasonResponse;
import com.famrut.farmer_management_api.entity.Season;


@Component
public class SeasonMapper {

    public SeasonResponse toResponse(Season season) {

        SeasonResponse response = new SeasonResponse();

        // PRACTICE:
        // id
        // code
        // name
        // startMonth
        // endMonth
        // active
        response.setId(season.getId());
        response.setName(season.getName());
        response.setCode(season.getCode());
        response.setStartMonth(season.getStartMonth());
        response.setEndMonth(season.getEndMonth());
        response.setActive(season.isActive());

        return response;
    }

    public Season toEntity(SeasonRequest request) {

        Season season = new Season();

        // PRACTICE:
        // code -> trim + uppercase
        // name
        // startMonth
        // endMonth
        // active = true
        season.setName(request.getName());
        season.setCode(  request.getCode().trim().toUpperCase());
        season.setStartMonth(request.getStartMonth());
        season.setEndMonth(request.getEndMonth());
        season.setActive(true);

        return season;
    }

    public void updateEntity(
            SeasonRequest request,
            Season season) {
        season.setName(request.getName());
        season.setCode(request.getCode().trim().toUpperCase());
        season.setStartMonth(request.getStartMonth());
        season.setEndMonth(request.getEndMonth());
        // PRACTICE:
        // update code
        // name
        // startMonth
        // endMonth
        //
        // do NOT change active
    }
}