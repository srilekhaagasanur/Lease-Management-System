package com.example.leaseManagement.controller;

import com.example.leaseManagement.entity.Payment;
import com.example.leaseManagement.entity.Lease;
import com.example.leaseManagement.service.PaymentService;
import com.example.leaseManagement.service.LeaseService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/payments")
public class PaymentWebController {

    private final PaymentService paymentService;
    private final LeaseService leaseService;

    public PaymentWebController(PaymentService paymentService, LeaseService leaseService) {
        this.paymentService = paymentService;
        this.leaseService = leaseService;
    }

    @GetMapping
    public String listPayments(@RequestParam(required = false) String status, Model model) {
        List<Payment> payments;
        if (status != null && !status.isEmpty()) {
            payments = paymentService.getPaymentsByStatus(Payment.PaymentStatus.valueOf(status));
        } else {
            payments = paymentService.getAllPayments();
        }
        model.addAttribute("payments", payments);
        model.addAttribute("statuses", Payment.PaymentStatus.values());
        model.addAttribute("selectedStatus", status);
        return "payment-list";
    }

    @GetMapping("/overdue")
    public String listOverduePayments(Model model) {
        List<Payment> payments = paymentService.getOverduePayments();
        model.addAttribute("payments", payments);
        model.addAttribute("overdueView", true);
        return "payment-list";
    }

    @GetMapping("/new")
    public String showCreateForm(@RequestParam(required = false) Long leaseId, Model model) {
        Payment payment = new Payment();
        if (leaseId != null) {
            Lease lease = leaseService.getLeaseById(leaseId)
                    .orElseThrow(() -> new RuntimeException("Lease not found"));
            payment.setLease(lease);
            payment.setAmountPaid(lease.getMonthlyRent());
        }
        model.addAttribute("payment", payment);
        model.addAttribute("leases", leaseService.getAllLeases());
        model.addAttribute("paymentMethods", Payment.PaymentMethod.values());
        model.addAttribute("paymentStatuses", Payment.PaymentStatus.values());
        return "payment-form";
    }

    @PostMapping("/save")
    public String savePayment(@RequestParam("leaseId") Long leaseId,
                              @RequestParam("paymentDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date paymentDate,
                              @RequestParam("dueDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date dueDate,
                              @RequestParam("amountPaid") BigDecimal amountPaid,
                              @RequestParam("paymentMethod") Payment.PaymentMethod paymentMethod,
                              @RequestParam("paymentStatus") Payment.PaymentStatus paymentStatus,
                              @RequestParam(value = "transactionReference", required = false) String transactionReference,
                              @RequestParam(value = "notes", required = false) String notes,
                              @RequestParam(value = "paymentId", required = false) Long paymentId) {

        System.out.println("=== SAVING PAYMENT ===");

        Lease lease = leaseService.getLeaseById(leaseId)
                .orElseThrow(() -> new RuntimeException("Lease not found"));

        Payment payment;
        if (paymentId != null) {
            payment = paymentService.getPaymentById(paymentId)
                    .orElseThrow(() -> new RuntimeException("Payment not found"));
        } else {
            payment = new Payment();
        }

        payment.setLease(lease);
        payment.setPaymentDate(paymentDate);
        payment.setDueDate(dueDate);
        payment.setAmountPaid(amountPaid);
        payment.setPaymentMethod(paymentMethod);
        payment.setPaymentStatus(paymentStatus);
        payment.setTransactionReference(transactionReference);
        payment.setNotes(notes);

        paymentService.savePayment(payment);

        System.out.println("=== PAYMENT SAVED ===");

        return "redirect:/payments";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Payment payment = paymentService.getPaymentById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found"));
        model.addAttribute("payment", payment);
        model.addAttribute("leases", leaseService.getAllLeases());
        model.addAttribute("paymentMethods", Payment.PaymentMethod.values());
        model.addAttribute("paymentStatuses", Payment.PaymentStatus.values());
        return "payment-form";
    }

    @GetMapping("/delete/{id}")
    public String deletePayment(@PathVariable Long id) {
        paymentService.deletePayment(id);
        return "redirect:/payments";
    }
}