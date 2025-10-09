package com.example.springbootapp.dto;

import java.time.LocalDateTime;

public class InventoryDTO {

    private Integer id;
    private Integer productId;
    private String productName;
    private Integer currentStock;
    private Integer minStock;
    private Integer maxStock;
    private String stockStatus;
    private LocalDateTime lastUpdated;

    public InventoryDTO() {
    }

    public InventoryDTO(Integer id, Integer productId, Integer currentStock, Integer minStock, Integer maxStock, LocalDateTime lastUpdated) {
        this.id = id;
        this.productId = productId;
        this.currentStock = currentStock;
        this.minStock = minStock;
        this.maxStock = maxStock;
        this.lastUpdated = lastUpdated;
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

    public Integer getCurrentStock() {
        return currentStock;
    }

    public void setCurrentStock(Integer currentStock) {
        this.currentStock = currentStock;
    }

    public Integer getMinStock() {
        return minStock;
    }

    public void setMinStock(Integer minStock) {
        this.minStock = minStock;
    }

    public Integer getMaxStock() {
        return maxStock;
    }

    public void setMaxStock(Integer maxStock) {
        this.maxStock = maxStock;
    }

    public String getStockStatus() {
        return stockStatus;
    }

    public void setStockStatus(String stockStatus) {
        this.stockStatus = stockStatus;
    }

    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
}
