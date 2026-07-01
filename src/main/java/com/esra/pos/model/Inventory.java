package com.esra.pos.model;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;

@Entity
@Table(name = "inventory")
@Data
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String ingredientName; // Malzeme adı
    
    private BigDecimal quantity; // Mevcut miktar
    private String unit; // KG, LİTRE, ADET
    private BigDecimal minimumRequired; // Kritik stok seviyesi
}