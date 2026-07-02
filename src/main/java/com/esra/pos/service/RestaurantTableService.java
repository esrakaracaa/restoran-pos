package com.esra.pos.service;

import com.esra.pos.model.RestaurantTable;
import com.esra.pos.repository.RestaurantTableRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RestaurantTableService {

    private final RestaurantTableRepository tableRepository;

    public RestaurantTableService(RestaurantTableRepository tableRepository) {
        this.tableRepository = tableRepository;
    }

    public List<RestaurantTable> getAllTables() {
        return tableRepository.findAll();
    }

    public RestaurantTable createTable(RestaurantTable table) {
        return tableRepository.save(table);
    }

    public RestaurantTable updateTableStatus(Long id, String status) {
        RestaurantTable table = tableRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Masa bulunamadı!"));
        table.setStatus(status);
        return tableRepository.save(table);
    }
}