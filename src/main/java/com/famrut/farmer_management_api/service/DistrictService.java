package com.famrut.farmer_management_api.service;

import com.famrut.farmer_management_api.dto.DistrictRequest;
import com.famrut.farmer_management_api.dto.DistrictResponse;
import com.famrut.farmer_management_api.entity.District;
import com.famrut.farmer_management_api.entity.State;
import com.famrut.farmer_management_api.exception.DistrictNotFoundException;
import com.famrut.farmer_management_api.exception.StateNotFoundException;
import com.famrut.farmer_management_api.repository.DistrictRepository;
import com.famrut.farmer_management_api.repository.StateRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DistrictService {

    private final DistrictRepository districtRepository;
    private final StateRepository stateRepository;

    public DistrictService(
            DistrictRepository districtRepository,
            StateRepository stateRepository) {

        this.districtRepository = districtRepository;
        this.stateRepository = stateRepository;
    }

    public DistrictResponse create(DistrictRequest request) {

        State state = stateRepository
                .findById(request.getStateId())
                .orElseThrow(() ->
                        new StateNotFoundException(
                                request.getStateId()
                        )
                );

        String code = request.getDistrictCode().trim();
        String name = request.getDistrictName().trim();

        if (districtRepository
                .existsByStateIdAndDistrictCode(
                        state.getId(),
                        code)) {

            throw new IllegalArgumentException(
                    "District code already exists for this state"
            );
        }

        District district = new District();

        district.setDistrictCode(code);
        district.setDistrictName(name);
        district.setState(state);
        district.setActive(true);

        return toResponse(
                districtRepository.save(district)
        );
    }

    public DistrictResponse getById(Long id) {

        District district = districtRepository
                .findById(id)
                .orElseThrow(() ->
                        new DistrictNotFoundException(id)
                );

        return toResponse(district);
    }

    public List<DistrictResponse> getByState(Long stateId) {

        if (!stateRepository.existsById(stateId)) {
            throw new StateNotFoundException(stateId);
        }

        return districtRepository
                .findAllByStateIdAndActiveTrueOrderByDistrictNameAsc(
                        stateId
                )
                .stream()
                .map(this::toResponse)
                .toList();
    }

    private DistrictResponse toResponse(District district) {

        return new DistrictResponse(
                district.getId(),
                district.getDistrictCode(),
                district.getDistrictName(),
                district.getState().getId(),
                district.getState().getStateName(),
                district.isActive()
        );
    }

    public DistrictResponse update(
        Long id,
        DistrictRequest request) {

    District district = districtRepository
            .findById(id)
            .orElseThrow(() ->
                    new DistrictNotFoundException(id)
            );

    State state = stateRepository
            .findById(request.getStateId())
            .orElseThrow(() ->
                    new StateNotFoundException(
                            request.getStateId()
                    )
            );

    String code = request.getDistrictCode().trim();
    String name = request.getDistrictName().trim();

    boolean codeChanged =
            !district.getDistrictCode().equals(code)
            || !district.getState().getId().equals(state.getId());

    if (codeChanged &&
            districtRepository.existsByStateIdAndDistrictCode(
                    state.getId(),
                    code)) {

        throw new IllegalArgumentException(
                "District code already exists for this state"
        );
    }

    district.setDistrictCode(code);
    district.setDistrictName(name);
    district.setState(state);

    return toResponse(
            districtRepository.save(district)
    );
}

public DistrictResponse updateStatus(
        Long id,
        boolean active) {

    District district = districtRepository
            .findById(id)
            .orElseThrow(() ->
                    new DistrictNotFoundException(id)
            );

    district.setActive(active);

    return toResponse(
            districtRepository.save(district)
    );
}
}