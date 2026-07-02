package com.esra.pos.repository;

import com.esra.pos.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Giriş yaparken kullanıcı adına göre kontrol etmek için bu şart:
    Optional<User> findByUsername(String username);
}