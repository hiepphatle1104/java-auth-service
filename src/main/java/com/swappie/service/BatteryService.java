package com.swappie.service;

import com.swappie.domain.entities.BatterySpec;
import org.springframework.stereotype.Service;

@Service
public interface BatteryService {
    BatterySpec createBatterySpec(BatterySpec batterySpec);

    BatterySpec getBatterySpecByProductId(String id);

    void deleteBatterySpecByProductId(String id);

    void updateBatterySpec(BatterySpec batterySpec);
}
