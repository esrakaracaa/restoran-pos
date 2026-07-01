package com.esra.pos.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "restaurant_tables")
@Data
public class RestaurantTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String tableName; 
    
    private String status; // BOŞ, DOLU, REZERVE
    private Integer capacity;
}