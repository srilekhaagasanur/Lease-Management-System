package com.example.leaseManagement.dao;

import com.example.leaseManagement.entity.MaintenanceRequest;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class MaintenanceRequestDAOImpl implements MaintenanceRequestDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(MaintenanceRequest request) {
        entityManager.persist(request);
    }

    @Override
    public Optional<MaintenanceRequest> findById(Long id) {
        MaintenanceRequest request = entityManager.find(MaintenanceRequest.class, id);
        return Optional.ofNullable(request);
    }

    @Override
    public List<MaintenanceRequest> findAll() {
        TypedQuery<MaintenanceRequest> query = entityManager.createQuery(
                "SELECT m FROM MaintenanceRequest m JOIN FETCH m.unit u ORDER BY m.requestDate DESC",
                MaintenanceRequest.class);
        return query.getResultList();
    }

    @Override
    public void update(MaintenanceRequest request) {
        entityManager.merge(request);
    }

    @Override
    public void delete(Long id) {
        MaintenanceRequest request = entityManager.find(MaintenanceRequest.class, id);
        if (request != null) {
            entityManager.remove(request);
        }
    }

    @Override
    public List<MaintenanceRequest> findByUnitId(Long unitId) {
        TypedQuery<MaintenanceRequest> query = entityManager.createQuery(
                "SELECT m FROM MaintenanceRequest m WHERE m.unit.unitId = :unitId ORDER BY m.requestDate DESC",
                MaintenanceRequest.class);
        query.setParameter("unitId", unitId);
        return query.getResultList();
    }

    @Override
    public List<MaintenanceRequest> findByStatus(MaintenanceRequest.Status status) {
        TypedQuery<MaintenanceRequest> query = entityManager.createQuery(
                "SELECT m FROM MaintenanceRequest m JOIN FETCH m.unit WHERE m.status = :status ORDER BY m.requestDate DESC",
                MaintenanceRequest.class);
        query.setParameter("status", status);
        return query.getResultList();
    }

    @Override
    public List<MaintenanceRequest> findByPriority(MaintenanceRequest.Priority priority) {
        TypedQuery<MaintenanceRequest> query = entityManager.createQuery(
                "SELECT m FROM MaintenanceRequest m JOIN FETCH m.unit WHERE m.priority = :priority ORDER BY m.requestDate DESC",
                MaintenanceRequest.class);
        query.setParameter("priority", priority);
        return query.getResultList();
    }

    @Override
    public List<MaintenanceRequest> findPendingRequests() {
        TypedQuery<MaintenanceRequest> query = entityManager.createQuery(
                "SELECT m FROM MaintenanceRequest m JOIN FETCH m.unit " +
                        "WHERE m.status IN (:openStatus, :inProgressStatus) " +
                        "ORDER BY m.priority DESC, m.requestDate ASC",
                MaintenanceRequest.class);
        query.setParameter("openStatus", MaintenanceRequest.Status.OPEN);
        query.setParameter("inProgressStatus", MaintenanceRequest.Status.IN_PROGRESS);
        return query.getResultList();
    }
}