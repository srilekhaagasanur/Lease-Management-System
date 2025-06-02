package com.property.management.lease_system.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "PROPERTY_MASTER", schema = "test")
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Property_id")
    private Integer id;

    @Column(name = "Property_Name", nullable = false)
    private String name;

    @Column(name = "Property_Type")
    private String type;

    @Column(name = "Property_Address")
    private String address;

    @Column(name = "Property_City")
    private String city;

    @Column(name = "Property_Country")
    private String country;

    @Column(name = "Created_usr_id")
    private String createdBy;

    @Column(name = "Created_date")
    private LocalDateTime createdDate;

    @Column(name = "Update_usr_id")
    private String updatedBy;

    @Column(name = "Updated_date")
    private LocalDateTime updatedDate;

    // Getters
    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public LocalDateTime getUpdatedDate() {
        return updatedDate;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public void setUpdatedDate(LocalDateTime updatedDate) {
        this.updatedDate = updatedDate;
    }


}
