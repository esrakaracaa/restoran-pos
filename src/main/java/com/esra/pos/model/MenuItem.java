package com.esra.pos.model;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;

@Entity
@Table(name = "menu_items")
@Data
public class MenuItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;
    
    private String description;
    
    @Column(nullable = false)
    private BigDecimal price; // Fiyatlar için kurumsal standart BigDecimal'dır
    
    private Boolean isAvailable = true;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category; // Her ürünün bir kategorisi olur
}