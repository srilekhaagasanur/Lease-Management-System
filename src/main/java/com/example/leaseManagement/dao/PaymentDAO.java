package com.example.leaseManagement.dao;

import com.example.leaseManagement.entity.Payment;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface PaymentDAO {

    void save(Payment payment);

    Optional<Payment> findById(Long id);

    List<Payment> findAll();

    void update(Payment payment);

    void delete(Long id);

    List<Payment> findByLeaseId(Long leaseId);

    List<Payment> findByStatus(Payment.PaymentStatus status);

    List<Payment> findOverduePayments();

    List<Payment> findPaymentsByDateRange(Date startDate, Date endDate);
}