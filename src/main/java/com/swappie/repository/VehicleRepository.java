package com.swappie.repository;

import com.swappie.domain.entities.VehicleSpec;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface VehicleRepository extends JpaRepository<VehicleSpec, UUID> {
}
