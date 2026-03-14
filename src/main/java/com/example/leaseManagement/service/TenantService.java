package com.example.leaseManagement.service;

import com.example.leaseManagement.entity.Tenant;
import java.util.List;
import java.util.Optional;

public interface TenantService {

    Tenant saveTenant(Tenant tenant);

    Optional<Tenant> getTenantById(Long id);

    List<Tenant> getAllTenants();

    Tenant updateTenant(Tenant tenant);

    void deleteTenant(Long id);

    Optional<Tenant> getTenantByEmail(String email);

    List<Tenant> searchTenantsByLastName(String lastName);
}