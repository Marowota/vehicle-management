package com.maha.vehicle_management.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "VEHICLE")
public class Vehicle {
    @Id
    String plateNumber;
    Long specId;
    Double cost;
    String health;
    String currentInspectId;
    Boolean isRemoved;

    public Vehicle() {
        setIsRemoved(false);
    }

    public Vehicle(String plateNumber, Long specId, Double cost, String health) {
        setPlateNumber(plateNumber);
        setSpecId(specId);
        setCost(cost);
        setHealth(health);
        setIsRemoved(false);
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public Long getSpecId() {
        return specId;
    }

    public void setSpecId(Long specId) {
        this.specId = specId;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public String getHealth() {
        return health;
    }

    public void setHealth(String health) {
        this.health = health;
    }

    public String getCurrentInspectId() {
        return currentInspectId;
    }

    public void setCurrentInspectId(String currentInspectId) {
        this.currentInspectId = currentInspectId;
    }

    public Boolean getIsRemoved() {
        return isRemoved;
    }

    public void setIsRemoved(Boolean isRemoved) {
        this.isRemoved = isRemoved;
    }
}
