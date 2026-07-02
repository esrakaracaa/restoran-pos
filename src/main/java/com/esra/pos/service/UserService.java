package com.esra.pos.service;

import com.esra.pos.model.User;
import com.esra.pos.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User createUser(User user) {
        // İlerleyen aşamalarda Security Config entegre ettiğinde 
        // user.setPassword(passwordEncoder.encode(user.getPassword())) 
        // işlemini burada yapacaksın.
        return userRepository.save(user);
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı!"));
    }
}