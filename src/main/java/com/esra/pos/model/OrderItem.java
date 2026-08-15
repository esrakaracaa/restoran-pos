package com.esra.pos.model;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "order_items")
@Data
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer quantity; // Kaç adet istendi?
    
    private String note; // Sipariş notu
    
    private BigDecimal priceAtOrder; // Ürün fiyatı değişirse eski siparişler bozulmasın diye o anki fiyatı saklar

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    @JsonBackReference // YENİ EKLENDİ: Order çağrılırken içindeki OrderItem'ın tekrar Order'ı çağırmasını (sonsuz döngüyü) engeller.
    private Order order; // Hangi ana siparişe ait?

    @ManyToOne
    @JoinColumn(name = "menu_item_id", nullable = false)
    private MenuItem menuItem; // Hangi ürün istendi?
}