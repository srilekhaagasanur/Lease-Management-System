package com.example.leaseManagement.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.FetchType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import java.math.BigDecimal;

@Entity
@Table(name = "units")
public class Unit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "unit_id")
    private Long unitId;

    @NotBlank(message = "Unit number is required")
    @Column(name = "unit_number", nullable = false, length = 50)
    private String unitNumber;

    @NotNull(message = "Number of bedrooms is required")
    @Min(value = 0, message = "Bedrooms cannot be negative")
    @Column(name = "bedrooms", nullable = false)
    private Integer bedrooms;

    @NotNull(message = "Number of bathrooms is required")
    @DecimalMin(value = "0.0", message = "Bathrooms cannot be negative")
    @Column(name = "bathrooms", nullable = false)
    private BigDecimal bathrooms;

    @NotNull(message = "Square footage is required")
    @DecimalMin(value = "1.0", message = "Square footage must be positive")
    @Column(name = "square_footage", nullable = false)
    private BigDecimal squareFootage;

    @NotNull(message = "Monthly rent is required")
    @DecimalMin(value = "0.0", message = "Rent cannot be negative")
    @Column(name = "monthly_rent", nullable = false, precision = 10, scale = 2)
    private BigDecimal monthlyRent;

    @NotNull(message = "Unit status is required")
    @Enumerated(EnumType.STRING)
    @Column(name = "unit_status", nullable = false, length = 20)
    private UnitStatus unitStatus;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    // Many-to-One relationship with Property
    @NotNull(message = "Property is required")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "property_id", nullable = false)
    private Property property;

    // Enum for Unit Status
    public enum UnitStatus {
        AVAILABLE,
        OCCUPIED,
        MAINTENANCE,
        UNAVAILABLE
    }

    // Default constructor
    public Unit() {
    }

    // Constructor
    public Unit(String unitNumber, Integer bedrooms, BigDecimal bathrooms,
                BigDecimal squareFootage, BigDecimal monthlyRent,
                UnitStatus unitStatus, Property property) {
        this.unitNumber = unitNumber;
        this.bedrooms = bedrooms;
        this.bathrooms = bathrooms;
        this.squareFootage = squareFootage;
        this.monthlyRent = monthlyRent;
        this.unitStatus = unitStatus;
        this.property = property;
    }

    // Getters and Setters
    public Long getUnitId() {
        return unitId;
    }

    public void setUnitId(Long unitId) {
        this.unitId = unitId;
    }

    public String getUnitNumber() {
        return unitNumber;
    }

    public void setUnitNumber(String unitNumber) {
        this.unitNumber = unitNumber;
    }

    public Integer getBedrooms() {
        return bedrooms;
    }

    public void setBedrooms(Integer bedrooms) {
        this.bedrooms = bedrooms;
    }

    public BigDecimal getBathrooms() {
        return bathrooms;
    }

    public void setBathrooms(BigDecimal bathrooms) {
        this.bathrooms = bathrooms;
    }

    public BigDecimal getSquareFootage() {
        return squareFootage;
    }

    public void setSquareFootage(BigDecimal squareFootage) {
        this.squareFootage = squareFootage;
    }

    public BigDecimal getMonthlyRent() {
        return monthlyRent;
    }

    public void setMonthlyRent(BigDecimal monthlyRent) {
        this.monthlyRent = monthlyRent;
    }

    public UnitStatus getUnitStatus() {
        return unitStatus;
    }

    public void setUnitStatus(UnitStatus unitStatus) {
        this.unitStatus = unitStatus;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    @Override
    public String toString() {
        return "Unit{" +
                "unitId=" + unitId +
                ", unitNumber='" + unitNumber + '\'' +
                ", bedrooms=" + bedrooms +
                ", bathrooms=" + bathrooms +
                ", monthlyRent=" + monthlyRent +
                ", unitStatus=" + unitStatus +
                '}';
    }
}