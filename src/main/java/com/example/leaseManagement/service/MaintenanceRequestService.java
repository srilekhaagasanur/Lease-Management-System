package com.example.leaseManagement.service;

import com.example.leaseManagement.entity.MaintenanceRequest;
import java.util.List;
import java.util.Optional;

public interface MaintenanceRequestService {

    MaintenanceRequest saveRequest(MaintenanceRequest request);

    Optional<MaintenanceRequest> getRequestById(Long id);

    List<MaintenanceRequest> getAllRequests();

    MaintenanceRequest updateRequest(MaintenanceRequest request);

    void deleteRequest(Long id);

    List<MaintenanceRequest> getRequestsByUnitId(Long unitId);

    List<MaintenanceRequest> getRequestsByStatus(MaintenanceRequest.Status status);

    List<MaintenanceRequest> getRequestsByPriority(MaintenanceRequest.Priority priority);

    List<MaintenanceRequest> getPendingRequests();
}