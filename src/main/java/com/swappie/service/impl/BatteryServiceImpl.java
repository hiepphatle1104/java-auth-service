package com.swappie.service.impl;

import com.swappie.domain.entities.BatterySpec;
import com.swappie.service.BatteryService;
import org.springframework.stereotype.Service;

@Service
public class BatteryServiceImpl implements BatteryService {
    @Override
    public BatterySpec createBatterySpec(BatterySpec batterySpec) {
        return null;
    }

    @Override
    public BatterySpec getBatterySpecByProductId(String id) {
        return null;
    }

    @Override
    public void deleteBatterySpecByProductId(String id) {

    }

    @Override
    public void updateBatterySpec(BatterySpec batterySpec) {

    }
}
