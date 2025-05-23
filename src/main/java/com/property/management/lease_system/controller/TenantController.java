package com.property.management.lease_system.controller;

import com.property.management.lease_system.entity.Tenant;
import com.property.management.lease_system.service.TenantService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tenants")
public class TenantController {

    private final TenantService tenantService;

    public TenantController(TenantService tenantService) {
        this.tenantService = tenantService;
    }

    @GetMapping
    public List<Tenant> getAllTenants() {
        return tenantService.getAllTenants();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tenant> getTenantById(@PathVariable Integer id) {
        Tenant tenant = tenantService.getTenantById(id);
        if (tenant == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(tenant);
    }


    @PostMapping
    public ResponseEntity<Tenant> addTenant(@Valid @RequestBody Tenant tenant) {
        Tenant saved = tenantService.saveTenant(tenant);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

//    @PostMapping("/new")
//    public ResponseEntity<Tenant> createTenant(@RequestBody Tenant tenant) {
//        Tenant savedTenant = tenantService.saveTenant(tenant);
//        return new ResponseEntity<>(savedTenant, HttpStatus.CREATED);
//    }

    @PutMapping("/{id}")
    public ResponseEntity<Tenant> updateTenant(@PathVariable Integer id, @RequestBody Tenant updatedTenant) {
        Tenant existingTenant = tenantService.getTenantById(id);
        if (existingTenant == null) {
            return ResponseEntity.notFound().build();
        }

        // Set updated fields (only sample fields shown)
        existingTenant.setName(updatedTenant.getName());
        existingTenant.setContactNumber(updatedTenant.getContactNumber());
        existingTenant.setUpdatedBy(updatedTenant.getUpdatedBy());
        existingTenant.setUpdatedDate(updatedTenant.getUpdatedDate());

        Tenant savedTenant = tenantService.saveTenant(existingTenant);
        return ResponseEntity.ok(savedTenant);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTenant(@PathVariable Integer id) {
        Tenant tenant = tenantService.getTenantById(id);
        if (tenant == null) {
            return ResponseEntity.notFound().build();
        }

        tenantService.deleteTenant(id);
        return ResponseEntity.noContent().build();
    }





}
