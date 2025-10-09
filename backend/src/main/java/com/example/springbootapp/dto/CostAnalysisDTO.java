package com.example.springbootapp.dto;

import java.math.BigDecimal;
import java.util.Map;

public class CostAnalysisDTO {
    private String category;
    private BigDecimal totalCost;
    private Integer itemCount;
    private BigDecimal totalInventoryValue;
    private Map<String, BigDecimal> valueByCategory;

    public CostAnalysisDTO() {}

    public CostAnalysisDTO(String category, BigDecimal totalCost, Integer itemCount) {
        this.category = category;
        this.totalCost = totalCost;
        this.itemCount = itemCount;
    }

    // Getters and setters
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public BigDecimal getTotalCost() { return totalCost; }
    public void setTotalCost(BigDecimal totalCost) { this.totalCost = totalCost; }

    public Integer getItemCount() { return itemCount; }
    public void setItemCount(Integer itemCount) { this.itemCount = itemCount; }

    public BigDecimal getTotalInventoryValue() { return totalInventoryValue; }
    public void setTotalInventoryValue(BigDecimal totalInventoryValue) { this.totalInventoryValue = totalInventoryValue; }

    public Map<String, BigDecimal> getValueByCategory() { return valueByCategory; }
    public void setValueByCategory(Map<String, BigDecimal> valueByCategory) { this.valueByCategory = valueByCategory; }
}
