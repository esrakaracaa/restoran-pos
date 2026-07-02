package com.esra.pos.service;

import com.esra.pos.model.Inventory;
import com.esra.pos.repository.InventoryRepository;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.List;

@Service
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    public List<Inventory> getAllInventory() {
        return inventoryRepository.findAll();
    }

    public Inventory addInventoryItem(Inventory inventory) {
        return inventoryRepository.save(inventory);
    }

    public Inventory updateQuantity(Long id, BigDecimal newQuantity) {
        Inventory item = inventoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Stok kalemi bulunamadı!"));
        item.setQuantity(newQuantity);
        return inventoryRepository.save(item);
    }
}