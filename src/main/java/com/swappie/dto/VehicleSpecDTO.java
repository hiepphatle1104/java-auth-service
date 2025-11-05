package com.swappie.dto;

import com.swappie.domain.enums.Condition;
import lombok.Data;

@Data
public class VehicleSpecDTO {
    private String brand;
    private String model;
    private int manufactureYear;
    private String color;
    private int odometer;
    private int batteryHealth;
    private Condition vehicleCondition;
}
