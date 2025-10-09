package com.example.springbootapp.dto;

import java.time.LocalDateTime;

public class StockMovementDTO {

    private Integer id;
    private Integer productId;
    private String productName;
    private Integer quantity;
    private String movementType;
    private String notes;
    private LocalDateTime createdAt;

    public StockMovementDTO() {
    }

    public StockMovementDTO(Integer id, Integer productId, Integer quantity, String movementType, String notes, LocalDateTime createdAt) {
        this.id = id;
        this.productId = productId;
        this.quantity = quantity;
        this.movementType = movementType;
        this.notes = notes;
        this.createdAt = createdAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getMovementType() {
        return movementType;
    }

    public void setMovementType(String movementType) {
        this.movementType = movementType;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
