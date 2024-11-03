package com.maha.vehicle_management.Repositories;

import com.maha.vehicle_management.Entities.VehicleMaintenanceInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleMaintenanceInfoRepository extends JpaRepository<VehicleMaintenanceInfo, Long> {
}
