package com.esra.pos.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    public SecurityConfig(JwtAuthenticationFilter jwtAuthFilter, AuthenticationProvider authenticationProvider) {
        this.jwtAuthFilter = jwtAuthFilter;
        this.authenticationProvider = authenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .cors().and().csrf().disable()
            .authorizeHttpRequests()
                // 1. ÖN YÜZ DOSYALARI İÇİN GENİŞLETİLMİŞ İZİNLER
                .requestMatchers(
                    "/", 
                    "/index.html", 
                    "/**/*.html",  // Tüm klasörlerdeki HTML dosyaları
                    "/**/*.css",   // Tüm klasörlerdeki CSS dosyaları
                    "/**/*.js",    // Tüm klasörlerdeki JS dosyaları
                    "/images/**",  // İleride eklenebilecek görseller
                    "/favicon.ico", 
                    "/error"       // Olası arka plan hatalarını 403 yerine görebilmek için
                ).permitAll()

                // 2. HERKESE AÇIK API YOLLARI
                .requestMatchers("/api/auth/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/categories/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/menu-items/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/tables/**").permitAll()
                
                // 3. DİĞER TÜM İŞLEMLER İÇİN TOKEN ZORUNLU
                .anyRequest().authenticated()
            .and()
            .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authenticationProvider(authenticationProvider)
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
