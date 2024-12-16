package com.maha.vehicle_management.DTO;

public class VehicleDTO {
    String plateNumber;
    VehicleSpecDTO vehicleSpec;
    Double cost;
    String health;
    String currentInspectId;
    Boolean isRemoved;

    public String getPlateNumber() {
        return plateNumber;
    }

    public VehicleSpecDTO getVehicleSpec() {
        return vehicleSpec;
    }

    public Double getCost() {
        return cost;
    }

    public String getHealth() {
        return health;
    }

    public String getCurrentInspectId() {
        return currentInspectId;
    }

    public Boolean getIsRemoved() {
        return isRemoved;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public void setVehicleSpec(VehicleSpecDTO vehicleSpec) {
        this.vehicleSpec = vehicleSpec;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public void setHealth(String health) {
        this.health = health;
    }

    public void setCurrentInspectId(String currentInspectId) {
        this.currentInspectId = currentInspectId;
    }

    public void setIsRemoved(Boolean isRemoved) {
        this.isRemoved = isRemoved;
    }
}
