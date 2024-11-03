package com.maha.vehicle_management.DTO;

public class VehicleSpecDTO {
    Long id;
    String type;
    String brand;
    String modelCode;
    Integer noOfSeat;
    String madeIn;
    String fuelType;
    Double fuelConsumptionPer100Km;
    Double tankCapacity;
    Double topSpeed;
    String frontRimsDimension;
    String backRimsDimension;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModelCode() {
        return modelCode;
    }

    public void setModelCode(String modelCode) {
        this.modelCode = modelCode;
    }

    public Integer getNoOfSeat() {
        return noOfSeat;
    }

    public void setNoOfSeat(Integer noOfSeat) {
        this.noOfSeat = noOfSeat;
    }

    public String getMadeIn() {
        return madeIn;
    }

    public void setMadeIn(String madeIn) {
        this.madeIn = madeIn;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public Double getFuelConsumptionPer100Km() {
        return fuelConsumptionPer100Km;
    }

    public void setFuelConsumptionPer100Km(Double fuelConsumptionPer100Km) {
        this.fuelConsumptionPer100Km = fuelConsumptionPer100Km;
    }

    public Double getTankCapacity() {
        return tankCapacity;
    }

    public void setTankCapacity(Double tankCapacity) {
        this.tankCapacity = tankCapacity;
    }

    public Double getTopSpeed() {
        return topSpeed;
    }

    public void setTopSpeed(Double topSpeed) {
        this.topSpeed = topSpeed;
    }

    public String getFrontRimsDimension() {
        return frontRimsDimension;
    }

    public void setFrontRimsDimension(String frontRimsDimension) {
        this.frontRimsDimension = frontRimsDimension;
    }

    public String getBackRimsDimension() {
        return backRimsDimension;
    }

    public void setBackRimsDimension(String backRimsDimension) {
        this.backRimsDimension = backRimsDimension;
    }
}
