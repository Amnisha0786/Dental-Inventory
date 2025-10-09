package com.example.springbootapp.controller;

import com.example.springbootapp.dto.StockMovementDTO;
import com.example.springbootapp.service.StockMovementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/stock-movements")
@Validated
public class StockMovementController {

    @Autowired
    private StockMovementService stockMovementService;

    @GetMapping
    public ResponseEntity<List<StockMovementDTO>> getAllMovements(
            @RequestParam(required = false) Integer productId,
            @RequestParam(required = false) String movementType,
            @RequestParam(required = false) Integer limit) {
        List<StockMovementDTO> movements = stockMovementService.getAllMovements(
                productId, movementType, limit);
        return ResponseEntity.ok(movements);
    }

    @PostMapping
    public ResponseEntity<StockMovementDTO> createMovement(
            @Valid @RequestBody StockMovementDTO movementDTO) {
        StockMovementDTO created = stockMovementService.createMovement(movementDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<List<StockMovementDTO>> getMovementsByProductId(
            @PathVariable Integer id) {
        List<StockMovementDTO> movements = stockMovementService.getMovementsByProductId(id);
        return ResponseEntity.ok(movements);
    }
}