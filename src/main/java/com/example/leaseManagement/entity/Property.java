package com.example.leaseManagement.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "properties")
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "property_id")
    private Long propertyId;

    @NotBlank(message = "Property name is required")
    @Size(max = 100, message = "Property name cannot exceed 100 characters")
    @Column(name = "property_name", nullable = false, length = 100)
    private String propertyName;

    // Address fields
    @NotBlank(message = "Street address is required")
    @Column(name = "street_address_1", nullable = false, length = 200)
    private String streetAddress1;

    @Column(name = "street_address_2", length = 200)
    private String streetAddress2;

    @NotBlank(message = "City is required")
    @Column(name = "city", nullable = false, length = 100)
    private String city;

    @NotBlank(message = "State is required")
    @Column(name = "state", nullable = false, length = 50)
    private String state;

    @NotBlank(message = "Zip code is required")
    @Pattern(regexp = "^\\d{5}(-\\d{4})?$", message = "Invalid zip code format")
    @Column(name = "zip_code", nullable = false, length = 10)
    private String zipCode;

    @NotBlank(message = "Country is required")
    @Column(name = "country", nullable = false, length = 50)
    private String country;

    // Owner information
    @NotBlank(message = "Owner name is required")
    @Size(max = 100, message = "Owner name cannot exceed 100 characters")
    @Column(name = "owner_name", nullable = false, length = 100)
    private String ownerName;

    @NotBlank(message = "Primary phone number is required")
    @Pattern(regexp = "^\\+?[1-9]\\d{1,14}$", message = "Invalid phone number format")
    @Column(name = "owner_phone", nullable = false, length = 20)
    private String ownerPhone;

    @Column(name = "owner_phone_2", length = 20)
    @Pattern(regexp = "^\\+?[1-9]\\d{1,14}$|^$", message = "Invalid phone number format")
    private String ownerPhone2;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    @Column(name = "owner_email", nullable = false, length = 100)
    private String ownerEmail;

    // Default constructor
    public Property() {
    }

    // Constructor
    public Property(String propertyName, String streetAddress1, String city,
                    String state, String zipCode, String country, String ownerName,
                    String ownerPhone, String ownerEmail) {
        this.propertyName = propertyName;
        this.streetAddress1 = streetAddress1;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.country = country;
        this.ownerName = ownerName;
        this.ownerPhone = ownerPhone;
        this.ownerEmail = ownerEmail;
    }

    // One-to-Many relationship with Units
    @OneToMany(mappedBy = "property", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Unit> units = new ArrayList<>();

    // Getters and Setters
    public Long getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(Long propertyId) {
        this.propertyId = propertyId;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getStreetAddress1() {
        return streetAddress1;
    }

    public void setStreetAddress1(String streetAddress1) {
        this.streetAddress1 = streetAddress1;
    }

    public String getStreetAddress2() {
        return streetAddress2;
    }

    public void setStreetAddress2(String streetAddress2) {
        this.streetAddress2 = streetAddress2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getOwnerPhone() {
        return ownerPhone;
    }

    public void setOwnerPhone(String ownerPhone) {
        this.ownerPhone = ownerPhone;
    }

    public String getOwnerPhone2() {
        return ownerPhone2;
    }

    public void setOwnerPhone2(String ownerPhone2) {
        this.ownerPhone2 = ownerPhone2;
    }

    public String getOwnerEmail() {
        return ownerEmail;
    }

    public void setOwnerEmail(String ownerEmail) {
        this.ownerEmail = ownerEmail;
    }

    public List<Unit> getUnits() {
        return units;
    }

    public void setUnits(List<Unit> units) {
        this.units = units;
    }

    // Helper method to add a unit
    public void addUnit(Unit unit) {
        units.add(unit);
        unit.setProperty(this);
    }

    // Helper method to remove a unit
    public void removeUnit(Unit unit) {
        units.remove(unit);
        unit.setProperty(null);
    }

    // Helper method to get full address
    public String getFullAddress() {
        StringBuilder address = new StringBuilder();
        address.append(streetAddress1);
        if (streetAddress2 != null && !streetAddress2.isEmpty()) {
            address.append(", ").append(streetAddress2);
        }
        address.append(", ").append(city);
        address.append(", ").append(state);
        address.append(" ").append(zipCode);
        address.append(", ").append(country);
        return address.toString();
    }

    @Override
    public String toString() {
        return "Property{" +
                "propertyId=" + propertyId +
                ", propertyName='" + propertyName + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", ownerName='" + ownerName + '\'' +
                '}';
    }
}