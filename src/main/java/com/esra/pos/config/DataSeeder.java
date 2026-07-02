package com.esra.pos.config;

import com.esra.pos.model.Category;
import com.esra.pos.model.RestaurantTable;
import com.esra.pos.model.User;
import com.esra.pos.repository.CategoryRepository;
import com.esra.pos.repository.RestaurantTableRepository;
import com.esra.pos.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataSeeder implements CommandLineRunner {

    private final RestaurantTableRepository tableRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    // Spring, bu constructor sayesinde repository'leri otomatik enjekte eder (Dependency Injection)
    public DataSeeder(RestaurantTableRepository tableRepository, 
                      CategoryRepository categoryRepository, 
                      UserRepository userRepository) {
        this.tableRepository = tableRepository;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        
        // 1. EĞER VERİ TABANINDA HİÇ MASA YOKSA ÖRNEK MASALARI EKLE
        if (tableRepository.count() == 0) {
            createTable("Masa 1", "BOŞ", 4);
            createTable("Masa 2", "BOŞ", 2);
            createTable("Masa 3", "BOŞ", 6);
            createTable("Masa 4", "REZERVE", 4);
            System.out.println(">> Başlangıç Masaları Veri Tabanına Eklendi!");
        }

        // 2. EĞER HİÇ KATEGORİ YOKSA ÖRNEK KATEGORİLERİ EKLE
        if (categoryRepository.count() == 0) {
            createCategory("Ana Yemekler", "Geleneksel ana yemek çeşitleri");
            createCategory("İçecekler", "Sıcak ve soğuk meşrubatlar");
            createCategory("Tatlılar", "Günlük taze tatlılar");
            System.out.println(">> Başlangıç Kategorileri Veri Tabanına Eklendi!");
        }

        // 3. EĞER HİÇ KULLANICI YOKSA ÖRNEK KULLANICILARI EKLE
        if (userRepository.count() == 0) {
            createUser("ahmet_garson", "12345", "Ahmet", "Yılmaz", "WAITER");
            createUser("esra_admin", "admin123", "Esra", "Karaca", "ADMIN");
            System.out.println(">> Başlangıç Kullanıcıları Veri Tabanına Eklendi!");
        }
    }

    // Masa eklemeyi kolaylaştıran yardımcı metot
    private void createTable(String name, String status, Integer capacity) {
        RestaurantTable table = new RestaurantTable();
        table.setTableName(name);
        table.setStatus(status);
        table.setCapacity(capacity);
        tableRepository.save(table);
    }

    // Kategori eklemeyi kolaylaştıran yardımcı metot
    private void createCategory(String name, String description) {
        Category category = new Category();
        category.setName(name);
        category.setDescription(description);
        categoryRepository.save(category);
    }

    // Kullanıcı eklemeyi kolaylaştıran yardımcı metot
    private void createUser(String username, String password, String firstName, String lastName, String role) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password); // İleride burayı BCrypt ile şifreleyeceğiz
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setRole(role);
        userRepository.save(user);
    }
}