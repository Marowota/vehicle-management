package com.maha.vehicle_management.Repositories;

import com.maha.vehicle_management.Entities.VehicleUsageInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleUsageInfoRepository extends JpaRepository<VehicleUsageInfo, Long> {
}
