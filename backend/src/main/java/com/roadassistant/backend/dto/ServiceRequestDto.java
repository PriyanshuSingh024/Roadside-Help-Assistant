package com.roadassistant.backend.dto;

public class ServiceRequestDto {
    private String issueType;
    private String vehicleType;
    private String vehicleNumber;
    private String location;
    private String description;
    private Long userId;
    private Double latitude;
    private Double longitude;

    public ServiceRequestDto() {}

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

    public Long getUserId() {
        return userId;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
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

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}