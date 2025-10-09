package com.example.springbootapp.service;

import com.example.springbootapp.dto.StockMovementDTO;
import com.example.springbootapp.exception.BadRequestException;
import com.example.springbootapp.exception.ResourceNotFoundException;
import com.example.springbootapp.model.Inventory;
import com.example.springbootapp.model.MovementType;
import com.example.springbootapp.model.Product;
import com.example.springbootapp.model.StockMovement;
import com.example.springbootapp.repository.InventoryRepository;
import com.example.springbootapp.repository.ProductRepository;
import com.example.springbootapp.repository.StockMovementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class StockMovementService {
    
    @Autowired
    private StockMovementRepository stockMovementRepository;
    
    @Autowired
    private ProductRepository productRepository;
    
    @Autowired
    private InventoryRepository inventoryRepository;
    
    public List<StockMovementDTO> getAllMovements(Integer productId, String movementType, Integer limit) {
        List<StockMovement> movements;
        
        if (productId != null) {
            movements = stockMovementRepository.findByProductIdOrderByCreatedAtDesc(productId);
        } else if (movementType != null) {
            movements = stockMovementRepository.findByMovementType(movementType);
        } else {
            movements = stockMovementRepository.findAllByOrderByCreatedAtDesc();
        }
        
        if (limit != null && limit > 0) {
            movements = movements.stream().limit(limit).collect(Collectors.toList());
        }
        
        return movements.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    public List<StockMovementDTO> getMovementsByProductId(Integer productId) {
        return stockMovementRepository.findByProductIdOrderByCreatedAtDesc(productId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    public StockMovementDTO createMovement(StockMovementDTO movementDTO) {
        Product product = productRepository.findById(movementDTO.getProductId())
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        
        Inventory inventory = inventoryRepository.findByProductId(movementDTO.getProductId());
        if (inventory == null) {
            throw new ResourceNotFoundException("Inventory not found for product");
        }
        
        // Create movement record
        StockMovement movement = new StockMovement();
        movement.setProduct(product);
        movement.setQuantity(movementDTO.getQuantity());
        movement.setMovementType(MovementType.valueOf(movementDTO.getMovementType()));
        movement.setNotes(movementDTO.getNotes());
        
        // Update inventory based on movement type
        int newStock;
        if (movementDTO.getMovementType().equals("IN")) {
            newStock = inventory.getCurrentStock() + movementDTO.getQuantity();
        } else if (movementDTO.getMovementType().equals("OUT")) {
            newStock = inventory.getCurrentStock() - movementDTO.getQuantity();
            if (newStock < 0) {
                throw new BadRequestException("Insufficient stock for product");
            }
        } else {
            throw new BadRequestException("Invalid movement type");
        }
        inventory.setCurrentStock(newStock);
        
        inventoryRepository.save(inventory);
        StockMovement saved = stockMovementRepository.save(movement);
        
        return convertToDTO(saved);
    }
    
    private StockMovementDTO convertToDTO(StockMovement movement) {
        StockMovementDTO dto = new StockMovementDTO();
        dto.setId(movement.getId());
        dto.setProductId(movement.getProduct().getId());
        dto.setProductName(movement.getProduct().getName());
        dto.setQuantity(movement.getQuantity());
        dto.setMovementType(movement.getMovementType().name());
        dto.setNotes(movement.getNotes());
        dto.setCreatedAt(movement.getCreatedAt());
        return dto;
    }
}
