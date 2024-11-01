package com.maha.vehicle_management.Repositories;

import com.maha.vehicle_management.Entities.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VehicleRepository extends JpaRepository<Vehicle, String> {
    List<Vehicle> findByPlateNumberLike(String plateNumber);
    Vehicle findOneByPlateNumber(String plateNumber);
}
