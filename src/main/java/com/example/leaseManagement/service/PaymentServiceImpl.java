package com.example.leaseManagement.service;

import com.example.leaseManagement.dao.PaymentDAO;
import com.example.leaseManagement.entity.Payment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentDAO paymentDAO;

    public PaymentServiceImpl(PaymentDAO paymentDAO) {
        this.paymentDAO = paymentDAO;
    }

    @Override
    @Transactional
    public Payment savePayment(Payment payment) {
        paymentDAO.save(payment);
        return payment;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Payment> getPaymentById(Long id) {
        return paymentDAO.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Payment> getAllPayments() {
        return paymentDAO.findAll();
    }

    @Override
    @Transactional
    public Payment updatePayment(Payment payment) {
        paymentDAO.update(payment);
        return payment;
    }

    @Override
    @Transactional
    public void deletePayment(Long id) {
        paymentDAO.delete(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Payment> getPaymentsByLeaseId(Long leaseId) {
        return paymentDAO.findByLeaseId(leaseId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Payment> getPaymentsByStatus(Payment.PaymentStatus status) {
        return paymentDAO.findByStatus(status);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Payment> getOverduePayments() {
        return paymentDAO.findOverduePayments();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Payment> getPaymentsByDateRange(Date startDate, Date endDate) {
        return paymentDAO.findPaymentsByDateRange(startDate, endDate);
    }
}