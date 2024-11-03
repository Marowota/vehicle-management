package com.maha.vehicle_management.DTO;

import java.time.LocalDateTime;

public class VehicleMaintenanceInfoDTO {
    Long id;
    String plateNumber;
    LocalDateTime start;
    LocalDateTime end;
    Double cost;
    Double addedFuel;
    String info;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Double getAddedFuel() {
        return addedFuel;
    }

    public void setAddedFuel(Double addedFuel) {
        this.addedFuel = addedFuel;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
