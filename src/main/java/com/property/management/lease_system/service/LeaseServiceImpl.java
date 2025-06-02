package com.property.management.lease_system.service;

import com.property.management.lease_system.entity.Lease;
import com.property.management.lease_system.entity.Property;
import com.property.management.lease_system.entity.Tenant;
import com.property.management.lease_system.repository.LeaseRepository;
import com.property.management.lease_system.repository.PropertyRepository;
import com.property.management.lease_system.repository.TenantRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaseServiceImpl implements LeaseService {

    private final LeaseRepository leaseRepository;
    private final PropertyRepository propertyRepository;
    private final TenantRepository tenantRepository;

    public LeaseServiceImpl(
            LeaseRepository leaseRepository,
            PropertyRepository propertyRepository,
            TenantRepository tenantRepository) {
        this.leaseRepository = leaseRepository;
        this.propertyRepository = propertyRepository;
        this.tenantRepository = tenantRepository;
    }

    @Override
    public List<Lease> getAllLeases() {
        return leaseRepository.findAll();
    }

    @Override
    public Lease getLeaseById(Integer id) {
        return leaseRepository.findById(id).orElse(null);
    }

    @Override
    public Lease saveLease(Lease lease) {
        Integer propertyId = lease.getProperty().getId();
        Integer tenantId = lease.getTenant().getId();

        Property property = propertyRepository.findById(propertyId)
                .orElseThrow(() -> new RuntimeException("Property not found with id: " + propertyId));

        Tenant tenant = tenantRepository.findById(tenantId)
                .orElseThrow(() -> new RuntimeException("Tenant not found with id: " + tenantId));

        lease.setProperty(property);
        lease.setTenant(tenant);

        return leaseRepository.save(lease);
    }

    @Override
    public void deleteLease(Integer id) {
        leaseRepository.deleteById(id);
    }
}
