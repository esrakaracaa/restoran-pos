package com.esra.pos.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.esra.pos.model.MenuItem;
import com.esra.pos.repository.MenuItemRepository;

@Service
public class MenuItemService {

    private final MenuItemRepository menuItemRepository;

    public MenuItemService(MenuItemRepository menuItemRepository) {
        this.menuItemRepository = menuItemRepository;
    }

    public List<MenuItem> getAllMenuItems() {
        return menuItemRepository.findAll();
    }

    public MenuItem createMenuItem(MenuItem menuItem) {
        return menuItemRepository.save(menuItem);
    }

    public void deleteMenuItem(Long id) {
        menuItemRepository.deleteById(id);
    }

    public MenuItem updateMenuItem(Long id, MenuItem menuItemDetails) {
        MenuItem existingItem = menuItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ürün bulunamadı!"));

        // Yeni değerleri mevcut ürünün üzerine yazıyoruz
        existingItem.setName(menuItemDetails.getName());
        existingItem.setPrice(menuItemDetails.getPrice());
        existingItem.setCategory(menuItemDetails.getCategory());

        // Güncellenmiş halini kaydediyoruz
        return menuItemRepository.save(existingItem);
    }
}