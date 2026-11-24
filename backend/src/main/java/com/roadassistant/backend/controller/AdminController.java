package com.roadassistant.backend.controller;

import com.roadassistant.backend.config.JwtUtil;
import com.roadassistant.backend.dto.AdminLoginRequest;
import com.roadassistant.backend.model.Admin;
import com.roadassistant.backend.model.ServiceRequest;
import com.roadassistant.backend.service.AdminService;
import com.roadassistant.backend.service.ServiceRequestService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin("*")
public class AdminController {

    @Autowired
    private ServiceRequestService serviceRequestService;

    @Autowired
    private AdminService adminService;

    @Autowired
    private JwtUtil jwtUtil;

    private boolean isAdmin(HttpServletRequest request) {
        Object role = request.getAttribute("jwtRole");
        return role != null && role.equals("ADMIN");
    }

    @PostMapping("/login")
    public Object loginAdmin(@RequestBody AdminLoginRequest request) {
        Admin admin = adminService.loginAdmin(request);

        if (admin == null) {
            return "Invalid admin email or password";
        }

        String token = jwtUtil.generateToken(admin.getEmail(), "ADMIN", admin.getId());

        Map<String, Object> response = new HashMap<>();
        response.put("id", admin.getId());
        response.put("name", admin.getName());
        response.put("email", admin.getEmail());
        response.put("role", "ADMIN");
        response.put("token", token);

        return response;
    }

    @GetMapping("/requests")
    public Object getAllRequests(HttpServletRequest request) {
        if (!isAdmin(request)) {
            return "Unauthorized";
        }
        return serviceRequestService.getAllRequests();
    }

    @PutMapping("/requests/{id}/{status}")
    public String updateStatus(@PathVariable Long id,
                               @PathVariable String status,
                               HttpServletRequest request) {
        if (!isAdmin(request)) {
            return "Unauthorized";
        }
        return serviceRequestService.updateRequestStatus(id, status);
    }

    @PutMapping("/assign-agent/{requestId}/{agentId}")
    public String assignRequestToAgent(@PathVariable Long requestId,
                                       @PathVariable Long agentId,
                                       HttpServletRequest request) {
        if (!isAdmin(request)) {
            return "Unauthorized";
        }
        return serviceRequestService.assignRequestToAgent(requestId, agentId);
    }

    @GetMapping("/agent-requests/{agentId}")
    public Object getRequestsByAgent(@PathVariable Long agentId,
                                     HttpServletRequest request) {
        Object role = request.getAttribute("jwtRole");
        Object jwtId = request.getAttribute("jwtId");

        if (role == null) {
            return "Unauthorized";
        }

        if (role.equals("ADMIN")) {
            return serviceRequestService.getRequestsByAgent(agentId);
        }

        if (role.equals("AGENT") && jwtId != null && Long.valueOf(jwtId.toString()).equals(agentId)) {
            return serviceRequestService.getRequestsByAgent(agentId);
        }

        return "Unauthorized";
    }
}