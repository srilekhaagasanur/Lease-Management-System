package com.example.leaseManagement.controller;

import com.example.leaseManagement.entity.Tenant;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/tenants")
public class TenantWebController {

    private final TenantService tenantService;

    public TenantWebController(TenantService tenantService) {
        this.tenantService = tenantService;
    }

    @GetMapping
    public String listTenants(@RequestParam(required = false) String search, Model model) {
        List<Tenant> tenants;
        if (search != null && !search.trim().isEmpty()) {
            tenants = tenantService.searchTenantsByLastName(search);
        } else {
            tenants = tenantService.getAllTenants();
        }
        model.addAttribute("tenants", tenants);
        model.addAttribute("search", search);
        return "tenant-list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("tenant", new Tenant());
        return "tenant-form";
    }

    @PostMapping("/save")
    public String saveTenant(@RequestParam("firstName") String firstName,
                             @RequestParam("lastName") String lastName,
                             @RequestParam("email") String email,
                             @RequestParam("phone") String phone,
                             @RequestParam(value = "phone2", required = false) String phone2,
                             @RequestParam("dateOfBirth") @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateOfBirth,
                             @RequestParam(value = "ssnLastFour", required = false) String ssnLastFour,
                             @RequestParam(value = "driversLicense", required = false) String driversLicense,
                             @RequestParam(value = "vehicleInfo", required = false) String vehicleInfo,
                             @RequestParam(value = "emergencyContactName", required = false) String emergencyContactName,
                             @RequestParam(value = "emergencyContactPhone", required = false) String emergencyContactPhone,
                             @RequestParam(value = "emergencyContactRelationship", required = false) String emergencyContactRelationship,
                             @RequestParam(value = "occupation", required = false) String occupation,
                             @RequestParam(value = "employerName", required = false) String employerName,
                             @RequestParam(value = "notes", required = false) String notes,
                             @RequestParam(value = "tenantId", required = false) Long tenantId) {

        System.out.println("=== SAVING TENANT ===");

        Tenant tenant;
        if (tenantId != null) {
            tenant = tenantService.getTenantById(tenantId)
                    .orElseThrow(() -> new RuntimeException("Tenant not found"));
        } else {
            tenant = new Tenant();
        }

        tenant.setFirstName(firstName);
        tenant.setLastName(lastName);
        tenant.setEmail(email);
        tenant.setPhone(phone);
        tenant.setPhone2(phone2);
        tenant.setDateOfBirth(dateOfBirth);
        tenant.setSsnLastFour(ssnLastFour);
        tenant.setDriversLicense(driversLicense);
        tenant.setVehicleInfo(vehicleInfo);
        tenant.setEmergencyContactName(emergencyContactName);
        tenant.setEmergencyContactPhone(emergencyContactPhone);
        tenant.setEmergencyContactRelationship(emergencyContactRelationship);
        tenant.setOccupation(occupation);
        tenant.setEmployerName(employerName);
        tenant.setNotes(notes);

        tenantService.saveTenant(tenant);

        System.out.println("=== TENANT SAVED ===");

        return "redirect:/tenants";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Tenant tenant = tenantService.getTenantById(id)
                .orElseThrow(() -> new RuntimeException("Tenant not found"));
        model.addAttribute("tenant", tenant);
        return "tenant-form";
    }

    @GetMapping("/delete/{id}")
    public String deleteTenant(@PathVariable Long id) {
        tenantService.deleteTenant(id);
        return "redirect:/tenants";
    }
}