package com.example.springbootapp.controller;

import com.example.springbootapp.dto.InventoryDTO;
import com.example.springbootapp.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@Validated
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @GetMapping
    public ResponseEntity<List<InventoryDTO>> getAllInventory(
            @RequestParam(required = false) String status) {
        List<InventoryDTO> inventory = inventoryService.getAllInventory(status);
        return ResponseEntity.ok(inventory);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InventoryDTO> getInventoryById(@PathVariable Integer id) {
        InventoryDTO inventory = inventoryService.getInventoryById(id);
        return ResponseEntity.ok(inventory);
    }

    @PutMapping("/{id}")
    public ResponseEntity<InventoryDTO> updateInventory(
            @PathVariable Integer id,
            @Valid @RequestBody InventoryDTO inventoryDTO) {
        InventoryDTO updated = inventoryService.updateInventory(id, inventoryDTO);
        return ResponseEntity.ok(updated);
    }

    @GetMapping("/low-stock")
    public ResponseEntity<List<InventoryDTO>> getLowStockItems() {
        List<InventoryDTO> items = inventoryService.getLowStockItems();
        return ResponseEntity.ok(items);
    }

    @GetMapping("/by-frequency/{frequency}")
    public ResponseEntity<List<InventoryDTO>> getInventoryByFrequency(
            @PathVariable String frequency) {
        List<InventoryDTO> items = inventoryService.getInventoryByFrequency(frequency);
        return ResponseEntity.ok(items);
    }
}