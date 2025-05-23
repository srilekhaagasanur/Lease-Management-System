package com.property.management.lease_system.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

@Entity
@Table(name = "TENANT_MASTER", schema = "test")
public class Tenant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Tenant_id")
    private Integer id;

    @Column(name = "Tenant_Contact_Number")
    private Long contactNumber;

    @NotNull
    @Size(min = 2, max = 100)
    @Column(name = "Tenant_name")
    private String name;

    @Column(name = "Tenant_Registration_Date")
    private LocalDateTime registrationDate;

    @Column(name = "Tenant_Gender_Type")
    private String gender;

    @Column(name = "Tenant_Active_flag")
    private String activeFlag;

    @Column(name = "Tenant_Company_Name")
    private String companyName;

    @Column(name = "Tenant_Alternate_Contact_Number")
    private Long alternateContactNumber;

    @Column(name = "Tenant_Permanent_Address")
    private String address;

    @Column(name = "Tenant_Country_Name")
    private String country;

    @Column(name = "Created_usr_id")
    private String createdBy;

    @Column(name = "Created_date")
    private LocalDateTime createdDate;

    @Column(name = "Update_usr_id")
    private String updatedBy;

    @Column(name = "Updated_date")
    private LocalDateTime updatedDate;

    // Getters and Setters (generate in IDE)

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(Long contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getActiveFlag() {
        return activeFlag;
    }

    public void setActiveFlag(String activeFlag) {
        this.activeFlag = activeFlag;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Long getAlternateContactNumber() {
        return alternateContactNumber;
    }

    public void setAlternateContactNumber(Long alternateContactNumber) {
        this.alternateContactNumber = alternateContactNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public LocalDateTime getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(LocalDateTime updatedDate) {
        this.updatedDate = updatedDate;
    }


}
