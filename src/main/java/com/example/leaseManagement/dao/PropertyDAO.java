package com.example.leaseManagement.dao;

import com.example.leaseManagement.entity.Property;
import java.util.List;
import java.util.Optional;

public interface PropertyDAO {

    void save(Property property);

    Optional<Property> findById(Long id);

    List<Property> findAll();

    void update(Property property);

    void delete(Long id);

    List<Property> findByOwnerName(String ownerName);
}