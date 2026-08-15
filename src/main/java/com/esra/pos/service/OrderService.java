package com.esra.pos.service;

import com.esra.pos.model.Order;
import com.esra.pos.repository.OrderRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order createOrder(Order order) {
        // Yeni sipariş oluşturulduğunda varsayılan durumu AÇIK yapabiliriz
        order.setStatus("AÇIK");
        return orderRepository.save(order);
    }
    
    public Order updateOrderStatus(Long id, String status) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sipariş bulunamadı!"));
        order.setStatus(status);
        return orderRepository.save(order);
    }
    // Masaya göre siparişleri getiren servis metodu
    public List<Order> getOrdersByTableId(Long tableId) {
        return orderRepository.findByTableId(tableId); 
        // (Eğer Order modelinde masa değişkeninin adı 'restaurantTable' ise, metot adını findByRestaurantTableId olarak değiştirmen gerekebilir)
    }
}