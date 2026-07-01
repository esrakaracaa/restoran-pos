package com.esra.pos.model;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "payments")
@Data
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal amount; // Ödenen miktar
    private String paymentMethod; // CASH, CREDIT_CARD, TICKET
    private LocalDateTime paymentTime = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "bill_id", nullable = false)
    private Bill bill; // Hangi hesaba ait ödeme?
}