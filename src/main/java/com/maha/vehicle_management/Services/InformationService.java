package com.maha.vehicle_management.Services;

import com.maha.vehicle_management.DTO.*;
import com.maha.vehicle_management.Entities.VehicleInspectionInfo;
import com.maha.vehicle_management.Entities.VehicleMaintenanceInfo;
import com.maha.vehicle_management.Entities.VehicleRegisterInfo;
import com.maha.vehicle_management.Entities.VehicleUsageInfo;
import com.maha.vehicle_management.Models.MaintenanceSearchType;
import com.maha.vehicle_management.Models.enums.InspectionSearchType;
import com.maha.vehicle_management.Repositories.*;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

@Service
public class InformationService {
    private final VehicleMaintenanceInfoRepository vehicleMaintenanceInfoRepository;
    private final VehicleRegisterInfoRepository vehicleRegisterInfoRepository;
    private final VehicleInspectionInfoRepository vehicleInspectionInfoRepository;
    private final VehicleUsageInfoRepository vehicleUsageInfoRepository;
    private final VehicleRepository vehicleRepository;
    private final ModelMapper modelMapper;

    public InformationService(VehicleMaintenanceInfoRepository vehicleMaintenanceInfoRepository, VehicleRegisterInfoRepository vehicleRegisterInfoRepository, VehicleInspectionInfoRepository vehicleInspectionInfoRepository, VehicleUsageInfoRepository vehicleUsageInfoRepository, VehicleRepository vehicleRepository, ModelMapper modelMapper) {
        this.vehicleMaintenanceInfoRepository = vehicleMaintenanceInfoRepository;
        this.vehicleRegisterInfoRepository = vehicleRegisterInfoRepository;
        this.vehicleInspectionInfoRepository = vehicleInspectionInfoRepository;
        this.vehicleUsageInfoRepository = vehicleUsageInfoRepository;
        this.vehicleRepository = vehicleRepository;
        this.modelMapper = modelMapper;
    }

    public List<VehicleMaintenanceInfo> getVehiclesMaintenanceInfo(String query) {
        if (query == null) query = "";
        StringBuilder sb = new StringBuilder();
        sb.append("%").append(query).append("%");
        return vehicleMaintenanceInfoRepository.findAllByPlateNumberLike(sb.toString());
    }

    public List<VehicleMaintenanceInfo> getVehiclesMaintenanceInfo(LocalDateTime from, LocalDateTime to, MaintenanceSearchType type) {
        System.out.println("from: " + from);
        System.out.println("to: " + to);
        if (type == MaintenanceSearchType.START) {
            return vehicleMaintenanceInfoRepository.findStartBetween(from, to);
        }
        else {
            return vehicleMaintenanceInfoRepository.findEndBetween(from, to);
        }
    }

    public List<VehicleRegisterInfo> getVehiclesRegisterInfo(LocalDateTime from, LocalDateTime to) {
        System.out.println("from: " + from);
                System.out.println("to: " + to);
        return vehicleRegisterInfoRepository.findBetween(from, to);
    }

    public List<VehicleInspectionInfo> getVehicleInspectionInfo(String query, InspectionSearchType type) {
        if (query == null) query = "";
        StringBuilder sb = new StringBuilder();
        sb.append("%").append(query).append("%");
        if (type == InspectionSearchType.PLATE_NUMBER) {
            return vehicleInspectionInfoRepository.findALlByPlateNumberLike(sb.toString());
        }
        else {
            return vehicleInspectionInfoRepository.findAllByInspectionNoLike(sb.toString());
        }
    }

    public List<VehicleInspectionInfo> getVehicleInspectionInfo(LocalDateTime from, LocalDateTime to, InspectionSearchType type) {
        System.out.println("from: " + from);
        System.out.println("to: " + to);
        if (type == InspectionSearchType.REGISTRATION_DATE) {
            return vehicleInspectionInfoRepository.findRegDateBetween(from, to);
        }
        else {
            return vehicleInspectionInfoRepository.findValidUntilBetween(from, to);
        }
    }

    public List<VehicleUsageInfo> getVehicleUsageInfo(String query) {
        if (query == null) query = "";
        StringBuilder sb = new StringBuilder();
        sb.append("%").append(query).append("%");
        return vehicleUsageInfoRepository.findAllByPlateNumberLike(sb.toString());
    }

    public VehicleInspectionInfo getVehicleInspectionInfoById(String id) {
        return vehicleInspectionInfoRepository.findFirstByInspectionNo(id);
    }

    public VehicleMaintenanceInfo getVehicleMaintenanceInfoById(long id) {
        return vehicleMaintenanceInfoRepository.findFirstById(id);
    }


    public DashboardDTO getDashboardInfo(){
        DashboardDTO result = new DashboardDTO();
        result.setNumberOfVehicles(vehicleRepository.countVehicles());
        result.setUsingVehiclesDetail(vehicleRegisterInfoRepository.findUsing(LocalDateTime.now()).stream().map((v) -> modelMapper.map(v, VehicleRegisterInfoDTO.class)).toList());
        result.setUsingVehicles(result.getUsingVehiclesDetail().size());
        result.setInspectedVehicles(vehicleInspectionInfoRepository.countInspected(LocalDateTime.now()).size());
        long totalYearCost = vehicleInspectionInfoRepository.totalCost(LocalDateTime.now().with(TemporalAdjusters.firstDayOfYear()),
                LocalDateTime.now().with(TemporalAdjusters.firstDayOfNextYear()).minusSeconds(1));
        result.setTotalYearCost(totalYearCost);

        result.setCostPerMonth(new ArrayList<Double>());
        for (int i = 0; i < 12; i++){
            result.getCostPerMonth().add(0.0);
        }
        List<MonthlyCostDTO> inspectionCost = vehicleInspectionInfoRepository.costByMonthThisYear();
        inspectionCost.forEach((value) -> {
            result.getCostPerMonth().set(value.getMonth() - 1, value.getCost());
        });

        return result;
    }

}
