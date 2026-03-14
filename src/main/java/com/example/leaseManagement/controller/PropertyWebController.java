package com.example.leaseManagement.controller;

import com.example.leaseManagement.entity.Property;
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

import java.util.List;

@Controller
@RequestMapping("/properties")
public class PropertyWebController {

    private final PropertyService propertyService;

    public PropertyWebController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    @GetMapping
    public String listProperties(Model model) {
        List<Property> properties = propertyService.getAllProperties();
        model.addAttribute("properties", properties);
        return "property-list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("property", new Property());
        return "property-form";
    }

    @PostMapping("/save")
    public String saveProperty(@Valid @ModelAttribute("property") Property property,
                               BindingResult result) {
        if (result.hasErrors()) {
            return "property-form";
        }
        propertyService.saveProperty(property);
        return "redirect:/properties";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Property property = propertyService.getPropertyById(id)
                .orElseThrow(() -> new RuntimeException("Property not found"));
        model.addAttribute("property", property);
        return "property-form";
    }

    @GetMapping("/delete/{id}")
    public String deleteProperty(@PathVariable Long id) {
        propertyService.deleteProperty(id);
        return "redirect:/properties";
    }
}