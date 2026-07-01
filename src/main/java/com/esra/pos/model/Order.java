package com.esra.pos.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

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
}