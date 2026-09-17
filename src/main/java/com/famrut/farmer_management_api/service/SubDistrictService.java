package com.famrut.farmer_management_api.service;

import com.famrut.farmer_management_api.dto.SubDistrictRequest;
import com.famrut.farmer_management_api.dto.SubDistrictResponse;
import com.famrut.farmer_management_api.entity.District;
import com.famrut.farmer_management_api.entity.SubDistrict;
import com.famrut.farmer_management_api.exception.DistrictNotFoundException;
import com.famrut.farmer_management_api.exception.SubDistrictNotFoundException;
import com.famrut.farmer_management_api.repository.DistrictRepository;
import com.famrut.farmer_management_api.repository.SubDistrictRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubDistrictService {

    private final SubDistrictRepository subDistrictRepository;
    private final DistrictRepository districtRepository;

    public SubDistrictService(
            SubDistrictRepository subDistrictRepository,
            DistrictRepository districtRepository) {

        this.subDistrictRepository = subDistrictRepository;
        this.districtRepository = districtRepository;
    }

    public SubDistrictResponse create(
            SubDistrictRequest request) {

        District district = districtRepository
                .findById(request.getDistrictId())
                .orElseThrow(() ->
                        new DistrictNotFoundException(
                                request.getDistrictId()
                        )
                );

        String code =
                request.getSubDistrictCode().trim();

        String name =
                request.getSubDistrictName().trim();

        if (subDistrictRepository
                .existsByDistrictIdAndSubDistrictCode(
                        district.getId(),
                        code)) {

            throw new IllegalArgumentException(
                    "Sub-district code already exists for this district"
            );
        }

        SubDistrict subDistrict =
                new SubDistrict();

        subDistrict.setSubDistrictCode(code);
        subDistrict.setSubDistrictName(name);
        subDistrict.setDistrict(district);
        subDistrict.setActive(true);

        return toResponse(
                subDistrictRepository.save(subDistrict)
        );
    }

    public SubDistrictResponse getById(Long id) {

        SubDistrict subDistrict =
                subDistrictRepository
                        .findById(id)
                        .orElseThrow(() ->
                                new SubDistrictNotFoundException(id)
                        );

        return toResponse(subDistrict);
    }

    public List<SubDistrictResponse>
    getByDistrict(Long districtId) {

        if (!districtRepository.existsById(districtId)) {
            throw new DistrictNotFoundException(districtId);
        }

        return subDistrictRepository
                .findAllByDistrictIdAndActiveTrueOrderBySubDistrictNameAsc(
                        districtId
                )
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public SubDistrictResponse update(
            Long id,
            SubDistrictRequest request) {

        SubDistrict subDistrict =
                subDistrictRepository
                        .findById(id)
                        .orElseThrow(() ->
                                new SubDistrictNotFoundException(id)
                        );

        District district =
                districtRepository
                        .findById(request.getDistrictId())
                        .orElseThrow(() ->
                                new DistrictNotFoundException(
                                        request.getDistrictId()
                                )
                        );

        String code =
                request.getSubDistrictCode().trim();

        String name =
                request.getSubDistrictName().trim();

        boolean codeChanged =
                !subDistrict.getSubDistrictCode().equals(code)
                        ||
                !subDistrict.getDistrict()
                        .getId()
                        .equals(district.getId());

        if (codeChanged &&
                subDistrictRepository
                        .existsByDistrictIdAndSubDistrictCode(
                                district.getId(),
                                code)) {

            throw new IllegalArgumentException(
                    "Sub-district code already exists for this district"
            );
        }

        subDistrict.setSubDistrictCode(code);
        subDistrict.setSubDistrictName(name);
        subDistrict.setDistrict(district);

        return toResponse(
                subDistrictRepository.save(subDistrict)
        );
    }

    public SubDistrictResponse updateStatus(
            Long id,
            boolean active) {

        SubDistrict subDistrict =
                subDistrictRepository
                        .findById(id)
                        .orElseThrow(() ->
                                new SubDistrictNotFoundException(id)
                        );

        subDistrict.setActive(active);

        return toResponse(
                subDistrictRepository.save(subDistrict)
        );
    }

    private SubDistrictResponse toResponse(
            SubDistrict subDistrict) {

        District district =
                subDistrict.getDistrict();

        return new SubDistrictResponse(
                subDistrict.getId(),
                subDistrict.getSubDistrictCode(),
                subDistrict.getSubDistrictName(),
                district.getId(),
                district.getDistrictName(),
                district.getState().getId(),
                district.getState().getStateName(),
                subDistrict.isActive()
        );
    }
}