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
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "maintenance_requests")
public class MaintenanceRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "request_id")
    private Long requestId;

    @NotNull(message = "Unit is required")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "unit_id", nullable = false)
    private Unit unit;

    @NotBlank(message = "Title is required")
    @Column(name = "title", nullable = false, length = 200)
    private String title;

    @NotBlank(message = "Description is required")
    @Column(name = "description", nullable = false, columnDefinition = "TEXT")
    private String description;

    @NotNull(message = "Priority is required")
    @Enumerated(EnumType.STRING)
    @Column(name = "priority", nullable = false, length = 20)
    private Priority priority;

    @NotNull(message = "Status is required")
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 20)
    private Status status;

    @NotNull(message = "Request date is required")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "request_date", nullable = false)
    private Date requestDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "completed_date")
    private Date completedDate;

    @Column(name = "assigned_to", length = 100)
    private String assignedTo;

    @Column(name = "estimated_cost", precision = 10, scale = 2)
    private java.math.BigDecimal estimatedCost;

    @Column(name = "actual_cost", precision = 10, scale = 2)
    private java.math.BigDecimal actualCost;

    @Column(name = "notes", columnDefinition = "TEXT")
    private String notes;

    // Enums
    public enum Priority {
        LOW,
        MEDIUM,
        HIGH,
        URGENT
    }

    public enum Status {
        OPEN,
        IN_PROGRESS,
        COMPLETED,
        CANCELLED
    }

    // Default constructor
    public MaintenanceRequest() {
        this.requestDate = new Date();
        this.status = Status.OPEN;
    }

    // Constructor
    public MaintenanceRequest(Unit unit, String title, String description,
                              Priority priority, Status status) {
        this.unit = unit;
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.status = status;
        this.requestDate = new Date();
    }

    // Getters and Setters
    public Long getRequestId() {
        return requestId;
    }

    public void setRequestId(Long requestId) {
        this.requestId = requestId;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
        if (status == Status.COMPLETED && this.completedDate == null) {
            this.completedDate = new Date();
        }
    }

    public Date getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }

    public Date getCompletedDate() {
        return completedDate;
    }

    public void setCompletedDate(Date completedDate) {
        this.completedDate = completedDate;
    }

    public String getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(String assignedTo) {
        this.assignedTo = assignedTo;
    }

    public java.math.BigDecimal getEstimatedCost() {
        return estimatedCost;
    }

    public void setEstimatedCost(java.math.BigDecimal estimatedCost) {
        this.estimatedCost = estimatedCost;
    }

    public java.math.BigDecimal getActualCost() {
        return actualCost;
    }

    public void setActualCost(java.math.BigDecimal actualCost) {
        this.actualCost = actualCost;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    // Helper methods
    public String getPropertyName() {
        return unit != null && unit.getProperty() != null
                ? unit.getProperty().getPropertyName()
                : "N/A";
    }

    public String getUnitNumber() {
        return unit != null ? unit.getUnitNumber() : "N/A";
    }

    public String getUnitInfo() {
        return getPropertyName() + " - Unit " + getUnitNumber();
    }

    @Override
    public String toString() {
        return "MaintenanceRequest{" +
                "requestId=" + requestId +
                ", title='" + title + '\'' +
                ", priority=" + priority +
                ", status=" + status +
                ", requestDate=" + requestDate +
                '}';
    }
}