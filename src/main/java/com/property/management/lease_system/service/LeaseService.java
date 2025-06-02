package com.property.management.lease_system.service;

import com.property.management.lease_system.entity.Lease;

import java.util.List;

public interface LeaseService {
    List<Lease> getAllLeases();
    Lease getLeaseById(Integer id);
    Lease saveLease(Lease lease);
    void deleteLease(Integer id);
}
