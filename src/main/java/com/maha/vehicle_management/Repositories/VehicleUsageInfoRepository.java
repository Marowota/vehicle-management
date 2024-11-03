package com.maha.vehicle_management.Repositories;

import com.maha.vehicle_management.Entities.VehicleRegisterInfo;
import com.maha.vehicle_management.Entities.VehicleUsageInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleUsageInfoRepository extends JpaRepository<VehicleUsageInfo, Long> {
    List<VehicleUsageInfo> findAllByPlateNumberLike(final String plateNumber);
}
