package com.example.leaseManagement.controller;

import com.example.leaseManagement.entity.MaintenanceRequest;
import com.example.leaseManagement.entity.Unit;
import com.example.leaseManagement.service.MaintenanceRequestService;
import com.example.leaseManagement.service.UnitService;
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
@RequestMapping("/maintenance")
public class MaintenanceRequestWebController {

    private final MaintenanceRequestService maintenanceRequestService;
    private final UnitService unitService;

    public MaintenanceRequestWebController(MaintenanceRequestService maintenanceRequestService,
                                           UnitService unitService) {
        this.maintenanceRequestService = maintenanceRequestService;
        this.unitService = unitService;
    }

    @GetMapping
    public String listRequests(@RequestParam(required = false) String status,
                               @RequestParam(required = false) String priority,
                               Model model) {
        List<MaintenanceRequest> requests;

        if (status != null && !status.isEmpty()) {
            requests = maintenanceRequestService.getRequestsByStatus(
                    MaintenanceRequest.Status.valueOf(status));
        } else if (priority != null && !priority.isEmpty()) {
            requests = maintenanceRequestService.getRequestsByPriority(
                    MaintenanceRequest.Priority.valueOf(priority));
        } else {
            requests = maintenanceRequestService.getAllRequests();
        }

        model.addAttribute("requests", requests);
        model.addAttribute("statuses", MaintenanceRequest.Status.values());
        model.addAttribute("priorities", MaintenanceRequest.Priority.values());
        model.addAttribute("selectedStatus", status);
        model.addAttribute("selectedPriority", priority);
        return "maintenance-list";
    }

    @GetMapping("/pending")
    public String listPendingRequests(Model model) {
        List<MaintenanceRequest> requests = maintenanceRequestService.getPendingRequests();
        model.addAttribute("requests", requests);
        model.addAttribute("pendingView", true);
        return "maintenance-list";
    }

    @GetMapping("/new")
    public String showCreateForm(@RequestParam(required = false) Long unitId, Model model) {
        MaintenanceRequest request = new MaintenanceRequest();
        if (unitId != null) {
            Unit unit = unitService.getUnitById(unitId)
                    .orElseThrow(() -> new RuntimeException("Unit not found"));
            request.setUnit(unit);
        }
        model.addAttribute("request", request);
        model.addAttribute("units", unitService.getAllUnits());
        model.addAttribute("priorities", MaintenanceRequest.Priority.values());
        model.addAttribute("statuses", MaintenanceRequest.Status.values());
        return "maintenance-form";
    }

    @PostMapping("/save")
    public String saveRequest(@RequestParam("unitId") Long unitId,
                              @RequestParam("title") String title,
                              @RequestParam("description") String description,
                              @RequestParam("priority") MaintenanceRequest.Priority priority,
                              @RequestParam("status") MaintenanceRequest.Status status,
                              @RequestParam("requestDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date requestDate,
                              @RequestParam(value = "completedDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date completedDate,
                              @RequestParam(value = "assignedTo", required = false) String assignedTo,
                              @RequestParam(value = "estimatedCost", required = false) BigDecimal estimatedCost,
                              @RequestParam(value = "actualCost", required = false) BigDecimal actualCost,
                              @RequestParam(value = "notes", required = false) String notes,
                              @RequestParam(value = "requestId", required = false) Long requestId) {

        System.out.println("=== SAVING MAINTENANCE REQUEST ===");

        Unit unit = unitService.getUnitById(unitId)
                .orElseThrow(() -> new RuntimeException("Unit not found"));

        MaintenanceRequest request;
        if (requestId != null) {
            request = maintenanceRequestService.getRequestById(requestId)
                    .orElseThrow(() -> new RuntimeException("Request not found"));
        } else {
            request = new MaintenanceRequest();
        }

        request.setUnit(unit);
        request.setTitle(title);
        request.setDescription(description);
        request.setPriority(priority);
        request.setStatus(status);
        request.setRequestDate(requestDate);
        request.setCompletedDate(completedDate);
        request.setAssignedTo(assignedTo);
        request.setEstimatedCost(estimatedCost);
        request.setActualCost(actualCost);
        request.setNotes(notes);

        maintenanceRequestService.saveRequest(request);

        System.out.println("=== MAINTENANCE REQUEST SAVED ===");

        return "redirect:/maintenance";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        MaintenanceRequest request = maintenanceRequestService.getRequestById(id)
                .orElseThrow(() -> new RuntimeException("Request not found"));
        model.addAttribute("request", request);
        model.addAttribute("units", unitService.getAllUnits());
        model.addAttribute("priorities", MaintenanceRequest.Priority.values());
        model.addAttribute("statuses", MaintenanceRequest.Status.values());
        return "maintenance-form";
    }

    @GetMapping("/delete/{id}")
    public String deleteRequest(@PathVariable Long id) {
        maintenanceRequestService.deleteRequest(id);
        return "redirect:/maintenance";
    }
}