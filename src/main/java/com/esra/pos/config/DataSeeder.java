package com.esra.pos.config;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.esra.pos.model.Category;
import com.esra.pos.model.MenuItem;
import com.esra.pos.model.RestaurantTable;
import com.esra.pos.model.User;
import com.esra.pos.repository.CategoryRepository;
import com.esra.pos.repository.MenuItemRepository;
import com.esra.pos.repository.RestaurantTableRepository;
import com.esra.pos.repository.UserRepository;

@Component
public class DataSeeder implements CommandLineRunner {

    private final RestaurantTableRepository tableRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;
    private final MenuItemRepository menuItemRepository; // YENİ EKLENDİ

    public DataSeeder(RestaurantTableRepository tableRepository, 
                      CategoryRepository categoryRepository, 
                      UserRepository userRepository,
                      MenuItemRepository menuItemRepository) {
        this.tableRepository = tableRepository;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
        this.menuItemRepository = menuItemRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        
        // 1. MASALARI EKLE
        if (tableRepository.count() == 0) {
            createTable("Masa 1", "BOŞ", 4);
            createTable("Masa 2", "BOŞ", 2);
            createTable("Masa 3", "BOŞ", 6);
            createTable("Masa 4", "REZERVE", 4);
            System.out.println(">> Başlangıç Masaları Eklendi!");
        }

        // 2. KATEGORİLERİ EKLE
        if (categoryRepository.count() == 0) {
            createCategory("Ana Yemekler", "Geleneksel ana yemek çeşitleri");
            createCategory("İçecekler", "Sıcak ve soğuk meşrubatlar");
            createCategory("Tatlılar", "Günlük taze tatlılar");
            System.out.println(">> Başlangıç Kategorileri Eklendi!");
        }

        // 3. MENÜ ÜRÜNLERİNİ (YEMEKLERİ) EKLE
        if (menuItemRepository.count() == 0) {
            List<Category> categories = categoryRepository.findAll();
            if (!categories.isEmpty()) {
                Category anaYemek = categories.stream().filter(c -> c.getName().equals("Ana Yemekler")).findFirst().orElse(categories.get(0));
                Category icecek = categories.stream().filter(c -> c.getName().equals("İçecekler")).findFirst().orElse(categories.get(0));
                Category tatli = categories.stream().filter(c -> c.getName().equals("Tatlılar")).findFirst().orElse(categories.get(0));

                createMenuItem("İskender Kebap", "Bol tereyağlı enfes iskender", new BigDecimal("250.00"), anaYemek);
                createMenuItem("Adana Dürüm", "Acılı zırh kıyması", new BigDecimal("180.00"), anaYemek);
                createMenuItem("Yayık Ayran", "Köpüklü ev yapımı ayran", new BigDecimal("35.00"), icecek);
                createMenuItem("Kutu Kola", "Soğuk içecek", new BigDecimal("40.00"), icecek);
                createMenuItem("Fıstıklı Künefe", "Özel peynirli sıcak künefe", new BigDecimal("120.00"), tatli);
                createMenuItem("Fırın Sütlaç", "Tam kıvamında anne sütlacı", new BigDecimal("70.00"), tatli);
                
                System.out.println(">> Başlangıç Yemekleri Eklendi!");
            }
        }

        // 4. KULLANICILARI EKLE
        if (userRepository.count() == 0) {
            createUser("ahmet_garson", "12345", "Ahmet", "Yılmaz", "WAITER");
            createUser("esra_admin", "admin123", "Esra", "Karaca", "ADMIN");
            System.out.println(">> Başlangıç Kullanıcıları Eklendi!");
        }
    }

    // YARDIMCI METOTLAR
    private void createTable(String name, String status, Integer capacity) {
        RestaurantTable table = new RestaurantTable();
        table.setTableName(name);
        table.setStatus(status);
        table.setCapacity(capacity);
        tableRepository.save(table);
    }

    private void createCategory(String name, String description) {
        Category category = new Category();
        category.setName(name);
        category.setDescription(description);
        categoryRepository.save(category);
    }

    private void createMenuItem(String name, String description, BigDecimal price, Category category) {
        MenuItem item = new MenuItem();
        item.setName(name);
        item.setDescription(description);
        item.setPrice(price);
        item.setCategory(category);
        item.setIsAvailable(true);
        menuItemRepository.save(item);
    }

    private void createUser(String username, String password, String firstName, String lastName, String role) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password); 
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setRole(role);
        userRepository.save(user);
    }
}