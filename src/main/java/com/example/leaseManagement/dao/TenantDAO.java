package com.example.leaseManagement.dao;

import com.example.leaseManagement.entity.Tenant;
import java.util.List;
import java.util.Optional;

public interface TenantDAO {

    void save(Tenant tenant);

    Optional<Tenant> findById(Long id);

    List<Tenant> findAll();

    void update(Tenant tenant);

    void delete(Long id);

    Optional<Tenant> findByEmail(String email);

    List<Tenant> findByLastName(String lastName);
}