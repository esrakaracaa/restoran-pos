package com.esra.pos.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "reservations")
@Data
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime reservationTime;
    
    private Integer numberOfPeople;
    private String status; // BEKLEMEDE, ONAYLANDI, İPTAL

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer; // Rezervasyonu yapan müşteri

    @ManyToOne
    @JoinColumn(name = "table_id")
    private RestaurantTable table; // Hangi masa ayrıldı?
}