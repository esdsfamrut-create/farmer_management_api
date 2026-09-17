package com.famrut.farmer_management_api.repository;
import com.famrut.farmer_management_api.entity.Crop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CropRepository extends JpaRepository<Crop, Long> {

    boolean existsByCropNameAndCropVariety(
            String cropName,
            String cropVariety
    );

    List<Crop> findAllByActiveTrueOrderByCropNameAsc();
    

}