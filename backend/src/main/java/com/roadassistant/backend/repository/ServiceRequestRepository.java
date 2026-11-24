package com.roadassistant.backend.repository;

import com.roadassistant.backend.model.ServiceRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServiceRequestRepository extends JpaRepository<ServiceRequest, Long> {
    List<ServiceRequest> findByUser_Id(Long userId);
    List<ServiceRequest> findByAssignedAgent_Id(Long agentId);
}