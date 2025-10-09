package com.example.springbootapp.dto;

import java.math.BigDecimal;

import java.util.Map;

public class LowStockSummaryDTO {
    private Integer productId;
    private String productName;
    private Integer currentStock;
    private Integer minStock;
    private BigDecimal unitCost;
    private String reorderFrequency;
    private int totalLowStockItems;
    private Map<String, Integer> lowStockByCategory;

    public LowStockSummaryDTO() {}

    public LowStockSummaryDTO(Integer productId, String productName, Integer currentStock,
                             Integer minStock, BigDecimal unitCost, String reorderFrequency) {
        this.productId = productId;
        this.productName = productName;
        this.currentStock = currentStock;
        this.minStock = minStock;
        this.unitCost = unitCost;
        this.reorderFrequency = reorderFrequency;
    }

    // Getters and setters
    public Integer getProductId() { return productId; }
    public void setProductId(Integer productId) { this.productId = productId; }

    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }

    public Integer getCurrentStock() { return currentStock; }
    public void setCurrentStock(Integer currentStock) { this.currentStock = currentStock; }

    public Integer getMinStock() { return minStock; }
    public void setMinStock(Integer minStock) { this.minStock = minStock; }

    public BigDecimal getUnitCost() { return unitCost; }
    public void setUnitCost(BigDecimal unitCost) { this.unitCost = unitCost; }

    public String getReorderFrequency() { return reorderFrequency; }
    public void setReorderFrequency(String reorderFrequency) { this.reorderFrequency = reorderFrequency; }

    public int getTotalLowStockItems() { return totalLowStockItems; }
    public void setTotalLowStockItems(int totalLowStockItems) { this.totalLowStockItems = totalLowStockItems; }

    public Map<String, Integer> getLowStockByCategory() { return lowStockByCategory; }
    public void setLowStockByCategory(Map<String, Integer> lowStockByCategory) { this.lowStockByCategory = lowStockByCategory; }
}
