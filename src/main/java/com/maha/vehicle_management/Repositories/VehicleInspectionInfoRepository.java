package com.maha.vehicle_management.Repositories;

import com.maha.vehicle_management.Entities.VehicleInspectionInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleInspectionInfoRepository extends JpaRepository<VehicleInspectionInfo, String> {
    List<VehicleInspectionInfo> findALlByPlateNumberLike(final String plateNumber);
}
