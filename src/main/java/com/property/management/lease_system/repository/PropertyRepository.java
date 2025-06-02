package com.property.management.lease_system.repository;

import com.property.management.lease_system.entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropertyRepository extends JpaRepository<Property, Integer> {
}
