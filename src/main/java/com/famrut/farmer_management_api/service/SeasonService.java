package com.famrut.farmer_management_api.service;

import java.util.List;

import org.springframework.stereotype.Service;
import com.famrut.farmer_management_api.dto.SeasonRequest;
import com.famrut.farmer_management_api.dto.SeasonResponse;
import com.famrut.farmer_management_api.entity.Season;
import com.famrut.farmer_management_api.repository.SeasonRepository;
import com.famrut.farmer_management_api.mapper.SeasonMapper;
/**
 * SeasonService
 */
@Service
public class SeasonService {

    private final SeasonRepository seasonRepository;
    private final SeasonMapper seasonMapper;

    public SeasonService( SeasonRepository seasonRepository,
    SeasonMapper seasonMapper){
        this.seasonRepository=seasonRepository;
        this.seasonMapper=seasonMapper;
    }


    public SeasonResponse createSeason(SeasonRequest request) {

        // 1. normalize code
        // 2. existsByCodeIgnoreCase(...)
        // 3. duplicate -> IllegalArgumentException
        // 4. mapper.toEntity(request)
        // 5. repository.save(...)
        // 6. mapper.toResponse(...)
        boolean isAdded=seasonRepository.existsByCodeIgnoreCase(request.getCode().trim().toUpperCase());
        if(isAdded){
           throw new IllegalArgumentException(
                    "Season already exists: " + request.getCode()
            );
        }

        Season season=seasonMapper.toEntity(request);
        Season newSeason=seasonRepository.save(season);

        return seasonMapper.toResponse(newSeason);
    }

     public List<SeasonResponse> getAllActiveSeasons(){
        List<SeasonResponse> response=seasonRepository.findAllByActiveTrueOrderByNameAsc().stream().map(seasonMapper::toResponse).toList();
     
    return response;
    }

    public SeasonResponse getSeasonById(Long id) {

        // 1. repository.findById(id)
        // 2. if missing -> throw exception
        // 3. convert Season -> SeasonResponse
        // 4. return response
    Season season=seasonRepository.findById(id).orElseThrow(() ->
                    new RuntimeException("Season not found with id: " + id)
            );
    return seasonMapper.toResponse(season);
    }

    public SeasonResponse updateSeason(Long id, SeasonRequest request) {
        // 1. Find existing Season by id
        // 2. If missing -> throw
        // 3. Determine whether code changed
        // 4. If code changed, check duplicate code
        // 5. seasonMapper.updateEntity(request, season)
        // 6. save
        // 7. map and return

        Season season=seasonRepository.findById(id).orElseThrow(() ->
                    new RuntimeException("Season not found with id: " + id)
            );

            boolean isAdded=seasonRepository.existsByCodeIgnoreCase(request.getCode().trim().toUpperCase());
        if(isAdded){
           throw new IllegalArgumentException(
                    "Season already exists: " + request.getCode()
            );
        }
            seasonMapper.updateEntity(request,season);

            SeasonResponse response=seasonMapper.toResponse(seasonRepository.save(season));
            return response;

        
    }

    public SeasonResponse updateStatus(
        Long id,
        boolean active){
            Season season=seasonRepository.findById(id).orElseThrow(() ->
                    new RuntimeException("Season not found with id: " + id)
            );

           season.setActive(active);
            
            SeasonResponse response=seasonMapper.toResponse(seasonRepository.save(season));
            return response;

        }
}