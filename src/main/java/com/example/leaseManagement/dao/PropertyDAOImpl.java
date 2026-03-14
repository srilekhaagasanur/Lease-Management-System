package com.example.leaseManagement.dao;

import com.example.leaseManagement.entity.Property;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PropertyDAOImpl implements PropertyDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(Property property) {
        entityManager.persist(property);
    }

    @Override
    public Optional<Property> findById(Long id) {
        Property property = entityManager.find(Property.class, id);
        return Optional.ofNullable(property);
    }

    @Override
    public List<Property> findAll() {
        TypedQuery<Property> query = entityManager.createQuery(
                "SELECT p FROM Property p", Property.class);
        return query.getResultList();
    }

    @Override
    public void update(Property property) {
        entityManager.merge(property);
    }

    @Override
    public void delete(Long id) {
        Property property = entityManager.find(Property.class, id);
        if (property != null) {
            entityManager.remove(property);
        }
    }

    @Override
    public List<Property> findByOwnerName(String ownerName) {
        TypedQuery<Property> query = entityManager.createQuery(
                "SELECT p FROM Property p WHERE p.ownerName = :ownerName",
                Property.class);
        query.setParameter("ownerName", ownerName);
        return query.getResultList();
    }
}