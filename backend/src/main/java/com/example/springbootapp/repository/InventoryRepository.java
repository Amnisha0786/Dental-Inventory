package com.example.springbootapp.repository;

import com.example.springbootapp.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Integer> {

    Inventory findByProductId(Integer productId);

    @Query("SELECT i FROM Inventory i WHERE i.currentStock <= i.minStock")
    List<Inventory> findLowStockItems();

    @Query("SELECT i FROM Inventory i WHERE i.currentStock <= i.minStock * 0.5")
    List<Inventory> findCriticalStockItems();

    @Query("SELECT i FROM Inventory i WHERE i.currentStock >= i.maxStock")
    List<Inventory> findOverstockedItems();

    @Query("SELECT i FROM Inventory i WHERE i.product.reorderFrequency = :reorderFrequency")
    List<Inventory> findByReorderFrequency(String reorderFrequency);
}
