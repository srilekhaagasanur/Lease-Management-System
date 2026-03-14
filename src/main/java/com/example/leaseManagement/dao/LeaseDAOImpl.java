package com.example.leaseManagement.dao;

import com.example.leaseManagement.entity.Lease;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class LeaseDAOImpl implements LeaseDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(Lease lease) {
        entityManager.persist(lease);
    }

    @Override
    public Optional<Lease> findById(Long id) {
        Lease lease = entityManager.find(Lease.class, id);
        return Optional.ofNullable(lease);
    }

    @Override
    public List<Lease> findAll() {
        TypedQuery<Lease> query = entityManager.createQuery(
                "SELECT l FROM Lease l JOIN FETCH l.unit u JOIN FETCH l.tenant t ORDER BY l.startDate DESC",
                Lease.class);
        return query.getResultList();
    }

    @Override
    public void update(Lease lease) {
        entityManager.merge(lease);
    }

    @Override
    public void delete(Long id) {
        Lease lease = entityManager.find(Lease.class, id);
        if (lease != null) {
            entityManager.remove(lease);
        }
    }

    @Override
    public List<Lease> findByUnitId(Long unitId) {
        TypedQuery<Lease> query = entityManager.createQuery(
                "SELECT l FROM Lease l JOIN FETCH l.tenant WHERE l.unit.unitId = :unitId ORDER BY l.startDate DESC",
                Lease.class);
        query.setParameter("unitId", unitId);
        return query.getResultList();
    }

    @Override
    public List<Lease> findByTenantId(Long tenantId) {
        TypedQuery<Lease> query = entityManager.createQuery(
                "SELECT l FROM Lease l JOIN FETCH l.unit WHERE l.tenant.tenantId = :tenantId ORDER BY l.startDate DESC",
                Lease.class);
        query.setParameter("tenantId", tenantId);
        return query.getResultList();
    }

    @Override
    public List<Lease> findByStatus(Lease.LeaseStatus status) {
        TypedQuery<Lease> query = entityManager.createQuery(
                "SELECT l FROM Lease l JOIN FETCH l.unit JOIN FETCH l.tenant WHERE l.leaseStatus = :status ORDER BY l.startDate DESC",
                Lease.class);
        query.setParameter("status", status);
        return query.getResultList();
    }
}