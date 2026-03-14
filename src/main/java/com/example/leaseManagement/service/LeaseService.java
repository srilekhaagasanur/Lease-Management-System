package com.example.leaseManagement.service;

import com.example.leaseManagement.entity.Lease;
import java.util.List;
import java.util.Optional;

public interface LeaseService {

    Lease saveLease(Lease lease);

    Optional<Lease> getLeaseById(Long id);

    List<Lease> getAllLeases();

    Lease updateLease(Lease lease);

    void deleteLease(Long id);

    List<Lease> getLeasesByUnitId(Long unitId);

    List<Lease> getLeasesByTenantId(Long tenantId);

    List<Lease> getLeasesByStatus(Lease.LeaseStatus status);
}