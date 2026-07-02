package com.esra.pos.repository;

import com.esra.pos.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    // Telefon numarasına göre müşteri bulmak için:
    Optional<Customer> findByPhoneNumber(String phoneNumber);
}