package com.esra.pos.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.esra.pos.model.RestaurantTable;
import com.esra.pos.repository.RestaurantTableRepository;

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
    public RestaurantTable updateTable(Long id, RestaurantTable tableDetails) {
        // Masayı veritabanından bul
        RestaurantTable existingTable = tableRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Masa bulunamadı!"));

        // Sadece statüsünü yeni gelen veriyle değiştir (BOS, DOLU, REZERVE)
        existingTable.setStatus(tableDetails.getStatus());

        // Güncellenmiş halini kaydet
        return tableRepository.save(existingTable);
    }
}