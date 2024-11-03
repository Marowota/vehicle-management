package com.maha.vehicle_management.Repositories;

import com.maha.vehicle_management.Entities.VehicleSpec;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleSpecRepository extends JpaRepository<VehicleSpec, Long> {
    VehicleSpec findOneById(long id);
}
