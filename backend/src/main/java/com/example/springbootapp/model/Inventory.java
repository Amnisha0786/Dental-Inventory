package com.example.springbootapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "inventory")
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Min(value = 0, message = "Current stock must be non-negative")
    @Column(name = "current_stock", nullable = false)
    private Integer currentStock = 0;

    @Min(value = 0, message = "Minimum stock must be non-negative")
    @Column(name = "min_stock", nullable = false)
    private Integer minStock = 10;

    @Min(value = 0, message = "Maximum stock must be non-negative")
    @Column(name = "max_stock", nullable = false)
    private Integer maxStock = 100;

    @Column(name = "last_updated")
    private LocalDateTime lastUpdated;

    @PrePersist
    @PreUpdate
    protected void onUpdate() {
        lastUpdated = LocalDateTime.now();
    }

    // Getters and setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Product getProduct() { return product; }
    public void setProduct(Product product) { this.product = product; }

    public Integer getCurrentStock() { return currentStock; }
    public void setCurrentStock(Integer currentStock) { this.currentStock = currentStock; }

    public Integer getMinStock() { return minStock; }
    public void setMinStock(Integer minStock) { this.minStock = minStock; }

    public Integer getMaxStock() { return maxStock; }
    public void setMaxStock(Integer maxStock) { this.maxStock = maxStock; }

    public LocalDateTime getLastUpdated() { return lastUpdated; }

    public String getStockStatus() {
        if (currentStock <= 0) {
            return "OUT_OF_STOCK";
        } else if (currentStock <= minStock) {
            return "LOW_STOCK";
        } else if (currentStock >= maxStock) {
            return "OVERSTOCK";
        } else {
            return "IN_STOCK";
        }
    }
}
