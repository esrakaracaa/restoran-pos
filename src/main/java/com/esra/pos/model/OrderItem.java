package com.esra.pos.model;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;

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
    private Order order; // Hangi ana siparişe ait?

    @ManyToOne
    @JoinColumn(name = "menu_item_id", nullable = false)
    private MenuItem menuItem; // Hangi ürün istendi?
}