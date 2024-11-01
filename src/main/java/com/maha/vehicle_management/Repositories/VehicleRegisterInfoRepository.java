package com.maha.vehicle_management.Repositories;

import com.maha.vehicle_management.Entities.VehicleRegisterInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VehicleRegisterInfoRepository extends JpaRepository<VehicleRegisterInfo, Long> {
    @Query("select vri from VehicleRegisterInfo vri where vri.plateNumber = :#{#info.plateNumber} and " +
            "(vri.start <= :#{#info.start} and :#{#info.start} <= vri.end) or " +
            "(vri.start <= :#{#info.end} and :#{#info.end} <= vri.end) ORDER BY vri.start DESC LIMIT 1")
    VehicleRegisterInfo findUsageInTime(VehicleRegisterInfo info);
}
