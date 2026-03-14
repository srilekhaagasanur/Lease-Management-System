package com.example.leaseManagement.controller;

import com.example.leaseManagement.entity.Unit;
import com.example.leaseManagement.entity.Property;
import com.example.leaseManagement.service.UnitService;
import com.example.leaseManagement.service.PropertyService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("/units")
public class UnitWebController {

    private final UnitService unitService;
    private final PropertyService propertyService;

    public UnitWebController(UnitService unitService, PropertyService propertyService) {
        this.unitService = unitService;
        this.propertyService = propertyService;
    }

    @GetMapping
    public String listUnits(@RequestParam(required = false) Long propertyId, Model model) {
        List<Unit> units;
        if (propertyId != null) {
            units = unitService.getUnitsByPropertyId(propertyId);
            Property property = propertyService.getPropertyById(propertyId)
                    .orElseThrow(() -> new RuntimeException("Property not found"));
            model.addAttribute("selectedProperty", property);
        } else {
            units = unitService.getAllUnits();
        }
        model.addAttribute("units", units);
        model.addAttribute("properties", propertyService.getAllProperties());
        return "unit-list";
    }

    @GetMapping("/new")
    public String showCreateForm(@RequestParam(required = false) Long propertyId, Model model) {
        Unit unit = new Unit();
        if (propertyId != null) {
            Property property = propertyService.getPropertyById(propertyId)
                    .orElseThrow(() -> new RuntimeException("Property not found"));
            unit.setProperty(property);
        }
        model.addAttribute("unit", unit);
        model.addAttribute("properties", propertyService.getAllProperties());
        model.addAttribute("statuses", Unit.UnitStatus.values());
        return "unit-form";
    }

    @PostMapping("/save")
    public String saveUnit(@RequestParam("propertyId") Long propertyId,
                           @RequestParam("unitNumber") String unitNumber,
                           @RequestParam("bedrooms") Integer bedrooms,
                           @RequestParam("bathrooms") BigDecimal bathrooms,
                           @RequestParam("squareFootage") BigDecimal squareFootage,
                           @RequestParam("monthlyRent") BigDecimal monthlyRent,
                           @RequestParam("unitStatus") Unit.UnitStatus unitStatus,
                           @RequestParam(value = "description", required = false) String description,
                           @RequestParam(value = "unitId", required = false) Long unitId) {

        System.out.println("=== SAVING UNIT ===");

        Property property = propertyService.getPropertyById(propertyId)
                .orElseThrow(() -> new RuntimeException("Property not found"));

        Unit unit;
        if (unitId != null) {
            unit = unitService.getUnitById(unitId)
                    .orElseThrow(() -> new RuntimeException("Unit not found"));
        } else {
            unit = new Unit();
        }

        unit.setProperty(property);
        unit.setUnitNumber(unitNumber);
        unit.setBedrooms(bedrooms);
        unit.setBathrooms(bathrooms);
        unit.setSquareFootage(squareFootage);
        unit.setMonthlyRent(monthlyRent);
        unit.setUnitStatus(unitStatus);
        unit.setDescription(description);

        unitService.saveUnit(unit);

        System.out.println("=== UNIT SAVED ===");

        return "redirect:/units?propertyId=" + propertyId;
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Unit unit = unitService.getUnitById(id)
                .orElseThrow(() -> new RuntimeException("Unit not found"));
        model.addAttribute("unit", unit);
        model.addAttribute("properties", propertyService.getAllProperties());
        model.addAttribute("statuses", Unit.UnitStatus.values());
        return "unit-form";
    }

    @GetMapping("/delete/{id}")
    public String deleteUnit(@PathVariable Long id) {
        Unit unit = unitService.getUnitById(id)
                .orElseThrow(() -> new RuntimeException("Unit not found"));
        Long propertyId = unit.getProperty().getPropertyId();
        unitService.deleteUnit(id);
        return "redirect:/units?propertyId=" + propertyId;
    }
}