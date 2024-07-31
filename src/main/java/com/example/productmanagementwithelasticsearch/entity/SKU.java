package com.example.productmanagementwithelasticsearch.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class SKU {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String color;
    private Boolean availability;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    // Getters and Setters
}