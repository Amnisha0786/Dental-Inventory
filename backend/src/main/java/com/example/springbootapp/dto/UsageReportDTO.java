package com.example.springbootapp.dto;

import java.time.LocalDateTime;

import java.util.List;

public class UsageReportDTO {
    private Integer productId;
    private String productName;
    private Integer totalUsed;
    private LocalDateTime lastUsed;
    private String usageFrequency;
    private int totalProducts;
    private int totalMovements;
    private List<StockMovementDTO> recentMovements;

    public UsageReportDTO() {}

    public UsageReportDTO(Integer productId, String productName, Integer totalUsed,
                         LocalDateTime lastUsed, String usageFrequency) {
        this.productId = productId;
        this.productName = productName;
        this.totalUsed = totalUsed;
        this.lastUsed = lastUsed;
        this.usageFrequency = usageFrequency;
    }

    // Getters and setters
    public Integer getProductId() { return productId; }
    public void setProductId(Integer productId) { this.productId = productId; }

    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }

    public Integer getTotalUsed() { return totalUsed; }
    public void setTotalUsed(Integer totalUsed) { this.totalUsed = totalUsed; }

    public LocalDateTime getLastUsed() { return lastUsed; }
    public void setLastUsed(LocalDateTime lastUsed) { this.lastUsed = lastUsed; }

    public String getUsageFrequency() { return usageFrequency; }
    public void setUsageFrequency(String usageFrequency) { this.usageFrequency = usageFrequency; }

    public int getTotalProducts() { return totalProducts; }
    public void setTotalProducts(int totalProducts) { this.totalProducts = totalProducts; }

    public int getTotalMovements() { return totalMovements; }
    public void setTotalMovements(int totalMovements) { this.totalMovements = totalMovements; }

    public List<StockMovementDTO> getRecentMovements() { return recentMovements; }
    public void setRecentMovements(List<StockMovementDTO> recentMovements) { this.recentMovements = recentMovements; }
}
