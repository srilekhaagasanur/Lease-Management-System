package com.example.leaseManagement.dao;

import com.example.leaseManagement.entity.MaintenanceRequest;
import java.util.List;
import java.util.Optional;

public interface MaintenanceRequestDAO {

    void save(MaintenanceRequest request);

    Optional<MaintenanceRequest> findById(Long id);

    List<MaintenanceRequest> findAll();

    void update(MaintenanceRequest request);

    void delete(Long id);

    List<MaintenanceRequest> findByUnitId(Long unitId);

    List<MaintenanceRequest> findByStatus(MaintenanceRequest.Status status);

    List<MaintenanceRequest> findByPriority(MaintenanceRequest.Priority priority);

    List<MaintenanceRequest> findPendingRequests();
}