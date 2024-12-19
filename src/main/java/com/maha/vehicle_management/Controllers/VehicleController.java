package com.maha.vehicle_management.Controllers;

import com.maha.vehicle_management.DTO.*;
import com.maha.vehicle_management.Entities.*;
import com.maha.vehicle_management.Models.enums.InspectionResult;
import com.maha.vehicle_management.Models.enums.RegisterResult;
import com.maha.vehicle_management.Models.enums.RequestResult;
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
    public List<VehicleDTO> getVehicleList(@RequestParam(name = "query", required = false) String query) {
        if (query == null) query = "";
        return vehicleService.search(query);
    }

    @GetMapping("/{id}")
    public VehicleDTO getVehicle(@PathVariable("id") String plateNumber) {
        return vehicleService.get(plateNumber);
    }

    @PostMapping
    public RequestResult addVehicle(@RequestBody VehicleDTO vehicleDTO) {
        vehicleService.add(vehicleDTO.getPlateNumber(),
                            modelMapper.map(vehicleDTO.getVehicleSpec(), VehicleSpec.class),
                            vehicleDTO.getCost(),
                            vehicleDTO.getHealth());
        return RequestResult.SUCCESS;
    }

    @PutMapping
    public RequestResult updateVehicle(@RequestBody VehicleDTO vehicleDTO) {
        vehicleService.edit(vehicleDTO.getPlateNumber(),
                            modelMapper.map(vehicleDTO.getVehicleSpec(), VehicleSpec.class),
                            vehicleDTO.getCost(),
                            vehicleDTO.getHealth());
        return RequestResult.SUCCESS;
    }

    @DeleteMapping("/{id}")
    public RequestResult deleteVehicle(@PathVariable("id") String plateNumber) {
        vehicleService.remove(plateNumber);
        return RequestResult.SUCCESS;
    }


    @GetMapping("/{id}/register/{regId}")
    public VehicleRegisterInfoDTO getRegisterVehicle(@PathVariable("id") String plateNumber,
                                          @PathVariable("regId") long regId) {
        return vehicleService.getRegister(regId);
    }

    @PostMapping("/{id}/register")
    public RegisterResult registerVehicle(@PathVariable("id") String plateNumber,
                                          @RequestBody VehicleRegisterInfoDTO registerInfo) {
        return vehicleService.register(modelMapper.map(registerInfo, VehicleRegisterInfo.class));
    }

    @PostMapping("/{id}/inspection")
    public InspectionResult setInspectionInfo(@PathVariable("id") String plateNumber,
                                              @RequestBody VehicleInspectionInfoDTO inspectionInfo) {
        return vehicleService.inspect(plateNumber, modelMapper.map(inspectionInfo, VehicleInspectionInfo.class));
    }

    @PostMapping("/{id}/log-usage")
    public RequestResult logUsage(@PathVariable("id") String plateNumber, @RequestBody VehicleUsageInfoDTO usageInfo){
        vehicleService.logUsage(plateNumber, usageInfo.getFuelUsed());
        return RequestResult.SUCCESS;
    }

    @PostMapping("/{id}/maintenance")
    public RequestResult logVehicleMaintenance(@PathVariable("id") String plateNumber,
                                      @RequestBody VehicleMaintenanceInfoDTO maintenanceInfoDTO){
        vehicleService.maintenance(modelMapper.map(maintenanceInfoDTO, VehicleMaintenanceInfo.class));
        return RequestResult.SUCCESS;
    }

    @PutMapping("/{id}/register")
    public RegisterResult editRegisterVehicle(@PathVariable("id") String plateNumber,
                                          @RequestBody VehicleRegisterInfoDTO registerInfo) {
        return vehicleService.editRegister(modelMapper.map(registerInfo, VehicleRegisterInfo.class));
    }

    @PutMapping("/{id}/inspection")
    public RequestResult editSetInspectionInfo(@PathVariable("id") String plateNumber,
                                  @RequestBody VehicleInspectionInfoDTO inspectionInfo) {
        vehicleService.editInspect(plateNumber, modelMapper.map(inspectionInfo, VehicleInspectionInfo.class));
        return RequestResult.SUCCESS;
    }

    @PutMapping("/{id}/log-usage")
    public RequestResult editLogUsage(@PathVariable("id") String plateNumber, @RequestBody VehicleUsageInfoDTO usageInfo){
        vehicleService.editLogUsage(plateNumber, modelMapper.map(usageInfo, VehicleUsageInfo.class));
        return RequestResult.SUCCESS;
    }

    @PutMapping("/{id}/maintenance")
    public RequestResult editLogVehicleMaintenance(@PathVariable("id") String plateNumber,
                                      @RequestBody VehicleMaintenanceInfoDTO maintenanceInfoDTO){
        vehicleService.editMaintenance(modelMapper.map(maintenanceInfoDTO, VehicleMaintenanceInfo.class));
        return RequestResult.SUCCESS;
    }

    @DeleteMapping("/{id}/inspection")
    public String deleteInspection(@PathVariable("id") String plateNumber, @RequestParam("id") String inspectionNo){
        return vehicleService.deleteInspection(inspectionNo);
    }

    @DeleteMapping("/{id}/register")
    public String deleteRegistration(@PathVariable("id") String plateNumber, @RequestParam("id") Long id){
        return vehicleService.deleteRegistration(id);
    }

}
