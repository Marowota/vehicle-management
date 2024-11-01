package com.maha.vehicle_management.Entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "VEHICLE_MAINTENANCE_INFO")
public class VehicleMaintenanceInfo {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    Long Id;
    String plateNumber;
    LocalDateTime start;
    LocalDateTime end;
    Double cost;
    Double addedFuel;
    String info;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
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
