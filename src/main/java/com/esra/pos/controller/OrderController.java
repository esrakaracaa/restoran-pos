package com.esra.pos.controller;

import com.esra.pos.model.Order;
import com.esra.pos.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin("*")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        return ResponseEntity.ok(orderService.createOrder(order));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<Order> updateOrderStatus(@PathVariable Long id, @RequestParam String status) {
        return ResponseEntity.ok(orderService.updateOrderStatus(id, status));
    }

    // --- YENİ EKLENEN KISIM: Masaya göre sipariş getirme ---
    @GetMapping("/table/{tableId}")
    public ResponseEntity<List<Order>> getOrdersByTableId(@PathVariable Long tableId) {
        return ResponseEntity.ok(orderService.getOrdersByTableId(tableId));
    }
}