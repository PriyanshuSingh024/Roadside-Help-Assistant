package com.roadassistant.backend.controller;

import com.roadassistant.backend.dto.ServiceRequestDto;
import com.roadassistant.backend.model.ServiceRequest;
import com.roadassistant.backend.service.ServiceRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/requests")
@CrossOrigin("*")
public class ServiceRequestController {

    @Autowired
    private ServiceRequestService serviceRequestService;

    @PostMapping
    public String createRequest(@RequestBody ServiceRequestDto dto) {
        return serviceRequestService.createRequest(dto);
    }

    @GetMapping("/user/{userId}")
    public List<ServiceRequest> getUserRequests(@PathVariable Long userId) {
        return serviceRequestService.getUserRequests(userId);
    }
}