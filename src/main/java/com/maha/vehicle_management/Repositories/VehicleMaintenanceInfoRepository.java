package com.maha.vehicle_management.Repositories;

import com.maha.vehicle_management.Entities.VehicleMaintenanceInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleMaintenanceInfoRepository extends JpaRepository<VehicleMaintenanceInfo, Long> {
}
