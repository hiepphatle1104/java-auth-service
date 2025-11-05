package com.swappie.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.swappie.domain.enums.Category;
import com.swappie.domain.enums.Currency;
import com.swappie.domain.enums.Status;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductDTO {
    private String name;
    private String description;
    private double price;
    private Currency currency;
    private Status status;
    private Category category;
    private List<ImageDTO> images;
    private VehicleSpecDTO vehicleSpec;
    private BatterySpecDTO batterySpec;
}
