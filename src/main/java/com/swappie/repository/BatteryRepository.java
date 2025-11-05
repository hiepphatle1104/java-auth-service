package com.swappie.repository;

import com.swappie.domain.entities.BatterySpec;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BatteryRepository extends JpaRepository<BatterySpec, UUID> {
}
