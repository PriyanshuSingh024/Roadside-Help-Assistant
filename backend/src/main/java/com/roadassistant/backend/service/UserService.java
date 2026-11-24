package com.roadassistant.backend.service;

import com.roadassistant.backend.dto.LoginRequest;
import com.roadassistant.backend.dto.RegisterRequest;
import com.roadassistant.backend.model.User;
import com.roadassistant.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public String register(RegisterRequest request) {
        Optional<User> existingUser = userRepository.findByEmail(request.getEmail());

        if (existingUser.isPresent()) {
            return "Email already exists";
        }

        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setPhone(request.getPhone());

        userRepository.save(user);
        return "User registered successfully";
    }

    public User login(LoginRequest request) {
        Optional<User> userOptional = userRepository.findByEmail(request.getEmail());

        if (userOptional.isPresent()) {
            User user = userOptional.get();

            if (user.getPassword().equals(request.getPassword())) {
                return user;
            }
        }

        return null;
    }
}