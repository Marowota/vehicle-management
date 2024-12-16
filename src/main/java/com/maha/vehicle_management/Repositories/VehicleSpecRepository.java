package com.maha.vehicle_management.Repositories;

import com.maha.vehicle_management.Entities.VehicleSpec;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface VehicleSpecRepository extends JpaRepository<VehicleSpec, Long> {
    VehicleSpec findOneById(long id);
    List<VehicleSpec> findByIdIn(Collection<Long> ids);
}
