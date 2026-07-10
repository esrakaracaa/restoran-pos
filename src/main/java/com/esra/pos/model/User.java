package com.esra.pos.model;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore; // BÜYÜK KURTARICI BURADA!

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class User implements UserDetails {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;
    
    @Column(nullable = false)
    private String password;
    
    private String firstName;
    private String lastName;
    
    @Column(nullable = false)
    private String role; // WAITER, CASHIER, MANAGER, ADMIN
    
    private Boolean active = true;

    // ========================================================================
    // AŞAĞIDAKİ METOTLAR SPRING SECURITY (USERDETAILS) İÇİN ZORUNLU METOTLARDIR
    // ========================================================================

    @JsonIgnore // JSON işlemlerinde bu metodu atla!
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Eğer frontend sadece ID gönderirse ve role null kalırsa hata vermemesi için koruma:
        if (this.role == null) {
            return List.of(); 
        }
        String authorityRole = role.startsWith("ROLE_") ? role : "ROLE_" + role;
        return List.of(new SimpleGrantedAuthority(authorityRole));
    }

    @JsonIgnore // Şifreyi asla JSON ile dışarı sızdırma!
    @Override
    public String getPassword() {
        return this.password;
    }

    @JsonIgnore
    @Override
    public String getUsername() {
        return this.username;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return this.active;
    }
}