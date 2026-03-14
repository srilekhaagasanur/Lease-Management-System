package com.example.leaseManagement.service;

import com.example.leaseManagement.dao.LeaseDAO;
import com.example.leaseManagement.entity.Lease;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class LeaseServiceImpl implements LeaseService {

    private final LeaseDAO leaseDAO;

    public LeaseServiceImpl(LeaseDAO leaseDAO) {
        this.leaseDAO = leaseDAO;
    }

    @Override
    @Transactional
    public Lease saveLease(Lease lease) {
        leaseDAO.save(lease);
        return lease;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Lease> getLeaseById(Long id) {
        return leaseDAO.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Lease> getAllLeases() {
        return leaseDAO.findAll();
    }

    @Override
    @Transactional
    public Lease updateLease(Lease lease) {
        leaseDAO.update(lease);
        return lease;
    }

    @Override
    @Transactional
    public void deleteLease(Long id) {
        leaseDAO.delete(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Lease> getLeasesByUnitId(Long unitId) {
        return leaseDAO.findByUnitId(unitId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Lease> getLeasesByTenantId(Long tenantId) {
        return leaseDAO.findByTenantId(tenantId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Lease> getLeasesByStatus(Lease.LeaseStatus status) {
        return leaseDAO.findByStatus(status);
    }
}