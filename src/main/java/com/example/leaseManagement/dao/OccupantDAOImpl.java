package com.example.leaseManagement.dao;

import com.example.leaseManagement.entity.Occupant;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class OccupantDAOImpl implements OccupantDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(Occupant occupant) {
        entityManager.persist(occupant);
    }

    @Override
    public Optional<Occupant> findById(Long id) {
        Occupant occupant = entityManager.find(Occupant.class, id);
        return Optional.ofNullable(occupant);
    }

    @Override
    public List<Occupant> findAll() {
        TypedQuery<Occupant> query = entityManager.createQuery(
                "SELECT o FROM Occupant o JOIN FETCH o.unit u ORDER BY o.lastName, o.firstName",
                Occupant.class);
        return query.getResultList();
    }

    @Override
    public void update(Occupant occupant) {
        entityManager.merge(occupant);
    }

    @Override
    public void delete(Long id) {
        Occupant occupant = entityManager.find(Occupant.class, id);
        if (occupant != null) {
            entityManager.remove(occupant);
        }
    }

    @Override
    public List<Occupant> findByUnitId(Long unitId) {
        TypedQuery<Occupant> query = entityManager.createQuery(
                "SELECT o FROM Occupant o WHERE o.unit.unitId = :unitId ORDER BY o.lastName, o.firstName",
                Occupant.class);
        query.setParameter("unitId", unitId);
        return query.getResultList();
    }
}