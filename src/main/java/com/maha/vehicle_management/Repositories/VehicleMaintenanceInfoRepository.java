package com.maha.vehicle_management.Repositories;

import com.maha.vehicle_management.Entities.Vehicle;
import com.maha.vehicle_management.Entities.VehicleMaintenanceInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleMaintenanceInfoRepository extends JpaRepository<VehicleMaintenanceInfo, Long> {
    List<VehicleMaintenanceInfo> findAllByPlateNumberLike(final String plateNumber);
}
