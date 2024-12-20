package com.maha.vehicle_management.DTO;

public class MonthlyCostDTO {
    private int month;
    private double cost;

    public MonthlyCostDTO() {

    }

    public MonthlyCostDTO(int month, double cost) {
        this.month = month;
        this.cost = cost;
    }

    // Getters and setters
    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
}
