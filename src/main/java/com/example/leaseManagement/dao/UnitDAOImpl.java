package com.example.leaseManagement.dao;

import com.example.leaseManagement.entity.Unit;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UnitDAOImpl implements UnitDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(Unit unit) {
        entityManager.persist(unit);
    }

    @Override
    public Optional<Unit> findById(Long id) {
        Unit unit = entityManager.find(Unit.class, id);
        return Optional.ofNullable(unit);
    }

    @Override
    public List<Unit> findAll() {
        TypedQuery<Unit> query = entityManager.createQuery(
                "SELECT u FROM Unit u JOIN FETCH u.property", Unit.class);
        return query.getResultList();
    }

    @Override
    public void update(Unit unit) {
        entityManager.merge(unit);
    }

    @Override
    public void delete(Long id) {
        Unit unit = entityManager.find(Unit.class, id);
        if (unit != null) {
            entityManager.remove(unit);
        }
    }

    @Override
    public List<Unit> findByPropertyId(Long propertyId) {
        TypedQuery<Unit> query = entityManager.createQuery(
                "SELECT u FROM Unit u WHERE u.property.propertyId = :propertyId",
                Unit.class);
        query.setParameter("propertyId", propertyId);
        return query.getResultList();
    }

    @Override
    public List<Unit> findByStatus(Unit.UnitStatus status) {
        TypedQuery<Unit> query = entityManager.createQuery(
                "SELECT u FROM Unit u WHERE u.unitStatus = :status",
                Unit.class);
        query.setParameter("status", status);
        return query.getResultList();
    }
}