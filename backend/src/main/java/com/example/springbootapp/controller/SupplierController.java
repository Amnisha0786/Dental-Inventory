package com.example.springbootapp.controller;

import com.example.springbootapp.dto.SupplierDTO;
import com.example.springbootapp.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/suppliers")
@Validated
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    @GetMapping
    public ResponseEntity<List<SupplierDTO>> getAllSuppliers() {
        List<SupplierDTO> suppliers = supplierService.getAllSuppliers();
        return ResponseEntity.ok(suppliers);
    }

    @PostMapping
    public ResponseEntity<SupplierDTO> createSupplier(@Valid @RequestBody SupplierDTO supplierDTO) {
        SupplierDTO created = supplierService.createSupplier(supplierDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SupplierDTO> updateSupplier(
            @PathVariable Integer id,
            @Valid @RequestBody SupplierDTO supplierDTO) {
        SupplierDTO updated = supplierService.updateSupplier(id, supplierDTO);
        return ResponseEntity.ok(updated);
    }
}