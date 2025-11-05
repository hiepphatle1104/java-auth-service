package com.swappie.domain.entities;


import com.swappie.domain.enums.Condition;
import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Table(name = "battery_specs")
@Data
public class BatterySpec {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    @Column(nullable = false)
    private String brand;

    @Column(nullable = false)
    private int capacity;

    @Column(nullable = false)
    private int health;

    @Column(nullable = false)
    private int voltage;

    @Enumerated(EnumType.STRING)
    private Condition batteryCondition;
}
