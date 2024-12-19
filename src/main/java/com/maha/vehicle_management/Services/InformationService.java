package com.maha.vehicle_management.Services;

import com.maha.vehicle_management.Entities.VehicleInspectionInfo;
import com.maha.vehicle_management.Entities.VehicleMaintenanceInfo;
import com.maha.vehicle_management.Entities.VehicleRegisterInfo;
import com.maha.vehicle_management.Entities.VehicleUsageInfo;
import com.maha.vehicle_management.Models.enums.InspectionSearchType;
import com.maha.vehicle_management.Repositories.VehicleInspectionInfoRepository;
import com.maha.vehicle_management.Repositories.VehicleMaintenanceInfoRepository;
import com.maha.vehicle_management.Repositories.VehicleRegisterInfoRepository;
import com.maha.vehicle_management.Repositories.VehicleUsageInfoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class InformationService {
    private final VehicleMaintenanceInfoRepository vehicleMaintenanceInfoRepository;
    private final VehicleRegisterInfoRepository vehicleRegisterInfoRepository;
    private final VehicleInspectionInfoRepository vehicleInspectionInfoRepository;
    private final VehicleUsageInfoRepository vehicleUsageInfoRepository;

    public InformationService(VehicleMaintenanceInfoRepository vehicleMaintenanceInfoRepository, VehicleRegisterInfoRepository vehicleRegisterInfoRepository, VehicleInspectionInfoRepository vehicleInspectionInfoRepository, VehicleUsageInfoRepository vehicleUsageInfoRepository) {
        this.vehicleMaintenanceInfoRepository = vehicleMaintenanceInfoRepository;
        this.vehicleRegisterInfoRepository = vehicleRegisterInfoRepository;
        this.vehicleInspectionInfoRepository = vehicleInspectionInfoRepository;
        this.vehicleUsageInfoRepository = vehicleUsageInfoRepository;
    }

    public List<VehicleMaintenanceInfo> getVehiclesMaintenanceInfo(String query) {
        if (query == null) query = "";
        StringBuilder sb = new StringBuilder();
        sb.append("%").append(query).append("%");
        return vehicleMaintenanceInfoRepository.findAllByPlateNumberLike(sb.toString());
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


}
