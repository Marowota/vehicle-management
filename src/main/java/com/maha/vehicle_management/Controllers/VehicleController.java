package com.maha.vehicle_management.Controllers;

import com.maha.vehicle_management.DTO.*;
import com.maha.vehicle_management.Entities.*;
import com.maha.vehicle_management.Models.enums.RegisterResult;
import com.maha.vehicle_management.Services.VehicleService;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("vehicles")
public class VehicleController {

    private final VehicleService vehicleService;
    private final ModelMapper modelMapper;

    VehicleController(VehicleService vehicleService, ModelMapper modelMapper) {
        this.vehicleService = vehicleService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public List<Vehicle> getVehicleList(@RequestParam(name = "query", required = false) String query) {
        if (query == null) query = "";
        return vehicleService.search(query);
    }

    @GetMapping("/{id}")
    public Vehicle getVehicle(@PathVariable("id") String plateNumber) {
        return vehicleService.get(plateNumber);
    }

    @PostMapping
    public void addVehicle(@RequestBody VehicleDTO vehicleDTO) {
        ModelMapper modelMapper = new ModelMapper();
        vehicleService.add(vehicleDTO.getPlateNumber(),
                            modelMapper.map(vehicleDTO.getVehicleSpec(), VehicleSpec.class),
                            vehicleDTO.getCost(),
                            vehicleDTO.getHealth());

    }

    @PutMapping
    public void updateVehicle(@RequestBody VehicleDTO vehicleDTO) {
        ModelMapper modelMapper = new ModelMapper();
        vehicleService.edit(vehicleDTO.getPlateNumber(),
                            modelMapper.map(vehicleDTO.getVehicleSpec(), VehicleSpec.class),
                            vehicleDTO.getCost(),
                            vehicleDTO.getHealth());
    }

    @DeleteMapping("/{id}")
    public void deleteVehicle(@PathVariable("id") String plateNumber) {
        vehicleService.remove(plateNumber);
    }

    @PostMapping("/{id}/register")
    public RegisterResult registerVehicle(@PathVariable("id") String plateNumber,
                                          @RequestBody VehicleRegisterInfoDTO registerInfo) {
        return vehicleService.register(modelMapper.map(registerInfo, VehicleRegisterInfo.class));
    }

    @PostMapping("/{id}/set-inspection")
    public void setInspectionInfo(@PathVariable("id") String plateNumber,
                         @RequestBody VehicleInspectionInfoDTO inspectionInfo) {
        vehicleService.inspect(plateNumber, modelMapper.map(inspectionInfo, VehicleInspectionInfo.class));
    }

    @PostMapping("/{id}/log-usage")
    public void logUsage(@PathVariable("id") String plateNumber, @RequestBody VehicleUsageInfoDTO usageInfo){
        vehicleService.logUsage(plateNumber, usageInfo.getFuelUsed());
    }

    @PostMapping("/{id}/maintenance")
    public void logVehicleMaintenance(@PathVariable("id") String plateNumber,
                                      @RequestBody VehicleMaintenanceInfoDTO maintenanceInfoDTO){
        vehicleService.maintenance(modelMapper.map(maintenanceInfoDTO, VehicleMaintenanceInfo.class));
    }

    @PutMapping("/{id}/register")
    public RegisterResult editRegisterVehicle(@PathVariable("id") String plateNumber,
                                          @RequestBody VehicleRegisterInfoDTO registerInfo) {
        return vehicleService.editRegister(modelMapper.map(registerInfo, VehicleRegisterInfo.class));
    }

    @PutMapping("/{id}/set-inspection")
    public void editSetInspectionInfo(@PathVariable("id") String plateNumber,
                                  @RequestBody VehicleInspectionInfoDTO inspectionInfo) {
        vehicleService.editInspect(plateNumber, modelMapper.map(inspectionInfo, VehicleInspectionInfo.class));
    }

    @PutMapping("/{id}/log-usage")
    public void editLogUsage(@PathVariable("id") String plateNumber, @RequestBody VehicleUsageInfoDTO usageInfo){
        vehicleService.editLogUsage(plateNumber, modelMapper.map(usageInfo, VehicleUsageInfo.class));
    }

    @PutMapping("/{id}/maintenance")
    public void editLogVehicleMaintenance(@PathVariable("id") String plateNumber,
                                      @RequestBody VehicleMaintenanceInfoDTO maintenanceInfoDTO){
        vehicleService.editMaintenance(modelMapper.map(maintenanceInfoDTO, VehicleMaintenanceInfo.class));
    }

}
