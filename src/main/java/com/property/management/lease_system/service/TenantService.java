package com.property.management.lease_system.service;

import com.property.management.lease_system.entity.Tenant;
import com.property.management.lease_system.repository.TenantRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TenantService {

    private final TenantRepository tenantRepository;

    public TenantService(TenantRepository tenantRepository) {
        this.tenantRepository = tenantRepository;
    }

    public List<Tenant> getAllTenants() {
        return tenantRepository.findAll();
    }

    public Tenant getTenantById(Integer id) {
        return tenantRepository.findById(id).orElse(null);
    }

    public Tenant saveTenant(Tenant tenant) {
        return tenantRepository.save(tenant);
    }

    // TenantService
    public void deleteTenant(Integer id) {
        tenantRepository.deleteById(id);
    }


}
