package com.swappie.domain.entities;

import com.swappie.domain.enums.Condition;
import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Table(name = "vehicle_specs")
@Data
public class VehicleSpec {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    @Column(nullable = false)
    private String brand;

    @Column(nullable = false)
    private String model;

    @Column(nullable = false)
    private int manufactureYear;

    @Column(nullable = false)
    private String color;

    @Column(nullable = false)
    private int odometer;

    @Column(nullable = false)
    private int batteryHealth;

    @Enumerated(EnumType.STRING)
    private Condition vehicleCondition;
}
