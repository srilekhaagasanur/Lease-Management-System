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
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import java.util.Date;

@Entity
@Table(name = "occupants")
public class Occupant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "occupant_id")
    private Long occupantId;

    @NotNull(message = "Unit is required")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "unit_id", nullable = false)
    private Unit unit;

    @NotBlank(message = "First name is required")
    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Column(name = "last_name", nullable = false, length = 50)
    private String lastName;

    @NotBlank(message = "Relationship to tenant is required")
    @Column(name = "relationship_to_tenant", nullable = false, length = 50)
    private String relationshipToTenant;

    @Past(message = "Date of birth must be in the past")
    @Temporal(TemporalType.DATE)
    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    @Column(name = "phone", length = 20)
    @Pattern(regexp = "^\\+?[1-9]\\d{1,14}$|^$", message = "Invalid phone number format")
    private String phone;

    @Column(name = "email", length = 100)
    private String email;

    @Column(name = "occupation", length = 100)
    private String occupation;

    @Column(name = "vehicle_info", length = 200)
    private String vehicleInfo;

    @Column(name = "notes", columnDefinition = "TEXT")
    private String notes;

    // Default constructor
    public Occupant() {
    }

    // Constructor
    public Occupant(Unit unit, String firstName, String lastName, String relationshipToTenant) {
        this.unit = unit;
        this.firstName = firstName;
        this.lastName = lastName;
        this.relationshipToTenant = relationshipToTenant;
    }

    // Getters and Setters
    public Long getOccupantId() {
        return occupantId;
    }

    public void setOccupantId(Long occupantId) {
        this.occupantId = occupantId;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getRelationshipToTenant() {
        return relationshipToTenant;
    }

    public void setRelationshipToTenant(String relationshipToTenant) {
        this.relationshipToTenant = relationshipToTenant;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getVehicleInfo() {
        return vehicleInfo;
    }

    public void setVehicleInfo(String vehicleInfo) {
        this.vehicleInfo = vehicleInfo;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    // Helper methods
    public String getFullName() {
        return firstName + " " + lastName;
    }

    public String getUnitInfo() {
        return unit != null && unit.getProperty() != null
                ? unit.getProperty().getPropertyName() + " - Unit " + unit.getUnitNumber()
                : "N/A";
    }

    @Override
    public String toString() {
        return "Occupant{" +
                "occupantId=" + occupantId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", relationshipToTenant='" + relationshipToTenant + '\'' +
                '}';
    }
}