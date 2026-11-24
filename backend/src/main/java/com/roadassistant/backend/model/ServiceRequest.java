package com.roadassistant.backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "service_requests")
public class ServiceRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String issueType;
    private String vehicleType;
    private String vehicleNumber;
    private String location;
    private String description;
    private String status;

    private Double latitude;
    private Double longitude;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "assigned_agent_id")
    private Agent assignedAgent;

    public ServiceRequest() {}

    public Long getId() {
        return id;
    }

    public String getIssueType() {
        return issueType;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public String getLocation() {
        return location;
    }

    public String getDescription() {
        return description;
    }

    public String getStatus() {
        return status;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public User getUser() {
        return user;
    }

    public Agent getAssignedAgent() {
        return assignedAgent;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setIssueType(String issueType) {
        this.issueType = issueType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setAssignedAgent(Agent assignedAgent) {
        this.assignedAgent = assignedAgent;
    }
}