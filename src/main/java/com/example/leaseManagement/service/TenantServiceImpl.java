package com.example.leaseManagement.service;

import com.example.leaseManagement.dao.TenantDAO;
import com.example.leaseManagement.entity.Tenant;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TenantServiceImpl implements TenantService {

    private final TenantDAO tenantDAO;

    public TenantServiceImpl(TenantDAO tenantDAO) {
        this.tenantDAO = tenantDAO;
    }

    @Override
    @Transactional
    public Tenant saveTenant(Tenant tenant) {
        tenantDAO.save(tenant);
        return tenant;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Tenant> getTenantById(Long id) {
        return tenantDAO.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Tenant> getAllTenants() {
        return tenantDAO.findAll();
    }

    @Override
    @Transactional
    public Tenant updateTenant(Tenant tenant) {
        tenantDAO.update(tenant);
        return tenant;
    }

    @Override
    @Transactional
    public void deleteTenant(Long id) {
        tenantDAO.delete(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Tenant> getTenantByEmail(String email) {
        return tenantDAO.findByEmail(email);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Tenant> searchTenantsByLastName(String lastName) {
        return tenantDAO.findByLastName(lastName);
    }
}