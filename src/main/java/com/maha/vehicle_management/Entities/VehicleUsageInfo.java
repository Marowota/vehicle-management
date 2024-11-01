package com.maha.vehicle_management.Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "VEHICLE_USAGE_INFO")
public class VehicleUsageInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long Id;
    String plateNumber;
    Double fuelUsed;

    public  VehicleUsageInfo() {}

    public VehicleUsageInfo(String plateNumber, Double fuelUsed) {
        this.plateNumber = plateNumber;
        this.fuelUsed = fuelUsed;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Double getFuelUsed() {
        return fuelUsed;
    }

    public void setFuelUsed(Double fuelUsed) {
        this.fuelUsed = fuelUsed;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

}
