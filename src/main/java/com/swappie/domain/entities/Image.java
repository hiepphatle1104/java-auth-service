package com.swappie.domain.entities;


import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Table(name = "images")
@Data
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    @Column(nullable = false)
    private String alt;

    @Column(nullable = false)
    private String url;
}
