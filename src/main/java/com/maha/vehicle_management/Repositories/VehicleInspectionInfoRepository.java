package com.maha.vehicle_management.Repositories;

import com.maha.vehicle_management.DTO.MonthlyCostDTO;
import com.maha.vehicle_management.Entities.VehicleInspectionInfo;
import com.maha.vehicle_management.Entities.VehicleRegisterInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface VehicleInspectionInfoRepository extends JpaRepository<VehicleInspectionInfo, String> {
    List<VehicleInspectionInfo> findALlByPlateNumberLike(final String plateNumber);

    List<VehicleInspectionInfo> findAllByInspectionNoLike(final String inspectionNo);

    @Query("select vii from VehicleInspectionInfo vii where " +
            "(:start <= vii.registrationDate and vii.registrationDate <= :end) ORDER BY vii.registrationDate")
    List<VehicleInspectionInfo> findRegDateBetween(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end);
    @Query("select vii from VehicleInspectionInfo vii where " +
            "(:start <= vii.validUntil and vii.validUntil <= :end) ORDER BY vii.validUntil")
    List<VehicleInspectionInfo> findValidUntilBetween(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end);

    void removeByInspectionNo(final String inspectionNo);

    VehicleInspectionInfo findFirstByInspectionNo(final String inspectionNo);

    @Query("select count(isp.plateNumber) from VehicleInspectionInfo as isp where isp.registrationDate <= :current and :current <= isp.validUntil group by isp.plateNumber")
    List<Long> countInspected(LocalDateTime current);

    @Query("select sum(vii.cost) from VehicleInspectionInfo as vii where :front <= vii.registrationDate and vii.registrationDate <= :back")
    long totalCost(LocalDateTime front, LocalDateTime back);;



    @Query("SELECT new com.maha.vehicle_management.DTO.MonthlyCostDTO(month( vii.registrationDate), SUM(vii.cost)) " +
            "FROM VehicleInspectionInfo as vii " +
            "WHERE year(vii.registrationDate) = year(CURRENT_DATE)  " +
            "GROUP BY month(vii.registrationDate) ")
    List<MonthlyCostDTO> costByMonthThisYear();
}
