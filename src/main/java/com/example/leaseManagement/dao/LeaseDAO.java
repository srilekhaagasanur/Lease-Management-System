package com.example.leaseManagement.dao;

import com.example.leaseManagement.entity.Lease;
import java.util.List;
import java.util.Optional;

public interface LeaseDAO {

    void save(Lease lease);

    Optional<Lease> findById(Long id);

    List<Lease> findAll();

    void update(Lease lease);

    void delete(Long id);

    List<Lease> findByUnitId(Long unitId);

    List<Lease> findByTenantId(Long tenantId);

    List<Lease> findByStatus(Lease.LeaseStatus status);
}