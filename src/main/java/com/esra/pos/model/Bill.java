package com.esra.pos.model;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "bills")
@Data
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal subTotal; // Ara toplam
    private BigDecimal tax; // KDV tutarı
    private BigDecimal discount = BigDecimal.ZERO; // İndirim tutarı
    private BigDecimal totalAmount; // Net ödenecek tutar
    private LocalDateTime billDate = LocalDateTime.now();
    private Boolean isPaid = false;

    @OneToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order; // Hangi siparişin hesabı?
}