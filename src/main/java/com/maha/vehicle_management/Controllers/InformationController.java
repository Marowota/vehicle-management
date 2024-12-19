package com.maha.vehicle_management.Controllers;

import com.maha.vehicle_management.DTO.VehicleInspectionInfoDTO;
import com.maha.vehicle_management.DTO.VehicleMaintenanceInfoDTO;
import com.maha.vehicle_management.DTO.VehicleRegisterInfoDTO;
import com.maha.vehicle_management.DTO.VehicleUsageInfoDTO;
import com.maha.vehicle_management.Entities.VehicleInspectionInfo;
import com.maha.vehicle_management.Entities.VehicleMaintenanceInfo;
import com.maha.vehicle_management.Entities.VehicleRegisterInfo;
import com.maha.vehicle_management.Entities.VehicleUsageInfo;
import com.maha.vehicle_management.Models.enums.InspectionSearchType;
import com.maha.vehicle_management.Services.InformationService;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
public class InformationController {
    private final InformationService informationService;
    private final ModelMapper modelMapper;

    public InformationController(InformationService informationService, ModelMapper modelMapper) {
        this.informationService = informationService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/inspection-info/{id}")
    public VehicleInspectionInfo getInspectionInfoById(@PathVariable String id) {
        return informationService.getVehicleInspectionInfoById(id);
    }

    @GetMapping("/inspection-info")
    public List<VehicleInspectionInfoDTO> getInspectionInfo(@RequestParam(name = "from", required = false) String stringFrom,
                                                            @RequestParam(name = "to", required = false) String stringTo,
                                                            @RequestParam(name = "query", required = false) String query,
                                                            @RequestParam(name = "type", required = false) InspectionSearchType type){
        System.out.println(stringFrom);
        System.out.println(stringTo);
        LocalDateTime from = LocalDateTime.parse(stringFrom);
        LocalDateTime to = LocalDateTime.parse(stringTo);
        List<VehicleInspectionInfo> result = new ArrayList<>();
        if (type == InspectionSearchType.PLATE_NUMBER || type == InspectionSearchType.INSPECTION_NO) {
            result = informationService.getVehicleInspectionInfo(query, type);
        }
        else if (type == InspectionSearchType.REGISTRATION_DATE || type == InspectionSearchType.VALID_UNTIL) {
            result = informationService.getVehicleInspectionInfo(from, to, type);
        }
        List<VehicleInspectionInfoDTO> response = result.stream()
                .map(x -> modelMapper.map(x, VehicleInspectionInfoDTO.class))
                .toList();
        return response;
    }

    @GetMapping("/maintenance-info")
    public List<VehicleMaintenanceInfoDTO> getMaintenanceInfo(@RequestParam(name = "query", required = false) String query){
        List<VehicleMaintenanceInfo> result =  informationService.getVehiclesMaintenanceInfo(query);
        List<VehicleMaintenanceInfoDTO> response = result.stream()
                .map(x -> modelMapper.map(x, VehicleMaintenanceInfoDTO.class))
                .toList();
        return response;
    }

    @GetMapping("/register-info")
    public List<VehicleRegisterInfoDTO> getRegisterInfo(@RequestParam(name = "from", required = false) String stringFrom,
                                                        @RequestParam(name = "to", required = false) String stringTo){
        System.out.println(stringFrom);
        System.out.println(stringTo);
        LocalDateTime from = LocalDateTime.parse(stringFrom);
        LocalDateTime to = LocalDateTime.parse(stringTo);
        if (from == null) {
            from = LocalDateTime.MIN;
        }
        if (to == null) {
            to = LocalDateTime.MAX;
        }
        List<VehicleRegisterInfo> result =  informationService.getVehiclesRegisterInfo(from, to);
        List<VehicleRegisterInfoDTO> response = result.stream()
                .map(x -> modelMapper.map(x, VehicleRegisterInfoDTO.class))
                .toList();
        return response;
    }

    @GetMapping("/usage-info")
    public List<VehicleUsageInfoDTO> getUsageInfo(@RequestParam(name = "query", required = false) String query){
        List<VehicleUsageInfo> result =  informationService.getVehicleUsageInfo(query);
        List<VehicleUsageInfoDTO> response = result.stream()
                .map(x -> modelMapper.map(x, VehicleUsageInfoDTO.class))
                .toList();
        return response;
    }
}
