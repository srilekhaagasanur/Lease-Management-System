package com.example.leaseManagement.dao;

import com.example.leaseManagement.entity.Tenant;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class TenantDAOImpl implements TenantDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(Tenant tenant) {
        entityManager.persist(tenant);
    }

    @Override
    public Optional<Tenant> findById(Long id) {
        Tenant tenant = entityManager.find(Tenant.class, id);
        return Optional.ofNullable(tenant);
    }

    @Override
    public List<Tenant> findAll() {
        TypedQuery<Tenant> query = entityManager.createQuery(
                "SELECT t FROM Tenant t ORDER BY t.lastName, t.firstName", Tenant.class);
        return query.getResultList();
    }

    @Override
    public void update(Tenant tenant) {
        entityManager.merge(tenant);
    }

    @Override
    public void delete(Long id) {
        Tenant tenant = entityManager.find(Tenant.class, id);
        if (tenant != null) {
            entityManager.remove(tenant);
        }
    }

    @Override
    public Optional<Tenant> findByEmail(String email) {
        TypedQuery<Tenant> query = entityManager.createQuery(
                "SELECT t FROM Tenant t WHERE t.email = :email", Tenant.class);
        query.setParameter("email", email);
        List<Tenant> results = query.getResultList();
        return results.isEmpty() ? Optional.empty() : Optional.of(results.get(0));
    }

    @Override
    public List<Tenant> findByLastName(String lastName) {
        TypedQuery<Tenant> query = entityManager.createQuery(
                "SELECT t FROM Tenant t WHERE LOWER(t.lastName) LIKE LOWER(:lastName)",
                Tenant.class);
        query.setParameter("lastName", "%" + lastName + "%");
        return query.getResultList();
    }
}