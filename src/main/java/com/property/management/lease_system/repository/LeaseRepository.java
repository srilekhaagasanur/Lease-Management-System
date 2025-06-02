package com.property.management.lease_system.repository;

import com.property.management.lease_system.entity.Lease;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeaseRepository extends JpaRepository<Lease, Integer> {
}
