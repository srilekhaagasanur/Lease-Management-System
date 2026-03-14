package com.example.leaseManagement.service;

import com.example.leaseManagement.dao.MaintenanceRequestDAO;
import com.example.leaseManagement.entity.MaintenanceRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class MaintenanceRequestServiceImpl implements MaintenanceRequestService {

    private final MaintenanceRequestDAO maintenanceRequestDAO;

    public MaintenanceRequestServiceImpl(MaintenanceRequestDAO maintenanceRequestDAO) {
        this.maintenanceRequestDAO = maintenanceRequestDAO;
    }

    @Override
    @Transactional
    public MaintenanceRequest saveRequest(MaintenanceRequest request) {
        maintenanceRequestDAO.save(request);
        return request;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<MaintenanceRequest> getRequestById(Long id) {
        return maintenanceRequestDAO.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<MaintenanceRequest> getAllRequests() {
        return maintenanceRequestDAO.findAll();
    }

    @Override
    @Transactional
    public MaintenanceRequest updateRequest(MaintenanceRequest request) {
        maintenanceRequestDAO.update(request);
        return request;
    }

    @Override
    @Transactional
    public void deleteRequest(Long id) {
        maintenanceRequestDAO.delete(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<MaintenanceRequest> getRequestsByUnitId(Long unitId) {
        return maintenanceRequestDAO.findByUnitId(unitId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<MaintenanceRequest> getRequestsByStatus(MaintenanceRequest.Status status) {
        return maintenanceRequestDAO.findByStatus(status);
    }

    @Override
    @Transactional(readOnly = true)
    public List<MaintenanceRequest> getRequestsByPriority(MaintenanceRequest.Priority priority) {
        return maintenanceRequestDAO.findByPriority(priority);
    }

    @Override
    @Transactional(readOnly = true)
    public List<MaintenanceRequest> getPendingRequests() {
        return maintenanceRequestDAO.findPendingRequests();
    }
}