package com.example.springbootapp.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ProductDTO {

    private Integer id;
    private String name;
    private String description;
    private Integer categoryId;
    private Integer supplierId;
    private BigDecimal unitCost;
    private String reorderFrequency;
    private LocalDateTime createdAt;

    public ProductDTO() {
    }

    public ProductDTO(Integer id, String name, Integer categoryId, Integer supplierId, BigDecimal unitCost, String reorderFrequency, LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.categoryId = categoryId;
        this.supplierId = supplierId;
        this.unitCost = unitCost;
        this.reorderFrequency = reorderFrequency;
        this.createdAt = createdAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }

    public BigDecimal getUnitCost() {
        return unitCost;
    }

    public void setUnitCost(BigDecimal unitCost) {
        this.unitCost = unitCost;
    }

    public String getReorderFrequency() {
        return reorderFrequency;
    }

    public void setReorderFrequency(String reorderFrequency) {
        this.reorderFrequency = reorderFrequency;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
