package com.maha.vehicle_management.Services;

import com.maha.vehicle_management.DTO.VehicleDTO;
import com.maha.vehicle_management.DTO.VehicleSpecDTO;
import com.maha.vehicle_management.Entities.*;
import com.maha.vehicle_management.Models.enums.RegisterResult;
import com.maha.vehicle_management.Repositories.*;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VehicleService {
    private final AccountRepository accountRepository;
    private final VehicleRepository vehicleRepository;
    private final VehicleSpecRepository vehicleSpecRepository;
    private final VehicleRegisterInfoRepository vehicleRegisterInfoRepository;
    private final VehicleUsageInfoRepository vehicleUsageInfoRepository;
    private final VehicleInspectionInfoRepository vehicleInspectionInfoRepository;
    private final VehicleMaintenanceInfoRepository vehicleMaintenanceInfoRepository;
    private final ModelMapper modelMapper;

    public VehicleService(AccountRepository accountRepository,
                          VehicleRepository vehicleRepository,
                          VehicleSpecRepository vehicleSpecRepository,
                          VehicleRegisterInfoRepository vehicleRegisterInfoRepository,
                          VehicleUsageInfoRepository vehicleUsageInfoRepository,
                          VehicleInspectionInfoRepository vehicleInspectionInfoRepository,
                          VehicleMaintenanceInfoRepository vehicleMaintenanceInfoRepository, ModelMapper modelMapper) {
        this.accountRepository = accountRepository;
        this.vehicleRepository = vehicleRepository;
        this.vehicleSpecRepository = vehicleSpecRepository;
        this.vehicleRegisterInfoRepository = vehicleRegisterInfoRepository;
        this.vehicleUsageInfoRepository = vehicleUsageInfoRepository;
        this.vehicleInspectionInfoRepository = vehicleInspectionInfoRepository;
        this.vehicleMaintenanceInfoRepository = vehicleMaintenanceInfoRepository;
        this.modelMapper = modelMapper;
    }

    public List<Vehicle> get(){
        List<Vehicle> vehicles = vehicleRepository.findAll();
        return vehicles;
    }

    public VehicleDTO get(String plateNumber){
        Vehicle result = vehicleRepository.findOneByPlateNumber(plateNumber);
        VehicleSpec spec =vehicleSpecRepository.findOneById(result.getSpecId());
        VehicleDTO dto = modelMapper.map(result, VehicleDTO.class);
        dto.setVehicleSpec(modelMapper.map(spec, VehicleSpecDTO.class));
        return dto;

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
        vehicle.setIsRemoved(true);
        vehicleRepository.save(vehicle);
    }

    public List<VehicleDTO> search(String query){
        StringBuilder sb = new StringBuilder();
        sb.append("%").append(query).append("%");
        System.out.println(sb.toString());
        List<Vehicle> vehicles = vehicleRepository.findAllByPlateNumberLikeAndIsRemovedEquals(sb.toString(), false);

        List<VehicleDTO> vehicleDTOs = new ArrayList<>();
        for (int i = 0; i < vehicles.size(); i++){
            VehicleDTO tmp = modelMapper.map(vehicles.get(i), VehicleDTO.class);
            tmp.setVehicleSpec(modelMapper.map(vehicleSpecRepository.findOneById(vehicles.get(i).getSpecId()), VehicleSpecDTO.class));
            vehicleDTOs.add(tmp);
        }
        return vehicleDTOs;
    }

    public RegisterResult register(VehicleRegisterInfo registerInfo){

        VehicleRegisterInfo exist = vehicleRegisterInfoRepository.findUsageInTime(registerInfo);
        if (exist != null){
            return RegisterResult.DATE_COLLISION;
        }

        vehicleRegisterInfoRepository.save(registerInfo);
        return RegisterResult.ACCEPTED;
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

    public RegisterResult editRegister(VehicleRegisterInfo registerInfo){
        VehicleRegisterInfo exist = vehicleRegisterInfoRepository.findUsageInTime(registerInfo);
        if (exist != null){
            return RegisterResult.DATE_COLLISION;
        }
//
//        Account teacherAccount = accountRepository.findOneById(registerInfo.getTeacherId());
//        if (teacherAccount == null){
//            return RegisterResult.NO_TEACHER;
//        }

        vehicleRegisterInfoRepository.save(registerInfo);
        return RegisterResult.ACCEPTED;
    }

    public void editLogUsage(String plateNumber, VehicleUsageInfo usageInfo){
        vehicleUsageInfoRepository.save(usageInfo);
    }

    public void editInspect(String plateNumber, VehicleInspectionInfo inspectionInfo){
        Vehicle vehicle = vehicleRepository.findOneByPlateNumber(plateNumber);
        if (vehicle == null) return;
        VehicleInspectionInfo savedInfo =  vehicleInspectionInfoRepository.save(inspectionInfo);
        vehicle.setCurrentInspectId(savedInfo.getInspectionNo());
        vehicleRepository.save(vehicle);
    }

    public void editMaintenance(VehicleMaintenanceInfo maintenanceInfo){
        vehicleMaintenanceInfoRepository.save(maintenanceInfo);
    }


}
