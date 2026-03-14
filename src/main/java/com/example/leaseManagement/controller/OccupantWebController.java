package com.example.leaseManagement.controller;

import com.example.leaseManagement.entity.Occupant;
import com.example.leaseManagement.entity.Unit;
import com.example.leaseManagement.service.OccupantService;
import com.example.leaseManagement.service.UnitService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/occupants")
public class OccupantWebController {

    private final OccupantService occupantService;
    private final UnitService unitService;

    public OccupantWebController(OccupantService occupantService, UnitService unitService) {
        this.occupantService = occupantService;
        this.unitService = unitService;
    }

    @GetMapping
    public String listOccupants(@RequestParam(required = false) Long unitId, Model model) {
        List<Occupant> occupants;
        if (unitId != null) {
            occupants = occupantService.getOccupantsByUnitId(unitId);
            Unit unit = unitService.getUnitById(unitId)
                    .orElseThrow(() -> new RuntimeException("Unit not found"));
            model.addAttribute("selectedUnit", unit);
        } else {
            occupants = occupantService.getAllOccupants();
        }
        model.addAttribute("occupants", occupants);
        model.addAttribute("units", unitService.getAllUnits());
        return "occupant-list";
    }

    @GetMapping("/new")
    public String showCreateForm(@RequestParam(required = false) Long unitId, Model model) {
        Occupant occupant = new Occupant();
        if (unitId != null) {
            Unit unit = unitService.getUnitById(unitId)
                    .orElseThrow(() -> new RuntimeException("Unit not found"));
            occupant.setUnit(unit);
        }
        model.addAttribute("occupant", occupant);
        model.addAttribute("units", unitService.getAllUnits());
        return "occupant-form";
    }

    @PostMapping("/save")
    public String saveOccupant(@RequestParam("unitId") Long unitId,
                               @RequestParam("firstName") String firstName,
                               @RequestParam("lastName") String lastName,
                               @RequestParam("relationshipToTenant") String relationshipToTenant,
                               @RequestParam(value = "dateOfBirth", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateOfBirth,
                               @RequestParam(value = "phone", required = false) String phone,
                               @RequestParam(value = "email", required = false) String email,
                               @RequestParam(value = "occupation", required = false) String occupation,
                               @RequestParam(value = "vehicleInfo", required = false) String vehicleInfo,
                               @RequestParam(value = "notes", required = false) String notes,
                               @RequestParam(value = "occupantId", required = false) Long occupantId) {

        System.out.println("=== SAVING OCCUPANT ===");

        Unit unit = unitService.getUnitById(unitId)
                .orElseThrow(() -> new RuntimeException("Unit not found"));

        Occupant occupant;
        if (occupantId != null) {
            occupant = occupantService.getOccupantById(occupantId)
                    .orElseThrow(() -> new RuntimeException("Occupant not found"));
        } else {
            occupant = new Occupant();
        }

        occupant.setUnit(unit);
        occupant.setFirstName(firstName);
        occupant.setLastName(lastName);
        occupant.setRelationshipToTenant(relationshipToTenant);
        occupant.setDateOfBirth(dateOfBirth);
        occupant.setPhone(phone);
        occupant.setEmail(email);
        occupant.setOccupation(occupation);
        occupant.setVehicleInfo(vehicleInfo);
        occupant.setNotes(notes);

        occupantService.saveOccupant(occupant);

        System.out.println("=== OCCUPANT SAVED ===");

        return "redirect:/occupants?unitId=" + unitId;
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Occupant occupant = occupantService.getOccupantById(id)
                .orElseThrow(() -> new RuntimeException("Occupant not found"));
        model.addAttribute("occupant", occupant);
        model.addAttribute("units", unitService.getAllUnits());
        return "occupant-form";
    }

    @GetMapping("/delete/{id}")
    public String deleteOccupant(@PathVariable Long id) {
        Occupant occupant = occupantService.getOccupantById(id)
                .orElseThrow(() -> new RuntimeException("Occupant not found"));
        Long unitId = occupant.getUnit().getUnitId();
        occupantService.deleteOccupant(id);
        return "redirect:/occupants?unitId=" + unitId;
    }
}