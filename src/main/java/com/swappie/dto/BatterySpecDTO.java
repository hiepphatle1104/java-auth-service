package com.swappie.dto;

import com.swappie.domain.enums.Condition;
import lombok.Data;

@Data
public class BatterySpecDTO {
    private String brand;
    private int capacity;
    private int health;
    private int voltage;
    private Condition batteryCondition;
}
