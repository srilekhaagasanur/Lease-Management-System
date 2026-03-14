package com.example.leaseManagement.service;

import com.example.leaseManagement.dao.PropertyDAO;
import com.example.leaseManagement.entity.Property;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PropertyServiceImpl implements PropertyService {

    private final PropertyDAO propertyDAO;

    public PropertyServiceImpl(PropertyDAO propertyDAO) {
        this.propertyDAO = propertyDAO;
    }

    @Override
    @Transactional
    public Property saveProperty(Property property) {
        propertyDAO.save(property);
        return property;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Property> getPropertyById(Long id) {
        return propertyDAO.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Property> getAllProperties() {
        return propertyDAO.findAll();
    }

    @Override
    @Transactional
    public Property updateProperty(Property property) {
        propertyDAO.update(property);
        return property;
    }

    @Override
    @Transactional
    public void deleteProperty(Long id) {
        propertyDAO.delete(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Property> findPropertiesByOwner(String ownerName) {
        return propertyDAO.findByOwnerName(ownerName);
    }
}