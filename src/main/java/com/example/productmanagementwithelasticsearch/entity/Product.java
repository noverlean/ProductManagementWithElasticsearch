package com.example.productmanagementwithelasticsearch.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private Date startDate;
    private Boolean active;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<SKU> skus;

    // Getters and Setters
}