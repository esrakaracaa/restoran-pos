package com.esra.pos.model;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "orders")
@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime orderTime = LocalDateTime.now();
    
    private String status; // AÇIK, TAMAMLANDI, İPTAL

    @ManyToOne
    @JoinColumn(name = "table_id")
    private RestaurantTable table; // Sipariş hangi masaya ait?

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user; // Siparişi hangi garson aldı?

    // YENİ EKLENEN KISIM: Siparişe ait ürünlerin listesi
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<OrderItem> orderItems;
}