package com.maha.vehicle_management.DTO;

import java.time.LocalDateTime;

public class VehicleInspectionInfoDTO {
    String inspectionNo;
    String plateNumber;
    LocalDateTime registrationDate;
    LocalDateTime validUntil;
    Double cost;

    public String getInspectionNo() {
        return inspectionNo;
    }

    public void setInspectionNo(String inspectionNo) {
        this.inspectionNo = inspectionNo;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }

    public LocalDateTime getValidUntil() {
        return validUntil;
    }

    public void setValidUntil(LocalDateTime validUntil) {
        this.validUntil = validUntil;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }
}
