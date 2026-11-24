package com.roadassistant.backend.service;

import com.roadassistant.backend.dto.AdminLoginRequest;
import com.roadassistant.backend.model.Admin;
import com.roadassistant.backend.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    public Admin loginAdmin(AdminLoginRequest request) {
        String cleanEmail = request.getEmail() == null ? "" : request.getEmail().trim();

        Optional<Admin> adminOptional = adminRepository.findByEmailIgnoreCase(cleanEmail);

        if (adminOptional.isPresent()) {
            Admin admin = adminOptional.get();

            if (admin.getPassword().equals(request.getPassword())) {
                return admin;
            }
        }

        return null;
    }
}