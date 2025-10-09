package com.example.springbootapp.service;

import com.example.springbootapp.dto.InventoryDTO;
import com.example.springbootapp.exception.ResourceNotFoundException;
import com.example.springbootapp.model.Inventory;
import com.example.springbootapp.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class InventoryService {
    
    @Autowired
    private InventoryRepository inventoryRepository;
    
    public List<InventoryDTO> getAllInventory(String status) {
        List<Inventory> inventory;
        
        if (status != null) {
            switch (status.toUpperCase()) {
                case "LOW":
                    inventory = inventoryRepository.findLowStockItems();
                    break;
                case "CRITICAL":
                    inventory = inventoryRepository.findCriticalStockItems();
                    break;
                case "OVERSTOCKED":
                    inventory = inventoryRepository.findOverstockedItems();
                    break;
                default:
                    inventory = inventoryRepository.findAll();
            }
        } else {
            inventory = inventoryRepository.findAll();
        }
        
        return inventory.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    public InventoryDTO getInventoryById(Integer id) {
        Inventory inventory = inventoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Inventory not found with id: " + id));
        return convertToDTO(inventory);
    }
    
    public InventoryDTO updateInventory(Integer id, InventoryDTO inventoryDTO) {
        Inventory inventory = inventoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Inventory not found with id: " + id));
        
        inventory.setCurrentStock(inventoryDTO.getCurrentStock());
        if (inventoryDTO.getMinStock() != null) {
            inventory.setMinStock(inventoryDTO.getMinStock());
        }
        if (inventoryDTO.getMaxStock() != null) {
            inventory.setMaxStock(inventoryDTO.getMaxStock());
        }
        
        Inventory updated = inventoryRepository.save(inventory);
        return convertToDTO(updated);
    }
    
    public List<InventoryDTO> getLowStockItems() {
        return inventoryRepository.findLowStockItems().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    public List<InventoryDTO> getInventoryByFrequency(String frequency) {
        return inventoryRepository.findByReorderFrequency(frequency).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    private InventoryDTO convertToDTO(Inventory inventory) {
        InventoryDTO dto = new InventoryDTO();
        dto.setId(inventory.getId());
        dto.setProductId(inventory.getProduct().getId());
        dto.setProductName(inventory.getProduct().getName());
        dto.setCurrentStock(inventory.getCurrentStock());
        dto.setMinStock(inventory.getMinStock());
        dto.setMaxStock(inventory.getMaxStock());
        dto.setStockStatus(inventory.getStockStatus());
        dto.setLastUpdated(inventory.getLastUpdated());
        return dto;
    }
}