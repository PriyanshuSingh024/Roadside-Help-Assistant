package com.roadassistant.backend.controller;

import com.roadassistant.backend.dto.LoginRequest;
import com.roadassistant.backend.dto.RegisterRequest;
import com.roadassistant.backend.model.User;
import com.roadassistant.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest request) {
        return userService.register(request);
    }

    @PostMapping("/login")
    public Object login(@RequestBody LoginRequest request) {
        User user = userService.login(request);

        if (user == null) {
            return "Invalid email or password";
        }

        return user;
    }
}