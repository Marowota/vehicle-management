package com.maha.vehicle_management.Repositories;

import com.maha.vehicle_management.Entities.VehicleUsageInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleUsageInfoRepository extends JpaRepository<VehicleUsageInfo, Long> {
}
