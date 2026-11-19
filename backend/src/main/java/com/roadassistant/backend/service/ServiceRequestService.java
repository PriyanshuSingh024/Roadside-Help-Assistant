package com.roadassistant.backend.service;

import com.roadassistant.backend.dto.ServiceRequestDto;
import com.roadassistant.backend.model.Agent;
import com.roadassistant.backend.model.ServiceRequest;
import com.roadassistant.backend.model.User;
import com.roadassistant.backend.repository.AgentRepository;
import com.roadassistant.backend.repository.ServiceRequestRepository;
import com.roadassistant.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceRequestService {

    @Autowired
    private ServiceRequestRepository serviceRequestRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AgentRepository agentRepository;

    public String createRequest(ServiceRequestDto dto) {
        Optional<User> userOptional = userRepository.findById(dto.getUserId());

        if (userOptional.isEmpty()) {
            return "User not found";
        }

        ServiceRequest request = new ServiceRequest();
        request.setIssueType(dto.getIssueType());
        request.setVehicleType(dto.getVehicleType());
        request.setVehicleNumber(dto.getVehicleNumber());
        request.setLocation(dto.getLocation());
        request.setDescription(dto.getDescription());
        request.setStatus("PENDING");
        request.setLatitude(dto.getLatitude());
        request.setLongitude(dto.getLongitude());
        request.setUser(userOptional.get());

        serviceRequestRepository.save(request);
        return "Service request created successfully";
    }

    public List<ServiceRequest> getUserRequests(Long userId) {
        return serviceRequestRepository.findByUser_Id(userId);
    }

    public List<ServiceRequest> getAllRequests() {
        return serviceRequestRepository.findAll();
    }

    public String updateRequestStatus(Long requestId, String status) {
        Optional<ServiceRequest> optionalRequest = serviceRequestRepository.findById(requestId);

        if (optionalRequest.isEmpty()) {
            return "Request not found";
        }

        ServiceRequest request = optionalRequest.get();
        request.setStatus(status);
        serviceRequestRepository.save(request);

        return "Status updated successfully";
    }

    public String assignRequestToAgent(Long requestId, Long agentId) {
        Optional<ServiceRequest> requestOpt = serviceRequestRepository.findById(requestId);
        Optional<Agent> agentOpt = agentRepository.findById(agentId);

        if (requestOpt.isEmpty() || agentOpt.isEmpty()) {
            return "Request or Agent not found";
        }

        ServiceRequest request = requestOpt.get();
        request.setAssignedAgent(agentOpt.get());
        request.setStatus("ASSIGNED");

        serviceRequestRepository.save(request);

        return "Request assigned to agent successfully";
    }

    public List<ServiceRequest> getRequestsByAgent(Long agentId) {
        return serviceRequestRepository.findByAssignedAgent_Id(agentId);
    }
}