package com.famrut.farmer_management_api.service;

import com.famrut.farmer_management_api.dto.BlockRequest;
import com.famrut.farmer_management_api.dto.BlockResponse;
import com.famrut.farmer_management_api.entity.Block;
import com.famrut.farmer_management_api.entity.District;
import com.famrut.farmer_management_api.entity.SubDistrict;
import com.famrut.farmer_management_api.exception.BlockNotFoundException;
import com.famrut.farmer_management_api.exception.SubDistrictNotFoundException;
import com.famrut.farmer_management_api.repository.BlockRepository;
import com.famrut.farmer_management_api.repository.SubDistrictRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlockService {

    private final BlockRepository blockRepository;
    private final SubDistrictRepository subDistrictRepository;

    public BlockService(
            BlockRepository blockRepository,
            SubDistrictRepository subDistrictRepository) {

        this.blockRepository = blockRepository;
        this.subDistrictRepository = subDistrictRepository;
    }

    public BlockResponse create(BlockRequest request) {

        SubDistrict subDistrict =
                subDistrictRepository
                        .findById(request.getSubDistrictId())
                        .orElseThrow(() ->
                                new SubDistrictNotFoundException(
                                        request.getSubDistrictId()
                                )
                        );

        String code = request.getBlockCode().trim();
        String name = request.getBlockName().trim();

        if (blockRepository
                .existsBySubDistrictIdAndBlockCode(
                        subDistrict.getId(),
                        code)) {

            throw new IllegalArgumentException(
                    "Block code already exists for this sub-district"
            );
        }

        Block block = new Block();

        block.setBlockCode(code);
        block.setBlockName(name);
        block.setSubDistrict(subDistrict);
        block.setActive(true);

        return toResponse(
                blockRepository.save(block)
        );
    }

    public BlockResponse getById(Long id) {

        Block block = blockRepository
                .findById(id)
                .orElseThrow(() ->
                        new BlockNotFoundException(id)
                );

        return toResponse(block);
    }

    public List<BlockResponse> getBySubDistrict(
            Long subDistrictId) {

        if (!subDistrictRepository.existsById(subDistrictId)) {
            throw new SubDistrictNotFoundException(subDistrictId);
        }

        return blockRepository
                .findAllBySubDistrictIdAndActiveTrueOrderByBlockNameAsc(
                        subDistrictId
                )
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public BlockResponse update(
            Long id,
            BlockRequest request) {

        Block block = blockRepository
                .findById(id)
                .orElseThrow(() ->
                        new BlockNotFoundException(id)
                );

        SubDistrict subDistrict =
                subDistrictRepository
                        .findById(request.getSubDistrictId())
                        .orElseThrow(() ->
                                new SubDistrictNotFoundException(
                                        request.getSubDistrictId()
                                )
                        );

        String code = request.getBlockCode().trim();
        String name = request.getBlockName().trim();

        boolean codeChanged =
                !block.getBlockCode().equals(code)
                ||
                !block.getSubDistrict()
                        .getId()
                        .equals(subDistrict.getId());

        if (codeChanged &&
                blockRepository
                        .existsBySubDistrictIdAndBlockCode(
                                subDistrict.getId(),
                                code)) {

            throw new IllegalArgumentException(
                    "Block code already exists for this sub-district"
            );
        }

        block.setBlockCode(code);
        block.setBlockName(name);
        block.setSubDistrict(subDistrict);

        return toResponse(
                blockRepository.save(block)
        );
    }

    public BlockResponse updateStatus(
            Long id,
            boolean active) {

        Block block = blockRepository
                .findById(id)
                .orElseThrow(() ->
                        new BlockNotFoundException(id)
                );

        block.setActive(active);

        return toResponse(
                blockRepository.save(block)
        );
    }

    private BlockResponse toResponse(Block block) {

        SubDistrict subDistrict =
                block.getSubDistrict();

        District district =
                subDistrict.getDistrict();

        return new BlockResponse(
                block.getId(),
                block.getBlockCode(),
                block.getBlockName(),

                subDistrict.getId(),
                subDistrict.getSubDistrictName(),

                district.getId(),
                district.getDistrictName(),

                district.getState().getId(),
                district.getState().getStateName(),

                block.isActive()
        );
    }
}