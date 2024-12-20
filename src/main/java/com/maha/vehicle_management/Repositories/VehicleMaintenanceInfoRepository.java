package com.maha.vehicle_management.Repositories;

import com.maha.vehicle_management.Entities.Vehicle;
import com.maha.vehicle_management.Entities.VehicleInspectionInfo;
import com.maha.vehicle_management.Entities.VehicleMaintenanceInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface VehicleMaintenanceInfoRepository extends JpaRepository<VehicleMaintenanceInfo, Long> {
    List<VehicleMaintenanceInfo> findAllByPlateNumberLike(final String plateNumber);

    VehicleMaintenanceInfo findFirstById(long id);

    @Query("select vmi from VehicleMaintenanceInfo vmi where " +
            "(:start <= vmi.start and vmi.start <= :end) ORDER BY vmi.start")
    List<VehicleMaintenanceInfo> findStartBetween(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end);
    @Query("select vmi from VehicleMaintenanceInfo vmi where " +
            "(:start <= vmi.end and vmi.end <= :end) ORDER BY vmi.end")
    List<VehicleMaintenanceInfo> findEndBetween(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end);
}
