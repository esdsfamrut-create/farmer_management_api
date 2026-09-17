package com.famrut.farmer_management_api.service;

import com.famrut.farmer_management_api.dto.VillageRequest;
import com.famrut.farmer_management_api.dto.VillageResponse;
import com.famrut.farmer_management_api.entity.Block;
import com.famrut.farmer_management_api.entity.District;
import com.famrut.farmer_management_api.entity.SubDistrict;
import com.famrut.farmer_management_api.entity.Village;
import com.famrut.farmer_management_api.exception.BlockNotFoundException;
import com.famrut.farmer_management_api.exception.VillageNotFoundException;
import com.famrut.farmer_management_api.repository.BlockRepository;
import com.famrut.farmer_management_api.repository.VillageRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VillageService {

    private final VillageRepository villageRepository;
    private final BlockRepository blockRepository;

    public VillageService(
            VillageRepository villageRepository,
            BlockRepository blockRepository) {

        this.villageRepository = villageRepository;
        this.blockRepository = blockRepository;
    }

    public VillageResponse create(VillageRequest request) {

        Block block = blockRepository
                .findById(request.getBlockId())
                .orElseThrow(() ->
                        new BlockNotFoundException(request.getBlockId())
                );

        String code = request.getVillageCode().trim();
        String name = request.getVillageName().trim();

        if (villageRepository
                .existsByBlockIdAndVillageCode(
                        block.getId(),
                        code)) {

            throw new IllegalArgumentException(
                    "Village code already exists for this block"
            );
        }

        Village village = new Village();

        village.setVillageCode(code);
        village.setVillageName(name);
        village.setBlock(block);
        village.setActive(true);

        return toResponse(
                villageRepository.save(village)
        );
    }

    public VillageResponse getById(Long id) {

        Village village = villageRepository
                .findById(id)
                .orElseThrow(() ->
                        new VillageNotFoundException(id)
                );

        return toResponse(village);
    }

    public List<VillageResponse> getByBlock(Long blockId) {

        if (!blockRepository.existsById(blockId)) {
            throw new BlockNotFoundException(blockId);
        }

        return villageRepository
                .findAllByBlockIdAndActiveTrueOrderByVillageNameAsc(
                        blockId
                )
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public VillageResponse update(
            Long id,
            VillageRequest request) {

        Village village = villageRepository
                .findById(id)
                .orElseThrow(() ->
                        new VillageNotFoundException(id)
                );

        Block block = blockRepository
                .findById(request.getBlockId())
                .orElseThrow(() ->
                        new BlockNotFoundException(request.getBlockId())
                );

        String code = request.getVillageCode().trim();
        String name = request.getVillageName().trim();

        boolean changed =
                !village.getVillageCode().equals(code)
                ||
                !village.getBlock().getId().equals(block.getId());

        if (changed &&
                villageRepository.existsByBlockIdAndVillageCode(
                        block.getId(),
                        code)) {

            throw new IllegalArgumentException(
                    "Village code already exists for this block"
            );
        }

        village.setVillageCode(code);
        village.setVillageName(name);
        village.setBlock(block);

        return toResponse(
                villageRepository.save(village)
        );
    }

    public VillageResponse updateStatus(
            Long id,
            boolean active) {

        Village village = villageRepository
                .findById(id)
                .orElseThrow(() ->
                        new VillageNotFoundException(id)
                );

        village.setActive(active);

        return toResponse(
                villageRepository.save(village)
        );
    }

    private VillageResponse toResponse(Village village) {

        Block block = village.getBlock();
        SubDistrict subDistrict = block.getSubDistrict();
        District district = subDistrict.getDistrict();

        return new VillageResponse(
                village.getId(),
                village.getVillageCode(),
                village.getVillageName(),

                block.getId(),
                block.getBlockName(),

                subDistrict.getId(),
                subDistrict.getSubDistrictName(),

                district.getId(),
                district.getDistrictName(),

                district.getState().getId(),
                district.getState().getStateName(),

                village.isActive()
        );
    }
}