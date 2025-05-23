package com.property.management.lease_system.repository;

import com.property.management.lease_system.entity.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TenantRepository extends JpaRepository<Tenant, Integer> {
}
