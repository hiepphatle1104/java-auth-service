package com.swappie.service;

import com.swappie.domain.entities.VehicleSpec;
import org.springframework.stereotype.Service;

@Service
public interface VehicleService {
    VehicleSpec createVehicleSpec(VehicleSpec vehicleSpec);

    VehicleSpec getVehicleSpecByProductId(String id);

    void deleteVehicleSpecByProductId(String id);

    void updateVehicleSpec(VehicleSpec VehicleSpec);
}
