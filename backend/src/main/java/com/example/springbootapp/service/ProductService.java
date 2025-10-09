package com.example.springbootapp.service;

import com.example.springbootapp.dto.ProductDTO;
import com.example.springbootapp.model.Product;
import com.example.springbootapp.model.ReorderFrequency;
import com.example.springbootapp.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<ProductDTO> getAllProducts(Integer categoryId, Integer supplierId, String reorderFrequency, String search) {
        List<Product> products = productRepository.findAll();
        return products.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public ProductDTO getProductById(Integer id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
        return convertToDTO(product);
    }

    public ProductDTO createProduct(ProductDTO productDTO) {
        Product product = convertToEntity(productDTO);
        Product saved = productRepository.save(product);
        return convertToDTO(saved);
    }

    public ProductDTO updateProduct(Integer id, ProductDTO productDTO) {
        Product existing = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
        existing.setName(productDTO.getName());
        existing.setDescription(productDTO.getDescription());
        existing.setUnitCost(productDTO.getUnitCost());
        existing.setReorderFrequency(ReorderFrequency.valueOf(productDTO.getReorderFrequency()));
        Product saved = productRepository.save(existing);
        return convertToDTO(saved);
    }

    public void deleteProduct(Integer id) {
        productRepository.deleteById(id);
    }

    private ProductDTO convertToDTO(Product product) {
        ProductDTO dto = new ProductDTO();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setDescription(product.getDescription());
        dto.setUnitCost(product.getUnitCost());
        dto.setReorderFrequency(product.getReorderFrequency().name());
        dto.setCreatedAt(product.getCreatedAt());
        return dto;
    }

    private Product convertToEntity(ProductDTO dto) {
        Product product = new Product();
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setUnitCost(dto.getUnitCost());
        product.setReorderFrequency(ReorderFrequency.valueOf(dto.getReorderFrequency()));
        return product;
    }
}
