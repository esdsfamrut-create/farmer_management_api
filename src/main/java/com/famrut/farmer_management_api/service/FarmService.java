package com.famrut.farmer_management_api.service;
import org.springframework.stereotype.Service;
import com.famrut.farmer_management_api.dto.FarmRequest;
import com.famrut.farmer_management_api.dto.FarmResponse;
import com.famrut.farmer_management_api.entity.Farm;
import com.famrut.farmer_management_api.mapper.FarmMapper;
import com.famrut.farmer_management_api.repository.FarmRepository;
import java.util.List;
import java.util.stream.Collectors;
import com.famrut.farmer_management_api.mapper.FarmerMapper;
import com.famrut.farmer_management_api.repository.VillageRepository;
import com.famrut.farmer_management_api.entity.Farmer;
import com.famrut.farmer_management_api.repository.FarmerRepository;
import com.famrut.farmer_management_api.exception.FarmerNotFoundException;



@Service
public class FarmService {
    
    private final FarmMapper farmMapper;
    private final FarmRepository farmRepository;
    private final FarmerRepository farmerRepository;

   public FarmService(
        FarmMapper farmMapper,
        FarmRepository farmRepository,
        FarmerRepository farmerRepository) {

    this.farmMapper = farmMapper;
    this.farmRepository = farmRepository;
    this.farmerRepository = farmerRepository;
}


    public FarmResponse getFarmById(Long id, Long farmerId) {
        
        Farm farm = farmRepository.findByIdAndFarmerId(id, farmerId)
                .orElseThrow(() -> new RuntimeException("Farm not found with id: " + id));
        return farmMapper.toFarmResponse(farm);
    }

    public FarmResponse createFarm(
                Long farmerId,
                FarmRequest farmRequest) {

            Farmer farmer = farmerRepository
                    .findById(farmerId)
                    .orElseThrow(() ->
                            new FarmerNotFoundException(farmerId)
                    );

            Farm farm = new Farm();

            farm.setFarmName(farmRequest.getFarmName());
            farm.setArea(farmRequest.getArea());
            farm.setAreaUnit(farmRequest.getAreaUnit());
            farm.setPolygon(farmRequest.getPolygon());

            farm.setFarmer(farmer);
            farm.setActive(true);

            Farm savedFarm =
                    farmRepository.save(farm);

            return farmMapper.toFarmResponse(savedFarm);
        }

    public FarmResponse updateFarm(Long farmId,Long farmerId, FarmRequest farmRequest) {
        Farm farm = farmRepository.findByIdAndFarmerId(farmId, farmerId)
                .orElseThrow(() -> new RuntimeException("Farm not found with id: " + farmId));

        farm.setFarmName(farmRequest.getFarmName());
        farm.setArea(farmRequest.getArea());
        farm.setAreaUnit(farmRequest.getAreaUnit());
        farm.setPolygon(farmRequest.getPolygon());

        Farm updatedFarm = farmRepository.save(farm);
        return farmMapper.toFarmResponse(updatedFarm);
    }

    public void deleteFarm(Long farmId,Long farmerId) {
        Farm farm = farmRepository.findByIdAndFarmerId(farmId, farmerId)
                .orElseThrow(() -> new RuntimeException("Farm not found with id: " + farmId));
       // farmRepository.delete(farm);
            farm.setActive(false);
            farmRepository.save(farm);
    }

    public List<FarmResponse> getAllFarms(Long farmerId) {
        List<Farm> farms = farmRepository.findAllByFarmerIdAndActiveTrueOrderByFarmNameAsc(farmerId);
        return farms.stream()
            .map(farm -> farmMapper.toFarmResponse(farm))
            .collect(Collectors.toList());
    }
}