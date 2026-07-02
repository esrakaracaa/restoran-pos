package com.esra.pos.controller;

import com.esra.pos.model.Inventory;
import com.esra.pos.service.InventoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@CrossOrigin("*")
public class InventoryController {

    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping
    public ResponseEntity<List<Inventory>> getAllInventory() {
        return ResponseEntity.ok(inventoryService.getAllInventory());
    }

    @PostMapping
    public ResponseEntity<Inventory> addInventoryItem(@RequestBody Inventory inventory) {
        return ResponseEntity.ok(inventoryService.addInventoryItem(inventory));
    }

    @PatchMapping("/{id}/quantity")
    public ResponseEntity<Inventory> updateQuantity(@PathVariable Long id, @RequestParam BigDecimal quantity) {
        return ResponseEntity.ok(inventoryService.updateQuantity(id, quantity));
    }
}