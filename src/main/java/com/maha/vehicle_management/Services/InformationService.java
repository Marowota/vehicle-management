package com.maha.vehicle_management.Services;

import com.maha.vehicle_management.Entities.VehicleInspectionInfo;
import com.maha.vehicle_management.Entities.VehicleMaintenanceInfo;
import com.maha.vehicle_management.Entities.VehicleRegisterInfo;
import com.maha.vehicle_management.Entities.VehicleUsageInfo;
import com.maha.vehicle_management.Repositories.VehicleInspectionInfoRepository;
import com.maha.vehicle_management.Repositories.VehicleMaintenanceInfoRepository;
import com.maha.vehicle_management.Repositories.VehicleRegisterInfoRepository;
import com.maha.vehicle_management.Repositories.VehicleUsageInfoRepository;
import org.springframework.stereotype.Service;

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

    public List<VehicleRegisterInfo> getVehiclesRegisterInfo(String query) {
        if (query == null) query = "";
        StringBuilder sb = new StringBuilder();
        sb.append("%").append(query).append("%");
        return vehicleRegisterInfoRepository.findAllByPlateNumberLike(sb.toString());
    }

    public List<VehicleInspectionInfo> getVehicleInspectionInfo(String query) {
        if (query == null) query = "";
        StringBuilder sb = new StringBuilder();
        sb.append("%").append(query).append("%");
        return vehicleInspectionInfoRepository.findALlByPlateNumberLike(sb.toString());
    }

    public List<VehicleUsageInfo> getVehicleUsageInfo(String query) {
        if (query == null) query = "";
        StringBuilder sb = new StringBuilder();
        sb.append("%").append(query).append("%");
        return vehicleUsageInfoRepository.findAllByPlateNumberLike(sb.toString());
    }


}
