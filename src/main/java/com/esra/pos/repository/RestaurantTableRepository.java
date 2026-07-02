package com.esra.pos.repository;

import com.esra.pos.model.RestaurantTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantTableRepository extends JpaRepository<RestaurantTable, Long> {
    // İleride buraya "Sadece boş masaları getir" gibi özel metotlar yazacağız.
}