package com.example.leaseManagement.dao;

import com.example.leaseManagement.entity.Payment;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public class PaymentDAOImpl implements PaymentDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(Payment payment) {
        entityManager.persist(payment);
    }

    @Override
    public Optional<Payment> findById(Long id) {
        Payment payment = entityManager.find(Payment.class, id);
        return Optional.ofNullable(payment);
    }

    @Override
    public List<Payment> findAll() {
        TypedQuery<Payment> query = entityManager.createQuery(
                "SELECT p FROM Payment p JOIN FETCH p.lease l JOIN FETCH l.unit JOIN FETCH l.tenant ORDER BY p.paymentDate DESC",
                Payment.class);
        return query.getResultList();
    }

    @Override
    public void update(Payment payment) {
        entityManager.merge(payment);
    }

    @Override
    public void delete(Long id) {
        Payment payment = entityManager.find(Payment.class, id);
        if (payment != null) {
            entityManager.remove(payment);
        }
    }

    @Override
    public List<Payment> findByLeaseId(Long leaseId) {
        TypedQuery<Payment> query = entityManager.createQuery(
                "SELECT p FROM Payment p WHERE p.lease.leaseId = :leaseId ORDER BY p.paymentDate DESC",
                Payment.class);
        query.setParameter("leaseId", leaseId);
        return query.getResultList();
    }

    @Override
    public List<Payment> findByStatus(Payment.PaymentStatus status) {
        TypedQuery<Payment> query = entityManager.createQuery(
                "SELECT p FROM Payment p JOIN FETCH p.lease l JOIN FETCH l.tenant WHERE p.paymentStatus = :status ORDER BY p.dueDate",
                Payment.class);
        query.setParameter("status", status);
        return query.getResultList();
    }

    @Override
    public List<Payment> findOverduePayments() {
        TypedQuery<Payment> query = entityManager.createQuery(
                "SELECT p FROM Payment p JOIN FETCH p.lease l JOIN FETCH l.tenant " +
                        "WHERE p.paymentStatus IN (:pendingStatus, :lateStatus) AND p.dueDate < :today " +
                        "ORDER BY p.dueDate ASC",
                Payment.class);
        query.setParameter("pendingStatus", Payment.PaymentStatus.PENDING);
        query.setParameter("lateStatus", Payment.PaymentStatus.LATE);
        query.setParameter("today", new Date());
        return query.getResultList();
    }

    @Override
    public List<Payment> findPaymentsByDateRange(Date startDate, Date endDate) {
        TypedQuery<Payment> query = entityManager.createQuery(
                "SELECT p FROM Payment p JOIN FETCH p.lease l JOIN FETCH l.tenant " +
                        "WHERE p.paymentDate BETWEEN :startDate AND :endDate " +
                        "ORDER BY p.paymentDate DESC",
                Payment.class);
        query.setParameter("startDate", startDate);
        query.setParameter("endDate", endDate);
        return query.getResultList();
    }
}