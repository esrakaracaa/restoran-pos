package com.esra.pos.service;

import com.esra.pos.model.Category;
import com.esra.pos.repository.CategoryRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    // Tüm yemek türlerini listelemek için (Ekranda göstermek için)
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    // Yöneticinin yeni bir yemek türü ekleyebilmesi için:
    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }
}