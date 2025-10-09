package com.example.springbootapp.controller;

import com.example.springbootapp.dto.ProductDTO;
import com.example.springbootapp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/products")
@Validated
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProducts(
            @RequestParam(required = false) Integer categoryId,
            @RequestParam(required = false) Integer supplierId,
            @RequestParam(required = false) String reorderFrequency,
            @RequestParam(required = false) String search) {
        
        List<ProductDTO> products = productService.getAllProducts(
            categoryId, supplierId, reorderFrequency, search);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Integer id) {
        ProductDTO product = productService.getProductById(id);
        return ResponseEntity.ok(product);
    }

    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@Valid @RequestBody ProductDTO productDTO) {
        ProductDTO created = productService.createProduct(productDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> updateProduct(
            @PathVariable Integer id,
            @Valid @RequestBody ProductDTO productDTO) {
        ProductDTO updated = productService.updateProduct(id, productDTO);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Integer id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}