package com.maha.vehicle_management.Repositories;

import com.maha.vehicle_management.Entities.VehicleRegisterInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;


@Repository
public interface VehicleRegisterInfoRepository extends JpaRepository<VehicleRegisterInfo, Long> {
    @Query("select vri from VehicleRegisterInfo vri where vri.plateNumber = :#{#info.plateNumber} and " +
            "((vri.start <= :#{#info.start} and :#{#info.start} <= vri.end) or " +
            "(vri.start <= :#{#info.end} and :#{#info.end} <= vri.end) or" +
            "(:#{#info.start} <= vri.start and vri.end <= :#{#info.end}  ) ) ORDER BY vri.start DESC LIMIT 1")
    VehicleRegisterInfo findUsageInTime(VehicleRegisterInfo info);

    @Query("select vri from VehicleRegisterInfo vri where vri.plateNumber = :#{#info.plateNumber} and " +
            "vri.id <> :#{#info.id} and" +
            "((vri.start <= :#{#info.start} and :#{#info.start} <= vri.end) or " +
            "(vri.start <= :#{#info.end} and :#{#info.end} <= vri.end) or" +
            "(:#{#info.start} <= vri.start and vri.end <= :#{#info.end}  ) ) ORDER BY vri.start DESC LIMIT 1")
    VehicleRegisterInfo findUsageInTimeAndId(VehicleRegisterInfo info);
    @Query("select vri from VehicleRegisterInfo vri where " +
            "((vri.start <= :start and :start <= vri.end) or " +
            "vri.start <= :end and :end <= vri.end or" +
            "(:start <= vri.start and vri.end <= :end)) ORDER BY vri.start")
    List<VehicleRegisterInfo> findBetween(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end);

    VehicleRegisterInfo findOneById(long id);
    List<VehicleRegisterInfo> findAllByPlateNumberLike(final String plateNumber);


    @Query("select vri from VehicleRegisterInfo as vri where vri.start <= :now and :now <= vri.end")
    List<VehicleRegisterInfo> findUsing(LocalDateTime now);
}
