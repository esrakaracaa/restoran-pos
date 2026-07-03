package com.esra.pos.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.esra.pos.dto.AuthResponse;
import com.esra.pos.dto.LoginRequest;
import com.esra.pos.model.User;
import com.esra.pos.repository.UserRepository;
import com.esra.pos.security.JwtService;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserRepository userRepository;

    public AuthController(AuthenticationManager authenticationManager, JwtService jwtService, UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.userRepository = userRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            // 1. Spring Security ile kullanıcı adı ve şifreyi doğrula
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );

            // 2. Doğrulama başarılıysa kullanıcıyı veritabanından bul
            User user = userRepository.findByUsername(request.getUsername())
                    .orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı"));

            // 3. Kullanıcı için JWT Token üret
            String jwtToken = jwtService.generateToken(user);

            // 4. Token'ı ve kullanıcı bilgilerini ön yüze (React/Vue vb.) gönder
            return ResponseEntity.ok(new AuthResponse(jwtToken, user.getRole(), user.getUsername()));

        } catch (AuthenticationException e) {
            // Şifre yanlışsa 401 Unauthorized dön
            return ResponseEntity.status(401).body("Hatalı kullanıcı adı veya şifre!");
        }
    }
}