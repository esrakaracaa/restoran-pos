package com.esra.pos.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.esra.pos.model.Order;
import com.esra.pos.model.RestaurantTable;
import com.esra.pos.model.User;
import com.esra.pos.repository.OrderRepository;
import com.esra.pos.repository.RestaurantTableRepository;
import com.esra.pos.repository.UserRepository;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final RestaurantTableRepository tableRepository;
    private final UserRepository userRepository; // Kullanıcı kısıtlamasını aşmak için eklendi

    public OrderService(OrderRepository orderRepository, RestaurantTableRepository tableRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.tableRepository = tableRepository;
        this.userRepository = userRepository;
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order createOrder(Order order) {
        // 1. Siparişte bir masa belirtilmişse, o masayı bul ve DOLU yap
        if (order.getTable() != null && order.getTable().getId() != null) {
            RestaurantTable table = tableRepository.findById(order.getTable().getId())
                    .orElseThrow(() -> new RuntimeException("Masa bulunamadı!"));
            
            table.setStatus("DOLU");
            tableRepository.save(table);
        }

        // 2. Siparişte kullanıcı (user) belirtilmemişse, veritabanındaki ilk kullanıcıyı otomatik ata
        if (order.getUser() == null || order.getUser().getId() == null) {
            User defaultUser = userRepository.findAll().stream().findFirst()
                    .orElseThrow(() -> new RuntimeException("Sistemde kayıtlı personel/kullanıcı bulunamadı!"));
            order.setUser(defaultUser);
        }

        // 3. Yeni sipariş oluşturulduğunda varsayılan durumu AÇIK yap
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
    }
}
