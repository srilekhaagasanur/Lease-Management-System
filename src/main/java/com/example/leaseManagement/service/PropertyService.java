package com.example.leaseManagement.service;

import com.example.leaseManagement.entity.Property;
import java.util.List;
import java.util.Optional;

public interface PropertyService {

    Property saveProperty(Property property);

    Optional<Property> getPropertyById(Long id);

    List<Property> getAllProperties();

    Property updateProperty(Property property);

    void deleteProperty(Long id);

    List<Property> findPropertiesByOwner(String ownerName);
}