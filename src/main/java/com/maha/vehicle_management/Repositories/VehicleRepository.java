package com.maha.vehicle_management.Repositories;

import com.maha.vehicle_management.Entities.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, String> {
    List<Vehicle> findAllByPlateNumberLikeAndIsRemovedEquals(String plateNumber, boolean isRemoved);
    Vehicle findOneByPlateNumber(String plateNumber);

}
