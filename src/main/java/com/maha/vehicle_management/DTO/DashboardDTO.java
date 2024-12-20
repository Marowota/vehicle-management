package com.maha.vehicle_management.DTO;

import java.util.List;

public class DashboardDTO {
    long numberOfVehicles;
    long usingVehicles;
    long inspectedVehicles;
    long totalYearCost;
    List<Double> costPerMonth;
    List<VehicleRegisterInfoDTO> usingVehiclesDetail;

    public long getNumberOfVehicles() {
        return numberOfVehicles;
    }

    public void setNumberOfVehicles(long numberOfVehicles) {
        this.numberOfVehicles = numberOfVehicles;
    }

    public long getUsingVehicles() {
        return usingVehicles;
    }

    public void setUsingVehicles(long usingVehicles) {
        this.usingVehicles = usingVehicles;
    }

    public long getInspectedVehicles() {
        return inspectedVehicles;
    }

    public void setInspectedVehicles(long inspectedVehicles) {
        this.inspectedVehicles = inspectedVehicles;
    }

    public long getTotalYearCost() {
        return totalYearCost;
    }

    public void setTotalYearCost(long totalYearCost) {
        this.totalYearCost = totalYearCost;
    }

    public List<Double> getCostPerMonth() {
        return costPerMonth;
    }

    public void setCostPerMonth(List<Double> costPerMonth) {
        this.costPerMonth = costPerMonth;
    }

    public List<VehicleRegisterInfoDTO> getUsingVehiclesDetail() {
        return usingVehiclesDetail;
    }

    public void setUsingVehiclesDetail(List<VehicleRegisterInfoDTO> usingVehiclesDetail) {
        this.usingVehiclesDetail = usingVehiclesDetail;
    }
}
