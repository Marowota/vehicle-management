package com.maha.vehicle_management.Services;

import com.maha.vehicle_management.Entities.*;
import com.maha.vehicle_management.Models.enums.RegisterResult;
import com.maha.vehicle_management.Repositories.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleService {
    private final AccountRepository accountRepository;
    private final VehicleRepository vehicleRepository;
    private final VehicleSpecRepository vehicleSpecRepository;
    private final VehicleRegisterInfoRepository vehicleRegisterInfoRepository;
    private final VehicleUsageInfoRepository vehicleUsageInfoRepository;
    private final VehicleInspectionInfoRepository vehicleInspectionInfoRepository;
    private final VehicleMaintenanceInfoRepository vehicleMaintenanceInfoRepository;

    public VehicleService(AccountRepository accountRepository,
                          VehicleRepository vehicleRepository,
                          VehicleSpecRepository vehicleSpecRepository,
                          VehicleRegisterInfoRepository vehicleRegisterInfoRepository,
                          VehicleUsageInfoRepository vehicleUsageInfoRepository,
                          VehicleInspectionInfoRepository vehicleInspectionInfoRepository,
                          VehicleMaintenanceInfoRepository vehicleMaintenanceInfoRepository) {
        this.accountRepository = accountRepository;
        this.vehicleRepository = vehicleRepository;
        this.vehicleSpecRepository = vehicleSpecRepository;
        this.vehicleRegisterInfoRepository = vehicleRegisterInfoRepository;
        this.vehicleUsageInfoRepository = vehicleUsageInfoRepository;
        this.vehicleInspectionInfoRepository = vehicleInspectionInfoRepository;
        this.vehicleMaintenanceInfoRepository = vehicleMaintenanceInfoRepository;
    }

    public void add(String plateNumber, VehicleSpec spec, Double cost, String health){
        VehicleSpec vehicleSpec = vehicleSpecRepository.save(spec);
        Vehicle vehicle = new Vehicle(plateNumber, vehicleSpec.getId(), cost, health);
        vehicleRepository.save(vehicle);
    }

    public void edit(String plateNumber, VehicleSpec spec, Double cost, String health){
        Vehicle vehicle = vehicleRepository.findOneByPlateNumber(plateNumber);
        if (vehicle == null) return;
        VehicleSpec vehicleSpec = vehicleSpecRepository.findOneById(spec.getId());
        if (vehicleSpec != null) {
            spec.setId(vehicleSpec.getId());
        }
        vehicleSpec = vehicleSpecRepository.save(spec);
        vehicle.setSpecId(vehicleSpec.getId());
        vehicle.setCost(cost);
        vehicle.setHealth(health);
        vehicleRepository.save(vehicle);
    }

    public void remove(String plateNumber){
        Vehicle vehicle = vehicleRepository.findOneByPlateNumber(plateNumber);
        if (vehicle == null) return;
        vehicle.setRemoved(true);
        vehicleRepository.save(vehicle);
    }

    public List<Vehicle> search(String plateNumber){
        List<Vehicle> vehicles = vehicleRepository.findByPlateNumberLike(plateNumber);
        return vehicles;
    }

    public RegisterResult register(VehicleRegisterInfo registerInfo){

        VehicleRegisterInfo exist = vehicleRegisterInfoRepository.findUsageInTime(registerInfo);
        if (exist != null){
            return RegisterResult.DATE_COLLISION;
        }
        Account teacherAccount = accountRepository.findOneById(registerInfo.getTeacherId());
        if (teacherAccount == null){
            return RegisterResult.NO_TEACHER;
        }
        vehicleRegisterInfoRepository.save(registerInfo);
        return null;
    }

    public void logUsage(String plateNumber, double fuelUsed){
        VehicleUsageInfo usageInfo = new VehicleUsageInfo(plateNumber, fuelUsed);
        vehicleUsageInfoRepository.save(usageInfo);
    }

    public void inspect(String plateNumber, VehicleInspectionInfo inspectionInfo){
        Vehicle vehicle = vehicleRepository.findOneByPlateNumber(plateNumber);
        if (vehicle == null) return;
        VehicleInspectionInfo savedInfo =  vehicleInspectionInfoRepository.save(inspectionInfo);
        vehicle.setCurrentInspectId(savedInfo.getInspectionNo());
        vehicleRepository.save(vehicle);
    }
    public void maintenance(VehicleMaintenanceInfo maintenanceInfo){
        vehicleMaintenanceInfoRepository.save(maintenanceInfo);
    }
}
