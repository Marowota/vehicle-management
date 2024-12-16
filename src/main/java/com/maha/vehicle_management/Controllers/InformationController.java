package com.maha.vehicle_management.Controllers;

import com.maha.vehicle_management.DTO.VehicleInspectionInfoDTO;
import com.maha.vehicle_management.DTO.VehicleMaintenanceInfoDTO;
import com.maha.vehicle_management.DTO.VehicleRegisterInfoDTO;
import com.maha.vehicle_management.DTO.VehicleUsageInfoDTO;
import com.maha.vehicle_management.Entities.VehicleInspectionInfo;
import com.maha.vehicle_management.Entities.VehicleMaintenanceInfo;
import com.maha.vehicle_management.Entities.VehicleRegisterInfo;
import com.maha.vehicle_management.Entities.VehicleUsageInfo;
import com.maha.vehicle_management.Services.InformationService;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
public class InformationController {
    private final InformationService informationService;
    private final ModelMapper modelMapper;

    public InformationController(InformationService informationService, ModelMapper modelMapper) {
        this.informationService = informationService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/inspection-info")
    public List<VehicleInspectionInfoDTO> getInspectionInfo(@RequestParam(name = "query", required = false) String query){
        List<VehicleInspectionInfo> result =  informationService.getVehicleInspectionInfo(query);
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
