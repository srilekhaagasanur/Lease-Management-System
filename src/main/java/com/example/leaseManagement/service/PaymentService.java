package com.example.leaseManagement.service;

import com.example.leaseManagement.entity.Payment;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface PaymentService {

    Payment savePayment(Payment payment);

    Optional<Payment> getPaymentById(Long id);

    List<Payment> getAllPayments();

    Payment updatePayment(Payment payment);

    void deletePayment(Long id);

    List<Payment> getPaymentsByLeaseId(Long leaseId);

    List<Payment> getPaymentsByStatus(Payment.PaymentStatus status);

    List<Payment> getOverduePayments();

    List<Payment> getPaymentsByDateRange(Date startDate, Date endDate);
}