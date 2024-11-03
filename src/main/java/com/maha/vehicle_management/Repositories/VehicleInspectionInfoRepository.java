package com.maha.vehicle_management.Repositories;

import com.maha.vehicle_management.Entities.VehicleInspectionInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleInspectionInfoRepository extends JpaRepository<VehicleInspectionInfo, String> {
}
