package com.property.management.lease_system.controller;

import com.property.management.lease_system.entity.Lease;
import com.property.management.lease_system.service.LeaseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/leases")
public class LeaseController {

    private final LeaseService leaseService;

    public LeaseController(LeaseService leaseService) {
        this.leaseService = leaseService;
    }

    @GetMapping
    public List<Lease> getAllLeases() {
        return leaseService.getAllLeases();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Lease> getLeaseById(@PathVariable Integer id) {
        Lease lease = leaseService.getLeaseById(id);
        return lease != null ? ResponseEntity.ok(lease) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Lease> addLease(@RequestBody Lease lease) {
        Lease savedLease = leaseService.saveLease(lease);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedLease);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Lease> updateLease(@PathVariable Integer id, @RequestBody Lease updatedLease) {
        Lease existing = leaseService.getLeaseById(id);
        if (existing == null) {
            return ResponseEntity.notFound().build();
        }

        existing.setStartDate(updatedLease.getStartDate());
        existing.setEndDate(updatedLease.getEndDate());
        existing.setMonthlyRent(updatedLease.getMonthlyRent());
        existing.setSecurityDeposit(updatedLease.getSecurityDeposit());
        existing.setTenant(updatedLease.getTenant());
        existing.setProperty(updatedLease.getProperty());

        return ResponseEntity.ok(leaseService.saveLease(existing));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLease(@PathVariable Integer id) {
        if (leaseService.getLeaseById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        leaseService.deleteLease(id);
        return ResponseEntity.noContent().build();
    }
}
