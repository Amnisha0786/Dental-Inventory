package com.example.springbootapp.dto;

import java.math.BigDecimal;

public class StockValueDTO {
    private String category;
    private BigDecimal totalValue;
    private Integer totalItems;

    public StockValueDTO() {}

    public StockValueDTO(String category, BigDecimal totalValue, Integer totalItems) {
        this.category = category;
        this.totalValue = totalValue;
        this.totalItems = totalItems;
    }

    // Getters and setters
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public BigDecimal getTotalValue() { return totalValue; }
    public void setTotalValue(BigDecimal totalValue) { this.totalValue = totalValue; }

    public Integer getTotalItems() { return totalItems; }
    public void setTotalItems(Integer totalItems) { this.totalItems = totalItems; }

    public void setItemCount(int itemCount) { this.totalItems = itemCount; }
}
