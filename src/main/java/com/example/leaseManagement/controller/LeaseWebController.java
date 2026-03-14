package com.example.leaseManagement.controller;

import com.example.leaseManagement.entity.Lease;
import com.example.leaseManagement.entity.Unit;
import com.example.leaseManagement.entity.Tenant;
import com.example.leaseManagement.service.LeaseService;
import com.example.leaseManagement.service.UnitService;
import com.example.leaseManagement.service.TenantService;
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
@RequestMapping("/leases")
public class LeaseWebController {

    private final LeaseService leaseService;
    private final UnitService unitService;
    private final TenantService tenantService;

    public LeaseWebController(LeaseService leaseService, UnitService unitService, TenantService tenantService) {
        this.leaseService = leaseService;
        this.unitService = unitService;
        this.tenantService = tenantService;
    }

    @GetMapping
    public String listLeases(@RequestParam(required = false) String status, Model model) {
        List<Lease> leases;
        if (status != null && !status.isEmpty()) {
            leases = leaseService.getLeasesByStatus(Lease.LeaseStatus.valueOf(status));
        } else {
            leases = leaseService.getAllLeases();
        }
        model.addAttribute("leases", leases);
        model.addAttribute("statuses", Lease.LeaseStatus.values());
        model.addAttribute("selectedStatus", status);
        return "lease-list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("lease", new Lease());
        model.addAttribute("units", unitService.getAllUnits());
        model.addAttribute("tenants", tenantService.getAllTenants());
        model.addAttribute("statuses", Lease.LeaseStatus.values());
        return "lease-form";
    }

    @PostMapping("/save")
    public String saveLease(@RequestParam("unitId") Long unitId,
                            @RequestParam("tenantId") Long tenantId,
                            @RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
                            @RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
                            @RequestParam("monthlyRent") BigDecimal monthlyRent,
                            @RequestParam("securityDeposit") BigDecimal securityDeposit,
                            @RequestParam("leaseStatus") Lease.LeaseStatus leaseStatus,
                            @RequestParam(value = "notes", required = false) String notes,
                            @RequestParam(value = "leaseId", required = false) Long leaseId) {

        System.out.println("=== SAVING LEASE ===");

        Unit unit = unitService.getUnitById(unitId)
                .orElseThrow(() -> new RuntimeException("Unit not found"));
        Tenant tenant = tenantService.getTenantById(tenantId)
                .orElseThrow(() -> new RuntimeException("Tenant not found"));

        Lease lease;
        if (leaseId != null) {
            lease = leaseService.getLeaseById(leaseId)
                    .orElseThrow(() -> new RuntimeException("Lease not found"));
        } else {
            lease = new Lease();
        }

        lease.setUnit(unit);
        lease.setTenant(tenant);
        lease.setStartDate(startDate);
        lease.setEndDate(endDate);
        lease.setMonthlyRent(monthlyRent);
        lease.setSecurityDeposit(securityDeposit);
        lease.setLeaseStatus(leaseStatus);
        lease.setNotes(notes);

        leaseService.saveLease(lease);

        System.out.println("=== LEASE SAVED ===");

        return "redirect:/leases";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Lease lease = leaseService.getLeaseById(id)
                .orElseThrow(() -> new RuntimeException("Lease not found"));
        model.addAttribute("lease", lease);
        model.addAttribute("units", unitService.getAllUnits());
        model.addAttribute("tenants", tenantService.getAllTenants());
        model.addAttribute("statuses", Lease.LeaseStatus.values());
        return "lease-form";
    }

    @GetMapping("/delete/{id}")
    public String deleteLease(@PathVariable Long id) {
        leaseService.deleteLease(id);
        return "redirect:/leases";
    }
}